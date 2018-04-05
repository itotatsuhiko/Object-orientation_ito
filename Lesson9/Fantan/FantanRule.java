package Lesson9.Fantan;

import Lesson8.Card;
import Lesson8.Hand;
import Lesson8.Rule;
import Lesson8.Table;

public class FantanRule implements Rule {
	/**
	 * テーブルに置けるカードを探す
	 * 手札とテーブルから,出しうるカードの組み合わせを探す。
	 *
	 * @param hand  手札
	 * @param table テーブル
	 * @return      見つかったカードの組み合わせ。見つからなかった場合はnullを返す。
	 **/
	public Card[] findCandidate(Hand hand, Table table) {
		//テーブルに置けるカードの候補
		Card[] candidate =null;
		//手札にあるカードを1枚づつチェックして,テーブルに置けるか調べる
		int numberOfHand = hand.getNumberOfCards();
		for(int position = 0; position < numberOfHand; position++) {
			//手札にあるカードを見る
			Card lookingCard = hand.lookCard(position);
			int number = lookingCard.getNumber();
			int suit = lookingCard.getSuit();
			//今注目している手札の左か右かにカードがあれば,カードを置ける
			int leftNumber = (number != 1) ? number - 1 : Card.CARD_NUM;
			int rightNumber = (number != Card.CARD_NUM) ? number + 1 : 1;
			if ((true == isThereCard(table, suit, leftNumber))
				|| (true == isThereCard(table, suit, rightNumber))) {
				//手札からカードを引いて候補とする
				candidate = new Card[1];
				candidate[0] = hand.pickCard(position);
				break;
			}
		}
		return candidate;
	}

	/**
	 * テーブルにカードが置かれているか調べる
	 *
	 * @param table  テーブル
	 * @param suit   スート
	 * @param number 数
	 * @return カードが置かれている場合はtrue
	 **/
	private boolean isThereCard(Table table, int suit, int number) {
		//テーブルにあるカードを調べ、カードが置かれているか調べる
		Card[][] cards =  table.getCards();
		if (cards[suit - 1][number - 1] != null) {
			return true;
		} else {
			return false;
		}
	}
}