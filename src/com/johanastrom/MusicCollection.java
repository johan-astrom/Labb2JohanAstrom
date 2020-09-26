package com.johanastrom;

import java.util.ArrayList;

public class MusicCollection {

    private String name;
    private ArrayList<Album> albums;

    public MusicCollection(String name) {
        this.name = name;
        this.albums=new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public boolean addAlbum(Album album) {
        if (findAlbum(album)) {
            return false;
        }

        albums.add(album);
        return true;
    }

    public void removeAlbum(Album album) {

            albums.remove(album);

    }

    public boolean changeAlbum(Album currentAlbum, Album newAlbum) {
        if (findAlbum(currentAlbum)) {
            int index = albums.indexOf(currentAlbum);
            albums.set(index, newAlbum);
            System.out.println("<" + currentAlbum + ">" + " changed to: " + "<" + newAlbum + ">");
            return true;
        } else
            System.out.println("Album not found.");
        return false;
    }

    public Album queryAlbum(String name, String artist){
        for (int i = 0; i < albums.size(); i++) {
            if (name.equalsIgnoreCase(albums.get(i).getName()) && artist.equalsIgnoreCase(albums.get(i).getArtist())){
                return albums.get(i);
            }
        }
        return null;
    }

    public void displayAlbums(){
       if (albums.size()==0){
           System.out.println("Collection empty.");
       }
       else {
           for (int i = 0; i < albums.size(); i++) {
               System.out.print((i + 1) + " - ");
               System.out.println(albums.get(i));
           }
       }
    }

    private boolean findAlbum(Album album) {
        for (int i = 0; i < albums.size(); i++) {
            if (album.getName().equalsIgnoreCase(albums.get(i).getName()) && album.getArtist().equalsIgnoreCase(albums.get(i).getArtist()))
                return true;
        }
        return false;
    }
}
