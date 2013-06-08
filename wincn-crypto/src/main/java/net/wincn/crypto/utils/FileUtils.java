package net.wincn.crypto.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import org.apache.log4j.Logger;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.activation.DataHandler;

public class FileUtils {

	public static Logger log = Logger.getLogger(FileUtils.class);

	/**
	 * 获取文件列表
	 * 
	 * @param String
	 *            fileDir 获取文件的目录
	 * @return 文件数组
	 */
	public static File[] getFileList(String fileDir) {
		File dir = new File(fileDir);
		for (String children : dir.list()) {
			System.out.println(children);
		}
		return dir.listFiles();
	}

	/**
	 * 读取源文件字符数组
	 * 
	 * @param File
	 *            file 获取字符数组的文件
	 * @return 字符数组
	 */
	public static byte[] readFileByte(File file) {
		FileInputStream fis = null;
		FileChannel fc = null;
		byte[] data = null;
		try {
			fis = new FileInputStream(file);
			fc = fis.getChannel();
			data = new byte[(int) (fc.size())];
			fc.read(ByteBuffer.wrap(data));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fc != null) {
				try {
					fc.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return data;
	}

	/**
	 * 读取源文件字符数组
	 * 
	 * @param filename
	 *            String 文件路径
	 * @throws IOException
	 * @return byte[] 文件内容
	 */
	public static byte[] readFileByte(String filename) throws IOException {

		if (filename == null || filename.equals("")) {
			throw new NullPointerException("无效的文件路径");
		}
		File file = new File(filename);
		long len = file.length();
		byte[] bytes = new byte[(int) len];

		BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
		int r = bufferedInputStream.read(bytes);
		if (r != len)
			throw new IOException("读取文件不正确");
		bufferedInputStream.close();

		return bytes;

	}

	/**
	 * 字符数组写入文件
	 * 
	 * @param byte[] bytes 被写入的字符数组
	 * @param File
	 *            file 被写入的文件
	 * @return 字符数组
	 */
	public static String writeByteFile(byte[] bytes, File file) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			fos.write(bytes);
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "success";
	}

	/**
	 * 移动指定文件夹内的全部文件,(剪切移动)
	 * 
	 * @param fromDir
	 *            要移动的文件目录
	 * @param toDir
	 *            目标文件目录
	 * @param errDir
	 *            出错文件目录
	 * @throws Exception
	 */
	public static void moveFile(String fromDir, String toDir, String errDir) {
		try {
			// 目标文件目录
			File destDir = new File(toDir);
			if (!destDir.exists()) {
				destDir.mkdirs();
			}
			// 开始文件移动
			for (File file : new File(fromDir).listFiles()) {
				if (file.isDirectory()) {
					moveFile(file.getAbsolutePath(), toDir + File.separator + file.getName(), errDir);
					file.delete();
					log.info("文件夹" + file.getName() + "删除成功");
				} else {
					File moveFile = new File(toDir + File.separator + file.getName());
					if (moveFile.exists()) {
						moveFileToErrDir(moveFile, errDir);// 转移到错误目录
					}
					file.renameTo(moveFile);
					log.info("文件" + moveFile.getName() + "转移到错误目录成功");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

	private static void moveFileToErrDir(File moveFile, String errDir) {
		int i = 0;
		String errFile = errDir + File.separator + "rnError" + moveFile.getName();
		while (new File(errFile).exists()) {
			i++;
			errFile = errDir + File.separator + i + "rnError" + moveFile.getName();
		}
		moveFile.renameTo(new File(errFile));
	}

	/**
	 * 从输入流获取字节数组
	 * 
	 * @param
	 */
	public static byte[] getFileByte(InputStream in) {
		ByteArrayOutputStream out = new ByteArrayOutputStream(4096);
		try {
			copy(in, out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out.toByteArray();

	}

	/**
	 * 从输入流输出到输出流
	 * 
	 */
	private static void copy(InputStream in, OutputStream out) throws IOException {

		try {
			byte[] buffer = new byte[4096];
			int nrOfBytes = -1;
			while ((nrOfBytes = in.read(buffer)) != -1) {
				out.write(buffer, 0, nrOfBytes);
			}
			out.flush();
		} catch (IOException e) {

		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
			}
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException ex) {
			}
		}

	}

	// DataHandler写入文件
	public static boolean writeDataHandlerToFile(DataHandler attachinfo, String filename) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filename);
			writeInputStreamToFile(attachinfo.getInputStream(), fos);
			fos.close();
		} catch (Exception e) {
			return false;
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e) {
				}
			}
		}
		return true;
	}

	private static void writeInputStreamToFile(InputStream is, OutputStream os) throws Exception {
		int n = 0;
		byte[] buffer = new byte[8192];
		while ((n = is.read(buffer)) > 0) {
			os.write(buffer, 0, n);
		}
	}
}
