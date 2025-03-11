package com.project.vendora.auth.service;

import com.project.vendora.core.entity.Token;

public interface TokenService {
    Token findByToken(String token);

    void save(Token token);
}
