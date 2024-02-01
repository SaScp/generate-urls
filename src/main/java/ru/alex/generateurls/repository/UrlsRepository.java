package ru.alex.generateurls.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.alex.generateurls.model.response.URLs;

import java.util.Optional;

@Repository
public interface UrlsRepository extends MongoRepository<URLs, String> {

    URLs findByNewUrl(String url);
    Optional<URLs> findByYourUrl(String url);
}
