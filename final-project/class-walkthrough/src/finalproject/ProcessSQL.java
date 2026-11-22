/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;

import java.io.*;
import java.net.*;

/**
 * @author User
 */
public class ProcessSQL implements Runnable {

    private Socket connection;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private String command;

    public ProcessSQL(Socket connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        try {
            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input = new ObjectInputStream(connection.getInputStream());

            command = (String) input.readObject();

            LoggerUtil.logCommand(command);

            if (command.indexOf("#select") == 0) {
                Service s = new SQLSelectService(command, output);
                s.doWork();
            } else if (command.indexOf("#update") == 0) {
                Service s = new SQLUpdateService(command, output);
                s.doWork();
            } else if (command.indexOf("#insert") == 0) {
                Service s = new SQLInsertService(command, output);
                s.doWork();
            } else if (command.indexOf("#delete") == 0) {
                Service s = new SQLDeleteService(command, output);
                s.doWork();
            }
            connection.close();
        } catch (Exception e) {
            System.out.println("error in process");
        }
    }
}
