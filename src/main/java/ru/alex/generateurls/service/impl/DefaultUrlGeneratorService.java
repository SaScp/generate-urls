package ru.alex.generateurls.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alex.generateurls.model.request.URL;
import ru.alex.generateurls.model.response.URLs;
import ru.alex.generateurls.repository.UrlsRepository;
import ru.alex.generateurls.service.UrlGeneratorService;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultUrlGeneratorService implements UrlGeneratorService {

    private final UrlsRepository urlsRepository;
    private final String alphabetAndDigits = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    @Override
    public URLs generate(URL url) {
        URLs urLs = URLs.builder()
                .id(UUID.randomUUID().toString())
                .yourUrl(url.getUrl())
                .newUrl(encode(url.getUrl().replace("/", "")))
                .timestamp(Date.from(Instant.now()))
                .build();

        urlsRepository.save(urLs);
        return urlsRepository.save(urLs);
    }


    @Override
    public String get(String url) {
        return urlsRepository.findByNewUrl(url).getYourUrl();
    }

    @Override
    public List<URLs> findAll() {
        return urlsRepository.findAll();
    }

    private String encode(String input) {
        StringBuilder result = new StringBuilder();
        int size = input.length() / 2;

        for (int i = 0; i < size; i++) {
            result.append(alphabetAndDigits.charAt(new Random().nextInt(alphabetAndDigits.length() - 1)));
        }
        return result.append(".temp").toString();
    }
}
