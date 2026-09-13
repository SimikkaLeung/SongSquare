package com.songsquare.api.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AppPasswordEncoder extends BCryptPasswordEncoder {

}