package net.wincn.blog.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.wincn.base.utils.RSAUtils;

/**
 * 
 * @project base-mvc
 * @author 方帅
 * @email gefangshuai@163.com
 * @createDate 2013-6-6 上午9:11:07
 */
@Controller
@RequestMapping(value = "/download")
public class FileDownLoadController {

	@RequestMapping(value="index")
	public String index(HttpServletResponse response) {
		return "download/index";
	}

	@RequestMapping(value = "download")
	@ResponseBody
	public void download(HttpServletResponse response) {
		// response.setContentType("");
		OutputStream out = null;
		FileInputStream in = null;
		try {
			// in = new FileInputStream("F:/d1.swf");
			// in = new FileInputStream("F:/test/download/Encrypt.zip");
			in = new FileInputStream("F:/test/b.swf");
			out = response.getOutputStream();
			int bytesRead = 0;
			byte[] buffer = new byte[32 * 10 * 1024];
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
			}
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "public")
	@ResponseBody
	public void getKey(HttpServletResponse response,String password,String username) {
		byte[] data;
		try {
			data = RSAUtils.encryptByPrivateKey("123456789".getBytes());
			FileUtils.writeByteArrayToFile(new File("F:/key.txt"), data);
			IOUtils.write(data, response.getWriter());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
