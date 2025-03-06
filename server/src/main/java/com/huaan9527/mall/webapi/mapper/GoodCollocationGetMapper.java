package com.huaan9527.mall.webapi.mapper;

import com.huaan9527.mall.webapi.domain.Collocation;
import com.huaan9527.mall.webapi.utils.ResponseEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface GoodCollocationGetMapper {

    void getGoodInDaTaoKe(ArrayList<Collocation> list);

}
