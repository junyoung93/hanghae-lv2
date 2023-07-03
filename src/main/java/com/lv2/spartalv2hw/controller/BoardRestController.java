package com.lv2.spartalv2hw.controller;

import com.lv2.spartalv2hw.dto.BoardRequestDto;
import com.lv2.spartalv2hw.dto.BoardResponseDto;
import com.lv2.spartalv2hw.service.BoardService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("")
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto boardRequestDto, @CookieValue("Authorization")String tokenValue){
        return boardService.createBoard(boardRequestDto,tokenValue);
    }

}
