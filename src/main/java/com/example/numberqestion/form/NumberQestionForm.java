package com.example.numberqestion.form;

import java.time.LocalDateTime;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NumberQestionForm {
    /*プレイ履歴のID*/
    private Integer id;

    /*プレイ履歴の日時*/
    private LocalDateTime playDate;

    /*プレイ履歴のプレイヤー名*/
    @Size(min = 1, max = 8, message = "プレイヤー名は1文字以上8文字以内で入力してください")
    private String playName;

    /*プレイ履歴のクリアまでの回答数*/
    private Integer clearCount;
    
}
