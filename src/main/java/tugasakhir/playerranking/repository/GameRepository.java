package tugasakhir.playerranking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tugasakhir.playerranking.model.GameModel;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<GameModel,Long> {
    Optional<GameModel> findByCode(String code);
}
