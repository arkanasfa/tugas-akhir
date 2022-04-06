package tugasakhir.playerranking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tugasakhir.playerranking.model.RankModel;

@Repository
public interface RankRepository extends JpaRepository<RankModel,Long> {
}
