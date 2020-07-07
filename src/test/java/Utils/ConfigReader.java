package Utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;
    static {
        String path="configuration.properties";
        try{
            //this line will open your file
            FileInputStream input=new FileInputStream(path);
            properties = new Properties();
            //we need to load opened file to the properties
            properties.load(input);
            input.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }//all data will be available

    /**Method takes one string as a key and it will return value from .properties file
     * @param key
     * @return
     */
    public static String getProperty(String key){
        return properties.getProperty(key);

    }
}

