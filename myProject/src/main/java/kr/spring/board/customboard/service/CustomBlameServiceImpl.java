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
		public int blamePostCount_user(Map<String, Object> map) {
			return customBlameMapper.blamePostCount_user(map);
		}

		@Override
		public void insertPostBlame(CustomBlameVO customBlameVO) {
			customBlameMapper.insertPostBlame(customBlameVO);
		}

		@Override
		public int blameCommCount_user(Map<String, Object> map) {
			return customBlameMapper.blameCommCount_user(map);
		}

		@Override
		public void insertCommBlame(CustomBlameVO customBlameVO) {
			customBlameMapper.insertCommBlame(customBlameVO);
		}

		
		
		
}
