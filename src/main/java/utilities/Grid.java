package utilities;

import java.util.ArrayList;
import java.util.Iterator;

public class Grid<T> implements Iterable<T>{
    private ArrayList<ArrayList<T>> grid;

    public Grid(int initialWidth, int initialHeight){
        grid = new ArrayList<ArrayList<T>>(initialWidth);
        for(int i = 0 ; i < initialWidth ; ++i){
            grid.add(new ArrayList<>());
            for(int j = 0 ; j < initialHeight ; ++j){
                grid.get(i).add(null);
            }
        }

    }

    public ArrayList<T> get(int i){
        return grid.get(i);
    }

    public T get(int i, int j){
        if(i >= getSizeWidth() || j >= getSizeHeight()) return null;
        if(grid.get(i) == null) return null;
        return grid.get(i).get(j);
    }

    public void set(int i, int j, T element){
        grid.get(i).set(j, element);
    }

    public void resizeWidth(int size){
        if(grid.size() == size) return;

        while(grid.size() != size){
            if(grid.size() < size)
                grid.add(new ArrayList<T>());
            else
                grid.remove(grid.size()-1);
        }
    }

    public void resizeHeight(int size){
        for(ArrayList<T> list : grid){
            if (list.size() == size) continue;

            while (list.size() != size) {
                if (list.size() < size)
                    list.add(null);
                else
                    list.remove(list.size()-1);
            }
        }
    }

    public int getSizeWidth(){
        return grid.size();
    }

    public int getSizeHeight(){
        return (grid.size() == 0 ? 0 : grid.get(0).size());
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Iterator<ArrayList<T>> iteratorList = grid.iterator();
            Iterator<T> iteratorT = iteratorList.next().iterator();

            @Override
            public boolean hasNext() {
                return iteratorT.hasNext() && iteratorList.hasNext();
            }

            @Override
            public T next() {
                if( ! iteratorT.hasNext())
                    iteratorT = iteratorList.next().iterator();
                return iteratorT.next();
            }
        };
    }
}
