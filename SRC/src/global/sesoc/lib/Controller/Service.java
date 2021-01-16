/*작성자: 최하라, 박태원
작성환경: windows7 professional K,
	   JAVA jdk 8u202
	   Eclipse IDE for Java Developers Version 2019-03(4.110)
	   Launch 4j3.12
	   Oracle DateBase 11gExpress Edition
	   Oracle SQL developer 19.1.0.094
작성의도 :JDBC PROJECT
작성일자 2019/06/06*/

package global.sesoc.lib.Controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import global.sesoc.lib.View.OutList;
import global.sesoc.lib.model.Media;
import global.sesoc.lib.model.ServiceDao;
import global.sesoc.lib.model.ServiceDaoInterface;
import global.sesoc.lib.model.User;

public class Service {

	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM");
	OutList outMenu = new OutList();
	ServiceDaoInterface sDao = null;

	public Service() {
		this.sDao = new ServiceDao();
	}

	////////////////////////// 유저////////////////////////////////////
	// 유저 생성
	public boolean createUser(User user) { 
		Calendar today = Calendar.getInstance();
		Date date = today.getTime();
		String da = sdf1.format(date);
		user.setJoinDate(da);
		boolean flag = sDao.createUser(user);

		return flag;

	}
	// 아이디로 검색
	public User findUser(String userID) { 
		return sDao.findUser(userID);
	}
	 // 이메일로 검색
	public User findEmail(String email) {
		return sDao.findEmail(email);
	}
	 // 닉네임으로 검색
	public User findNickname(String nickname) {
		return sDao.findNickname(nickname);
	}
	 // 로그인
	public int login(String userId, String userPw) {
		int idx = -1;
		User user = this.findUser(userId);
		if (user != null) {
			if (user.getUserID().equals(userId)) {
				if (user.getUserPw().equals(userPw)) {
					if (user.getLevel().equals("0")) {
						idx = 3;

					} else {
						idx = 2;

					}
				} else {
					idx = 1;
				}
			} else {
				idx = 0;
			}
		} else {
			idx = -1;
		}

		return idx;
	}
	// 유저 정보 삭제
	public boolean deleteUser(User user) {
		return sDao.deleteUser(user);

	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 유저 정보 출력 
	public List<User> printUser() {
		List<User> user = sDao.loadUSER();
		return user;
	}
	// 유저 패스워드 수정 
	public boolean updatePassword(User user, String password) {
		return sDao.updatePassword(user, password);

	}
	// 유저 닉네임 수정
	public boolean updateNickname(User user, String nickname) {
		return sDao.updateNickname(user, nickname);

	}
	// 유저 email 수정 
	public boolean updateEmail(User user, String email) {
		return sDao.updateEmail(user, email);

	}
	// 유저의 등급 수정 
	public boolean updateUserLevel(User user, String userlevel) {
		@SuppressWarnings("deprecation")
		Date UserMembership = new Date(user.getEnd());
		Date now = new Date();
		switch (userlevel) {
		case "4":
			user.setGold(user.getGold() + 1);
			if (user.getLevel().equals("4")) {
				if (UserMembership.before(now)) {
					this.changeStartDay(user, now);
					this.changeEndDay(user, now);
				} else {
					this.changeEndDay(user, UserMembership);
				}
			} else {
				this.changeStartDay(user, now);
				this.changeEndDay(user, now);

			}
			break;
		case "3":
			user.setSilver(user.getSilver() + 1);
			if (user.getLevel().equals("3")) {
				if (UserMembership.before(now)) {
					this.changeStartDay(user, now);
					this.changeEndDay(user, now);
				} else {
					this.changeEndDay(user, UserMembership);
				}
			} else {
				this.changeStartDay(user, now);
				this.changeEndDay(user, now);
			}

			break;
		case "2":
			user.setBronze(user.getBronze() + 1);
			if (user.getLevel().equals("2")) {
				if (UserMembership.before(now)) {
					this.changeStartDay(user, now);
					this.changeEndDay(user, now);
				} else {
					this.changeEndDay(user, UserMembership);
				}
			} else {
				this.changeStartDay(user, now);
				this.changeEndDay(user, now);
			}
			break;
		}

		return sDao.updateUserLevel(user, userlevel);
	}
	//유저의 등급을 언어로 변경 
	public String selectLevel(String level) {
		String slevel = null;
		switch (level) {
		case "0":
			slevel = outMenu.administrator();
			break;
		case "1":
			slevel = outMenu.general();
			break;
		case "2":
			slevel = outMenu.bronze();
			break;
		case "3":
			slevel = outMenu.silver();
			break;
		case "4":
			slevel = outMenu.gold();
			break;
		default:
			break;
		}
		return slevel;
	}
	// 멤버쉽 시작일 수정
	public void changeStartDay(User user, Date date) {
		String dateString = sdf1.format(date);
		user.setStart(dateString);
	}
	// 멤버쉽 종료일 수정 
	@SuppressWarnings("deprecation")
	public void changeEndDay(User user, Date date) {
		date.setMonth(date.getMonth() + 1);
		String dateString = sdf1.format(date);
		user.setEnd(dateString);

	}
	// 멤버쉽 변경
	public boolean joinMembership(User user) {
		user.setLevel("1");
		return sDao.joinMembership(user);
	}
	/////////////// 미디어 관련 ///////////////////////////////////
	// 전체 미디어 출력
	public List<Media> printMedia() {
		List<Media> media = sDao.loadMEDIA();
		return media;
	}
	// 전체 미디어를 조회순의 내림차순으로 출력 
	public List<Media> printMediaAudience() {
		List<Media> media = sDao.printMediaAudience();
		return media;
	}
	// 새로운 미디어 생성 
	public boolean createMedia(Media media) {
		return sDao.createMedia(media);

	}
	// 시청연령 의 글자화 
	public String checkRating(String rate) {
		String rating = null;
		switch (rate) {
		case "1":
			rating = outMenu.AvailableAll();
			break;
		case "2":
			rating = outMenu.Available7();
			break;
		case "3":
			rating = outMenu.Available12();
			break;
		case "4":
			rating = outMenu.Available15();
			break;
		case "5":
			rating = outMenu.Available19();
			break;
		default:

			break;
		}

		return rating;
	}
	// 번호를 통한 미디어 검색 ( 시청 하기 / 번호로 검색) 에서 사용 
	public Media findMediaWithOrder(int no, List<Media> media) {
		List<Media> mList = printMedia();
		try {
			if (media.size() > 0) {
				if (no >= 0 && no < mList.size()) {
					return media.get(no);
				} else {
					return null;
				}
			} else {
				return null;
			}
		} catch (Exception e) {

		}
		return null;

	}
	// 이름을 이용한 미디어 검색 
	public List<Media> findMediaWithName(String name) {
		List<Media> m = sDao.findMediaWithName(name);
		return m;
	}
	// 카테고리를 이용한 미디어 검색 
	public List<Media> findMediaWithCategory(String category) {
		List<Media> m = sDao.findMediaWithCategory(category);
		return m;
	}
	// 장르를 이용한 미디어 검색
	public List<Media> findMediaWithGenre(String genre) {
		List<Media> m = sDao.findMediaWithGenre(genre);
		return m;
	}
	// 시청연령을 이용한  미디어 검색 
	public List<Media> findMediaWithRating(String rating) {
		List<Media> m = sDao.findMediaWithRating(rating);
		return m;
	}
	// 국가정보를 이용한 미디어검색
	public List<Media> findMediaWithNation(String nation) {
		List<Media> m = sDao.findMediaWithNation(nation);
		return m;
	}
	// 미디어 정보 삭제 
	public boolean deleteMedia(Media m) {
		return sDao.deleteMedia(m);

	}
	// 날짜를 String 타입으로  변경
	public String changeDate(Calendar cal) {
		Date date = cal.getTime();
		String d = sdf2.format(date);
		return d;
	}
	// 아이디를 이용한 미디어 변경 
	public Media findMediaWithID(int no) {
		return sDao.findMediaWithID(no);
	}
	// 미디어 정보 수정 
	public void updateMedia(Media media) { // view에서 list 업데이트하고 서비스에서는 db만 업데이트
											// 하는 방법
		sDao.updateMedia(media);
	}
	//user가 시청한 미디어 목록
	public List<Media> myMediaList(String loginId) {
		List<Media> mList = sDao.MyMediaList(loginId);
		return mList;
	}
	// 미디어 등급에 따른 미디어 시청 구현 및 미디어 조회 수 및 시청 목록 업데이트
	@SuppressWarnings("deprecation")
	public boolean watchMedia(String loginId, Media media) {

		boolean flag = false;

		Date releasedDate = new Date(media.getDate() + "/01");
		Date now = new Date();
		Date aYearAgo = new Date();
		aYearAgo.setYear(now.getYear() - 1);
		Date halfYearAgo = new Date();
		halfYearAgo.setMonth(now.getMonth() - 6);

		media.setAudience(media.getAudience() + 1);

		User user = this.findUser(loginId);
		
		switch (user.getLevel()) {
		case "1" : flag = false; break;
		case "2" : if (releasedDate.before(aYearAgo)) flag = true; break;
		case "3" : if (releasedDate.before(halfYearAgo)) flag = true;
		case "4" : flag = true; break;
		default : break;
		}
		
		if(flag) {
			sDao.watchMedia(loginId, media);
			sDao.updateMediaAudience(media);
		}
		
		return flag;
	}
}
