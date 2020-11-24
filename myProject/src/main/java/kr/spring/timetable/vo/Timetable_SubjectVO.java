package kr.spring.timetable.vo;

public class Timetable_SubjectVO {
	private int ts_num;
	private int t_num;		//REFERENCES Timetable (t_num)
	private int sub_num;	//REFERENCES Subject (sub_num)
	
	//Getters and Setters
	public int getTs_num() {
		return ts_num;
	}
	public int getT_num() {
		return t_num;
	}
	public int getSub_num() {
		return sub_num;
	}
	public void setTs_num(int ts_num) {
		this.ts_num = ts_num;
	}
	public void setT_num(int t_num) {
		this.t_num = t_num;
	}
	public void setSub_num(int sub_num) {
		this.sub_num = sub_num;
	}
	
	@Override
	public String toString() {
		return "Timetable_SubjectVO [ts_num=" + ts_num + ", t_num=" + t_num + ", sub_num=" + sub_num + "]";
	}
	
}
