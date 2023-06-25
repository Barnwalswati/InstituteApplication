package com.instituteApplication.service;

import com.instituteApplication.dao.FacultyDao;
import com.instituteApplication.dao.UserDao;
import com.instituteApplication.model.Faculty;
import com.instituteApplication.model.MyUserDetails;
import com.instituteApplication.model.User;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserService implements UserDetailsService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private FacultyDao facultyDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userDao.findByUsername(username);
        return user.map(MyUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
    }

    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
        if (user.getRoles().equals("ROLE_FACULTY")) {
            Faculty faculty = new Faculty();
            faculty.setName(user.getUsername());
            faculty.setEmail(user.getEmail());
            faculty.setUserId(user.getId());
            facultyDao.save(faculty);
        }
    }
    public void resetPassword(long id, String password){
        User user = userDao.findById(id).orElseThrow(() -> new NoSuchElementException("UserId not found"));
        user.setPassword(passwordEncoder.encode(password));
        userDao.save(user);
    }
}
