package net.wincn.crypto;

import java.io.File;


import net.wincn.crypto.utils.CryptoUtils;
import net.wincn.crypto.utils.FileUtils;

import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

/**
 * 测试单元，使用方法
 * 
 * @project crypto
 * @author gefangshuai
 * @email gefangshuai@163.com
 * @createDate 2013-6-8 下午3:16:39
 */
public class TestCrypto {
	String key = CryptoUtils.initKey(16);
	File src = new File("F:/test/1.pdf");// 源文件
	File tag = new File("F:/test/22.pdf");// 加密后的文件
	File tag2 = new File("F:/test/33.pdf");// 解密后的文件

	/**
	 * 测试加密
	 */
	@Test
	public void testEncrypt() {
		byte[] b1 = FileUtils.readFileByte(src);
		byte[] b2 = CryptoUtils.encrypt(key, b1);
		FileUtils.writeByteFile(b2, tag);
	}

	/**
	 * 测试解密
	 */
	@Test
	public void testDecrypt() {
		System.out.println(key);
		byte[] c1 = FileUtils.readFileByte(tag);
		byte[] c2 = CryptoUtils.decrypt(key, c1);
		FileUtils.writeByteFile(c2, tag2);
	}

	public static void main(String[] args) {
		String key = CryptoUtils.initKey(56);

		long t1 = System.currentTimeMillis();
		System.out.println(key);
		File src = new File("F:/test/1.pdf");// 源文件
		File tag = new File("F:/test/22.pdf");// 加密后的文件
		File tag2 = new File("F:/test/33.pdf");// 解密后的文件
		byte[] b1 = FileUtils.readFileByte(src);
		byte[] b2 = CryptoUtils.encrypt(key, b1, b1.length);
		FileUtils.writeByteFile(b2, tag);
		long t2 = System.currentTimeMillis();
		System.out.println("加密完成：" + (t2 - t1));
		
		byte[] c1 = FileUtils.readFileByte(tag);
		byte[] c2 = CryptoUtils.decrypt(key, c1, b1.length);
		FileUtils.writeByteFile(c2, tag2);
		long t3 = System.currentTimeMillis();
		System.out.println("解密完成：" + (t3 - t2));
		
		System.out.println(Hex.encodeHex(CryptoUtils.encrypt("hlsq123456", key.getBytes())));
	}
}