package net.wincn.crypto.encrypt;

import net.wincn.crypto.core.Crypto;

/**
 * 加密操作(被CryptoUtils代替)
 * 
 * @project crypto
 * @author 方帅
 * @email gefangshuai@163.com
 * @createDate 2013-6-8 下午3:30:19
 */
@Deprecated
public class Encrypt {
	private static Crypto crypto = Crypto.getInstance();

	/**
	 * 加密，默认加密长度
	 * 
	 * @param password
	 *            用于加密的密码
	 * @param data
	 *            加密的字节数组
	 */
	public static byte[] encrypt(String password, byte[] data) {
		crypto.InitCipher(password);
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
	public static byte[] encrypt(String password, byte[] data, int length) {
		crypto.InitCipher(password);
		return crypto.encrypt(data, length);
	}
}
