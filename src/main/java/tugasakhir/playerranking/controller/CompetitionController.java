package tugasakhir.playerranking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import tugasakhir.playerranking.model.ClubModel;
import tugasakhir.playerranking.model.CompetitionModel;
import tugasakhir.playerranking.model.PlayerModel;
import tugasakhir.playerranking.service.ClubService;
import tugasakhir.playerranking.service.CompetitionService;

import java.util.List;

@Controller
@RequestMapping("/competition")
public class CompetitionController {

    @Autowired
    CompetitionService competitionService;

    @Autowired
    ClubService clubService;

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
        model.addAttribute("competition",competition);
        model.addAttribute("listClub",listClub);
        return "competition-detail";
    }

    @GetMapping("/participant/add")
    private String addCompetitionParticipantForm(
            @RequestParam(value="id") Long id,
            Model model) {
        CompetitionModel competition = competitionService.getCompetitionById(id);
        System.out.println(competition.getName());
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
        System.out.println(participantId);
        System.out.println(competitionId);
        competitionService.removeParticipant(competitionId,participantId);
        model.addAttribute("id",competitionId);
        model.addAttribute("name", competitionService.getCompetitionById(competitionId).getName());
        model.addAttribute("participant", clubService.getClubById(participantId).getName());
        return "participant-removed";

    }
}
