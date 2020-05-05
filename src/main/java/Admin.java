import org.bytedeco.javacv.FrameGrabber;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Admin extends JFrame {
    private JPanel contentPane;
    private JButton b1, b2, b3, b4,b5,b6,b7,b8;//
    private JLabel label1, label2;

    private int LOGIN_WIDTH = 360;
    private int LOGIN_HEIGTH = 350;

    Connection connection;
    Statement statement;


    public  Admin(int usernum, final String account, String username) {
        setTitle("管理员主界面");
        setBounds(500, 300, LOGIN_WIDTH, LOGIN_HEIGTH);  //设置窗体坐标以及打下
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBackground(Color.darkGray);

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        //contentPane.setBorder(new EmptyBorder(200, 200, 200, 200));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        //按钮—运行系统
        b1 = new JButton("运行系统");
        b1.setBounds(50, 50, 100, 23);
//        btn2.setIcon(new ImageIcon(Login.class.getResource("/images/exit.png")));
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                                new FaceRecog().faceRec(resultSet.getLong(1));
                            }

                            }catch (Exception ex) {
                                ex.printStackTrace();
                            }finally {
                                jdbcUtils.result(connection, statement);
                            }

                        }
                    }).start();

                }
            }
        });
        contentPane.add(b1);

        //按钮—导出记录
        b2 = new JButton("查询个人");
        b2.setBounds(50, 78, 100, 23);
//        btn2.setIcon(new ImageIcon(Login.class.getResource("/images/exit.png")));
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b2) {

                        new Input().inputName();

                }
            }
        });
        contentPane.add(b2);


        b3 = new JButton("打开记录");
        b3.setBounds(50, 106, 100, 23);
//        btn2.setIcon(new ImageIcon(Login.class.getResource("/images/exit.png")));
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b3) {

                    new ShowSelect();

                }
            }
        });
        contentPane.add(b3);


        //按钮—导出记录
        b4 = new JButton("导出记录");
        b4.setBounds(50, 134, 100, 23);
//        btn2.setIcon(new ImageIcon(Login.class.getResource("/images/exit.png")));
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b4) {
                    new FilePathChooser();
                }
            }
        });
        contentPane.add(b4);





        //按钮—退出
        b5 = new JButton("退出登录");
        b5.setBounds(210, 210, 100, 23);
//        btn2.setIcon(new ImageIcon(Login.class.getResource("/images/exit.png")));
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b5) {

                    new Login();
                    dispose();
                }
            }
        });
        contentPane.add(b5);

        //按钮—导出记录
        b6 = new JButton("校正识别器");
        b6.setBounds(170, 50, 100, 23);
//        btn2.setIcon(new ImageIcon(Login.class.getResource("/images/exit.png")));
        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b6) {

                    new FaceRecog().faceTrain();
                    JOptionPane.showMessageDialog(null,"校正完成！");

                }
            }
        });
        contentPane.add(b6);
        //按钮—导出记录
        b7 = new JButton("更改间隔时间");
        b7.setBounds(170, 78, 100, 23);
//        btn2.setIcon(new ImageIcon(Login.class.getResource("/images/exit.png")));
        b7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b7) {
                    new Input().inputIntervalTime();

                }
            }
        });
        contentPane.add(b7);
        //按钮—导出记录
        b8 = new JButton("更改图片数量时间");
        b8.setBounds(170, 106, 100, 23);
//        btn2.setIcon(new ImageIcon(Login.class.getResource("/images/exit.png")));
        b8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b8) {
                    new Input().inputFacePicNum();

                }
            }
        });
        contentPane.add(b8);

    }


}