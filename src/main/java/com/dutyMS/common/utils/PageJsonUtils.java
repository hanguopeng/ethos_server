package com.dutyMS.common.utils;

import net.sf.json.JSONObject;

public class PageJsonUtils {
    public static JSONObject create(Object list,Integer totalCount, Integer limit,Integer page){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("list",list);
        jsonObject.put("totalCount", totalCount);//总记录数
        jsonObject.put("pageSize", limit);//每页记录数
        jsonObject.put("totalPage",(int)Math.ceil((double)totalCount/limit));//总页数
        jsonObject.put("currPage",page);//当前页码
        return jsonObject;
    }
}
