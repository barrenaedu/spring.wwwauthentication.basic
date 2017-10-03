package com.service;

public interface SecurityManager {

    public boolean isAuthorized(String action, String authString);

}
