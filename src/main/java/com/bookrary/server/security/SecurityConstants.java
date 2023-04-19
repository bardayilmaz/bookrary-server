package com.bookrary.server.security;

public class SecurityConstants {

    // JWT token defaults
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "bookraryapi";
    public static final String TOKEN_AUDIENCE = "bookraryapi";

    private SecurityConstants() {}
}
