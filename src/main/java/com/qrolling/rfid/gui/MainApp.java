package com.qrolling.rfid.gui;

import com.phidget22.PhidgetException;
import com.qrolling.rfid.config.AppConfig;
import com.qrolling.rfid.core.RFIDInterface;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Quy Nguyen (nguyenledinhquy@gmail.com) on 10/29/17
 */
public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        RFIDInterface reader1 = context.getBean(RFIDInterface.class);

        try {
            System.out.println("Opening and waiting 5 seconds for attachment...");
            reader1.getPhidgetReader().open(50000);

            System.out.println("\n\nGathering data for 10 seconds\n\n");
            Thread.sleep(100000);

            reader1.getPhidgetReader().close();
            System.out.println("\nClosed RFID");

        } catch (PhidgetException ex) {
            System.out.println(ex.getDescription());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        context.close();
    }
}
