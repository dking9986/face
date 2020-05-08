import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowRecord extends JFrame {
    private JPanel contentPane;
    private JButton insert,delete,update;
    private int LOGIN_WIDTH = 900;
    private int LOGIN_HEIGTH = 500;
    private Color color=new Color(56,189,248);
    JTable jTable = null;
    JScrollPane jsp = null;

    private DefaultTableModel mm;

    public void showAccountSelect(final String account,String type) {
            String[] col = { "出入编号", "用户编号", "账号","姓名","出入类型" ,"时间","联系电话"};
            String[][] users=new RecordOperation().selectUserRecord(account);
            mm = new DefaultTableModel(col, 0); // 定义一个表的模板
            Integer count=new RecordOperation().getCount(account);
            if (count>0){
                for (int i=0;i<count;i++){
                    String[] str_row=users[i];
                    mm.addRow(str_row);
                }
                jTable=new JTable();
                jTable.setModel(mm);

                setTitle("记录用户名："+ account+"注意修改、删除功能每次仅对选中的一行做操作");
                setBounds(350,360,LOGIN_WIDTH,LOGIN_HEIGTH);
                setResizable(true);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                jsp=new JScrollPane(jTable);
                jsp.setBackground(Color.WHITE);
                add(jsp,BorderLayout.NORTH);

                if (type.equals("admin")){//如果是管理员才显示以下按钮
                    contentPane = new JPanel();
                    insert=new JButton("添加");
                    update=new JButton("修改");
                    delete=new JButton("删除");
                    insert.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            new Input().inputNewRecord(account);
                            dispose();
                        }
                    });
                    update.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int row=jTable.getSelectedRow();
                            if (row==-1){
                                JOptionPane.showMessageDialog(null,"请选中一行");
                            }else {
                                Integer recnum=Integer.parseInt((String) mm.getValueAt(row,0));
                                Integer usernum=Integer.parseInt((String) mm.getValueAt(row,1));
                                String account=(String) mm.getValueAt(row,2);
                                String name=(String) mm.getValueAt(row,3);
                                String type=(String) mm.getValueAt(row,4);
                                String time=(String) mm.getValueAt(row,5);
                                String phone=(String) mm.getValueAt(row,6);
                                boolean b=new RecordOperation().updateRecord(recnum,usernum,account,name,type,time,phone);
                                if (b) {
                                    JOptionPane.showMessageDialog(null, "修改成功");
                                }else {
                                    JOptionPane.showMessageDialog(null, "修改失败");
                                }

                                dispose();
                                new ShowRecord().showAccountSelect(account,"admin");

                            }
                        }
                    });
                    delete.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int row=jTable.getSelectedRow();
                            if (row==-1){
                                JOptionPane.showMessageDialog(null,"请选中一行");
                            }else {
                                Integer recnum=Integer.parseInt((String) mm.getValueAt(row,0));
                                boolean b=new RecordOperation().deleteRecord(recnum);
                                //刷新表数据 重开页面
                                dispose();
                                new ShowRecord().showAccountSelect(account,"admin");
                            }
                        }
                    });
                    insert.setFont(new Font("微软雅黑", Font.BOLD,15));
                    insert.setForeground(Color.WHITE);
                    insert.setBackground(color);
                    update.setFont(new Font("微软雅黑", Font.BOLD,15));
                    update.setForeground(Color.WHITE);
                    update.setBackground(color);
                    delete.setFont(new Font("微软雅黑", Font.BOLD,15));
                    delete.setForeground(Color.WHITE);
                    delete.setBackground(color);
                    contentPane.add(insert);
                    contentPane.add(update);
                    contentPane.add(delete);



                    add(contentPane,BorderLayout.CENTER);
                }
                setVisible(true);
            } else{
                JOptionPane.showMessageDialog(null, "无此人记录！");
            }
    }


    public void showAllRecord() {

        String[] col = { "出入编号", "用户编号", "账号","姓名","出入类型" ,"时间","联系电话"};
        String[][] users=new RecordOperation().selectAllRecord();
        mm = new DefaultTableModel(col, 0); // 定义一个表的模板
        Integer count=new RecordOperation().getCount();
        if (count>0){
            for (int i=0;i<count;i++){
                String[] str_row=users[i];
                mm.addRow(str_row);
            }
                jTable=new JTable();
                jTable.setModel(mm);

                setTitle("全部记录");
                setBounds(350,360,LOGIN_WIDTH,LOGIN_HEIGTH);
                setResizable(true);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                jsp=new JScrollPane(jTable);
                jsp.setBackground(Color.WHITE);
                add(jsp,BorderLayout.NORTH);
                contentPane = new JPanel();
                insert=new JButton("添加");
                update=new JButton("修改");
                delete=new JButton("删除");
                insert.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new Input().inputNewRecord();
                        dispose();
                    }
                });
                update.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int row=jTable.getSelectedRow();
                        if (row==-1){
                            JOptionPane.showMessageDialog(null,"请选中一行");
                        }else {
                            Integer recnum=Integer.parseInt((String) mm.getValueAt(row,0));
                            Integer usernum=Integer.parseInt((String) mm.getValueAt(row,1));
                            String account=(String) mm.getValueAt(row,2);
                            String name=(String) mm.getValueAt(row,3);
                            String type=(String) mm.getValueAt(row,4);
                            String time=(String) mm.getValueAt(row,5);
                            String phone=(String) mm.getValueAt(row,6);
                            boolean b=new RecordOperation().updateRecord(recnum,usernum,account,name,type,time,phone);
                            if (b){
                                JOptionPane.showMessageDialog(null,"修改成功");
                            }
                            else {
                                JOptionPane.showMessageDialog(null,"修改失败");
                            }
                            dispose();
                            new ShowRecord().showAllRecord();

                        }
                    }
                });
                delete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int row=jTable.getSelectedRow();
                        if (row==-1){
                            JOptionPane.showMessageDialog(null,"请选中一行");
                        }else {
                            Integer recnum=Integer.parseInt((String) mm.getValueAt(row,0));
                            new RecordOperation().deleteRecord(recnum);
                            //刷新表数据 重开页面
                            dispose();
                            new ShowRecord().showAllRecord();
                        }
                    }
                });
                insert.setFont(new Font("微软雅黑", Font.BOLD,15));
                insert.setForeground(Color.WHITE);
                insert.setBackground(color);
                update.setFont(new Font("微软雅黑", Font.BOLD,15));
                update.setForeground(Color.WHITE);
                update.setBackground(color);
                delete.setFont(new Font("微软雅黑", Font.BOLD,15));
                delete.setForeground(Color.WHITE);
                delete.setBackground(color);
                contentPane.add(insert);
                contentPane.add(update);
                contentPane.add(delete);
                add(contentPane,BorderLayout.CENTER);
                setVisible(true);

            } else{
                JOptionPane.showMessageDialog(null, "无记录！");
            }

    }

    public void showAllUser() {
        String[][] users=new UserOperation().selectAll();
        String[] col = {  "用户编号", "账号","姓名","类型","电话"};
        mm = new DefaultTableModel(col, 0); // 定义一个表的模板
        Integer count=new UserOperation().getUserCount();
        if (count>0){
            for (int i=0;i<count;i++){
                String[] str_row=users[i];
                mm.addRow(str_row);
            }
                jTable=new JTable();
                jTable.setModel(mm);

                setTitle("全部记录");
                setBounds(350,360,LOGIN_WIDTH,LOGIN_HEIGTH);
                setResizable(true);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                jsp=new JScrollPane(jTable);
                jsp.setBackground(Color.WHITE);
                add(jsp,BorderLayout.NORTH);
                contentPane = new JPanel();
                insert=new JButton("添加用户");
                delete=new JButton("删除用户");
                insert.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new Input().inputNewUser();
                        dispose();
                    }
                });
                delete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int row=jTable.getSelectedRow();
                        if (row==-1){
                            JOptionPane.showMessageDialog(null,"请选中一行");
                        }else {
                                Integer usernum=Integer.parseInt((String) mm.getValueAt(row,0));
                                new UserOperation().deleteUser(usernum);
                            //刷新表数据 重开页面
                            dispose();
                            new ShowRecord().showAllUser();
                        }
                    }
                });
                insert.setFont(new Font("微软雅黑", Font.BOLD,15));
                insert.setForeground(Color.WHITE);
                insert.setBackground(color);
                delete.setFont(new Font("微软雅黑", Font.BOLD,15));
                delete.setForeground(Color.WHITE);
                delete.setBackground(color);
                contentPane.add(insert);
                contentPane.add(delete);
                add(contentPane,BorderLayout.CENTER);

                setVisible(true);
            } else{
                JOptionPane.showMessageDialog(null, "无账号记录！");
            }

    }
}