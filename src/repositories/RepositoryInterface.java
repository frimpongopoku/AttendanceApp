package repositories;

import java.util.ArrayList;
import java.util.List;


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
    List<Swipe> getItems(String cardId);

   List<Swipe> getItems();

    /**
     *
     * @param id
     */
    
    void remove(int id);

    void setItems(ArrayList items);

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

    public String toString(char delimiter);
    
}
