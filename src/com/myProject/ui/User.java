package com.myProject.ui;

public record User(String username, String password) {

    /**
     * 获取
     *
     * @return username
     */
    @Override
    public String username() {
        return username;
    }

    /**
     * 获取
     *
     * @return password
     */
    @Override
    public String password() {
        return password;
    }


}