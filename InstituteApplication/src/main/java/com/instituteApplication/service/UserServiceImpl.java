//package com.instituteApplication.service;
//
//import com.instituteApplication.dao.UserDao;
//import com.instituteApplication.model.User;
//import java.util.Collections;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserServiceImpl implements IUserService{
//
//    private final UserDao userDao;
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
//        this.userDao = userDao;
//        this.passwordEncoder = passwordEncoder;
//    }
//
////    @Override
////    public void addUser(User user) {
////        user.setPassword(passwordEncoder.encode(user.getPassword()));
////        userDao.save(user);
////    }
//
//    @Override
//    public void updateUser(long userId) {
//
//    }
//
//    @Override
//    public void deleteUser(long userId) {
//
//    }
//
//    @Override
//    public void findByUserId(long userId) {
//
//    }
//
//    @Override
//    public void findAllUsers() {
//
//    }
//}
