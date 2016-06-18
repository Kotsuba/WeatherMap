package com.kotsuba.weathermap.app.gui;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kotsuba on 14.06.2016.
 */
public class TypeInfo {
    Map<Integer,String> typeList= new LinkedHashMap<>();

    public TypeInfo(){
        setTypeList();
    }
    private void setTypeList(){
        typeList.put(0,"Cейчас");
        typeList.put(1,"На 5 дней");
        typeList.put(2,"На 16 дней");
    }

    public List<String> getTypeList() {
        return new ArrayList<String>(typeList.values());
    }
}
