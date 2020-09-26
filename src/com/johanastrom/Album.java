package com.johanastrom;

import java.util.ArrayList;

public abstract class Album implements MusicMedium{

    protected String name;
    protected String artist;
    protected int lengthInSeconds;
    protected ArrayList<Song> songs;

    public Album(String name, String artist) {
        this.name=name;
        this.artist=artist;
        this.songs = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public String getArtist() {
        return this.artist;
    }

    public int getLengthInSeconds() {
        return this.lengthInSeconds;
    }

    public void setLengthInSeconds(int lengthInSeconds) {
        this.lengthInSeconds = lengthInSeconds;
    }

    public abstract String getType();

    @Override
    public String toString() {
        return artist + ": " +
                name + " (" +
                calculateLength() + ")";
    }

    //Returnerar lengthInSeconds formaterat som minuter och sekunder (00:00)
    public String calculateLength() {
        int minutes = lengthInSeconds / 60;
        int seconds = lengthInSeconds % 60;

            return (String.format("%02d", minutes) + ":" + String.format("%02d", seconds));

    }

    public void displaySongs() {
        for (int i = 0; i < songs.size(); i++) {
            System.out.println((i + 1) + ": " + songs.get(i).getTitle() + " (" + songs.get(i).calculateSongLength() + ")");
        }
    }

    public boolean addSong(Song song){
        for (int i = 0; i <songs.size(); i++) {
            if (song.getTitle().equals(songs.get(i).getTitle())) {
                System.out.println("Song already added to record.");
                return false;
            }
        }
        this.songs.add(song);
        this.setLengthInSeconds(this.getLengthInSeconds() + song.getLengthInSeconds());
        System.out.println("Song added.");
        return true;
    }

}


