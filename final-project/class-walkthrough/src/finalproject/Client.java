/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author User
 */
public class Client {

    static Socket client;

    static ObjectOutputStream output;
    static ObjectInputStream input;

    GUIClient gc;

    private int currentID = -1;

    public Client(GUIClient gc) {
        this.gc = gc;
    }

    public int getCurrentID() {
        return currentID;
    }

    public void setCurrentID(int currentID) {
        this.currentID = currentID;
    }

    public void connectToServer() {
        try {
            client = new Socket(InetAddress.getByName("127.0.0.1"), 3202);
            output = new ObjectOutputStream(client.getOutputStream());
            output.flush();
            input = new ObjectInputStream(client.getInputStream());
            System.out.println("Connected to server!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String createLookupCommand(String command, int id) {
        return command + "|" + id;
    }

    public String createUpdateCommand(String command, String title, String artist, String album, String year, int genreId) {
        return command + "|" + getCurrentID() + "|" + title + "|" + artist + "|" + album + "|" + year + "|" + genreId;
    }

    public String createInsertCommand(String command, String title, String artist, String album, String year, int genreId) {
        return command + "|" + title + "|" + artist + "|" + album + "|" + year + "|" + genreId;
    }

    public String createDeleteCommand(String command, int id) {
        return command + "|" + id;
    }

    public void sendCommand(String command) {
        try {
            connectToServer();
            output.writeObject(command);
            String messageIn = (String) input.readObject();
            if (command.indexOf("#select") == 0) {
                sendToGUI(messageIn);
                gc.setGoodMessage("Lookup successful");
            } else {
                gc.setGoodMessage(messageIn);
            }
            disconnectFromServer();
        } catch (Exception e) {
            gc.setBadError("Error Communicating with Server");
            System.out.println("send command didnt work");
        }
    }

    public void disconnectFromServer() {
        try {
            client.close();
        } catch (Exception e) {
            System.out.println("disconnect failed");
        }
    }

    public void sendToGUI(String command) {

        String[] commandArray = command.split("\\|", 6);
        Song s = new Song(Integer.parseInt(commandArray[0]), commandArray[1], commandArray[2], commandArray[3], Integer.parseInt(commandArray[4]), Integer.parseInt(commandArray[5]));
        if (s.getId() == 0) {
            gc.setBadError("Invalid ID");
            return;
        }
        setCurrentID(s.getId());
        gc.setTxtTitle(s.getTitle());
        gc.setTxtArtist(s.getArtist());
        gc.setTxtAlbum(s.getAlbum());
        gc.setTxtYear("" + s.getReleaseYear());
        if (s.getGenreId() > 0) {
            gc.setComboGenre(s.getGenreId() - 1);
        }
    }

}
