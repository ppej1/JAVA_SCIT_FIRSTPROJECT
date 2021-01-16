/*작성자: 최하라, 박태원
작성환경: windows7 professional K,
	   JAVA jdk 8u202
	   Eclipse IDE for Java Developers Version 2019-03(4.110)
	   Launch 4j3.12
	   Oracle DateBase 11gExpress Edition
	   Oracle SQL developer 19.1.0.094
작성의도 :JDBC PROJECT
작성일자 2019/06/06*/
package global.sesoc.lib.model;



public class User {
	private String userID;// user ID
	private String userPw;// User Password
	private String nickname;// nickname
	private String email;// email
	private String level;// 등급
	private String joinDate; //날짜 
	private String start;  //멤버쉽  시작일
	private String end;	// 멤버쉽 종료일
	private int gold; //골드등급
	private int silver; // 실버등급
	private int bronze; //브론즈 등급 
	
	//브론즈 골드 실버 결제 여부 	


	public User(String userID, String userPw, String nickname, String email, String level, String joinDate, String start,
			String end, int gold, int silver, int bronze) {
		this.userID = userID;
		this.userPw = userPw;
		this.nickname = nickname;
		this.email = email;
		this.level = level;
		this.joinDate = joinDate;
		this.start = start;
		this.end = end;
		this.gold = gold;
		this.silver = silver;
		this.bronze = bronze;
	}



	public String getEnd() {
		return end;
	}




	public void setEnd(String end) {
		this.end = end;
	}

	public String getJoinDate() {
		return joinDate;
	}




	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}




	public String getStart() {
		return start;
	}




	public void setStart(String start) {
		this.start = start;
	}




	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	
	public String getNickname() {
		return nickname;
	}



	public void setNickname(String nickname) {
		this.nickname = nickname;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	public int getGold() {
		return gold;
	}


	public void setGold(int gold) {
		this.gold = gold;
	}


	public int getSilver() {
		return silver;
	}


	public void setSilver(int silver) {
		this.silver = silver;
	}


	public int getBronze() {
		return bronze;
	}


	public void setBronze(int bronze) {
		this.bronze = bronze;
	}
}
