package com.myProject.ui;

import javax.swing.*;

public class LogonFrame extends JFrame {
    public LogonFrame() {
        //设置宽高
        this.setSize(488, 500);
        //设置界面标题
        this.setTitle("拼图游戏 用户注册");
        //设置界面置顶（鼠标点击其他界面时该界面不会被覆盖）
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置界面关闭方式（在界面被关闭时，同时关闭控制台）
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //窗口显示
        this.setVisible(true);
    }
}
