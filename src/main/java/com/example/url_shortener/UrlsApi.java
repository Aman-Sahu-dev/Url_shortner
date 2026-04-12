package com.example.url_shortener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/urlshortner")
public class UrlsApi {
    private final UrlsService urlsService;
    public UrlsApi(UrlsService urlsService){
        this.urlsService = urlsService;
    }
    @PostMapping
    public Urls saveUrls(@RequestBody UrlsRequest request){
         return urlsService.createUrl(request.getOriginalUrl());
    }
    @GetMapping("/{shortcode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortcode){
        Urls urls = urlsService.getUrl(shortcode);
        return ResponseEntity
                .status(302)
                .header("Location", urls.getOriginalCode())
                .build();
    }
    @GetMapping("/all")
    public List<Urls> getAll() {
        return urlsService.getAllUrls();
    }
}
