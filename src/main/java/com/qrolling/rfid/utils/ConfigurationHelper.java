package com.qrolling.rfid.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationHelper {

    private Properties prop;

    private static ConfigurationHelper sSoleInstance;

    public synchronized static ConfigurationHelper getInstance(){
        if (sSoleInstance == null){
            sSoleInstance = new ConfigurationHelper();
        }
        return sSoleInstance;
    }
    public ConfigurationHelper(){

        if (sSoleInstance != null){
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }

        initialiseConfigurations();
    }

    private void initialiseConfigurations() {
        prop = new Properties();
        String fileName = "app.config";
        try( InputStream is = new FileInputStream(fileName)){
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getInt(String key){
        return Integer.parseInt( ConfigurationHelper.getInstance().prop.getProperty(key));
    }
}
