package com.github.parsad23.motogpapi.reader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

class PropertiesReader {

    public static Properties readPropertiesFile(String fileName) throws IOException {
        FileInputStream fis = null;
        Properties prop = null;
        try {
            fis = new FileInputStream(fileName);
            prop = new Properties();
            prop.load(fis);
        } catch(IOException e) {
            throw new IOException("Not able to read the url_configuration.properties file");
        }
        if (fis != null)
            fis.close();
        return prop;
    }
}
