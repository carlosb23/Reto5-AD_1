package com.example.reto5ad_1;

import org.springframework.stereotype.Service;

@Service
public class Security {

    public boolean validateToken(String token){
        return token.equals("t0k3n");
    }
}
