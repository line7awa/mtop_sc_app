package com.huaan9527.mall.webapi.controller;

import com.huaan9527.mall.webapi.service.GoodCollocationGetService;
import com.huaan9527.mall.webapi.utils.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 商品推荐、逛街获取模拟接口
 * 用于存储数据
 */
@RestController
@RequestMapping("/demo/goodCollocationGetController")
public class GoodCollocationGetController {

    @Resource
    private GoodCollocationGetService goodCollocationGetService;

    @PostMapping("getGoodInDaTaoKe")
    public ResponseEntity getGoodInDaTaoKe(String subcid, Integer page, Integer size, Integer sort){

        ResponseEntity goodInDaTaoKe = goodCollocationGetService.getGoodInDaTaoKe(subcid, page, size, sort);


        return ResponseEntity.success(goodInDaTaoKe);
    }


}
