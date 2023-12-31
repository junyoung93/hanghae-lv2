package com.lv2.spartalv2hw.entity;

import com.lv2.spartalv2hw.dto.BoardRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Board extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String username;

    public Board(BoardRequestDto boardRequestDto, String tokenUsername) {
        this.contents= boardRequestDto.getContents();
        this.title= boardRequestDto.getTitle();
        this.username=tokenUsername;
    }
    public void putBoard(BoardRequestDto boardRequestDto) {
        this.contents= boardRequestDto.getContents();
        this.title= boardRequestDto.getTitle();
    }
}
