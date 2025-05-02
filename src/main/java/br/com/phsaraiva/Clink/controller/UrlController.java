package br.com.phsaraiva.Clink.controller;

import br.com.phsaraiva.Clink.model.Url;
import br.com.phsaraiva.Clink.services.UrlServices;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/url")
public class UrlController {

    Logger logger = LoggerFactory.getLogger(UrlController.class);

    @Autowired
    private UrlServices urlServices;

    @PostMapping("/shorten")
    public ResponseEntity<Map<String,String>> shortenUrl(@RequestBody Map<String, String> request) {
        String originalUrl = request.get("url");
        if (originalUrl == null) { throw new RuntimeException("Url Null"); }
        String shortUrl = urlServices.shortenUrl(originalUrl);
        Map<String, String> response = new HashMap<String, String>();
        response.put("url", "https://localhost:8080/"+shortUrl);
        return  ResponseEntity.ok(response);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Object> countClicksForLink(@PathVariable("shortUrl") String shortUrl) {
        Optional<Url> optionalUrl = urlServices.clicksForLink(shortUrl);
        if (optionalUrl.isPresent()) {
           return ResponseEntity.ok(optionalUrl.get());
        }
        return ResponseEntity.notFound().build();
    }
}
