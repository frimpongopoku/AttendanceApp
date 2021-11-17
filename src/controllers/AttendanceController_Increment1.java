package controllers;

import helpers.InputHelper;
import repositories.Repository;

/**
 * @author Frimpong Opoku Agyemang
 */
public class AttendanceController_Increment1 {
    private final Repository repository;

    /**
     *
     */

    public AttendanceController_Increment1() {
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
        System.out.format("\033[31m%s\033[0m%n", "=========");
    }

    private void listSwipes() {
        repository.toString();
    }


    private void listSwipesInReverseDateTimeOrder() {
        System.out.format("\033[31m%s\033[0m%n", "Date Time Order");
        System.out.format("\033[31m%s\033[0m%n", "===============");
    }

    private void listSwipesWhichMatchCardId() {
        System.out.format("\033[31m%s\033[0m%n", "Swipes By Card Id");
        System.out.format("\033[31m%s\033[0m%n", "=================");
    }
}
