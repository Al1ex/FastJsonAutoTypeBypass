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