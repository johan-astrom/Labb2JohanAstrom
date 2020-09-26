package com.johanastrom;

import java.util.ArrayList;

public class CollectionHandler {

    private ArrayList<MusicCollection> musicCollections;

    public CollectionHandler() {
        this.musicCollections = new ArrayList<>();
    }


    public boolean createCollection(String name) {
        for (int i = 0; i < musicCollections.size(); i++) {
            if (name.equalsIgnoreCase(musicCollections.get(i).getName())) {
                System.out.println("Collection already exists.");
                return false;
            }
        }
        musicCollections.add(new MusicCollection(name));
        System.out.println("Music collection " + name + " added.");
        return true;
    }

    //Söker efter ett album i alla MusicCollection
    public Album queryAll(String name, String artist) {
        for (int i = 0; i < musicCollections.size(); i++) {
            Album albumQuery = musicCollections.get(i).queryAlbum(name, artist);
            if (albumQuery != null) {
                return albumQuery;
            }
        }
        return null;
    }

    public MusicCollection findCollectionByAlbum(Album album) {
        for (int i = 0; i < musicCollections.size(); i++) {
            if (musicCollections.get(i).getAlbums().contains(album)) {
                return musicCollections.get(i);
            }
        }
        return null;
    }

    public void displayAll() {
        if (musicCollections.size() == 0) {
            System.out.println("No music collections.");
        } else {
            for (int i = 0; i < musicCollections.size(); i++) {
                System.out.println((i + 1) + ": " + musicCollections.get(i).getName() + ": ");
                musicCollections.get(i).displayAlbums();
                System.out.println();
            }
        }
    }


    public MusicCollection findCollection(String name) {
        for (int i = 0; i < musicCollections.size(); i++) {
            if (name.equalsIgnoreCase(musicCollections.get(i).getName()))
                return musicCollections.get(i);
        }
        return null;
    }


}
