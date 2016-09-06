package lyons.db;
/**
 * 链接数据库
 * @author Lyons
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConn
{
	public static Connection getConn()
	{
		Connection conn = null;

		String user 	= "root";
		String passwd	= "justdoit";
		String url = "jdbc:mysql://127.0.0.1:3306/SUPERMALL?useUnicode=true&characterEncoding=utf8";
			// url = "jdbc:mysql://127.0.0.1:3306/test?user=root&password=justdoit&useUnicode=true&&characterEncoding=utf-8&autoReconnect = true";

		try {
			Class.forName("com.mysql.jdbc.Driver"); //加载mysq驱动
		} catch (ClassNotFoundException e) {
			System.out.println("驱动加载错误");
			e.printStackTrace();//打印出错详细信息
		}

		try {
			conn = DriverManager.getConnection(url,user,passwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}
}
