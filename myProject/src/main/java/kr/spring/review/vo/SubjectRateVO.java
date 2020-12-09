package kr.spring.review.vo;

//과목의 총평점
public class SubjectRateVO {
	private int sub_num;	//외래키이면서 기본키 역할
	private int totalCount; //평가 수
	
	private int totalRate;	//총 점수
	//각 항목의 평가점수를 누적
	private int homework;	//과제
	private int team;		//조 모임
	private int grade;		//학점비율
	private int attendance;	//출결
	private int exam;		//시험
	
	public int getSub_num() {
		return sub_num;
	}
	public void setSub_num(int sub_num) {
		this.sub_num = sub_num;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalRate() {
		return totalRate;
	}
	public void setTotalRate(int totalRate) {
		this.totalRate = totalRate;
	}
	public int getHomework() {
		return homework;
	}
	public void setHomework(int homework) {
		this.homework = homework;
	}
	public int getTeam() {
		return team;
	}
	public void setTeam(int team) {
		this.team = team;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getAttendance() {
		return attendance;
	}
	public void setAttendance(int attendance) {
		this.attendance = attendance;
	}
	public int getExam() {
		return exam;
	}
	public void setExam(int exam) {
		this.exam = exam;
	}
	
	@Override
	public String toString() {
		return "SubjectRateVO [sub_num=" + sub_num + ", totalCount=" + totalCount + ", totalRate=" + totalRate
				+ ", homework=" + homework + ", team=" + team + ", grade=" + grade + ", attendance=" + attendance
				+ ", exam=" + exam + "]";
	}
	
}
