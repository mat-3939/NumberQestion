package com.example.numberqestion.service;

import java.util.List;

import com.example.numberqestion.entity.qestion;

public interface NumberQestionService {
    // 全プレイ履歴を検索
    List<qestion> findAll();

    // プレイ履歴を追加
    void insert(qestion qestion);

    // プレイ履歴を削除
    void delete(int id);
}
