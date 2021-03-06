package com.jpro;

import lombok.extern.log4j.Log4j2;
import org.bson.Document;

import java.util.Map;
import java.util.Properties;

@Log4j2
public class SimpDataProc implements AbstDataProc {
    Properties context;

    private Map<String, String> colorDict;

    SimpDataProc(Properties pro) {
        context = pro;
        colorDict = ComToo.generateColorDict(context.getProperty("color.dict"));
    }

    @Override
    public Document doVerify(Document orig) {
        if (!orig.containsKey(context.getProperty("unique.field"))
        ) {
            throw new RuntimeException("lack of necessary field");
        }
        return orig;
    }

    @Override
    public Document doFilter(Document orig) {
        return orig;
    }

    @Override
    public Document generateNotifyData(Document orig) {
        return orig;
    }

    @Override
    public Document generateStorageData(Document orig) {
        orig.put("_id", orig.getString(context.getProperty("unique.field")));
        return orig;
    }
}
