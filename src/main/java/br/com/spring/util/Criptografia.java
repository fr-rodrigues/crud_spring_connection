package br.com.spring.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Classe que contem o metodo para criptografia em MD5
 * @author fernando
 *
 */
public class Criptografia {
	
	/**
	 * Metodo que criptografa um password
	 * @param password
	 * @return
	 */
	public String encrypt(String password){
		
		try {
			MessageDigest digest = MessageDigest.getInstance("Md5");
			byte[] pas = digest.digest(password.getBytes("UTF-8"));
			
			StringBuilder hexString = new StringBuilder();
			for (byte b : pas) {
			  hexString.append(String.format("%02x", 0xFF & b));
			}
			
			return hexString.toString();
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
