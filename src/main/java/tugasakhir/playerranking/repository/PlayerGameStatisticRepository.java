package tugasakhir.playerranking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tugasakhir.playerranking.model.PlayerGameStatisticModel;

import java.util.Optional;

@Repository
public interface PlayerGameStatisticRepository extends JpaRepository<PlayerGameStatisticModel,Long> {
}
