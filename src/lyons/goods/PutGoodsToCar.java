package lyons.goods;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lyons.entity.Login;

public class PutGoodsToCar extends HttpServlet
{
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 97434567L;

    /**
     * Constructor of the object.
     */
    public PutGoodsToCar()
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
        
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String goods = null;
        goods = request.getParameter("GoodsCar");
        
        if (goods==null)
        {
            response.sendRedirect("/lyons.eaby/index.jsp");
        }else 
            {
                String[] details = null;
                details = goods.split(",");//Êý×éÄÚ´¢´æµÄÐÅÏ¢ÓëÊý¾Ý¿âÒ»ÖÂ¡£
                
                //½«ÎïÆ·ÐÅÏ¢·Å½øÄ£ÐÍÖÐ
                HttpSession session = request.getSession(true);
                Login loginBean = (Login)session.getAttribute("loginBean");
                LinkedList<String> car = null;
                car = loginBean.getCar();
               /* if (request.getAttribute("clear")!=null) //¹ºÎï³µÍê³É½áËã£¬Çå¿ÕÊý¾Ý£¡
                {
                   car = null;
                }*/
                car.add(goods);
                loginBean.setCar(car);
                
                backNews(request, response, details[1]);//²ÎÊýÈý£ºÉÌÆ·ÂðÃû³Æ
            }
     
    }
    
    
    /**
     * 
     * ·µ»ØÓÃ»§ÏûÏ¢
     * Ìí¼Ó¹ºÎï³µ³É¹¦ºó£¬·µ»ØÌáÊ¾²Ù×÷ÐÅÏ¢
     * @param request
     * @param response
     * @param goodsName
     * @throws IOException
     */
    private void backNews(HttpServletRequest request, HttpServletResponse response, String goodsName) throws IOException
    {
        
        PrintWriter out = response.getWriter();
        out.print("<br><br><br>");
        out.print("<center><font size=5 color=red><B>"+goodsName+"</B></font>&nbsp;ÒÑ³É¹¦Ìí¼Ó¹ºÎï³µ");
        out.print("<br><br><br>");
        out.print("<a href=/lyons.eaby/jsp/browse/showGoods.jsp>·µ»Ø¼ÌÐø¹ºÎï</a>");
        out.print("&nbsp;or&nbsp;");
        out.print("<a href=/lyons.eaby/jsp/shoppingCar/lookShoppingCar.jsp>²é¿´¹ºÎï³µ</a></center>");
        
    }

    public void init()
        throws ServletException
    {
        // Put your code here
    }
    
}
