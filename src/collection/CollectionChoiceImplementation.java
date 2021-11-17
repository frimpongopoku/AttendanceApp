package collection;

import java.util.ArrayList;
import java.util.function.Predicate;


public class CollectionChoiceImplementation<Swipe> extends CollectionChoice<Swipe> {

    public CollectionChoiceImplementation() {
        super();
        this.items = new ArrayList<>();
    }

    @Override
    public model.Swipe getItem(Predicate<model.Swipe> predicate) {
        for (model.Swipe item : this.getItems()) {
            Boolean itMatches = predicate.test(item);
            if (itMatches)
                return item;
        }
        return null;
    }

    @Override
    public model.Swipe getItem(int i) {
        return this.getItems().get(i);
    }

    @Override
    public void removeIf(Predicate<model.Swipe> predicate) {
        for (int index = 0; index < this.getItems().size(); index++) {
            model.Swipe item = this.items.get(index);
            Boolean itMatches = predicate.test(item);
            if (itMatches) this.items.remove(index);
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
    public String toString(char delimiter) {
        String output = "";
        for (model.Swipe s : this.getItems()) {
            output += s.toString(delimiter);
        }
        return output;
    }
}
