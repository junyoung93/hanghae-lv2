package com.lv2.spartalv2hw.service;

import com.lv2.spartalv2hw.dto.BoardRequestDto;
import com.lv2.spartalv2hw.dto.BoardResponseDto;
import com.lv2.spartalv2hw.entity.Board;
import com.lv2.spartalv2hw.jwt.JwtUtil;
import com.lv2.spartalv2hw.repositoy.BoardRepositoy;
import io.jsonwebtoken.Claims;
import jakarta.transaction.Transactional;
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

    public BoardResponseDto getBoard(Long id) {
        return new BoardResponseDto(boardRepositoy.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다.")));
    }

    @Transactional
    public BoardResponseDto putBoard(Long id, BoardRequestDto boardRequestDto, String tokenValue) {
        Board board = boardRepositoy.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다."));
        if (!tokenUsername(tokenValue).equals(board.getUsername())) {
            throw new IllegalArgumentException("이글은 작성자만 수정할 수 있습니다.");
        }
        board.putBoard(boardRequestDto);

        return new BoardResponseDto(board);
    }

    public void deleteBoard(Long id, String tokenValue) {
        Board board = boardRepositoy.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다."));
        if (!tokenUsername(tokenValue).equals(board.getUsername())) {
            throw new IllegalArgumentException("이글은 작성자만 삭제할 수 있습니다.");
        }

        boardRepositoy.delete(board);
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
