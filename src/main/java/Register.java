import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;

public class Register  extends JFrame {

    private int LOGIN_WIDTH=360;
    private int LOGIN_HEIGTH=350;
    private JPanel contentPane;
    private JTextField account;
    private JTextField password;
    private JTextField userName;
    private JButton btn3,btn4;
    private JLabel label3,label4;
    private JCheckBox chkbox;
    Connection connection;
    Statement statement;

    public void insertUser() {

        setTitle("注册");
        setBounds(500, 300, LOGIN_WIDTH, LOGIN_HEIGTH	);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
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
        label3=new JLabel("账号");
        label3.setBounds(80,76, 54, 28);
//        label3.setIcon(new ImageIcon(Login.class.getResource("/images/user.png")));
        contentPane.add(label3);


        //密码标签
        label4=new JLabel("密码");
        label4.setBounds(80, 135, 54, 28);
//        label4.setIcon(new ImageIcon(Login.class.getResource("/images/psw.png")));
        contentPane.add(label4);

        label3=new JLabel("姓名");
        label3.setBounds(80,194, 54, 28);
//        label3.setIcon(new ImageIcon(Login.class.getResource("/images/user.png")));
        contentPane.add(label3);

        //账号输入框
        account =new JTextField();
        account.setBounds(139, 80, 161, 25);
        contentPane.add(account);

        //密码输入框
        password=new JPasswordField();
        password.setBounds(139, 140, 161, 25);
        contentPane.add(password);

        //姓名输入框
        userName =new JTextField();
        userName.setBounds(139, 200, 161, 25);
        contentPane.add(userName);

        chkbox=new JCheckBox("管理员");
        chkbox.setBounds(150, 230,65,25);
        contentPane.add(chkbox);

        btn3=new JButton("注   册");
        btn3.setBounds(95, 270, 80, 23);
//        btn3.setIcon(new ImageIcon(Login.class.getResource("/images/insist.png")));
        btn3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if(e.getSource()==btn3) {
                    try {

                        //加载数据库驱动
                        connection = jdbcUtils.getConnection();
                        //创建执行sql语句的对象
                        statement = connection.createStatement();
                        if (chkbox.isSelected()) {
                            //编写sql语句
                            String sql = "insert into user values(null,'" + account.getText() + "','" + password.getText() + "',0,'"+ userName.getText()+"')";
                            //执行sql语句
                            statement.execute(sql);
                            JOptionPane.showMessageDialog(null, "管理员注册成功!");
                            dispose();  //关闭注册窗体
                            new Login();  //打开登录窗体
                        }else{
                            String sql = "insert into user values(null,'" + account.getText() + "','" + password.getText() + "',1,'"+ userName.getText()+"')";
                            //执行sql语句
                            statement.execute(sql);
                            JOptionPane.showMessageDialog(null, "用户注册成功!");
                            dispose();  //关闭注册窗体

                            new Login();  //打开登录窗体
                        }

                    }catch (Exception e1) {
                        e1.printStackTrace();
                    }finally {
                        jdbcUtils.result(connection, statement);
                    }

                }

            }
        });

        contentPane.add(btn3);
        btn4=new JButton("退  出");
        btn4.setBounds(210, 270, 80, 23);
        //btn4.setIcon( new ImageIcon(Login.class.getResource("/images/exit.png")));
        btn4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if(e.getSource()==btn4) {

                    dispose();
                }
            }
        });
        contentPane.add(btn4);

    }


}
