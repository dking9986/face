import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserOperation {//用作所有人脸数据库信息处理类

    private Connection connection = null;
    private Statement statement =null;

    public Integer getUsernum(String account){
        try {
            connection=JdbcUtils.getConnection();
            statement=connection.createStatement();
            String sql1="select  usernum from user where account='"+account+"'";
            ResultSet resultSet = statement.executeQuery(sql1);

            if (resultSet.next()) {
                Integer usernum = resultSet.getInt(1);
                return usernum;
            }
        }  catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtils.result(connection, statement);
        }
       return -1;//没找到返回-1
    }
    public String getName(int usernum){//通过用户号编号找人姓名
        String name="";
        try {
            connection = JdbcUtils.getConnection();//获取数据库连接
            statement = connection.createStatement();  //创建sql语句执行对象
            //编写sql语句
            String sql = "select * from user where usernum = '"+usernum+"' ";
            //执行sql语句
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()){
                name=rs.getString("username");
                return name;
            }else{
                return "UnknownPeople";
            }

        } catch (java.lang.Exception e0) {
            e0.printStackTrace();
        } finally {
            JdbcUtils.result(connection, statement);
        }
        return "程序错误";
    }
    public String getType(int usernum) {
        String type="";
        try {
            connection = JdbcUtils.getConnection();//获取数据库连接
            statement = connection.createStatement();  //创建sql语句执行对象
            //编写sql语句
            String sql = "select * from user where usernum = '"+usernum+"' ";
            //执行sql语句
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()){
                type=rs.getString("type");
                return type;
            }else{
                return "未知";
            }

        } catch (java.lang.Exception e0) {
            e0.printStackTrace();
        } finally {
            JdbcUtils.result(connection, statement);
        }
        return "程序错误";
    }
    public String getAccount(int usernum){//通过用户号编号找人姓名
        String account="";
        try {
            connection = JdbcUtils.getConnection();//获取数据库连接
            statement = connection.createStatement();  //创建sql语句执行对象
            //编写sql语句
            String sql = "select * from user where usernum = '"+usernum+"' ";
            //执行sql语句
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()){
                account=rs.getString("account");
                return account;
            }else{
                return "UnknownPeople";
            }

        } catch (java.lang.Exception e0) {
            e0.printStackTrace();
        } finally {
            JdbcUtils.result(connection, statement);
        }
        return "程序错误";
    }
    public String getPhone(int usernum){//通过用户号编号找人电话
        String phone="";
        try {
            connection = JdbcUtils.getConnection();//获取数据库连接
            statement = connection.createStatement();  //创建sql语句执行对象
            //编写sql语句
            String sql = "select * from user where usernum = '"+usernum+"' ";
            //执行sql语句
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()){
                phone=rs.getString("phone");
                return phone;
            }

        } catch (java.lang.Exception e0) {
            e0.printStackTrace();
        } finally {
            JdbcUtils.result(connection, statement);
        }
        return "-1";
    }



    public int changePhoneNumber(String password, String phone, String account) {
        try {

            connection=JdbcUtils.getConnection();
            statement=connection.createStatement();
            String sql1="select  * from user where account='"+account+"'";
            ResultSet resultSet = statement.executeQuery(sql1);

            if (resultSet.next()) {
                if (password.equals(resultSet.getString(3))) {
                    String sql2="UPDATE user set phone='"+phone+"' where account='"+account+"'";
                    statement.executeUpdate(sql2);
                    return 1;
                }else {
                    return 2;
                }
            }else {
                return 3;
            }
        }  catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtils.result(connection, statement);
        }
        return 4;
    }



    public int insertAdmin(String account, String name, String password, String phone){
        try {
            connection=JdbcUtils.getConnection();
            statement=connection.createStatement();
            String sql1="select  usernum from user where account='"+account+"'";//先查找账号是否已经存在
            ResultSet resultSet = statement.executeQuery(sql1);
            if (resultSet.next()) {
                return 2;
            }else {
                String sql2="insert  into user values (null ,'"+account+"','"+password+"','管理员','"+name+"' ,'"+phone+"')";
                statement.executeUpdate(sql2);
                return 1;
            }
        }  catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtils.result(connection, statement);
        }
        return 3;
    }


    public int insertUser(String account, String name, String password, String phone) {
        try {
            connection=JdbcUtils.getConnection();
            statement=connection.createStatement();
            String sql1="select  usernum from user where account='"+account+"'";//先查找账号是否已经存在
            ResultSet resultSet = statement.executeQuery(sql1);
            if (resultSet.next()) {
                return 2;
            }else {
                String sql2="insert  into user values (null ,'"+account+"','"+password+"','用户','"+name+"' ,'"+phone+"')";
                statement.executeUpdate(sql2);
                return 1;
            }
        }  catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtils.result(connection, statement);
        }
        return 3;
    }

    public Integer changePassword(String account, String passwordold, String passwordnew) {
        try {
            connection=JdbcUtils.getConnection();
            statement=connection.createStatement();
            String sql1="select  * from user where account='"+account+"'";
            ResultSet resultSet = statement.executeQuery(sql1);
            if (resultSet.next()) {//有用户
                if (passwordold.equals(resultSet.getString(3))) {//密码正确
                    String sql2="UPDATE user set password='"+passwordnew+"' where account='"+account+"' ";
                    statement.executeUpdate(sql2);
                    return 1;
                }else {//密码错误
                    return 2;
                }
            }else {
                return 3;
            }
        }  catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtils.result(connection, statement);
        }
        return 4;
    }

    public boolean checkAccountandPassword(String account, String password) {
        try {
            connection = JdbcUtils.getConnection();//获取数据库连接
            statement = (Statement) connection.createStatement();  //创建sql语句执行对象
            //编写sql语句
            String sql = "select * from user where account='" + account + "'  and password='" + password + "'     ";//通过getText()获取输入的信息
            //执行sql语句
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                return true;
            }
        } catch (Exception e0) {
            e0.printStackTrace();
        } finally {
            JdbcUtils.result(connection, statement);
        }
        return  false;

    }


    public void deleteUser(Integer usernum) {
        try {
            connection=JdbcUtils.getConnection();
            statement=connection.createStatement();

            String sql="delete from user where usernum="+usernum+"";
            statement.executeUpdate(sql);
        }  catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtils.result(connection, statement);
        }
    }


    public String[][] selectAll() {
        try {
            connection = JdbcUtils.getConnection();//获取数据库连接
            statement =  connection.createStatement();  //创建sql语句执行对象
            //编写sql语句
            String sql = "select * from user where type='用户'";
            //执行sql语句
            ResultSet rs = statement.executeQuery(sql);
            int i=new UserOperation().getUserCount();
            if (i>0){
                String[][] users=new String[i][5];

                for (int j=0;j<i;j++){
                    rs.next();
                    String[] str={rs.getString(1), rs.getString(2), rs.getString(5),rs.getString(4), rs.getString(6)}; // 将一行的数据存在str_row
                    users[j]=str;
                }
                return users;
            }
        }  catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.result(connection, statement);
        }
        return null;
    }


    public int getUserCount(){
        try {
            connection = JdbcUtils.getConnection();//获取数据库连接
            statement =  connection.createStatement();  //创建sql语句执行对象
            //编写sql语句
            String sql2 = "select count(*) from user where type='用户'";//通过getText()获取输入的信息
            //执行sql语句
            ResultSet rs2=statement.executeQuery(sql2);
            int i=0;
            if (rs2.next()){
                i=rs2.getInt(1);
                return i;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
