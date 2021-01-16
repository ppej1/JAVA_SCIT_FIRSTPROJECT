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

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDao implements ServiceDaoInterface {
	DBConnection dBconn;
	Connection conn;
	CallableStatement cstmt = null;
	PreparedStatement psmt1 = null;
	PreparedStatement psmt = null;
	

	public ServiceDao() {
		dBconn = new DBConnection();
	}
	@Override
	public List<User> loadUSER() {
		List<User> userList = new ArrayList<User>();
		conn = dBconn.getConnection();
		String query = "{ call sp_user_selectAll(?) }";
		ResultSet rs = null;

		try {
			cstmt = conn.prepareCall(query);
			cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
			cstmt.executeUpdate();
			rs = (ResultSet) cstmt.getObject(1);
			if (!rs.next()) { // 레코드가 없다면
				userList = null;
			} else { // 있다면
				do {
					User user = new User(rs.getString("U_ID"), rs.getString("U_PW"), rs.getString("U_NIK"),
							rs.getString("U_EMAIL"), rs.getString("U_LEVEL"), rs.getString("JOINDATE"),
							rs.getString("START_DAY"), rs.getString("END_DAY"), rs.getInt("GOLD"), rs.getInt("SILVER"),
							rs.getInt("BRONZE"));

					userList.add(user);
				} while (rs.next());
			}
			if (rs != null)
				rs.close();
			if (cstmt != null)
				cstmt.close();
			DBclose.close(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;

	}
	@Override
	public User findUser(String userID) { // 아이디로 검색
		conn = dBconn.getConnection();
		if (conn == null) {
			System.out.println("!!");
		}
		User user = null;

		String query = "{ call sp_user_selectId(?,?) }";
		ResultSet rs = null;

		try {
			cstmt = conn.prepareCall(query);
			cstmt.setString(1, userID);
			cstmt.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
			cstmt.executeUpdate();
			rs = (ResultSet) cstmt.getObject(2);
			if (rs.next()) {
				user = new User(rs.getString("U_ID"), rs.getString("U_PW"), rs.getString("U_NIK"),
						rs.getString("U_EMAIL"), rs.getString("U_LEVEL"), rs.getString("JOINDATE"),
						rs.getString("START_DAY"), rs.getString("END_DAY"), rs.getInt("GOLD"), rs.getInt("SILVER"),
						rs.getInt("BRONZE"));
			}
			if (rs != null)
				rs.close();
			if (cstmt != null)
				cstmt.close();
			DBclose.close(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	@Override
	public User findEmail(String email) { // 아이디로 검색
		conn = dBconn.getConnection();
		if (conn == null) {
			System.out.println("!!");
		}
		User user = null;

		String query = "{ call sp_user_selectEmail(?,?) }";
		ResultSet rs = null;

		try {
			cstmt = conn.prepareCall(query);
			cstmt.setString(1, email);
			cstmt.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
			cstmt.executeUpdate();
			rs = (ResultSet) cstmt.getObject(2);
			if (rs.next()) {
				user = new User(rs.getString("U_ID"), rs.getString("U_PW"), rs.getString("U_NIK"),
						rs.getString("U_EMAIL"), rs.getString("U_LEVEL"), rs.getString("JOINDATE"),
						rs.getString("START_DAY"), rs.getString("END_DAY"), rs.getInt("GOLD"), rs.getInt("SILVER"),
						rs.getInt("BRONZE"));
			}
			if (rs != null)
				rs.close();
			if (cstmt != null)
				cstmt.close();
			DBclose.close(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	@Override
	public User findNickname(String nickname) { // 아이디로 검색
		conn = dBconn.getConnection();
		if (conn == null) {
			System.out.println("!!");
		}
		User user = null;

		String query = "{ call sp_user_selectNickname(?,?) }";
		ResultSet rs = null;

		try {
			cstmt = conn.prepareCall(query);
			cstmt.setString(1, nickname);
			cstmt.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
			cstmt.executeUpdate();
			rs = (ResultSet) cstmt.getObject(2);
			if (rs.next()) {
				user = new User(rs.getString("U_ID"), rs.getString("U_PW"), rs.getString("U_NIK"),
						rs.getString("U_EMAIL"), rs.getString("U_LEVEL"), rs.getString("JOINDATE"),
						rs.getString("START_DAY"), rs.getString("END_DAY"), rs.getInt("GOLD"), rs.getInt("SILVER"),
						rs.getInt("BRONZE"));
			}
			if (rs != null)
				rs.close();
			if (cstmt != null)
				cstmt.close();
			DBclose.close(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	@Override
	public boolean createUser(User user) {
		conn = dBconn.getConnection();
		String query = "{ call sp_user_insert(?,?,?,?,?,?,?,?,?,?,?)}";
		boolean flag = false;
		try {
			cstmt = conn.prepareCall(query);
			cstmt.setString(1, user.getUserID());
			cstmt.setString(2, user.getUserPw());
			cstmt.setString(3, user.getNickname());
			cstmt.setString(4, user.getEmail());
			cstmt.setString(5, user.getLevel());
			cstmt.setString(6, user.getJoinDate());
			cstmt.setString(7, user.getStart());
			cstmt.setString(8, user.getEnd());
			cstmt.setInt(9, user.getGold());
			cstmt.setInt(10, user.getSilver());
			cstmt.setInt(11, user.getBronze());
			int cnt = cstmt.executeUpdate();

			if (cnt == 1) {
				flag = true;
				conn.commit();
			}
			if (cstmt != null)
				cstmt.close();
			DBclose.close(conn);
			return flag;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean deleteUser(User user) {
		conn = dBconn.getConnection();

		String query = "{ call sp_user_delete(?) }";
		boolean flag = false;
		try {
			cstmt = conn.prepareCall(query);
			cstmt.setString(1, user.getUserID());
			int cnt = cstmt.executeUpdate();

			if (cnt == 1) {
				flag = true;
				conn.commit();
			}
			if (cstmt != null)
				cstmt.close();
			DBclose.close(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;

	}
	@Override
	public boolean updatePassword(User user, String password) {
		conn = dBconn.getConnection();

		String query = "{ call sp_user_updatePassword(?,?) }";
		boolean flag = false;

		try {
			cstmt = conn.prepareCall(query);

			cstmt.setString(2, user.getUserID());
			cstmt.setString(1, password);
			int cnt = cstmt.executeUpdate();

			if (cnt == 1) {
				flag = true;
				conn.commit();
			}
			if (cstmt != null)
				cstmt.close();
			DBclose.close(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	public boolean updateNickname(User user, String nickname) {
		conn = dBconn.getConnection();
		String query = "{ call sp_user_updateNickname(?,?) }";
		boolean flag = false;

		try {
			cstmt = conn.prepareCall(query);
			cstmt.setString(2, user.getUserID());
			cstmt.setString(1, nickname);
			int cnt = cstmt.executeUpdate();

			if (cnt == 1) {
				flag = true;
				conn.commit();
			}
			if (cstmt != null)
				cstmt.close();
			DBclose.close(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	public boolean updateEmail(User user, String email) {
		conn = dBconn.getConnection();

		String query = "{ call sp_user_updateEmail(?,?) }";
		boolean flag = false;

		try {
			cstmt = conn.prepareCall(query);

			cstmt.setString(2, user.getUserID());
			cstmt.setString(1, email);
			int cnt = cstmt.executeUpdate();

			if (cnt == 1) {
				flag = true;
				conn.commit();
			}
			if (cstmt != null)
				cstmt.close();
			DBclose.close(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	public boolean updateUserLevel(User user, String userLevel) {
		conn = dBconn.getConnection();
		String query = "{ call sp_user_updateUserLevel(?,?,?,?,?,?,?) }";

		boolean flag = false;
		try {
			cstmt = conn.prepareCall(query);

			cstmt.setString(7, user.getUserID());
			cstmt.setString(1, userLevel);
			cstmt.setString(2, user.getStart());
			cstmt.setString(3, user.getEnd());
			cstmt.setInt(4, user.getGold());
			cstmt.setInt(5, user.getSilver());
			cstmt.setInt(6, user.getBronze());
			int cnt = cstmt.executeUpdate();
			if (cnt == 1) {
				flag = true;
				conn.commit();
			}
			if (cstmt != null)
				cstmt.close();
			DBclose.close(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	////////////////////////////////////////////////////////////////////
	@Override
	public List<Media> loadMEDIA() {
		List<Media> mList = new ArrayList<Media>();
		conn = dBconn.getConnection();
		String query = "{ call sp_media_selectAll(?) }";
		ResultSet rs = null;

		try {
			cstmt = conn.prepareCall(query);
			cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
			cstmt.executeUpdate();
			rs = (ResultSet) cstmt.getObject(1);
			if (!rs.next()) { // 레코드가 없다면
				mList = null;
			} else { // 있다면
				do {
					Media media = new Media(rs.getString("MEDIA_NM"), rs.getString("NATION"),
							rs.getString("MD_CATEGORY"), rs.getString("GENRE"), rs.getString("RATING"));
					media.setDate(rs.getString("M_DATE"));
					media.setMediaId(rs.getInt("MEDIA_ID"));
					media.setAudience(rs.getInt("AUDIENCE"));
					mList.add(media);
				} while (rs.next());
			}
			if (rs != null)
				rs.close();
			if (cstmt != null)
				cstmt.close();
			DBclose.close(conn);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mList;
	}
	@Override
	public List<Media> printMediaAudience() {
		List<Media> mList = new ArrayList<Media>();
		conn = dBconn.getConnection();
		String query = "{ call sp_media_selectbyview(?) }";
		ResultSet rs = null;

		try {
			cstmt = conn.prepareCall(query);
			cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
			cstmt.executeUpdate();
			rs = (ResultSet) cstmt.getObject(1);
			if (!rs.next()) { // 레코드가 없다면
				mList = null;
			} else { // 있다면
				do {
					Media media = new Media(rs.getString("MEDIA_NM"), rs.getString("NATION"),
							rs.getString("MD_CATEGORY"), rs.getString("GENRE"), rs.getString("RATING"));
					media.setDate(rs.getString("M_DATE"));
					media.setMediaId(rs.getInt("MEDIA_ID"));
					media.setAudience(rs.getInt("AUDIENCE"));
					mList.add(media);
				} while (rs.next());
			}
			if (rs != null)
				rs.close();
			if (cstmt != null)
				cstmt.close();
			DBclose.close(conn);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mList;
	}
	@Override
	public boolean createMedia(Media media) {
		conn = dBconn.getConnection();

		String query = "{ call sp_media_insert(?,?,?,?,?,?,?)}";
		boolean flag = false;

		try {
			cstmt = conn.prepareCall(query);
			cstmt.setString(1, media.getMediaName());
			cstmt.setString(2, media.getNation());
			cstmt.setString(3, media.getCategory());
			cstmt.setString(4, media.getGenre());
			cstmt.setString(5, media.getDate());
			cstmt.setString(6, media.getRating());
			cstmt.setInt(7, media.getAudience());

			int cnt = cstmt.executeUpdate();

			if (cnt == 1) {
				flag = true;
				conn.commit();
			}
			if (cstmt != null)
				cstmt.close();
			DBclose.close(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;

	}
	@Override
	public boolean deleteMedia(Media m) {
		conn = dBconn.getConnection();
		String query = "{ call sp_media_delete(?) }";
		boolean flag = false;
		try {
			cstmt = conn.prepareCall(query);
			cstmt.setLong(1, m.getMediaId());
			int cnt = cstmt.executeUpdate();

			if (cnt == 1) {
				flag = true;
				conn.commit();
			}
			if (cstmt != null)
				cstmt.close();
			DBclose.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	public boolean updateMedia(Media media) {

		conn = dBconn.getConnection();
		boolean flag = false;
		String query = "{ call sp_media_update(?,?,?,?,?,?,?) }"; // 메소드가
		// 하나면
		// 된다는게
		// 장점
		try {
			cstmt = conn.prepareCall(query);

			cstmt.setInt(7, media.getMediaId());
			cstmt.setString(1, media.getMediaName());
			cstmt.setString(2, media.getNation());
			cstmt.setString(3, media.getCategory());
			cstmt.setString(4, media.getGenre());
			cstmt.setString(5, media.getRating());
			cstmt.setString(6, media.getDate());
			int cnt = cstmt.executeUpdate();

			if (cnt == 1) {
				flag = true;
				conn.commit();
			}
			if (cstmt != null)
				cstmt.close();
			DBclose.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	public Media findMediaWithID(int no) {
		Media media = null;
		conn = dBconn.getConnection();
		String query = "{ call sp_media_selectMediaId(?,?) }";
		ResultSet rs = null;

		try {
			cstmt = conn.prepareCall(query);
			cstmt.setInt(1, no);
			cstmt.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
			cstmt.executeUpdate();
			rs = (ResultSet) cstmt.getObject(2);
			if (rs.next()) {
				media = new Media(rs.getString("MEDIA_NM"), rs.getString("NATION"), rs.getString("MD_CATEGORY"),
						rs.getString("GENRE"), rs.getString("RATING"));
				media.setDate(rs.getString("M_DATE"));
				media.setMediaId(rs.getInt("MEDIA_ID"));
				media.setAudience(rs.getInt("AUDIENCE"));

			}
			if (rs != null)
				rs.close();
			if (cstmt != null)
				cstmt.close();
			DBclose.close(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return media;
	}
	@Override
	public List<Media> findMediaWithName(String name) {
		List<Media> mList = new ArrayList<Media>();
		conn = dBconn.getConnection();
		String query = "{ call sp_media_selectMediaName(?,?) }";
		ResultSet rs = null;

		try {
			cstmt = conn.prepareCall(query);
			cstmt.setString(1, "%" + name + "%");
			cstmt.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
			cstmt.executeUpdate();
			rs = (ResultSet) cstmt.getObject(2);
			if (!rs.next()) { // 레코드가 없다면
				mList = null;
			} else { // 있다면
				do {
					Media media = new Media(rs.getString("MEDIA_NM"), rs.getString("NATION"),
							rs.getString("MD_CATEGORY"), rs.getString("GENRE"), rs.getString("RATING"));
					media.setDate(rs.getString("M_DATE"));
					media.setMediaId(rs.getInt("MEDIA_ID"));
					media.setAudience(rs.getInt("AUDIENCE"));
					mList.add(media);
				} while (rs.next());
			}

			if (rs != null)
				rs.close();
			if (cstmt != null)
				cstmt.close();
			DBclose.close(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mList;
	}
	@Override
	public List<Media> findMediaWithCategory(String category) {
		List<Media> mList = new ArrayList<Media>();
		conn = dBconn.getConnection();
		String query = "{ call sp_media_selectMediaCategory(?,?) }";
		ResultSet rs = null;
		try {
			cstmt = conn.prepareCall(query);
			cstmt.setString(1,category);
			//cstmt.setString(1, "%" + category + "%");
			cstmt.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
			cstmt.executeUpdate();
			rs = (ResultSet) cstmt.getObject(2);
			if (!rs.next()) { // 레코드가 없다면
				mList = null;
			} else { // 있다면
				do {
					Media media = new Media(rs.getString("MEDIA_NM"), rs.getString("NATION"),
							rs.getString("MD_CATEGORY"), rs.getString("GENRE"), rs.getString("RATING"));
					media.setDate(rs.getString("M_DATE"));
					media.setMediaId(rs.getInt("MEDIA_ID"));
					media.setAudience(rs.getInt("AUDIENCE"));
					mList.add(media);
				} while (rs.next());
			}
			if (rs != null)
				rs.close();
			if (cstmt != null)
				cstmt.close();
			DBclose.close(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mList;
	}
	@Override
	public List<Media> findMediaWithGenre(String genre) {
		List<Media> mList = new ArrayList<Media>();
		conn = dBconn.getConnection();
		String query = "{ call sp_media_selectMediaGenre(?,?) }";

		ResultSet rs = null;

		try {
			cstmt = conn.prepareCall(query);
			cstmt.setString(1, genre);
			cstmt.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
			cstmt.executeUpdate();
			rs = (ResultSet) cstmt.getObject(2);
			if (!rs.next()) { // 레코드가 없다면
				mList = null;
			} else { // 있다면
				do {
					Media media = new Media(rs.getString("MEDIA_NM"), rs.getString("NATION"),
							rs.getString("MD_CATEGORY"), rs.getString("GENRE"), rs.getString("RATING"));
					media.setDate(rs.getString("M_DATE"));
					media.setMediaId(rs.getInt("MEDIA_ID"));
					media.setAudience(rs.getInt("AUDIENCE"));
					mList.add(media);
				} while (rs.next());
			}
			if (rs != null)
				rs.close();
			if (cstmt != null)
				cstmt.close();
			DBclose.close(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mList;
	}
	@Override
	public List<Media> findMediaWithRating(String rating) {
		List<Media> mList = new ArrayList<Media>();
		conn = dBconn.getConnection();
		String query = "{ call sp_media_selectMediaRating(?,?) }";

		ResultSet rs = null;

		try {
			cstmt = conn.prepareCall(query);
			cstmt.setString(1, rating);
			cstmt.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
			cstmt.executeUpdate();
			rs = (ResultSet) cstmt.getObject(2);
			if (!rs.next()) { // 레코드가 없다면
				mList = null;
			} else { // 있다면
				do {
					Media media = new Media(rs.getString("MEDIA_NM"), rs.getString("NATION"),
							rs.getString("MD_CATEGORY"), rs.getString("GENRE"), rs.getString("RATING"));
					media.setDate(rs.getString("M_DATE"));
					media.setMediaId(rs.getInt("MEDIA_ID"));
					media.setAudience(rs.getInt("AUDIENCE"));
					mList.add(media);
				} while (rs.next());
			}
			if (rs != null)
				rs.close();
			if (cstmt != null)
				cstmt.close();
			DBclose.close(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mList;
	}
	@Override
	public List<Media> findMediaWithNation(String nation) {
		List<Media> mList = new ArrayList<Media>();
		conn = dBconn.getConnection();
		String query = "{ call sp_media_selectMediaNation(?,?) }";

		ResultSet rs = null;

		try {
			cstmt = conn.prepareCall(query);
			cstmt.setString(1, nation);
			cstmt.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
			cstmt.executeUpdate();
			rs = (ResultSet) cstmt.getObject(2);
			if (!rs.next()) { // 레코드가 없다면
				mList = null;
			} else { // 있다면
				do {
					Media media = new Media(rs.getString("MEDIA_NM"), rs.getString("NATION"),
							rs.getString("MD_CATEGORY"), rs.getString("GENRE"), rs.getString("RATING"));
					media.setDate(rs.getString("M_DATE"));
					media.setMediaId(rs.getInt("MEDIA_ID"));
					media.setAudience(rs.getInt("AUDIENCE"));
					mList.add(media);
				} while (rs.next());
			}
			if (rs != null)
				rs.close();
			if (psmt1 != null)
				psmt1.close();
			DBclose.close(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mList;
	}
	@Override
	public List<Media> MyMediaList(String loginId) {
		List<Media> mList = new ArrayList<Media>();
		conn = dBconn.getConnection();
		ResultSet rs = null;
		String query = "{ call sp_Mymedia_selectuser(?,?) }";

		try {
			cstmt = conn.prepareCall(query);
			cstmt.setString(1, loginId);
			cstmt.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
			cstmt.executeUpdate();
			rs = (ResultSet) cstmt.getObject(2);
			if (!rs.next()) { // 레코드가 없다면
				mList = null;
			} else { // 있다면
				do {
				Media media = new Media(rs.getString("MEDIA_NM"), rs.getString("NATION"), rs.getString("MD_CATEGORY"),
						rs.getString("GENRE"), rs.getString("RATING"));
				media.setDate(rs.getString("M_DATE"));
				media.setMediaId(rs.getInt("MEDIA_ID"));
				media.setAudience(rs.getInt("AUDIENCE"));
				mList.add(media);
			}while (rs.next());
			}
			if (rs != null)
				rs.close();
			if (cstmt != null)
				cstmt.close();
			DBclose.close(conn);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mList;
	}
	@Override
	public void watchMedia(String loginId, Media media) {
		conn = dBconn.getConnection();
		String query =  "{ call sp_mymedia_insert(?,?)}";
		try {
			cstmt = conn.prepareCall(query);
			cstmt.setString(1, loginId);
			cstmt.setInt(2, media.getMediaId());

			int cnt = cstmt.executeUpdate();

			if (cnt == 1) {
				conn.commit();
			}
			if (cstmt != null)
				cstmt.close();
			DBclose.close(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Override
	public boolean joinMembership(User user) {

		conn = dBconn.getConnection();
		boolean flag = false;
		String query = "{ call sp_user_updateMembership(?,?) }";
		try {
			cstmt = conn.prepareCall(query);

			cstmt.setString(2, user.getUserID());
			cstmt.setString(1, user.getLevel());
			int cnt = cstmt.executeUpdate();

			if (cnt == 1) {
				flag = true;
				conn.commit();
			}
			if (cstmt != null)
				cstmt.close();
			DBclose.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	public boolean updateMediaAudience(Media media) {

		conn = dBconn.getConnection();
		boolean flag = false;
		String query = "{ call sp_media_updateView(?,?) }";				 // 메소드가
																			// 하나면																					// 장점
		try {
			cstmt = conn.prepareCall(query);

			cstmt.setInt(2, media.getMediaId());
			cstmt.setInt(1, media.getAudience());
			int cnt = cstmt.executeUpdate();

			if (cnt == 1) {
				flag = true;
				conn.commit();
			}
			if (cstmt != null)
				cstmt.close();
			DBclose.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

}
