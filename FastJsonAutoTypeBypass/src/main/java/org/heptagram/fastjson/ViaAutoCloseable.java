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