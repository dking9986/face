import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FilePathChooser extends JFrame {
    private JLabel label=new JLabel("所选文件路径：");
    private JTextField jTextField =new JTextField(25);//输入框
    private JPanel panel;
    private JButton b1, b2;
    private Color color=new Color(56,189,248);

    String path="";

    public void outputFilePathChooser()
    {
        setTitle("文件导出");
        panel =new JPanel();
        panel.add(label);
        panel.add(jTextField);
        b1=new JButton("浏览");
        b2=new JButton("保存");
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
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RecordOperation().writeDbExcel(path);
                JOptionPane.showMessageDialog(null, "保存完成!");
                dispose();
            }
        });
        panel.add(b1);
        panel.add(b2);
        add(panel);
        pack();    //自动调整大小
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(300,300);
    }


    public void savePathChooser(final String choosetype)
    {
        setTitle(choosetype);
        panel =new JPanel();
        panel.add(label);
        panel.add(jTextField);
        b1=new JButton("浏览");
        b2=new JButton("确定");
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
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer flag=new RecognizerOperation().changePath(path,choosetype);
                if (flag==1) {
                    JOptionPane.showMessageDialog(null, "更改完成!");
                    dispose();
                }else {
                    JOptionPane.showMessageDialog(null, "更改失败!");
                }
            }
        });
        panel.add(b1);
        panel.add(b2);
        add(panel);
        pack();    //自动调整大小
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(300,300);
    }
}
