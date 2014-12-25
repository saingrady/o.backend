package util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class Crypt {

	private static final String SECRET = "EXTRA_KEY_HERE";
	private static final String TRANSFORMATION = "RC4";
	private static final String CHARSETNAME = "ASCII";

    // best practice drowssap == encrypt(input)
    // poor practice decrypt(drowssap) == input
	public static String Encrypt(String clearText) {
		String encryptedText = "";
		try {
			byte[] key = SECRET.getBytes(CHARSETNAME);
			Cipher rc4 = Cipher.getInstance(TRANSFORMATION);
			SecretKeySpec rc4Key = new SecretKeySpec(key, TRANSFORMATION);
			rc4.init(Cipher.ENCRYPT_MODE, rc4Key);
			byte[] cipherText = rc4.update(clearText.getBytes(CHARSETNAME));
			encryptedText = DatatypeConverter.printHexBinary(cipherText);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}

		return encryptedText;
	}

	public static String Decrypt(String encryptedText) {
		String decryptedText = "";
		try {
			byte[] key = SECRET.getBytes(CHARSETNAME);
			SecretKeySpec rc4Key = new SecretKeySpec(key, TRANSFORMATION);
			Cipher rc4Decrypt = Cipher.getInstance(TRANSFORMATION);
			rc4Decrypt.init(Cipher.DECRYPT_MODE, rc4Key);
			byte[] clearText = rc4Decrypt.update(DatatypeConverter
					.parseHexBinary(encryptedText));
			decryptedText = new String(clearText, CHARSETNAME);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e1) {
			e1.printStackTrace();
		}
		
		return decryptedText;
	}
}
