package tugasakhir.playerranking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tugasakhir.playerranking.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel,String> {
    UserModel findByUsername(String username);
}
