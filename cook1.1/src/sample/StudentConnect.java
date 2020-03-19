package sample;
/*
用于实现对数据库的操作的函数的类
*/
import java.sql.*;

public class StudentConnect {

    private String url=null;
    private String uname=null;
    private String pswd=null;
    private Connection conn;

    public StudentConnect(String url1, String uname1, String pswd1) throws SQLException {  //通过一个构造函数传递参数
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

    public ResultSet getStudentInfo(String snum) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select *\n" + "from student \n"+ " where Snum = '"+snum+"'" );//查询语句
        return rs; //将查询结果返回
    }

    public ResultSet searchbyname(String name) throws SQLException {  //按照书名查找
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select *\n" + "from allbook \n"+ " where Bname = '"+name+"'" );//查询语句
//        while(rs.next()){ //在控制窗口显示查询到的结果
//            System.out.println("book:"+rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5)+"\t"+rs.getString(6));
//        }
        return rs; //将查询结果返回
    }

    public ResultSet searchbykind(String kind) throws SQLException {  //按照种类查找
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select *\n" + "from allbook \n"+ " where Bkind = '"+kind+"'" );//查询语句
        return rs; //将查询结果返回
    }

    public ResultSet searchbypublisher(String publichser) throws SQLException {   //按照出版社查找
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select *\n" + "from allbook \n"+ " where Bpub = '"+publichser+"'" );//查询语句
//        while(rs.next()){
//            System.out.println("book:"+rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5)+"\t"+rs.getString(6));
//        }
        return rs; //将查询结果返回
    }

    public ResultSet searchbyauther(String kind) throws SQLException {  //按照种类查找
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select *\n" + "from allbook \n"+ " where Bauther = '"+kind+"'" );//查询语句
        return rs; //将查询结果返回
    }

    public int UpdateBookStatueTojiechu(String Snum,String bno,String statuebefore,String statueafter) throws SQLException {  //更新图书表中图书的状态
        if (statuebefore.equals("可借")){
            Statement statement = conn.createStatement();
            int rs = statement.executeUpdate("update allbook\n" +
                    "set Bstatus = '"+statueafter+"'\n" +
                    "where Bno = '"+bno+"'" );//更新语句
            return rs;
        }else{
            return 0;
        }
    }

    public int UpdateBookStatueTokejie(String bno) throws SQLException {  //更新图书表中图书的状态
            Statement statement = conn.createStatement();
            int rs = statement.executeUpdate("update allbook\n" +
                    "set Bstatus = '可借'\n" +
                    "where Bno = '"+bno+"'" );//更新语句
            return rs;
        }

    public int Deteleborrow(String Snum,String bno) throws SQLException {  //更新图书表中图书的状态
        Statement statement = conn.createStatement();

        int rs = statement.executeUpdate("delete \n" +
                "from Borrow\n" +
                "where Bno = '"+bno+"'" );//更新语句
        //用于更新借阅数量
        int num = 0;
        ResultSet rs2= statement.executeQuery("select *\n" +
                "from Borrow\n" +
                "where Snum = '"+Snum+"'" );//更新语句
        while (rs2.next()){
            num++;
            System.out.println("num的数值时"+num);
        }
        statement.executeUpdate("update student\n" +
                " set Sborrownum ='"+num+"'\n"+
                "where Snum = '"+Snum+"'" );//更新语句
        return rs;
    }

    public int Updateindate(String bno,String indate) throws SQLException {
        Statement statement = conn.createStatement();
        int rs = statement.executeUpdate("update Borrow\n" +
                "set BRdateback = '"+indate+"'\n" +
                "where Bno = '"+bno+"'" );
        return rs;
    }

    public int InsertBorrow(String Bno,String Snum,String Sname,String Bpub,String Bprice,String BRdateout,String BRdateback,String Bauther) throws SQLException {
        Statement statement = conn.createStatement();
        int rs = statement.executeUpdate("insert \n" +
                "into Borrow\n" +
                "values ('"+Bno+"','"+Snum+"','"+Sname+"','"+Bpub+"','"+Bprice+"','"+BRdateout+"','"+BRdateback+"','"+Bauther+"')" );//查询语句
        //用于更新借出数量表
        int num = 0;
        ResultSet rs2= statement.executeQuery("select *\n" +
                "from Borrow\n" +
                "where Snum = '"+Snum+"'" );//更新语句
        while (rs2.next()){
            num++;
            System.out.println("num的数值是"+num);
        }
        statement.executeUpdate("update student\n" +
                " set Sborrownum ='"+num+"'\n"+
                "where Snum = '"+Snum+"'" );//更新语句

        return rs;
    }

    public ResultSet searchmyborrow(String Snum) throws SQLException {  //查询已经借阅的书
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select allbook.Bno,Bkind,Bname,allbook.Bauther,allbook.Bpub,allbook.Bprice,BRdateout,BRdateback\n" +
                "from allbook,Borrow\n" +
                "where allbook.Bno=Borrow.Bno and Snum = "+Snum );//查询语句
        return rs; //将查询结果返回
    }

    public ResultSet searchbyzonghe(String bookkind,String pubkind,String libkind,String pricelow,String pricehigh,String statuekind) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select *\n" +
                "from allbook\n" +
                "where  Bkind='"+bookkind+"' and Bpub = '"+pubkind+"' and Lname='"+libkind+"' and Bprice>'"+pricelow+"' and Bprice<'"+pricehigh+"' and Bstatus = '"+statuekind+"'");//查询语句
        return rs; //将查询结果返回
    }
}
