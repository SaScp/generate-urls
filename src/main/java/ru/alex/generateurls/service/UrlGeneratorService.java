package ru.alex.generateurls.service;

import ru.alex.generateurls.model.request.URL;
import ru.alex.generateurls.model.response.URLs;

import java.util.List;

public interface UrlGeneratorService {
    URLs generate(URL url);

    String get(String url);

    List<URLs> findAll();
}
