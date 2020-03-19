package sample;
/*
用于实现对数据库的操作的函数的类
*/
import java.sql.*;

public class BookmanagerConnect {

    private String url=null;
    private String uname=null;
    private String pswd=null;
    private Connection conn;

    public BookmanagerConnect(String url1, String uname1, String pswd1) throws SQLException {  //通过一个构造函数传递参数
        url = url1;
        uname = uname1;
        pswd = pswd1;
    }

    public void StudentConnClose() throws SQLException { //用于关闭连接的函数
        conn.close();
    }

    public  void connect() throws SQLException {  //用于连接数据库的函数
        conn = DriverManager.getConnection(url, uname, pswd);
    }

    public ResultSet getBookmanagerInfo(String bmnum) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select *\n" + "from bookmanager \n"+ " where BMnum = '"+bmnum+"'" );//查询语句
        return rs; //将查询结果返回
    }

    public ResultSet searchbyname(String name) throws SQLException {  //按照书名查找
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select *\n" + "from allbook \n"+ " where Bname = '"+name+"'" );//查询语句
        return rs; //将查询结果返回
    }

    public ResultSet searchbykind(String kind) throws SQLException {  //按照种类查找
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select *\n" + "from allbook \n"+ " where Bkind = '"+kind+"'" );//查询语句
        return rs; //将查询结果返回
    }

    public ResultSet showall() throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select *\n" + "from allbook ");//查询语句
        return rs; //将查询结果返回
    }

    public ResultSet searchbypublisher(String publichser) throws SQLException {   //按照出版社查找
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select *\n" + "from allbook \n"+ " where Bpub = '"+publichser+"'" );//查询语句
        return rs; //将查询结果返回
    }

    public ResultSet searchbyauther(String kind) throws SQLException {  //按照种类查找
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select *\n" + "from allbook \n"+ " where Bauther = '"+kind+"'" );//查询语句
//        while(rs.next()){
//            System.out.println("book:"+rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5)+"\t"+rs.getString(6));
//        }
        return rs; //将查询结果返回
    }

    public int UpdateBookStatueTokejie(String bno) throws SQLException {  //更新图书表中图书的状态
            Statement statement = conn.createStatement();
            int rs = statement.executeUpdate("update allbook\n" +
                    "set Bstatus = '可借'\n" +
                    "where Bno = '"+bno+"'" );//更新语句
            return rs;
        }

    public int Detelebook(String bno,String status) throws SQLException {  //更新图书表中图书的状态
        if (status.equals("可借")){
            Statement statement = conn.createStatement();
            int rs = statement.executeUpdate("delete \n" +
                    "from allbook\n" +
                    "where Bno = '"+bno+"'" );//更新语句
            return rs;
        }else{
            return 0;
        }
    }

    public int Updateindate(String bno,String indate) throws SQLException {
        Statement statement = conn.createStatement();
        int rs = statement.executeUpdate("update Borrow\n" +
                "set BRdateback = '"+indate+"'\n" +
                "where Bno = '"+bno+"'" );
        return rs;
    }

    public int Addbook(String Bno,String Bkind,String Bname,String Bauther,String Bpub,String Bprice,String Bpubdate,String Lname) throws SQLException {
        Statement statement = conn.createStatement();
        int rs = statement.executeUpdate("insert \n" +
                "into allbook\n" +
                "values ('"+Bno+"','"+Bkind+"','"+Bname+"','"+Bauther+"','"+Bpub+"','"+Bprice+"','可借','"+Bpubdate+"','"+Lname+"')" );//查询语句
        return rs;
    }

    public int alterbook(String beforeBno,String Bno,String Bkind,String Bname,String Bauther,String Bpub,String Bprice,String Bpubdate,String Lname) throws SQLException {
        Statement statement = conn.createStatement();
        int rs = statement.executeUpdate("update allbook\n" +
                "set Bno='"+Bno+"',Bkind='"+Bkind+"',Bname='"+Bname+"',Bauther='"+Bauther+"',Bpub='"+Bpub+"',Bprice='"+Bprice+"',Bstatus='可借',Bpubdate='"+Bpubdate+"',Lname='"+Lname+"'\n"+
                "where Bno='"+beforeBno+"'");//查询语句
        return rs;
    }

    public ResultSet showallborrow() throws SQLException {  //查询已经借阅的书
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select allbook.Bno,Bkind,Borrow.Snum,Borrow.Sname,Bname,allbook.Bauther,allbook.Bpub,allbook.Bprice,BRdateout,BRdateback\n" +
                "from allbook,Borrow\n" +
                "where allbook.Bno=Borrow.Bno " );//查询语句
        return rs; //将查询结果返回
    }
//String bno2, String bkind2, String sno,String sname,String bname2,String bauther2,String bpub2,String bprice2,String bdateout,String bdatein
    public ResultSet searchborrowbyname(String name) throws SQLException {//按照书名查询借的书
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select allbook.Bno,Bkind,Borrow.Snum,Borrow.Sname,Bname,allbook.Bauther,allbook.Bpub,allbook.Bprice,BRdateout,BRdateback\n" +
                "from allbook,Borrow\n" +
                "where allbook.Bno=Borrow.Bno and Bname = '"+name+"'" );//查询语句
        return rs; //将查询结果返回
    }

    public ResultSet searchborrowbykind(String bbkind) throws SQLException {//按照书种类查询借的书
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select allbook.Bno,Bkind,Borrow.Snum,Borrow.Sname,Bname,allbook.Bauther,allbook.Bpub,allbook.Bprice,BRdateout,BRdateback\n" +
                "from allbook,Borrow\n" +
                "where allbook.Bno=Borrow.Bno and Bkind = '"+bbkind+"'" );//查询语句
        return rs; //将查询结果返回
    }

    public ResultSet searchborrowbySno(String name) throws SQLException {//按照学号查询借的书
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select allbook.Bno,Bkind,Borrow.Snum,Borrow.Sname,Bname,allbook.Bauther,allbook.Bpub,allbook.Bprice,BRdateout,BRdateback\n" +
                "from allbook,Borrow\n" +
                "where allbook.Bno=Borrow.Bno and Snum = '"+name+"'" );//查询语句
        return rs; //将查询结果返回
    }

    public ResultSet searchborrowbySname(String name) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select allbook.Bno,Bkind,Borrow.Snum,Borrow.Sname,Bname,allbook.Bauther,allbook.Bpub,allbook.Bprice,BRdateout,BRdateback\n" +
                "from allbook,Borrow\n" +
                "where allbook.Bno=Borrow.Bno and Sname = '"+name+"'" );//查询语句
        return rs; //将查询结果返回
    }

    public ResultSet searchborrowbyoutdate(String name) throws SQLException {  //按借出日期查找
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select allbook.Bno,Bkind,Borrow.Snum,Borrow.Sname,Bname,allbook.Bauther,allbook.Bpub,allbook.Bprice,BRdateout,BRdateback\n" +
                "from allbook,Borrow\n" +
                "where allbook.Bno=Borrow.Bno and BRdateout = '"+name+"'" );//查询语句
        return rs; //将查询结果返回
    }

    public ResultSet searchborrowbyindate(String name) throws SQLException { //按应还日期查找
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select allbook.Bno,Bkind,Borrow.Snum,Borrow.Sname,Bname,allbook.Bauther,allbook.Bpub,allbook.Bprice,BRdateout,BRdateback\n" +
                "from allbook,Borrow\n" +
                "where allbook.Bno=Borrow.Bno and BRdateback = '"+name+"'" );//查询语句
        return rs; //将查询结果返回
    }

    public ResultSet searchborrowbyauther(String name) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select allbook.Bno,Bkind,Borrow.Snum,Borrow.Sname,Bname,allbook.Bauther,allbook.Bpub,allbook.Bprice,BRdateout,BRdateback\n" +
                "from allbook,Borrow\n" +
                "where allbook.Bno=Borrow.Bno and allbook.Bauther = '"+name+"'" );//查询语句
        return rs; //将查询结果返回
    }

    public int Deteleborrow(String bno) throws SQLException {
        Statement statement = conn.createStatement();
        int rs = statement.executeUpdate("delete \n" +
                "from Borrow\n" +
                "where Bno = '"+bno+"'" );//更新语句
        return rs;
    }


}
