package kr.spring.review.service;

import java.util.List;

import kr.spring.review.vo.ReviewVO;
import kr.spring.review.vo.SubjectRateVO;

public interface ReviewService {
	public List<ReviewVO> selectList();
	public List<ReviewVO> selectListBySubnum(int sub_num);
	public SubjectRateVO selectSubjectRate(int sub_num);

}
