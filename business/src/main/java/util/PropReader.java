package util;

import java.io.*;
import java.util.Properties;

public class PropReader {
    public static Properties getUserMailProps() {
        File file=new File("business/src/main/resources/userMail.properties");
        FileInputStream fis;
        Properties property = new Properties();
        try {
            fis = new FileInputStream(file);
            property.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return property;
    }
}
