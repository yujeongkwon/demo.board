package com.board.demo.controller;


import com.board.demo.domain.Board;
import com.board.demo.repository.BoardRepository;
import com.board.demo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;


    @GetMapping("/board/search")    //검색
    public String search(String keyword, Model model) {
        List<Board> searchList = boardService.search(keyword);
        model.addAttribute("searchList", searchList);
        return "search/searchPage";
    }

    /*
     * 게시글 목록
     */
    @GetMapping("/list")
    public String list(@PageableDefault Pageable pageable, Model model) {
        model.addAttribute("boardList", boardService.findBoardList(pageable));
        return "board/list";
    }

    /*
     * 게시글 상세 및 등록 폼 호출
     */
    @GetMapping({"", "/"})
    public String board(@RequestParam(value = "idx", defaultValue = "0") Long idx, Model model) {
        model.addAttribute("board", boardService.findBoardByIdx(idx));
        return "board/form";
    }

    /*
     * 게시글 생성
     */
    @PostMapping
    public ResponseEntity<?> postBoard(@RequestBody Board board) {
        board.setCreatedDate(LocalDateTime.now());
        boardRepository.save(board);

        return new ResponseEntity<>("{}", HttpStatus.CREATED);
    }

    /*
     * 게시글 수정
     */
    @PutMapping("/{idx}")
    public ResponseEntity<?> putBoard(@PathVariable("idx") Long idx, @RequestBody Board board) {
        Board updateBoard = boardRepository.getOne(idx);
        updateBoard.setTitle(board.getTitle());
        updateBoard.setContent(board.getContent());
        boardRepository.save(updateBoard);

        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    /*
     * 게시글 삭제
     */
    @DeleteMapping("/{idx}")
    public ResponseEntity<?> deleteBoard(@PathVariable("idx") Long idx) {
        boardRepository.deleteById(idx);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }
}