import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class FaceRecord {//用作所有人脸数据库信息处理类

    private Connection connection = null;
    private Statement statement =null;


    public String getName(int usernum){//通过用户号编号找人姓名
        String name="";
        try {
            connection = jdbcUtils.getConnection();//获取数据库连接
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
            jdbcUtils.result(connection, statement);
        }
        return "程序错误";
    }
    public String getAccount(int usernum){//通过用户号编号找人姓名
        String account="";
        try {
            connection = jdbcUtils.getConnection();//获取数据库连接
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
            jdbcUtils.result(connection, statement);
        }
        return "程序错误";
    }



    public boolean recTrip(int usernum,long intervaltime) throws Exception {//通过对应的用户编号记录出行时间 偶数是出 奇数是入 将信息插入到record表中
                if (usernum==-1){//如果是陌生人
                    try {
                        String username="unknown";
                        String account="unknown";
                        connection = jdbcUtils.getConnection();//获取数据库连接
                        statement = (Statement) connection.createStatement();  //创建sql语句执行对象

                        String s="未知";

                        //得到当前时间
                        Date time = new Date(System.currentTimeMillis());
                        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String current = sdf.format(time);//出入时间

                        String sql3 = "select * from record where usernum = '"+usernum+"' order by recordnum desc " ;//得到数据库中此人的最新数据
                        //执行sql语句
                        ResultSet rs3 = null;
                        rs3 = statement.executeQuery(sql3);
                        if (rs3.next()) {
                            String lastest=rs3.getString("time");
                            java.util.Date date = sdf.parse(lastest);//之前最新的时间
                            java.util.Date date1 = sdf.parse(current);//现在的时间
                            long diff = date1.getTime() - date.getTime();
                            //long inttime=intervaltime*1000;//interval单位是秒  inttime单位是ms
                            if (diff<10000){//陌生人出入间隔小于10s则不记录 大于10s则记录
                                //时间小于10s
                                return false;
                            }
                        }

                        //进行record表插入
                        String sql4="insert into record values(null ,'"+usernum+"','"+account+"','"+username+"','"+s+"','"+current+"')";//记录出行总次数
                        //执行sql语句
                        int rs4 = statement.executeUpdate(sql4);
                        if (rs4>0){
                            System.out.println(" 陌生人记录成功");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        jdbcUtils.result(connection, statement);
                    }
                }else {//如果是有记录的人
                    try {
                        String username=getName(usernum);
                        String account=getAccount(usernum);
                        connection = jdbcUtils.getConnection();//获取数据库连接
                        statement = (Statement) connection.createStatement();  //创建sql语句执行对象
                        String sql2="select count(*) from record where usernum = '"+usernum+"' ";//记录出行总次数
                        //执行sql语句
                        ResultSet rs2 = null;
                        rs2 = statement.executeQuery(sql2);
                        int count= 0;//初始化出行总次数 来得到是进入还是出去
                        if (rs2.next()){

                            count = rs2.getInt(1);
                        }
                        int type=count%2;
                        String s="";
                        if (type==0){
                            s="入";
                        }else {
                            s="出";
                        }
                        //得到当前时间
                        Date time = new Date(System.currentTimeMillis());
                        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String current = sdf.format(time);//出入时间

                        String sql3 = "select * from record where usernum = '"+usernum+"' order by recordnum desc " ;//得到数据库中此人的最新数据
                        //执行sql语句
                        ResultSet rs3 = null;
                        rs3 = statement.executeQuery(sql3);
                        if (rs3.next()) {
                            String lastest=rs3.getString("time");
                            java.util.Date date = sdf.parse(lastest);//之前最新的时间
                            java.util.Date date1 = sdf.parse(current);//现在的时间
                            long diff = date1.getTime() - date.getTime();
                            long inttime=intervaltime*1000;//interval单位是秒  inttime单位是ms
                            if (diff<inttime){//出入间隔小于30s则不记录 大于30s则记录
                                return false;
                            }

                        }

                        //进行record表插入
                        String sql4="insert into record values(null ,'"+usernum+"','"+account+"','"+username+"','"+s+"','"+current+"')";//记录出行总次数
                        //执行sql语句
                        int rs4 = statement.executeUpdate(sql4);
                        if (rs4>0){
                            System.out.println("记录成功");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        jdbcUtils.result(connection, statement);
                    }
                }
                return true;

    }
    public void writeDbExcel(String path){//导出到excel表格
        Date time = new Date(System.currentTimeMillis());
        DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String current = sdf.format(time);//现在时间
        String savepath=""+path+"\\record"+current+".xls";
        HSSFWorkbook sheets=new HSSFWorkbook();
        HSSFSheet sheet=sheets.createSheet("sheet1");
        try {
            connection=jdbcUtils.getConnection();
            statement=connection.createStatement();
            String sql="select * from  record";
            ResultSet rs=statement.executeQuery(sql);
            ResultSetMetaData rsmd=rs.getMetaData();//得到结果集的字段名
            int columnCount=rsmd.getColumnCount();//得到数据表的结果集的字段的数量
            //生成表单的第一行，即表头
            HSSFRow row0=sheet.createRow(0);//创建第一行

            for(int i=0;i<columnCount;i++){
                HSSFCell cel=row0.createCell(i);//创建第一行的第i列
                cel.setCellValue(rsmd.getColumnName(i+1));
            }
            //将数据表中的数据按行导入进Excel表中
            int r=1;
            while(rs.next()){
                HSSFRow row=sheet.createRow(r++);//创建非第一行的其他行
                for(int i=0;i<columnCount;i++){//仍然是c列，导入第r行的第i列
                    HSSFCell cel=row.createCell(i);
                    cel.setCellValue(rs.getString(i+1));//resultset从1号开始
                }
            }
            //用文件输出流类创建名为table的Excel表格
            FileOutputStream out=new FileOutputStream(savepath);
            sheets.write(out);//将HSSFWorkBook中的表写入输出流中
            sheets.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            jdbcUtils.result(connection, statement);
        }
        return ;

    }
    public void getUser(){

    }
}
