package com.bthouse.util;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.bthouse.util.UIUtils.showToast;

public class CheckUtil {

	/**
	 * 判断两个string是否相等
	 */
	public static boolean checkEquels(Object strObj0, Object strObj1) {
		String str0 = strObj0 + "";
		String str1 = strObj1 + "";
		if (str0.equals(str1)) {
			return true;
		}
		return false;
	}

	public static boolean isNull(Object... strArray) {
		for (Object obj : strArray) {
			if (!"".equals(obj + "")) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNull(Object strObj) {
		String str = strObj + "";
		if (!"".equals(str) && !"null".equals(str)) {
			return false;
		}
		return true;
	}

	public static boolean checkEmail(Object strObj) {
		String str = strObj + "";
		String match = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		Pattern pattern = Pattern.compile(match);
		Matcher matcher = pattern.matcher(str);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	public static boolean checkPhoneNumber(Object phoneNumber) {
		boolean isValid = false;
		String expression = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{5})$";
		String expression2 = "^\\(?(\\d{3})\\)?[- ]?(\\d{4})[- ]?(\\d{4})$";
		CharSequence inputStr = phoneNumber + "";
		Pattern pattern = Pattern.compile(expression);
		Matcher matcher = pattern.matcher(inputStr);
		Pattern pattern2 = Pattern.compile(expression2);
		Matcher matcher2 = pattern2.matcher(inputStr);
		if (matcher.matches() || matcher2.matches()) {
			isValid = true;
		}
		return isValid;
	}

	public static boolean checkLength(Object strObj, int length) {
		String str = strObj + "";
		if (str.length() >= length) {
			return true;
		}
		return false;
	}

	/**
	 * 检查字符串的长度
	 *
	 * @param strObj
	 * @param length
	 * @return
	 */
	public static boolean checkLengthEq(Object strObj, int length) {
		String str = strObj + "";
		if (str.length() == length) {
			return true;
		}
		return false;
	}

	public static boolean checkLength(Object strObj, int start, int end) {
		String str = strObj + "";
		if (str.length() >= start && str.length() <= end) {
			return true;
		}
		return false;
	}

	public static boolean checkStatusOk(String status) {
		if ("1".equals(status)) {
			return true;
		}
		return false;
	}

	public static boolean checkStatusOk(int status) {
		if (2000000 == status) {
			return true;
		}
		return false;
	}

	/**
	 * 校验密码
	 *
	 * @param passwd
	 * @return
	 */
	public static boolean isPasswdValid(String passwd) {
		if (!TextUtils.isEmpty(passwd) && passwd.length() >= 6 && passwd.length() <= 16) {
			return true;
		} else {
			if (TextUtils.isEmpty(passwd)) {
				showToast("请输入密码");
				return false;
			} else {
				showToast("密码码格式错误，请重新输入");
				return false;
			}
		}
	}

}
