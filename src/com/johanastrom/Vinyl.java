package com.johanastrom;

public class Vinyl extends Album {

    private boolean isEP;
    private Song song = new Song("title", 0);

    public Vinyl(String name, String artist, boolean isEP) {
        super(name, artist);
        this.isEP = isEP;
    }

    @Override
    public String getType() {
        if (isEP) {
            return "EP";
        } else {
            return "LP";
        }
    }

    //Begränsar albumets längd för EP resp. LP
    @Override
    public boolean addSong(Song song) {
        int maxLength = 0;
        if (isEP) {
            maxLength = 1800;
        } else {
            maxLength = 2760;
        }
        if (song.getLengthInSeconds() + this.getLengthInSeconds() > maxLength) {
            System.out.println("The song is too long to fit on the album!");
            return false;
        } else {
            super.addSong(song);
            return true;
        }
    }

    @Override
    public String toString() {
        String format;
        if (isEP) {
            format = "EP";
        } else {
            format = "LP";
        }
        return (super.toString() + ", " + format);
    }
}