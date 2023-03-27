package com.tradinggame.kalmar.game.model;


import java.util.ArrayList;
import java.util.List;


public class Inventory {
    private List<Item> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    public void addItem(Item item) {   //HOZZÁADÁS
        items.add(item);
    } //ÚJ ADAT HOZZÁADÁSA

    public void removeItem(Item item) {    //TÖRLÉS
        items.remove(item);
    }

    public void modifyItem(Item item,  int price) {    //MÓDÓSÍTÁS
        if (items.contains(item)) {
            item.setPrice(price);
        }
    }
    public List<Item> getItems() {
        return items;
    }

}
