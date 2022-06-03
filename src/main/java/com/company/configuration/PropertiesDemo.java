package com.company.configuration;

import java.io.IOException;
import java.util.Properties;

/**
 * @Description PropertiesDemo properties配置文件
 * @Author stopping
 * @date: 2021/4/14 15:56
 */

public class PropertiesDemo {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.setProperty("name","stopping");
        properties.setProperty("age","24");
        properties.storeToXML(System.out,"comment","UTF-8");
    }
}
