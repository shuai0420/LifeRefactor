package net.wincn.crypto;

import java.io.File;

import net.wincn.crypto.decrypt.Decrypt;
import net.wincn.crypto.encrypt.Encrypt;
import net.wincn.crypto.utils.FileUtils;

import org.junit.Test;

/**
 * 测试单元，使用方法
 * 
 * @project crypto
 * @author 方帅
 * @email gefangshuai@163.com
 * @createDate 2013-6-8 下午3:16:39
 */
public class TestCrypto {
	String key = "123456789";
	File src = new File("F:/test/1.swf");// 源文件
	File tag = new File("F:/test/22.swf");// 加密后的文件
	File tag2 = new File("F:/test/33.swf");// 解密后的文件

	/**
	 * 测试加密
	 */
	@Test
	public void testEncrypt() {
		byte[] b1 = FileUtils.readFileByte(src);
		byte[] b2 = Encrypt.encrypt(key, b1);
		FileUtils.writeByteFile(b2, tag);
	}

	/**
	 * 测试解密
	 */
	@Test
	public void testDecrypt() {
		byte[] c1 = FileUtils.readFileByte(tag);
		byte[] c2 = Decrypt.decrypt(key, c1);
		FileUtils.writeByteFile(c2, tag2);
	}
}
