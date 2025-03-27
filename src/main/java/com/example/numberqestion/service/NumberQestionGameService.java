package com.example.numberqestion.service;

public interface NumberQestionGameService {
    String checkAnswer(int answer);  // 回答チェック
    void startNewGame();            // 新規ゲーム開始
    boolean isGameFinished();       // ゲーム終了確認
    int getAttempts();             // 試行回数取得
} 