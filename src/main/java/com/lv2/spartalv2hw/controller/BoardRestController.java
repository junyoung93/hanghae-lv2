package com.lv2.spartalv2hw.controller;

import com.lv2.spartalv2hw.dto.BoardResponseDto;
import com.lv2.spartalv2hw.service.BoardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/board")
public class BoardRestController {

    private final BoardService boardService;

    public BoardRestController(BoardService boardService) {
        this.boardService = boardService;
    }


    @GetMapping("")
    public List<BoardResponseDto> getBoardList(){
        return boardService.getBoardList();
    }
}
