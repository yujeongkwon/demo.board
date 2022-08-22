package com.board.demo.repository;

import com.board.demo.domain.Board;
import com.board.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

//@Repository 필요x 기본적인 CRUD 아래로 가능

//JpaRepository의 부모 인터페이스 PagingAndSortingRepository -> 페이징,소팅제공
public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByTitleContaining(String keyword);
    // 특정문자 검색 일단 제목기준으로
}