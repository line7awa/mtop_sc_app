package com.huaan9527.mall.webapi.service;

import com.huaan9527.mall.webapi.utils.ResponseEntity;

public interface GoodCollocationGetService {


    ResponseEntity getGoodInDaTaoKe(String subcid, Integer page, Integer size, Integer sort);


}
