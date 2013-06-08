package net.wincn.blog.web;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.wincn.user.constant.FilePathEnum;

@Controller
@RequestMapping(value = "/blog")
public class BlogController {
	private String message = "";

	@RequestMapping(value = "{username}")
	public String index(@PathVariable String username) {
		return FilePathEnum.USER_MAIN_DIR.getPath() + username + "/index";
	}

	@RequestMapping(value = "{username}/newpost")
	public String newPost(@PathVariable String username) {
		return FilePathEnum.USER_MAIN_DIR.getPath() + username + "/newpost";
	}

	@RequestMapping(value = "test")
	@ResponseBody
	public void getMessage(HttpServletResponse response) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			int i = 0;
			while (i < 5) {
				Thread.sleep(1000);
				i++;
				out.write("aaaa-");
				out.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
}
