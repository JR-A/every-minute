package kr.spring.view;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class ImageView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		
		byte[] file = (byte[]) model.get("imageFile");
		String filename = (String) model.get("filename");
		
		String ext = filename.substring(
				           filename.lastIndexOf("."));
		if(ext.equalsIgnoreCase(".gif")) {
			ext = "image/gif";
		}else if(ext.equalsIgnoreCase(".png")) {
			ext = "image/png";
		}else {
			ext = "image/jpeg";
		}

		response.setContentType(ext);
		response.setContentLength(file.length);

		String userAgent = request.getHeader("User-Agent");
		boolean ie = userAgent.indexOf("MSIE") > -1;
		String fileName = null;
		if (ie) {
			fileName = URLEncoder.encode(filename, "utf-8");
		} else {
			fileName = new String(filename.getBytes("utf-8"),
					"iso-8859-1");
		}
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		OutputStream out = response.getOutputStream();

		InputStream input = new ByteArrayInputStream(file);
		IOUtils.copy(input, out);
		out.flush();
		out.close();
		input.close();
	}

}
