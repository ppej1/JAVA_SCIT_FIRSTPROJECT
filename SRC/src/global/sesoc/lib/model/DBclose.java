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


import java.sql.Connection;
import java.sql.SQLException;

public class DBclose {
	public static void close(Connection conn) {
		if(conn != null)
		try {
			if (conn != null) {
				conn.close();
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
}
