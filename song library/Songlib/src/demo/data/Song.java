/*Author: Tianle Chen Chenyan Fan*/
package demo.data;

public class Song {
    private String name;
    private String artist;
    private String album;
    private String year;

    public Song(){}

    public Song(String name, String artist){
        this.name = name;
        this.artist = artist;
    }
    public Song(String name, String artist, String album, String year){
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.year = year;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public String getYear() {
        return year;
    }


    @Override
    public String toString() {
        return "Name:" + this.name + ";Artist:" + this.artist;
    }
}
