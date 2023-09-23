import javax.swing.JFrame;

public class TestFrame {
    public static void main(String[] args) {
        Approjectframe frame = new Approjectframe();
        frame.setSize(800, 500);   //設定寬，長
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //設定預設的關閉視窗
        frame.setVisible(true);    //視窗預設是不可見的
    }
}