package kr.spring.board.infoboard.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.infoboard.dao.InfoBlameMapper;
import kr.spring.board.infoboard.vo.InfoBlameVO;

@Service("infoBlameService")
public class InfoBlameServiceImpl implements InfoBlameService{

	@Resource
	InfoBlameMapper infoBlameMapper;
	
	@Override
	public int blameCount_user(Map<String, Object> map) {
		return infoBlameMapper.blameCommCount_user(map);
	}

	@Override
	public int blameCommCount_user(Map<String, Object> map) {
		return infoBlameMapper.blameCommCount_user(map);
	}

	@Override
	public void insertCommentBlame(InfoBlameVO infoBlameVO) {
		infoBlameMapper.insertCommBlame(infoBlameVO);
	}
	
}
