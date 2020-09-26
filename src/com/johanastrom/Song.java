package com.johanastrom;

public class Song {

    private String title;
    private int lengthInSeconds;

    public Song(String title, int lengthInSeconds) {
        this.title = title;
        this.lengthInSeconds = lengthInSeconds;
    }

    public String getTitle() {
        return title;
    }

    public int getLengthInSeconds() {
        return this.lengthInSeconds;
    }


    //Returnerar lengthInSeconds formaterat som minuter och sekunder (00:00)
    public String calculateSongLength() {
        int minutes = lengthInSeconds / 60;
        int seconds = lengthInSeconds % 60;

        return (String.format("%02d", minutes) + ":" + String.format("%02d", seconds));

    }
}
