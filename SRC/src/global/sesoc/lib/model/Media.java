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



public class Media {
	private int mediaId;
	private String mediaName;//미디어 이름 
	private String nation;	//국가
	private String category;//카테고리 - 드라마 / 영화 / 애니 / 예능
	private String genre;	//장르   - 코믹 / 드라마 / 로맨스 / 액션 
	private String date;	// 출시일
	private String rating;//등급
	private int audience;  //조회수 
	
	public Media(String mediaName, String nation, String category, String genre, String rating) {
		super();
		this.mediaName = mediaName;
		this.nation = nation;
		this.category = category;
		this.genre = genre;
		this.rating = rating;
		this.audience = 0;

	}
	public int getMediaId() {
		return mediaId;
	}

	public void setMediaId(int mediaId) {
		this.mediaId = mediaId;
	}
	public String getMediaName() {
		return mediaName;
	}

	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public int getAudience() {
		return audience;
	}

	public void setAudience(int audience) {
		this.audience = audience;
	}


	@Override
	public String toString() {
		return String.format(
				"[%d]   %-9s%-22s%-9s%-11s%-5s%-10s%-3d",
				mediaId,nation, mediaName, category, genre, rating, date, audience);
	}

	//	System.out.println("[no]국가\t 제목\t 카테고리\t 장르\t 등급\t 출시일\t 시청수 \t 평점");

	
	
}
