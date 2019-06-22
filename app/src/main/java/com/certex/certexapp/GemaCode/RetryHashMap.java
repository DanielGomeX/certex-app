package com.certex.certexapp.GemaCode;

import java.util.HashMap;

public class RetryHashMap
{

    private HashMap<String, String> map;

    public RetryHashMap() {
        this.map = new HashMap();
        temp = newMap();
    }

    public HashMap<String, String> getMap() {
        return map;
    }

    public void setMap(HashMap<String, String> map) {
        this.map = map;
        temp = map;
    }

    public void put(String key, String value){
        this.map.put(key, value);
        temp.put(key, value);
    }

    public String get (String key){
        return this.map.get(key);
    }

    //STATIC
    public static HashMap<String, String> temp = null;
    public static HashMap<String, String> newMap(){ return new HashMap(); }

}
