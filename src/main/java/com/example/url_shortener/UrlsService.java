package com.example.url_shortener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class UrlsService {
    private final UrlsRepository urlsRepository;
    public UrlsService(UrlsRepository urlsRepository){
        this.urlsRepository = urlsRepository;
    }
    public Urls createUrl(String orginalUrl){
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWYZ0123456789";
        Random  random = new Random();
        StringBuilder code = new StringBuilder();
        for(int i = 0; i< 6;i++) {
            code.append(characters.charAt(random.nextInt(characters.length())));
        }
        Urls urls = new Urls();
        urls.setShortCode(code.toString());
        urls.setOriginalCode(orginalUrl);
       return urlsRepository.save(urls);
    }
    public Urls getUrl(String shortcode){
        Urls urls = urlsRepository.findByShortCode(shortcode).orElseThrow(() -> new RuntimeException("doesnt exist!"));
        urls.setCount(urls.getCount()+1);
        urlsRepository.save(urls);
        return urls;
    }
    public List<Urls> getAllUrls(){
        return urlsRepository.findAll();
    }

}
