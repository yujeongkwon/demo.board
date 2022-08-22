package com.board.demo.service;

import com.board.demo.domain.Board;
import com.board.demo.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
//결국 우리는 JpaRepository<> 를 사용할 때, findAll() 메서드를
// Pageable 인터페이스로 파라미터를 넘기면 페이징을 사용할 수 있게된다.

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    // Respository로 Pageable을 넘겨주면 pagination 처리 가능
    // pageable로 넘어온 pageNumber 객체 <= 0 ? 0으로 초기화
    // 기본 페이지 크기인 10으로 새로운 PageRequest 객체를 만들어 페이징 처리된 게시글 리스트를 반환한다.
    public Page<Board> findBoardList(Pageable pageable) {   //리스트 타입을 page로
        //PageRequest.of(int page, int size) : 페이지 번호(0부터 시작), 페이지당 데이터의수
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                10);    // PageRequest.of() 사용해 pageable객체 생성
        return boardRepository.findAll(pageable);
        //사용하는 SQL의 문법에 맞게 페이징 쿼리를 만들어서 DB에 데이터를 가져옴
    }


    public Board findBoardByIdx(Long idx) {
        return boardRepository.findById(idx).orElse(new Board());
    }

    @Transactional
    public List<Board> search(String keyword) { //키워드 제목찾기
        List<Board> boardList = boardRepository.findByTitleContaining(keyword);
        return boardList;
    }
}