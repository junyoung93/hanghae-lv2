package com.lv2.spartalv2hw.repository;


import com.lv2.spartalv2hw.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepositoy extends JpaRepository<Board,Long> {
    List<Board> findAllByOrderByCreatedAtDesc();
}
