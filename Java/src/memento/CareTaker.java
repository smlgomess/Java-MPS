package memento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CareTaker implements Serializable {

    private static final long serialVersionUID = 1L;
    private final List<Memento> mementoList = new ArrayList<>();

    public void add(Memento state) {
        mementoList.add(state);
    }

    public Memento getLast() {
        return mementoList.isEmpty() ? null : mementoList.get(mementoList.size() - 1);
    }

}
