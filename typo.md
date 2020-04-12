# 誤字かもしれない部分

- 5.詳細ページ作成>一覧からのリンク作成: 

「書き方は{Controllerのパッケー���}.routes.{Controller名}となります。」

- 6.登録ページの作成>登録用画面の実装>Formの追加のコードの部分: `From`→`Form`, `Tweet` → `TweetFormData`

```scala
// パターン1: 既存クラスを使いまわして、apply, unapplyを自前で書くパターン
  val form1: From[Tweet] = Form(
```

```scala
// パターン2: Form用にcase classを作成するパターン(推奨)
  val form2: From[Tweet] = Form(
```

を

```scala
val form: Form[TweetFormData] = Form(
```

みたいに。

- 6.登録ページの作成>表示・テンプレートの調整の`application.conf`でmessagesのディレクトリを指定する部分: "messages/"でなく"messages/messageconf/messages/"あるいは、`messages.ja`を入れる場所が間違っている。
