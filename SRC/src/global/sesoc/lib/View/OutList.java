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

import java.text.SimpleDateFormat;
import java.util.Date;

import global.sesoc.lib.model.User;

public class OutList {
	private SimpleDateFormat smfm = new SimpleDateFormat("yyyy/MM/dd");
	private Date today = new Date();

	public void outLine() {
		System.out.println("================================================================================");
	}

	public void title() {
		this.outLine();
		System.out.print("[ 미떼미떼  ]");
	}

	public void time() {
		System.out.print(smfm.format(today));
	}

	public void mainMenu() {
		this.title();
		System.out.print("  \t\t\t\t\t\t\t   ");
		this.time();
		System.out.println();
		this.outLine();
		System.out.println();
		System.out.println("[ 1.로그인  ]");
		System.out.println("[ 2.회원가입  ]");
		System.out.println("[ 3.아이디 / 비밀번호 찾기  ]");
		System.out.println("[ 0.프로그램 종료  ]");
		System.out.println();

		this.outLine();
		this.select();
	}

	public void findIdPasswordMenu() {
		System.out.println();
		this.outLine();
		System.out.println("[ 아이디 / 비밀번호 찾기  ]");
		this.outLine();
		System.out.println();
		System.out.println("[ 1.아이디 찾기  ]");
		System.out.println("[ 2.비밀번호 찾기  ]");
		this.returnFront();
		System.out.println();
		this.outLine();
		this.select();
	}

	public void findId() {
		System.out.println();
		this.outLine();
		System.out.println("[ 아이디 찾기  ]");
		this.outLine();
		System.out.println();

	}
	public void findPw() {
		System.out.println();
		this.outLine();
		System.out.println("[ 비밀번호 찾기  ]");
		this.outLine();
		System.out.println();

	}
	////////////////////////// 관리자 페이지 ////////////////////////////////////////
	public void menuforAdmin(String nickname) {
		System.out.print("[ 미떼미떼 - <관리자> - " + nickname + " ]");
		System.out.print("  \t\t\t\t   ");
		this.time();
		System.out.println();
		this.outLine();
		System.out.println();
		System.out.println("[ 1.사용자 정보 확인  ]");
		System.out.println("[ 2.매출 정보 확인  ]");
		System.out.println("[ 3.영상 목록 출력  ]");
		System.out.println("[ 0.로그아웃 ]");
		System.out.println();
		this.outLine();
		this.select();
	}

	public void printUser() {
		System.out.println();
		System.out.println("[사용자 정보]");
		this.outLine();
		System.out.println("[ 등급 ] 아이디    닉네임        이메일            가입날짜     멤버십 소멸날짜");
		this.outLine();

	}

	public void userShow(String slevel, User user) {
		if (slevel.length() < 3) {
			System.out.printf("[%4s] %-9s %-9s %-22s %-15s %s\n", slevel, user.getUserID(), user.getNickname(),
					user.getEmail(), user.getJoinDate(), user.getEnd());
		} else {
			System.out.printf("[%s] %-9s %-9s %-22s %-15s %s\n", slevel, user.getUserID(), user.getNickname(),
					user.getEmail(), user.getJoinDate(), user.getEnd());

		}
	}

	public void printSelectUser() {
		this.outLine();
		System.out.println();
		System.out.println("[ 1.아이디 검색  ]");
		System.out.println("[ 2.닉네임 검색  ]");
		System.out.println("[ 3.이메일 검색  ]");
		this.returnFront();
		System.out.println();
		this.outLine();
		this.select();
	}

	public void selectUserUpdateMenu() {
		System.out.println();
		System.out.println("[ 1.비밀번호 수정  ]");
		System.out.println("[ 2.닉네임 수정  ]");
		System.out.println("[ 3.이메일 수정  ]");
		System.out.println("[ 4.등급 수정  ]");
		this.returnFront();
		System.out.println();
		this.outLine();
		this.select();
	}

	public void userSelectMenu() {
		System.out.println();
		System.out.println("[ 1.사용자 검색  ]");
		System.out.println("[ 2.사용자 수정  ]");
		System.out.println("[ 3.사용자 삭제  ]");
		this.returnFront();
		System.out.println();
		this.outLine();
		this.select();
	}

	public void salesRePort(String nickname){
		System.out.print("[미떼미떼 - <관리자> - " + nickname + "]");
		System.out.print("  \t\t\t\t   ");
		this.time();
		System.out.println();
		this.outLine();
		System.out.print("\t등급\t\t가격\t\t판매 수\t\t매출\n");
		this.outLine();
	}
	public void salesReportGold(){
		System.out.print("\t골드\t\t 15,000\t\t");
	}
	public void salesReportsilver(){
		System.out.print("\t실버\t\t 12,000\t\t");
	}
	public void salesReportBronze(){
		System.out.print("\t브론즈\t\t 10,000\t\t");
	}
	public void salesReportTotal(){
		System.out.print("\t종합\t\t\t\t");
	}
	
	//////////////////영상 관련 /////////
	public void mediacolumn(){
		System.out.println("[no][id]  국가        제목             카테고리   장르     등급  출시일  조회수 ");

	}
	public void printMedia() {
		System.out.println();
		this.outLine();
		System.out.println("[영상목록]");
		this.outLine();
		this.mediacolumn();
	}

	public void mediaSelectMenu() {
		this.outLine();
		System.out.println("[ 1.영상정보 검색  ]");
		System.out.println("[ 2.영상정보 등록  ]");
		System.out.println("[ 3.영상정보 수정  ]");
		System.out.println("[ 4.영상 삭제  ]");
		this.returnFront();
		this.outLine();
		this.select();
	}

	public void selectMediaMenu() {
		this.outLine();
		System.out.println("[ 1.번호별 검색 ]");
		System.out.println("[ 2.제목별 검색  ]");
		System.out.println("[ 3.카테고리별 검색  ]");
		System.out.println("[ 4.장르별 검색  ]");
		System.out.println("[ 5.등급별 검색  ]");
		System.out.println("[ 6.국가별 검색  ]");
		System.out.println("[ 7.조회수 기준 정렬  ]");
		this.returnFront();
		this.outLine();
		this.select();

	}

	public void menuOfMedia() {
		this.outLine();
		System.out.println("[ 1.제목 변경  ]");
		System.out.println("[ 2.국가 변경  ]");
		System.out.println("[ 3.카테고리 변경  ]");
		System.out.println("[ 4.장르 변경  ]");
		System.out.println("[ 5.등급 변경  ]");
		System.out.println("[ 6.출시일 변경  ]");
		this.returnFront();
		this.outLine();
		this.select();
	}

	
	
	
	///////////////////// 유저용 페이지

	public void menuforUser(String nickname) {
		this.outLine();
		System.out.print("[미떼미떼 - <User> - " + nickname + "]");
		System.out.print(" \t\t\t\t\t   ");
		this.time();
		System.out.println();
		this.outLine();
		System.out.println("[ 1.영상 시청  ]");
		System.out.println("[ 2.멤버십 가입  ] ");
		System.out.println("[ 3.마이페이지  ] ");
		System.out.println("[ 0.로그아웃  ]");
		this.outLine();
		this.select();
	}
	
	
	public void mainMenuForUser(String nickname) {
		this.outLine();
		System.out.print("[미떼미떼 - <User> - " + nickname + "]");
		System.out.print(" \t\t\t\t\t    ");
		this.time();
		System.out.println();
		this.outLine(); 
		System.out.println();
		System.out.println(" 안녕하세요!" + nickname + "님:)");
		System.out.println();
		System.out.println(" 오늘 기분은 어떠신가요? ");
		System.out.println( nickname + "님의 기분에 맞는 영화나 드라마를 추천드려도 괜찮을까요? ");
		System.out.println();
		System.out.println("[ 1.네. 좋아요. ]");
		System.out.println("[ 2.괜찮아요. 다음에요.] ");
		System.out.println();
		this.outLine();
		this.select();
	}
	
	public void userEmotion() {
		this.outLine();
		System.out.println();
		System.out.println(" 나는 지금, ");
		System.out.println();
		System.out.println("[ 1.행복해요  ]");
		System.out.println("[ 2.우울해요  ]");
		System.out.println("[ 3.스트레스받아요  ]");
		System.out.println();
		this.outLine();
		this.select();
	}
	
	public void happyUser(String nickname){
		this.outLine();
		System.out.println("행복한 " + nickname + "님을 위해서 로맨스 영화를 추천드릴게요.");		
		System.out.println("보고싶으신 영상의 번호를 선택해주세요.");
		this.outLine();
		this.mediacolumn();
		this.outLine();
		
	}
	

	
	public void sadUser(String nickname){
		this.outLine();
		System.out.println("우울하신 " + nickname + "님을 위해서 멜로드라마 영화를 추천드릴게요.");		
		System.out.println("보고싶으신 영상의 번호를 선택해주세요.");
		this.outLine();
		this.mediacolumn();
		this.outLine();
		
	}
	
	public void stressfulUser(String nickname){
		this.outLine();
		System.out.println("즐거움이 필요하신 " + nickname + "님을 위해서 액션 영화를 추천드릴게요.");		
		System.out.println("보고싶으신 영상의 번호를 선택해주세요.");
		this.outLine();
		this.mediacolumn();
		this.outLine();
		
	}
	
	public void watchMedia() {
		System.out.println();
		System.out.println();
		this.outLine();
		System.out.println();
		System.out.println();
		System.out.println("  ∧,＿,∧ ");
		System.out.println();
		System.out.println(" （。ㅠ ω ㅠ。)つ━☆・*。 ");
		System.out.println();
		System.out.println(" ⊂　 ノ 　　　 。°+. ");
		System.out.println();
		System.out.println("し-Ｊ　　　 °。+ *영화같지않지만´,) ");
		System.out.println();
		System.out.println("　　　　　　　　　.· ´¸영화재생중입니다.·*´¨) ¸.·*¨) ");
		System.out.println();
		System.out.println("　　　　　　　　　　(¸.·´ ( ");
		System.out.println();
		System.out.println();
		this.outLine();
		System.out.println();
		System.out.println();
		
		
		
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
	public void watchMediaMenu() {
		this.outLine();
		System.out.println();
		System.out.println("[ 1. 바로 시청하기]");
		System.out.println("[ 2. 영상 검색하기]");
		System.out.println("[ 3. 내가 본 목록]");
		System.out.println("[ 0. 돌아가기]");
		System.out.println();
		this.outLine();
		this.select();
	}
	
	public void searchByMenu() {
		this.outLine();
		System.out.println();
		System.out.println("[1.장르별 영상검색]");
		System.out.println("[2.제목별 영상검색]");
		System.out.println("[3.국가별 영상검색]");
		System.out.println("[4.조회수별 영상검색]");
		System.out.println("[0.돌아가기]");
		System.out.println();
		this.outLine();
		this.select();
	}
	
	public void joinMembership() {
		this.outLine();
		System.out.println();
		System.out.println("가입하실 요금제를 선택해주세요.");
		System.out.println("[1. GOLD : 최신 영상컨텐츠 무제한 감상] [월/15,000원]");
		System.out.println("[2. SILVER : 최근 6개월 이전의 영상컨텐츠 무제한 감상] [월/12,000원]");
		System.out.println("[3. BRONZE : 최근 1년 이전의 영상컨텐츠 무제한 감상] [월/10,000원]");
		System.out.println();
		this.outLine();
		this.select();
	}
	
	public void realJoinMembership(String grade, String slevel, String nickname) {
		this.outLine();
		System.out.println();
		System.out.println("지금 " + nickname + "님은 [" + slevel + "]등급입니다.");
		System.out.println("[" + slevel+ "]등급에서  [" + grade + "]등급으로 변경을 원하시나요?");
		System.out.println();
	}

	public void updateMembership() {
		System.out.println("※※※※※※※※※※※※※※주의※※※※※※※※※※※※※※※※※");
		System.out.println("기존에 결제하신 남은 기간은 등급 변경과 동시에 자동소멸됩니다.^^;;");
		System.out.println("남은 기간이 종료 된 후에 결제를 추천드립니다^^;;;");
		System.out.println("※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※");
		System.out.println();
		System.out.println("[ 1. 주의사항을 숙지했으며 소멸되는 것에 동의합니다. ]");
		System.out.println("[ 2. 메인 메뉴로 돌아가기 ]");
		System.out.println();
		this.outLine();
		this.select();	
	}
	
	public void myPage() {
		System.out.println();
		System.out.println("[1. 내정보수정]");
		System.out.println("[2. 회원탈퇴]");
		System.out.println("[3. 메인으로 돌아가기]");
		System.out.println();
		this.outLine();
		this.select();	
	}
	
	public void updateMyPage() {
		System.out.println();
		System.out.println("[ 1.비밀번호 변경 ]");
		System.out.println("[ 2.닉네임 변경 ]");
		System.out.println("[ 3.이메일 변경 ]");
		System.out.println("[ 0.메인으로 돌아가기]");
		System.out.println();
		this.outLine();
		this.select();	
	}
	
	public void endmembership01(String slevel,User user) {
		System.out.println(slevel + "등급의 서비스가    " + user.getEnd() + "   해제됩니다.");
		System.out.println("서비스를 계속 이용하시려면 결제를 부탁드립니다.");
		System.out.println("[1. 결제하기 ]");
		System.out.println("[0. 나중에하기 ]");
	}	
	public void endmembership02(String slevel,User user) {
		System.out.println(slevel + "등급의 서비스가    " + user.getEnd() + "   해제되었습니다.");
		System.out.println("서비스를 계속 이용하시려면 결제를 부탁드립니다.");
	}		
	
	public void showMedia(){
		System.out.println("정말 시청하시겠어요?");
		System.out.println();
		System.out.println("[1. 시청하기]");
		System.out.println("[0. 메인화면]");
	}
	public void membershipSuccess() {
		System.out.println("멤버십 등록이 완료되었습니다.");
	}

	public void membershipFail() {
		System.out.println("왜 돈을 주는데..받지를 못하죠..."); // 나중에 고쳐욤~~
	}	
	
	public void noAccess() {
		System.out.println();
		System.out.println("멤버십 권한 미달으로 영상시청이 불가능합니다.");
		System.out.println("3초 후에 메인화면으로 돌아갑니다.");
	
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	////////////////////////////// 국가에 맞춰서 언어를 수정 및 중복된 언어 수정을 해결 하기 위해 모듈화
	
	public void administratorLogin(String loginId) {
		System.out.println("[ NOTFLIX - <관리자> - " + loginId + " ]");
	}

	public void administratorloginSuccess() {
		System.out.println("[ 관리자 로그인 성공 ]");
	}

	public void userloginSuccess() {
		System.out.println("[ 로그인 성공 ]");
	}

	public void loginFail() {
		System.out.println("[ 로그인 실패 ] 아이디 또는 비밀번호가 잘못 되었습니다.");
	}

	public void failDb() {
		System.out.println("[ 로그인 실패 ] 아이디 또는 비밀번호가 잘못 되었습니다");
	}

	public void cancel() {
		System.out.println("[ 취소 되었습니다. ]");
	}

	public void identfyDelete() {
		System.out.print("[ 정말 삭제 하실 건가요?(Y/N) ] : ");
	}

	public void deleteSuccess() {
		System.out.println("[ 정상 삭제 되었습니다. ]");
	}

	public void NowLogin() {
		System.out.println("[ 현재 로그인 중인 아이디입니다. ]");
	}

	public void existId() {
		System.out.println("[ 이미 존재하는 아이디입니다. ]");
	}

	public void existNickname() {
		System.out.println("[ 이미 존재하는 닉네임 입니다. ]");
	}

	public void existEmail() {
		System.out.println("[ 이미 존재하는 이메일 입니다. ]");
	}

	public void no() {
		System.out.print("[no] : ");
	}

	public void login() {
		System.out.println("[ 로그인 ]");
	}

	public void createID() {
		System.out.println("[ 회원가입 ]");
	}

	public void id() {
		System.out.print("[ 아이디 ] :");
	}

	public void password() {
		System.out.print("[ 비밀번호 ] :");
	}

	public void email() {
		System.out.print("[ 이메일 ] :");
	}

	public void nickname() {
		System.out.print("[ 닉네임 ]: ");
	}

	public void selectGrate() {
		System.out.print("[ 등급 수정 (관리자 - 0 / 일반 유저 - 1 / 브론즈 -2 / 실버 - 3 / 골드 - 4) ] :");
	}

	public void createSuccess() {
		System.out.println("[ 생성 성공 ]");
	}

	public void createFail() {
		System.out.println("[ 생성 실패 ]");
	}

	public void updateSuccess() {
		System.out.println("[ 수정 성공 ]");
	}

	public void updateFail() {
		System.out.println("[ 수정 실패 ]");
	}

	public void insertMedia() {
		System.out.println("[ 영상 입력 ]");
	}

	public void mediaName() {
		System.out.print("[ 제목 ] : ");
	}

	public void nation() {
		System.out.print("[ 국가 ] : ");
	}

	public void category() {
		System.out.print("[ 카테고리 ] : ");
	}

	public void genre() {
		System.out.print("[ 장르 ] : ");
	}

	public void selectRating() {
		System.out.print("[ 등급(1 - 전체 / 2 - 7세 /3 - 12세 / 4 - 15세 / 5 - 19세) ] : ");
	}

	public void releasedYear() {
		System.out.print("[ 출시 년도 ] : ");
	}

	public void releasedMonth() {
		System.out.print("[ 출시 월 ] : ");
	}

	public void notFoundMedia() {
		System.out.println("[ 검색된 영상이 없습니다. ]");
	}

	public void notFoundMediainList() {
		System.out.println("[ 목록에 영상이 없습니다. ]");
	}

	public void noUsedUser() {
		System.out.println("[ 사용자 목록을 받아 올 수 없습니다. ]");
	}

	public void insertUseEmail() {
		System.out.print("[ 수정하실 이메일을 입력하세요] : ");
	}

	public void insertUseNickname() {
		System.out.print("[ 수정하실 닉네임을 입력하세요 ] : ");
	}

	public void insertUsePw() {
		System.out.print("[ 수정하실 비밀번호를 입력하세요 ] :");
	}

	public void programOver() {
		System.out.println("[ 프로그램 종료을 종료합니다. ]");
	}

	public void notFoundUser() {
		System.out.println("[ 회원을 찾을 수 없습니다. ]");
	}

	public void wrongInsert() {
		System.out.println("[ 입력이 잘못 되었습니다. ]");
	}

	public void wrongIdEmail() {
		System.out.println("[ 아이디와이메일 정보가 맞지 않습니다. ]");
	}

	public void notSamePassword() {
		System.out.println("[ 비밀번호가 같지 않습니다. 비밀번호와 비밀번호 확인을 같게 넣어주세요. ]");
	}
	
	public void returnFront(){
		System.out.print("[ 0.돌아가기 ]\n");
	}
	public void select(){
		System.out.print("[선택]");
	}

	
	
	
///////////////////////////////////////////////////////////
	public String administrator() {
		return "관리자";
	}

	public String general() {
		return "일반";
	}

	public String bronze() {
		return "브론즈";
	}

	public String silver() {
		return "실버";
	}

	public String gold() {
		return "골드";
	}

	public String AvailableAll() {
		return "All";
	}

	public String Available7() {
		return "7";
	}

	public String Available12() {
		return "12";
	}

	public String Available15() {
		return "15";
	}

	public String Available19() {
		return "19";
	}
















}
