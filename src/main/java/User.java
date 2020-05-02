import javax.swing.*;
import java.awt.*;

public class User extends JFrame {

    private static final int GAME_WIDTH = 1100;
    private static final int GAME_HEIGTH = 600;


    /**
     * 构造方法
     */
    public  User() {
        setTitle("用户主界面");
        setSize(GAME_WIDTH, GAME_HEIGTH);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setBackground(Color.darkGray);
        setLocationRelativeTo(null);// 居中显示


    }
}
 
 
