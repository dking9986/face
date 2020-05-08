import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class RecognizerOperation {

    private Connection connection = null;
    private Statement statement =null;

    public Long getIntervaltime() {
        try {
            connection=JdbcUtils.getConnection();
            statement=connection.createStatement();
            String sql="select * from recognizer ";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()){
                Long intervaltime=resultSet.getLong(1);
                return intervaltime;
            }

        }catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            JdbcUtils.result(connection, statement);
        }
        return 30l;//默认30
    }

    public Integer changeFacescount(long i) {
        try {
            connection=JdbcUtils.getConnection();
            statement=connection.createStatement();
            String sql="update recognizer set facescount="+ i +" ";
            Integer flag=statement.executeUpdate(sql);
            if (flag>0){

                return 1;
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            JdbcUtils.result(connection, statement);
        }
        return 2;
    }

    public Integer changeIntervalTime(long i) {
        try {
            connection=JdbcUtils.getConnection();
            statement=connection.createStatement();
            String sql="update recognizer set intervaltime="+ i +" ";
            Integer flag=statement.executeUpdate(sql);
            if (flag>0){

                return 1;
            }
        }  catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            JdbcUtils.result(connection, statement);
        }
        return 0;
    }


    public Integer changePath(String path,String choosetype) {
        try {
            connection=JdbcUtils.getConnection();
            statement=connection.createStatement();
            String sql="update recognizer set "+choosetype+"='"+ path +"'";
            int i = statement.executeUpdate(sql);

            if (i>0){
                return 1;
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            JdbcUtils.result(connection, statement);
        }
        return 2;
    }

    public int getFaceCount() {

        try {
            connection=JdbcUtils.getConnection();
            statement=connection.createStatement();
            String sql="select * from recognizer ";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()){
                int facecount=resultSet.getInt(2);
                return facecount;
            }

        }catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            JdbcUtils.result(connection, statement);
        }
        return 15;//默认30
    }
    public String getPath(String pathtype) {

        try {
            connection=JdbcUtils.getConnection();
            statement=connection.createStatement();
            String sql="select "+pathtype+" from recognizer ";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()){
                String path=resultSet.getString(1);
                return path;
            }

        }catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            JdbcUtils.result(connection, statement);
        }
        return "";//默认30
    }
}
