package tugasakhir.playerranking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tugasakhir.playerranking.model.PersonalStatisticModel;

import java.util.Optional;

@Repository
public interface PersonalStatisticRepository extends JpaRepository<PersonalStatisticModel,Long> {
    @Query(value = "SELECT * FROM personal_statistic WHERE competition_id=?1 and player=?2", nativeQuery = true)
    Optional<PersonalStatisticModel> findPersonalStatisticByCompetitionIdandPlayerId(Long competitionId,Long playerId);
}
