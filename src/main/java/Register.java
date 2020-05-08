import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register  extends JFrame {

    private int LOGIN_WIDTH=360;
    private int LOGIN_HEIGTH=350;
    private JPanel contentPane;
    private JTextField account;
    private JTextField password;
    private JTextField userName;
    private JTextField userPhone;

    private JButton btn3,btn4;
    private JLabel lable1,lable2,label3,label4;
    private JCheckBox chkbox;
    private Color color=new Color(130,200,248);

    public void insertUser() {

        setTitle("注册");
        setBounds(500, 300, LOGIN_WIDTH, LOGIN_HEIGTH	);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        /**
         * 添加一个面板容器到窗体中
         */
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //账号标签
        lable1=new JLabel("账号");
        lable1.setBounds(80,50, 54, 28);
        contentPane.add(lable1);


        //密码标签
        lable2=new JLabel("密码");
        lable2.setBounds(80, 100, 54, 28);
        contentPane.add(lable2);

        label3=new JLabel("姓名");
        label3.setBounds(80,150, 54, 28);
        contentPane.add(label3);

        label4=new JLabel("联系电话");
        label4.setBounds(80,200, 54, 28);
        contentPane.add(label4);

        //账号输入框
        account =new JTextField();
        account.setBounds(139, 50, 161, 25);
        contentPane.add(account);

        //密码输入框
        password=new JPasswordField();
        password.setBounds(139, 100, 161, 25);
        contentPane.add(password);

        //姓名输入框
        userName =new JTextField();
        userName.setBounds(139, 150, 161, 25);
        contentPane.add(userName);

        //电话输入框
        userPhone =new JTextField();
        userPhone.setBounds(139, 200, 161, 25);
        contentPane.add(userPhone);

        chkbox=new JCheckBox("管理员");
        chkbox.setBounds(180, 230,65,25);
        contentPane.add(chkbox);


        btn3=new JButton("注   册");
        btn3.setBounds(95, 270, 80, 23);
        btn3.setFont(new Font("微软雅黑", Font.BOLD,15));
        btn3.setForeground(Color.WHITE);
        btn3.setBackground(color);
        btn3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==btn3) {

                    Integer flag = new UserOperation().getUsernum(account.getText());
                    if (flag!=-1){
                        JOptionPane.showMessageDialog(null,"账号已存在，不可重复");
                        account.setText("");
                    }
                    else if (account.getText().equals("")||password.getText().equals("")||userName.getText().equals("")||userPhone.getText().equals("")){
                        JOptionPane.showMessageDialog(null,"请填写全部信息");
                    }
                    else {
                        if (chkbox.isSelected()) {
                            new UserOperation().insertAdmin(account.getText(),userName.getText(),password.getText(),userPhone.getText());
                            JOptionPane.showMessageDialog(null, "管理员注册成功!");
                            dispose();  //关闭注册窗体
                            new Login();  //打开登录窗体
                        } else {
                            new UserOperation().insertUser(account.getText(),userName.getText(),password.getText(),userPhone.getText());
                            JOptionPane.showMessageDialog(null, "用户注册成功!");
                            dispose();  //关闭注册窗体
                            new Login();  //打开登录窗体
                        }
                    }
                }
            }
        });
        contentPane.add(btn3);


        btn4=new JButton("退  出");
        btn4.setBounds(210, 270, 80, 23);
        btn4.setFont(new Font("微软雅黑", Font.BOLD,15));
        btn4.setForeground(Color.WHITE);
        btn4.setBackground(color);
        btn4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==btn4) {
                    dispose();
                }
            }
        });
        contentPane.add(btn4);
        repaint();
    }
}
