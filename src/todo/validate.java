package todo;

public class validate {
	public static boolean is_validated(String txt) {
		if (txt.length() >= 1 && txt.length() <= 60) {
			return true;
		} else {
			return false;
		}
	}
}
