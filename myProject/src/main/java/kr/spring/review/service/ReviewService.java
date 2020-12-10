package kr.spring.review.service;

import java.util.List;

import kr.spring.review.vo.ReviewVO;
import kr.spring.review.vo.SubjectRateVO;
import kr.spring.timetable.vo.SubjectVO;

public interface ReviewService {
	public List<ReviewVO> selectList();
	public List<SubjectRateVO> searchedSubjectsList(String keyword);
	public List<ReviewVO> selectListBySubnum(int sub_num);
	public SubjectRateVO selectSubjectRate(int sub_num);

	public void insertReview(ReviewVO review, SubjectRateVO subjectRate);

}
