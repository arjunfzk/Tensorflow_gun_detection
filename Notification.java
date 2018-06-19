import javax.swing.*;

public class Notification extends JFrame {
    private JLabel alert;
    public Notification(String message) {
        setLayout(null);

        alert = new JLabel(message);
        add(alert);
        alert.setBounds(40, 40, 100, 50);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(1000, 30, 400, 150);
    }


    public static void main(String[] args) {
        new Notification("Baby not found");
    }

}
