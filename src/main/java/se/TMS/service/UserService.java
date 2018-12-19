package Project.LI.service;

import se.TMS.data_access.RoleDao;
import se.TMS.data_access.TimeLogDao;
import se.TMS.data_access.UserDao;
import se.TMS.model.Role;
import se.TMS.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service("userService")
public class UserService {

    private UserDao userDao;
    private RoleDao roleDao;
    private TimeLogDao timeLogDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserDao userDao,
                       RoleDao roleDao,
                       TimeLogDao timeLogDao,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao =userDao;
        this.roleDao = roleDao;
        this.timeLogDao = timeLogDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

//    public TimeLog findById(Integer id){
//        return timeLogRepository.findById(id);
//    }

    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleDao.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userDao.save(user);
    }
}
