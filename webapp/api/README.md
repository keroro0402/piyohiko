# Api のマイルストーン

---

## Spring Boot の起動と Controller で画面表示（Hello World）

- JDK の　インストール

  - ターミナルで　'java -version'　を実行
    - openjdk version "xx.x.xx" 2025-04-15 LTS <br>
      OpenJDK Runtime Environment Microsoft-11369864 (build 17.0.15+6-LTS) <br>
      OpenJDK 64-Bit Server VM Microsoft-11369864 (build 17.0.15+6-LTS, mixed mode, sharing)<br>
      が出れば OK（xx.x.xx は 17.0.15 などになる）

- IDE が使えるようにする

  - IntelliJ IDEA の場合の確認方法
    - ファイル　 → 　プロジェクトの構造　 → 　プロジェクト設定 > プロジェクト > SDK<br>
      の値がターミナルで 'java -version' で調べたバージョンと一致してれば OK

- プロジェクトを作成する

  - https://start.spring.io/ でプロジェクトを作成する（以下３つの Dependencies は必須）
    - Spring Web (Http サーバ起動、ブラウザからアクセスできるようにする、Controller を使えるようにするなど)
    - Thymeleaf (HTML テンプレートを使えるようにする)
    - Spring Boot DevTools (アプリ起動中にコード変更を自動で反映してくれる)

- Spring Boot を起動する

  - ターミナルでプロジェクトルートディレクトリ (マーベンラッパー(mvnw)がある階層
    )で以下コマンドを打つ <br>
    ./mvnw spring-boot:run
  - Tomcat が起動したことを確認する (以下ログが出れば OK)
    - Tomcat started on port 8080 (http) with context path '/'
  - http://localhost:8080/　にアクセスして 404 を確認する

  ***
- 【ビルド時のエラー対処】
  - コントローラーの記述とファイルが存在しているのに 500 エラー、HTML が見つからないのが出る時は以下のコマンドで再ビルドを行う<br>
    mvn clean package

- Controller クラスの作成
  - com.example.demo.controller パッケージ配下に Controller クラスを作成する
- 実際に画面表示
  - Controller クラスでマッピングした階層にアクセスして「Hello World」の表示を確認する

## Controller の基本とメソッドの戻り値型を理解する

- メソッドの戻り値と return の値の型は必ず一致させる

## 型とキャストの基礎理解

- ModelMap.getAttribute() の戻り値は Object 型 なので String 型として扱うには return の直後に (String) でキャストする

**_ 以下マイルストーンは編集中 _**

## Model でデータを渡す（Thymeleaf 連携）

## フォーム入力 → Controller → DB 登録（CRUD）

## REST API 化して JSON 通信

## Spring Security でログイン機能

## Docker などでデプロイ
