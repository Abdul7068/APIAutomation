package org.example.utils;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class YamlReader {

    public Map<String, Object> readKey(){
        Yaml yaml = new Yaml();
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("org/example/resources/TestDataYaml.yaml");
        Map<String, Object> obj = yaml.load(inputStream);
           return obj;
    }

}
