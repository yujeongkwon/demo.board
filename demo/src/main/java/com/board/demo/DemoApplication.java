package com.board.demo;

import com.board.demo.domain.Board;
import com.board.demo.domain.User;
import com.board.demo.repository.BoardRepository;
import com.board.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

		@Bean
		public CommandLineRunner runner(BoardRepository boardRepository, UserRepository userRepository) throws Exception {
			// 100개의 임시 사용자, 게시글 생성
			return args ->
					IntStream.rangeClosed(1, 94).forEach(i -> {
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
					});
			}


}
