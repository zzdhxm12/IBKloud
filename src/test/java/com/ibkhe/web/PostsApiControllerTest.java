package com.ibkhe.web;

import com.ibkhe.domain.posts.Posts;
import com.ibkhe.domain.posts.PostsRepository;
import com.ibkhe.web.dto.PostsSaveRequestDto;
import com.ibkhe.web.dto.PostsUpdateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt.isEqualTo;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    public void tearDown() throws Exception{
        postsRepository.deleteAll();
    }

    @Test
    @DisplayName("Posts 등록된다.")
    public void test_postCreate() {
        // given
        String expectedTitle = "title";
        String expectedContent = "content";

        PostsSaveRequestDto requestDto
                = PostsSaveRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .author("author")
                .build();

        String url = "http://localhost:"+port+"/api/v1/posts";

        //when
        ResponseEntity<Long> responseEntity
                = restTemplate.postForEntity(url,requestDto,Long.class);

        //then
        assertThat( responseEntity.getStatusCode() ).isEqualTo(HttpStatus.OK);
        assertThat( responseEntity.getBody() ).isGreaterThan( 0L );

        List<Posts> postsList = postsRepository.findAll();

        assertThat( postsList.get(0).getTitle() ).isEqualTo(expectedTitle);
        assertThat( postsList.get(0).getContent() ).isEqualTo(expectedContent);
    }

    @Test
    @DisplayName("Posts 수정된다.")
    public void test_postUpdate throws Exception{
        //given

        //테스트용 게시글 db 입력
        Posts savedPosts = postsRepository.save(
                Posts.builder().title("title").content("content").author("author").build()
        );

        Long updateId = savedPosts.getId();
        String newTitle = "title2";
        String newContent = "content2";

        //게시글 수정에 사용할 requestDto 생성
        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(newTitle).content(newContent).build();

        //PostsApiController 에 게시글 수정 url을 설정
        String url = "http://localhost:"+port+"/api/v1/posts/"+updateId;

        //게시글 수정 요청을 보낼 requestDto를 담은 requestEntity 생성
        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url,
                HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(newTitle);
        assertThat(all.get(0).getContent()).isEqualTo(newContent);
    }
}
