import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {//登录界面类

    private JPanel contentPane;
    private JButton b1, b2, b3;//登录 退出 注册
    private JTextField account;
    private JTextField password;
    private JLabel label1, label2;

    private int LOGIN_WIDTH = 360;
    private int LOGIN_HEIGTH = 350;
    private Color color=new Color(56,189,248);


    public  Login() {

        setTitle("人脸识别出入管理系统");  //设置窗体标题
        setBounds(500, 300, LOGIN_WIDTH, LOGIN_HEIGTH);  //设置窗体坐标以及打下
        setDefaultCloseOperation(new JFrame().EXIT_ON_CLOSE);  //设置窗体可关闭
        setResizable(false);  //设置窗体大小不可以改变
        setVisible(true);//设置窗体可见

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        //账号标签
        label1 = new JLabel("账号");
        label1.setBounds(80, 76, 54, 28);
        contentPane.add(label1);


        //密码标签
        label2 = new JLabel("密码");
        label2.setBounds(80, 135, 54, 28);
        contentPane.add(label2);

        //账号输入框
        account = new JTextField();
        account.setBounds(139, 80, 161, 25);
        contentPane.add(account);

        //密码输入框
        password = new JPasswordField();
        password.setBounds(139, 140, 161, 25);
        contentPane.add(password);

        //按钮—登录
        b1 = new JButton("登   录");//登录的时候会把用户编号传入
        b1.setBounds(95, 210, 80, 23);
        b1.setFont(new Font("微软雅黑", Font.BOLD,15));
        b1.setForeground(Color.WHITE);
        b1.setBackground(color);
        b1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b1) {
                    boolean b=new UserOperation().checkAccountandPassword(account.getText(),password.getText());
                    if(b){
                        int usernum=new UserOperation().getUsernum(account.getText());
                        String username=new UserOperation().getName(usernum);
                        String type=new UserOperation().getType(usernum);
                        dispose();//关闭当前窗口
                        if (type.equals("用户")){
                                new User(usernum,account.getText(),username).setVisible(true);//类型1进入用户界面
                        }
                        else if (type.equals("管理员")){
                            new Admin(usernum,account.getText(),username).setVisible(true);//类型2进入管理员界面
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "用户名密码错误！");

                    }
                }
            }
        });
        contentPane.add(b1);
        //按钮—退出
        b2 = new JButton("退  出");

        b2.setFont(new Font("微软雅黑", Font.BOLD,15));

        b2.setForeground(Color.WHITE);
        b2.setBackground(color);

        b2.setBounds(210, 210, 80, 23);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b2) {
                    System.exit(0);
                }
            }
        });
        contentPane.add(b2);


        //按钮-注册
        b3 = new JButton("注        册");

        b3.setFont(new Font("微软雅黑", Font.BOLD,15));
        b3.setForeground(Color.WHITE);
        b3.setBackground(color);
        b3.setBounds(95, 240, 195, 23);
        b3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();//关闭登录窗体
                new Register().insertUser(); // 打开注册窗体
            }
        });
        contentPane.add(b3);
        repaint();//添加组件后重绘就可以直接显示

    }

    public static void main(String[] args) throws Exception {
        new Login();
    }


}


