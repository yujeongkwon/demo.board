package com.board.demo.domain;

import com.board.demo.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class BoardTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    private BoardRepository boardRepository;

    private final String title = "테스트";
    private final String content = "내용";

    @Test
    public void 게시판도메인() throws Exception{
        boardRepository.save(Board.builder()
                        .user(createUser())
                        .title(title)
                        .content(content)
                .createdDate(LocalDateTime.now()).build());

        Board board = boardRepository.getReferenceById((long) 1);
        assertEquals(board.getTitle(),title);
        assertEquals(board.getContent(),content);
    }

    private User createUser() {
        User user = new User();
        user.setUsername("회원1");
        em.persist(user);
        return user;
    }
}