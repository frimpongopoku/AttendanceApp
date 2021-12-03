package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import daos.DAOTextImplementation;
import model.Swipe;


public class Repository implements RepositoryInterface {
    private List<Swipe> items;

    public Repository() {
        this.items = new ArrayList<>();
    }

    public Repository(ArrayList<Swipe> items) {
        this.items = items;
    }

    public Repository(String filename) {
        this();
        DAOTextImplementation dao = new DAOTextImplementation();
        this.items = dao.load(filename).getItems();
    }


    @Override
    public void add(Swipe item) {
        this.items.add(item);
    }

    @Override
    public void remove(int id) {
        Predicate<Swipe> predicate = itm -> itm.getId() == id;
        this.removeIf(predicate);
    }

    public void removeIf(Predicate<model.Swipe> predicate) {
        for (int index = 0; index < this.items.size(); index++) {
            model.Swipe item = this.items.get(index);
            boolean matched = predicate.test(item);
            if (matched) {
                this.items.remove(index);
            }
        }
    }


    @Override
    public void setItems(ArrayList items) {
        this.items = items;
    }


    @Override
    public Swipe getItem(int id) {
       return items.get(id);
    }

    @Override
    public ArrayList<Swipe> getItems(String cardId) {
        ArrayList<Swipe> foundSwipes = new ArrayList<>();
        for( Swipe s: items) {
            if(s.getCardId().equals(cardId)) {
                foundSwipes.add(s);
            }
        }
        return foundSwipes;
    }

    @Override
    public List<Swipe> getItems() {
        return this.items;
    }

    @Override
    public String toString() {
        String output = "";
        for (model.Swipe item : this.getItems()) {
            output += item.toString();
        }
        return output;
    }


    @Override
    public String toString(char delimiter) {
        String output = "";
        for (model.Swipe s : this.getItems()) {
            output += s.toString(delimiter);
        }
        return output;
    }

    @Override
    public void store(String filename) {
        DAOTextImplementation dao = new DAOTextImplementation();
        dao.store(filename, this);
    }
}
