package tugasakhir.playerranking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tugasakhir.playerranking.model.PersonalStatisticModel;

import java.util.Optional;

@Repository
public interface PersonalStatisticRepository extends JpaRepository<PersonalStatisticModel,Long> {
}
