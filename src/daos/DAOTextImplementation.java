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
                temp = line.split(Character.toString(DELIMITER));
                int swipeId = Integer.parseInt(temp[0]);
                String cardId = stripQuotes(temp[1]);
                String room = stripQuotes(temp[2]);
                Calendar swipeDate = Swipe.formatSwipeDateTime(stripQuotes(temp[3]));
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
            Logger.getLogger(DAOTextImplementation.class.getName()).log(Level.SEVERE, null, ex);

        }
        return repository;
    }

    @Override
    public void store(String filename, Repository repository) {
        try (PrintWriter output = new PrintWriter(filename)) {
            output.print(repository.toString(DELIMITER));
            output.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DAOTextImplementation.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    private String stripQuotes(String str) {
        return str.substring(1, str.length() - 1);
    }
}
