import org.bytedeco.javacv.FrameGrabber;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Input extends JFrame {
    private JPanel contentPane;
    private JButton b1;
    private JLabel label1, label2,label3,label4,label5;
    private int LOGIN_WIDTH = 360;
    private int LOGIN_HEIGTH = 350;
    private JTextField inputtext,inputtext2,inputtext3,inputtext4,inputtext5;
    private long i;

    Connection connection;
    Statement statement;

    public void inputName(){
        setBounds(350,360,LOGIN_WIDTH,LOGIN_HEIGTH);
        setLocation(350,350);
        setDefaultCloseOperation(new JFrame().DISPOSE_ON_CLOSE);  //设置窗体可关闭
        setResizable(false);  //设置窗体大小不可以改变
        setVisible(true);

        contentPane =new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        label1 = new JLabel("请输入要查找的账号：");
        label1.setBounds(80, 76, 54, 28);
        contentPane.add(label1);

        inputtext = new JTextField();//输入框
        inputtext.setBounds(139, 80, 161, 25);
        contentPane.add(inputtext);

        b1 = new JButton("查找");
        b1.setBounds(210, 210, 80, 23);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = "";
                s = inputtext.getText();
                if (s.equals("")) {
                    JOptionPane.showMessageDialog(null, "输入为空");
                } else {
                    new ShowRecord().showAccountSelect(inputtext.getText(),"admin");
                }

            }
        });
        contentPane.add(b1);
    }
    public void inputIntervalTime() {
        setBounds(350,360,LOGIN_WIDTH,LOGIN_HEIGTH);
        setLocation(350,350);
        setDefaultCloseOperation(new JFrame().DISPOSE_ON_CLOSE);  //设置窗体可关闭
        setResizable(false);  //设置窗体大小不可以改变
        setVisible(true);

        contentPane =new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //输入间隔时间
        label1 = new JLabel("请输入每个出入记录的间隔时间：");
        label1.setBounds(80, 76, 100, 28);
        contentPane.add(label1);

        inputtext = new JTextField();//输入框
        inputtext.setBounds(80, 105, 161, 25);
        contentPane.add(inputtext);

        b1 = new JButton("确定");
        b1.setBounds(210, 210, 80, 23);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = "";
                s = inputtext.getText();
                if (s.equals("")) {
                    JOptionPane.showMessageDialog(null, "输入为空");
                } else {

                    i = Long.parseLong(s);
                    System.out.println(i);
                    try {
                        connection=jdbcUtils.getConnection();
                        statement=connection.createStatement();
                        String sql="update recognizer set intervaltime="+ i +" ";
                        statement.executeUpdate(sql);
                    } catch (FrameGrabber.Exception ex) {
                        ex.printStackTrace();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }finally {
                        jdbcUtils.result(connection, statement);
                    }

                    JOptionPane.showMessageDialog(null, "更改成功");
                    dispose();
                }
            }
        });
        contentPane.add(b1);
    }


    public void inputFacePicNum() {
        setBounds(350,360,LOGIN_WIDTH,LOGIN_HEIGTH);
        setLocation(350,350);
        setDefaultCloseOperation(new JFrame().DISPOSE_ON_CLOSE);  //设置窗体可关闭
        setResizable(false);  //设置窗体大小不可以改变
        setVisible(true);

        contentPane =new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //输入间隔时间
        label1 = new JLabel("请输入每个人保存图片数量（越多越准越慢）：");
        label1.setBounds(80, 76, 100, 28);
        contentPane.add(label1);


        inputtext = new JTextField();//输入框
        inputtext.setBounds(80, 105, 161, 25);
        contentPane.add(inputtext);


        b1 = new JButton("确定");
        b1.setBounds(210, 210, 80, 23);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = "";
                s = inputtext.getText();
                if (s.equals("")) {
                    JOptionPane.showMessageDialog(null, "输入为空");
                } else {

                    i = Integer.parseInt(s);
                    System.out.println(i);
                    try {
                        connection=jdbcUtils.getConnection();
                        statement=connection.createStatement();
                        String sql="update recognizer set facescount="+ i +" ";
                        statement.executeUpdate(sql);
                    }catch (Exception ex) {
                        ex.printStackTrace();
                    }finally {
                        jdbcUtils.result(connection, statement);
                    }

                    JOptionPane.showMessageDialog(null, "更改成功");
                    dispose();
                }

            }
        });
        contentPane.add(b1);
    }

    //输入新记录内容
    public void inputNewRecord(){
        setBounds(350,360,LOGIN_WIDTH,LOGIN_HEIGTH);
        setLocation(350,350);
        setDefaultCloseOperation(new JFrame().DISPOSE_ON_CLOSE);  //设置窗体可关闭
        setResizable(false);  //设置窗体大小不可以改变
        setVisible(true);

        contentPane =new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        label2 = new JLabel("账  号");
        label2.setBounds(20, 70, 100, 28);
        contentPane.add(label2);

        inputtext2 = new JTextField();//输入框
        inputtext2.setBounds(120, 70, 161, 25);
        contentPane.add(inputtext2);

        label4 = new JLabel("出入类型");
        label4.setBounds(20, 110, 100, 28);
        contentPane.add(label4);

        inputtext4 = new JTextField();//输入框
        inputtext4.setBounds(120, 110, 161, 25);
        contentPane.add(inputtext4);

        label5 = new JLabel("时间");
        label5.setBounds(20, 150, 100, 28);
        contentPane.add(label5);

        inputtext5 = new JTextField();//输入框
        inputtext5.setBounds(120, 150, 161, 25);
        contentPane.add(inputtext5);

        b1=new JButton("确定");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String account=inputtext2.getText();
                    String type=inputtext4.getText();
                    String time=inputtext5.getText();

                    connection=jdbcUtils.getConnection();
                    statement=connection.createStatement();
                    String sql1="select  usernum from user where account='"+account+"'";
                    ResultSet resultSet = statement.executeQuery(sql1);
                    Integer usernum=-1;
                    String phone="-1";
                    String name="";
                    if (resultSet.next()) {
                        usernum = resultSet.getInt(1);
                        name=new FaceRecord().getName(usernum);
                        phone=new FaceRecord().getPhone(usernum);
                        String sql2="insert  into record values (null ,"+usernum+",'"+account+"','"+name+"','"+type+"','"+time+"','"+phone+"')";
                        statement.executeUpdate(sql2);
                        JOptionPane.showMessageDialog(null, "插入成功");
                        dispose();
                        new ShowRecord().showAllRecord();
                    }else {
                        JOptionPane.showMessageDialog(null, "输入的账号不在数据库中 无法插入");
                    }
                }  catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    jdbcUtils.result(connection, statement);
                }


            }
        });
        b1.setBounds(250, 250, 80, 23);
        contentPane.add(b1);
    }


    public void inputNewRecord(final String account){
        setBounds(350,360,LOGIN_WIDTH,LOGIN_HEIGTH);
        setLocation(350,350);
        setDefaultCloseOperation(new JFrame().DISPOSE_ON_CLOSE);  //设置窗体可关闭
        setResizable(false);  //设置窗体大小不可以改变
        setVisible(true);

        contentPane =new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        /*label2 = new JLabel("账  号");
        label2.setBounds(20, 70, 100, 28);
        contentPane.add(label2);


        inputtext2 = new JTextField();//输入框
        inputtext2.setBounds(120, 70, 161, 25);
        contentPane.add(inputtext2);
*/
        /*label3 = new JLabel("姓名");
        label3.setBounds(20, 110, 100, 28);
        contentPane.add(label3);*/


       /* inputtext3 = new JTextField();//输入框
        inputtext3.setBounds(120, 110, 161, 25);
        contentPane.add(inputtext3);*/

        label4 = new JLabel("出入类型");
        label4.setBounds(20, 150, 100, 28);
        contentPane.add(label4);


        inputtext4 = new JTextField();//输入框
        inputtext4.setBounds(120, 150, 161, 25);
        contentPane.add(inputtext4);

        label5 = new JLabel("时间");
        label5.setBounds(20, 190, 100, 28);
        contentPane.add(label5);


        inputtext5 = new JTextField();//输入框
        inputtext5.setBounds(120, 190, 161, 25);
        contentPane.add(inputtext5);


        b1=new JButton("确定");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //String account=inputtext2.getText();
                    //String name=inputtext3.getText();
                    String type=inputtext4.getText();
                    String time=inputtext5.getText();

                    connection=jdbcUtils.getConnection();
                    statement=connection.createStatement();
                    String sql1="select  usernum from user where account='"+account+"'";
                    ResultSet resultSet = statement.executeQuery(sql1);
                    Integer usernum=-1;
                    String phone="";
                    String name="";
                    if (resultSet.next()) {
                        usernum = resultSet.getInt(1);
                        name=new FaceRecord().getName(usernum);
                        phone=new FaceRecord().getPhone(usernum);
                        String sql2="insert  into record values (null ,"+usernum+",'"+account+"','"+name+"','"+type+"','"+time+"' ,'"+phone+"')";
                        statement.executeUpdate(sql2);
                        JOptionPane.showMessageDialog(null, "插入成功");
                        dispose();
                        new ShowRecord().showAccountSelect(account,"admin");
                    }else {
                        JOptionPane.showMessageDialog(null, "输入的账号不在数据库中 无法插入");
                    }
                }  catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    jdbcUtils.result(connection, statement);
                }
            }
        });
        b1.setBounds(250, 250, 80, 23);
        contentPane.add(b1);
    }
}
