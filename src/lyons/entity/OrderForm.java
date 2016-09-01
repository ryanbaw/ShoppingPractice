package lyons.entity;

import java.util.Date;

/**
 * 
 * ¶©µ¥ÊµÌåÀà
 * 
 * @author  Lyons(zhanglei)
 * @version  [°æ±¾ºÅ, 2016 6 3]
 * @since  [Ó¦ÓÃ/°æ±¾]
 */
public class OrderForm
{
    private int id = 0;
    private String username = "";
    private Date orderDate = null;
    private String commodity_name = "";
    private double commodity_price = 0.00;
    private int sum = 0;                    //×ÜÊý
    
    public OrderForm(){}
    
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public String getUsername()
    {
        return username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    public Date getOrderDate()
    {
        return orderDate;
    }
    public void setOrderDate(Date orderDate)
    {
        this.orderDate = orderDate;
    }
    public String getCommodity_name()
    {
        return commodity_name;
    }
    public void setCommodity_name(String commodity_name)
    {
        this.commodity_name = commodity_name;
    }
    public double getCommodity_price()
    {
        return commodity_price;
    }
    public void setCommodity_price(double commodity_price)
    {
        this.commodity_price = commodity_price;
    }
    public int getSum()
    {
        return sum;
    }
    public void setSum(int sum)
    {
        this.sum = sum;
    }
 
    
}
