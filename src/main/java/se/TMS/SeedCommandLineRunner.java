package se.TMS;

import se.TMS.data_access.RoleDao;
import se.TMS.data_access.TimeLogDao;
import se.TMS.data_access.UserDao;
import se.TMS.model.Role;
import se.TMS.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


import java.util.Arrays;
import java.util.HashSet;

@Component
public class SeedCommandLineRunner implements CommandLineRunner {
    private final UserDao userDao;
    private final RoleDao roleDao;
    private final TimeLogDao timeLogDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public SeedCommandLineRunner(UserDao userDao, RoleDao roleDao, TimeLogDao timeLogDao,BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.timeLogDao = timeLogDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void run(String... strings) throws Exception {

        Role roleUser = roleDao.findByRole("USER");
        Role roleAdmin = roleDao.findByRole("ADMIN");

        System.out.println("Checking Roles.");
        if(roleAdmin == null)  {
            roleAdmin = new Role();
            roleAdmin.setRole("ADMIN");
            roleDao.save(roleAdmin);
            System.out.println("Seeding ADMIN role");
        }
        else {
            System.out.println("ADMIN role OK.");
        }
        if(roleUser == null)  {
            roleUser = new Role();
            roleUser.setRole("USER");
            roleDao.save(roleUser);
            System.out.println("Seeding USER role.");
        }
        else {
            System.out.println("USER role OK.");
        }


        System.out.println("Checking Admin user.");
        User user = userDao.findByEmail("admin@admin.com");
        if(user == null) {
            user = new User();
            user.setName("Admin");
            user.setLastName("Adminstrator");
            user.setPassword(bCryptPasswordEncoder.encode("admin"));
            user.setEmail("admin@admin.com");
            user.setActive(1);
            userDao.save(user);
            user.setRoles(new HashSet<Role>(Arrays.asList(roleAdmin)));
            userDao.save(user);
            System.out.println("Seeding Admin user.");
        }
        else {
            System.out.println("ADMIN user OK.");
        }
        System.out.println("Seed done.");
    }
}
