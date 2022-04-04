package tugasakhir.playerranking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import tugasakhir.playerranking.model.*;
import tugasakhir.playerranking.service.ClubService;
import tugasakhir.playerranking.service.CompetitionService;
import tugasakhir.playerranking.service.GameService;
import tugasakhir.playerranking.service.PlayerGameStatisticService;
import tugasakhir.playerranking.utility.CSVprocessor;

import java.util.List;

@Controller
@RequestMapping("/competition")
public class CompetitionController {

    @Autowired
    CompetitionService competitionService;

    @Autowired
    ClubService clubService;

    @Autowired
    GameService gameService;

    @Autowired
    PlayerGameStatisticService playerGameStatisticService;

    @GetMapping("/list")
    private String listCompetition(
            Model model){
        List<CompetitionModel> listCompetition = competitionService.getCompetitionList();
        model.addAttribute("listCompetition", listCompetition);
        return "competition-list";
    }

    @GetMapping("/add")
    private String addCompetitionForm(
            Model model) {
        model.addAttribute("newCompetition", new CompetitionModel());
        return "form-add-competition";
    }

    @PostMapping("/add")
    private String addCompetitionFormSubmit(
            @ModelAttribute CompetitionModel newCompetition,
            Model model){
        competitionService.addCompetition(newCompetition);
        model.addAttribute("name", newCompetition.getName());
        model.addAttribute("year", newCompetition.getYear());
        return "competition-added";
    }

    @GetMapping("/edit")
    private String editCompetitionForm(
            @RequestParam(value="id") Long id,
            Model model) {
        CompetitionModel competition = competitionService.getCompetitionById(id);
        model.addAttribute("competition", competition);
        return "form-edit-competition";
    }

    @PostMapping("/edit")
    private String editCompetitionFormSubmit(
            @ModelAttribute CompetitionModel competition,
            Model model){
        competitionService.editCompetition(competition);
        model.addAttribute("name", competition.getName());
        model.addAttribute("year", competition.getYear());
        return "competition-edited";
    }

    @GetMapping("/delete")
    private String deleteCompetition(
            @RequestParam(value="id") Long id,
            Model model){
        CompetitionModel competition = competitionService.getCompetitionById(id);
        competitionService.deleteCompetition(competition);
        model.addAttribute("name", competition.getName());
        model.addAttribute("year", competition.getYear());
        return "competition-deleted";
    }

    @GetMapping("/detail")
    private String detailCompetition(
            @RequestParam(value="id") Long id,
            Model model){
        CompetitionModel competition = competitionService.getCompetitionById(id);
        List<ClubModel> listClub = competition.getParticipant_club();
        List<GameModel> listGame = competition.getGameList();
        model.addAttribute("competition",competition);
        model.addAttribute("listClub",listClub);
        model.addAttribute("listGame",listGame);
        return "competition-detail";
    }

    @GetMapping("/participant/add")
    private String addCompetitionParticipantForm(
            @RequestParam(value="id") Long id,
            Model model) {
        CompetitionModel competition = competitionService.getCompetitionById(id);
        List<ClubModel> listClub = clubService.getClubList();
        List<ClubModel> participantClub = competition.getParticipant_club();
        listClub.removeAll(participantClub);
        model.addAttribute("listClub", listClub);
        model.addAttribute("competition",competition);
        return "form-add-competition-participant";
    }

    @PostMapping("/participant/add")
    private String addCompetitionParticipantFormSubmit(
            @ModelAttribute CompetitionModel competition,
            @RequestParam(value="participantId") Long participantId,
            Model model){
        competitionService.addParticipant(competition,participantId);
        model.addAttribute("id",competition.getId());
        model.addAttribute("name", competitionService.getCompetitionById(competition.getId()).getName());
        model.addAttribute("participant", clubService.getClubById(participantId).getName());
        return "participant-added";
    }

    @GetMapping("/participant/remove")
    private String removeParticipant(
            @RequestParam(value="competitionId") Long competitionId,
            @RequestParam(value="participantId") Long participantId,
            Model model){
        competitionService.removeParticipant(competitionId,participantId);
        model.addAttribute("id",competitionId);
        model.addAttribute("name", competitionService.getCompetitionById(competitionId).getName());
        model.addAttribute("participant", clubService.getClubById(participantId).getName());
        return "participant-removed";
    }

    @GetMapping("/game/add")
    private String addGameForm(
            @RequestParam(value="id") Long id,
            Model model) {
        GameModel newGame = new GameModel();
        CompetitionModel competition = competitionService.getCompetitionById(id);
        List<ClubModel> listClub = competition.getParticipant_club();
        newGame.setGame_competition(competition);
        model.addAttribute("listClub", listClub);
        model.addAttribute("newGame",newGame);
        return "form-add-competition-game";
    }

    @PostMapping("/game/add")
    private String addGameFormSubmit(
            @ModelAttribute GameModel newGame,
            @RequestParam(value="home_clubId") Long home_id,
            @RequestParam(value="away_clubId") Long away_id,
            Model model) {
        System.out.println(newGame.getId());
        String gameCode = competitionService.addGame(newGame,home_id,away_id);
        model.addAttribute("id",newGame.getGame_competition().getId());
        model.addAttribute("name", newGame.getGame_competition().getName());
        model.addAttribute("code", gameCode);
        return "game-added";
    }

    @GetMapping("/game/delete")
    private String deleteGame(
            @RequestParam(value="competitionId") Long competitionId,
            @RequestParam(value="gameId") Long gameId,
            Model model){
        GameModel game = gameService.findGameById(gameId);
        model.addAttribute("id",competitionId);
        model.addAttribute("name", competitionService.getCompetitionById(competitionId).getName());
        model.addAttribute("code", game.getCode());
        gameService.removeGame(game);
        return "game-removed";
    }

    @GetMapping("/game/edit")
    private String editGameForm(
            @RequestParam(value="id") Long id,
            Model model){
        GameModel game = gameService.findGameById(id);
        CompetitionModel competition = game.getGame_competition();
        List<ClubModel> listClub = competition.getParticipant_club();
        model.addAttribute("listClub",listClub);
        model.addAttribute("game",game);
        return "form-edit-competition-game";
    }

    @PostMapping("/game/edit")
    private String editGameSubmit(
            @ModelAttribute GameModel game,
            Model model){
        String code = competitionService.editGame(game);
        model.addAttribute("id",game.getGame_competition().getId());
        model.addAttribute("name", game.getGame_competition().getName());
        model.addAttribute("code", code);
        return "game-edited";
    }

    @GetMapping("/game/detail")
    private String detailGame(
            @RequestParam(value="id") Long id,
            Model model){
        GameModel game = gameService.findGameById(id);
        List<PlayerGameStatisticModel> allGameStatistic = game.getPlayergamestatisticList();
        List<PlayerGameStatisticModel> awayGameStatistic = playerGameStatisticService.getClubGameStatistic(game.getId(),game.getAway_club().getId());
        List<PlayerGameStatisticModel> homeGameStatistic = playerGameStatisticService.getClubGameStatistic(game.getId(),game.getHome_club().getId());
        CompetitionModel competition = game.getGame_competition();
        model.addAttribute("game",game);
        model.addAttribute("competition",competition);
        model.addAttribute("awayGameStatistic",awayGameStatistic);
        model.addAttribute("homeGameStatistic",homeGameStatistic);
        return "game-detail";
    }

    @GetMapping("game/statistic/add")
    private String addStatisticForm(
            @RequestParam(value="clubId") Long clubId,
            @RequestParam(value="gameId") Long gameId,
            Model model){
        GameModel game = gameService.findGameById(gameId);
        ClubModel club = clubService.getClubById(clubId);
        model.addAttribute("game",game);
        model.addAttribute("club",club);
        return "form-add-competition-game-statistic";
    }

    @PostMapping("game/statistic/add")
    private String addStatisticSubmit(
            @RequestParam(value="clubId") Long clubId,
            @RequestParam(value="gameId") Long gameId,
            @RequestParam(value="file")MultipartFile file,
            Model model){
        System.out.println(CSVprocessor.isCSV(file));
        GameModel game = gameService.findGameById(gameId);
        ClubModel club = clubService.getClubById(clubId);
        List<PlayerModel> listPlayer = club.getPlayerList();
        playerGameStatisticService.addPlayerGameStatistic(file,listPlayer,game,club);
        model.addAttribute("game",game);
        model.addAttribute("club",club);
        return "statistic-added";
    }

}
