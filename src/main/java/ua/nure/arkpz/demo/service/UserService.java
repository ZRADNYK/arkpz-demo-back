package ua.nure.arkpz.demo.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.nure.arkpz.demo.dao.UserDao;
import ua.nure.arkpz.demo.dto.UserDto;
import ua.nure.arkpz.demo.model.User;
import ua.nure.arkpz.demo.role.Role;

import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    private final UserDao userDao;


    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
     }

    public User findByUserId(Long userId) {
        return userDao.findById(userId).get();
    }

    public User updateUser(User oldUser, UserDto newUser) {
        BeanUtils.copyProperties(newUser, oldUser);
        userDao.save(oldUser);
        return oldUser;
    }

    public User makeUserAdmin(User user) {
        Set<Role> roles = user.getRoles();
        roles.add(Role.ADMIN);
        user.setRoles(roles);
        userDao.save(user);
        return user;
    }

    public User makeAdminUser(User user) {
        Set<Role> roles = user.getRoles();
        roles.remove(Role.ADMIN);
        user.setRoles(roles);
        userDao.save(user);
        return user;
    }

    public User makeUserSuperadmin(User user) {
        Set<Role> roles = user.getRoles();
        roles.add(Role.SUPERADMIN);
        user.setRoles(roles);
        userDao.save(user);
        return user;
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userDao.findByEmail(email).orElse(new User());
    }

    public void save(User user) {
        userDao.save(user);
    }


    public User findByEmail(String email) {
        return userDao.findByEmail(email).orElse(new User());
    }


    public List<User> findAll() {
        return userDao.findAll();
    }
}
