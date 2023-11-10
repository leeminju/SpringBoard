package com.sparta.board.post.service;

import com.sparta.board.post.dto.PostRequestDto;
import com.sparta.board.post.dto.PostResponseDto;
import com.sparta.board.post.entity.Post;
import com.sparta.board.post.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostResponseDto createPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);

        Post savePost = postRepository.save(post);//저장

        return new PostResponseDto(savePost);
    }

    public List<PostResponseDto> getPost() {
        return postRepository.findAllByOrderByCreatedAtDesc().stream().map(PostResponseDto::new).toList();
    }

    public PostResponseDto getPostById(Long id) {
        Post findPost = findPost(id);
        return new PostResponseDto(findPost);
    }

    @Transactional
    public ResponseEntity<?> updatePost(Long id, PostRequestDto requestDto) {
        Post post = findPost(id);
        if (post.getPassword().equals(requestDto.getPassword())) {
            post.update(requestDto);
            PostResponseDto responseDto = new PostResponseDto(post);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } else {
            String message = HttpStatus.FORBIDDEN.value() + " " + HttpStatus.FORBIDDEN.getReasonPhrase();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(message);
        }
    }

    public ResponseEntity<?> deletePost(Long id, String password) {
        Post post = findPost(id);
        if (post.getPassword().equals(password)) {
            postRepository.delete(post);
            return ResponseEntity.status(HttpStatus.OK).body(id + "번 게시글 삭제 성공");
        } else {
            String message = HttpStatus.FORBIDDEN.value() + " " + HttpStatus.FORBIDDEN.getReasonPhrase();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(message);
        }
    }

    private Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시글는 존재하지 않습니다."));
        // 적합하지 않거나(illegal) 적절하지 못한(inappropriate) 인자를 메소드에 넘겨주었을 때 발생합니다.
        // "status": 500  "error": "Internal Server Error"
        // 서버에 오류가 발생했음

    }


}
