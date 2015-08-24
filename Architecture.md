# 実装方針(編集中) #
  * 各レイヤーごとに以下のような記載だけすれば実装可能なフレームワークを作成する。
  1. サービス層
    * 更新処理の場合、以下のメソッド順、指定したコンポーネント順に呼び出す。
      1. Component#setCondition(Condition condition) ＊１
      1. Component#validate() ＊２
      1. Component#select() ＊２
      1. Component#setCheck(Check check) ＊１
      1. Component#check() ＊２
      1. Component#setUpdatable(Updatable updatable) ＊１
      1. Component#update() ＊２
    * 照会処理の場合、以下のメソッド順、指定したコンポーネント順に呼び出す。
      1. Component#setCondition(Condition condition) ＊１
      1. Component#validate() ＊２
      1. Component#select() ＊２
    * ＊１…個々のマッピング処理にて呼び出す。
    * ＊２…抽象クラスの中のループ処理にて呼び出す。
  1. コンポーネント層
    * 更新用コンポーネントの場合、以下のメソッドを定義する。
      1. void setCondition(Condition condition) ＊３
      1. int validate()
      1. int select()
      1. void setCheck(Check check) ＊３
      1. int check()
      1. void setUpdatable(Updatable updatable) ＊３
      1. int update()
    * 参照専用コンポーネントの場合、以下のメソッドを定義する。
      1. void setCondition(Condition condition) ＊３
      1. int validate()
      1. int select()
    * ＊３…抽象クラスにて定義する。