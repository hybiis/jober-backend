package com.javajober.core.fcm;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.javajober.core.error.exception.Exception500;
import com.javajober.core.message.ErrorMessage;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Component
public class FcmInitializer {

    @Value("${fcm.certification}")
    private String googleApplicationCredentials;

    @PostConstruct
    public void initialize() {
        ClassPathResource resource = new ClassPathResource(googleApplicationCredentials);

        try (InputStream is = resource.getInputStream()) {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(is))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

        } catch (IOException e) {
            log.error("FCM initialization ERROR {}", e.getMessage());
            throw new Exception500(ErrorMessage.FCM_INITIALIZATION_FAILED);
        }
    }
}