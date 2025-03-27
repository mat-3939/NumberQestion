// このコントローラーは数当てゲームの全体的な制御を担当します

// 主な機能:
// - プレイ履歴の管理（一覧表示、追加、削除）
// - ゲームプレイの進行管理
// - ユーザー入力の処理とレスポンスの生成

// 使用しているサービス:
// - NumberQestionService: データベースとの連携を担当
// - NumberQestionGameService: ゲームのロジックを担当
package com.example.numberqestion.controller;

import java.time.LocalDateTime;
import com.example.numberqestion.entity.qestion;
import com.example.numberqestion.form.NumberQestionForm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.numberqestion.model.NumberQestionGame;
import com.example.numberqestion.service.NumberQestionService;

import jakarta.validation.Valid;

import com.example.numberqestion.service.NumberQestionGameService;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/numberqestion")
@RequiredArgsConstructor
public class NumberQestionController {
    /*DI（依存性注入）
     * dataService: プレイ履歴のデータベース操作を担当
     * gameService: 数当てゲームのロジック（正誤判定、試行回数管理など）を担当
     */
    private final NumberQestionService dataService;
    private final NumberQestionGameService gameService;

    /**
     * トップページの表示
     * - すべてのプレイ履歴をデータベースから取得
     * - 取得したデータをモデルに追加してビューに渡す
     */
    @GetMapping
    public String index(Model model) {
        model.addAttribute("qestions", dataService.findAll());
        return "numberqestion/index";
    }

    /**
     * ゲーム画面の初期表示
     * - 新しいゲームを開始
     * - ゲーム画面（NumberQestionGame.html）を表示
     */
    @GetMapping("/gameplay")
    public String gameplay(Model model) {
        gameService.startNewGame();  // ゲームサービスの状態を初期化
        model.addAttribute("game", new NumberQestionGame());
        model.addAttribute("numberQestionForm", new NumberQestionForm());
        return "numberqestion/NumberQestionGame";
    }

    /**
     * プレイヤーの予想した数字を処理するメソッド
     * 
     * @param guess プレイヤーが入力した予想数字
     * @param model ビューに渡すデータを格納するモデル
     * @return ゲーム画面のビュー名
     */
    @PostMapping("/gameplay")
    public String guess(@RequestParam("guess") int guess, Model model) {
        // プレイヤーの予想が正解かどうかチェックし、結果を取得
        String result = gameService.checkAnswer(guess);
        
        // 判定結果をモデルに追加
        model.addAttribute("result", result);
        
        // 現在の試行回数をモデルに追加
        model.addAttribute("attempts", gameService.getAttempts());
        
        // ゲームが終了したかどうかの状態をモデルに追加
        model.addAttribute("isFinished", gameService.isGameFinished());
        
        // 次回の入力用にフォームオブジェクトを新規作成
        model.addAttribute("numberQestionForm", new NumberQestionForm());
        
        return "numberqestion/NumberQestionGame";
    }

    /**
     * ゲームの結果を保存するメソッド
     * 
     * @param form プレイヤー名を含むフォームデータ
     * @param bindingResult バリデーション結果
     * @param model ビューに渡すデータを格納するモデル
     * @return 遷移先のビュー名
     */
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute NumberQestionForm form, BindingResult bindingResult, Model model) {
        // バリデーションエラーの処理
        if (bindingResult.hasErrors()) {
            // バリデーションエラーが発生した場合、ゲーム画面に戻る
            // isFinished: ゲーム終了状態を維持（true）
            // attempts: プレイヤーの現在の試行回数を保持
            model.addAttribute("isFinished", true);
            model.addAttribute("attempts", gameService.getAttempts());
            // エラーメッセージと共にゲーム画面を再表示
            return "numberqestion/NumberQestionGame";
        }

        // プレイヤー名の入力チェックと結果保存処理
        if (form.getPlayName() != null && !form.getPlayName().isEmpty()) {
            // 新しいqestionエンティティを作成して保存
            // - ID: 0（自動採番）
            // - 日時: 現在時刻
            // - プレイヤー名: フォームから取得
            // - 試行回数: ゲームサービスから取得
            dataService.insert(new qestion(0, LocalDateTime.now(), 
                form.getPlayName(), gameService.getAttempts()));
            // 保存成功後、一覧画面へリダイレクト
            return "redirect:/numberqestion";
        }

        // プレイヤー名が未入力の場合、ゲーム画面を再表示
        return "numberqestion/NumberQestionGame";
    }

    /**
     * プレイ履歴の削除
     * - 指定されたIDのプレイ履歴をデータベースから削除
     * - 処理結果に応じてフラッシュメッセージを設定
     * - 処理後はトップページにリダイレクト
     */
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        if (id != null) {
            //削除処理
            dataService.delete(id);
            //削除完了メッセージ
            redirectAttributes.addFlashAttribute("message","プレイ履歴を削除しました");
        } else {
            //エラーメッセージ
            redirectAttributes.addFlashAttribute("message","プレイ履歴を削除できませんでした");
        }
        return "redirect:/numberqestion";
    }
}
