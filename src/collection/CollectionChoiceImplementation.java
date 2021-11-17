package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import model.Swipe;


public class CollectionChoiceImplementation<Swipe> extends CollectionChoice<Swipe> {

    public CollectionChoiceImplementation() {
        super();
    }

    @Override
    public ArrayList<model.Swipe> getItems(Predicate<model.Swipe> predicate) {
        ArrayList<model.Swipe> arr = new ArrayList<>();
        for (model.Swipe item : this.getItems()) {
            boolean itMatches = predicate.test(item);
            if (itMatches)
                arr.add(item);
        }
        return arr;
    }

    @Override
    public model.Swipe getItem(int i) {
        return this.getItems().get(i);
    }

    @Override
    public void removeIf(Predicate<model.Swipe> predicate) {
        for (int index = 0; index < this.getItems().size(); index++) {
            model.Swipe item = this.getItems().get(index);
            boolean itMatches = predicate.test(item);
            if (itMatches) this.getItems().remove(index);
        }
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
    public String reverseToString() {
        String output = "";
        List<model.Swipe> list = this.getItems();
        Collections.reverse(list);
        for (model.Swipe item : list) {
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
}
