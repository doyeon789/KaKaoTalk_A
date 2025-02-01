package Icon;

import Home.Home_Page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class K_Icon {

    private static boolean isHome_PageOpen = false; // private으로 변경

    public K_Icon() {

        if (Taskbar.isTaskbarSupported()) {
            Taskbar taskbar = Taskbar.getTaskbar();
            Image originalIcon = Toolkit.getDefaultToolkit().getImage("image/Icon/icon.png");
            Image scaledIcon = originalIcon.getScaledInstance(64, 64, Image.SCALE_SMOOTH); // 크기 조정

            try {
                taskbar.setIconImage(scaledIcon);
            } catch (UnsupportedOperationException | SecurityException e) {
                e.printStackTrace();
            }
        }


        JFrame frame = new JFrame("");

        // 사이즈 설정
        frame.setSize(72, 140);
        frame.setLocation(1581, 713);
        frame.setUndecorated(true);
        frame.setBackground(new Color(0,0,0,0));
        frame.setResizable(false);

        JPanel Icon_panel = new JPanel();
        Icon_panel.setLayout(null);
        Icon_panel.setOpaque(false);
        Icon_panel.setBounds(0, 0, 72, 104);  // 크기 설정

        JLabel Icon_image = new JLabel();
        Icon_image.setOpaque(false);
        Icon_image.setBounds(8, 0, 64, 64);
        ImageIcon Icon_imageI = new ImageIcon("image/Icon/icon.png");
        Image Icon_image_img = Icon_imageI.getImage();
        Image Icon_image_logo = Icon_image_img.getScaledInstance(216/4, 216/4, Image.SCALE_SMOOTH);
        Icon_image.setIcon(new ImageIcon(Icon_image_logo));

        Icon_image.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                boolean temp_isHome_PageOpne = isHome_PageOpen();
                if(!temp_isHome_PageOpne){
                    isHome_PageOpen = true;
                    Home_Page.Home();
                }
            }
        });

        JLabel madeby = new JLabel("made by");
        madeby.setBounds(10,63,64,30);
        madeby.setForeground(Color.WHITE);
        try {
            Font Pretendard = Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/Pretendard-Bold.otf"));
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(Pretendard);
            madeby.setFont(Pretendard.deriveFont(12f));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        JLabel Doyeon = new JLabel("Doyeon");
        Doyeon.setBounds(14,80,64,30);
        Doyeon.setForeground(Color.WHITE);
        try {
            Font Pretendard = Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/Pretendard-Bold.otf"));
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(Pretendard);
            Doyeon.setFont(Pretendard.deriveFont(12f));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        Icon_panel.add(Doyeon);
        Icon_panel.add(madeby);
        Icon_panel.add(Icon_image);
        frame.add(Icon_panel);
        frame.setVisible(true);
    }
    // Getter 메서드
    public static boolean isHome_PageOpen() {
        return isHome_PageOpen;
    }

    // Setter 메서드
    public static void setHome_PageOpen(boolean home_PageOpen) {
        isHome_PageOpen = home_PageOpen;
    }
}