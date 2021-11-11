package ressourceManager;


import utilities.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class RessourceManager<RessourceType> {
    public HashMap<String, Pair<RessourceType, ArrayList<Object>>> ressources;

    public RessourceManager(){
        ressources = new HashMap<String, Pair<RessourceType, ArrayList<Object>>>();
    }

    public RessourceType getRessource(String source, Object object){
        if( ! ressources.containsKey(source))
            ressources.put(source, new Pair<>( constructRessource(source), new ArrayList<>()));
        if( ! ressources.get(source).second.contains(object))
            ressources.get(source).second.add(object);
        return ressources.get(source).first;
    }

    public void removeObjectFromList(String source, Object object){
        if( ! ressources.containsKey(source))
            return;

        removeFromKey(source, object);
    }

    private void removeFromKey(String source, Object object){
        ressources.get(source).second.remove(object);
        checkIfKeyIsEmpty(source);
    }

    private void checkIfKeyIsEmpty(String source){
        if( true || ressources.get(source).second.isEmpty() || listIsAllNull(ressources.get(source).second))
        {
            ressources.get(source).first = null;
            ressources.get(source).second = null;
            ressources.remove(source);
        }
    }

    private static boolean listIsAllNull(ArrayList list){
        for(Object object : list)
            if(object != null)
                return false;
        return true;
    }

    protected abstract RessourceType constructRessource(String source);
}
