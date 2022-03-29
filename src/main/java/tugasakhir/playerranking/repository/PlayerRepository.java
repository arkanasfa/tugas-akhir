package tugasakhir.playerranking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tugasakhir.playerranking.model.PlayerModel;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerModel,Long> {
}
