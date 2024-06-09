package com.ibkhe.web.controller;

import com.ibkhe.web.dto.PostsResponseDto;
import com.ibkhe.web.dto.PostsSaveRequestDto;
import com.ibkhe.web.dto.PostsUpdateRequestDto;
import com.ibkhe.web.service.PostsService;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto ){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/vl/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/vl/posts/{id}")
    public PostsResponseDto findByld(@PathVariable Long id){
        return postsService.findByld(id);
    }

}
