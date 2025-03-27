package com.example.numberqestion.service.impl;

import org.springframework.stereotype.Service;
import com.example.numberqestion.model.NumberQestionGame;
import com.example.numberqestion.service.NumberQestionGameService;

/**
 * 数字当てゲームのサービス実装クラス
 * ユーザーの回答をチェックし、ゲームの状態を管理します
 */
@Service
public class NumberQestionGameServiceImpl implements NumberQestionGameService {
    /** 現在進行中のゲームインスタンス */
    private NumberQestionGame game;

    /**
     * コンストラクタ
     * 新しいゲームを開始します
     */
    public NumberQestionGameServiceImpl() {
        startNewGame();
    }

    /**
     * ユーザーの回答をチェックし、結果に応じたメッセージを返します
     * @param answer ユーザーが入力した数字
     * @return ゲームの結果メッセージ
     */
    @Override
    public String checkAnswer(int answer) {
        // 試行回数をインクリメント
        game.setAttempts(game.getAttempts() + 1);
        
        // 正解との差分を計算
        int difference = Math.abs(answer - game.getTargetNumber());
        
        // 正解の場合
        if (difference == 0) {
            game.setFinished(true);
            return "正解です！";
        }
        
        // 差が5以内の場合
        if (difference <= 5) {
            return "惜しい!+-5の範囲です!";
        }
        
        // 差が10以内の場合
        if (difference <= 10) {
            return "残念!+-10の範囲です!";
        }
        
        // それ以外の場合、大きいか小さいかのヒントを返す
        return answer > game.getTargetNumber() ? "+-10よりもっと小さい数字です" : "+-10よりもっと大きい数字です";
    }

    /**
     * 新しいゲームを開始します
     */
    @Override
    public void startNewGame() {
        game = new NumberQestionGame();
    }

    /**
     * ゲームが終了したかどうかを確認します
     * @return ゲームが終了している場合はtrue
     */
    @Override
    public boolean isGameFinished() {
        return game.isFinished();
    }

    /**
     * 現在の試行回数を取得します
     * @return 試行回数
     */
    @Override
    public int getAttempts() {
        return game.getAttempts();
    }

} 