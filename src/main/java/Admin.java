import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;

public class Admin extends JFrame {
    private JPanel contentPane;
    private JButton btn1, btn2, btn3,btn4;//登录 退出 注册
    private JLabel label1, label2;

    private int LOGIN_WIDTH = 360;
    private int LOGIN_HEIGTH = 350;


    Connection connection;
    Statement stam;

    /**
     * 构造方法
     */
    public  Admin() {
        setTitle("管理员主界面");
        setSize(LOGIN_WIDTH, LOGIN_HEIGTH);
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
        btn1 = new JButton("运行系统");
        btn1.setBounds(100, 100, 100, 23);
//        btn2.setIcon(new ImageIcon(Login.class.getResource("/images/exit.png")));
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btn1) {


                }
            }
        });
        contentPane.add(btn1);

        //按钮—导出记录
        btn2 = new JButton("查询个人");
        btn2.setBounds(100, 128, 100, 23);
//        btn2.setIcon(new ImageIcon(Login.class.getResource("/images/exit.png")));
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btn2) {


                }
            }
        });
        contentPane.add(btn2);
        //按钮—导出记录
        btn3 = new JButton("导出记录");
        btn3.setBounds(100, 156, 100, 23);
//        btn2.setIcon(new ImageIcon(Login.class.getResource("/images/exit.png")));
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btn3) {


                }
            }
        });
        contentPane.add(btn3);




        //按钮—退出
        btn4 = new JButton("退出登录");
        btn4.setBounds(210, 210, 100, 23);
//        btn2.setIcon(new ImageIcon(Login.class.getResource("/images/exit.png")));
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btn4) {
                    System.out.println("退出登录之后是什么样子");
                    new Login();
                    dispose();
                }
            }
        });
        contentPane.add(btn4);


    }


}