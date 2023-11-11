package org.example.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReaderUtil {

    public PropertyReaderUtil() {
    }


    public static String readkey(String key) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(new File(System.getProperty("user.dir") + "/src/main/java/org/example/resources/TestData.properties"));
        Properties properties = new Properties();
        properties.load(fileInputStream);
        if (properties.getProperty(key) == null) {
            throw new Exception("Not able to find the key");
        } else {
            return properties.getProperty(key);
        }


    }
}
