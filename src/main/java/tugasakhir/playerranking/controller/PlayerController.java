package tugasakhir.playerranking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tugasakhir.playerranking.model.*;
import tugasakhir.playerranking.service.ClubService;
import tugasakhir.playerranking.service.PlayerService;
import tugasakhir.playerranking.service.PositionService;

import java.util.List;

@Controller
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @Autowired
    ClubService clubService;

    @Autowired
    PositionService positionService;

    @GetMapping("/list")
    private String listPlayer(
            Model model){
        List<PlayerModel> listPlayer = playerService.getPlayerList() ;
        model.addAttribute("listPlayer", listPlayer);
        return "player-list";
    }

    @GetMapping("/add")
    private String addPlayerform(
            Model model) {
        List<ClubModel> listClub = clubService.getClubList();
        List<PositionModel> listPosition = positionService.getPositionList();
        model.addAttribute("newPlayer", new PlayerModel());
        model.addAttribute("listClub",listClub);
        model.addAttribute("listPosition",listPosition);
        return "form-add-player";
    }

    @PostMapping("/add")
    private String addPlayerFormSubmit(
            @ModelAttribute PlayerModel newPlayer,
            @RequestParam(value="positionId") Long positionId,
            @RequestParam(value="clubId") Long clubId,
            Model model){
        playerService.addPlayer(newPlayer,positionId,clubId);
        String name = newPlayer.getName();
        model.addAttribute("name", newPlayer.getName());
        return "player-added";
    }

    @GetMapping("/edit")
    private String editPlayerForm(
            @RequestParam(value="id") Long id,
            Model model) {
        PlayerModel player = playerService.getPlayerById(id);
        List<ClubModel> listClub = clubService.getClubList();
        List<PositionModel> listPosition = positionService.getPositionList();
        model.addAttribute("player", player);
        model.addAttribute("listClub",listClub);
        model.addAttribute("listPosition",listPosition);
        return "form-edit-player";
    }

    @PostMapping("/edit")
    private String editPlayerFormSubmit(
            @ModelAttribute PlayerModel editedPlayer,
            @RequestParam(value="positionId") Long positionId,
            @RequestParam(value="clubId") Long clubId,
            Model model){
        playerService.editPlayer(editedPlayer,positionId,clubId);
        model.addAttribute("name", editedPlayer.getName());
        return "player-edited";
    }

    @GetMapping("/delete")
    private String deletePlayer(
            @RequestParam(value="id") Long id,
            Model model){
        PlayerModel player = playerService.getPlayerById(id);
        String name = player.getName();
        playerService.deletePlayer(player);
        model.addAttribute("name",name);
        return "player-deleted";
    }

    @GetMapping("/detail")
    private String detailPlayer(
            @RequestParam(value="id") Long id,
            Model model){
        PlayerModel player = playerService.getPlayerById(id);
        List<PlayerGameStatisticModel> gameLogs = player.getPlayergamestatisticList();
        List<PersonalStatisticModel> personalStats = player.getPersonalstatisticList();
        model.addAttribute("player",player);
        model.addAttribute("gameLogs",gameLogs);
        model.addAttribute("personalStats",personalStats);
        return "player-detail";
    }
}
