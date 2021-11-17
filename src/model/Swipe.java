package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * @author mga
 */
public class Swipe  {

    /**
     *
     */
    protected final int id;

    /**
     *
     */
    protected String cardId;

    /**
     *
     */
    protected String room;

    /**
     *
     */
    protected final Calendar swipeDateTime;

    private static int lastSwipeIdUsed = 0;
    static final char EOLN = '\n';
    static final String QUOTE = "\"";

    /**
     *
     */
    public Swipe() {
        this.id = ++lastSwipeIdUsed;
        this.cardId = "Unknown";
        this.room = "Unknown";
        this.swipeDateTime = getNow();
    }

    /**
     * @param cardId
     * @param room
     */
    public Swipe(String cardId, String room) {
        this.id = ++lastSwipeIdUsed;
        this.cardId = cardId;
        this.room = room;
        this.swipeDateTime = getNow();
    }


    /**
     * @param swipeId
     * @param cardId
     * @param room
     * @param swipeDateTime
     */
    public Swipe(int swipeId, String cardId, String room, Calendar swipeDateTime) {
        this.id = swipeId;
        this.cardId = cardId;
        this.room = room;
        this.swipeDateTime = swipeDateTime;
        if (swipeId > Swipe.lastSwipeIdUsed)
            Swipe.lastSwipeIdUsed = swipeId;
    }


    private Calendar getNow() {
        Calendar now = Calendar.getInstance();
        return now;
    }

    public Calendar getSwipeDateTime() {
        return swipeDateTime;
    }


    /**
     * @return the id
     */
    public int getId() {
        return this.id;
    }

    // Methods required: getters, setters, hashCode, equals, compareTo, comparator


    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Swipe swipe = (Swipe) o;
        return getCardId().equals(swipe.getCardId()) &&
                getRoom().equals(swipe.getRoom());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCardId(), getRoom());
    }


    /**
     * @param calendar
     * @return
     */

    public static String formatSwipeDateTime(Calendar calendar) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar now = Calendar.getInstance();
        return dateFormat.format(calendar.getTime());
    }

    /**
     * Used to convert string back to calendar object when reading collection from file
     *
     * @param dateString
     * @return
     * @throws ParseException
     */
    public static Calendar formatSwipeDateTime(String dateString) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = dateFormat.parse(dateString);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "\nSwipe Id: " + this.id + " - Card Id: " + this.cardId +
                " - Room: " + this.room + " - Swiped: " + formatSwipeDateTime(this.swipeDateTime);
    }

    /**
     *
     * @param delimiter
     * @return
     */
    public String toString(char delimiter) {
        return Integer.toString(this.id) + delimiter + this.cardId +
                delimiter + this.room + delimiter + formatSwipeDateTime(this.swipeDateTime);
    }

//    /**
//     * Comparing swipe objects lexicographically
//     * DONT USE LEXICROGRAPHICAL COMPARISON, this is where you should be comparing the dates bruh
//     *
//     * @param swipe
//     * @return
//     */
//    @Override
//    public int compareTo(Swipe swipe) {
//        return toString().compareTo(swipe.toString());
//    }
}
