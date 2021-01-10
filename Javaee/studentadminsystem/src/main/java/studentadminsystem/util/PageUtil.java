package studentadminsystem.util;

public class PageUtil {
    private int currentPage;
    private long totalRecords;
    private int pageSize;
    private int maxPage;

    public PageUtil(long totalRecords, int pageSize,int currentPage) {

        this.totalRecords = totalRecords;
        this.pageSize = pageSize;
        this.maxPage=(int)(totalRecords%pageSize==0?(totalRecords/pageSize):(totalRecords/pageSize+1));
        setCurrentPage(currentPage);
    }
    /*
    * 是否为第一页？
    *
    * */
    public boolean isFirstPage(){
        return currentPage==1;
    }
    /*
    * 是否为最后一页？
    * */
    public boolean isMaxPage(){
        return currentPage==maxPage;
    }
    /*
    * 获取下一页
    * */
    public int nextPage(){
        if(currentPage==maxPage){
            System.out.println("已经是最后一页");
            return currentPage;
        }
        else {
            return currentPage++;
        }
        //return currentPage==maxPage?maxPage:currentPage++;
    }
    /*
    * 获取上一页
    * */
    public int prevPage(){
        if(currentPage==1){
            System.out.println("已经是第一页");
            return currentPage;
        }
        else {
            return currentPage--;
        }
    }
    public int getMaxPage(){
        return maxPage;
    }

    public void setCurrentPage(int currentPage){
        if(currentPage>maxPage){
            this.currentPage=maxPage;
        }else if(currentPage<1){
            this.currentPage=1;
        }else {
            this.currentPage=currentPage;
        }

    }

    public  int getCurrentPage(){
        return this.currentPage;
    }



}

