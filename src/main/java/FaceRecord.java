import org.bytedeco.javacpp.opencv_core;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import static org.bytedeco.javacpp.opencv_imgproc.FONT_HERSHEY_PLAIN;
import static org.bytedeco.javacpp.opencv_imgproc.putText;

public class FaceRecord {//用作所有数据库信息处理类
    Connection connection = null;
    Statement stam=null;
    public String getName(int usernum){//通过用户号找人
        String name="";

        try {
            connection = jdbcUtils.getConnection();//获取数据库连接
            stam = (Statement) connection.createStatement();  //创建sql语句执行对象
            //编写sql语句
            String sql = "select * from user where usernum = '"+usernum+"' ";
            //执行sql语句
            ResultSet rs = stam.executeQuery(sql);

            if (rs.next()){
                name=rs.getString("username");
                return name;
            }else{
                return "UnknownPeople";
            }

        } catch (java.lang.Exception e0) {
            e0.printStackTrace();
        } finally {
            jdbcUtils.result(connection, stam);
        }
        return "程序错误";
    }
    public void recTrip(int usernum) throws Exception {//通过对应的序号记录出行时间 偶数是出 奇数是入

        connection = jdbcUtils.getConnection();//获取数据库连接
        stam = (Statement) connection.createStatement();  //创建sql语句执行对象
        //编写sql语句
        String name="";
        String sql1 = "select * from user where usernum = '"+usernum+"' ";//记录人名
        //执行sql语句
        ResultSet rs1 = stam.executeQuery(sql1);
        name=rs1.getString("username");

        String sql2="select count(*) from record where usernum = '"+usernum+"' ";//记录出行总次数
        //执行sql语句
        ResultSet rs2 = stam.executeQuery(sql2);
        int count=rs2.getInt(0);//出行总次数

        String sql3="";
        Date time = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String current = sdf.format(time);//出入时间

        //进行record表插入



    }
}
