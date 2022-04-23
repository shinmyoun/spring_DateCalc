# 課題　日付計算アプリ CRUD処理
## 概要および機能一覧
編集中

## 環境
- Java 11
- Spring Boot 2.6.3
- Spring Security
- MySQL
- MyBatis
- gradle
- Bootstrap 4.4.1
- Docker 20.10.12
&thinsp;&nbsp;

## 画面遷移

```mermaid
stateDiagram-v2
    [*] --> ログイン画面
    ログイン画面 --> 一覧画面
    一覧画面 --> 新規登録画面: 計算式登録
    一覧画面 --> 更新画面: 計算式編集
    一覧画面 --> ログアウト画面
    ログアウト画面 --> ログイン画面
```
※ 一覧画面で計算式の削除ができる

## 構造

<details><summary>階層</summary>


```rb
├─conf
│  └─mysql
│          my.cnf
├─sql
│      001-create-table-and-data.sql
└─src
    ├─main
    │  ├─java
    │  │  └─com
    │  │      └─example
    │  │          └─api
    │  │              │  ApiApplication.java
    │  │              │
    │  │              ├─config
    │  │              │      MvcConfig.java
    │  │              │      SecurityConfig.java
    │  │              │
    │  │              ├─controller
    │  │              │      DateCalcController.java
    │  │              │
    │  │              ├─entity
    │  │              │      DateCalc.java
    │  │              │
    │  │              ├─repository
    │  │              │      DateCalcMapper.java
    │  │              │
    │  │              └─service
    │  │                      DateCalcService.java
    │  │
    │  └─resources
    │      │  application.properties
    │      │
    │      ├─static
    │      │  └─css
    │      │          style.css
    │      │
    │      └─templates
    │          │  login.html
    │          │  register.html
    │          │  top.html
    │          │  update.html
    │          │
    │          └─common
    │                  common.html
    │                  header.html
    │
    └─test
        └─java
            └─com
                └─example
                    └─api
```
</details>
&thinsp;&nbsp;

## 起動手順
`$ git clone https://github.com/shinmyoun/spring_DateCalc.git`

`$ cd spring_DateCalc`

`$ docker-compose up -d`

`$ ./gradlew bootRun`

起動成功時のイメージ
![start-sucess](https://user-images.githubusercontent.com/97828162/162575171-61d77cac-0f4a-4f94-bce4-7702051c67d7.png)

`http://localhost:8080/`にアクセスするとログイン画面が表示される。


## アプリ画面
### ログイン画面
![login](https://user-images.githubusercontent.com/97828162/163658461-13e29c67-ed2a-42a6-987a-c8aef590c273.png)
- ユーザー名：`hoge`
- パスワード ：`12345`
&thinsp;&nbsp;

### 一覧画面
![list](https://user-images.githubusercontent.com/97828162/162599915-49a98c3c-5093-44b5-89ee-fe641961e3dd.png)
&thinsp;&nbsp;

### 計算実行後の画面
![list-push](https://user-images.githubusercontent.com/97828162/162600044-17d8e0ed-dcf5-4a8d-ac4e-60a6f4fad192.png)
&thinsp;&nbsp;

### 新規登録画面
![regist](https://user-images.githubusercontent.com/97828162/162600138-72d5510a-b753-49ff-bde6-11eee1a8836d.png)
&thinsp;&nbsp;

### 更新画面
![update](https://user-images.githubusercontent.com/97828162/162600156-3445efab-ad2c-441f-a50a-aef4c0b21bcd.png)
&thinsp;&nbsp;

### ログアウト画面
![logout](https://user-images.githubusercontent.com/97828162/162600174-cbad60c6-af3d-428b-a486-6acaa47431a2.png)

### ログアウト成功
![logout-sucess](https://user-images.githubusercontent.com/97828162/162600176-09e65141-4afd-4516-865e-054a6ca303cd.png)
&thinsp;&nbsp;

## 終了手順
(`./gradlew bootRun`コマンドで実行している場合) 

`ctrl + c`で終了

終了時のイメージ
![end-sucess](https://user-images.githubusercontent.com/97828162/162575296-da64c6a7-1054-4b7d-b2d2-692ed76e9053.png)

`$ docker-compose down`
&thinsp;&nbsp;

## 不具合メモ
- webjars:jquery:3.5.0が適用されない
- 日付計算結果が一部不適切
&thinsp;&nbsp;

## 修正したいこと
- 認可機能を追加（管理者とユーザーでできることを分ける）
