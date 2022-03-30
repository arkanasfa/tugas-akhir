package tugasakhir.playerranking.service;

import tugasakhir.playerranking.model.PlayerModel;

import java.util.List;

public interface PlayerService {
    List<PlayerModel> getPlayerList();
    PlayerModel getPlayerById(Long id);
    void addPlayer(PlayerModel newPlayer, Long positionId, Long clubId);
    void editPlayer(PlayerModel editedPlayer, Long positionId, Long clubId);
    void deletePlayer(PlayerModel player);
}
