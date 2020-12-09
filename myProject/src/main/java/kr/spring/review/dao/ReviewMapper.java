package kr.spring.review.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import kr.spring.review.vo.ReviewVO;
import kr.spring.review.vo.SubjectRateVO;

public interface ReviewMapper {
	//전체 수강평 목록(최신글 상위)
	@Select("SELECT * FROM Review JOIN Subject USING(sub_num) ORDER BY post_num DESC")
	public List<ReviewVO> selectList();
	
	//특정 과목의 수강평 목록(최신글 상위)
	@Select("SELECT * FROM Review WHERE sub_num=#{sub_num} ORDER BY post_num DESC")
	public List<ReviewVO> selectListBySubnum(int sub_num);
	
	//특정 과목의 평가 가져오기
	@Select("SELECT * FROM SubjectRate WHERE sub_num=#{sub_num}")
	public SubjectRateVO selectSubjectRate(int sub_num);
}
