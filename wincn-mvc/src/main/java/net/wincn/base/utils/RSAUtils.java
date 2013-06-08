package net.wincn.base.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

public class RSAUtils {

	//非对称加密算法
	public static final String KEY_ALGORITEM = "RSA";
	//RSA秘钥长度，默认1024
	public static final Integer KEY_SIZE= 1024;
	//公钥
	public static final String PUBLICKEY = "publickey";
	//私钥
	public static final String PRIVATEKEY = "privatekey";
	// 默认的公钥，经过base64加密
	private static String publicKeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCag1vPheaWCfzlR3YMEjloLwYPlLZruRnUjTfvu82x257POQaxucb6sTkiglUxZkB20v3fA/eUrFc/bxe/0TD/AX/IkJuZZJsgeYgq4IE03hiA+X6xtaMNHeZA0IcTJ18mj2RIADABBV6pKwp25BrBLYmTKXxb+HciofIqy4DKxwIDAQAB";
	// 默认的私钥，经过base64加密
	private static String privateKeyStr = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJqDW8+F5pYJ/OVHdgwSOWgvBg+Utmu5GdSNN++7zbHbns85BrG5xvqxOSKCVTFmQHbS/d8D95SsVz9vF7/RMP8Bf8iQm5lkmyB5iCrggTTeGID5frG1ow0d5kDQhxMnXyaPZEgAMAEFXqkrCnbkGsEtiZMpfFv4dyKh8irLgMrHAgMBAAECgYEAgmzPWuUUNyJHLuKbaqUXgDHxU8WcFmIww5JQ3TQR9UgFTbY9SFgg9gwSxmZtsz00vRhs44tduUmgfBMyYOAcElUs8p2eQnm/aJjyF+BcyOWALAmu/+wUMM4V//M4kvyNgJiYNzyjPISmQcoPGV/Oa2D8ahX2fcHLIwkE30M63QECQQDNHoT2+BX7YaAkaue3L6Dr/6eJB5K8TY/tukZfAgJGuvpg6gZBNajq6gX7ran9ZhpK8g8GQ/jOz8maqyydiQvRAkEAwNdAOCiQ9e6+v4mCAvr64/M6nFuuV4iEsWFonLiIiuYCUdQueP8wRPFeSZszrS0Q/W7aWTG01GPpBVDNWTLLFwJAKifUkeFIu0JEJFfoSWi7fOUs7GenC5YxN11qKwqBp0G0RYizQmGh1q3EDwaRAigizTZUSihcETz5JRV69OF+kQI/ZzLKK0OKUBJ0AgyUhwPJQDPqKcLmifGFEWgPk88tQyfUFfZ26EmoizygOMPV49c6QGYSc6kmHINbMH0NZ67HAkEAjb5zTPyJzOrBHfppwXq8mOgEzj65UM7AvBoKMHsdFBxHb4zBpnZtDUPjQPuQ7PL2iPCkPWpGDmqpBOKaE1GOIw==";
	// 加密长度
	public static final int dataLength = 117;
	
	/**
	 * 私钥解密
	 * @param data 待解密数据
	 * @param key 私钥
	 * @param byte[] 解密数据
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryByPrivateKey(byte[] data,byte[] key) throws Exception{
		//得到私钥Key
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITEM);
		//生成私钥
		PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
		//对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}
	
	/**
	 * 私钥解密
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryByPrivateKey(byte[] data) throws Exception{
		return decryByPrivateKey(data,Base64.decodeBase64(privateKeyStr));
	}
	
	/**
	 * 公钥解密
	 * @param data 待解密数据
	 * @param key 公钥
	 * @param byte[] 解密数据
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPublicKey(byte[] data,byte[] key) throws Exception{
		//StringBuffer sb = new StringBuffer();
		//取得公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITEM);
		//生成公钥
		PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
		//对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		return cipher.doFinal(data);
	}
	
	/**
	 * 公钥解密
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPublicKey(byte[] data) throws Exception{
		return decryptByPublicKey(data,Base64.decodeBase64(publicKeyStr));
	}
	
	/**
	 * 公钥加密
	 * @param data 待加密数据
	 * @param key 公钥
	 * @param byte[] 加密数据
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data,byte[] key) throws Exception{
		//取得公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITEM);
		PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
		//对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(data);
	}
	
	/**
	 * 公钥加密
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data) throws Exception{
		return encryptByPublicKey(data,Base64.decodeBase64(publicKeyStr));
	}
	
	/**
	 * 私钥加密
	 * @param data 待加密数据
	 * @param key 私钥
	 * @param byte[] 加密数据
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPrivateKey(byte[] data,byte[] key) throws Exception{
		//StringBuffer sb = new StringBuffer();
		//得到私钥Key
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITEM);
		//生成私钥
		PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
		//对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}
	
	/**
	 * 私钥加密
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPrivateKey(byte[] data) throws Exception{
		return encryptByPrivateKey(data,Base64.decodeBase64(privateKeyStr));
	}
	
	/**
	 * 取得私钥
	 * @param keyMap 秘钥Map
	 * @return byte[] 私钥
	 * @return
	 * @throws Exception
	 */
	public static byte[] getPrivateKey(Map<String,Object> keyMap) throws Exception{
		Key key = (Key)keyMap.get(PRIVATEKEY);
		return key.getEncoded();
	}
	
	/**
	 * 取得公钥
	 * @param keyMap 秘钥Map
	 * @return byte[] 公钥
	 * @return
	 * @throws Exception
	 */
	public static byte[] getPublicKey(Map<String,Object> keyMap) throws Exception{
		Key key = (Key)keyMap.get(PUBLICKEY);
		return key.getEncoded();
	}
	
	/**
	 * 初始化秘钥
	 * @param Map 秘钥MAP
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> initKey() throws Exception{
		//实例化秘钥对生成器
		KeyPairGenerator keyPairGen=KeyPairGenerator.getInstance(KEY_ALGORITEM);
		//初始化秘钥对生成器
		keyPairGen.initialize(KEY_SIZE);
		//生成秘钥对
		KeyPair keyPair = keyPairGen.generateKeyPair();
		//公钥
		RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();
		//私钥
		RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();
		//封装秘钥
		Map<String,Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put(PUBLICKEY, publicKey);
		keyMap.put(PRIVATEKEY, privateKey);
		return keyMap;
	}
	
	/**
	 * 加密文件
	 * @param data
	 * @param fileName
	 * @param publicKey
	 */
	public static byte[] encryptFile(File sourceFile, byte[] publicKey) {
		//加密源文件的文件流
		FileInputStream fis=null;
		//返回结果
		byte[] result=null;
		// 公钥加密
		try {
			//根据来源文件，生成byte
			fis = new FileInputStream(sourceFile);
			byte[] sourceData = new byte[fis.available()];
			fis.read(sourceData);
			fis.close();
			//返回结果
			result=new byte[sourceData.length+(128-dataLength)];
			//被加密的byte数组
			byte[] data1 = new byte[dataLength];
			for (int i = 0; i < data1.length; i++) {
				data1[i] = sourceData[i];
			}
			//加密后的byte数组
			byte[] encodedData = RSAUtils.encryptByPublicKey(data1, publicKey);
			for (int i = 0; i < encodedData.length; i++) {
				result[i] = encodedData[i];
			}
			
			for (int i = dataLength; i < sourceData.length; i++) {
				result[i+(128-dataLength)] = sourceData[i];
			}

			// 为了解决加密后某些pdf阅读软件仍能打开的问题，加密后位数向后错位。
			// for (int i = data1.length + 1; i < data.length; i++) {
			// data[i] = (byte) (data[i] + 1);
			// }

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	/**
	 * 给文件加密
	 * @param sourceFile
	 * @param publicKeyStr
	 * @return
	 */
	public static byte[] encryptFile(File sourceFile, String publicKeyStr) {
		return encryptFile(sourceFile, Base64.decodeBase64(publicKeyStr));
	}

	/**
	 * 给文件加密
	 * @param sourceFile
	 * @return
	 */
	public static byte[] encryptFile(File sourceFile) {
		return encryptFile(sourceFile, Base64.decodeBase64(publicKeyStr));
	}
	
	/**
	 * 解密文件
	 * @param sourceFile
	 * @param privateKey
	 * @return
	 */
	public static byte[] decryptFile(File sourceFile, byte[] privateKey) {
		//加密源文件的文件流
		FileInputStream fis=null;
		//返回结果
		byte[] result=null;
		try {
			//根据来源文件，生成byte
			fis = new FileInputStream(sourceFile);
			byte[] sourceData = new byte[fis.available()];
			fis.read(sourceData);
			fis.close();
			//返回结果
			result=new byte[sourceData.length-(128-dataLength)];
			//被解密的byte数组
			byte[] data1 = new byte[128];
			for (int i = 0; i < data1.length; i++) {
				data1[i] = sourceData[i];
			}
			// 私钥解密
			byte[] decodedData = RSAUtils.decryByPrivateKey(data1, privateKey);
			for (int i = 0; i < decodedData.length; i++) {
				result[i] = decodedData[i];
			}
			for (int i = 128; i < sourceData.length; i++) {
				result[i-(128-dataLength)] = sourceData[i];
			}
			// 为了解决加密后某些pdf阅读软件仍能打开的问题，解密后位数向前错位。
			// for (int i = data1.length + 1; i < data.length; i++) {
			// data[i] = (byte) (data[i] - 1);
			// }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	/**
	 * 解密文件
	 * @param sourceFile
	 * @param privateKeyStr
	 * @return
	 */
	public static byte[] decryptFile(File sourceFile, String privateKeyStr) {
		return decryptFile(sourceFile, Base64.decodeBase64(privateKeyStr));
		
	}

	/**
	 * 解密文件
	 * @param sourceFile
	 * @return
	 */
	public static byte[] decryptFile(File sourceFile) {
		return decryptFile(sourceFile, Base64.decodeBase64(privateKeyStr));
	}
}
