package com.example.f22comp1011s1w1;

import javafx.scene.Camera;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DBUtility {
    //the user name and password are what ever you have defined on your local
    //MySQL server
    private static String user = "student";
    private static String password = "student";

    // jdbc:mysql  - this piece tells Java which SQL driver to load
    // 127.0.0.1:3306 - this tells Java that the MySQL server is at IP 127.0.0.1, port 3306
    // F22- that is the database name (do not confuse that with a table name)
    private static String connectUrl = "jdbc:mysql://127.0.0.1:3306/F22";

    /**
     * This method will return a list of Artist objects
     */
    public static ArrayList<Artist> getArtistsFromDB()
    {
        ArrayList<Artist> artists = new ArrayList();

        //query the DB to get a list of Artists
        String sql = "SELECT * FROM artists";

        //the try() is called "try with resources"
        try(
                Connection conn = DriverManager.getConnection(connectUrl,user,password);
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                )
        {
            //loop over the objects returned and create an Artist object
            while (resultSet.next())
            {
                int artistID = resultSet.getInt("artistID");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                LocalDate birthday = resultSet.getDate("birthday").toLocalDate();

                Artist newArtist = new Artist(artistID, firstName,lastName,birthday);
                artists.add(newArtist);
            }
        }
        catch (Exception e)
        {e.printStackTrace();}

        return artists;
    }

    /**
     * This method will send a Song object into the DB and return the songID
     */
    public static int insertSongDB(Song song) throws SQLException {
        int songID = -1;
        ResultSet resultSet = null;

        String sql = "INSERT INTO songs (name, genre, length, releaseYear, artistID) VALUES (?,?,?,?,?);";

        //This is called a "try with resources" block.  It will autoclose anything in the ()
        try(
                Connection conn = DriverManager.getConnection(connectUrl,user,password);
                PreparedStatement ps = conn.prepareStatement(sql, new String[] {"songID"})
        )
        {
            //configure the prepared statement to prevent SQL injection attacks
            ps.setString(1, song.getName());
            ps.setString(2, song.getGenre());
            ps.setInt(3, song.getLength());
            ps.setInt(4, song.getReleaseYear());
            ps.setInt(5, song.getArtist().getArtistID());

            //run the command into the DB
            ps.executeUpdate();

            //get the cameraID
            resultSet = ps.getGeneratedKeys();
            while (resultSet.next())
                songID = resultSet.getInt(1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            if (resultSet != null)
                resultSet.close();
        }

        return songID;
    }

    /**
     * This method will return all the songs in the Database
     */
    public static ArrayList<Song> getSongsFromDB()
    {
        ArrayList<Song> songs = new ArrayList();

        //query the DB to get a list of Artists
        String sql = "SELECT * " +
                     "FROM songs INNER JOIN artists ON artists.artistID = songs.artistID;";

        //the try() is called "try with resources"
        try(
                Connection conn = DriverManager.getConnection(connectUrl,user,password);
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        )
        {
            //loop over the objects returned and create an Song object
            while (resultSet.next())
            {
                //create the Artist object
                int artistID = resultSet.getInt("artistID");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                LocalDate birthday = resultSet.getDate("birthday").toLocalDate();
                Artist artist = new Artist(artistID, firstName,lastName,birthday);

                //create the Song object
                int songID = resultSet.getInt("songID");
                String name = resultSet.getString("name");
                String genre = resultSet.getString("genre");
                int length = resultSet.getInt("length");
                int releaseYear = resultSet.getInt("releaseYear");

                Song song = new Song(songID, name,genre,length,releaseYear,artist);
                songs.add(song);
            }
        }
        catch (Exception e)
        {e.printStackTrace();}

        return songs;
    }
}
