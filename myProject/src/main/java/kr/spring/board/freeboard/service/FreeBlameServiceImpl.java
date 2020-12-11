package kr.spring.board.freeboard.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.freeboard.dao.FreeBlameMapper;
import kr.spring.board.freeboard.vo.FreeBlameVO;

@Service("freeBlameService")
public class FreeBlameServiceImpl implements FreeBlameService{

	@Resource
	FreeBlameMapper freeBlameMapper;
	
	@Override
	public int blameCount_user(Map<String, Object> map) {

		return freeBlameMapper.blameCount_user(map);
	}

	@Override
	public void insertPostBlame(FreeBlameVO freeBlameVO) {
		
		freeBlameMapper.insertBlame(freeBlameVO);
		
	}

	@Override
	public void insertCommentBlame(FreeBlameVO freeBlameVO) {
		
		freeBlameMapper.insertCommBlame(freeBlameVO);
		
	}

	@Override
	public int blameCommCount_user(Map<String, Object> map) {

		return freeBlameMapper.blameCommCount_user(map);
	}

}
