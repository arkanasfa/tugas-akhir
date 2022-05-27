package tugasakhir.playerranking.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tugasakhir.playerranking.model.ClubModel;
import tugasakhir.playerranking.model.CompetitionModel;
import tugasakhir.playerranking.model.PlayerModel;
import tugasakhir.playerranking.model.RankModel;
import tugasakhir.playerranking.service.ClubService;
import tugasakhir.playerranking.service.CompetitionService;
import tugasakhir.playerranking.service.RankService;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/rank")
public class RankController {

    @Autowired
    ClubService clubService;

    @Autowired
    CompetitionService competitionService;

    @Autowired
    RankService rankService;


    @GetMapping("/find")
    private String findRank(
            Model model){
        List<ClubModel> listClub = clubService.getClubList();
        List<CompetitionModel> listCompetition = competitionService.getCompetitionList();
        model.addAttribute("listClub",listClub);
        model.addAttribute("listCompetition",listCompetition);
        return "rank-find";
    }

    @PostMapping("/find")
    private String playerRank(
            @RequestParam(value="competitionId") Long competitionId,
            @RequestParam(value="clubId") Long clubId,
            Model model){
        List<PlayerModel> listPlayer = clubService.getClubById(clubId).getPlayerList();
        CompetitionModel competition = competitionService.getCompetitionById(competitionId);
        List<RankModel> listRank = rankService.getRankingByPlayerAndCompetition(listPlayer,competition);
        Collections.sort(listRank, (RankModel r1, RankModel r2) -> r2.getPerformance_score().compareTo(r1.getPerformance_score()));
        model.addAttribute("listRank",listRank);
        model.addAttribute("club",clubService.getClubById(clubId));
        model.addAttribute("competition",competition);
        return "rank-list";
    }
}
