package com.lv2.spartalv2hw.service;

import com.lv2.spartalv2hw.dto.BoardResponseDto;
import com.lv2.spartalv2hw.jwt.JwtUtil;
import com.lv2.spartalv2hw.repositoy.BoardRepositoy;
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
}
