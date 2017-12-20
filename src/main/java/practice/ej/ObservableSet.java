package practice.ej;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ObservableSet<E> extends HashSet<E> {
    private final Set<E> set;
    public ObservableSet(Set<E> set) {
        Preconditions.checkNotNull(set);
        this.set = set;
    }
    private final List<SetObserver<E>> observers = new ArrayList<>();

    public void addObserver(SetObserver<E> observer) {
        synchronized (observers) {
            observers.add(observer); // return value is ignored
        }
    }

    public void removeObserver(SetObserver<E> observer) {
        synchronized (observers) {
            observers.remove(observer); // return value is ignored
        }
    }

    private void notifyElementAdded(E e) {
        synchronized (observers) {
            for (SetObserver<E> observer : observers) {
                observer.added(this, e); // let observer know that e was added to this
            }
        }
    }
    @Override
    public boolean add(E e) {
        boolean added = set.add(e);
        if (added) {
            notifyElementAdded(e);
        }
        return added;
    }
    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean result = false;
        for (E e : c) {
            result |= add(e); // may call notifyElement
        }
        return result;
    }
}
