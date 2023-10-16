package com.example.camelSnsRoute.Controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config{
    private static final String ACCESS_KEY = "AKIA2YE6VFZCRY7MDXSE";
    private static final String SECRET_KEY = "t45ZV+XTDV1AW0EKSU29zx/46Fp0aeH72uuB7oJf";
    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
            .region(Region.US_EAST_1) // Set your desired AWS region
            .credentialsProvider(credentialsProvider())
            .build();
    }

    @Bean
    public AwsCredentialsProvider credentialsProvider() {
        AwsBasicCredentials credentials = AwsBasicCredentials.create(ACCESS_KEY, SECRET_KEY);
        return StaticCredentialsProvider.create(credentials);
    }
}
