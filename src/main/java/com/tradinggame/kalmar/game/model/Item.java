package com.tradinggame.kalmar.game.model;

public class Item {
    private String name;
    private int price;

    public Item(String name, int price) {
        this.name = name;
        this.price = 0; //Játék kezdéskor mindent tárgyadból 0 van
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    //Ha Felveszel egy tárgyat
    // akkor inventoryibant nő a száma annak a tárgynak
    public void increase(int amount){
        this.price += amount;
    }

    //ha vesztettél egy harcot akkor csökkenni fog az item az inventoryba
    public void decrease(int amount){this.price -=amount;}
}
