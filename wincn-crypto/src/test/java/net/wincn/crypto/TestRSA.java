package net.wincn.crypto;

import java.math.BigInteger;

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
			byte[] b1 = RSAUtils.encryptByPublicKey("123456789".getBytes());
			;

			System.out.println(new String(Base64.encode(b1)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		// System.out.println(new
		// String(Base64.decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCag1vPheaWCfzlR3YMEjloLwYPlLZruRnUjTfvu82x257POQaxucb6sTkiglUxZkB20v3fA/eUrFc/bxe/0TD/AX/IkJuZZJsgeYgq4IE03hiA+X6xtaMNHeZA0IcTJ18mj2RIADABBV6pKwp25BrBLYmTKXxb+HciofIqy4DKxwIDAQAB")));
		BigInteger bigInteger = new BigInteger(
				"eb272fb01a89b1119f5ad7dd13ef94919d10ee9b3669f43497e822b1c5473ccc",16);
		System.out.println(bigInteger);
	}
}
