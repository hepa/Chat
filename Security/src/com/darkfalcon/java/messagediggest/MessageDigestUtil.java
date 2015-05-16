package com.darkfalcon.java.messagediggest;

import java.util.Arrays;

import org.apache.commons.codec.digest.DigestUtils;

public class MessageDigestUtil {

	public static byte[] getMessageDigest(String message) {
		return DigestUtils.sha256(message);
	}
	
	public static boolean isDigestEquals(byte[] digest1, byte[] digest2) {
		return Arrays.equals(digest1, digest2);
	}
}
