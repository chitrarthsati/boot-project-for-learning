package com.boot.features.utils;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClients;

public class FileAndParamsUploader {

    public static void main(String[] args) {
        String springBootUrl = "http://10.10.21.89:8080/pdf/upload";
        String filePath = "C://Users//csati//Downloads//appraisal.pdf";
        String paramName1 = "companyId";
        String paramValue1 = "1";
        String paramName2 = "subscriberId";
        String paramValue2 = "641";

        try {
            sendFileAndParams(springBootUrl, filePath, paramName1, paramValue1, paramName2, paramValue2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendFileAndParams(String url, String filePath,
                                          String paramName1, String paramValue1,
                                          String paramName2, String paramValue2) throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addPart("fileName", new FileBody(new File(filePath), ContentType.APPLICATION_FORM_URLENCODED));
        builder.addPart(paramName1, new StringBody(paramValue1, ContentType.TEXT_PLAIN));
        builder.addPart(paramName2, new StringBody(paramValue2, ContentType.TEXT_PLAIN));

        HttpEntity multipart = builder.build();
        httpPost.setEntity(multipart);

        HttpResponse response = httpClient.execute(httpPost);

        // Handle the response if needed
        int responseCode = response.getStatusLine().getStatusCode();
        System.out.println("Server response code: " + responseCode);

        // Read the response if needed
        // ...

//        httpClient.close();
    }
}
