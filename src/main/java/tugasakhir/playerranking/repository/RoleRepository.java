package tugasakhir.playerranking.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tugasakhir.playerranking.model.RoleModel;

public interface RoleRepository extends JpaRepository<RoleModel,Long>{
    RoleModel findRoleModelById(Long id);
}
