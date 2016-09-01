package lyons.entity;


import com.sun.rowset.CachedRowSetImpl;

/**
 * ÉÌÆ·ÊµÌåÀà
 * @author lyons(zhanglei)
 *
 */
public class Goods 
{
	
	CachedRowSetImpl rowSet = null; //±íÖÐËùÓÐµÄÐÐ¼¯¶ÔÏó
	
	private int pageSize = 5;	//Ã¿Ò³ÏÔÊ¾¶àÉÙÌõ¼ÇÂ¼(Ä¬ÈÏÎª5Ìõ)
	private int currentPage = 1;//µ±Ç°Ò³Êý
	private int totalRecord = 1;//×Ü¼ÇÂ¼Êý
	private int totalPage = 1;	 //×ÜÒ³Êý
	
	public Goods(){}
	
	public Goods(CachedRowSetImpl rowSet, int pageSize, int currentPage,
			int totalPRecord, int totalPage)
	{
		this.rowSet = rowSet;
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.totalRecord = totalPRecord;
		this.totalPage = totalPage;
	}

    public CachedRowSetImpl getRowSet()
    {
        return rowSet;
    }

    public void setRowSet(CachedRowSetImpl rowSet)
    {
        this.rowSet = rowSet;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public int getCurrentPage()
    {
        return currentPage;
    }

    public void setCurrentPage(int currentPage)
    {
        this.currentPage = currentPage;
    }

    public int getTotalRecord()
    {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord)
    {
        this.totalRecord = totalRecord;
    }

    public int getTotalPage()
    {
        return totalPage;
    }

    public void setTotalPage(int totalPage)
    {
        this.totalPage = totalPage;
    }

	
}
