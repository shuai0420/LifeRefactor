package net.wincn.crypto.core;

/**
 * 加解密工具类
 * 
 * @project base-mvc
 * @author 方帅
 * @email gefangshuai@163.com
 * @createDate 2013-6-7 下午4:12:11
 */
public class Crypto {
	private long _Keys[] = { 0x12345678, 0x23456789, 0x34567890 };
	private static Crypto instance = null;

	private Crypto() {
	}

	private short MagicByte() {
		int t;
		t = (int) ((_Keys[2] & 0xFFFF) | 2);
		t = ((t * (t ^ 1)) >> 8);
		return (short) t;
	}

	private void UpdateKeys(short byteValue) {
		short key0val;
		_Keys[0] = Crc32.update(_Keys[0], byteValue);
		key0val = (byte) _Keys[0];
		if ((byte) _Keys[0] < 0) {
			key0val += 256;
		}
		_Keys[1] = _Keys[1] + key0val;
		_Keys[1] = (_Keys[1] * 0x08088405);
		_Keys[1] = _Keys[1] + 1;
		_Keys[2] = Crc32.update(_Keys[2], (byte) (_Keys[1] >> 24));

	}

	/**
	 * 对密码进行处理
	 **/
	public void InitCipher(String password) {
		_Keys[0] = 0x12345678;
		_Keys[1] = 0x23456789;
		_Keys[2] = 0x34567890;
		for (int i = 0; i < password.length(); i++) {
			UpdateKeys((byte) password.charAt(i));
		}
	}

	/**
	 * 根据指定的长度进行加密
	 * 
	 * @param byteInfo
	 * @param length
	 * @return
	 */
	public byte[] encrypt(byte[] byteInfo, int length) {
		for (int i = 0; i < length; i++) {
			byte C = byteInfo[i];
			byteInfo[i] = (byte) (byteInfo[i] ^ MagicByte());
			UpdateKeys(C);
		}
		return byteInfo;
	}

	/**
	 * 加密,加密第100~第199位及倒数length-1-199~length-1-100位 如果不够400位则加密前200位
	 * 如果不够200位，则加密全部
	 * 
	 * @param plaintext
	 * @param length
	 * @return
	 */
	public byte[] encrypt(byte[] byteInfo) {
		int length = byteInfo.length;
		if (length < 200) {// 长度不够，加密全部
			for (int i = 0; i < length; i++) {
				byte C = byteInfo[i];
				byteInfo[i] = (byte) (byteInfo[i] ^ MagicByte());
				UpdateKeys(C);
			}
		} else if (200 <= length && length < 400) {// 不足400，加密前200位
			for (int i = 0; i < 200; i++) {
				byte C = byteInfo[i];
				byteInfo[i] = (byte) (byteInfo[i] ^ MagicByte());
				UpdateKeys(C);
			}
		} else {
			for (int i = 100; i < 200; i++) {// 加密100-199位
				byte C = byteInfo[i];
				byteInfo[i] = (byte) (byteInfo[i] ^ MagicByte());
				UpdateKeys(C);
			}
			for (int i = length - 1 - 199; i < length - 100; i++) {
				byte C = byteInfo[i];
				byteInfo[i] = (byte) (byteInfo[i] ^ MagicByte());
				UpdateKeys(C);
			}
		}
		return byteInfo;
	}

	/**
	 * 根据指定的长度进行解密
	 * 
	 * @param byteInfo
	 * @param length
	 * @return
	 */
	public byte[] decrypt(byte[] byteInfo, int length) {
		for (int i = 0; i < length; i++) {
			short m = MagicByte();
			byte C = (byte) (byteInfo[i] ^ m);
			if (C < 0) {
				UpdateKeys((short) ((short) C + 256));
				byteInfo[i] = (byte) (short) ((short) C + 256);
			} else {
				UpdateKeys(C);
				byteInfo[i] = C;
			}
		}
		return byteInfo;
	}

	/**
	 * 解密
	 * 
	 * @param cipherText
	 * @param length
	 * @return
	 */
	public byte[] decrypt(byte[] byteInfo) {
		int length = byteInfo.length;
		if (length < 200) {// 长度不够，解密全部
			for (int i = 0; i < length; i++) {
				short m = MagicByte();
				byte C = (byte) (byteInfo[i] ^ m);
				if (C < 0) {
					UpdateKeys((short) ((short) C + 256));
					byteInfo[i] = (byte) (short) ((short) C + 256);
				} else {
					UpdateKeys(C);
					byteInfo[i] = C;
				}
			}
		} else if (200 <= length && length < 400) {// 不足400，解密前200位
			for (int i = 0; i < 200; i++) {
				short m = MagicByte();
				byte C = (byte) (byteInfo[i] ^ m);
				if (C < 0) {
					UpdateKeys((short) ((short) C + 256));
					byteInfo[i] = (byte) (short) ((short) C + 256);
				} else {
					UpdateKeys(C);
					byteInfo[i] = C;
				}
			}
		} else {
			for (int i = 100; i < 200; i++) {// 解密100-199位
				short m = MagicByte();
				byte C = (byte) (byteInfo[i] ^ m);
				if (C < 0) {
					UpdateKeys((short) ((short) C + 256));
					byteInfo[i] = (byte) (short) ((short) C + 256);
				} else {
					UpdateKeys(C);
					byteInfo[i] = C;
				}
			}
			for (int i = length - 1 - 199; i < length - 100; i++) {
				short m = MagicByte();
				byte C = (byte) (byteInfo[i] ^ m);
				if (C < 0) {
					UpdateKeys((short) ((short) C + 256));
					byteInfo[i] = (byte) (short) ((short) C + 256);
				} else {
					UpdateKeys(C);
					byteInfo[i] = C;
				}
			}
		}
		return byteInfo;
	}

	public static Crypto getInstance() {
		if (instance == null) {
			instance = new Crypto();
		}
		return instance;
	}
}