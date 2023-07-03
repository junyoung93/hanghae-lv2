package com.lv2.spartalv2hw.controller;

import com.lv2.spartalv2hw.dto.BoardRequestDto;
import com.lv2.spartalv2hw.dto.BoardResponseDto;
import com.lv2.spartalv2hw.dto.BoardResponseEntity;
import com.lv2.spartalv2hw.service.BoardService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
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

    @GetMapping("/{id}")
    public BoardResponseDto getBoard(@PathVariable Long id){
        return boardService.getBoard(id);
    }

    @PutMapping("/{id}")
    public BoardResponseDto putBoard(@PathVariable Long id,@RequestBody BoardRequestDto boardRequestDto, @CookieValue("Authorization")String tokenValue){
        return boardService.putBoard(id,boardRequestDto,tokenValue);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBoard(@PathVariable Long id,@CookieValue("Authorization")String tokenValue){
        try {
            boardService.deleteBoard(id,tokenValue);
        }catch (Exception e){
            return new ResponseEntity("게시글 작성자만 삭제할수 있습니다", HttpStatusCode.valueOf(202));
        }


        return new ResponseEntity( new BoardResponseEntity(), HttpStatusCode.valueOf(200));
    }
}
