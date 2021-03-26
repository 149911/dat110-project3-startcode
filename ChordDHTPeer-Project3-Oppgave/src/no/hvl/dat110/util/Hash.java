package no.hvl.dat110.util;

/**
 * project 3
 * @author tdoy
 *
 */

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash { 
	
	private static BigInteger hashint; 
	private static MessageDigest md;
	
	public static BigInteger hashOf(String entity) {		
		// Task: Hash a given string using MD5 and return the result as a BigInteger.
		// we use MD5 with 128 bits digest
		// compute the hash of the input 'entity'
		// convert the hash into hex format
		// convert the hex into BigInteger
		// return the BigInteger
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(entity.getBytes());
			String hex = toHex(messageDigest);
			hashint = new BigInteger(hex, 16);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return hashint;
	}
	
	public static BigInteger addressSize() {
		// Task: compute the address size of MD5
		// get the digest length
		// compute the number of bits = digest length * 8
		// compute the address size = 2 ^ number of bits
		// return the address size
		
//		long length = 0;
//		long bitslength = 0;
		try {
			md = MessageDigest.getInstance("MD5");
//			length = md.getDigestLength();
//			bitslength = length * 8;		
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte bits = (byte) bitSize();
		byte[] bits1 = {bits};
		String s = toHex(md.digest(bits1));
	
		hashint = new BigInteger(s, 16);
		return hashint.add(BigInteger.valueOf((long) Math.pow(2, bitSize())));
	}
	
	public static int bitSize() {
		int digestlen = 0;
		try {
			md = MessageDigest.getInstance("MD5");
			digestlen = md.getDigestLength();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return digestlen*8;
	}
	
	public static String toHex(byte[] digest) {
		StringBuilder strbuilder = new StringBuilder();
		for(byte b : digest) {
			strbuilder.append(String.format("%02x", b&0xff));
		}
		return strbuilder.toString();
	}

}
