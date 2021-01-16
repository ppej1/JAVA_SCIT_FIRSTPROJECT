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

import java.util.Scanner;

import global.sesoc.lib.Controller.Service;
import global.sesoc.lib.model.User;

public class MainView {

	private Scanner sc;
	private OutList outMenu;
	private Service service;
	private ViewForAdmin adminVw;
	public String loginId;

	public MainView() {
		sc = new Scanner(System.in);
		outMenu = new OutList();
		service = new Service();
		loginId = null;

	}

	public void start() {
		while(true){
			
			String select;
			// TODO Auto-generated constructor stub
			outMenu.mainMenu();
			select = sc.nextLine();
			System.out.println();
			mainMenuSelect(select);
		}

	}

	private void mainMenuSelect(String select) {
		// TODO Auto-generated method stub
		switch (select) {
		case "1":
			login();
			break;
		case "2":
			createIDPW();
			break;
		case "3":
			this.findIdPassword();
			break;
		case "0":
			System.out.println();
			outMenu.outLine();
			System.out.println();
			outMenu.programOver();
			System.out.println();
			outMenu.outLine();
			// service.saveFile();
			System.exit(-1);
			break;

		default:

			break;
		}
	}

	private void createIDPW() {
		System.out.println();
		outMenu.createID();
		outMenu.outLine();
		System.out.println();
		boolean flag = true;
		String userID = null;
		String email = null;
		String nickname = null;
		while (flag) {
			outMenu.id();
			userID = sc.nextLine();
			if (userID.length() > 0) {
				User knUser = service.findUser(userID);
				if (knUser != null) {
					outMenu.existId();
				} else {
					flag = false;
				}
			}
		}
		flag = true;
		String userPw=null;
		while (flag) {
			outMenu.password();
			userPw = sc.nextLine();
			if (userPw.length() > 0) {
				flag = false;
			}
		}
		flag = true;
		while (flag) {
			outMenu.nickname();// nickname
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
		flag = true;
		while (flag) {
			outMenu.email();
			email = sc.nextLine();
			if (email.length() > 0) {
				User knUser = service.findEmail(email);
				if (knUser != null) {
					outMenu.email();
				} else {
					flag = false;
				}
			}

		}

		System.out.println();
		outMenu.outLine();
		User user = new User(userID, userPw, nickname, email, "1","1900/01/01","1900/01/01", "1900/01/01", 0, 0, 0);
		boolean cflag = service.createUser(user);
		if (cflag) {
			outMenu.createSuccess();
			return;
		} else {
			outMenu.createFail();
			return;
		}
	}

	private void findIdPassword() {
		outMenu.findIdPasswordMenu();
		String select = sc.nextLine();

		if (select.equals("0")) {
			loginId = null;
			return;
		}
		switch (select) {
		case "1":
			outMenu.findId();
			outMenu.email();
			String email = sc.nextLine();
			User user = service.findEmail(email);
			try {
				if (user == null) {
					throw new Exception();
				} else {
					outMenu.id();
					System.out.println(user.getUserID());
					System.out.println();

				}
			} catch (Exception e) {
				outMenu.notFoundUser();
			}
			break;
		case "2":
			try {
				System.out.println();
				outMenu.findPw();
				outMenu.id();
				String id = sc.nextLine();
				user = service.findUser(id);
				if (user != null) {
					outMenu.email();
					email = sc.nextLine();
					if (user.getEmail().equals(email)) {
						boolean flag = true;
						while (flag) {
							System.out.println();
							outMenu.outLine();
							outMenu.insertUsePw();
							String password1 = sc.nextLine();
							outMenu.insertUsePw();
							String password2 = sc.nextLine();
							if (password1.equals(password2)) {
								service.updatePassword(user, password1);
								flag = false;
							} else {
								System.out.println();
								outMenu.outLine();
								outMenu.notSamePassword();
								System.out.println();
								outMenu.outLine();
							}
						}
					} else {
						throw new Exception();
					}
				} else {
					throw new Exception();
				}
			} catch (Exception e) {
				System.out.println();
				outMenu.outLine();
				outMenu.wrongIdEmail();
				System.out.println();
				outMenu.outLine();
				System.out.println();

			}
			break;

		default:
			outMenu.wrongInsert();
			break;
		}
	}

	private void login() {
		outMenu.outLine();
		outMenu.login();
		outMenu.outLine();
		System.out.println();
		String userId= null, userPw =null;
		boolean flag =true;
		while(flag){
			outMenu.id();
			userId = sc.nextLine();
			if (userId.length()>0) {
				flag =false;
			}
		}
		flag = true;
		while(flag){
			outMenu.password();
			userPw = sc.nextLine();
			if (userPw.length()>0) {
				flag =false;
			}
		}

		System.out.println();
		outMenu.outLine();

		int select = service.login(userId, userPw);
		switch (select) {
		case 3:
			outMenu.administratorloginSuccess();
			System.out.println();
			loginId = userId;
			adminVw = new ViewForAdmin(loginId, service);
			adminVw.menuforAdmin();
			break;
		case 2:
			outMenu.userloginSuccess();
			outMenu.outLine();
			System.out.println();

			loginId = userId;
			new ViewForUser(loginId, service);
			break;
		case 1:
		case 0:
			outMenu.loginFail();

			break;
		case -1:
			outMenu.failDb();

			break;
		}
		System.out.println();
	}
}
