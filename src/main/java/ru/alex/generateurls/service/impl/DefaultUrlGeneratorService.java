package ru.alex.generateurls.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alex.generateurls.model.request.URL;
import ru.alex.generateurls.model.response.URLs;
import ru.alex.generateurls.repository.UrlsRepository;
import ru.alex.generateurls.service.UrlGeneratorService;
import ru.alex.generateurls.util.exception.ResourceNotFoundException;

import java.net.MalformedURLException;
import java.time.Instant;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DefaultUrlGeneratorService implements UrlGeneratorService {

    private final UrlsRepository urlsRepository;
    private final String alphabetAndDigits = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    @Override
    public URLs generate(URL url) throws MalformedURLException {
        Optional<URLs> optionalURLs = urlsRepository.findByYourUrl(url.getUrl());
        if (optionalURLs.isEmpty()) {
            URLs urLs = URLs.builder()
                    .id(UUID.randomUUID().toString())
                    .yourUrl(url.getUrl())
                    .newUrl(encode(url.getUrl().replace("/", "")))
                    .timestamp(Date.from(Instant.now()))
                    .build();

            return urlsRepository.save(urLs);
        } else {
            return optionalURLs.get()
                    .builder()
                    .timestamp(Date.from(Instant.now())).build();
        }
    }


    @Override
    public String get(String url) {
        return urlsRepository.findByNewUrl(url)
                .orElseThrow(ResourceNotFoundException::new)
                .getYourUrl();
    }

    private String encode(String input) throws MalformedURLException {
        StringBuilder result = new StringBuilder();
        int size = input.length() / 3;

        for (int i = 0; i < size; i++) {
            result.append(alphabetAndDigits.charAt(new Random().nextInt(alphabetAndDigits.length() - 1)));
        }
        return result.append(".domain").toString();
    }
}
