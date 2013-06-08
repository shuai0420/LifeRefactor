package net.wincn.crypto.decrypt;

import net.wincn.crypto.core.Crypto;

/**
 * 解密操作
 * 
 * @project crypto
 * @author 方帅
 * @email gefangshuai@163.com
 * @createDate 2013-6-8 下午3:38:28
 */
public class Decrypt {
	private static Crypto crypto = Crypto.getInstance();

	/**
	 * 解密，默认的解密长度
	 * 
	 * @param password
	 *            用于加密的密码
	 * @param data
	 *            加密的字节数组
	 * @return
	 */
	public static byte[] decrypt(String password, byte[] data) {
		crypto.InitCipher(password);
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
	public static byte[] decrypt(String password, byte[] data, int length) {
		crypto.InitCipher(password);
		return crypto.decrypt(data, length);
	}
}
