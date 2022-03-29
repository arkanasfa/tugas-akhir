package tugasakhir.playerranking.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tugasakhir.playerranking.model.ClubModel;
import tugasakhir.playerranking.service.ClubService;

import java.util.List;

@Controller
@RequestMapping("/club")
public class ClubController {

    @Autowired
    ClubService clubService;


    @GetMapping("/list")
    private String listLowongan(
            Model model){
        List<ClubModel> listClub = clubService.getClubList();
        model.addAttribute("listClub", listClub);
        return "club-list";
    }

    @GetMapping("/add")
    private String addClubForm(
            Model model) {
        model.addAttribute("newClub", new ClubModel());
        return "form-add-club";
    }

    @PostMapping("/add")
    private String addClubFormSubmit(
            @ModelAttribute ClubModel newClub,
            Model model){
        clubService.addClub(newClub);
        model.addAttribute("name", newClub.getName());
        return "club-added";
    }

    @GetMapping("/edit")
    private String editClubForm(
            @RequestParam(value="id") Long id,
            Model model) {
        ClubModel club = clubService.getClubById(id);
        model.addAttribute("club", club);
        return "form-edit-club";
    }

    @PostMapping("/edit")
    private String editClubFormSubmit(
            @ModelAttribute ClubModel club,
            Model model){
        clubService.editClub(club);
        model.addAttribute("name", club.getName());
        return "club-edited";
    }

    @GetMapping("/delete")
    private String deleteClub(
            @RequestParam(value="id") Long id,
            Model model){
        ClubModel club = clubService.getClubById(id);
        String name = club.getName();
        clubService.deleteClub(club);
        model.addAttribute("name",name);
        return "club-deleted";
    }

    @GetMapping("/detail")
    private String detailClub(
            @RequestParam(value="id") Long id,
            Model model){
        ClubModel club = clubService.getClubById(id);
        model.addAttribute("club",club);
        return "club-detail";
    }
}
