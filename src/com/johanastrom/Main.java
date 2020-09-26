package com.johanastrom;

import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static CollectionHandler collectionHandler = new CollectionHandler();

    public static void main(String[] args) {
        System.out.println("Welcome!\n");
        menu();
        boolean loop = true;

        while (loop) {

            System.out.println("\nChoose an option: \n");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 0:
                    menu();
                    break;

                case 1:
                    createCollection();
                    break;

                case 2:
                    addAlbum();
                    break;

                case 3:
                    removeAlbum();
                    break;

                case 4:
                    changeAlbum();
                    break;

                case 5:
                    queryAlbum();
                    break;

                case 6:
                    viewCollection();
                    break;

                case 7:
                    collectionHandler.displayAll();
                    break;

                case 8:
                    loop = false;
                    break;
            }
        }
    }

    private static void createCollection() {
        System.out.print("Enter name of the Music Collection: ");
        String name = sc.nextLine();
        collectionHandler.createCollection(name);
    }

    private static void addAlbum() {
        System.out.print("Enter collection to add album to: ");
        String collectionName = sc.nextLine();
        MusicCollection chosenCollection = collectionHandler.findCollection(collectionName);
        if (chosenCollection == null) {
            System.out.println("Collection not found.");
        } else {
            Album albumToAdd = enterAlbumDetails(chosenCollection);
            if (albumToAdd!=null) {
                chosenCollection.addAlbum(albumToAdd);
                System.out.println(albumToAdd.getType() + " " + albumToAdd.getName() +  " added to " + chosenCollection.getName() + ".");
            }
            else {
                System.out.println("Album already in list.");
            }
        }
    }

    private static void removeAlbum() {
        System.out.print("Enter title of album to remove: ");
        String title = sc.nextLine();
        System.out.print("Enter artist of album to remove: ");
        String artist = sc.nextLine();
        Album albumToRemove = collectionHandler.queryAll(title, artist);
        MusicCollection chosenCollection = collectionHandler.findCollectionByAlbum(albumToRemove);
        if (albumToRemove != null) {
            System.out.println(albumToRemove.getType() + " " + albumToRemove.getName() + " removed from " + chosenCollection.getName() + ".");
            chosenCollection.removeAlbum(albumToRemove);
        } else {
            System.out.println("Album not found.");
        }
    }

    private static void changeAlbum() {
        System.out.print("Enter title of album to be replaced: ");
        String title = sc.nextLine();
        System.out.print("Enter artist of album to be replaced: ");
        String artist = sc.nextLine();
        Album currentAlbum = collectionHandler.queryAll(title, artist);
        if (currentAlbum == null) {
            System.out.println("Album not found.");
        } else {
            MusicCollection chosenCollection = collectionHandler.findCollectionByAlbum(currentAlbum);
            System.out.println("Enter details for the new album:");
            Album newAlbum = enterAlbumDetails(chosenCollection);
            if (newAlbum!=null) {
                chosenCollection.changeAlbum(currentAlbum, newAlbum);
            }
            else {
                System.out.println("Album to be replaced is the same as the new album - no replacement executed.");
            }
        }
    }

    private static void queryAlbum() {
        System.out.print("Enter album title to search for: ");
        String title = sc.nextLine();
        System.out.print("Enter album artist: ");
        String artist = sc.nextLine();
        Album queriedAlbum = collectionHandler.queryAll(title, artist);
        if (queriedAlbum == null) {
            System.out.println("Album not found.");
        } else {
            MusicCollection queriedCollection = collectionHandler.findCollectionByAlbum(queriedAlbum);
            System.out.println("Album found in " + queriedCollection.getName() + ": ");
            System.out.println(queriedAlbum);
            System.out.println("Songs: ");
            queriedAlbum.displaySongs();
        }
    }

    private static void viewCollection() {
        System.out.print("Enter name of Music Collection to view: ");
        String name = sc.nextLine();
        MusicCollection chosenCollection = collectionHandler.findCollection(name);
        if (chosenCollection == null) {
            System.out.println("Collection not found.");
        } else {
            chosenCollection.displayAlbums();
        }
    }

    private static void menu() {

        System.out.println("press 0 to view menu\n" +
                "press 1 to create a Music Collection\n" +
                "press 2 to add an album\n" +
                "press 3 to remove an album\n" +
                "press 4 to change an album\n" +
                "press 5 to search for and view an album\n" +
                "press 6 to view a Music Collection\n" +
                "press 7 to view all Music Collections\n" +
                "press 8 to quit.");
    }

    //Anropas när ett nytt album ska skapas. Användaren fyller i albumets uppgifter, och ett nytt album-objekt skapas.
    private static Album enterAlbumDetails(MusicCollection chosenCollection) {
        boolean loop = true;
        int albumChoice = 0;

        while (loop) {

            System.out.print("Enter album title: ");
            String title = sc.nextLine();
            System.out.print("Enter artist: ");
            String artist = sc.nextLine();

            if (chosenCollection.queryAlbum(title, artist) != null) {
                return null;
            } else {
                System.out.println("Press 1 for vinyl\n" +
                        "press 2 for CD\n" +
                        "press 3 for digital album.");
                albumChoice = sc.nextInt();
                sc.nextLine();
                if (albumChoice < 1 || albumChoice > 3) {
                    System.out.println("Invalid choice");
                } else {
                    loop = false;
                }

                if (albumChoice == 1) {
                    int vinylChoice = 0;
                    boolean isEP = true;
                    loop = true;
                    while (loop) {
                        System.out.println("Press 1 for EP\n" +
                                "press 2 for LP ");
                        vinylChoice = sc.nextInt();
                        sc.nextLine();
                        if (vinylChoice < 1 || vinylChoice > 2) {
                            System.out.println("Invalid choice.");
                        } else {
                            loop = false;
                        }
                    }
                    if (vinylChoice == 1) {
                        isEP = true;
                    } else if (vinylChoice == 2) {
                        isEP = false;
                    }
                    Album newVinyl = new Vinyl(title, artist, isEP);
                    addSong(newVinyl);
                    return newVinyl;
                } else if (albumChoice == 2) {
                    Album newCD = new CompactDisc(title, artist);
                    addSong(newCD);
                    return newCD;
                } else if (albumChoice == 3) {
                    System.out.print("Enter platform name: (e.g. iTunes, Amazon Music...)");
                    String platform = sc.nextLine();
                    System.out.print("Enter format: ");
                    String format = sc.nextLine();

                    Album newDigital = new Digital(title, artist, platform, format);
                    addSong(newDigital);
                    return newDigital;
                }
            }
        }
        return null;
    }

    private static void addSong(Album album) {
        boolean loop = true;
        while (loop) {
            System.out.println("Press 1 to add a song\n" +
                    "press 0 to finish.");
            int songChoice = sc.nextInt();
            sc.nextLine();

            if (songChoice == 1) {
                System.out.print("Enter song title: ");
                String title = sc.nextLine();
                System.out.print("Enter length in seconds: ");
                int lengthInSeconds = sc.nextInt();
                album.addSong(new Song(title, lengthInSeconds));
            } else if (songChoice == 0) {
                loop = false;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
}




