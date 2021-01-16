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

import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import global.sesoc.lib.Controller.Service;
import global.sesoc.lib.model.Media;
import global.sesoc.lib.model.User;

public class ViewForAdmin {
	private String loginId;
	private Scanner sc;
	private OutList outMenu;
	private Service service;
	private MainView mv = new MainView();

	public ViewForAdmin(String userId, Service service) { // default constructor
		sc = new Scanner(System.in);
		this.service = service;
		outMenu = new OutList();
		this.loginId = userId;
	}

	///////////////// 관리자메인메뉴///////////////////
	public void menuforAdmin() {
		try {
			while (true) {
				User nowUser = service.findUser(loginId);
				outMenu.outLine();
				outMenu.menuforAdmin(nowUser.getNickname());
				String select = sc.nextLine();

				if (select.equals("0")) {
					loginId = null;
					mv.start();
				}
				this.selectMenu(select);

			}
		} catch (Exception e) {
			outMenu.wrongInsert();
		}
	}

	private void selectMenu(String select) {
		switch (select) {
		case "1":
			printUserList();
			break;
		case "2":
			this.salesReport();
			break;
		case "3":
			this.mediaMenu();
			break;
		default:
			System.out.println("2");
			outMenu.wrongInsert();
			break;
		}

	}

	///////////// 유저목록 //////////////
	private void printUserList() {
		while (true) {
			outMenu.printUser();
			System.out.println();
			List<User> user = service.printUser();
			int cntG = 0, cntS = 0, cntB = 0, cntC = 0;
			if (user != null) {
				for (User u : user) {
					String slevel = service.selectLevel(u.getLevel());
					outMenu.userShow(slevel, u);
					switch (u.getLevel()) {
					case "1":
						cntC++;
						break;
					case "2":
						cntB++;
						break;
					case "3":
						cntS++;
						break;
					case "4":
						cntG++;
						break;
					}
				}
				int cnt = cntG + cntS + cntB + cntC;
				System.out.println();
				outMenu.outLine();
				System.out.println();
				System.out.printf(
						"[Gold ] %5d/%d [%.2s%%]\n[Silver]%5d/%d [%.2s%%]\n[Bronze]%5d/%d [%.2s%%]\n[일반]   %5d/%d [%.2s%%]\n ",
						cntG, cnt, (cntG / (double) cnt) * 100, cntS, cnt, (cntS / (double) cnt) * 100, cntB, cnt,
						(cntB / (double) cnt) * 100, cntC, cnt, (cntC / (double) cnt) * 100);
				System.out.println();
				outMenu.outLine();
			} else {
				outMenu.noUsedUser();
				System.out.println();
				outMenu.outLine();
			}
			outMenu.userSelectMenu();

			String select = sc.nextLine();
			System.out.println();
			if (select.equals("0")) {
				return;
			}
			this.SwitchUserMenu(select);
		}

	}

	private void SwitchUserMenu(String select) {
		switch (select) {
		case "1":
			selectUser();
			break;
		case "2":
			updateUser();
			break;
		case "3":
			deleteUser();
			break;

		default:
			outMenu.wrongInsert();
			break;
		}
	}

	///////////////// 유저 검색 //////////////////////////
	private void selectUser() {
		outMenu.printSelectUser();
		String select = sc.nextLine();
		if (select.equals("0")) {
			return;
		}
		SelectUserSwich(select);

	}

	private void SelectUserSwich(String select) {
		switch (select) {
		case "1":
			selectWithId();
			break;
		case "2":
			selectWithNickname();
			break;
		case "3":
			selectWithEmail();
			break;
		default:
			outMenu.wrongInsert();
			break;
		}
	}

	// email로 검색
	private void selectWithEmail() {
		try {
			outMenu.outLine();
			outMenu.email();
			String email = sc.next();
			sc.nextLine();
			User user = service.findEmail(email);
			if (user != null) {
				String slevel = service.selectLevel(user.getLevel());
				outMenu.printUser();
				outMenu.userShow(slevel, user);
				outMenu.outLine();

			} else {
				outMenu.outLine();
				System.out.println();
				outMenu.notFoundUser();
				return;
			}
		} catch (Exception e) {
			outMenu.outLine();
			System.out.println();
			outMenu.notFoundUser();
			return;
		}

	}

	// 닉네임으로 검색
	private void selectWithNickname() {
		try {
			outMenu.outLine();
			outMenu.nickname();
			String nickname = sc.nextLine();
			User user = service.findNickname(nickname);
			if (user != null) {
				String slevel = service.selectLevel(user.getLevel());
				outMenu.printUser();
				outMenu.userShow(slevel, user);
				outMenu.outLine();

			} else {
				outMenu.outLine();
				System.out.println();
				outMenu.notFoundUser();
				return;

			}
		} catch (Exception e) {
			outMenu.outLine();
			System.out.println();
			outMenu.notFoundUser();
		}

	}

	// 아이디로 검색
	private User selectWithId() {
		User user = null;
		try {
			outMenu.outLine();
			outMenu.id();
			String userID = sc.nextLine();
			user = service.findUser(userID);
			if (user != null) {
				String slevel = service.selectLevel(user.getLevel());
				outMenu.printUser();
				outMenu.userShow(slevel, user);
				outMenu.outLine();

			} else {
				outMenu.outLine();
				System.out.println();
				outMenu.notFoundUser();
				return null;

			}
		} catch (Exception e) {
			outMenu.outLine();
			System.out.println();
			outMenu.notFoundUser();
		}

		return user;
	}

	/// 유저 삭제
	void deleteUser() {
		User user = null;
		try {
			user = selectWithId();
			if (!(loginId.equals(user.getUserID()))) {
				outMenu.identfyDelete();
				String YN = sc.nextLine().toUpperCase();
				if (YN.equals("Y")) {
					boolean result = service.deleteUser(user);
					if (result) {
						System.out.println();
						outMenu.deleteSuccess();
						return;
					}
				} else {
					System.out.println();
					outMenu.cancel();
					return;
				}
			} else {
				outMenu.NowLogin();
			}
		} catch (Exception e) {
			outMenu.notFoundUser();
		}

	}

	/// 유저 수정
	private void updateUser() {
		try {
			User user = selectWithId();
			if (user == null) {
				return;
			}
			outMenu.selectUserUpdateMenu();
			String select = sc.nextLine();
			System.out.println();
			if (select.equals("0")) {
				return;
			}
			updateUserSwitch(select, user);
		} catch (Exception e) {
			outMenu.wrongInsert();
		}

	}

	private void updateUserSwitch(String select, User user) {
		switch (select) {
		case "1":
			changePassword(user);
			break;
		case "2":
			changeNickname(user);
			break;
		case "3":
			changeEmail(user);
			break;
		case "4":
			changeLevel(user);
			break;
		default:
			break;
		}
	}

	void changePassword(User user) {
		try {
			String password = null;
			boolean flag = true;
			while (flag) {
				outMenu.insertUsePw();

				password = sc.nextLine();
				if (password.length() > 0) {
					flag = false;
				}
			}
			flag = service.updatePassword(user, password);
			if (flag) {
				outMenu.updateSuccess();
			} else {
				outMenu.updateFail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			outMenu.wrongInsert();
		}

	}

	void changeNickname(User user) {
		try {
			String nickname = null;
			boolean flag = true;
			while (flag) {
				outMenu.insertUseNickname();

				nickname = sc.nextLine();
				if (nickname.length() > 0) {
					User knUser = service.findNickname(nickname);
					if (knUser != null) {
						outMenu.existNickname();
					} else {
						flag = false;
					}
				}
			}
			flag = service.updateNickname(user, nickname);
			if (flag) {
				outMenu.updateSuccess();
			} else {
				outMenu.updateFail();
			}
		} catch (Exception e) {
			outMenu.wrongInsert();
		}

	}

	void changeEmail(User user) {
		try {
			String email = null;
			boolean flag = true;
			while (flag) {
				outMenu.insertUseEmail();
				email = sc.nextLine();
				if (email.length() > 0) {
					User knUser = service.findEmail(email);
					if (knUser != null) {
					} else {
						flag = false;
					}
				}
			}
			flag = service.updateEmail(user, email);
			if (flag) {
				outMenu.updateSuccess();
			} else {
				outMenu.updateFail();
			}
		} catch (Exception e) {
			outMenu.wrongInsert();
		}

	}

	private void changeLevel(User user) {
		try {
			if (user.getUserID().equals(loginId)) {
				outMenu.NowLogin();
				return;
			} else {
				String userlevel = null;
				boolean flag = true;
				while (flag) {
					outMenu.selectGrate();
					userlevel = sc.nextLine();
					if (userlevel.length() > 0) {
						flag = false;
					}

				}
				flag = service.updateUserLevel(user, userlevel);
				if (flag) {
					outMenu.updateSuccess();
				} else {
					outMenu.updateFail();
				}
			}
		} catch (Exception e) {
			outMenu.wrongInsert();
		}
	}

	///////////////// 매출정보////////////////////////////////
	private void salesReport() {
		User nowUser = service.findUser(loginId);
		System.out.println();
		outMenu.salesRePort(nowUser.getNickname());
		List<User> user = service.printUser();
		int gold = 0, silver = 0, bronz = 0;
		for (User u : user) {
			gold += u.getGold();
			silver += u.getSilver();
			bronz += u.getBronze();
		}
		System.out.println();
		outMenu.salesReportGold();
		System.out.printf("%d\t\t%d\n", gold, gold * 15000);
		outMenu.salesReportsilver();
		System.out.printf("%d\t\t%d\n", silver, silver * 12000);
		outMenu.salesReportBronze();
		System.out.printf("%d\t\t%d\n", bronz, bronz * 10000);
		System.out.println();
		outMenu.outLine();
		outMenu.salesReportTotal();
		System.out.printf("%d\t\t%d\n", gold + silver + bronz, gold * 15000 + silver * 12000 + bronz * 10000);
		outMenu.outLine();
		System.out.println();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	////////////////// 영상 목록 //////////////////////////////
	void mediaShow(List<Media> media) {
		if (media.size() > 0) {
			int cnt = 0;
			for (Media m : media) {
					System.out.println("[" + (cnt++) + "]" + m.toString());

			}
		} else {
			outMenu.notFoundMediainList();
		}
	}

	private void mediaMenu() {
		while (true) {
			try {
				outMenu.printMedia();
				outMenu.outLine();
				System.out.println();
				List<Media> media = service.printMedia();
				this.mediaShow(media);
				System.out.println();
				outMenu.mediaSelectMenu();
				String select = sc.nextLine();
				if (select.equals("0")) {
					return;
				}
				this.switchMediaMenu(select, media);
			} catch (Exception e) {
				outMenu.wrongInsert();
			}
		}

	}

	private void switchMediaMenu(String select, List<Media> media) {
		switch (select) {
		case "1":
			this.selectMedia(media);
			break;
		case "2":
			this.createMedia();
			break;
		case "3":
			this.updateMedia();
			break;
		case "4":
			this.deleteMedia();
			break;
		default:
			break;
		}

	}

	//////////////// 미디어 생성/////////////////////////////////////
	void createMedia() {
		String mediaName = null;// 미디어 이름
		String nation = null; // 국가
		String category = null;// 카테고리 - 드라마 / 영화 / 애니 / 예능
		String genre = null; // 장르 - 코믹 / 드라마 / 로맨스 / 액션
		int year = 0;
		int month = 0;
		String rate = null;// 등급

		System.out.println();
		outMenu.insertMedia();
		outMenu.outLine();
		System.out.println();

		boolean flag = true;
		while (flag) {
			outMenu.mediaName();
			mediaName = sc.nextLine();
			if (mediaName.length() > 0) {
				flag = false;
			}

		}
		flag = true;
		while (flag) {
			outMenu.nation();
			nation = sc.nextLine().toUpperCase();
			if (nation.length() > 0) {
				flag = false;
			}

		}
		flag = true;
		while (flag) {
			outMenu.category();
			category = sc.nextLine().toUpperCase();
			if (category.length() > 0) {
				flag = false;
			}
		}
		flag = true;
		while (flag) {
			outMenu.genre();
			genre = sc.nextLine().toUpperCase();
			if (genre.length() > 0) {
				flag = false;
			}

		}
		try {
			boolean flage = true;
			while (flage) {
				outMenu.selectRating();
				rate = sc.nextLine();
				if (!(rate.equals("1") || rate.equals("2") || rate.equals("3") || rate.equals("4")
						|| rate.equals("5"))) {
				} else {
					flage = false;
				}
			}
		} catch (Exception e) {

		}
		String rating = service.checkRating(rate);

		boolean flage = true;
		while (flage) {
			try {
				outMenu.releasedYear();
				year = sc.nextInt();
				if (year < 0) {
					throw new Exception();
				} else {
					flage = false;
				}
			} catch (Exception e) {
				sc.next();
			}
		}
		flage = true;
		while (flage) {
			try {
				outMenu.releasedMonth();
				month = sc.nextInt();
				if (0 < month && month <= 12) {
					flage = false;
				} else {
					throw new Exception();
				}
			} catch (Exception e) {
				sc.nextLine();
			}
		}
		sc.nextLine();

		Media media = new Media(mediaName, nation, category, genre, rating);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DATE, 1);
		String d = service.changeDate(cal);

		media.setDate(d);
		flag = service.createMedia(media);
		if (flag) {
			outMenu.createSuccess();
			return;
		} else {
			outMenu.createFail();
		}
	}

	////////////////////////////////////////////////
	private void selectMedia(List<Media> media) {
		outMenu.selectMediaMenu();
		String select = sc.nextLine();
		System.out.println();
		if (select.equals("0")) {
			return;
		}
		this.switchSelectMediaMenu(select, media);
	}

	private void switchSelectMediaMenu(String select, List<Media> media) {
		switch (select) {
		case "1":
			this.selectByOrder(media);
			break;
		case "2":
			this.selectByName();
			break;
		case "3":
			this.selectByCategory();
			break;
		case "4":
			this.selectByGenre();
			break;
		case "5":
			this.selectByRating();
			break;
		case "6":
			this.selectByNation();
			break;
		case "7":
			this.selectByAudience();
			break;
		default:
			break;
		}

	}

	Media selectByOrder(List<Media> media) {
		outMenu.outLine();
		boolean flag = true;
		int no = 0;
		while (flag) {
			try {
				outMenu.no();
				no = sc.nextInt();
				sc.nextLine();
				flag = false;
			} catch (InputMismatchException e) {
				sc.nextLine();
			}
		}
		Media m = service.findMediaWithOrder(no, media);
		if (m != null) {
			outMenu.printMedia();
			outMenu.outLine();
			System.out.println("[" + no + "]" + m.toString());
			outMenu.outLine();
			System.out.println();
		} else {
			outMenu.notFoundMedia();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}

	List<Media> selectByName() {
		outMenu.outLine();
		outMenu.mediaName();
		String name = sc.nextLine();
		System.out.println();
		List<Media> media = service.findMediaWithName(name);
		outMenu.printMedia();
		outMenu.outLine();
		this.mediaShow(media);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return media;

	}

	List<Media> selectByCategory() {
		outMenu.outLine();
		outMenu.category();
		String category = sc.nextLine().toUpperCase();
		List<Media> media = service.findMediaWithCategory(category);
		System.out.println();
		outMenu.printMedia();
		outMenu.outLine();
		System.out.println();
		this.mediaShow(media);
		System.out.println();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return media;
	}

	List<Media> selectByGenre() {
		outMenu.outLine();
		outMenu.genre();
		String genre = sc.nextLine().toUpperCase();
		List<Media> media = service.findMediaWithGenre(genre);
		System.out.println();
		outMenu.printMedia();
		outMenu.outLine();
		System.out.println();
		this.mediaShow(media);
		System.out.println();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return media;
	}

	List<Media> selectByRating() {
		outMenu.outLine();
		String rate = null;
		try {
			boolean flage = true;
			while (flage) {
				outMenu.selectRating();
				rate = sc.nextLine();
				if (!(rate.equals("1") || rate.equals("2") || rate.equals("3") || rate.equals("4")
						|| rate.equals("5"))) {
				} else {
					flage = false;
				}
			}
		} catch (Exception e) {

		}
		String rating = service.checkRating(rate);

		List<Media> media = service.findMediaWithRating(rating);
		System.out.println();
		outMenu.printMedia();
		outMenu.outLine();
		System.out.println();
		this.mediaShow(media);
		System.out.println();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return media;
	}

	List<Media> selectByNation() {
		outMenu.outLine();
		outMenu.nation();
		String nation = sc.nextLine().toUpperCase();
		List<Media> media = service.findMediaWithNation(nation);
		outMenu.printMedia();
		outMenu.outLine();
		System.out.println();
		this.mediaShow(media);
		System.out.println();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return media;
	}

	List<Media> selectByAudience() {
		outMenu.printMedia();
		outMenu.outLine();
		System.out.println();
		List<Media> media = service.printMediaAudience();
		this.mediaShow(media);
		System.out.println();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return media;
	}

	////////////////////////////////////////////////
	private void deleteMedia() {
		System.out.println();
		outMenu.outLine();
		System.out.println();
		System.out.print("[ID] : ");
		int no = sc.nextInt();
		sc.nextLine();
		Media m = service.findMediaWithID(no);
		if (m != null) {
			outMenu.printMedia();
			outMenu.outLine();
			System.out.println("[" + no + "]" + m.toString());
			outMenu.outLine();
			outMenu.identfyDelete();
			String YN = sc.nextLine().toUpperCase();
			if (YN.equals("Y")) {
				boolean result = service.deleteMedia(m);
				if (result) {
					outMenu.deleteSuccess();
					return;
				}
			} else {
				outMenu.cancel();
				return;
			}
		} else {
			outMenu.notFoundMedia();
		}
	}

	private void updateMedia() {
		System.out.println();
		outMenu.outLine();
		System.out.println();
		outMenu.id();
		int no = sc.nextInt();
		sc.nextLine();
		Media m = service.findMediaWithID(no);
		if (m != null) {
			outMenu.printMedia();
			outMenu.outLine();
			System.out.println("[" + no + "]" + m.toString());
			outMenu.menuOfMedia();
			String select = sc.nextLine();
			System.out.println();
			if (select.equals("0")) {
				return;
			}
			outMenu.outLine();
			switch (select) {
			case "1":
				String mediaName = null;
				boolean flag = true;
				while (flag) {
					outMenu.mediaName();
					mediaName = sc.nextLine();
					if (mediaName.length() > 0) {
						flag = false;
					}
				}
				m.setMediaName(mediaName);
				break;
			case "2":
				String nation = null;
				flag = true;
				while (flag) {
					outMenu.nation();
					nation = sc.nextLine().toUpperCase();
					if (nation.length() > 0) {
						flag = false;
					}
				}
				m.setNation(nation);
				break;
			case "3":
				String category = null;
				flag = true;
				while (flag) {
					outMenu.category();
					category = sc.nextLine().toUpperCase();
					if (category.length() > 0) {
						flag = false;
					}
				}
				m.setCategory(category);
				break;
			case "4":
				String genre = null;
				flag = true;
				while (flag) {
					outMenu.genre();
					genre = sc.nextLine().toUpperCase();
					if (genre.length() > 0) {
						flag = false;
					}
				}
				m.setGenre(genre);

				break;
			case "5":
				String rate = null;
				try {
					boolean flage = true;
					while (flage) {
						outMenu.selectRating();
						rate = sc.nextLine();
						if (!(rate.equals("1") || rate.equals("2") || rate.equals("3") || rate.equals("4")
								|| rate.equals("5"))) {
						} else {
							flage = false;
						}
					}
				} catch (Exception e) {

				}
				String rating = service.checkRating(rate);
				m.setRating(rating);
				break;
			case "6":
				int year = 0, month = 0;
				boolean flage = true;
				while (flage) {
					try {
						outMenu.releasedYear();
						year = sc.nextInt();
						if (year < 0) {
							throw new Exception();
						} else {
							flage = false;
						}
					} catch (Exception e) {
						sc.next();
					}
				}
				flage = true;
				while (flage) {
					try {
						outMenu.releasedMonth();
						month = sc.nextInt();
						if (0 < month && month <= 12) {
							flage = false;
						} else {
							throw new Exception();
						}
					} catch (Exception e) {
						sc.nextLine();
					}
				}
				sc.nextLine();

				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.YEAR, year);
				cal.set(Calendar.MONTH, month - 1);
				cal.set(Calendar.DATE, 1);
				String d = service.changeDate(cal);

				m.setDate(d);
				break;
			default:
				outMenu.wrongInsert();
				break;
			}
			service.updateMedia(m);
		} else {
			outMenu.notFoundMedia();
		}
	}

	/////////////////////////////

}
