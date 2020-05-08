import org.bytedeco.javacv.FrameGrabber;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class User extends JFrame {


    private JPanel contentPane;
    private JButton b1, b2,b3,b4, b5;//登录 退出 注册
    private JLabel label1, label2;

    private int LOGIN_WIDTH = 360;
    private int LOGIN_HEIGTH = 350;

    private String acut=null;
    private String name=null;
    private int num=-1;

    private Color color=new Color(56,190,248);
    private Color color1=new Color(56,140,248);
    private Color color2=new Color(56,160,248);
    private Color color3=new Color(56,180,248);
    private Color color4=new Color(130,200,248);


    public  User(int usernum, final String account, String username)  {

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
        b1.setBounds(100, 50, 120, 23);
        b1.setFont(new Font("微软雅黑", Font.BOLD,15));

        b1.setForeground(Color.WHITE);
        b1.setBackground(color1);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)  {
                if (e.getSource() == b1) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                    int facecount=new RecognizerOperation().getFaceCount();
                                try {
                                    new FaceRecog().getFace(num,name,facecount);
                                } catch (FrameGrabber.Exception ex) {
                                    ex.printStackTrace();
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
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
        b2.setFont(new Font("微软雅黑", Font.BOLD,15));
        b2.setForeground(Color.WHITE);
        b2.setBackground(color2);
        b2.setBounds(85, 100, 150, 23);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)  {
                if (e.getSource() == b2) {

                    new ShowRecord().showAccountSelect(acut,"user");

                }
            }
        });
        contentPane.add(b2);


        b3 = new JButton("修改手机号");
        b3.setBounds(100, 150, 120, 23);
        b3.setFont(new Font("微软雅黑", Font.BOLD,15));

        b3.setForeground(Color.WHITE);
        b3.setBackground(color3);
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)  {
                if (e.getSource() == b3) {
                        new Input().changePhone(acut);
                }
            }
        });
        contentPane.add(b3);

        b4 = new JButton("修改密码");
        b4.setFont(new Font("微软雅黑", Font.BOLD,15));

        b4.setForeground(Color.WHITE);
        b4.setBackground(color);
        b4.setBounds(100, 200, 120, 23);
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)  {
                if (e.getSource() == b4) {
                    new Input().changePassword(acut);
                }
            }
        });
        contentPane.add(b4);

        //按钮—退出
        b5 = new JButton("退出登录");
        b5.setFont(new Font("微软雅黑", Font.BOLD,15));

        b5.setForeground(Color.WHITE);
        b5.setBackground(color4);
        b5.setBounds(210, 250, 100, 23);
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b5) {
                    dispose();
                    new Login();
                }
            }
        });
        contentPane.add(b5);
        repaint();
    }
}
 
 
