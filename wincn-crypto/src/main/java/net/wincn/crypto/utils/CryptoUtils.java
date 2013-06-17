package net.wincn.crypto.utils;

import java.util.Random;

import net.wincn.crypto.core.Crypto;

public class CryptoUtils {
	// 密钥算法
	private static Crypto crypto = Crypto.getInstance();

	/**
	 * 生成密钥
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String initKey(int len) {
		// 35是因为数组是从0开始的，26个字母+10个数字
		int i; // 生成的随机数
		int count = 0; // 生成的密码的长度
		char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 
						'0', '1', '2', '3', '4','5', '6', '7', '8', '9',
						'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'						};
		final int maxNum = str.length;
		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < len) {
			// 生成随机数，取绝对值，防止生成负数，
			i = Math.abs(r.nextInt(maxNum));
			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}
		return pwd.toString();
	}

	/**
	 * 加密，默认加密长度
	 * 
	 * @param password
	 *            用于加密的密码
	 * @param data
	 *            加密的字节数组
	 */
	public static byte[] encrypt(String key, byte[] data) {
		crypto.InitCipher(key);
		return crypto.encrypt(data);
	}

	/**
	 * 加密，指定加密的长度
	 * 
	 * @param password
	 *            用于加密的密码
	 * @param data
	 *            加密的字节数组
	 * @param length
	 *            加密的长度
	 * @return
	 */
	public static byte[] encrypt(String key, byte[] data, int length) {
		crypto.InitCipher(key);
		return crypto.encrypt(data, length);
	}

	/**
	 * 解密，默认的解密长度
	 * 
	 * @param password
	 *            用于加密的密码
	 * @param data
	 *            加密的字节数组
	 * @return
	 */
	public static byte[] decrypt(String key, byte[] data) {
		crypto.InitCipher(key);
		return crypto.decrypt(data);
	}

	/**
	 * 解密，指定解密的长度
	 * 
	 * @param password
	 *            用于加密的密码
	 * @param data
	 *            加密的字节数组
	 * @param length
	 *            加密的长度
	 * @return
	 */
	public static byte[] decrypt(String key, byte[] data, int length) {
		crypto.InitCipher(key);
		return crypto.decrypt(data, length);
	}

}
