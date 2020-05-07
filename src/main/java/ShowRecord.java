import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ShowRecord extends JFrame {
    private JPanel contentPane;
    private JButton insert,delete,update;
    private int LOGIN_WIDTH = 900;
    private int LOGIN_HEIGTH = 500;
    private JLabel label;

    JTable jTable = null;
    JScrollPane jsp = null;

    private Connection connection;
    private Statement statement;
    private DefaultTableModel mm;

    public void showAccountSelect(final String account,String type) {
        try {
            connection = jdbcUtils.getConnection();
            statement = connection.createStatement();
            String sql = "select * from record where account='" + account + "'";
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                String[] col = { "出入编号", "用户编号", "账号","姓名","出入类型" ,"时间","联系电话"};
                mm = new DefaultTableModel(col, 0); // 定义一个表的模板
                do {
                    String[] str_row = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6),rs.getString(7)}; // 将一行的数据存在str_row
                    // 字符串数组里
                    mm.addRow(str_row);// 添加在表
                }while (rs.next());
                jTable=new JTable();
                jTable.setModel(mm);

                setTitle("记录用户名："+ account+"注意修改、删除功能每次仅对选中的一行做操作");
                setBounds(350,360,LOGIN_WIDTH,LOGIN_HEIGTH);
                setResizable(true);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                jsp=new JScrollPane(jTable);
                jsp.setBackground(Color.WHITE);
                add(jsp,BorderLayout.NORTH);

                if (type.equals("admin")){//如果是管理员才显示以下按钮
                    contentPane = new JPanel();
                    insert=new JButton("添加");
                    update=new JButton("修改");
                    delete=new JButton("删除");
                    insert.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            new Input().inputNewRecord(account);
                            dispose();
                        }
                    });
                    update.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int row=jTable.getSelectedRow();
                            if (row==-1){
                                JOptionPane.showMessageDialog(null,"请选中一行");
                            }else {
                                try {
                                    connection=jdbcUtils.getConnection();
                                    statement=connection.createStatement();
                                    Integer recnum=Integer.parseInt((String) mm.getValueAt(row,0));
                                    Integer usernum=Integer.parseInt((String) mm.getValueAt(row,1));
                                    String account=(String) mm.getValueAt(row,2);
                                    String name=(String) mm.getValueAt(row,3);
                                    String type=(String) mm.getValueAt(row,4);
                                    String time=(String) mm.getValueAt(row,5);
                                    String phone=(String) mm.getValueAt(row,6);
                                    String sql="update  record set recordnum="+recnum+",usernum="+usernum+",account='"+account+"',name='"+name+"',type='"+type+"',time='"+time+"',phone='"+phone+"' where recordnum ="+recnum+"";
                                    statement.executeUpdate(sql);
                                    JOptionPane.showMessageDialog(null,"修改成功");

                                }  catch (Exception ex) {
                                    ex.printStackTrace();
                                } finally {
                                    jdbcUtils.result(connection, statement);
                                }
                                dispose();
                                new ShowRecord().showAccountSelect(account,"admin");

                            }
                        }
                    });
                    delete.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int row=jTable.getSelectedRow();
                            if (row==-1){
                                JOptionPane.showMessageDialog(null,"请选中一行");
                            }else {
                                try {
                                    connection=jdbcUtils.getConnection();
                                    statement=connection.createStatement();

                                    Integer recnum=Integer.parseInt((String) mm.getValueAt(row,0));
                                    String sql="delete from record where recordnum="+recnum+"";
                                    statement.executeUpdate(sql);
                                }  catch (Exception ex) {
                                    ex.printStackTrace();
                                } finally {
                                    jdbcUtils.result(connection, statement);
                                }
                                //刷新表数据 重开页面
                                dispose();
                                new ShowRecord().showAccountSelect(account,"admin");
                            }
                        }
                    });

                    contentPane.add(insert);
                    contentPane.add(update);
                    contentPane.add(delete);


                    add(contentPane,BorderLayout.CENTER);
                }
                setVisible(true);
            } else{
                JOptionPane.showMessageDialog(null, "无此人记录！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            jdbcUtils.result(connection, statement);
        }
    }


    public void showAllRecord() {
        try {
            connection = jdbcUtils.getConnection();
            statement = (Statement) connection.createStatement();
            String sql = "select * from record ";
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                String[] col = { "出入编号", "用户编号", "账号","姓名","出入类型" ,"时间","联系电话"};
                mm = new DefaultTableModel(col, 0); // 定义一个表的模板
                do {
                    String[] str_row = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6),rs.getString(7)};
                    mm.addRow(str_row);// 添加在表
                }while (rs.next());

                jTable=new JTable();
                jTable.setModel(mm);

                setTitle("全部记录");
                setBounds(350,360,LOGIN_WIDTH,LOGIN_HEIGTH);
                setResizable(true);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                jsp=new JScrollPane(jTable);
                jsp.setBackground(Color.WHITE);
                add(jsp,BorderLayout.NORTH);
                contentPane = new JPanel();
                insert=new JButton("添加");
                update=new JButton("修改");
                delete=new JButton("删除");
                insert.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new Input().inputNewRecord();
                        dispose();
                    }
                });
                update.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int row=jTable.getSelectedRow();
                        if (row==-1){
                            JOptionPane.showMessageDialog(null,"请选中一行");
                        }else {
                            try {
                                connection=jdbcUtils.getConnection();
                                statement=connection.createStatement();

                                Integer recnum=Integer.parseInt((String) mm.getValueAt(row,0));
                                Integer usernum=Integer.parseInt((String) mm.getValueAt(row,1));

                                String account=(String) mm.getValueAt(row,2);
                                String name=(String) mm.getValueAt(row,3);
                                String type=(String) mm.getValueAt(row,4);
                                String time=(String) mm.getValueAt(row,5);
                                String phone=(String) mm.getValueAt(row,6);
                                String sql="update  record set recordnum="+recnum+",usernum="+usernum+",account='"+account+"',name='"+name+"',type='"+type+"',time='"+time+"',phone='"+phone+"' where recordnum =( select t.recordnum from (select * from record limit "+row+",1)as t)";
                                statement.executeUpdate(sql);
                                JOptionPane.showMessageDialog(null,"修改成功");
                            }  catch (Exception ex) {
                                ex.printStackTrace();
                            } finally {
                                jdbcUtils.result(connection, statement);
                            }
                            dispose();
                            new ShowRecord().showAllRecord();

                        }
                    }
                });
                delete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int row=jTable.getSelectedRow();
                        if (row==-1){
                            JOptionPane.showMessageDialog(null,"请选中一行");
                        }else {
                            try {
                                connection=jdbcUtils.getConnection();
                                statement=connection.createStatement();

                                Integer recnum=Integer.parseInt((String) mm.getValueAt(row,0));
                                String sql="delete from record where recordnum="+recnum+"";
                                statement.executeUpdate(sql);
                            }  catch (Exception ex) {
                                ex.printStackTrace();
                            } finally {
                                jdbcUtils.result(connection, statement);
                            }
                            //刷新表数据 重开页面
                            dispose();
                            new ShowRecord().showAllRecord();
                        }
                    }
                });
                contentPane.add(insert);
                contentPane.add(update);
                contentPane.add(delete);
                add(contentPane,BorderLayout.CENTER);
                setVisible(true);

            } else{
                JOptionPane.showMessageDialog(null, "无此人记录！");
            }
        }  catch (Exception e) {
            e.printStackTrace();
        }finally {
            jdbcUtils.result(connection, statement);
        }

    }
    public void showAllUser() {
        try {
            connection = jdbcUtils.getConnection();//获取数据库连接
            statement = (Statement) connection.createStatement();  //创建sql语句执行对象
            //编写sql语句
            String sql = "select * from user ";//通过getText()获取输入的信息
            //执行sql语句
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                String[] col = {  "用户编号", "账号","姓名","电话"};
                DefaultTableModel mm = new DefaultTableModel(col, 0); // 定义一个表的模板
                do {
                    String[] str_row = {rs.getString(1), rs.getString(2), rs.getString(5),rs.getString(6)}; // 将一行的数据存在str_row
                    // 字符串数组里
                    mm.addRow(str_row);// 添加在表
                }while (rs.next());
                jTable=new JTable();
                jTable.setModel(mm);

                setTitle("全部记录");
                setBounds(350,360,LOGIN_WIDTH,LOGIN_HEIGTH);
                setResizable(true);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                jsp=new JScrollPane(jTable);
                jsp.setBackground(Color.WHITE);
                add(jsp);
                setVisible(true);
            } else{
                JOptionPane.showMessageDialog(null, "无账号记录！");
            }

        }  catch (Exception e) {
            e.printStackTrace();
        }finally {
            jdbcUtils.result(connection, statement);
        }

    }
}