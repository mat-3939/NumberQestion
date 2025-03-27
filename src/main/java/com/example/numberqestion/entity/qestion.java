package com.example.numberqestion.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ゲームプレイ情報を管理するエンティティクラス
 * プレイヤーのゲームプレイ記録を保持します
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class qestion {
    /** プレイ記録の一意のID */
    private int id;
    
    /** ゲームをプレイした日時 */
    private LocalDateTime playDate;
    
    /** プレイヤーの名前 */
    private String playName;
    
    /** クリアまでの回数 */
    private int clearCount;
}
