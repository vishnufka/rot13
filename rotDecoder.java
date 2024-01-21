import java.lang.StringBuilder;

/**
 * ROT13/5 decoder.
 */

public class rotDecoder {

	/**
	 * For decode2()
	 */
	private static final char[] UPPERCASE = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	private static final char[] LOWERCASE = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
	private static final char[] DIGITS	  = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	/**
	 * Main method inspects args array and decodes each argument, printing on a new line.
	 * @param args strings to be decoded.
	 */
	public static void main(String args[]) {
		if (args.length == 0) {
			System.out.println("ERROR: Enter at least one string to decode");
		}
		else {
			for (String arg : args ) {
				System.out.println(decode1(arg));
			}
		}
	}

	/**
	 * First way of decoding - char to int conversion, with StringBuilder.
	 * Inspects each character of the encoded string, and applies ROT13 or ROT5 if applicable.
	 * @param encoded string to be decoded.
	 * @return Returns decoded string.
	 */
	private static StringBuilder decode1(String encoded) {

		StringBuilder result = new StringBuilder(); 
		for (int i = 0; i < encoded.length(); i++) {
			char c = encoded.charAt(i);

			//ROT13 for uppercase
			if (c >= 65 && c <= 90) {
				c -= 13;

				if (c < 65)
					c += 26;
			}
			//ROT13 for lowercase
			else if (c >= 97 && c <= 122) {
				c -= 13;

				if (c < 97)
					c += 26;
			}
			//ROT5 for digits
			else if (c >= 48 && c <= 57) {
				c -= 5;

				if (c < 48)
					c += 10;
			}
			result.append(c);
		}
		return result;
	}

	/**
	 * Second way of decoding - iterating through fixed arrays.
	 * Inspects each character of the encoded string, and applies ROT13 or ROT5 if applicable.
	 * @param encoded string to be decoded.
	 * @return Returns decoded string.
	 */
	private static String decode2(String encoded) {

		String result = "";
		for (int i = 0; i < encoded.length(); i++) {
			char c = encoded.charAt(i);
			boolean charChanged = false;

			for (int j = 0; j < 26; j++){
				if (UPPERCASE[j] == c){
					c = UPPERCASE[(j+13)%26];
					charChanged = true;
					break;
				}

				if (charChanged)
					break;
				if (LOWERCASE[j] == c){
					c = LOWERCASE[(j+13)%26];
					charChanged = true;
					break;
				}

				if (charChanged)
					break;
				if (j < 10) {
					if (DIGITS[j] == c){
						c = DIGITS[(j+5)%10];
						charChanged = true;
						break;
					}
				}
			}
			result += c;
		}
		return result;
	}

}
