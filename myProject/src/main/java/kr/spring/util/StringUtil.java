package kr.spring.util;

public class StringUtil {
	/*
  	 * html에서의 줄바꿈은 <br>
  	 * java에서의 줄바꿈은 \n 이므로 html에 표시하면 줄바꿈이 무시됨 (경우에 따라 \r\n, \r도 가능)
  	 */

	/*
	 * HTML 태그 허용하면서 줄바꿈
	 */
	public static String useBrHtml(String str) {
		if(str == null) return null;

		//chain걸기 (replaceAll메서드 호출 후 반환되는 것도 역시 문자열이므로 replaceAll메서드 연속하여 호출 가능)
		return str.replaceAll("\r\n", "<br>")
				  .replaceAll("\r", "<br>")
				  .replaceAll("\n", "<br>");
	}

	/*
	 * HTML 태그 허용하지 않으면서 줄바꿈 
	 */
	public static String useBrNoHtml(String str) {
		if(str == null) return null;

		return str.replaceAll("<", "&lt;")		//little
				  .replaceAll(">", "&gt;")		//greater
				  .replaceAll("\r\n", "<br>")
				  .replaceAll("\r", "<br>")
				  .replaceAll("\n", "<br>");
	}

	/*
	 * HTML 태그 허용하지 않으면서 줄바꿈도 허용하지 않음 
	 */
	public static String useNoHtml(String str) {
		if(str == null) return null;

		return str.replaceAll("<", "&lt;")		//little
				  .replaceAll(">", "&gt");		//greater
	}
}