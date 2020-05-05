import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ShowSelect extends JFrame {
    private JPanel contentPane;
    private JButton b1, b2, b3, b4;//
    private JLabel label1, label2;
    private int LOGIN_WIDTH = 360;
    private int LOGIN_HEIGTH = 350;

    JTable jTable = null;
    JScrollPane jsp = null;

    List<List<String>> rowData;
    List<String> columnNames;

    private Connection connection;
    private Statement statement;


    public ShowSelect(String account) {


        try {
            connection = jdbcUtils.getConnection();//获取数据库连接
            statement = (Statement) connection.createStatement();  //创建sql语句执行对象
            //编写sql语句
            String sql = "select * from record where account='" + account + "'";//通过getText()获取输入的信息
            //执行sql语句
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                //从数据库中取出信息
                //rowData用来存放行数据
                //columnNames存放列名
                String[] col = { "出入编号", "用户编号", "账号","姓名","出入类型" ,"时间"};
                DefaultTableModel mm = new DefaultTableModel(col, 0); // 定义一个表的模板
                do {
                    String[] str_row = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6)}; // 将一行的数据存在str_row
                    // 字符串数组里
                    mm.addRow(str_row);// 添加在表


                }while (rs.next());
                jTable=new JTable();
                jTable.setModel(mm);

                setTitle("用户："+ account);
                setBounds(350,360,LOGIN_WIDTH,LOGIN_HEIGTH);
                setResizable(true);

                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                //contentPane = new JPanel();
                //contentPane.setBackground(Color.WHITE);
                //setContentPane(contentPane);
                jsp=new JScrollPane(jTable);
                jsp.setBackground(Color.WHITE);
                //contentPane.add(jsp);
                add(jsp);



                setVisible(true);

            } else{
                JOptionPane.showMessageDialog(null, "无此人记录！");
            }



        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            jdbcUtils.result(connection, statement);
        }

    }


    public ShowSelect() {

        try {
            connection = jdbcUtils.getConnection();//获取数据库连接
            statement = (Statement) connection.createStatement();  //创建sql语句执行对象
            //编写sql语句
            String sql = "select * from record ";//通过getText()获取输入的信息
            //执行sql语句
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                //从数据库中取出信息
                //rowData用来存放行数据
                //columnNames存放列名
                String[] col = { "出入编号", "用户编号", "账号","姓名","出入类型" ,"时间"};
                DefaultTableModel mm = new DefaultTableModel(col, 0); // 定义一个表的模板
                do {
                    String[] str_row = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6)}; // 将一行的数据存在str_row
                    // 字符串数组里
                    mm.addRow(str_row);// 添加在表


                }while (rs.next());
                jTable=new JTable();
                jTable.setModel(mm);

                setTitle("全部记录");
                setBounds(350,360,LOGIN_WIDTH,LOGIN_HEIGTH);
                setResizable(true);

                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                //contentPane = new JPanel();
                //contentPane.setBackground(Color.WHITE);
                //setContentPane(contentPane);
                jsp=new JScrollPane(jTable);
                jsp.setBackground(Color.WHITE);
                //contentPane.add(jsp);
                add(jsp);

                setVisible(true);

            } else{
                JOptionPane.showMessageDialog(null, "无此人记录！");
            }



        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            jdbcUtils.result(connection, statement);
        }

    }
}