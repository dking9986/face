import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Input extends JFrame {
    private JPanel contentPane;
    private JButton b1;
    private JLabel label1, label2,label3,label4,label5;
    private int LOGIN_WIDTH = 360;
    private int LOGIN_HEIGTH = 350;
    private JTextField inputtext1,inputtext2,inputtext3,inputtext4,inputtext5;
    private String account;
    private Color color=new Color(130,200,248);



    public void inputName(){
        setBounds(350,360,LOGIN_WIDTH,LOGIN_HEIGTH);
        setLocation(350,350);
        setDefaultCloseOperation(new JFrame().DISPOSE_ON_CLOSE);  //设置窗体可关闭
        setResizable(false);  //设置窗体大小不可以改变
        setVisible(true);

        contentPane =new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        label1 = new JLabel("请输入要查找的账号：");
        label1.setFont(new Font("微软雅黑", Font.BOLD, 13));
        label1.setBounds(70, 70, 150, 30);
        contentPane.add(label1);

        inputtext1 = new JTextField();//输入框
        inputtext1.setBounds(80, 105, 161, 25);
        contentPane.add(inputtext1);

        b1 = new JButton("查找");
        b1.setBounds(210, 210, 80, 23);
        b1.setFont(new Font("微软雅黑", Font.BOLD,15));
        b1.setForeground(Color.WHITE);
        b1.setBackground(color);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = "";
                s = inputtext1.getText();
                if (s.equals("")) {
                    JOptionPane.showMessageDialog(null, "输入为空");
                } else {
                    new ShowRecord().showAccountSelect(inputtext1.getText(),"admin");
                }

            }
        });
        contentPane.add(b1);
        repaint();
    }


    public void inputIntervalTime() {
        setBounds(350,360,LOGIN_WIDTH,LOGIN_HEIGTH);
        setLocation(350,350);
        setDefaultCloseOperation(new JFrame().DISPOSE_ON_CLOSE);  //设置窗体可关闭
        setResizable(false);  //设置窗体大小不可以改变
        setVisible(true);

        contentPane =new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //输入间隔时间
        label1 = new JLabel("请输入每个人出入记录的最小间隔时间：");
        label1.setFont(new Font("微软雅黑", Font.BOLD, 13));
        label1.setBounds(40, 76, 250, 28);
        contentPane.add(label1);

        inputtext1 = new JTextField();//输入框
        inputtext1.setBounds(80, 105, 161, 25);
        contentPane.add(inputtext1);

        b1 = new JButton("确定");
        b1.setBounds(210, 210, 80, 23);
        b1.setFont(new Font("微软雅黑", Font.BOLD,15));
        b1.setForeground(Color.WHITE);
        b1.setBackground(color);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = "";
                s = inputtext1.getText();
                if (s.equals("")) {
                    JOptionPane.showMessageDialog(null, "输入为空");
                } else {

                    long i = Long.parseLong(s);
                    Integer flag=new RecognizerOperation().changeIntervalTime(i);
                    if (flag==1){
                        JOptionPane.showMessageDialog(null, "更改成功");
                        dispose();
                    }else {
                        JOptionPane.showMessageDialog(null, "更改失败");
                    }

                }
            }
        });
        contentPane.add(b1);
        repaint();
    }


    public void inputFacePicNum() {
        setBounds(350,360,LOGIN_WIDTH,LOGIN_HEIGTH);
        setLocation(350,350);
        setDefaultCloseOperation(new JFrame().DISPOSE_ON_CLOSE);  //设置窗体可关闭
        setResizable(false);  //设置窗体大小不可以改变
        setVisible(true);

        contentPane =new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //输入间隔时间
        label1 = new JLabel("请输入每个人保存图片数量（越多越准越慢）：");
        label1.setFont(new Font("微软雅黑", Font.BOLD, 13));
        label1.setBounds(40, 76, 280, 28);
        contentPane.add(label1);


        inputtext1 = new JTextField();//输入框
        inputtext1.setBounds(80, 105, 161, 25);
        contentPane.add(inputtext1);


        b1 = new JButton("确定");
        b1.setBounds(210, 210, 80, 23);
        b1.setFont(new Font("微软雅黑", Font.BOLD,15));
        b1.setForeground(Color.WHITE);
        b1.setBackground(color);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = "";
                s = inputtext1.getText();
                if (s.equals("")) {
                    JOptionPane.showMessageDialog(null, "输入为空");
                } else {

                    Integer picnum = Integer.parseInt(s);
                    Integer flag=new RecognizerOperation().changeFacescount(picnum);
                    if (flag==1){
                        JOptionPane.showMessageDialog(null, "更改成功");
                        dispose();
                    }else {
                        JOptionPane.showMessageDialog(null, "更改失败");
                    }

                }

            }
        });
        contentPane.add(b1);
        repaint();
    }

    //输入新记录内容
    public void inputNewRecord(){
        setBounds(350,360,LOGIN_WIDTH,LOGIN_HEIGTH);
        setLocation(350,350);
        setDefaultCloseOperation(new JFrame().DISPOSE_ON_CLOSE);  //设置窗体可关闭
        setResizable(false);  //设置窗体大小不可以改变
        setVisible(true);

        contentPane =new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        label2 = new JLabel("账  号");
        label2.setFont(new Font("微软雅黑", Font.BOLD,15));
        label2.setBounds(30, 70, 100, 28);
        contentPane.add(label2);

        inputtext2 = new JTextField();//输入框
        inputtext2.setBounds(120, 70, 161, 25);
        contentPane.add(inputtext2);

        label4 = new JLabel("出入类型");
        label4.setFont(new Font("微软雅黑", Font.BOLD,15));
        label4.setBounds(30, 110, 100, 28);
        contentPane.add(label4);

        inputtext4 = new JTextField();//输入框
        inputtext4.setBounds(120, 110, 161, 25);
        contentPane.add(inputtext4);

        label5 = new JLabel("时间");
        label5.setFont(new Font("微软雅黑", Font.BOLD,15));
        label5.setBounds(30, 150, 100, 28);
        contentPane.add(label5);

        inputtext5 = new JTextField();//输入框
        inputtext5.setBounds(120, 150, 161, 25);
        contentPane.add(inputtext5);

        b1=new JButton("确定");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                account=inputtext2.getText();
                String type=inputtext4.getText();
                String time=inputtext5.getText();
                Integer usernum=new UserOperation().getUsernum(account);
                String name=new UserOperation().getName(usernum);
                String phone=new UserOperation().getPhone(usernum);

                Integer flag=new RecordOperation().insertRecord(usernum,account,name,type,time,phone);
                if (flag==1){
                    JOptionPane.showMessageDialog(null, "插入成功");
                    dispose();
                    new ShowRecord().showAllRecord();
                }else {
                    JOptionPane.showMessageDialog(null, "插入失败");
                }

            }
        });
        b1.setBounds(250, 250, 80, 23);
        b1.setFont(new Font("微软雅黑", Font.BOLD,15));
        b1.setForeground(Color.WHITE);
        b1.setBackground(color);
        contentPane.add(b1);
        repaint();
    }


    public void inputNewRecord(final String account){
        setBounds(350,360,LOGIN_WIDTH,LOGIN_HEIGTH);
        setLocation(350,350);
        setDefaultCloseOperation(new JFrame().DISPOSE_ON_CLOSE);  //设置窗体可关闭
        setResizable(false);  //设置窗体大小不可以改变
        setVisible(true);

        contentPane =new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        label4 = new JLabel("出入类型");
        label4.setFont(new Font("微软雅黑", Font.BOLD,15));
        label4.setBounds(30, 70, 100, 28);
        contentPane.add(label4);


        inputtext4 = new JTextField();//输入框
        inputtext4.setBounds(120, 70, 161, 25);
        contentPane.add(inputtext4);

        label5 = new JLabel("时间");
        label5.setFont(new Font("微软雅黑", Font.BOLD,15));
        label5.setBounds(30, 110, 100, 28);
        contentPane.add(label5);


        inputtext5 = new JTextField();//输入框
        inputtext5.setBounds(120, 110, 161, 25);
        contentPane.add(inputtext5);


        b1=new JButton("确定");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    String type=inputtext4.getText();
                    String time=inputtext5.getText();

                    Integer usernum=-1;
                    String phone="";
                    String name="";

                    usernum = new UserOperation().getUsernum(account);
                    name=new UserOperation().getName(usernum);
                    phone=new UserOperation().getPhone(usernum);
                    Integer flag=new RecordOperation().insertRecord(usernum,account,name,type,time,phone);
                    if (flag==1){
                        JOptionPane.showMessageDialog(null, "插入成功");
                        dispose();
                        new ShowRecord().showAccountSelect(account,"admin");
                    }else {
                        JOptionPane.showMessageDialog(null, "插入失败");
                    }
            }
        });
        b1.setBounds(250, 250, 80, 23);
        b1.setFont(new Font("微软雅黑", Font.BOLD,15));
        b1.setForeground(Color.WHITE);
        b1.setBackground(color);
        contentPane.add(b1);
        repaint();
    }

    public void changePassword(String acc){
        account=acc;
        setBounds(350,360,LOGIN_WIDTH,LOGIN_HEIGTH);
        setLocation(350,350);
        setDefaultCloseOperation(new JFrame().DISPOSE_ON_CLOSE);  //设置窗体可关闭
        setResizable(false);  //设置窗体大小不可以改变
        setVisible(true);

        contentPane =new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        label4 = new JLabel("请输入原密码");
        label4.setFont(new Font("微软雅黑", Font.BOLD,15));
        label4.setBounds(20, 70, 100, 28);
        contentPane.add(label4);


        inputtext4 = new JTextField();//输入框
        inputtext4.setBounds(120, 70, 161, 25);
        contentPane.add(inputtext4);

        label5 = new JLabel("请输入新密码");
        label5.setFont(new Font("微软雅黑", Font.BOLD,15));
        label5.setBounds(20, 110, 100, 28);
        contentPane.add(label5);


        inputtext5 = new JTextField();//输入框
        inputtext5.setBounds(120, 110, 161, 25);
        contentPane.add(inputtext5);


        b1=new JButton("确定");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String passwordold=inputtext4.getText();
                String passwordnew=inputtext5.getText();
                Integer flag=new UserOperation().changePassword(account,passwordold,passwordnew);
                if (flag==1) {

                    JOptionPane.showMessageDialog(null, "密码修改成功");
                    dispose();
                }else if (flag==2){
                    JOptionPane.showMessageDialog(null, "原密码输入错误");
                    inputtext4.setText("");
                    inputtext5.setText("");
                }
                else if (flag==3){
                    JOptionPane.showMessageDialog(null, "输入的账号不在数据库中 无法插入");
                }

            }
        });
        b1.setBounds(250, 250, 80, 23);
        b1.setFont(new Font("微软雅黑", Font.BOLD,15));
        b1.setForeground(Color.WHITE);
        b1.setBackground(color);
        contentPane.add(b1);
        repaint();

    }
    public void changePhone(String acc){
        account=acc;
        setBounds(350,360,LOGIN_WIDTH,LOGIN_HEIGTH);
        setLocation(350,350);
        setDefaultCloseOperation(new JFrame().DISPOSE_ON_CLOSE);  //设置窗体可关闭
        setResizable(false);  //设置窗体大小不可以改变
        setVisible(true);

        contentPane =new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        label1 = new JLabel("请输入密码");
        label1.setFont(new Font("微软雅黑", Font.BOLD,15));
        label1.setBounds(20, 70, 100, 28);
        contentPane.add(label1);


        inputtext1 = new JPasswordField();//输入框
        inputtext1.setBounds(160, 70, 161, 25);
        contentPane.add(inputtext1);

        label2 = new JLabel("请输入新联系方式");
        label2.setFont(new Font("微软雅黑", Font.BOLD,15));
        label2.setBounds(20, 110, 150, 28);
        contentPane.add(label2);


        inputtext2 = new JTextField();//输入框
        inputtext2.setBounds(160, 110, 161, 25);
        contentPane.add(inputtext2);


        b1=new JButton("确定");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password=inputtext1.getText();
                String phone=inputtext2.getText();
                int flag = new UserOperation().changePhoneNumber(password, phone, account);
                if (flag==1) {
                    JOptionPane.showMessageDialog(null, "电话修改成功");
                    dispose();
                }else if (flag==2){
                    JOptionPane.showMessageDialog(null, "密码验证错误");
                    inputtext1.setText("");
                    inputtext2.setText("");
                }
                else if (flag==3){
                    JOptionPane.showMessageDialog(null, "输入的账号不在数据库中 无法插入");
                }
            }
        });
        b1.setBounds(250, 250, 80, 23);
        b1.setFont(new Font("微软雅黑", Font.BOLD,15));
        b1.setForeground(Color.WHITE);
        b1.setBackground(color);
        contentPane.add(b1);
        repaint();

    }

    public void inputNewUser() {
        setBounds(350,360,LOGIN_WIDTH,LOGIN_HEIGTH);
        setLocation(350,350);
        setDefaultCloseOperation(new JFrame().DISPOSE_ON_CLOSE);  //设置窗体可关闭
        setResizable(false);  //设置窗体大小不可以改变
        setVisible(true);

        contentPane =new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        label1 = new JLabel("账号:");
        label1.setFont(new Font("微软雅黑", Font.BOLD,15));
        label1.setBounds(50, 70, 100, 28);
        contentPane.add(label1);


        inputtext1 = new JTextField();//输入框
        inputtext1.setBounds(120, 70, 161, 25);
        contentPane.add(inputtext1);

        label2 = new JLabel("姓名:");
        label2.setFont(new Font("微软雅黑", Font.BOLD,15));
        label2.setBounds(50, 110, 100, 28);
        contentPane.add(label2);


        inputtext2 = new JTextField();//输入框
        inputtext2.setBounds(120, 110, 161, 25);
        contentPane.add(inputtext2);

        label3 = new JLabel("密码:");
        label3.setFont(new Font("微软雅黑", Font.BOLD,15));
        label3.setBounds(50, 150, 100, 28);
        contentPane.add(label3);


        inputtext3 = new JTextField();//输入框
        inputtext3.setBounds(120, 150, 161, 25);
        contentPane.add(inputtext3);


        label4 = new JLabel("电话:");
        label4.setFont(new Font("微软雅黑", Font.BOLD,15));
        label4.setBounds(50, 190, 100, 28);
        contentPane.add(label4);


        inputtext4 = new JTextField();//输入框
        inputtext4.setBounds(120, 190, 161, 25);
        contentPane.add(inputtext4);


        b1=new JButton("确定");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                account=inputtext1.getText();
                String name=inputtext2.getText();
                String password=inputtext3.getText();
                String phone=inputtext4.getText();
                Integer flag = new UserOperation().insertUser(account, name, password, phone);
                if (flag==1) {
                    JOptionPane.showMessageDialog(null, "添加成功");
                    dispose();
                    new ShowRecord().showAllUser();
                }else if (flag==2){
                    JOptionPane.showMessageDialog(null, "账号已经被使用");
                }

            }
        });
        b1.setBounds(250, 250, 80, 23);
        b1.setFont(new Font("微软雅黑", Font.BOLD,15));
        b1.setForeground(Color.WHITE);
        b1.setBackground(color);
        contentPane.add(b1);
        repaint();
    }

}
