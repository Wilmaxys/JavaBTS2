package jeu.cartes;

import jeu.cartes.carte.ICarte;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class Paquet<T> implements IPaquet<T> {

    protected List<T> elements = new ArrayList<T>();

    @Override
    public boolean add(T element) {
        return elements.add(element);
    }

    @Override
    public T get(int i) {
        return elements.get(i);
    }

    @Override
    public T remove(int i) {
        return elements.remove(i);
    }

    @Override
    public boolean remove(T element) {
        return elements.remove(element);
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public void melanger() {
        Collections.shuffle(elements);
    }


    @Override
    public boolean equals(Object obj) {
        boolean equals = false;

        if (obj instanceof IPaquet){
            IPaquet<T> p = (IPaquet<T>) obj;

            int i = 0;
            while (!equals && i < this.size()){
                equals = this.get(i).equals(p.get(i));
                i++;
            }
        }

        return equals;
    }
}
