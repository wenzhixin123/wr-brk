package com.sinotrans.gd.wlp.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.servlet.http.HttpSession;

public class GenerateCheckCode {
	public static final String CHECK_CODE_KEY = "CheckCode";
	public static final int WIDTH = 64;
	public static final int HEIGHT = 20;

	/**
	 * <p>
	 * 取得验证码图片，并把生成的数字放到HttpSession中
	 * </p>
	 */

	public static BufferedImage getImage(HttpSession session) {
		// 创建内存图象并获得其图形上下文
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		// 产生随机的认证码
		char[] rands = generateCheckCode();
		// 产生图像
		drawBackground(g);
		drawRands(g, rands);
		// 结束图像的绘制过程，完成图像
		g.dispose();
		// 将当前验证码存入到Session中
		session.setAttribute(CHECK_CODE_KEY, new String(rands));
		return image;
	}

	/**
	 * <p>
	 * 随机产生验证码字符
	 * </p>
	 */
	public static char[] generateCheckCode() {
		// 定义验证码的字符表
		String chars = "23456789ABCDEFGHJKLMNPRSTUVWXYZ";
		char[] rands = new char[4];
		for (int i = 0; i < 4; i++) {
			int rand = (int) (Math.random() * chars.length());
			rands[i] = chars.charAt(rand);
		}
		return rands;
	}

	/**
	 * <p>
	 * 字符位置、字体属性
	 * </p>
	 */
	public static void drawRands(Graphics g, char[] rands) {
		g.setColor(Color.BLACK);
		g.setFont(new Font(null, Font.ITALIC | Font.BOLD, 26));
		// 在不同的高度上输出验证码的每个字符
		g.drawString("" + rands[0], 1, 17);
		g.drawString("" + rands[1], 16, 15);
		g.drawString("" + rands[2], 31, 18);
		g.drawString("" + rands[3], 46, 16);
		// System.out.println(rands);
	}

	/**
	 * <p>
	 * 背景
	 * </p>
	 */
	public static void drawBackground(Graphics g) {
		// 画背景
		g.setColor(new Color(0xDCDCDC));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		// 随机产生120个干扰点
		for (int i = 0; i < 60; i++) {
			int x = (int) (Math.random() * WIDTH);
			int y = (int) (Math.random() * HEIGHT);
			/*
			 * int red = (int) (Math.random() * 128); int green = (int)
			 * (Math.random() * 128); int blue = (int) (Math.random() * 128);
			 */
			// g.setColor(new Color(red, green, blue));
			g.setColor(new Color(255, 255, 255));
			g.drawOval(x, y, 1, 0);
		}
		// 画4条直线
		for (int i = 0; i < 4; i++) {
			/*
			 * int red = (int) (Math.random() * 255); int green = (int)
			 * (Math.random() * 255); int blue = (int) (Math.random() * 255);
			 * g.setColor(new Color(red, green, blue));
			 */
			g.setColor(new Color(255, 255, 255));
			g.drawLine((int) (Math.random() * WIDTH),
					(int) (Math.random() * HEIGHT),
					(int) (Math.random() * WIDTH),
					(int) (Math.random() * HEIGHT));
		}
	}

}