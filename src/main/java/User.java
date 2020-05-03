import org.bytedeco.javacv.FrameGrabber;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class User extends JFrame {

    private JPanel contentPane;
    private JButton btn1, btn2, btn3,btn4;//登录 退出 注册
    private JLabel label1, label2;

    private int LOGIN_WIDTH = 360;
    private int LOGIN_HEIGTH = 350;
    private String name=null;
    private int num=-1;

    /**
     * 构造方法
     */
    public  User(String username, int usernum) throws FrameGrabber.Exception, InterruptedException {

        setTitle("管理员主界面");
        setSize(LOGIN_WIDTH, LOGIN_HEIGTH);
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

        //按钮—导出记录
        btn3 = new JButton("导出记录");
        btn3.setBounds(100, 156, 100, 23);
//        btn2.setIcon(new ImageIcon(Login.class.getResource("/images/exit.png")));
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)  {
                if (e.getSource() == btn3) {

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    new FaceRecog().getFace(name,num);
                                } catch (FrameGrabber.Exception ex) {
                                    ex.printStackTrace();
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }

                                new FaceRecog().faceTrain();
                            }
                        }).start();



                }
            }
        });
        contentPane.add(btn3);

    }
}
 
 
