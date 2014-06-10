package org.tarena.netctoss.action;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.tarena.netctoss.util.ImageUtils;
@Controller
public class ImageAction extends BaseAction{
	private InputStream is;
	public String execute(){
		Map<String, BufferedImage> map = ImageUtils.getImage();
		String code = map.keySet().iterator().next();
		session.put("code", code);
		BufferedImage image = map.get(code);
		try {
			is = ImageUtils.imageToStream(image);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	public InputStream getIs() {
		return is;
	}
	public void setIs(InputStream is) {
		this.is = is;
	}
	
}
