package com.lease.web.admin.service.impl;

import com.lease.common.minio.MinioProperties;
import com.lease.web.admin.service.FileService;
import io.minio.*;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    private final MinioClient client;
    private final MinioProperties properties;

    @Autowired
    public FileServiceImpl(MinioClient client, MinioProperties properties) {
        this.client = client;
        this.properties = properties;
    }

    @Override
    public String upload(MultipartFile file) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        boolean bucketExists = client.bucketExists(
                BucketExistsArgs.builder()
                        .bucket(properties.getBucketName())
                        .build());
        if (!bucketExists) {
            client.makeBucket(
                    MakeBucketArgs.builder()
                            .bucket(properties.getBucketName())
                            .build());
            client.setBucketPolicy(
                    SetBucketPolicyArgs.builder()
                            .bucket(properties.getBucketName())
                            .config(createBucketPolicyConfig(properties.getBucketName()))
                            .build());
        }
        String filename = new SimpleDateFormat("yyyyMMdd").format(new Date()) +
                "/" + UUID.randomUUID() + "-" + file.getOriginalFilename();
        client.putObject(
                PutObjectArgs.builder()
                        .bucket(properties.getBucketName())
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .object(filename)
                        .contentType(file.getContentType())
                        .build());

        return String.join("/",properties.getEndpoint(),properties.getBucketName(),filename);
    }

    private String createBucketPolicyConfig(String bucketName) {

        return """
            {
              "Statement" : [ {
                "Action" : "s3:GetObject",
                "Effect" : "Allow",
                "Principal" : "*",
                "Resource" : "arn:aws:s3:::%s/*"
              } ],
              "Version" : "2012-10-17"
            }
            """.formatted(bucketName);
    }
}
