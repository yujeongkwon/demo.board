package com.board.demo.service;

import com.board.demo.domain.Board;
import com.board.demo.domain.User;
import com.board.demo.repository.BoardRepository;
import com.board.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class BoardServiceTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void findBoardList() {
        for(int i = 0; i < 10; i++) {
            User user =  User.builder()
                    .username("tester" + i)
                    .build();
            userRepository.save(user);

            boardRepository.save(Board.builder()
                    .user(user)
                    .title("test" + i)
                    .content("내용"+i)
                    .createdDate(LocalDateTime.now())
                    .build());
        }

        Pageable pageable = PageRequest.of(0, 5);
        System.out.println("boardRepository.findAll() = " + boardRepository.findAll(pageable));
    }

    @Test
    void findBoardByIdx() {
    }

    @Test
    void search() {
    }
}