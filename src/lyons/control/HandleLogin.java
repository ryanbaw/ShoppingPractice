package lyons.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import lyons.db.DbClose;
import lyons.db.DbConn;
import lyons.entity.Login;

/**
 * µÇÂ½´¦Àí
 * @author Lyons(zhanglei)
 *
 */

public class HandleLogin extends HttpServlet 
{
	private static final long serialVersionUID = 1L; //ÉèÖÃÐòÁÐºÅ
	public HandleLogin()
	{
		super();
	}
	public void init() throws ServletException
	{
	}
	public void destroy()
	{
		super.destroy(); // Just puts "destroy" string in log
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");//servletÖÐÒ²Òª´ËÏî£¬·ñÔòÈ¡ÖµÂÒÂë
		String username = "";
		String userpass = "";
		String cookies  = "";
		username = request.getParameter("username");
		userpass = request.getParameter("userpass");
		cookies = request.getParameter("isCookie");
		handleCookies(request,response,username,userpass,cookies);//´¦ÀícookiesÐÅÏ¢
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		conn = DbConn.getConn();
		
		String sql = "select * from vip where username=? and userpass=?";
		try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, userpass);
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				//µÇÂ½³É¹¦
				success(request,response,username);
				request.getRequestDispatcher("/jsp/join/landing.jsp").forward(request, response);
			}else 
				{
					String backNews = "ÓÃ»§Ãû»òÕßÃÜÂë´íÎó";
					fail(request, response, backNews);
				}
		} catch (SQLException e)
		{
			String backNews = "µÇÂ¼Ê§°Ü"+e;
			fail(request, response, backNews);
		}finally
			{
				DbClose.allClose(pstmt, rs, conn);
			}
	}
	
	/**
	 * ´¦ÀíÓÃ»§cookiesÐÅÏ¢
	 * @param request
	 * @param response
	 * @param username
	 * @param userpass
	 */
	public void handleCookies(HttpServletRequest request,HttpServletResponse response, 
			String name,String pass,String isCookie)throws ServletException, IOException
	{
		if ("isCookie".equals(isCookie))//ÓÃ»§Ñ¡ÔñÁË¼Ç×¡ÃÜÂë
		{
			String username = URLEncoder.encode(name,"UTF-8");//±àÂë£¬½â¾öcookieÎÞ·¨±£´æ×Ö·û´®µÄÎÊÌâ
			String userpass = URLEncoder.encode(pass,"UTF-8");
			
			Cookie nameCookie = new Cookie("username",username );//ÉèÖÃÓëµÇÂ½Ê±µÄname¶ÔÓ¦µÄ¼üÖµ¶Ô
			Cookie passCookie = new Cookie("userpass",userpass );
			
			nameCookie.setPath("/");//ÉèÖÃµÄcookieµÄ´æ´¢Â·¾¶ºÜÖØÒª£¬²»È»È¡²»µ½Öµ
			passCookie.setPath("/");
			nameCookie.setMaxAge(864000); //ÉèÖÃÉúÃüÆÚÏÞÊ®Ìì µ¥Î»Ãë
			passCookie.setMaxAge(864000);
			response.addCookie(nameCookie); //±£´æÐÅÏ¢
			response.addCookie(passCookie); 
		}else 
			{
			//ÓÃ»§Î´Ñ¡Ôñ¼Ç×¡ÃÜÂë£¬É¾³ýä¯ÀÀÆ÷ÖÐ¿ÉÄÜ´æÔÚµÄcookie
				Cookie[] cookies = null;
				cookies = request.getCookies();
				if (cookies!=null&&cookies.length>0)
				{
					for (Cookie c : cookies)
					{
						if ("username".equals(c.getName())||"userpass".equals(c.getName()))
						{
							c.setMaxAge(0);//ÉèÖÃcookieÊ§Ð§
							c.setPath("/");//Îñ±ØÉèÖÃ
							response.addCookie(c);
						}
					}
				}
			}
	}
	
	/**
	 * µÇÂ½³É¹¦£¬´¢´æÓÃ»§ÐÅÏ¢
	 */
	public void success(HttpServletRequest request,
			HttpServletResponse response, String username)
	{
		Login loginBean = null;
		HttpSession session = request.getSession(true);
		
		try
		{
			loginBean = (Login) session.getAttribute("loginBean");//»ñÈ¡sessionÖÐ¿ÉÄÜ´æÔÚµÄloginBean¶ÔÏó
			if (loginBean == null)
			{
				loginBean = new Login();
				session.setAttribute("loginBean", loginBean);//×¢Òâjsp»ñÈ¡Ê±ÐèÒªÓÃµ½¸ÃnameµÄÊôÐÔÃû×Ö
				session.setMaxInactiveInterval(600);//Ê®·ÖÖÓµÄ´æ»îÆÚ µ¥Î»£ºÃë
				loginBean = (Login) session.getAttribute("loginBean");
			}
			
			String name = loginBean.getUsername();
			if (username.equals(name))
			{
				loginBean.setBackNews(username + "ÄúÒÑµÇÂ½£¬ÎÞÐèÔÙ´ÎµÇÂ¼");
				loginBean.setUsername(username);
			} else
				{
					loginBean.setBackNews(username + "µÇÂ½³É¹¦");
					loginBean.setUsername(username);
				}
		} catch (Exception e)
		{
			String backNews = "µÇÂ¼Ê§°Ü"+e;
			fail(request, response, backNews);
		}
	
	}
	
	/**
	 * µÇÂ½Ê§°Ü
	 */
	public void fail(HttpServletRequest request,
			HttpServletResponse response,String backNews)
	{
		try
		{
			PrintWriter out = response.getWriter();
			out.print(backNews+"<br>");
			out.print("·µ»Ø"+"<a href=/lyons.eaby/jsp/join/login.jsp>µÇÂ½½çÃæ</a>");
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
