/*작성자: 최하라, 박태원
작성환경: windows7 professional K,
	   JAVA jdk 8u202
	   Eclipse IDE for Java Developers Version 2019-03(4.110)
	   Launch 4j3.12
	   Oracle DateBase 11gExpress Edition
	   Oracle SQL developer 19.1.0.094
작성의도 :JDBC PROJECT
작성일자 2019/06/06*/
package global.sesoc.lib.View;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import global.sesoc.lib.Controller.Service;
import global.sesoc.lib.model.Media;
import global.sesoc.lib.model.User;

public class ViewForUser {
	private String loginId;
	private Scanner sc;
	private OutList outMenu;
	private Service service;
	private ViewForAdmin admin;
	private MainView mv =new MainView();

	@SuppressWarnings("deprecation")
	public ViewForUser(String userId, Service service) { //default constructor
		sc = new Scanner(System.in);
		this.service = service;
		this.loginId = userId;
		this.outMenu = new OutList();
		this.admin = new ViewForAdmin(userId, service);

		User user = service.findUser(loginId);
		Date UserplanEnd = new Date(user.getEnd());
		Date now = new Date();
		Date oneWeakago = new Date(user.getEnd());
		oneWeakago.setDate(now.getDate() - 7);
		String slevel = service.selectLevel(user.getLevel());
		if (!(user.getLevel().equals("1"))) {
			if (now.before(UserplanEnd) && now.after(oneWeakago)) {
				outMenu.endmembership01(slevel,user);

				String select = null;
				boolean flag = true;
				while (flag) {
					try {
						outMenu.select();
						select = sc.nextLine();
						if (select.equals("1") || select.equals("0")) {
							if (select.equals("1")) {
								this.joinMembership();
								this.menuforUser();
							}
							if (select.equals("0")) {
								this.menuforUser();
							}
							flag = false;
						} else {
							throw new Exception();
						}
					} catch (Exception e) {

					}
				}

				
			} else if (now.after(UserplanEnd)) {
				outMenu.endmembership02(slevel, user);
				service.joinMembership(user);
				this.menuforUser();
			} else {
				this.menuforUser();
			}
		} else {
			this.menuforUser();
		}

	}
	//유저 메인메뉴
	public void menuforUser() { 
		boolean flag = true;
		while (flag) {
			User nowUser = service.findUser(loginId);
			outMenu.mainMenuForUser(nowUser.getNickname());
			String select = sc.nextLine();
			if (select.equals("0")) {
				return;
			}
			switch (select) {
			case "1":
				flag = this.yesPlease();
				break;
			case "2":
				this.noThanks();
				flag = false;
			}
		}
	}
	
	//유저 기분에 맞춘 영상 추천
	public boolean yesPlease() {
		boolean flag = true;
		User nowUser = service.findUser(loginId);
		outMenu.userEmotion();
		String select = sc.nextLine();
		Media m = null;
		switch (select) {
		case "1":
			outMenu.happyUser(nowUser.getNickname());
			List<Media> romance = service.findMediaWithGenre("ROMANCE");
			System.out.println();
			admin.mediaShow(romance);
			if (romance.size() > 0) {
				m = admin.selectByOrder(romance);
			}
			break;
		case "2":
			outMenu.sadUser(nowUser.getNickname());
			List<Media> meloDrama = service.findMediaWithGenre("MELODRAMA");
			System.out.println();
			admin.mediaShow(meloDrama);
			if (meloDrama.size() > 0) {
				m = admin.selectByOrder(meloDrama);
			}
			break;

		case "3":
			outMenu.stressfulUser(nowUser.getNickname());
			List<Media> action = service.findMediaWithGenre("ACTION");
			System.out.println();
			admin.mediaShow(action);
			if (action.size() > 0) {
				m = admin.selectByOrder(action);
			}
			break;

		default:
			break;
		}
		if (m != null) {
			return this.watchMedia(m);
		}
		return flag;
	}
	
	//영상 시청
	public boolean watchMedia(Media media) {
		boolean flag = true;
		outMenu.showMedia();
		outMenu.outLine();
		String select = null;
		while (flag) {
			try {
				outMenu.select();
				select = sc.nextLine();
				if (select.equals("1") || select.equals("0")) {
					flag = false;
				} else {
					throw new Exception();
				}
			} catch (Exception e) {

			}
		}
		if (select.equals("0")) {
			this.noThanks();
			flag = false;
		} else {
			boolean flag1 = service.watchMedia(loginId, media);
				if(flag1) {outMenu.watchMedia();
				}else {
					outMenu.noAccess();
					this.noThanks();
				}
			flag = true;
		}
		return flag;
	};
	
	//유저 일반 메뉴
	public void noThanks() {
		while (true) {
			User nowUser = service.findUser(loginId);
			outMenu.menuforUser(nowUser.getNickname());
			String select = sc.nextLine();
			switch (select) {
			case "1":
				this.printAllMedia();
				break;
			case "2":
				this.joinMembership();
				break;
			case "3":
				this.myPage();
				break;
			case "0":
				mv.start();
			default:
				break;
			}
		}
	}
	
	//전체 영상 출력
	public void printAllMedia() {
		try{
			while (true) {
				outMenu.printMedia();
				outMenu.outLine();
				System.out.println();
				List<Media> media = service.printMedia();
				admin.mediaShow(media);
				System.out.println();
				outMenu.watchMediaMenu();
				String select = sc.nextLine();
				if (select.equals("0"))
					return;
				switch (select) {
				case "1":
					Media m = admin.selectByOrder(media);
					if (m != null) {
						this.watchMedia(m);
					}
					break;
				case "2":
					this.searchByMenu();
					break;
				case "3":
					List<Media> mymedia = this.myMediaList();
					if (mymedia.size() > 0) {
						m = admin.selectByOrder(mymedia);
						if (m != null) {
							this.watchMedia(m);
						}
					}
					break;
				default:
					break;
				}		
		}
		}catch(Exception e ){
			
		}

	}
	
	//분류별 영상검색
	public void searchByMenu() {
		try{
			outMenu.searchByMenu();
			String select = sc.nextLine();
			if (select.equals("0"))
				return;
			List<Media> list = null;
			switch (select) {
			case "1":
				list = admin.selectByGenre();
				break;
			case "2":
				list = admin.selectByName();
				break;
			case "3":
				list = admin.selectByNation();
				break;
			case "4":
				list = admin.selectByAudience();
				break;
			default:
				break;
			}
			if (list.size() > 0) {
				Media m = admin.selectByOrder(list);
				if (m != null) {
					this.watchMedia(m);
				}
			}
		}catch(Exception e ){
			
		}

	}
	//멤버십 등록 및 갱신
	public void joinMembership() {
		boolean flag = true;
		String select = null;
		while (flag) {
			try {
				outMenu.joinMembership();
				select = sc.nextLine();
				if (select.equals("1") || select.equals("2") || select.equals("3")) {
					flag = false;
				} else {
					throw new Exception();
				}
			} catch (Exception e) {

			}
		}
		String grade = null;
		String number = null;
		switch (select) {
		case "1":
			grade = "GOLD";
			number = "4";
			break;

		case "2":
			grade = "SLIVER";
			number = "3";
			break;
		case "3":
			grade = "BRONZE";
			number = "2";
			break;
		default:
			break;
		}
		User nowUser = service.findUser(loginId);
		String slevel = service.selectLevel(nowUser.getLevel());
		String nickname = nowUser.getNickname();
		outMenu.realJoinMembership(grade, slevel, nickname);
		outMenu.updateMembership();
		String select2 = sc.nextLine();
		switch (select2) {
		case "1":
			this.updateMembership(nowUser, number);
			break;
		case "2":
			this.noThanks();
		default:
			break;
		}
	}
	
	//멤버십 등록 성공 및 실패 출력
	public void updateMembership(User nowUser, String number) {
		boolean flag = service.updateUserLevel(nowUser, number);
		if (flag) {
			outMenu.membershipSuccess();

		} else {
			outMenu.membershipFail();
		}
	}
	
	//마이페이지
	public void myPage() {
		
		while(true){
			User user = service.findUser(loginId);
			if (user != null) {
				String slevel = service.selectLevel(user.getLevel());
				outMenu.printUser();
				outMenu.userShow(slevel, user);
				outMenu.outLine();
				outMenu.myPage();
				String select = sc.nextLine();
				this.updateMyPage(select);
				if(select.equals("3")){
				return;
				}

			} else {
				outMenu.outLine();
				System.out.println();
				outMenu.notFoundUser();
			}
		}
	}
	
	//회원정보 수정 및 회원탈퇴
	private void updateMyPage(String select) {
	
		switch (select) {
		case "1":
			this.updateUser();
			break;
		case "2":
			this.byebye();
			break;
		default:
			break;
		}
	}
	
	//회원 수정
	private void updateUser() {
		try {
			User user = service.findUser(loginId);
			if (user == null) {
				return;
			}
			outMenu.updateMyPage();
			String select = sc.nextLine();
			System.out.println();
			if (select.equals("0")) {
				return;
			}
			this.updateUserSwitch(select, user);

		} catch (Exception e) {
			outMenu.wrongInsert();
		}

	}
	//회원 수정 - 암호, 닉네임, 이메일
	private void updateUserSwitch(String select, User user) {
		switch (select) {
		case "1":
			admin.changePassword(user);
			break;
		case "2":
			admin.changeNickname(user);
			break;
		case "3":
			admin.changeEmail(user);
			break;
		default:
			break;
		}
	}
	
	//회원탈퇴
	private void byebye() {
		User user = service.findUser(loginId);

		outMenu.identfyDelete();
		String YN = sc.nextLine().toUpperCase();
		if (YN.equals("Y")) {
			boolean result = service.deleteUser(user);
			if (result) {
				System.out.println();
				outMenu.deleteSuccess();
				System.exit(-1);

			} else {
				System.out.println();
				outMenu.cancel();
				return;
			}
		}

	}
	
	//내가 시청한 영상 목록
	List<Media> myMediaList() {
		List<Media> mList = service.myMediaList(loginId);
		outMenu.printMedia();
		outMenu.outLine();
		System.out.println();
		admin.mediaShow(mList);
		System.out.println();
		return mList;
	}
}
