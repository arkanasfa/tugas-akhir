package tugasakhir.playerranking.service;

import tugasakhir.playerranking.model.RoleModel;

import java.util.List;

public interface RoleService {
    List<RoleModel> findAll();
    RoleModel findRolebyId(Long id);
}
