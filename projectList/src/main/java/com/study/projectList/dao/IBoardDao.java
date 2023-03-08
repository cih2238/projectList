package com.study.projectList.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.study.projectList.vo.BoardVo;

@Mapper
public interface IBoardDao {
	public void InsertBoard(BoardVo Vo);
	public List<BoardVo> selectBoard();
	public BoardVo viewBoard(int no);
	public String countBoard();
}
