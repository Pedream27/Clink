package br.com.phsaraiva.Clink.controller;

import br.com.phsaraiva.Clink.model.Url;
import br.com.phsaraiva.Clink.services.UrlServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Optional;

@RestController
public class ShortController {

    Logger logger = LoggerFactory.getLogger(ShortController.class);
    private UrlServices urlServices;

    public ShortController(UrlServices urlServices) {
        this.urlServices = urlServices;
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Object> redirectToOriginalUrl(@PathVariable("shortUrl") String shortUrl) {
        Optional<Url> optionalUrl = urlServices.findByShortUrl(shortUrl);
        if (optionalUrl.isPresent()) {
            Url url = optionalUrl.get();
            logger.info("Redirecting to original url: " + url.getOriginalUrl());
            return ResponseEntity.status(302).location(URI.create(url.getOriginalUrl())).build();
        }
        return ResponseEntity.notFound().build();
    }
}
