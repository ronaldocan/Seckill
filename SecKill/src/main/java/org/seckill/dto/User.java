package org.seckill.dto;

/**
 * Created by Administrator on 2017/6/27.
 */
public class User {
    private String username;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
