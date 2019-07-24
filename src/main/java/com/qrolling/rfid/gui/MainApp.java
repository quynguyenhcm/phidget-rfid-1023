package com.qrolling.rfid.gui;

import com.qrolling.rfid.config.AppConfig;
import com.qrolling.rfid.core.RFIDInterface;
import com.qrolling.rfid.utils.ConfigurationHelper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Quy Nguyen (nguyenledinhquy@gmail.com) on 10/29/17
 */
public class MainApp {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        RFIDInterface reader = context.getBean(RFIDInterface.class);
        int rfidPort = ConfigurationHelper.getInt("rfid.port");
        reader.startListening(rfidPort);
        reader.close(rfidPort);
        context.close();
    }
}
