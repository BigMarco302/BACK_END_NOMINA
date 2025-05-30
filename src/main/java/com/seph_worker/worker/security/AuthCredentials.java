package com.seph_worker.worker.security;


import lombok.Data;

@Data
public class AuthCredentials {

    private String user;
    private String password;
}
