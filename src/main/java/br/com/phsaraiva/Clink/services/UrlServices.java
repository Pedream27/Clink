package br.com.phsaraiva.Clink.services;

import br.com.phsaraiva.Clink.model.Url;
import br.com.phsaraiva.Clink.repository.UrlRepository;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class UrlServices {

    private UrlRepository urlRepository;

    public UrlServices(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String shortenUrl(String originalUrl) {
        String shortUrl = genereateShortUrl();
        Url url = new Url();
        url.setShortUrl(shortUrl);
        url.setOriginalUrl(originalUrl);
        url.setExpiresAt(LocalDateTime.now().plusDays(30));
        url.setClicks(0);
        urlRepository.save(url);
        return shortUrl;
    }

    private String genereateShortUrl() {
        String  characteres = "ABCDEFGHIJLMNOPQRSTUVXZWYabcdefghijlmnopqrstuvxzwy";
        StringBuilder shortUrl = new StringBuilder();
        Random random = new Random();
       int length = 5 + random.nextInt(6);
       for (int i = 0; i < length; i++) {
           shortUrl.append(characteres.charAt(random.nextInt(characteres.length())));
       }
      var  urlIsPresent = urlRepository.findByShortUrl(shortUrl.toString());
       if (urlIsPresent.isPresent()) {
           return genereateShortUrl();
       }
       return shortUrl.toString();
    }

    public Optional<Url> clicksForLink(String shortUrl) {
        Optional<Url> url = urlRepository.findByShortUrl(shortUrl);
        if (url.isPresent()) {
            return Optional.of(url.get());
        }
        return Optional.empty();
    }

    public Optional<Url> findByShortUrl(String shortUrl) {
    Optional<Url> url = urlRepository.findByShortUrl(shortUrl);

   if (url.isPresent()) {
       if (url.get().getExpiresAt().isAfter(LocalDateTime.now())) {
           url.get().setClicks(url.get().getClicks() + 1);
           urlRepository.save(url.get());
           return Optional.of(url.get());
       }else{
           urlRepository.delete(url.get());
       }
   }
        return Optional.empty();
    }


}
