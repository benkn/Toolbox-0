package ben.kn.toolbox.util;

import java.security.MessageDigest;

public class HashUtil {

	/** Change to SHA-256 or MD5 if needed */
	private static final String HASHING_ALGORITHM = "MD5";
	
	/**
	 * The usual default character encoding, but hard defining it to avoid
	 * complications.
	 */
	private static final String BYTE_CHARACTER_ENCODING = "ISO-8859-1";

	public static String hash(String text) throws Exception {
		try {
			MessageDigest md = MessageDigest.getInstance(HASHING_ALGORITHM);

			md.update(text.getBytes(BYTE_CHARACTER_ENCODING), 0, text.length());

			return convertToHexString(md.digest());
		} catch (Exception e) {
			throw new Exception("Couldn't hash the text given(" + text + ")", e);
		}
	}

	private static String convertToHexString(byte[] data) {
		StringBuffer buf = new StringBuffer();
		for ( int i = 0; i < data.length; i++ ) {
			int halfbyte = (data[i] >>> 4) & 0x0F;
			int two_halfs = 0;
			do {
				if ( (0 <= halfbyte) && (halfbyte <= 9) )
					buf.append((char) ('0' + halfbyte));
				else
					buf.append((char) ('a' + (halfbyte - 10)));
				halfbyte = data[i] & 0x0F;
			} while ( two_halfs++ < 1 );
		}
		return buf.toString();
	}

	public static void main(String[] args) throws Exception {
		String myString = "The quick fox jumped over the lazy dog... twice. :)";
		System.out.println("Hashing \"" + myString + "\"");

		System.out.println(HashUtil.hash(myString));
		// confirm the same output every time
		System.out.println(HashUtil.hash(myString));
		// confirm same length and different value a different string
		System.out.println(HashUtil.hash(myString + myString));
		// re-confirm with a small string
		System.out.println(HashUtil.hash("a"));
	}
}
