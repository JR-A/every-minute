package kr.spring.review.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.review.dao.ReviewMapper;
import kr.spring.review.vo.ReviewVO;
import kr.spring.review.vo.SubjectRateVO;

@Service("reviewService")
public class ReviewServiceImpl implements ReviewService {
	
	//프로퍼티
	@Resource
	private ReviewMapper reviewMapper;
	
	@Override
	public List<ReviewVO> selectList() {
		return reviewMapper.selectList();
	}

	@Override
	public List<ReviewVO> selectListBySubnum(int sub_num){
		return reviewMapper.selectListBySubnum(sub_num);
	}

	@Override
	public SubjectRateVO selectSubjectRate(int sub_num) {
		return reviewMapper.selectSubjectRate(sub_num);
	}

}
