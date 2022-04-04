package tugasakhir.playerranking.service;

import org.springframework.web.multipart.MultipartFile;
import tugasakhir.playerranking.model.ClubModel;
import tugasakhir.playerranking.model.GameModel;
import tugasakhir.playerranking.model.PlayerGameStatisticModel;
import tugasakhir.playerranking.model.PlayerModel;

import java.util.List;

public interface PlayerGameStatisticService {
    void addPlayerGameStatistic(MultipartFile file, List<PlayerModel> listPlayer, GameModel game, ClubModel club);
    List<PlayerGameStatisticModel> getClubGameStatistic(Long game_id,Long club_id);
}
