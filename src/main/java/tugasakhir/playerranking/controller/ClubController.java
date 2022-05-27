package tugasakhir.playerranking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tugasakhir.playerranking.model.ClubModel;
import tugasakhir.playerranking.model.CompetitionModel;
import tugasakhir.playerranking.model.PlayerModel;
import tugasakhir.playerranking.service.ClubService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/club")
public class ClubController {

    @Autowired
    ClubService clubService;


    @GetMapping("/list")
    private String listClub(
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(8);
        Page<ClubModel> clubPage = clubService.getClubPagination(PageRequest.of(currentPage-1,pageSize));
        model.addAttribute("clubPage", clubPage);
        int totalPages = clubPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
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
        List<CompetitionModel> listCompetition =  club.getCompetitions();
        List<PlayerModel> listPlayer = club.getPlayerList();
        model.addAttribute("club",club);
        model.addAttribute("listCompetition",listCompetition);
        model.addAttribute("listPlayer",listPlayer);
        return "club-detail";
    }
}
