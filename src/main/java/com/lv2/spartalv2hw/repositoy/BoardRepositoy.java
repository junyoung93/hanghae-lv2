package com.lv2.spartalv2hw.repositoy;


import com.lv2.spartalv2hw.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepositoy extends JpaRepository<Board,Long> {
}
