package lyons.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.rowset.CachedRowSetImpl;

import lyons.db.DbClose;
import lyons.db.DbConn;
import lyons.entity.Goods;
import lyons.entity.Login;

public class GoodsDao extends HttpServlet
{
	/**
     * serialVersionUID
     */
    private static final long serialVersionUID = 135785434567L;
 
	/**
	 * Constructor of the object.
	 */
	public GoodsDao()
	{
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy()
	{
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		response.setContentType("text/html;chartset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String value = "";
		value = request.getParameter("key");
		int key = Integer.parseInt(value);
		System.out.println("¼ì²âÊÇ·ñÓÐkey:"+key);
		
		String keyWord = "";
		keyWord = request.getParameter("keyWord");
		System.out.println(keyWord);
		queryGoods(request, response, key,keyWord);
	}

	public void init() throws ServletException
	{
		// Put your code here
	}
	
	/**
	 * ÉÌÆ·²éÑ¯
	 * @param request
	 * @param response
	 * @param key ²éÑ¯µÄÌõ¼þ/int:4(¼òµ¥²éÑ¯)
	 * @return ÉÌÆ·ÐÅÏ¢Êý×é
	 * @throws ServletException
	 * @throws IOException
	 */
	public void queryGoods(HttpServletRequest request, HttpServletResponse response,int key,String keyWord)
			throws ServletException, IOException
	{
	    response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        CachedRowSetImpl rowSet = null;//ÐÐ¼¯¶ÔÏó
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Goods goods = null;
		Login username = null;
//		OrderForm orderForm = null;
		
		HttpSession session = request.getSession(true);
		username = (Login)session.getAttribute("loginBean");
		goods = (Goods)session.getAttribute("goods");
//		orderForm = (OrderForm)session.getAttribute("orderForm");
//		ArrayList<Goods> goodsList = new ArrayList<Goods>();
		if (goods==null)
		{
			goods = new Goods();
			session.setAttribute("goods", goods);
		}
		if (username==null)
		{
		    username = new Login();
		    session.setAttribute("username", username);
		}
//		if (orderForm==null)
//		{
//		    orderForm = new OrderForm();
//		    session.setAttribute("orderForm", orderForm);
//		}
		  //ÅÐ¶ÏÓÃ»§ÊÇ·ñµÇÂ½
		  String user = "";
          user = username.getUsername();//µÇÂ½ÕßµÄÓÃ»§Ãû
          System.out.println("ÎÒÊÇÓÃ»§£º"+user);
          if (user.equals("userNull"))
          {
              out.print("<br>");
              out.print("<center><font color=#008B8B> µÇÂ½Ö®ºó²ÅÄÜ¿´¶©µ¥Å¶  </font>");
              out.print("<a href=/lyons.eaby/jsp/join/login.jsp><font color=red size=6>µÇÂ½</font></a></center>");
              return;
          }
		
		conn = DbConn.getConn();	

		switch (key)
		{
			case 1:
					/*//	key=1ÉÌÆ· ÊýÁ¿ ÉýÐò²éÑ¯
					String sqlGnum = "SELECT * FROM GOODS ORDER BY GNUM ASC";
					try
					{
						pstmt = conn.prepareStatement(sqlGnum);
						rs = pstmt.executeQuery();
						while (rs.next())
						{
							
						}
					} catch (SQLException e)
					{
						e.printStackTrace();
					}finally
							{
								DbClose.allClose(pstmt, rs, conn);
							}*/
				break;
			case 2:
        			  //key=2 °´ÕÕ¹Ø¼ü×Ö²éÑ¯ ÉÌÆ·ÐÅÏ¢
                      
                        String sqlShowGoodsByKey =  
                        "select * from commodity WHERE commodity_name LIKE '%'||?||'%'";
                        try
                        {
                            pstmt = conn.prepareStatement(sqlShowGoodsByKey);
                            pstmt.setString(1, keyWord);
                            rs = pstmt.executeQuery();
                            System.out.println("--2²é¿´¶©µ¥Ö´ÐÐÊý¾Ý¿â²Ù×÷--");
                            if(rs.next())
                            {
                                rs = pstmt.executeQuery();//ÖØÐÂ²éÑ¯µÄÔ­ÒòÊÇrs.nextÊ±¹â±êÆ«ÒÆºó£¬¶ªµô¼ÇÂ¼¡£
                                rowSet = new CachedRowSetImpl();
                                rowSet.populate(rs); 
                                goods.setRowSet(rowSet);
                                System.out.println("2ÒÑ¾­´ÓÊý¾Ý¿âÖÐ»ñÈ¡µ½Öµ£¬²¢Èû½øÐÐ¼¯");
                                request.getRequestDispatcher("/jsp/browse/showGoods.jsp").forward(request, response);
                            }else 
                                {
                                    out.print("<br><br><br><center>");
                                    out.print("<font color=green> Ç×,²éÑ¯³ö´íÀ².¸ü»»¹Ø¼ü×ÖÔÙ´Î </font>");
                                    out.print("<a href=/lyons.eaby/jsp/browse/searchByKeyWord.jsp><font color=red size=6>²éÑ¯</font></a>");
                                    out.print("</center>");     
                                }
                        } catch (SQLException e)
                        {
                            System.out.println("key=3²é¿´¶©µ¥Òì³££º"+e);
                            
                        }finally
                                {
                                    System.out.println("²é¿´¶©µ¥Ö´ÐÐ¹Ø±ÕÁ÷");
                                    DbClose.allClose(pstmt, rs, conn);
                                }
        				break;
			case 3:
                    //key=3 °´ÕÕµÇÂ¼ÈË²éÑ¯¶©µ¥ ÉÌÆ·Ãû×Ö+ÊýÁ¿
			      
                    String sqlOrder= 
                    "select commodity_name,sum(sum) from orderform where username=? group by commodity_name having sum(sum)>0";
                    try
                    {
                        pstmt = conn.prepareStatement(sqlOrder);
                        pstmt.setString(1, user);
                        rs = pstmt.executeQuery();
                        System.out.println("--²é¿´¶©µ¥Ö´ÐÐÊý¾Ý¿â²Ù×÷--");
                        if(rs.next())
                        {
                            rs = pstmt.executeQuery();//ÖØÐÂ²éÑ¯µÄÔ­ÒòÊÇrs.nextÊ±¹â±êÆ«ÒÆºó£¬¶ªµô¼ÇÂ¼¡£
                            rowSet = new CachedRowSetImpl();
                            rowSet.populate(rs); 
                            goods.setRowSet(rowSet);
                            System.out.println("3ÒÑ¾­´ÓÊý¾Ý¿âÖÐ»ñÈ¡µ½Öµ£¬²¢Èû½øÐÐ¼¯");
                            request.getRequestDispatcher("/jsp/order/lookOrderForm.jsp").forward(request, response);
                        }else 
                            {
                                out.print("<br><br><br><center>");
                                out.print("<font color=green> Ç×,¶©µ¥ÊÇ¿ÕµÄÄØ </font>");
                                out.print("<a href=/lyons.eaby/lyons.dao/GoodsDao?key=4><font color=red size=6>Go Shopping</font></a>");
                                out.print("</center>");		
                            }
                    } catch (SQLException e)
                    {
                        System.out.println("key=3²é¿´¶©µ¥Òì³££º"+e);
                        
                    }finally
                            {
                                System.out.println("²é¿´¶©µ¥Ö´ÐÐ¹Ø±ÕÁ÷");
                                DbClose.allClose(pstmt, rs, conn);
                            }
                    break;
			case 4:
			        StringBuffer url = request.getRequestURL();
			        System.out.println("4324234=========="+url.toString());
					//key=4 ä¯ÀÀÉÌÆ·
					String sqlList= "select * from commodity";
					try
					{
						pstmt = conn.prepareStatement(sqlList);
						rs = pstmt.executeQuery();
						System.out.println("--4ä¯ÀÀÉÌÆ·Ö´ÐÐÊý¾Ý¿â²Ù×÷--");
						if(rs.next())
						{
						    rs = pstmt.executeQuery();//ÖØÐÂ²éÑ¯µÄÔ­ÒòÊÇrs.nextÊ±¹â±êÆ«ÒÆºó£¬¶ªµô¼ÇÂ¼¡£
							rowSet = new CachedRowSetImpl();
							rowSet.populate(rs);
							goods.setRowSet(rowSet);
							System.out.println("4ä¯ÀÀÉÌÆ·ÒÑ¾­´ÓÊý¾Ý¿âÖÐ»ñÈ¡µ½Öµ£¬²¢Èû½øÐÐ¼¯");
							request.getRequestDispatcher("/jsp/browse/showGoods.jsp").forward(request, response);
						}else 
                        {
                                out.print("<br><br><br><center>");
                                out.print("<font color=green> Ç×,Âô¼Ò»¹Ã»ÉÏ»õÄØ </font>");
                                out.print("<a href=/lyons.eaby/lyons.dao/GoodsDao?key=4><font color=red size=6>½øÈëÊ×Ò³</font></a>");
                                out.print("</center>");     
                            }
					} catch (SQLException e)
					{
						e.printStackTrace();
						response.sendRedirect("/lyons.eaby/jsp/browse/showGoods.jsp");
					}finally
							{
								DbClose.allClose(pstmt, rs, conn);
							}
					break;
			default:
				break;
		}
	}

}
