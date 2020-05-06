import org.bytedeco.javacv.FrameGrabber;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class User extends JFrame {

    private JPanel contentPane;
    private JButton b1, b2,b3;//登录 退出 注册
    private JLabel label1, label2;

    private int LOGIN_WIDTH = 360;
    private int LOGIN_HEIGTH = 350;

    private String acut=null;
    private String name=null;
    private int num=-1;

    Connection connection;
    Statement statement;

    /**
     * 构造方法
     */
    public  User(int usernum, final String account, String username) throws FrameGrabber.Exception, InterruptedException {

        setTitle("用户："+username);
        setBounds(500, 300, LOGIN_WIDTH, LOGIN_HEIGTH);  //设置窗体坐标以及打下
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setBackground(Color.darkGray);

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        name=username;
        num=usernum;
        acut=account;



        //按钮—导出记录
        b1 = new JButton("录入人脸");
        b1.setBounds(100, 100, 100, 23);
//        btn2.setIcon(new ImageIcon(Login.class.getResource("/images/exit.png")));
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)  {
                if (e.getSource() == b1) {

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    connection=jdbcUtils.getConnection();
                                    statement=connection.createStatement();
                                    String sql="select * from recognizer ";
                                    ResultSet resultSet = statement.executeQuery(sql);
                                    if (resultSet.next()){
                                        new FaceRecog().getFace(acut,num,name,resultSet.getInt(2));
                                    }
                                }  catch (Exception ex) {
                                    ex.printStackTrace();
                                } finally {
                                    jdbcUtils.result(connection, statement);
                                }

                                new FaceRecog().faceTrain();
                                JOptionPane.showMessageDialog(null, "人脸录入成功!");
                            }
                        }).start();



                }
            }
        });
        contentPane.add(b1);



        b2 = new JButton("查询我的记录");
        b2.setBounds(100, 180, 120, 23);
//        btn2.setIcon(new ImageIcon(Login.class.getResource("/images/exit.png")));
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)  {
                if (e.getSource() == b2) {

                    new ShowRecord().showAccountSelect(acut,"user");

                }
            }
        });
        contentPane.add(b2);


        //按钮—退出
        b3 = new JButton("退出登录");
        b3.setBounds(210, 210, 100, 23);
//        btn2.setIcon(new ImageIcon(Login.class.getResource("/images/exit.png")));
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b3) {
                    dispose();
                    new Login();
                }
            }
        });
        contentPane.add(b3);

    }
}
 
 
