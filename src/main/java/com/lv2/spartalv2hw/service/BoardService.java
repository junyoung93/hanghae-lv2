package com.lv2.spartalv2hw.service;

import com.lv2.spartalv2hw.dto.BoardRequestDto;
import com.lv2.spartalv2hw.dto.BoardResponseDto;
import com.lv2.spartalv2hw.entity.Board;
import com.lv2.spartalv2hw.jwt.JwtUtil;
import com.lv2.spartalv2hw.repositoy.BoardRepositoy;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepositoy boardRepositoy;
    private final JwtUtil jwtUtil;


    public BoardService(BoardRepositoy boardRepositoy, JwtUtil jwtUtil) {
        this.boardRepositoy = boardRepositoy;
        this.jwtUtil = jwtUtil;
    }

    public List<BoardResponseDto> getBoardList() {
        return boardRepositoy.findAllByOrderByCreatedAtDesc().stream().map(BoardResponseDto::new).toList();
    }

    public BoardResponseDto createBoard(BoardRequestDto boardRequestDto, String tokenValue) {
        Board board = new Board(boardRequestDto,tokenUsername(tokenValue));
        return new BoardResponseDto(boardRepositoy.save(board));
    }


    private String tokenUsername(String tokenValue){
        String token = jwtUtil.substringToken(tokenValue);

        try {
            jwtUtil.validateToken(token);
        }catch (Exception e){
            throw new IllegalArgumentException("Token Error");
        }
        Claims claims = jwtUtil.getUserInfoFromToken(token);
        return claims.getSubject();
    }
}
