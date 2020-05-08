import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Admin extends JFrame {
    private JPanel contentPane;
    private JButton b1, b2, b3, b4,b5,b6,b7,b8,b9,b10,b11,b12,b13;//

    private int LOGIN_WIDTH = 500;
    private int LOGIN_HEIGTH = 400;
    private Color color=new Color(56,189,248);
    private Color color1=new Color(56,140,255);
    private Color color2=new Color(56,120,255);



    public  Admin(int usernum, final String account, String username) {
        setTitle("管理员:"+username);
        setBounds(500, 300, LOGIN_WIDTH, LOGIN_HEIGTH);  //设置窗体坐标以及打下
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.darkGray);

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //按钮 运行系统
        b1 = new JButton("运行人脸识别系统");
        b1.setBounds(100, 10, 300, 25);
        b1.setFont(new Font("微软雅黑", Font.BOLD,15));
        b1.setForeground(Color.WHITE);
        b1.setBackground(color1);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b1) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Long intervaltime=new RecognizerOperation().getIntervaltime();
                            boolean b = false;
                            try {
                                b = new FaceRecog().faceRec(intervaltime);
                                if (!b){
                                    JOptionPane.showMessageDialog(null, "识别器未加载，请先训练识别器");
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }).start();
                }
            }
        });
        contentPane.add(b1);

//        按钮 查询个人
        b2 = new JButton("查询个人");
        b2.setBounds(60, 50, 100, 23);
        b2.setFont(new Font("微软雅黑", Font.BOLD,15));
        b2.setForeground(Color.WHITE);
        b2.setBackground(color);
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
        b3.setBounds(60, 88, 100, 23);
        b3.setFont(new Font("微软雅黑", Font.BOLD,15));
        b3.setForeground(Color.WHITE);
        b3.setBackground(color);
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b3) {

                    new ShowRecord().showAllRecord();

                }
            }
        });
        contentPane.add(b3);

        //按钮 导出记录
        b4 = new JButton("导出记录");
        b4.setBounds(60, 126, 100, 23);
        b4.setFont(new Font("微软雅黑", Font.BOLD,15));
        b4.setForeground(Color.WHITE);
        b4.setBackground(color);
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b4) {
                    new FilePathChooser().outputFilePathChooser();
                }
            }
        });
        contentPane.add(b4);

        //按钮 查看所有用户信息
        b11 = new JButton("查看所有用户信息");
        b11.setBounds(25, 162, 180, 23);
        b11.setFont(new Font("微软雅黑", Font.BOLD,15));
        b11.setForeground(Color.WHITE);
        b11.setBackground(color);
        b11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b11) {
                    new ShowRecord().showAllUser();
                }
            }
        });
        contentPane.add(b11);

        //按钮 退出登录
        b5 = new JButton("退出登录");
        b5.setBounds(350, 300, 100, 23);
        b5.setFont(new Font("微软雅黑", Font.BOLD,15));
        b5.setForeground(Color.WHITE);
        b5.setBackground(color2);
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

        //按钮 校正识别系统
        b6 = new JButton("校正识别器");
        b6.setBounds(56, 200, 110, 23);
        b6.setFont(new Font("微软雅黑", Font.BOLD,15));
        b6.setForeground(Color.WHITE);
        b6.setBackground(color);
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


        //按钮 更改间隔时间
        b7 = new JButton("更改间隔时间");
        b7.setBounds(280, 50, 130, 23);
        b7.setFont(new Font("微软雅黑", Font.BOLD,15));
        b7.setForeground(Color.WHITE);
        b7.setBackground(color);
        b7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b7) {
                    new Input().inputIntervalTime();
                }
            }
        });
        contentPane.add(b7);

        //按钮 更改每人图片数量
        b8 = new JButton("更改每人图片数量");
        b8.setBounds(260, 88, 170, 23);
        b8.setFont(new Font("微软雅黑", Font.BOLD,15));
        b8.setForeground(Color.WHITE);
        b8.setBackground(color);
        b8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b8) {
                    new Input().inputFacePicNum();
                }
            }
        });
        contentPane.add(b8);

        //按钮 更改识别器保存位置
        b9 = new JButton("更改识别器保存位置");
        b9.setBounds(250, 126, 190, 23);
        b9.setFont(new Font("微软雅黑", Font.BOLD,15));
        b9.setForeground(Color.WHITE);
        b9.setBackground(color);
        b9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b9) {
                    new FilePathChooser().savePathChooser("recognizerpath");
                }
            }
        });
        contentPane.add(b9);

        //按钮 更改人脸图保存位置
        b10 = new JButton("更改人脸图保存位置");
        b10.setBounds(250, 162, 190, 23);
        b10.setFont(new Font("微软雅黑", Font.BOLD,15));
        b10.setForeground(Color.WHITE);
        b10.setBackground(color);
        b10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b10) {
                    new FilePathChooser().savePathChooser("facespath");
                }
            }
        });
        contentPane.add(b10);

        //按钮 更改陌生人保存位置
        b12 = new JButton("更改陌生人保存位置");
        b12.setBounds(250, 200, 190, 23);
        b12.setFont(new Font("微软雅黑", Font.BOLD,15));
        b12.setForeground(Color.WHITE);
        b12.setBackground(color);
        b12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b12) {
                    new FilePathChooser().savePathChooser("strangerfacespath");
                }
            }
        });
        contentPane.add(b12);


        b13 = new JButton("修改密码");
        b13.setBounds(40, 300, 100, 23);
        b13.setFont(new Font("微软雅黑", Font.BOLD,15));
        b13.setForeground(Color.WHITE);
        b13.setBackground(color2);
        b13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)  {
                if (e.getSource() == b13) {
                    new Input().changePassword(account);
                }
            }
        });
        contentPane.add(b13);
        repaint();//添加组件后重绘就可以直接显示
    }

}