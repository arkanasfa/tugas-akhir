package tugasakhir.playerranking.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tugasakhir.playerranking.model.CompetitionModel;
import tugasakhir.playerranking.model.GameModel;
import tugasakhir.playerranking.model.PlayerModel;

import java.util.List;

public interface CompetitionService {
    Page<CompetitionModel> getCompetitionPagination(Pageable pageable);
    List<CompetitionModel> getCompetitionList();
    void addCompetition(CompetitionModel newCompetition);
    CompetitionModel getCompetitionById(Long id);
    void deleteCompetition(CompetitionModel competition);
    void editCompetition(CompetitionModel competition);
    void addParticipant(CompetitionModel competition, Long participantId);
    void removeParticipant(Long competitionId, Long participantId);
    String addGame(GameModel newGame, Long home_clubId, Long away_clubId);
    String editGame(GameModel game);
    void createPersonalStatistic(List<PlayerModel> playerList, CompetitionModel competition);
}
