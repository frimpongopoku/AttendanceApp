package daos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Swipe;
import model.VisitorSwipe;
import repositories.Repository;

public class DAOTextImplementation implements DAOInterface {

    public static final char DELIMITER = ',';

    @Override
    public Repository load(String filename) {
        Repository repository = new Repository();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String[] temp;
            String line = br.readLine();
            while (line != null) {
                temp = line.split(Character.toString(DELIMITER)); // Read the file line by line and split into a string array using delimiter
                int swipeId = Integer.parseInt(temp[0]); // First item in the array is always the swipe id so convert to an integer
                String cardId = stripQuotes(temp[1]); // Second item is the cardId so remove quotes
                String room = stripQuotes(temp[2]); // Third item is the room name, remove quotes
                Calendar swipeDate = Swipe.formatSwipeDateTime(stripQuotes(temp[3])); // the fourth item is always the date
                Swipe swipe;
                String visitorName = "", visitorCompany = "";
                if (temp.length == 4)
                    swipe = new Swipe(swipeId, cardId, room, swipeDate);
                else {
                    visitorName = stripQuotes(temp[4]);
                    visitorCompany = stripQuotes(temp[5]);
                    swipe = new VisitorSwipe(swipeId, cardId, room, swipeDate, visitorName, visitorCompany);
                }
                repository.add(swipe);
                line = br.readLine();
            }
            br.close();
        } catch (IOException | ParseException ex) {
//            Logger.getLogger(DAOTextImplementation.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("X Sorry, something happened: " + ex.getLocalizedMessage());
        }
        return repository;
    }

    @Override
    public void store(String filename, Repository repository) {
        if (filename == null) {
            System.out.println("Please provide a valid file name in order to store content...");
            return;
        }
        ;
        try (PrintWriter output = new PrintWriter(filename)) {
            output.print(repository.toString(DELIMITER));
            output.close();
        } catch (FileNotFoundException ex) {
//            Logger.getLogger(DAOTextImplementation.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("X Sorry an an issue occurred: " + ex.getLocalizedMessage());
        }
    }

    private String stripQuotes(String str) {
        return str.substring(1, str.length() - 1);
    }
}
