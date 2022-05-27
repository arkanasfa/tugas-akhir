package tugasakhir.playerranking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tugasakhir.playerranking.model.UserModel;
import tugasakhir.playerranking.repository.UserRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Override
    public UserModel addUser(UserModel user, Long roleId) {
        user.setId_role(roleService.findRolebyId(roleId));
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);
        return userRepository.save(user);
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public UserModel findbyUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
