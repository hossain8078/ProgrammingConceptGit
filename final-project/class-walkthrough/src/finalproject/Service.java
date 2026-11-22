/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;

/**
 * @author User
 */
public abstract class Service {

    String command;

    public Service(String command) {
        this.command = command;
    }

    public abstract void doWork();
}
