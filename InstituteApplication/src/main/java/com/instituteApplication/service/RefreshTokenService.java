package com.instituteApplication.service;

import com.instituteApplication.dao.RefreshTokenReposiory;
import com.instituteApplication.dao.UserDao;
import com.instituteApplication.model.RefreshToken;
import com.instituteApplication.model.RefreshTokenRequest;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenService {

    @Autowired
    private RefreshTokenReposiory refreshTokenReposiory;
    @Autowired
    private UserDao userDao;

    public RefreshToken createRefreshTocken(String username){
        RefreshToken refreshToken = RefreshToken.builder()
                .user(userDao.findByUsername(username).get())
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(600000)) //10 min
                .build();
        return refreshTokenReposiory.save(refreshToken);
    }

    public Optional<RefreshToken> findByToken(String token){
        return refreshTokenReposiory.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token){
        if(token.getExpiryDate().compareTo(Instant.now())<0){
            refreshTokenReposiory.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token was expired. Please make a new sign-in request");
        }
        return token;
    }
}
