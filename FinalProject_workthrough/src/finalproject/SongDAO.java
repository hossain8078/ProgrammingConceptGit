/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author User
 */
public class SongDAO {

    public Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String connectionUrl = "jdbc:sqlserver://localhost:1434;DatabaseName=Songs; "
                    + "User=javaApps;Password=Java233";

            conn = DriverManager.getConnection(connectionUrl);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }

    public ArrayList getSongs() {
        
        ArrayList songs = new ArrayList();

        Connection conn = getConnection();

        ResultSet rs = null;
        try {
            Statement stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT id, title, artist, releaseYear, genreId, album FROM Songs");

            while (rs.next()) {
                int id = Integer.parseInt(rs.getString(1));
                String title = rs.getString(2);
                String artist = rs.getString(3);
                int releaseYear = Integer.parseInt(rs.getString(4));
                int genreId = Integer.parseInt(rs.getString(5));
                String album = rs.getString(6);

                Song s = new Song(id, title, artist, album, releaseYear, genreId);

                songs.add(s);
            }
            conn.close();
        } catch (SQLException sqle) {
            System.out.println("There was a problem with the SQL when trying to get songs");
            sqle.printStackTrace();
        } catch (Exception e) {
            System.out.println("There was a problem trying to get songs");
            e.printStackTrace();
        }
        return songs;
    }

    public Song getSongById(int id) {
        
        Song s = new Song();
        
        Connection conn = getConnection();

        ResultSet rs = null;
        try {
            Statement stmt = conn.createStatement();

            String query = "SELECT id, title, artist, releaseYear, genreId, album FROM Songs WHERE id=" + id;

            rs = stmt.executeQuery(query);

            if (rs.next()) {
                int songId = Integer.parseInt(rs.getString(1));
                String title = rs.getString(2);
                String artist = rs.getString(3);
                int releaseYear = Integer.parseInt(rs.getString(4));
                int genreId = Integer.parseInt(rs.getString(5));
                String album = rs.getString(6);
                s = new Song(songId, title, artist, album, releaseYear, genreId);
            }
            conn.close();
        } catch (SQLException sqle) {
            System.out.println("There was a problem with the SQL when trying to get songs");
            sqle.printStackTrace();
        } catch (Exception e) {
            System.out.println("There was a problem trying to get songs");
            e.printStackTrace();
        }
        return s;
    }

    public void updateSongById(Song s) {
        
        Connection conn = getConnection();

        try {
            Statement stmt = conn.createStatement();

            String update = String.format("""
                                          UPDATE Songs SET Title = '%s',
                                          Artist = '%s',
                                          Album = '%s',
                                          ReleaseYear = %d,
                                          GenreId = %d
                                          WHERE id = %d""", s.getTitle(), s.getArtist(),
                                          s.getAlbum(), s.getReleaseYear(),s.getGenreId(), s.getId());

            stmt.executeUpdate(update);
            conn.close();
        } catch (SQLException sqle) {
            System.out.println("An issue occured with the SQL when trying to update a song");
            sqle.printStackTrace();
        } catch (Exception e) {
            System.out.println("An issue occured when trying to update a song");
            e.printStackTrace();
        }
    }

    public void insertSong(Song s) {
        Connection conn = getConnection();

        try {
            Statement stmt = conn.createStatement();
            String insert = String.format("""
                                          INSERT INTO Songs(title, artist, album, releaseYear, genreId)
                                          VALUES('%s', '%s', '%s', %d, %d)""", s.getTitle(), s.getArtist(),
                                           s.getAlbum(), s.getReleaseYear(), s.getGenreId());

            stmt.executeUpdate(insert);
            conn.close();
        } catch (SQLException sqle) {
            System.out.println("There was an error with the SQL when inserting song");
            sqle.printStackTrace();
        } catch (Exception e) {
            System.out.println("There was an error when inserting song");
            e.printStackTrace();
        }
    }

    public void deleteSongById(int id) {
        
        Connection conn = getConnection();

        try {
            Statement stmt = conn.createStatement();

            String delete = "DELETE FROM Songs WHERE id = " + id;

            stmt.executeUpdate(delete);

            conn.close();
        } catch (SQLException sqle) {
            System.out.println("There was an error with the SQL when deleting song");
            sqle.printStackTrace();
        } catch (Exception e) {
            System.out.println("There was an error when deleting song");
            e.printStackTrace();
        }
    }
}
