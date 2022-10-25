package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileReader {
    public static Properties readPropertiesFile(String fileName) throws IOException {
        FileInputStream fileInputStream = null;
        Properties prop = null;
        try {
            fileInputStream = new FileInputStream(fileName);
            prop = new Properties();
            prop.load(fileInputStream);
        } catch(FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        } finally {
            fileInputStream.close();
        }
        return prop;
    }
}
