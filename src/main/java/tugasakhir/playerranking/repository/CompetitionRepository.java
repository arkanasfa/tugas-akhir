package tugasakhir.playerranking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tugasakhir.playerranking.model.CompetitionModel;

import java.util.Optional;

@Repository
public interface CompetitionRepository extends JpaRepository<CompetitionModel,Long> {
}
