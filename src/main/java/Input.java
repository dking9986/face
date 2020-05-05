import org.bytedeco.javacv.FrameGrabber;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;

public class Input extends JFrame {
    private JPanel contentPane;
    private JButton b1, b2, b3, b4;//
    private JLabel label1, label2;
    private int LOGIN_WIDTH = 360;
    private int LOGIN_HEIGTH = 350;
    private JTextField inputtext;
    private long i;

    Connection connection;
    Statement statement;

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
        label1.setBounds(80, 76, 54, 28);
        contentPane.add(label1);


        inputtext = new JTextField();//输入框
        inputtext.setBounds(139, 80, 161, 25);
        contentPane.add(inputtext);


        b1 = new JButton("查找");
        b1.setBounds(210, 210, 80, 23);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String s = "";
                s = inputtext.getText();
                if (s.equals("")) {
                    JOptionPane.showMessageDialog(null, "输入为空");
                } else {
                    new ShowSelect(inputtext.getText());
                }

            }
        });
        contentPane.add(b1);


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
        label1 = new JLabel("请输入每个出入记录的间隔时间：");
        label1.setBounds(80, 76, 100, 28);
        contentPane.add(label1);


        inputtext = new JTextField();//输入框
        inputtext.setBounds(80, 105, 161, 25);
        contentPane.add(inputtext);


        b1 = new JButton("确定");
        b1.setBounds(210, 210, 80, 23);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = "";
                s = inputtext.getText();
                if (s.equals("")) {
                    JOptionPane.showMessageDialog(null, "输入为空");
                } else {

                    i = Long.parseLong(s);
                    System.out.println(i);
                    try {
                        connection=jdbcUtils.getConnection();
                        statement=connection.createStatement();
                        String sql="update recognizer set intervaltime="+ i +" ";
                        statement.executeUpdate(sql);
                    } catch (FrameGrabber.Exception ex) {
                        ex.printStackTrace();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }finally {
                        jdbcUtils.result(connection, statement);
                    }

                    JOptionPane.showMessageDialog(null, "更改成功");
                    dispose();
                }

            }
        });
        contentPane.add(b1);
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
        label1.setBounds(80, 76, 100, 28);
        contentPane.add(label1);


        inputtext = new JTextField();//输入框
        inputtext.setBounds(80, 105, 161, 25);
        contentPane.add(inputtext);


        b1 = new JButton("确定");
        b1.setBounds(210, 210, 80, 23);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = "";
                s = inputtext.getText();
                if (s.equals("")) {
                    JOptionPane.showMessageDialog(null, "输入为空");
                } else {

                    i = Integer.parseInt(s);
                    System.out.println(i);
                    try {
                        connection=jdbcUtils.getConnection();
                        statement=connection.createStatement();
                        String sql="update recognizer set facescount="+ i +" ";
                        statement.executeUpdate(sql);
                    }catch (Exception ex) {
                        ex.printStackTrace();
                    }finally {
                        jdbcUtils.result(connection, statement);
                    }

                    JOptionPane.showMessageDialog(null, "更改成功");
                    dispose();
                }

            }
        });
        contentPane.add(b1);
    }
}
