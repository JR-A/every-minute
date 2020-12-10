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

	@Override
	public void insertReview(ReviewVO review, SubjectRateVO subjectRate) {
		//수강평 등록
		reviewMapper.insertReview(review);
		
		//해당 과목의 총 평점레코드 존재하는지 검사
		int cnt = reviewMapper.selectCountOfSubjectRateRecord(review);
		
		//첫 수강평 등록시
		if(cnt == 0) {
			//과목의 총평점 레코드 생성
			reviewMapper.insertNewSubjectRate(subjectRate);
		}else {//이후 수강평 등록시
			//과목의 총평점 레코드에 점수 반영
			reviewMapper.updateSubjectRate(subjectRate);
		}
	}

}
