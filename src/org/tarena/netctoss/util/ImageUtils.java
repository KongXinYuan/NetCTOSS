package org.tarena.netctoss.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageUtils {
	private static String str = "1234567890QWERTYUIOPASDFGHJKLZXCVBNM";
	private static char[] chars = str.toCharArray();
	private static int WIDTH = 140;
	private static int HEIGHT = 40;
	private static int SIZE = 4;
	private static int LINES = 6;
	private static int FONT_SIZE = 40;

	public static Map<String, BufferedImage> getImage() {
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = image.createGraphics();
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		StringBuffer bf = new StringBuffer();
		Random r = new Random();
		for (int i = 0; i < SIZE; i++) {
			char c = chars[r.nextInt(chars.length)];
			g.setColor(getColor());
			g.setFont(new Font(null,Font.BOLD,FONT_SIZE));
			g.drawString("" + c, i * WIDTH / SIZE, HEIGHT / 10 * 9);
			bf.append(c);
		}
		for (int i = 0; i < LINES; i++) {
			g.setColor(getColor());
			g.drawLine(r.nextInt(WIDTH), r.nextInt(HEIGHT), r.nextInt(WIDTH), r.nextInt(HEIGHT));
		}
		Map<String, BufferedImage> map = new HashMap<String, BufferedImage>();
		map.put(bf.toString(), image);
		return map;
	}

	private static Color getColor() {
		Random r = new Random();
		return new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
	}
	
	public static InputStream imageToStream(BufferedImage image) throws Exception{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
		encoder.encode(image);
		byte[] bytes = bos.toByteArray();
		InputStream is = new ByteArrayInputStream(bytes);
		return is;
	}
}
