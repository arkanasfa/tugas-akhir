package tugasakhir.playerranking.service;

import tugasakhir.playerranking.model.CompetitionModel;
import tugasakhir.playerranking.model.GameModel;

import java.util.List;

public interface GameService {
    GameModel findGameById(Long id);
    List<GameModel> getGameList();
    GameModel findGameByCode(String code);
    void removeGame(GameModel game);
}
