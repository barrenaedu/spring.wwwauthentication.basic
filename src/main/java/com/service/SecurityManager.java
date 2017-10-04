package com.service;

public interface SecurityManager {

    public boolean isAuthorized(String resource, String action, String authString);

}
