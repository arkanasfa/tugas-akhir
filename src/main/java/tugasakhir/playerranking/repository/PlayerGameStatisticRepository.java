package tugasakhir.playerranking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tugasakhir.playerranking.model.PlayerGameStatisticModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerGameStatisticRepository extends JpaRepository<PlayerGameStatisticModel,Long> {
    @Query(value = "SELECT * FROM player_game_statistic where game_id=?1 and player_id=?2",nativeQuery = true)
    Optional<PlayerGameStatisticModel> findPlayerGameStatistic(Long gameId,Long playerId);
    @Query(value = "SELECT * FROM player_game_statistic where game_id=?1 and club_id=?2",nativeQuery = true)
    List<PlayerGameStatisticModel> findClubGameStatistic(Long gameId, Long clubId);


}
