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

import java.util.List;

public interface ServiceDaoInterface {

	/*
	 * 유저목록의 출력이 필요할시 DB에서 모든 유저의 목록을 가져온뒤 리스트를 통해 반환한다.
	 * 
	 * @param 없음
	 * 
	 * @return 쿼리가 정상적으로 진행 되었을 경우 유저형 리스트를 반환한다.
	 */
	public List<User> loadUSER();

	/*
	 * userID를 받아와 DB에서 userID를 통해 정보를 검색 한후 유저를 반환한다.
	 * 
	 * @param userID를 받아온다.
	 * 
	 * @return 쿼리가 정상적으로 진행되었을 경우 User를 반환한다.
	 */
	public User findUser(String userID);

	/*
	 * 유저 email을 받아와 DB에서 유저 email을 통해 정보를 검색 한 후 유저를 반환한다.
	 * 
	 * @param User의 email을 받아온다.
	 * 
	 * @return 쿼리가 정상적으로 진행 되었을 경우 User를 반환한다.
	 */
	public User findEmail(String email);

	/*
	 * 유저nickname을 받아와 DB에서 유저nickname 을 통해 정보를 검색 한 후 유저를 반환한다.
	 * 
	 * @param User의 nickname을 받아온다.
	 * 
	 * @return 쿼리가 정상적으로 진행 되었을 경우 User를 반환한다.
	 */
	public User findNickname(String nickname);

	/*
	 * DB에 새로운 User의 정보를 등록한다.정상적으로 등록이 되었다면 boolean 을 이용해 true 실패 시 false 를
	 * 반환한다.
	 * 
	 * @param 새로 등록할 User 의 정보를 가져 옵니다.
	 * 
	 * @return 정상적으로 쿼리문이 실행되었다면 true 를 실패 했다면 false 를 반환한다.
	 */
	public boolean createUser(User user);

	/*
	 * DB 에서 USER의 정보를 삭제한다. 정상적으로 삭제 되었다면 boolean 을 이용해 true 를 실패시 false를 반환한다
	 * 
	 * @param 새로 등록한 User 정보를 가져옵니다.
	 * 
	 * @return 정상적으로 쿼리가 실행 되었다면 true 를 실패 했다면 false 를 반환한다.
	 */
	public boolean deleteUser(User user);

	/*
	 * DB에 user의 정보를 기반으로 password 의 정보를 수정한다
	 * 
	 * @param 수정하려고 하는 User 와 password 를 가져옵니다.
	 * 
	 * @return 정상적으로 쿼리가 실행 되었다면 trues를 실패했따면 false 를 반환한다.
	 */
	public boolean updatePassword(User user, String password);

	/*
	 * DB에 user의 정보를 기반으로 nickname 의 정보를 수정한다
	 * 
	 * @param 수정하려고 하는 User 와 nickname 를 가져옵니다.
	 * 
	 * @return 정상적으로 쿼리가 실행 되었다면 trues를 실패했따면 false 를 반환한다.
	 */
	public boolean updateNickname(User user, String nickname);

	/*
	 * DB에 user의 정보를 기반으로 email 의 정보를 수정한다
	 * 
	 * @param 수정하려고 하는 User 와 email 를 가져옵니다.
	 * 
	 * @return 정상적으로 쿼리가 실행 되었다면 trues를 실패했따면 false 를 반환한다.
	 */
	public boolean updateEmail(User user, String email);

	/*
	 * DB에 user의 정보를 기반으로 userlevel 의 정보를 수정한다
	 * 
	 * @param 수정하려고 하는 User 와 userlevel 를 가져옵니다.
	 * 
	 * @return 정상적으로 쿼리가 실행 되었다면 trues를 실패했따면 false 를 반환한다.
	 */
	public boolean updateUserLevel(User user, String userlevel);

	/*
	 * SELECT를 이용해 DB에서 모든 미디어의 정보를 가져온다.
	 * 
	 * @return 쿼리를 통해 받아온 모든 media 정보를 가지고 넣은 List<Media> 반환한다.
	 */
	public List<Media> loadMEDIA();

	/*
	 * SELECT를 이용해 DB에서 모든 미디어의 정보를 가져온다. 정보를 가져올 시 조회수를 기준으로 내림차순으로 받아온다.
	 * 
	 * @return 쿼리를 통해 받아온 모든 media 정보를 가지고 넣은 List<Media> 반환한다.
	 */
	public List<Media> printMediaAudience();

	/*
	 * DB에 새로운 media의 정보를 등록한다.정상적으로 등록이 되었다면 boolean 을 이용해 true 실패 시 false 를
	 * 반환한다.
	 * 
	 * @param 새로 등록할 media 의 정보를 가져 옵니다.
	 * 
	 * @return 정상적으로 쿼리문이 실행되었다면 true 를 실패 했다면 false 를 반환한다.
	 */
	public boolean createMedia(Media media);

	/*
	 * DB 에서 media의 정보를 삭제한다. 정상적으로 삭제 되었다면 boolean 을 이용해 true 를 실패시 false를 반환한다
	 * 
	 * @param 새로 등록한 media 정보를 가져옵니다.
	 * 
	 * @return 정상적으로 쿼리가 실행 되었다면 true 를 실패 했다면 false 를 반환한다.
	 */
	public boolean deleteMedia(Media m);

	/*
	 * DB에 media의 정보를 수정한다. user와는 달리 미리 수정된 media를 가져와 DB에 INSERT한다.
	 * 
	 * @param 수정하려고 하는 media 를 가져옵니다.
	 * 
	 * @return 정상적으로 쿼리가 실행 되었다면 trues를 실패했따면 false 를 반환한다.
	 */
	public boolean updateMedia(Media media);

	/*
	 * DB 에서 id를 통해 미디어의 정보를 검색한다.
	 * 
	 * @param no(id) 를 받아온다
	 * 
	 * @return id를 통해 검색된 media 의 정보를 media 객체를 통해 반환한다.
	 */
	public Media findMediaWithID(int no);
	/*
	 * DB 에서 name 을 통해 미디어의 정보를 검색한다.
	 * 
	 * @param name을  받아온다
	 * 
	 * @return name 을  통해 검색된 media 의 정보를 List<Media> 를 통해 반환한다.
	 */
	public List<Media> findMediaWithName(String name);
	/*
	 * DB 에서 category를 통해 미디어의 정보를 검색한다.
	 * 
	 * @param category 를 받아온다
	 * 
	 * @return  category를 통해 검색된 media 의 정보를 List<Media> 를 통해 반환한다.
	 */
	public List<Media> findMediaWithCategory(String category);
	/*
	 * DB 에서 genre를 통해 미디어의 정보를 검색한다.
	 * 
	 * @param genre 를 받아온다
	 * 
	 * @return genre를 통해 검색된 media 의 정보를 List<Media> 를 통해 반환한다.
	 */
	public List<Media> findMediaWithGenre(String genre);
	/*
	 * DB 에서 rating을 통해 미디어의 정보를 검색한다.
	 * 
	 * @param rating 을 받아온다
	 * 
	 * @return rating를 통해 검색된 media 의 정보를 List<Media> 를 통해 반환한다.
	 */
	public List<Media> findMediaWithRating(String rating);
	/*
	 * DB 에서 nation을 통해 미디어의 정보를 검색한다.
	 * 
	 * @param nation 을 받아온다
	 * 
	 * @return nation를 통해 검색된 media 의 정보를 List<Media> 를 통해 반환한다.
	 */
	public List<Media> findMediaWithNation(String nation);
	/*
	 * DB 에서 loginId을 통해  user 가 시정 한 미디어의 정보를 검색한다.
	 * 
	 * @param loginId 을 받아온다
	 * 
	 * @return loginId를 통해 검색된 media 의 정보를 List<Media> 를 통해 반환한다.
	 */
	public List<Media> MyMediaList(String loginId);
	/*
	 * USER가 시청을 한 경우 DB 에서 UserID와 mediaid를 연결해서 시청한 목록을 생성한다.
	 * 
	 * @param 시청한 User의 정보와 시청한 media의 정보를 받아온다.
	 * 
	 */
	public void watchMedia(String loginId, Media media);
	/*
	 * 유저의 멤버쉽이 변경되었을 경우 user의 아이디를 통해 변환된 user의 등급을 변경한다.
	 * 
	 * @param user의 정보를 변경한 user 가져온다.
	 * 
	 * @return  정상적으로 쿼리가 실행 되었다면 trues를 실패했따면 false 를 반환한다.
	 */
	public boolean joinMembership(User user);
	/*
	 * USER가 시청을 한 경우 DB 에서 media의 조회수를 올려준다 .
	 * 
	 * @param 이미 수정된 media 을 받아온다
	 * 
	 * @return 쿼리가 정상적으로 수정 되었다면 true를 실패하면 false 를 반환한다.
	 */
	public boolean updateMediaAudience(Media media);

}
