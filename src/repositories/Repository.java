package repositories;

import java.util.ArrayList;
import java.util.function.Predicate;

import collection.CollectionChoice;
import collection.CollectionChoiceImplementation;
import daos.DAOTextImplementation;
import model.Swipe;


public class Repository implements RepositoryInterface {
    private CollectionChoice<Swipe> items;

    public Repository() {
        this.items = new CollectionChoiceImplementation<Swipe>();
    }

    public Repository(CollectionChoice<Swipe> items) {
        this.items = items;
    }

    public Repository(String filename) {
        this();
        DAOTextImplementation dao = new DAOTextImplementation();
        this.items = dao.load(filename).getItems();
    }

    @Override
    public CollectionChoice<Swipe> getItems() {
        return this.items;
    }

    @Override
    public void setItems(CollectionChoice<Swipe> items) {
        this.items = items;
    }

    @Override
    public void add(Swipe item) {
        this.items.add(item);
    }

    @Override
    public void remove(int id) {
        Predicate<Swipe> predicate = itm -> itm.getId() == id;
        this.items.removeIf(predicate);
    }


    @Override
    public Swipe getItem(int id) {
        return this.items.getItem(id);

    }

    @Override
    public ArrayList<Swipe> getItems(String cardId) {
        Predicate<Swipe> predicate = itm -> itm.getCardId().equals(cardId);
        return this.items.getItems(predicate);
    }

    @Override
    public String toString() {
        return "\nItems: " + this.items.toString();
    }

    @Override
    public String reverseToString() {
        return this.items.reverseToString();
    }

    @Override
    public String toString(char delimiter) {
        return this.items.toString(delimiter);
    }

    @Override
    public void store(String filename) {
        DAOTextImplementation dao = new DAOTextImplementation();
        dao.store(filename, this);
    }
}
