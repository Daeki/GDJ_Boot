package com.winter.app.board;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.winter.app.util.Pager;

public interface BoardService {
	
	public List<BoardVO> getList(Pager pager)throws Exception;
	
	public int add(BoardVO boardVO, MultipartFile [] attach)throws Exception;
	
	public BoardVO getDetail(BoardVO boardVO)throws Exception;

	public FileVO getFileDetail(FileVO fileVO)throws Exception;
}
