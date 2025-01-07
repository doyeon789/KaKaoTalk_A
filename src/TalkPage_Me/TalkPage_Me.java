package TalkPage_Me;

import Home.Home_Page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TalkPage_Me {
    static JTextArea InputArea;
    static JLabel sending;
    public static void TalkPage(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame_Talk_me = new JFrame("");
        frame_Talk_me.setSize(379, 639);
        frame_Talk_me.setLocation(720,285);
        frame_Talk_me.setUndecorated(true);
        frame_Talk_me.setBackground(new Color(0,0,0,0));
        frame_Talk_me.setResizable(false);

        JPanel Talk_panel_me = new JPanel();
        Talk_panel_me.setLayout(null);
        Talk_panel_me.setOpaque(false);
        Talk_panel_me.setBounds(0, 0, 379, 639);

        sending = new JLabel();
        sending.setOpaque(false);
        sending.setBounds(313, 602, 61, 31);
        ImageIcon sendingI = new ImageIcon("image/TalkPage/send.png");
        Image sending_img = sendingI.getImage();
        Image sending_logo = sending_img.getScaledInstance(61, 31, Image.SCALE_SMOOTH);
        sending.setIcon(new ImageIcon(sending_logo));
        sending.setVisible(false);


        InputArea = new JTextArea("메시지 입력");
        InputArea.setForeground(new Color(139,139,139));
        InputArea.setOpaque(true);
        InputArea.setBackground(Color.white);
        InputArea.setBounds(0,0,370,72);
        InputArea.setLineWrap(true);
        InputArea.setWrapStyleWord(true);
        Talk_panel_me.requestFocus();

        InputArea.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (InputArea.getForeground().equals(new Color(139,139,139))) {
                    InputArea.setText("");
                    InputArea.setForeground(Color.BLACK);
                }
                checkInputState();
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (InputArea.getText().isEmpty()) {
                    InputArea.setForeground(new Color(139, 139, 139));
                    InputArea.setText("메시지 입력");
                    sending.setVisible(false);
                }
                checkInputState();
            }
        });

        InputArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                checkInputState();
            }
        });

        Talk_panel_me.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Talk_panel_me.requestFocus();
            }
        });


        Action sendAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                send();
            }
        };

        sending.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e){
                if(sending.isVisible()) {
                    send();
                    sending.setVisible(false);
                }
            }
        });

        InputArea.getInputMap().put(javax.swing.KeyStroke.getKeyStroke("shift ENTER"),"insert-newline");
        InputArea.getActionMap().put("insert-newline", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InputArea.append(System.lineSeparator());
            }
        });

        InputArea.getInputMap().put(javax.swing.KeyStroke.getKeyStroke("ENTER"),"send");
        InputArea.getActionMap().put("send",sendAction);

        JScrollPane scrollPane = new JScrollPane(InputArea, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        scrollPane.setBounds(8,525,370,72);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        Talk_panel_me.add(sending,0);
        Talk_panel_me.add(scrollPane,0);

        Init_Workig(frame_Talk_me,Talk_panel_me);
    }
    private static void Init_Workig(JFrame frame_Talk_me,JPanel Talk_panel_me){
        JLabel Talk_Background_me = new JLabel();
        Talk_Background_me.setOpaque(false);
        Talk_Background_me.setBounds(0, 0, 379, 639);
        ImageIcon Talk_B_meI = new ImageIcon("image/TalkPage/TalkPage.png");
        Image Talk_B_me_img = Talk_B_meI.getImage();
        Image Talk_B_me_logo = Talk_B_me_img.getScaledInstance(379, 639, Image.SCALE_SMOOTH);
        Talk_Background_me.setIcon(new ImageIcon(Talk_B_me_logo));

        final Point[] mouseClickPoint = {null};

        JLabel Move = new JLabel();
        Move.setOpaque(false);
        Move.setBounds(65,0,332,40);
        Move.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
                mouseClickPoint[0] = e.getPoint();
            }
        });
        Move.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e){
                if(mouseClickPoint[0] != null){
                    Point current = frame_Talk_me.getLocation();
                    frame_Talk_me.setLocation(
                            current.x + e.getX() - mouseClickPoint[0].x,
                            current.y + e.getY() - mouseClickPoint[0].y
                    );
                }
            }
        });

        JLabel X_button = new JLabel();
        X_button.setOpaque(false);
        X_button.setBounds(5, 3, 56, 21);
        ImageIcon X_buttonI = new ImageIcon("image/COM/X.png");
        Image X_button_img = X_buttonI.getImage();
        Image X_button_logo = X_button_img.getScaledInstance(56, 21, Image.SCALE_SMOOTH);
        X_button.setIcon(new ImageIcon(X_button_logo));
        X_button.setVisible(false);

        JLabel X_button_Box = new JLabel();
        X_button_Box.setBounds(10,5,18,18);
        X_button_Box.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                Home_Page.setTalk_myself_Open(false);
                frame_Talk_me.dispose();
            }

            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                X_button.setVisible(true);
                Talk_panel_me.revalidate();
                Talk_panel_me.repaint();
                X_button_Box.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                X_button.setVisible(false);
                Talk_panel_me.revalidate();
                Talk_panel_me.repaint();
                X_button_Box.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        Talk_panel_me.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "closeWindow");
        Talk_panel_me.getActionMap().put("closeWindow", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Home_Page.setTalk_myself_Open(false);
                frame_Talk_me.dispose();
            }
        });

        Talk_panel_me.add(X_button);
        Talk_panel_me.add(X_button_Box);
        Talk_panel_me.add(Move);
        Talk_panel_me.add(Talk_Background_me);

        frame_Talk_me.add(Talk_panel_me);
        frame_Talk_me.setVisible(true);
    }

    private static void send() {
        if (InputArea.getText().isEmpty()) {
            return;
        }

        System.out.println("send = \"" + InputArea.getText() + "\"");
        InputArea.setText(null);
    }

    // 입력 상태를 확인하는 메서드
    private static void checkInputState() {
        if (InputArea.getText().isEmpty() || (InputArea.getText().equals("메시지 입력") && InputArea.getForeground().equals(new Color(139,139,139)))) {
            sending.setVisible(false);
        } else {
            sending.setVisible(true);
        }
    }
}
