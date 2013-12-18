package de.hshannover.f4.trust.irongui.util;




import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class CryptoUtil {

	public static String generateMD5(String s){
		MessageDigest md = null;
		byte[] hash = null;
		StringBuffer buffer = null;
		
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		if(md != null){
			buffer = new StringBuffer();
			hash = md.digest(s.getBytes());
			for(Byte b : hash){
				String hex = Integer.toHexString(0xFF & b);
				if(hex.length() == 1)
					buffer.append(0);
				buffer.append(hex);
			}
		}
		return buffer.toString();
	}
	
	public static String generateMD5BySize(String s, int length){
		return generateMD5(s).substring(0, length-1);
	}
	
	public static String randomUUID(int length){
		if(length > 0){
			String uuid = UUID.randomUUID().toString();
			if(uuid.length() <= length)
				return uuid;
			else return uuid.substring(0, length-1);
		}
		else return null;
	}
	
	public static String randomUUID(){
		return UUID.randomUUID().toString();
	}
}
