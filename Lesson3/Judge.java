package Lesson3;

/**
 * じゃんけんの審判を表すクラス
 * **/

public class Judge {
	/**
	 * じゃんけんを開始する。
	 *
	 * @param player1  判定対象プレイヤー
	 * @param player2  判定対象プレイヤー
	 **/
	public void startJanken(Player player1, Player player2) {
		//じゃんけんの開始を宣言する
		System.out.println("【じゃんけん開始】\n");

		//じゃんけんを3回行う
		for (int cnt = 0; cnt < 3; cnt++) {
			//何回戦目か表示する
			System.out.println("【" +(cnt + 1) + "回戦目】");

			//プレイヤーの手をみて,どちらが勝ちかを判定する。
			Player winner = judgeJanken(player1, player2);

			if (winner != null) {
				//勝者の表示
				System.out.println("\n"+ winner.getName()
				+ "が勝ちました！\n");

				//勝ったプレイヤーへ結果を伝える。---------------③
				winner.notifyResult(true);
			} else {
				//引き分けの場合
				System.out.println("\n 引き分けです! \n");
			}
		}

		//じゃんけんの終了を宣言する
		System.out.println("【じゃんけん終了】\n");

		//最終的な勝者を判定する
		Player finalWinner = jadgeFinalWinnner(player1,player2);

		//結果の表示
		System.out.print(
			player1.getWinCount() + "対" + player2.getWinCount() + "で");
		if (finalWinner != null) {
			System.out.println(finalWinner.getName() + "の勝ちです");
		} else {
			System.out.println("引き分けです\n");
		}
	}

	/**
	 * 「じゃんけんポン！」と声をかけ,
	 * プレイヤーの手を見て,どちらが勝ちかを判定する。
	 *
	 * @param player1 判定対象プレイヤー1
	 * @param player2 判定対象プレイやー2
	 * @return 勝ったプレイヤー。引き分けの場合は null を返す。
	 **/
	private Player judgeJanken(Player player1, Player player2) {
		Player winner = null;

		//プレイヤー1の手を出す -----------------------①
		int player1hand = player1.showHand();
		//プレイヤー2の手を出す -----------------------②
		int player2hand = player2.showHand();

		// それぞれの手を表示する
		printHand(player1hand);
		System.out.print(" VS. \n");
		printHand(player2hand);

		//プレイヤー1が勝つ場合
		if ((player1hand == Player.STONE && player2hand == Player.SCISSORS)
			|| (player1hand == Player.SCISSORS && player2hand == Player.PAPER)
			|| (player1hand == Player.PAPER && player2hand == Player.STONE)) {
			winner = player1;
		}
		//どちらでもない場合は引き分け(nullを返す)
		return winner;
	}

	/**
	 * 最終的な勝者を判定
	 *
	 * @param  判定対象プレイヤー1
	 * @param  判定対象プレイヤー2
	 * @return 勝ったプレイヤー。引き分けの場合は null を返す。
	 **/
	private Player jadgeFinalWinnner(Player player1, Player player2) {
		Player winner = null;
		//Player1から勝った回数を聞く ---------------------④
		int player1WinCount = player1.getWinCount();
		//Player2から勝った回数を聞く ---------------------⑤
		int player2WinCount = player2.getWinCount();

		if (player1WinCount < player2WinCount) {
			//プレイヤー1の勝ち
			winner = player1;
		}else if (player1WinCount > player2WinCount) {
			winner = player2;
		}
		//どちらでもない場合は引き分け(nullを返す。)
		return winner;
	}

	/**
	 * じゃんけんの手を表示する
	 *
	 * @param hand じゃんけんの手
	 */
	private void printHand(int hand) {
		switch (hand) {
		case Player.STONE :
			System.out.println("グー");
			break;
		case Player.SCISSORS :
			System.out.println("チョキ");
			break;
		case Player.PAPER :
			System.out.println("パー");
			break;

		default :
			break;
		}
	}
}
