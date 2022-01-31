package todo;

public class validate {
	/**
	 * 入力値が0～60文字であることを検証します
	 * @param txt 入力値
	 * @return 論理値
	 */
	public static boolean is_validated(String txt) {
		if (txt.length() >= 1 && txt.length() <= 60) {
			return true;
		} else {
			return false;
		}
	}
}
