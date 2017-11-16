package com.photo.api.common.result;

import java.util.HashMap;
import java.util.Map;

public class ResultMap {
    private Map<String,Object> resultMap;
    public ResultMap(String k, Object v){
    	resultMap   = new HashMap<String,Object>();
    	this.add(k, v);
    }
    
    public ResultMap add(String k, Object v){
    	resultMap.put(k, v);
    	return this;
    }
    
    public Map<String,Object> getResult(){
    	return resultMap;
    }
}
