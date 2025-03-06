package com.huaan9527.mall.webapi.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huaan9527.mall.webapi.domain.Collocation;
import com.huaan9527.mall.webapi.mapper.GoodCollocationGetMapper;
import com.huaan9527.mall.webapi.service.GoodCollocationGetService;
import com.huaan9527.mall.webapi.service.operation.api.GoodsApi;
import com.huaan9527.mall.webapi.utils.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class GoodCollocationGetServiceImpl implements GoodCollocationGetService {

    @Resource
    private GoodsApi goodsApi;

    @Resource
    private GoodCollocationGetMapper goodCollocationGetMapper;

    @Override
    public ResponseEntity getGoodInDaTaoKe(String subcid, Integer page, Integer size, Integer sort) {

        Map<String, String> params = new HashMap<>();
        params.put("subcid", subcid);
        params.put("pageId", page.toString());
        params.put("pageSize", size.toString());
        params.put("sort", sort.toString());
        JSONObject jsonObject = goodsApi.loadGoods(params);


        JSONArray data1 = (JSONArray)jsonObject.get("list");

        ArrayList<Collocation> insertList = new ArrayList<>();

        for (int i = 0; i < data1.size(); i++) {

            JSONObject o = (JSONObject) data1.get(i);

            String mainPic = String.valueOf(o.get("mainPic"));

            Collocation collocation = new Collocation();
            collocation.setDescription(String.valueOf(o.get("title")));
            collocation.setImages(mainPic);
            collocation.setSourceId(String.valueOf(o.get("goodsId")));
            String marketingMainPic = String.valueOf(o.get("marketingMainPic"));

            collocation.setMainImage(marketingMainPic == null || marketingMainPic.isEmpty() ? mainPic : marketingMainPic);

            collocation.setCreatedDate(new Date());
            collocation.setUpdatedDate(new Date());
            collocation.setSourceType(String.valueOf(o.get("shopType")));
            collocation.setViewCount(0L);
            collocation.setCreatedBy(1L);
            collocation.setAppreciateCount(1L);
            collocation.setLastUpdatedBy(1L);
            insertList.add(collocation);
        }

        // 存储数据
        goodCollocationGetMapper.getGoodInDaTaoKe(insertList);

        return ResponseEntity.success(jsonObject);

    }
}
