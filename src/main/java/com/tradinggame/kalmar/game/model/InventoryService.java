package com.tradinggame.kalmar.game.model;

import java.util.HashMap;
import java.util.Map;

public class InventoryService {

    private Map<Long, Inventory> inventories;
    private Map<Long, Player> players;
    private Long nextInventoryId;
    private Long nextPlayerId;

    public InventoryService() {
        this.inventories = new HashMap<>();
        this.players = new HashMap<>();
        this.nextInventoryId = 1L;
        this.nextPlayerId = 1L;
    }

    public void createInventory(String playerName, int money, int wood) {
        Player player = new Player(nextPlayerId, playerName);
        Inventory inventory = new Inventory(nextInventoryId, money, wood, player);
        inventories.put(nextInventoryId, inventory);
        players.put(nextPlayerId, player);
        nextInventoryId++;
        nextPlayerId++;
    }

    public void updateInventory(Long id, int money, int wood) {
        Inventory inventory = inventories.get(id);
        if (inventory == null) {
            throw new IllegalArgumentException("Inventory with id " + id + " does not exist");
        }
        inventory.setMoney(money);
        inventory.setWood(wood);
        inventories.put(id, inventory);
    }

    public Inventory getInventory(Long id) {
        Inventory inventory = inventories.get(id);
        if (inventory == null) {
            throw new IllegalArgumentException("Inventory with id " + id + " does not exist");
        }
        return inventory;
    }

    public Player getPlayer(Long id) {
        Player player = players.get(id);
        if (player == null) {
            throw new IllegalArgumentException("Player with id " + id + " does not exist");
        }
        return player;
    }

    public static void main(String[] args) {
        InventoryService inventoryService = new InventoryService();
        System.out.println(inventoryService.getInventory(1L));

    }
}


