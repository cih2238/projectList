package com.study.projectList.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.projectList.dao.IBoardDao;
import com.study.projectList.vo.BoardVo;

@Service
public class BoardService{

	@Autowired
	IBoardDao boardDao;

	public void insertBoard(BoardVo Vo) {
		boardDao.InsertBoard(Vo);
	}
	public List<BoardVo> selectBoard(){
		return boardDao.selectBoard();
	}
	public BoardVo viewBoard(int no){
		return boardDao.viewBoard(no);
	}
	public String countBoard() {
		return boardDao.countBoard();
	}

}
