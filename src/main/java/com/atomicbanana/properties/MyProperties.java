package com.atomicbanana.properties;

import java.io.IOException;
import java.util.Properties;

public class MyProperties {
    
    private Properties properties;

    public MyProperties(String filename) throws IOException{
        ClassLoader classLoader = getClass().getClassLoader();
        properties = new Properties();
        properties.load(classLoader.getResourceAsStream(filename));
    }
    public String getKey() {
        return properties.getProperty("key");
    }

    public String getIterationCount() {
        return properties.getProperty("iteration.count");
    }

    public String getKeyLength() {
        return properties.getProperty("key.length");
    }

    public String getSalt() {
        return properties.getProperty("salt");
    }

}
