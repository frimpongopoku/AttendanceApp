package model;

import java.util.Calendar;

/**
 * @author mga
 */
public class VisitorSwipe extends Swipe {

    static final char EOLN = '\n';
    static final String QUOTE = "\"";

    /**
     *
     */
    protected String visitorName;

    /**
     *
     */
    protected String visitorCompany;

    /**
     *
     */
    public VisitorSwipe() {
        super();
        this.visitorName = "Unknown";
        this.visitorCompany = "Unknown";
    }

    /**
     *
     */
    public VisitorSwipe(String cardId, String room) {
        super(cardId, room);
    }

    /**
     * @param cardId
     * @param room
     * @param visitorName
     * @param visitorCompany
     */
    public VisitorSwipe(String cardId, String room, String visitorName, String visitorCompany) {
        super(cardId, room);
        this.visitorName = visitorName;
        this.visitorCompany = visitorCompany;
    }

    /**
     * @param swipeId
     * @param cardId
     * @param room
     * @param swipeDateTime
     * @param visitorName
     * @param visitorCompany
     */
    public VisitorSwipe(int swipeId, String cardId, String room, Calendar swipeDateTime, String visitorName, String visitorCompany) {
        super(swipeId, cardId, room, swipeDateTime);
        this.visitorName = visitorName;
        this.visitorCompany = visitorCompany;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getVisitorCompany() {
        return visitorCompany;
    }

    public void setVisitorCompany(String visitorCompany) {
        this.visitorCompany = visitorCompany;
    }


    @Override
    public String toString() {
        return super.toString() + "\nName: " + this.visitorName + " - Company: " + this.visitorCompany;
    }

    /**
     * @param delimiter
     * @return
     */
    public String toString(char delimiter) {
        final String QUOTE = "\"";
        return Integer.toString(this.id) + delimiter + QUOTE + this.cardId + QUOTE +
                delimiter + QUOTE + this.room + QUOTE + delimiter + QUOTE + formatSwipeDateTime(this.swipeDateTime) + QUOTE + delimiter + QUOTE + this.visitorName + QUOTE + this.visitorCompany + QUOTE + EOLN;
    }

}
