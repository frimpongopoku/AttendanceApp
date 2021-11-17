package repositories;

import java.util.ArrayList;

import collection.CollectionChoice;
import model.Swipe;

/**
 * THis repository interface specifies the rules about what the repository object should look like,
 */
public interface RepositoryInterface {

    /**
     *
     * @param item
     */
    void add(Swipe item);

    /**
     *
     * @param id
     * @return
     */
    Swipe getItem(int id);

    /**
     * Retrieves all swipes that match a particular card id
     * @param cardId
     * @return
     */
    ArrayList<Swipe> getItems(String cardId);

    CollectionChoice<Swipe> getItems();

    /**
     *
     * @param id
     */
    
    void remove(int id);

    void setItems(CollectionChoice<Swipe> items);

    /**
     *
     * @param filename
     */
    
    void store(String filename);

    /**
     *
     * @return
     */
    @Override
    String toString();

    public String reverseToString();

    public String toString(char delimiter);
    
}
