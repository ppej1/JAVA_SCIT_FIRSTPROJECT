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


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
	private Connection conn;
	private Properties info;
	public DBConnection() {
		String path ="config/dbinfo.properties";
		File file= new File(path);
		info = new Properties();
		try {
			info.load(new FileInputStream(file));
			//System.out.println(info.getProperty("db.driverClass") +" 연결성공");
		} catch (FileNotFoundException e) {
			System.out.println("[ File Not Found ]");
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	
	public Connection getConnection() {
		//Step 2 : Driver loading
		try {
			Class.forName(this.info.getProperty("db.driverClass"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Step 3 : Connection
		try {
			this.conn = DriverManager.getConnection(this.info.getProperty("db.url"),
													this.info.getProperty("db.username"),
													this.info.getProperty("db.password"));
			//System.out.println("[ Connection success ]");
		} catch (SQLException e) {
			System.out.println("[ Connection Failure ]");
		}
	
		return conn;
	}
	
	
	
	
}
