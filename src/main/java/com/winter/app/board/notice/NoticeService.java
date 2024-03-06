package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winter.app.board.BoardDAO;
import com.winter.app.board.BoardService;
import com.winter.app.board.BoardVO;
import com.winter.app.util.Pager;

@Service
public class NoticeService implements BoardService {
	
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Override
	public List<BoardVO> getList(Pager pager) throws Exception {
		pager.makeIndex();
		pager.makeNum(noticeDAO.getTotalCount(pager));
		
		return noticeDAO.getList(pager);
	}

	@Override
	public int add(BoardVO boardVO) throws Exception {
		int result = noticeDAO.add(boardVO);
		return result;
	}
	
	

}
