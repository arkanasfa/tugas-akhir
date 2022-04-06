package tugasakhir.playerranking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tugasakhir.playerranking.model.WeightModel;

@Repository
public interface WeightRepository extends JpaRepository<WeightModel,Long> {
}
