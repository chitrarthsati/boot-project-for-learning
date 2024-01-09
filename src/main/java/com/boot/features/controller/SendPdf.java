package com.boot.features.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class SendPdf {

	public static void main(String[] args) {
		String springBootUrl = "http://10.10.21.89:8080/pdf/upload";
		String filePath = "C://Users//csati//Downloads//appraisal.pdf";

		try {
			sendFile(springBootUrl, filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void sendFile(String url, String filePath) throws IOException {

		URL serverUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) serverUrl.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.addRequestProperty("companyId", "1");
        connection.addRequestProperty("subscriberId", "1");
        String boundary = "---------------------------" + System.currentTimeMillis();

        // Set request headers for multipart form-data
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

        try (OutputStream outputStream = connection.getOutputStream()) {
        	
        	outputStream.write(("--" + boundary + "\r\n").getBytes());
        	outputStream.write(("Content-Disposition: form-data; name=\"fileName\"; filename=\"appraisal.pdf\"\r\n").getBytes());
        	outputStream.write(("Content-Disposition: form-data; name=\"companyId\"\r\n\r\n" + "1\r\n").getBytes());
        	outputStream.write(("Content-Disposition: form-data; name=\"subscriberId\"\r\n\r\n" + "2\r\n").getBytes());
        	outputStream.write(("Content-Type: application/pdf\r\n\r\n").getBytes());
            // Write the file part header
            writeFormField(outputStream, boundary, "fileName", "appraisal.pdf");
            // Write the file content to the output stream
            writeBinaryFile(outputStream, filePath);

            // Write the first string parameter
            writeFormField(outputStream, boundary, "companyId", "1");
            // Write the second string parameter
            writeFormField(outputStream, boundary, "subscriberId", "2");

            // Write the closing boundary
            outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());
        }

        // Get the response from the server
        int responseCode = connection.getResponseCode();
        System.out.println("Server response code: " + responseCode);

        // Read the response if needed
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        connection.disconnect();
	}

	private static void writeFormField(OutputStream outputStream, String boundary, String name, String value)
			throws IOException {
		String formField = String.format("--%s\r\nContent-Disposition: form-data; name=\"%s\"\r\n\r\n%s\r\n",
                boundary, name, value);
        outputStream.write(formField.getBytes(StandardCharsets.UTF_8));
	}

	private static void writeBinaryFile(OutputStream outputStream, String filePath)
            throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }
}
