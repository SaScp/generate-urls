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
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultUrlGeneratorService implements UrlGeneratorService {
    
    private final UrlsRepository urlsRepository;
    
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
        int count = 1;

        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) == input.charAt(i - 1))
                count++;
            else {
                result.append(count).append(input.charAt(i - 1));
                count = 1;
            }
        }
        result.append(count).append(input.charAt(input.length() - 1));
        return result.toString();
    }
}
