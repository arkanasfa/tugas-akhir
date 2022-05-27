package tugasakhir.playerranking.service;

import tugasakhir.playerranking.model.UserModel;

public interface UserService {
    UserModel addUser(UserModel user, Long roleId);
    String encrypt(String password);
    UserModel findbyUsername(String username);
}
