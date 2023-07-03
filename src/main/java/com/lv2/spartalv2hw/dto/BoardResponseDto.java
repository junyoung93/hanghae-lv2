package com.lv2.spartalv2hw.dto;

import com.lv2.spartalv2hw.entity.Board;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponseDto {

    private Long id;
    private String title;
    private String contents;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.username = board.getUsername();
        this.createdAt =board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
    }
}
