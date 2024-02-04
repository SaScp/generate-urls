package ru.alex.generateurls.controller;



import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.alex.generateurls.model.request.URL;
import ru.alex.generateurls.model.response.Info;
import ru.alex.generateurls.model.response.URLs;
import ru.alex.generateurls.service.UrlGeneratorService;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/click")

public class Controller {

    private final UrlGeneratorService generatorService;

    @PostMapping("/create")
    public ResponseEntity<URLs> create(@RequestBody @Valid URL url) throws URISyntaxException, MalformedURLException {
        return ResponseEntity
                .created(new URI("/create"))
                .contentType(MediaType.APPLICATION_JSON)
                .body(generatorService.generate(url));
    }

    @GetMapping("/{url}")
    public RedirectView redirect(@PathVariable("url") String url) {
        return new RedirectView(generatorService.get(url));
    }
    @GetMapping("/")
    public Info getInfoOfProject() {
        return Info.getInstant();
    }

}
