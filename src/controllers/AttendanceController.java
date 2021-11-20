package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import helpers.InputHelper;
import model.Swipe;
import model.VisitorSwipe;
import repositories.Repository;

/**
 * @author Frimpong Opoku Agyemang
 */
public class AttendanceController {
    private final Repository repository;

    /**
     *
     */

    public AttendanceController() {
        InputHelper inputHelper = new InputHelper();
        char c = inputHelper.readCharacter("Would you like to load already existing swipes (Y/N)?");
        if (c == 'Y' || c == 'y') {
            String fileName = inputHelper.readString("Enter filename");
            this.repository = new Repository(fileName);
//            System.out.println(repository.toString());
        } else {
            this.repository = new Repository();
        }
    }

    /**
     *
     */
    public void run() {
        boolean finished = false;

        do {
            char choice = displayAttendanceMenu();
            switch (choice) {
                case 'A':
                    addSwipe();
                    break;
                case 'B':
                    listSwipes();
                    break;
                case 'C':
                    listSwipesInReverseDateTimeOrder();
                    break;
                case 'D':
                    listSwipesWhichMatchCardId();
                    break;
                case 'Q':
                    finished = true;
            }
        } while (!finished);

        InputHelper helper = new InputHelper();
        if (repository.getItems().getItems().size() > 0) {
            char choice = helper.readCharacter("Would you like to save the available swipes(Y/N) ? ");
            if (choice == 'Y' || choice == 'y') {
                String filename = helper.readString("Enter any filename your prefer? ");
                if (filename == null || filename == "")
                    System.out.println("Your file could not be saved, you did not provide a valid filename");
                else repository.store(filename);
            }
        }
    }

    private char displayAttendanceMenu() {
        InputHelper inputHelper = new InputHelper();
        System.out.print("\nA. Add Swipe");
        System.out.print("\tB. List Swipes");
        System.out.print("\tC. List Swipes In Date Time Order");
        System.out.print("\tD. List Swipes Which Match Card Id");
        System.out.print("\tQ. Quit\n");
        return inputHelper.readCharacter("Enter choice", "ABCDQ");
    }

    private void addSwipe() {
        System.out.format("\033[31m%s\033[0m%n", "Add Swipe");
        System.out.format("\033[31m%s\033[0m%n", "===============");
        InputHelper inputHelper = new InputHelper();
        Swipe swipe;
        boolean valid = false;
        do {
            char choice = inputHelper.readCharacter("A. Visitor \t B. Not A Visitor ");
            if (choice == 'A' || choice == 'a') {
                valid = true;
                repository.add(collectSwipeInput(true));
            } else if (choice == 'B' || choice == 'b') {
                valid = true;
                repository.add(collectSwipeInput(false));
            }

        } while (!valid);

    }

    private Swipe collectSwipeInput(boolean isVisitor) {
        InputHelper helper = new InputHelper();
        String cardId, room, name, company = "";
        cardId = helper.readString("Card Id? ");
        room = helper.readString("Room Number? ");
        if (isVisitor) {
            VisitorSwipe swipe;
            name = helper.readString("Your name? ");
            company = helper.readString("Your company Name? ");
            swipe = new VisitorSwipe(cardId, room);
            swipe.setVisitorCompany(company);
            swipe.setVisitorName(name);
            return swipe;
        } else
            return new Swipe(cardId, room);
    }

    private void listSwipes() {
        System.out.println(repository.toString());
    }

    private void listSwipesInReverseDateTimeOrder() {
        Collections.sort(repository.getItems().getItems(), new Comparator<Swipe>() {
            @Override
            public int compare(Swipe swipe, Swipe t1) {
                return Swipe.swipeDateTimeComparator(t1, swipe);
            }
        });
        System.out.println(repository.toString());
    }

    private void listSwipesWhichMatchCardId() {
        InputHelper helper = new InputHelper();
        String id = helper.readString("Enter card Id");
        ArrayList<Swipe> swipes = repository.getItems(id);
        System.out.format("\033[31m%s\033[0m%n", "Swipes By Card ");
        System.out.format("\033[31m%s\033[0m%n", "=================");
        System.out.format("\033[31m%s\033[0m%n", id + " was swiped " + swipes.size() + " time(s)");
    }
}
