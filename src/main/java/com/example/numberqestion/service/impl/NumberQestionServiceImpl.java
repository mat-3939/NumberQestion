package com.example.numberqestion.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.numberqestion.entity.qestion;
import com.example.numberqestion.repository.NumberQestionMapper;
import com.example.numberqestion.service.NumberQestionService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class NumberQestionServiceImpl implements NumberQestionService {

    private final NumberQestionMapper numberQestionMapper;

    //全プレイ履歴を検索
    @Override
    public List<qestion> findAll() {
        return numberQestionMapper.selectAll();
    }

    //プレイ履歴を追加
    @Override
    public void insert(qestion qestion) {
        numberQestionMapper.insert(qestion);
    }

    //プレイ履歴を削除
    @Override
    public void delete(int id) {
        numberQestionMapper.delete(id);
    }
    
}
