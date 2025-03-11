package com.project.vendora.auth.service.impl;

import com.project.vendora.auth.repository.TokenRepository;
import com.project.vendora.auth.service.TokenService;
import com.project.vendora.core.entity.Token;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;

    @Override
    @Transactional
    public Token findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    @Override
    @Transactional
    public void save(Token token) {
        tokenRepository.save(token);
    }
}
