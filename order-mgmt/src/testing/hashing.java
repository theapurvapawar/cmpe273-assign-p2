package testing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class hashing {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");

	      //generate a random number
	      String randomNum = new Integer(prng.nextInt()).toString();
	      System.out.println("Random number: " + randomNum);
        System.out.println(sha1(randomNum).length());
    }
     
    static String sha1(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
         
        return sb.toString();
    }
}