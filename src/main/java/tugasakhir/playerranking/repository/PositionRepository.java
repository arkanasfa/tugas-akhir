package tugasakhir.playerranking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tugasakhir.playerranking.model.PositionModel;

import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<PositionModel,Long> {
}
