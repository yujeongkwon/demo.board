package com.board.demo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor  //기본생성자 @Builder은 jap 애너테이션 = 생성자 필요
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_idx")
    private Long idx;

    // 글쓴이 - 게시판 1 : N (주인 = 게시판)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private String user; //글쓴이

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private LocalDateTime createdDate;

    @Builder
    public Board(Long idx,User user, String title, String content, LocalDateTime createdDate) {
        this.idx = idx;
        this.user = user.getUsername();
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
    }

}
