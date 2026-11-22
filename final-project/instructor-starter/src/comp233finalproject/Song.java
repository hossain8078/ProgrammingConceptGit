/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comp233finalproject;

/**
 *
 * @author Instructor
 */
public class Song {
    private int id;
    private String title;
    private String artist;
    private int releaseYear;
    private int genreId;
    private String album;
    
    public Song()
    {
        
    }
    
    public Song(int id, String title, String artist, String album, int releaseYear, int genreId)
    {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.releaseYear = releaseYear;
        this.genreId = genreId;
        this.album = album;
    }

    public Song(String title, String artist, int releaseYear, int genreId, String album) {
        this.title = title;
        this.artist = artist;
        this.releaseYear = releaseYear;
        this.genreId = genreId;
        this.album = album;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
    
    public String toString()
    {
        return String.format("""
                             Id #%d
                             Title: %s
                             Artist: %s""", id, title, artist);
    }
    
    public String getFullInfo()
    {
        return String.format("""
                             Id: %d
                             Title: %s
                             Artist: %s
                             Release Year: %d
                             Genre Id: %d
                             Album: %s""", id, title, artist, releaseYear, genreId, album);
    }
}
