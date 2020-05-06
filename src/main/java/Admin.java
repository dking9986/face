import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Admin extends JFrame {
    private JPanel contentPane;
    private JButton b1, b2, b3, b4,b5,b6,b7,b8,b9,b10,b11,b12;//
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
                                boolean b = new FaceRecog().faceRec(resultSet.getLong(1));
                                if (!b){
                                    JOptionPane.showMessageDialog(null, "识别器未加载，请先训练识别器");
                                }
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

                    new ShowRecord().showAllRecord();

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
                    new FilePathChooser().outputFilePathChooser();
                }
            }
        });
        contentPane.add(b4);



        b11 = new JButton("查看所有用户信息");
        b11.setBounds(20, 162, 130, 23);
//        btn2.setIcon(new ImageIcon(Login.class.getResource("/images/exit.png")));
        b11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b11) {
                    new ShowRecord().showAllUser();
                }
            }
        });
        contentPane.add(b11);





        //按钮—退出
        b5 = new JButton("退出登录");
        b5.setBounds(210, 250, 100, 23);
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
        b6.setBounds(170, 50, 120, 23);
//        btn2.setIcon(new ImageIcon(Login.class.getResource("/images/exit.png")));
        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b6) {

                    boolean b = new FaceRecog().faceTrain();
                    if (b) {
                        JOptionPane.showMessageDialog(null, "校正完成！");
                    }else {
                        JOptionPane.showMessageDialog(null, "训练集出错！");
                    }
                }
            }
        });
        contentPane.add(b6);
        //按钮—导出记录
        b7 = new JButton("更改间隔时间");
        b7.setBounds(170, 78, 120, 23);
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
        b8 = new JButton("更改每人图片数量");
        b8.setBounds(170, 106, 130, 23);
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

        //按钮—导出记录
        b9 = new JButton("更改识别器保存位置");
        b9.setBounds(170, 136, 150, 23);
//        btn2.setIcon(new ImageIcon(Login.class.getResource("/images/exit.png")));
        b9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b9) {
                    new FilePathChooser().savePathChooser("recognizerpath");

                }
            }
        });
        contentPane.add(b9);
        //按钮—导出记录
        b10 = new JButton("更改人脸图保存位置");
        b10.setBounds(170, 166, 150, 23);
//        btn2.setIcon(new ImageIcon(Login.class.getResource("/images/exit.png")));
        b10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b10) {
                    new FilePathChooser().savePathChooser("facespath");
                }
            }
        });
        contentPane.add(b10);


        b12 = new JButton("更改陌生人保存位置");
        b12.setBounds(170, 199, 150, 23);
//        btn2.setIcon(new ImageIcon(Login.class.getResource("/images/exit.png")));
        b12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b12) {
                    new FilePathChooser().savePathChooser("strangerfacespath");
                }
            }
        });
        contentPane.add(b12);

    }


}