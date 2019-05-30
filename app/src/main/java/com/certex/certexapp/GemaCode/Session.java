package com.certex.certexapp.GemaCode;

public class Session {

    private static Session instance = null;
    private Token token;

    private Session() {
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return token;
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

}
