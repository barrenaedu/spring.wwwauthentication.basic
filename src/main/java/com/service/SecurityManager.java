package com.service;

public interface SecurityManager {

    boolean isAuthorized(String resource, String action, String authString);

}
