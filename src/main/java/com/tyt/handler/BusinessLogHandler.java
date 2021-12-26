package com.tyt.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tyt.BussinessLogRun;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.weaver.patterns.HasMemberTypePattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author zhy
 * @since 2021/12/26 10:08
 */
@Aspect
@Component
public class BusinessLogHandler {
    @Autowired
    private BusinessLogService BusinessLogService;
    @Pointcut("@annotation(com.tyt.handler.CollectionLog)")
    public void pointcut() {
    }
    @Around("pointcut()")
    public Object handle(ProceedingJoinPoint joinPoint) throws Throwable {
        BusinessLog businessLog = new BusinessLog();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //IP地址
        String ipAddr = getRemoteHost(request);
        businessLog.setRequestIP(ipAddr);
        // rui
        String uri = request.getRequestURL().toString();
        businessLog.setUri(uri);
        // 请求方式
        String method = request.getMethod();
        businessLog.setMethod(method);
        // 获取注解
        CollectionLog logArchivesAnnotation = getLogArchivesAnnotation(joinPoint);
        // 参数集合
        Object[] args = joinPoint.getArgs();
        // 获取需要的参数
        Map<String, Object> reqParamMap = reqParam(logArchivesAnnotation, args, uri);
        businessLog.setParams(reqParamMap);

        // 执行方法
        Object result = joinPoint.proceed();
        // 返回参数
        String respParam = postHandle(result);
        Map<String, Object> resultMap = JSONObject.parseObject(respParam);

        if (!ObjectUtils.isEmpty(logArchivesAnnotation.resultField())){

            Object success = resultMap.get(logArchivesAnnotation.resultField());
            if (success.toString().equalsIgnoreCase(logArchivesAnnotation.sucessSign())){
                businessLog.setSuccess(true);
            }else{
                businessLog.setSuccess(false);
            }
        }
        businessLog.setResultInfo(resultMap.get(logArchivesAnnotation.infoField()).toString());
        handlerLog(businessLog);
        return result;
    }
    /**
     * 获取目标主机的ip
     *
     * @param request
     * @return
     */
    private String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }
    /**
     * 获取日志注解
     *
     * @param joinPoint 切点
     * @return 日志注解
     */
    private CollectionLog getLogArchivesAnnotation(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        return AnnotationUtils.findAnnotation(method, CollectionLog.class);
    }
    /**
     * 需要记录的请求参数
     *
     */
    private Map<String, Object> reqParam(CollectionLog paramPosition, Object[] args, String uri){

        if (BusinessLogEnum.ParamPosition.URI.equals(paramPosition.paramPosition())){
           return reqParamUri(uri);
        }
        if (BusinessLogEnum.ParamPosition.PARAM.equals(paramPosition.paramPosition())){
            return reqParamParam(args, paramPosition.paramFields());
        }
        return null;
    }

    private Map<String, Object> reqParamUri(String uri){
        String[] splits = uri.split("/");
        Map<String, Object> map = new HashMap<>();
        map.put("uri", splits[splits.length-1]);
        return map;
    }
    private Map<String, Object> reqParamParam(Object[] args, String[] paramFields){
        if (paramFields.length < 1){
            return null;
        }
        Map<String, Object> map = new HashMap<>();

        for (String paramField : paramFields) {
            for (Object arg : args) {
                Map<String, Object> argmap = JSONObject.parseObject(JSONObject.toJSONString(arg));
                Object field = argmap.get(paramField);

                if (null == field){
                    break;
                }
                map.put(paramField, field.toString());
            }
        }
        return map;
    }
    /**
     * 返回数据
     *
     * @param retVal
     * @return
     */
    private String postHandle(Object retVal) {
        if (null == retVal) {
            return "";
        }
        return  JSON.toJSONString(retVal);
    }

    // 处理日志
    private void handlerLog(BusinessLog businessLog){
        BusinessLogService.deal(businessLog);
    }
}
