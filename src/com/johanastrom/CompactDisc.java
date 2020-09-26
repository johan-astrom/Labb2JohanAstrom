package com.johanastrom;

public class CompactDisc extends Album {

    private int playingTime;

    public CompactDisc(String name, String artist) {
        super(name, artist);
    }

    @Override
    public String getType() {
        return "CD";
    }

    //Begränsar albumets längd
    @Override
    public boolean addSong(Song song) {
        int maxLenght = 4740;
        if (song.getLengthInSeconds()+this.getLengthInSeconds() > maxLenght) {
            System.out.println("The song is too long to fit on the album!");
            return false;
        } else {
            super.addSong(song);
            return true;
        }
    }


    @Override
    public String toString() {
        return super.toString() + ", CD";
    }

}
