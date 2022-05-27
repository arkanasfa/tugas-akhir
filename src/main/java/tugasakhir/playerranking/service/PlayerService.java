package tugasakhir.playerranking.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tugasakhir.playerranking.model.ClubModel;
import tugasakhir.playerranking.model.PlayerModel;

import java.util.List;

public interface PlayerService {
    List<PlayerModel> getPlayerList();
    Page<PlayerModel> getPlayerPagination(Pageable pageable);
    PlayerModel getPlayerById(Long id);
    void addPlayer(PlayerModel newPlayer, Long positionId, Long clubId);
    void editPlayer(PlayerModel editedPlayer, Long positionId, Long clubId);
    void deletePlayer(PlayerModel player);
    //PlayerModel getPlayerByNumberandByClub(String number, ClubModel player_club);
}
