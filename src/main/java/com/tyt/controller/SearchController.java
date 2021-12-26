package com.tyt.controller;

import com.tyt.entiy.R;
import com.tyt.entiy.Search;
import com.tyt.handler.BusinessLogEnum;
import com.tyt.handler.CollectionLog;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhy
 * @since 2021/12/26 9:55
 */
@RestController
public class SearchController {

    @GetMapping("/doget")
    @CollectionLog(paramPosition = BusinessLogEnum.ParamPosition.NULL)
    public R<String> doGet() {
        System.out.println("doget");
        return R.success("查询成功", "doget");
    }

    @PostMapping("/dopost")
    @CollectionLog(paramPosition = BusinessLogEnum.ParamPosition.NULL)
    public R<String> doPost() {
        return R.success("查询成功", "dopost");
    }

    @GetMapping("/doget/{id}")
    @CollectionLog(paramPosition = BusinessLogEnum.ParamPosition.URI)
    public R<String> doGet(@PathVariable String id) {
        System.out.println(id);
        System.out.println("doget");
        return R.success("查询成功", "doget");
    }

    @PostMapping("/dopost2")
    @CollectionLog(paramPosition = BusinessLogEnum.ParamPosition.PARAM, paramFields = {"search","size"}, resultField="info", infoField = "info", sucessSign = "200")
    public R<String> doPost(@RequestBody Search search) {
        return R.success("查询成功", "dopost2");
    }
    @PostMapping("/dopost3")
    @CollectionLog(paramPosition = BusinessLogEnum.ParamPosition.PARAM, paramFields = {"search","size"}, resultField="info", infoField = "info", sucessSign = "200")
    public R<String> doPost3(@RequestBody Search search) {
        return R.error(500,"查询异常");
    }
}
