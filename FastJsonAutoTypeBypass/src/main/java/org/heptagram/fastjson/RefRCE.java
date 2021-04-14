package org.heptagram.fastjson;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.jndi.JndiLocator;
import org.apache.shiro.util.Factory;
import javax.naming.NamingException;

public class RefRCE  <T> extends JndiLocator implements Factory<T>, AutoCloseable {
    private String resourceName;

    public RefRCE() {
    }

    public T getInstance() {
        System.out.println(getClass().getName() + ".getInstance() invoke.");
        try {
            return (T) this.lookup(this.resourceName);
        } catch (NamingException var3) {
            throw new IllegalStateException("Unable to look up with jndi name '" + this.resourceName + "'.", var3);
        }
    }

    public String getResourceName() {
        System.out.println(getClass().getName() + ".getResourceName() invoke.");
        return this.resourceName;
    }

    public void setResourceName(String resourceName) {
        System.out.println(getClass().getName() + ".setResourceName() invoke.");
        this.resourceName = resourceName;
    }

    @Override
    public void close() throws Exception {
    }
}