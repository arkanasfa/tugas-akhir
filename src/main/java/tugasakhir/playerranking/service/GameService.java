package tugasakhir.playerranking.service;

import tugasakhir.playerranking.model.ClubModel;
import tugasakhir.playerranking.model.CompetitionModel;
import tugasakhir.playerranking.model.GameModel;
import tugasakhir.playerranking.model.PlayerGameStatisticModel;

import java.util.List;

public interface GameService {
    GameModel findGameById(Long id);
    List<GameModel> getGameList();
    GameModel findGameByCode(String code);
    void removeGame(GameModel game);
    void addGameScore(GameModel game, ClubModel club, List<PlayerGameStatisticModel> listPlayerGameStatistic);
    void saveGame(GameModel game);
}
