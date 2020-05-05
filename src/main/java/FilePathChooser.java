import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;

public class FilePathChooser extends JFrame {
    private JLabel label=new JLabel("所选文件路径：");
    private JTextField jTextField =new JTextField(25);//输入框
    private JButton button=new JButton("浏览");
    private JPanel panel;
    private JButton b1, b2, b3, b4;
    private JLabel label1, label2;

    private int LOGIN_WIDTH = 360;
    private int LOGIN_HEIGTH = 350;
    String path="";

    Connection connection;
    Statement statement;

    public void outputFilePathChooser()
    {
        panel =new JPanel();
        panel.add(label);
        panel.add(jTextField);
        b1=new JButton("浏览");
        b2=new JButton("保存");
        panel.add(b1);
        panel.add(b2);
        add(panel);
        pack();    //自动调整大小
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(300,300);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc=new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int val=fc.showOpenDialog(null);    //文件打开对话框
                if(val==JFileChooser.APPROVE_OPTION)
                {
                    //正常选择文件
                    jTextField.setText(fc.getSelectedFile().toString());
                    path=fc.getSelectedFile().toString();
                }
                else
                {
                    //未正常选择文件，如选择取消按钮
                    jTextField.setText("未选择文件夹");
                }
            }
        });    //监听按钮事件
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FaceRecord().writeDbExcel(path);
                JOptionPane.showMessageDialog(null, "保存完成!");
            }
        });
    }


    public void savePathChooser(final String choosetype)
    {
        panel =new JPanel();
        panel.add(label);
        panel.add(jTextField);
        b1=new JButton("浏览");
        b2=new JButton("确定");
        panel.add(b1);
        panel.add(b2);
        add(panel);
        pack();    //自动调整大小
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(300,300);


        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc=new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int val=fc.showOpenDialog(null);    //文件打开对话框
                if(val==JFileChooser.APPROVE_OPTION)
                {
                    //正常选择文件
                    jTextField.setText(fc.getSelectedFile().toString());
                    path=fc.getSelectedFile().toString().replaceAll("\\\\","\\\\\\\\");//需要转义不然在数据库中无法识别
                }
                else
                {
                    //未正常选择文件，如选择取消按钮
                    jTextField.setText("未选择文件夹");
                }
            }
        });    //监听按钮事件
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    connection=jdbcUtils.getConnection();
                    statement=connection.createStatement();
                    String sql="update recognizer set "+choosetype+"='"+ path +" '";
                    statement.executeUpdate(sql);
                    System.out.println(path);
                }catch (Exception ex) {
                    ex.printStackTrace();
                }finally {
                    jdbcUtils.result(connection, statement);
                }
                JOptionPane.showMessageDialog(null, "更改完成!");
            }
        });
    }



}
