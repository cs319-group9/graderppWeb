package com.cs319.graderpp.misc;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.File;
import java.io.IOException;

/**
 * Created by burak on 08.12.2015.
 */
@ManagedBean
@ApplicationScoped
public class SubmitFiles {
    public void submit(String filePath) throws IOException {
        String TARGET_URL = "http://localhost:5550/submit/39";
        //String FILE_PATH = "/home/burak/Documents/CS319/prof_omp.txt";

        System.out.println("in SubmitFiles with path " + filePath);

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost uploadFile = new HttpPost(TARGET_URL);

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody("field1", "yes", ContentType.TEXT_PLAIN);
        builder.addBinaryBody("file", new File(filePath), ContentType.APPLICATION_OCTET_STREAM, "file.ext");
        HttpEntity multipart = builder.build();

        uploadFile.setEntity(multipart);

        CloseableHttpResponse response = httpClient.execute(uploadFile);
        HttpEntity responseEntity = response.getEntity();

        if (responseEntity != null) {
            System.out.println("Response content length: " + responseEntity.getContentLength());
        }
    }
}
