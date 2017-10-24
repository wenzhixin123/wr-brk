package com.sinotrans.gd.wlp.util;

import java.awt.image.BufferedImage;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;

public class BarcodeUtil {

	public static BufferedImage generateQRCode(String content, int width, int height) {
		
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		
		try {
			return MatrixToImageWriter.toBufferedImage(qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width,height));
			
		} catch (WriterException e) {
			e.printStackTrace();
			
			return new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		}
	}
}
