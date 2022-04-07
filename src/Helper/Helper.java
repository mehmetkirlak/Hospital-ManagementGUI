package Helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper {
	public static void optionPaneChangeButtonText() {
		UIManager.put("JOptionPane.cancelButtonText", "Ýptal");
		UIManager.put("JOptionPane.noButtonText", "Hayýr");
		UIManager.put("JOptionPane.okButtonText", "Tamam");
		UIManager.put("JOptionPane.yesButtonText", "Evet");

	}

	public static void showMsg(String str) {
		String msg;
		optionPaneChangeButtonText();
		switch (str) {
		case "fill":
			msg = "lütfen alanlarý doldurunuz.";
			break;
		case "success":
			msg = "Ýþlem baþarýyla yapýldý.";
			break;
		default:
			msg = str;
			break;
		}
		JOptionPane.showMessageDialog(null, msg, "Mesaj", JOptionPane.INFORMATION_MESSAGE);

	}

	public static boolean confirm(String str) {
		String msg;
		optionPaneChangeButtonText();
		switch (str) {
		case "sure":
			msg = "Bu iþlemi ger.ekleþtirmek istiyoru musunuz?";
			break;
		default:
			msg = str;
			break;
		}
		int res = JOptionPane.showConfirmDialog(null, msg, "Dikkat!", JOptionPane.YES_NO_CANCEL_OPTION);
		if (res == 0)
			return true;
		else
			return false;
	}
}
