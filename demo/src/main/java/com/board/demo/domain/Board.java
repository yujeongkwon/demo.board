package com.board.demo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_idx")
    private Long idx;

    // 글쓴이 - 게시판 1 : N (주인 = 게시판)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; //글쓴이

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private LocalDateTime createdDate;

    @Builder
    public Board(Long idx,User user, String title, String content, LocalDateTime createdDate) {
        this.idx = idx;
        this.user = user;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
    }

}
