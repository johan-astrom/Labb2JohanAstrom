package com.johanastrom;

public class Digital extends Album {

    private String platform;
    private String format;

    public Digital(String name, String artist, String platform, String format) {
        super(name, artist);
        this.platform = platform;
        this.format = format;
    }

    @Override
    public String getType() {
        return "Digital album";
    }

    @Override
    public boolean addSong(Song song) {
       if (super.addSong(song)){
           return true;
       }
       return false;
    }

    @Override
    public String toString() {
        return (super.toString() + ", Digital album (" + this.platform + "), " + this.format);
    }

}
