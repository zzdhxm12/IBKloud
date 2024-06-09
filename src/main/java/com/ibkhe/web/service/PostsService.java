package com.ibkhe.web.service;

import com.ibkhe.domain.posts.Posts;
import com.ibkhe.domain.posts.PostsRepository;
import com.ibkhe.web.dto.PostsResponseDto;
import com.ibkhe.web.dto.PostsSaveRequestDto;
import com.ibkhe.web.dto.PostsUpdateRequestDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto postsSaveRequestDto) {
        return postsRepository.save( postsSaveRequestDto.toEntity() ).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findByld(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 게시글이 없습니다. id="+ id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findByld(Long id){
        Posts entity = postsRepository.findByld(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDto(entity); }
}
