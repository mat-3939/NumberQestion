package com.example.numberqestion;

// 必要なSpring Bootのインポート
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Lombokのアノテーション用のインポート
import lombok.RequiredArgsConstructor;

/**
 * Spring Bootアプリケーションのメインクラス
 * このクラスがアプリケーションのエントリーポイントとなります
 */
@SpringBootApplication  // Spring Bootアプリケーションとして認識させるアノテーション
@RequiredArgsConstructor  // Lombokによる初期化用コンストラクタを自動生成
public class NumberQestionApplication 
{
	/**
	 * アプリケーションのメインメソッド
	 * Spring Bootアプリケーションを起動します
	 * 
	 * @param args コマンドライン引数
	 */
	public static void main(String[] args) 
	{
		SpringApplication.run(NumberQestionApplication.class, args);
	}
}
