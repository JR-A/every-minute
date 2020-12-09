package kr.spring.board.customboard.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.customboard.dao.CustomBlameMapper;
import kr.spring.board.customboard.vo.CustomBlameVO;

@Service("customBlameService")
public class CustomBlameServiceImpl implements CustomBlameService{

		@Resource
		CustomBlameMapper customBlameMapper;

		@Override
		public int blameCount_user(Map<String, Object> map) {
			return customBlameMapper.blameCount_user(map);
		}

		@Override
		public void insertPostBlame(CustomBlameVO customBlameVO) {
			customBlameMapper.insertBlame(customBlameVO);
		}

		@Override
		public void insertCommentBlame(CustomBlameVO customBlameVO) {
			customBlameMapper.insertCommentBlame(customBlameVO);
		}
		
}
