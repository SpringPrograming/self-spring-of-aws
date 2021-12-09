package com.jongyun.book.springboot.service;

import com.jongyun.book.springboot.domain.posts.Posts;
import com.jongyun.book.springboot.domain.posts.PostsRepository;
import com.jongyun.book.springboot.web.dto.PostsListResponseDto;
import com.jongyun.book.springboot.web.dto.PostsResponseDto;
import com.jongyun.book.springboot.web.dto.PostsSaveRequestDto;
import com.jongyun.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        // JPA 의 영속성 컨텍스트
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    // readOnly true 옵션을 주게 되면 트랙잭션 범위는 유지하되
    // 조회 기능만 남겨두어 조회 속도가 개선되기 때문에 등록 수정 삭제 기능이
    // 전혀 없는 서비스 메소드 에서 사용하는 것을 추천
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                // .map(posts -> new PostsListsResponseDto(posts) 와 동일 한 코드
                // postsRepository 결과로 넘어온 Posts 의 Stream 을 map 을 통해
                // PostsListsResponseDto 로 변환 List 로 반환하는 메소드
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
       Posts posts = postsRepository.findById(id)
               .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
       /*
       * JpaRepository 에서 이미 delete 메소드를 지원하고 있으니 이를 활용합니다.
       * 엔티티 파라미터로 삭제할 수도 있고, deleteById 메소드를 이용하면 id 로 삭제 할 수도 있음
       * 존재하는 Posts 인지 확인을 위해 엔티티 조회 후 그대로 삭제합니다.
        */
       postsRepository.delete(posts);
    }
}