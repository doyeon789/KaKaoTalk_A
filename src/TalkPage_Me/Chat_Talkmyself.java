package TalkPage_Me;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Chat_Talkmyself {

    public static void Chat(String chat_str,JPanel Talk_Panel_me) {
        Talk_Panel_me.revalidate();
        Talk_Panel_me.repaint();


        Font font = null;
        try {
            // 커스텀 폰트 경로 설정
            font = Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/KakaoOTFRegular.otf"));
            font = font.deriveFont(20f); // 폰트 크기 20으로 설정
        }
        catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        int x_value = calculateStringWidthInPoints(chat_str, Objects.requireNonNull(font));

        Make_Background_and_Chat(x_value,chat_str,Talk_Panel_me);

        System.out.println("Text: \"" + chat_str + "\"");

    }
    private static void Make_Background_and_Chat(int x_value,String chat_str,JPanel Talk_Panel_me){
        JPanel Chat_K = new JPanel();
        Chat_K.setOpaque(false);
        Chat_K.setBounds(100,100,x_value + 26, 31);
        Chat_K.setLayout(null);

        JLabel Yellow = new JLabel();
        Yellow.setOpaque(true);
        Yellow.setBackground(new Color(254,225,10));
        Yellow.setBounds(10,7,x_value,18);

        ImageIcon LEFT_UP_I = new ImageIcon("image/TalkPage/chat/left/leftup.png");
        JLabel LEFT_UP = new JLabel(LEFT_UP_I);
        LEFT_UP.setBounds(0,0,10,8);

        ImageIcon LEFT_DOWM_I = new ImageIcon("image/TalkPage/chat/left/leftdown.png");
        JLabel LEFT_DOWM = new JLabel(LEFT_DOWM_I);
        LEFT_DOWM.setBounds(0,18,10,8);

        ImageIcon RIGHT_DOWN_I = new ImageIcon("image/TalkPage/chat/right/rightdown.png");
        JLabel RIGHT_DOWN = new JLabel(RIGHT_DOWN_I);
        RIGHT_DOWN.setBounds(10+x_value,19,16,7);

        ImageIcon LEFT_I = new ImageIcon("image/TalkPage/chat/left/left.png");
        JLabel LEFT = new JLabel(LEFT_I);
        LEFT.setBounds(0,8,10,14);

        // 조건에 따라 바뀔예정 ㅗㅗㅗㅗ
        ImageIcon RIGHT_UP_I = new ImageIcon("image/TalkPage/chat/right/rightup2.png");
        JLabel RIGHT_UP = new JLabel(RIGHT_UP_I);
        RIGHT_UP.setBounds(10+x_value,0,16,8);

        ImageIcon RIGHT_I = new ImageIcon("image/TalkPage/chat/right/right2.png");
        JLabel RIGHT = new JLabel(RIGHT_I);
        RIGHT.setBounds(10+x_value,8,16,13);

        for(int i=0;i<chat_str.length();i++){
            ImageIcon UP_I = new ImageIcon("image/TalkPage/chat/up_down/up.png");
            JLabel UP = new JLabel(UP_I);
            UP.setBounds(10 + (12*i),0,12,7);

            ImageIcon DOWN_I = new ImageIcon("image/TalkPage/chat/up_down/down.png");
            JLabel DOWN = new JLabel(DOWN_I);
            DOWN.setBounds(10 + (12*i),19,12,7);

            Chat_K.add(DOWN);

            Chat_K.add(UP);
        }

        JLabel STR = new JLabel(chat_str);
        STR.setBounds(10,5,x_value,16);

        Chat_K.add(STR,0);
        Chat_K.add(LEFT);
        Chat_K.add(RIGHT);
        Chat_K.add(RIGHT_DOWN);
        Chat_K.add(RIGHT_UP);
        Chat_K.add(LEFT_DOWM);
        Chat_K.add(LEFT_UP);
        Chat_K.add(Yellow);

        Talk_Panel_me.add(Chat_K,0);
    }

    private static int calculateStringWidthInPoints(String text, Font font) {
        int DPI = 108;

        // 고정폭 폰트 설정 (예: Monospaced)
        Font fixedFont = new Font("Monospaced", font.getStyle(), font.getSize());

        BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setFont(fixedFont);
        FontMetrics metrics = g2d.getFontMetrics();

        int stringWidthPixels = metrics.stringWidth(text);

        g2d.dispose();

        // 픽셀 단위 길이를 포인트로 변환
        double widthInPoints = (double) stringWidthPixels / DPI * 72;

        return (int) widthInPoints;
    }
}
