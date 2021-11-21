package windowManager;

import sprite.Sprite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SpriteHandler {
    private HashMap<Ground, List<Sprite>> spriteList;

    public SpriteHandler(){
        spriteList = new HashMap<>();
        for(Ground ground : Ground.values())
            spriteList.put(ground, new ArrayList<>());
    }

    public SpriteHandler(SpriteHandler spriteHandler){
        spriteList = new HashMap<>();
        for(Ground ground : Ground.values()){
            spriteList.put(ground, new ArrayList<Sprite>());
            for(Sprite sprite : spriteHandler.getList(ground))
                addSpriteTo(ground, sprite);
        }
    }

    public void add(SpriteHandler... spriteHandlers){
        for(SpriteHandler spriteHandler : spriteHandlers)
            for(Ground ground : Ground.values())
                for(Sprite sprite : spriteHandler.spriteList.get(ground))
                    addSpriteTo(ground, sprite);
    }

    public void addSpriteTo(Ground ground, Sprite sprite){
        spriteList.get(ground).add(sprite);
    }

    public void removeSpriteTo(Ground ground, Sprite sprite){
        spriteList.get(ground).remove(sprite);
    }

    public List<Sprite> getList(Ground ground){
        return spriteList.get(ground);
    }

    public HashMap<Ground, List<Sprite>> getSpriteList(){
        return spriteList;
    }

    public void clean(Ground ground){
        spriteList.replace(ground, new ArrayList<>());
    }

    public void addHandlerToGround(Ground ground, SpriteHandler spriteHandler){
        for(Ground where : Ground.values())
            for(Sprite sprite : spriteHandler.spriteList.get(where))
                addSpriteTo(ground, sprite);
    }
}
