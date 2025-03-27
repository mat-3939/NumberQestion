package com.example.numberqestion.model;

import java.util.Random;

import lombok.Data;

@Data
public class NumberQestionGame {
    private int targetNumber;    // 当てる数字
    private int attempts;        // 試行回数
    private boolean isFinished;  // ゲーム終了フラグ
    
    public NumberQestionGame() {
        this.targetNumber = new Random().nextInt(20) + 1;  // 1-20の乱数
        this.attempts = 0;
        this.isFinished = false;
    }
} 