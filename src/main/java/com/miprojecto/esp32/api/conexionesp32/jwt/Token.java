package com.miprojecto.esp32.api.conexionesp32.jwt;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
public class Token {
    private String token = "8GH3J92HD846VSN273047NF6C37C983330357CN5765N284NF63V4";

    public String getToken() {
        return token;
    }
}
