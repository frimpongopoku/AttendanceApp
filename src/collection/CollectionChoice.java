package collection;

import java.util.ArrayList;
import java.util.function.Predicate;

import model.Swipe;

public abstract class CollectionChoice<T> {

    public ArrayList<Swipe> items;

    CollectionChoice() {
        this.items = new ArrayList<>();
    }

    public ArrayList<Swipe> getItems() {
        return this.items;
    }

    public void setItems(ArrayList<Swipe> items) {
        this.items = items;
    }

    public abstract Swipe getItem(Predicate<Swipe> predicate);

    public abstract Swipe getItem(int i);

    public abstract void removeIf(Predicate<Swipe> predicate);

    public void add(Swipe item) {
        this.items.add(item);
    }

    public abstract String toString();

    public abstract String toString(char delimiter);

}