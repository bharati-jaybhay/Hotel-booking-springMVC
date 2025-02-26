package com.sprk.sprk_hotels.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

@Service
public class QRCodeService {

    private static final String UPI_ID = "yourupi@bank";  // Replace with your actual UPI ID
    private static final String PAYEE_NAME = "Your Business Name"; // Your business or personal name
    private static final String CURRENCY = "INR";

    public String generateQRCode(int amount) {
        try {
            // UPI Payment URL
            String upiUri = String.format(
                    "upi://pay?pa=%s&pn=%s&am=%d&cu=%s",
                    UPI_ID, PAYEE_NAME, amount, CURRENCY
            );

            // Generate QR Code
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(upiUri, BarcodeFormat.QR_CODE, 250, 250);

            // Convert to Base64 String for Display
            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
            byte[] pngData = pngOutputStream.toByteArray();

            return "data:image/png;base64," + Base64.getEncoder().encodeToString(pngData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
