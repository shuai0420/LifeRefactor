package net.wincn.crypto;

import net.wincn.crypto.utils.RSAUtils;

import org.junit.Test;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * 测试RSA
 * 
 * @project crypto
 * @author 方帅
 * @email gefangshuai@163.com
 * @createDate 2013-6-8 下午4:03:15
 */
public class TestRSA {
	@Test
	public void initKey() {
		try {
			byte[] b1 = RSAUtils.encryptByPublicKey("123456789".getBytes());;
			
			System.out.println(new String(Base64.encode(b1)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		System.out.println(Base64.encode("123456789".getBytes()));
	}
}
