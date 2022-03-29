package tugasakhir.playerranking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tugasakhir.playerranking.model.ClubModel;

import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<ClubModel,Long> {
    Optional<ClubModel> getClubById(Long id);
}
