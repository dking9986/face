import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class Login extends JFrame {//登录界面类

    private JPanel contentPane;
    private JButton btn1, btn2, btn3;//登录 退出 注册
    private JTextField userName;
    private JTextField password;
    private JLabel label1, label2;

    private int LOGIN_WIDTH = 360;
    private int LOGIN_HEIGTH = 350;


    Connection connection;
    Statement stam;

    public  Login() {

        setTitle("人脸识别出入管理系统");  //设置窗体标题
        setBounds(100, 50, LOGIN_WIDTH, LOGIN_HEIGTH);  //设置窗体坐标以及打下
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //设置窗体可关闭
        setResizable(false);  //设置窗体大小不可以改变
        setVisible(true);
            //设置窗体可见
        //设置窗体标题图标
        /*setIconImage(
                Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/images/log.jpg"))
        );*/
        /**
         * 添加一个面板容器到窗体中
         */
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        //账号标签
        label1 = new JLabel("账号");
        label1.setBounds(80, 76, 54, 28);
//        label1.setIcon(new ImageIcon(Login.class.getResource("/images/user.png")));
        contentPane.add(label1);


        //密码标签
        label2 = new JLabel("密码");
        label2.setBounds(80, 135, 54, 28);
//        label2.setIcon(new ImageIcon(Login.class.getResource("/images/psw.png")));
        contentPane.add(label2);

        //账号输入框
        userName = new JTextField();
        userName.setBounds(139, 80, 161, 25);
        contentPane.add(userName);

        //密码输入框
        password = new JPasswordField();
        password.setBounds(139, 140, 161, 25);

        contentPane.add(password);




        //按钮—登录
        btn1 = new JButton("登   录");
        btn1.setBounds(95, 210, 80, 23);
//        btn1.setIcon(new ImageIcon(Login.class.getResource("/images/btn1.png")));
        btn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btn1) {
                    try {
                        connection = jdbcUtils.getConnection();//获取数据库连接
                        stam = (Statement) connection.createStatement();  //创建sql语句执行对象
                        //编写sql语句
                        String sql = "select * from user where username='" + userName.getText() + "'  and password='" + password.getText() + "'     ";
                        //执行sql语句
                        ResultSet rs = stam.executeQuery(sql);
                        if (rs.next()) {
                            Integer t=Integer.parseInt(rs.getString("type"));
                            dispose();//关闭当前窗口
                            if (t==1){
                                new User().setVisible(true);//类型1进入用户界面
                            }
                            else if (t==0){
                                new Admin().setVisible(true);//类型2进入管理员界面
                            }
                            else {
                                System.out.println("不是该系统用户");
                            }

                        }else {
                            System.out.println("无此人");
                        }
                    } catch (Exception e0) {
                        e0.printStackTrace();
                    } finally {
                        jdbcUtils.result(connection, stam);
                    }
                }
            }
        });
        contentPane.add(btn1);
        //按钮—退出
        btn2 = new JButton("退  出");
        btn2.setBounds(210, 210, 80, 23);
//        btn2.setIcon(new ImageIcon(Login.class.getResource("/images/exit.png")));
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btn2) {
                    System.exit(0);
                }
            }
        });
        contentPane.add(btn2);


        //按钮-注册
        btn3 = new JButton("注        册");
        btn3.setBounds(95, 240, 200, 23);
//        btn3.setIcon(new ImageIcon(Login.class.getResource("/images/regier.png")));
        btn3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();//关闭登录窗体
                new Register().insertUser(); // 打开注册窗体

            }
        });
        contentPane.add(btn3);
        repaint();//添加组件后重绘就可以直接显示

    }

    public static void main(String[] args) throws Exception {
        new Login();
        new FaceRecog().facetrain();

        Date time = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String current = sdf.format(time);
        System.out.println(current);
    }


}


