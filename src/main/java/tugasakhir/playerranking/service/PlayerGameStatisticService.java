package tugasakhir.playerranking.service;

import org.springframework.web.multipart.MultipartFile;
import tugasakhir.playerranking.model.ClubModel;
import tugasakhir.playerranking.model.GameModel;
import tugasakhir.playerranking.model.PlayerGameStatisticModel;
import tugasakhir.playerranking.model.PlayerModel;

import java.util.List;

public interface PlayerGameStatisticService {
    List<PlayerGameStatisticModel> addPlayerGameStatistic(MultipartFile file, List<PlayerModel> listPlayer, GameModel game, ClubModel club);
    List<PlayerGameStatisticModel> getClubGameStatistic(Long game_id,Long club_id);
    PlayerGameStatisticModel getPlayerGameStatistic(Long game_id, Long player_id);
    Boolean checkPlayerGameStatistic(Long game_id, Long player_id);
    void removeGameStatistic(List<PlayerGameStatisticModel> listPlayerGameStatistic);

}
