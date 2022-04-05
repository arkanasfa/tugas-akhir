package tugasakhir.playerranking.service;

import tugasakhir.playerranking.model.GameModel;
import tugasakhir.playerranking.model.PlayerModel;

import java.util.List;

public interface PersonalStatisticService {
    void addPersonalStatistic(List<PlayerModel> listPlayer, GameModel game);
}
