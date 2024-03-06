package com.winter.app.board;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.winter.app.util.Pager;

public interface BoardDAO {
	
	public Long getTotalCount(Pager pager)throws Exception;
	
	public List<BoardVO> getList(Pager pager)throws Exception;
	
	public int add(BoardVO boardVO)throws Exception;
	
	public int addFile(FileVO fileVO)throws Exception;
	

}
