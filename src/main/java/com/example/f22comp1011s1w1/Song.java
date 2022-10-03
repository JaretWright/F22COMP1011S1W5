package com.example.f22comp1011s1w1;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Song {
    private int songID;
    private String name, genre;
    private int length, releaseYear;
    private Artist artist;

    public Song(String name, String genre, int length, int releaseYear, Artist artist) {
        setName(name);
        setGenre(genre);
        setLength(length);
        setReleaseYear(releaseYear);
        setArtist(artist);
    }

    /**
     * This is an "overloaded" constructor.  It references the other constructor, but
     * also accepts/sets a songID.
     */
    public Song(int songID, String name, String genre, int length, int releaseYear, Artist artist) {
        this(name,genre,length,releaseYear,artist);
        setSongID(songID);
    }

    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        if (songID>0)
            this.songID = songID;
        else
            throw new IllegalArgumentException("songID must be greater than 0");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name.trim();  //remove any leading or trailing whitespaces
        if (name.length()>0)
            this.name = name;
        else
            throw new IllegalArgumentException("Name must have at least 1 character");
    }

    public String getGenre() {
        return genre;
    }

    /**
     * This method will return a list of valid music genres
     */
    public static List<String> getGenres()
    {
        List<String> validGenres = Arrays.asList("pop","rock","soul","jazz",
                "metal","punk","country","electronic","dance","classical",
                "rhythm and blues","folk");

        Collections.sort(validGenres);
        return validGenres;
    }

    /**
     * Validate the genre passed in as an argument is part of the list
     * of valid genres and set the instance variable
     * @param genre
     */
    public void setGenre(String genre) {
        genre = genre.toLowerCase();

        if (getGenres().contains(genre))
            this.genre = genre;
        else
            throw new IllegalArgumentException(genre + " must be from the " +
                    "list of "+getGenres());
    }

    public int getLength() {
        return length;
    }

    /**
     * The length of the song is recorded in seconds.
     * @param length
     */
    public void setLength(int length) {
        if (length>0 && length <= 47000)
            this.length = length;
        else
            throw new IllegalArgumentException("song length must be in the " +
                    "range of 0 to 47,000");
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * The release year must be in the range of 1900 to the current
     * year
     * @param releaseYear
     */
    public void setReleaseYear(int releaseYear) {
        if (releaseYear>=1900 && releaseYear <= LocalDate.now().getYear())
            this.releaseYear = releaseYear;
        else
            throw new IllegalArgumentException("release year must be in the " +
                    "range of 1900 to " + LocalDate.now().getYear());
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String toString()
    {
        return String.format("%s - %s ", artist, name);
    }

    public String getRandomString()
    {
        List<String> strings = Arrays.asList("Help","What the..?","crazy");
        SecureRandom rng = new SecureRandom();
        return strings.get(rng.nextInt(0,3));
    }
}
