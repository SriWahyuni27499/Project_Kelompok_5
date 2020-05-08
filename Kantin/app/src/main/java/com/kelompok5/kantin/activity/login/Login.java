package com.kelompok5.kantin.activity.login;

public class Login {
    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Login(String nim, String password) {
        this.nim = nim;
        this.password = password;
    }

    public String nim, password;
}
