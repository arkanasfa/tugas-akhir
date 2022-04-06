package tugasakhir.playerranking.service;

import tugasakhir.playerranking.model.GameModel;
import tugasakhir.playerranking.model.PersonalStatisticModel;
import tugasakhir.playerranking.model.PlayerModel;

import java.util.List;

public interface PersonalStatisticService {
    List<PersonalStatisticModel> addPersonalStatistic(List<PlayerModel> listPlayer, GameModel game);
    Boolean checkPersonalStatisticByCompetitionIdandPlayerId(Long competitionId, Long playerId);
    PersonalStatisticModel getPersonalStatisticByCompetitionIdandPlayerId(Long competitionId, Long playerId);
    void savePersonalStatistic(PersonalStatisticModel personalStatistic);
}
