/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author User
 */
public class LoggerUtil {

    public static void logCommand(String command) {
        try {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            String log = timeStamp + " | " + command;
            PrintWriter out = new PrintWriter(new FileWriter("server_log.txt", true));
            out.println(log);
            out.close();
            System.out.println(log);
        } catch (Exception e) {
            System.out.println("error writing to log file");
        }
    }
}
