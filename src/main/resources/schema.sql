-- テーブルが存在していたら削除
DROP TABLE IF EXISTS question;

-- テーブルの作成
CREATE TABLE question (
    --プレイID
    id SERIAL PRIMARY KEY,
    --プレイ日時
    play_date timestamp without time zone,
    --プレイヤ名
    play_name VARCHAR(255) NOT NULL,
    --クリアまでの回答数
    clear_count INT NOT NULL
)
