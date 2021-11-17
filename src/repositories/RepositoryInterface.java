package repositories;

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

    public String toString(char delimiter);
    
}
