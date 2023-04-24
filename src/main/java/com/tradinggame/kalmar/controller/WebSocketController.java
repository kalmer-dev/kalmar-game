package com.tradinggame.kalmar.controller;


import com.google.gson.Gson;
import com.tradinggame.kalmar.game.model.Game;
import com.tradinggame.kalmar.game.model.MiniGame;
import com.tradinggame.kalmar.game.model.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    List<Game> games = new ArrayList<>();
    List<MiniGame> miniGames = new ArrayList<>();

    @MessageMapping("/minigame/{id}")
    @SendTo("/topic/minigame/{id}")
    public List<MiniGame> sendMinigames(MiniGame miniGame){
        for (int i = 0; i < miniGames.size(); i++) {
            if(miniGames.get(i).getGameID().equals(miniGame.getGameID()) &&
            miniGames.get(i).getPlayer1().equals(miniGame.getPlayer1()) &&
            miniGames.get(i).getPlayer2().equals(miniGame.getPlayer2())){

                miniGames.get(i).setPlayer1Choose(miniGame.getPlayer1Choose());
                miniGames.get(i).setPlayer2Choose(miniGame.getPlayer2Choose());
                return searchMiniGames(miniGame.getGameID());
            }
        }
        return null;
    }

    @MessageMapping("/minigame/end")
    public void deleteMini(MiniGame miniGame){
        for (int i = 0; i < miniGames.size(); i++) {
            if(miniGames.get(i).getGameID().equals(miniGame.getGameID()) &&
                    miniGames.get(i).getPlayer1().equals(miniGame.getPlayer1()) &&
                    miniGames.get(i).getPlayer2().equals(miniGame.getPlayer2())){
                miniGames.remove(i);
            }
        }
    }


    @MessageMapping("/new/minigame/{id}")
    @SendTo("/topic/minigame/{id}")
    public  List<MiniGame> newMiniGame(MiniGame miniGame){
        miniGames.add(miniGame);
        List<MiniGame> games = searchMiniGames(miniGame.getGameID());
        return games;
    }

    private List<MiniGame> searchMiniGames(String id){
        List<MiniGame> games = new ArrayList<>();
        for (MiniGame actual : miniGames){
            if(id.equals(actual.getGameID())){
                games.add(actual);
            }
        }
        return games;
    }

    @MessageMapping("/refresh/{id}")
    @SendTo("/topic/update/{id}")
    public Game movePlayer(PlayerMoveInfo playerMoveInfo) {
        Game game = searchGame(playerMoveInfo.id);
        Player player = null;
        if (game == null) {
            return game;
        }
        for (Player p : game.getPlayers()) {
            if (p.getName().equals(playerMoveInfo.playerName)) {
                player = p;
                break;
            }
        }
        if (player == null) {
            return game;
        }

        player.setCoordinateX(playerMoveInfo.x);
        player.setCoordinateY(playerMoveInfo.y);
        player.setOnShop(playerMoveInfo.onShop);
        player.setFightWith(playerMoveInfo.fightWith);
        if (playerMoveInfo.tree >= 0 && playerMoveInfo.money >= 0) {
            player.getInventory().setTree(playerMoveInfo.tree);
            player.getInventory().setMoney(playerMoveInfo.money);
        } else {
            //player.getInventory().setMoney(-23);

        }
        //game.updatePlayer(player);
        return game;
    }

    @MessageMapping("/join/{id}")
    @SendTo("/topic/{id}")
    public List<Player> joinGame(NameId message) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Game game = searchGame(message.getId());
        Player player = new Player(message.name);
        for (Player actual:game.getPlayers()) {
            if(actual.getName().equals(player.getName())){
                return game.getPlayers();

            }
        }

                game.putPlayer(player);
        return game.getPlayers();
    }

    @GetMapping("/statistic/{id}")
    public String getStatistic(Model model, @PathVariable String id){
        Game game = searchGame(id);
        System.out.println(game.getPlayers());
        model.addAttribute("game", game);
        return "score";
    }
    @PostMapping("/connect")
    public String compareInput(@RequestParam("inputValue") String inputValue, Model model) {
        Game game = searchGame(inputValue);
        if (game != null) {
            return "redirect:/lobby/" + game.getIdentifier();
        }
        return "/connect";

    }

    @GetMapping("/game/{id}")
    public String getGame(@PathVariable String id, Model model, Principal principal) {
        Game game = searchGame(id);
        model.addAttribute("game", game);
        model.addAttribute("userName", principal.getName());
        Gson gson = new Gson();

        model.addAttribute("players", gson.toJson(game.getPlayers()));
        model.addAttribute("mycaracter", new Player("asd"));
        return "Map";
    }
    @MessageMapping("/boot/{id}")
    @SendTo("/topic/update/{id}")
    public Game bootGame(NameId message){
        Game game = searchGame(message.getId());
        return game;
    }

    @GetMapping("/lobby/{id}")
    public String getLobby(@PathVariable String id, Model model, Principal principal) {
        Game game = searchGame(id);
        game.putPlayer(new Player(principal.getName()));
        model.addAttribute("game", game);
        model.addAttribute("userName", principal.getName());
        return "lobby";
    }

    @PostMapping("/initialize-game")
    public String newGame(Model model) {
        Game game = new Game();
        games.add(game);
        model.addAttribute("game", game);
        return "redirect:/lobby/" + game.getIdentifier();
    }

    private Game searchGame(String id) {
        for (Game game : games) {
            if (game.getIdentifier().equals(id)) {
                return game;
            }
        }
        return null;
    }

    @MessageMapping("/start-game/{id}")
    @SendTo("/topic/start-game/{id}")
    public String startGame(@Payload NameId message) {
        return "";
    }

}

@AllArgsConstructor
@Data
class NameId {
    String id;
    String name;
}

@AllArgsConstructor
@Data
class PlayerMoveInfo{
    String id;
    String playerName;
    int x;
    int y;
    boolean onShop;
    String fightWith;
    int tree;
    int money;
}
