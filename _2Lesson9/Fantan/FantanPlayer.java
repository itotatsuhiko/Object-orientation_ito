package _2Lesson9.Fantan
;

import _2Lesson8.Card;
import _2Lesson8.Master;
import _2Lesson8.Player;
import _2Lesson8.Rule;
import _2Lesson8.Table;

/**
 * 七並べ用プレイヤークラス。
 **/
public class FantanPlayer extends Player{
	/** パスした回数 **/
	private int pass_;

	/**
	 * コンストラクタ。
	 *
	 * @param name    プレイヤーの名前
	 * @param master 進行役
	 * @param table  テーブル
	 * @param rule    ルール
	 **/
	public FantanPlayer(String name, Master master, Table table, Rule rule) {
		super(name, master, table, rule);
	}

	/**
	 * カードを配る。
	 *
	 * @param card 受け取ったカード
	 **/
	public void receiveCard(Card card) {
		if (card.getNumber() == 7) {
			// カードが7の場合は、テーブルにカードを置く
			System.out.println(name_ + " :" + card + "を置きました");
			table_.putCard(new Card[] {card});
		} else {
			// カードが7でない場合は、受け取ったカードを手札へ加える
			super.receiveCard(card);
		}
	}

	/**
	 * 順番を指名する。
	 *
	 * @param nextPlayer 次のプレイヤー
	 **/
	public void play(Player nextPlayer) {
		// 現在の手札を表示する
		System.out.println("  " + myHand_);
		// 現在の手札からテーブルに出せるものを探す
		Card[] candidate = rule_.findCandidate(myHand_, table_);
		// 候補がある場合はテーブルに出す
		if (candidate != null) {
			System.out.println(" " + candidate[0] + "を置きました");
			table_.putCard(candidate);
			// テーブルの状態を表示する
			System.out.println(table_);
			// 手札がなくなったら、上がりを宣言する
			if (myHand_.getNumberOfCards() == 0) {
				master_.declareWin(this);
			}
		} else {
			// テーブルに出せるカードがなかった場合、パスする
			if (pass_ >FantanMaster.PASS_LIMIT) {
				int numberOfHand = myHand_.getNumberOfCards();
				// 手札を全てテーブルに置く
				for (int count = 0; count < numberOfHand; count++) {
					table_.putCard(new Card[] {myHand_.pickCard(0)});
				}
			}
		}
	}

	/**
	 * パス回数を教える。
	 *
	 * @return パス回数
	 **/
	public int getPass() {
		return pass_;
	}
}