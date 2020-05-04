import org.bytedeco.javacv.FrameGrabber;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Admin extends JFrame {
    private JPanel contentPane;
    private JButton b1, b2, b3, b4;//
    private JLabel label1, label2;

    private int LOGIN_WIDTH = 360;
    private int LOGIN_HEIGTH = 350;



    public  Admin(int usernum,String account,String username) {
        setTitle("管理员主界面");
        setBounds(500, 300, LOGIN_WIDTH, LOGIN_HEIGTH);  //设置窗体坐标以及打下
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBackground(Color.darkGray);

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        //按钮—运行系统
        b1 = new JButton("运行系统");
        b1.setBounds(100, 100, 100, 23);
//        btn2.setIcon(new ImageIcon(Login.class.getResource("/images/exit.png")));
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b1) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                new FaceRecog().faceRec();
                            } catch (FrameGrabber.Exception ex) {
                                ex.printStackTrace();
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }

                        }
                    }).start();

                }
            }
        });
        contentPane.add(b1);

        //按钮—导出记录
        b2 = new JButton("查询个人");
        b2.setBounds(100, 128, 100, 23);
//        btn2.setIcon(new ImageIcon(Login.class.getResource("/images/exit.png")));
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b2) {


                }
            }
        });
        contentPane.add(b2);
        //按钮—导出记录
        b3 = new JButton("导出记录");
        b3.setBounds(100, 156, 100, 23);
//        btn2.setIcon(new ImageIcon(Login.class.getResource("/images/exit.png")));
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b3) {
                    new FilePathChooser();
                }
            }
        });
        contentPane.add(b3);




        //按钮—退出
        b4 = new JButton("退出登录");
        b4.setBounds(210, 210, 100, 23);
//        btn2.setIcon(new ImageIcon(Login.class.getResource("/images/exit.png")));
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b4) {

                    new Login();
                    dispose();
                }
            }
        });
        contentPane.add(b4);


    }


}