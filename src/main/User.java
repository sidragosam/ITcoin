package main;

import java.security.KeyPair;

public class User {
    private String name;
    private String privateKey;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public User(String name, String privateKey) {
        this.name = name;
        this.privateKey = privateKey;
    }

    public KeyPair generateRSAforUser() throws Exception {
        KeyPair kp;
        kp = RSA.generateRSA();
        return kp;
    }
}
