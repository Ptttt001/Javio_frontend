import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputFilter.Status;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.List;
import java.net.URL;
import javax.swing.*;
import java.awt.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;

public class Approjectframe extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JPanel panel1, panel2, panel3, panel4, panel5, panel6, panel7, panel8, panel9, panel10, panel11, panel13;
    private JLabel SclassLabel, TshowLabel;
    private String[] TArray, SArray;
    private JTextField usernameField, passwordField;
    private JButton loginButton, startAttendanceButton, endAttendanceButton, AttendCheckButton, SArriveButton;
    private JButton SConfirmButton1, SConfirmButton2, SConfirmButton3, SConfirmButton4, SConfirmButton5;
    private String num1 = "1", num2 = "2", num3 = "3", num4 = "4", num5 = "5";
    public int idnum;
    public loginjson publicpeople;
    public int choosecid;
    private boolean Scheck, isCorrect;
    private Timer timer, runTimer;

    Gson gson = new Gson();

    public Approjectframe() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        add(cardPanel);
        try {
            Image iconImage = ImageIO.read(getClass().getResource("icon2.jpeg"));
            setIconImage(iconImage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // panel 6 登入選項
        JPanel panel6 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image backgroundImage = new ImageIcon("src/identity.png").getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
            }
        };
        panel6.setBounds(0, 0, 800, 500);
        panel6.setLayout(null);
        JButton teacherbut = new JButton("");
        JButton studentbut = new JButton("");
        studentbut.setBounds(165, 230, 155, 85);
        studentbut.setOpaque(false); // 設為透明
        studentbut.setBorderPainted(false); // 按鈕邊框不可見
        studentbut.setContentAreaFilled(false); // 邊框設為透明
        teacherbut.setBounds(470, 230, 155, 85);
        teacherbut.setOpaque(false);
        teacherbut.setBorderPainted(false);
        teacherbut.setContentAreaFilled(false);
        panel6.add(teacherbut);
        panel6.add(studentbut);
        teacherbut.addActionListener(new teacherbutHandler());
        studentbut.addActionListener(new studentbutHandler());
        cardPanel.add(panel6, "Page 6");

        // Page 1 登入
        panel1 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image backgroundImage = new ImageIcon("src/login.png").getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
            }
        };
        setTitle("JAVIO");
        panel1.setBounds(0, 0, 800, 500);
        panel1.setLayout(null);
        JLabel usernameLabel = new JLabel("帳號");
        usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("密碼:");
        passwordField = new JTextField(20);
        loginButton = new JButton("");
        JButton backid = new JButton("");
        backid.addActionListener(new backidHandler());
        loginButton.addActionListener(new LoginButtonHandler());
        panel1.add(usernameLabel);
        panel1.add(usernameField);
        panel1.add(passwordLabel);
        panel1.add(passwordField);
        panel1.add(loginButton);
        panel1.add(backid);
        loginButton.setBounds(347, 363, 300, 58);
        loginButton.setOpaque(false); // 設為透明
        loginButton.setBorderPainted(false); // 按鈕邊框不可見
        loginButton.setContentAreaFilled(false); // 邊框設為透明
        backid.setBounds(139, 363, 200, 58);
        backid.setOpaque(false); // 設為透明
        backid.setBorderPainted(false); // 按鈕邊框不可見
        backid.setContentAreaFilled(false);
        usernameField.setBounds(427, 162, 134, 50);
        usernameField.setOpaque(false);
        passwordField.setBounds(426, 269, 133, 49);
        passwordField.setOpaque(false);
        cardPanel.add(panel1, "Page 1");

        // Page 2 課程清單
        panel2 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image backgroundImage = new ImageIcon("src/classlistback.jpg").getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
            }
        };
        panel2.setLayout(new GridBagLayout());
        cardLayout.show(cardPanel, "Page 2");
        cardPanel.add(panel2, "Page 2");

        // Page 3 老師功能選單
        panel3 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image backgroundImage = new ImageIcon("src/pmenu.png").getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
            }
        };
        panel3.setBounds(0, 0, 800, 500);
        panel3.setLayout(null);
        JButton backPButton = new JButton("");
        JButton initiateAttendanceButton = new JButton("");
        JButton SearchButton = new JButton("");
        initiateAttendanceButton.setBounds(165, 230, 155, 85);
        initiateAttendanceButton.setOpaque(false); // 設為透明
        initiateAttendanceButton.setBorderPainted(false); // 按鈕邊框不可見
        initiateAttendanceButton.setContentAreaFilled(false); // 邊框設為透明
        SearchButton.setBounds(468, 230, 155, 85);
        SearchButton.setOpaque(false);
        SearchButton.setBorderPainted(false);
        SearchButton.setContentAreaFilled(false);
        backPButton.setBounds(50, 47, 98, 35);
        backPButton.setOpaque(false);
        backPButton.setBorderPainted(false);
        backPButton.setContentAreaFilled(false);
        initiateAttendanceButton.addActionListener(new InitiateAttendanceButtonHandler());
        SearchButton.addActionListener(new SearchButtonHandler());
        backPButton.addActionListener(new backPageHandler());
        panel3.add(initiateAttendanceButton);
        panel3.add(SearchButton);
        panel3.add(backPButton);
        cardPanel.add(panel3, "Page 3");

        // Page 4 開始點名
        panel4 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image backgroundImage = new ImageIcon("src/tback.jpg").getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
            }
        };
        panel4.setBounds(0, 0, 800, 500);
        panel4.setLayout(null);
        ImageIcon startImage = new ImageIcon("src/startb.png");
        startAttendanceButton = new JButton("");
        startAttendanceButton.setPreferredSize(new Dimension(120, 50));
        startAttendanceButton.setBounds(335, 360, 120, 50);
        startAttendanceButton.setOpaque(false); // 設為透明
        startAttendanceButton.setBorderPainted(false); // 按鈕邊框不可見
        startAttendanceButton.setContentAreaFilled(false);
        startAttendanceButton.setIcon(startImage);
        startAttendanceButton.addActionListener(new startAttendanceButtonHandler());

        TshowLabel = new JLabel("10");
        TshowLabel.setBounds(335, 90, 200, 250);
        TshowLabel.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 150));
        TshowLabel.setVisible(false);

        endAttendanceButton = new JButton("");
        endAttendanceButton.setPreferredSize(new Dimension(120, 50));
        ImageIcon endImage = new ImageIcon("src/endb.png");
        endAttendanceButton.setIcon(endImage);
        endAttendanceButton.setBounds(335, 360, 120, 50);
        endAttendanceButton.setOpaque(false); // 設為透明
        endAttendanceButton.setBorderPainted(false); // 按鈕邊框不可見
        endAttendanceButton.setContentAreaFilled(false);
        endAttendanceButton.addActionListener(new endAttendanceButtonHandler());

        panel4.add(startAttendanceButton);
        panel4.add(TshowLabel);
        panel4.add(endAttendanceButton);

        endAttendanceButton.setVisible(false);
        cardPanel.add(panel4, "Page 4");

        // panel 5 查詢點名狀態
        panel5 = new JPanel(null) {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image backgroundImage = new ImageIcon("src/menu.jpg").getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
            }
        };
        JButton backPageButton = new JButton(" ");
        backPageButton.setBounds(50, 47, 98, 35);
        backPageButton.setOpaque(false); // 設為透明
        backPageButton.setBorderPainted(false); // 按鈕邊框不可見
        backPageButton.setContentAreaFilled(false); // 邊框設為透明

        backPageButton.addActionListener(new backPageHandler());
        panel13 = new JPanel(null);
        panel13.setBounds(250, 100, 300, 500);
        panel13.setOpaque(false);

        panel5.add(backPageButton);
        panel5.add(panel13);
        cardPanel.add(panel5, "Page 5");

        // panel 7 學生選單
        panel7 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image backgroundImage = new ImageIcon("src/smenu.png").getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
            }
        };
        panel7.setBounds(0, 0, 800, 500);
        panel7.setLayout(null);
        JButton stuinitiateAttendanceButton = new JButton(" ");
        JButton stuSearchButton = new JButton("");
        JButton backButton = new JButton("");
        stuinitiateAttendanceButton.setBounds(165, 230, 155, 85);
        stuinitiateAttendanceButton.setOpaque(false); // 設為透明
        stuinitiateAttendanceButton.setBorderPainted(false); // 按鈕邊框不可見
        stuinitiateAttendanceButton.setContentAreaFilled(false); // 邊框設為透明
        stuSearchButton.setBounds(468, 230, 155, 85);
        stuSearchButton.setOpaque(false);
        stuSearchButton.setBorderPainted(false);
        stuSearchButton.setContentAreaFilled(false);
        backButton.setBounds(50, 47, 98, 35);
        backButton.setOpaque(false);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        stuinitiateAttendanceButton.addActionListener(new AttendCheckButtonHandler());
        stuSearchButton.addActionListener(new SearchButtonHandler());
        backButton.addActionListener(new backPageHandler());
        panel7.add(stuinitiateAttendanceButton);
        panel7.add(stuSearchButton);
        panel7.add(backButton);

        cardPanel.add(panel7, "Page 7");

        // panel8 學生已開放點名
        panel8 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image backgroundImage = new ImageIcon("src/start.png").getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
            }
        };
        panel8.setBounds(0, 0, 800, 500);
        panel8.setLayout(null);
        SArriveButton = new JButton(" ");
        SArriveButton.setBounds(335, 360, 120, 50);
        SArriveButton.setOpaque(false); // 設為透明
        SArriveButton.setBorderPainted(false); // 按鈕邊框不可見
        SArriveButton.setContentAreaFilled(false);
        SArriveButton.addActionListener(new SArriveButtonHandler());
        panel8.add(SArriveButton);
        cardPanel.add(panel8, "Page 8");

        // panel9 驗證
        panel9 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image backgroundImage = new ImageIcon("src/five.png").getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
            }
        };
        panel9.setBounds(0, 0, 800, 500);
        panel9.setLayout(null);
        SConfirmButton1 = new JButton(num1);
        SConfirmButton2 = new JButton(num2);
        SConfirmButton3 = new JButton(num3);
        SConfirmButton4 = new JButton(num4);
        SConfirmButton5 = new JButton(num5);
        SConfirmButton1.setBounds(240, 195, 100, 35);
        SConfirmButton2.setBounds(450, 195, 100, 35);
        SConfirmButton3.setBounds(151, 303, 100, 35);
        SConfirmButton4.setBounds(345, 305, 100, 35);
        SConfirmButton5.setBounds(535, 305, 100, 35);
        SConfirmButton1.setOpaque(false); // 設為透明
        SConfirmButton2.setOpaque(false);
        SConfirmButton3.setOpaque(false);
        SConfirmButton4.setOpaque(false);
        SConfirmButton5.setOpaque(false);
        SConfirmButton1.setBorderPainted(false); // 按鈕邊框不可見
        SConfirmButton2.setBorderPainted(false);
        SConfirmButton3.setBorderPainted(false);
        SConfirmButton4.setBorderPainted(false);
        SConfirmButton5.setBorderPainted(false);
        SConfirmButton1.setContentAreaFilled(false); // 邊框設為透明
        SConfirmButton2.setContentAreaFilled(false);
        SConfirmButton3.setContentAreaFilled(false);
        SConfirmButton4.setContentAreaFilled(false);
        SConfirmButton5.setContentAreaFilled(false);
        SConfirmButton1.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 28));
        SConfirmButton2.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 28));
        SConfirmButton3.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 28));
        SConfirmButton4.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 28));
        SConfirmButton5.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 28));
        SConfirmButton1.addActionListener(new SConfirmButtonHandler());
        SConfirmButton2.addActionListener(new SConfirmButtonHandler());
        SConfirmButton3.addActionListener(new SConfirmButtonHandler());
        SConfirmButton4.addActionListener(new SConfirmButtonHandler());
        SConfirmButton5.addActionListener(new SConfirmButtonHandler());
        panel9.add(SConfirmButton1);
        panel9.add(SConfirmButton2);
        panel9.add(SConfirmButton3);
        panel9.add(SConfirmButton4);
        panel9.add(SConfirmButton5);
        cardPanel.add(panel9, "Page 9");

        // panel10 點名成功
        panel10 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image backgroundImage = new ImageIcon("src/success.png").getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
            }
        };
        panel10.setBounds(0, 0, 800, 500);
        panel10.setLayout(null);
        JButton successbackButton = new JButton("");
        successbackButton.setBounds(275, 350, 230, 45);
        successbackButton.setOpaque(false); // 設為透明
        successbackButton.setBorderPainted(false); // 按鈕邊框不可見
        successbackButton.setContentAreaFilled(false);
        successbackButton.addActionListener(new backmenuHandler());
        panel10.add(successbackButton);
        cardPanel.add(panel10, "Page 10");

        // Page 10 點名失敗
        // 傳遞失敗
        panel11 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image backgroundImage = new ImageIcon("src/fail.png").getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
            }
        };
        panel11.setBounds(0, 0, 800, 500);
        panel11.setLayout(null);
        JButton failbackButton = new JButton("");
        failbackButton.setBounds(275, 350, 230, 45);
        failbackButton.setOpaque(false); // 設為透明
        failbackButton.setBorderPainted(false); // 按鈕邊框不可見
        failbackButton.setContentAreaFilled(false);
        failbackButton.addActionListener(new backmenuHandler());
        panel11.add(failbackButton);
        cardPanel.add(panel11, "Page 11");

        // panel12 未開放點名
        JPanel panel12 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image backgroundImage = new ImageIcon("src/not.png").getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
            }
        };
        panel12.setBounds(0, 0, 800, 500);
        panel12.setLayout(null);
        failbackButton = new JButton("");
        failbackButton.setBounds(335, 360, 120, 50);
        failbackButton.setOpaque(false); // 設為透明
        failbackButton.setBorderPainted(false); // 按鈕邊框不可見
        failbackButton.setContentAreaFilled(false);
        failbackButton.addActionListener(new backmenuHandler());
        panel12.add(failbackButton);
        cardPanel.add(panel12, "Page 12");
    }

    // 登入按鈕
    private class LoginButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            String username = usernameField.getText();
            String password = new String(passwordField.getText());
            loginjson people = null;
            String line = "not connect";
            // 發送登入
            if (idnum == 1) { // 學生
                try {
                    // 創建URL
                    URL loginurl = new URL("https://approject2.azurewebsites.net/login/" + username + "/" + password);
                    // 創建http request
                    HttpURLConnection connection = (HttpURLConnection) loginurl.openConnection();
                    // 設置請求方法
                    connection.setRequestMethod("GET");
                    // 發送且或取代碼
                    int responsecode = connection.getResponseCode();
                    // 讀取內容
                    BufferedReader read = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    line = read.readLine();
                    System.out.println("respon:" + line);
                    read.close();
                    // String resjson = response.toString();
                    // 關閉連結
                    connection.disconnect();
                    // 解析gson
                    Gson gson = new Gson();

                    // 讀取訊息
                    people = gson.fromJson(line, loginjson.class);
                    publicpeople = people;
                    System.out.println("status: " + people.status);
                    System.out.println("coursenum: " + people.coursenum);
                    System.out.println("courselist: " + publicpeople.courselist);
                    System.out.println("coursename: " + people.courseName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(line);

                if (people != null && people.status) {
                    setcoursebtn(people.coursenum, people.courselist, people.courseName);
                    cardLayout.show(cardPanel, "Page 2");
                } else {
                    JOptionPane.showMessageDialog(null, "帳號或密碼錯誤", "登入失敗", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                try {
                    // 創建URL
                    URL loginurl = new URL(
                            "https://approject2.azurewebsites.net/prologin/" + username + "/" + password);
                    // 創建http request
                    HttpURLConnection connection = (HttpURLConnection) loginurl.openConnection();
                    // 設置請求方法
                    connection.setRequestMethod("GET");
                    // 發送且或取代碼
                    int responsecode = connection.getResponseCode();
                    // 讀取內容
                    BufferedReader read = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    line = read.readLine();
                    System.out.println("respon:" + line);
                    read.close();
                    // String resjson = response.toString();
                    // 關閉連結
                    connection.disconnect();
                    // 解析gson
                    Gson gson = new Gson();

                    // 讀取訊息
                    people = gson.fromJson(line, loginjson.class);
                    publicpeople = people;
                    System.out.println("status: " + people.status);
                    System.out.println("coursenum: " + people.coursenum);
                    System.out.println("courselist: " + publicpeople.courselist);
                    System.out.println("coursename: " + people.courseName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(line);

                if (people != null && people.status) {
                    setcoursebtn(people.coursenum, people.courselist, people.courseName);
                    cardLayout.show(cardPanel, "Page 2");
                } else {
                    JOptionPane.showMessageDialog(null, "帳號或密碼錯誤", "登入失敗", JOptionPane.ERROR_MESSAGE);
                }
            }

        }
    }

    class loginjson {
        boolean status;
        int coursenum;
        JsonArray courselist;
        JsonArray courseName;
    }

    private void setcoursebtn(int num, JsonArray courelist, JsonArray coursename) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        for (int i = 1; i <= num; i++) {
            ImageIcon backgroundImage = new ImageIcon("src/classbut.png");
            JButton classButton = new JButton(coursename.get(i - 1).getAsString());
            classButton.setPreferredSize(new Dimension(140, 60));
            classButton.setFont(new Font("Cooper Black", Font.BOLD, 25));
            classButton.setHorizontalTextPosition(JButton.CENTER); // 水平居中
            classButton.setVerticalTextPosition(JButton.CENTER);
            classButton.setOpaque(false); // 設為透明
            classButton.setBorderPainted(false); // 按鈕邊框不可見
            classButton.setContentAreaFilled(false); // 邊框設為透明
            classButton.setIcon(backgroundImage);
            classButton.addActionListener(new NextPageButtonHandler());
            panel2.add(classButton, gbc);
            gbc.gridx++;
        }
        cardPanel.add(panel2, "Page 2");

    }

    // 課程目錄按鈕
    private class NextPageButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            JButton choosebutton = (JButton) event.getSource();
            String coursename = choosebutton.getText();
            System.out.println("coursename:" + coursename);
            // find the index of coursename that correspond with the coursename in
            // publicpeople.courseName
            int index = 0;
            for (int i = 0; i < publicpeople.courseName.size(); i++) {
                if (publicpeople.courseName.get(i).getAsString().equals(coursename)) {
                    index = i;
                }
            }
            choosecid = publicpeople.courselist.get(index).getAsInt();
            System.out.println(choosecid);
            if (idnum == 0) {
                cardLayout.show(cardPanel, "Page 3");
            } else {
                cardLayout.show(cardPanel, "Page 7");
            }
        }
    }

    // 老師去點名按鈕
    private class InitiateAttendanceButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            String line2 = "not connect";
            // Getnumjson people = new TGetnumjson();
            try {
                // 创建URL对象
                URL Tstarturl = new URL("https://approject2.azurewebsites.net/holdattendant/" + choosecid);

                // 打开连接
                HttpURLConnection connection = (HttpURLConnection) Tstarturl.openConnection();

                // 设置请求方法为GET
                connection.setRequestMethod("GET");

                // 发送请求
                int responseCode = connection.getResponseCode();
                // 读取响应内容
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                StringBuilder response = new StringBuilder();
                line2 = reader.readLine();
                System.out.println("respon:" + line2);
                reader.close();
                line2 = line2.substring(1, line2.length() - 1);
                System.out.println("respon:" + line2);
                TArray = line2.split(",");
                for (String s : TArray) {
                    System.out.println(s);
                }
                // 断开连接
                connection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // System.out.println(line2);

            cardLayout.show(cardPanel, "Page 4");
        }
    }

    // 點名按鈕
    private class startAttendanceButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            TshowLabel.setVisible(true);
            endAttendanceButton.setVisible(true);
            startAttendanceButton.setVisible(false);
            timer = new Timer(1000, new ActionListener() {
                private int count = 10;

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (count >= 0) {
                        TshowLabel.setText(Integer.toString(count));
                        count--;
                    } else {
                        count = 10;
                        ((Timer) e.getSource()).stop();

                        runTimer = new Timer(3000, new ActionListener() {
                            private int runtimes = 0;
                            private int i = 0;

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (runtimes != 10) {
                                    TshowLabel.setText(TArray[i]);
                                    runtimes++;
                                    i++;
                                } else {
                                    ((Timer) e.getSource()).stop();
                                }
                            }
                        });
                        runTimer.start();
                    }
                }
            });
            timer.start();
        }
    }

    // 結束點名按鈕
    private class endAttendanceButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                // 创建URL对象
                URL endurl = new URL("https://approject2.azurewebsites.net/stopattendant/" + choosecid);
                // 打开连接
                HttpURLConnection connection = (HttpURLConnection) endurl.openConnection();
                // 设置请求方法为GET
                connection.setRequestMethod("GET");
                // 发送请求
                int responseCode = connection.getResponseCode();
                // 断开连接
                connection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
            timer.stop();
            if (runTimer != null) {
                runTimer.stop();
            }
            Arrays.fill(TArray, "");
            TshowLabel.setText("10");
            endAttendanceButton.setVisible(false);
            startAttendanceButton.setVisible(true);
            cardLayout.show(cardPanel, "Page 3");// 11
        }
    }

    // 查詢點名狀態按鈕
    private class SearchButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            searchjson searchlist = null;
            String line = "not connect";
            // 從課程目錄按進去就要儲存課程代碼
            try {
                URL searchurl = new URL("https://approject2.azurewebsites.net/attendtstatus/" + choosecid);
                System.out.println(choosecid);
                HttpURLConnection connection = (HttpURLConnection) searchurl.openConnection();
                connection.setRequestMethod("GET");
                int responseCode = connection.getResponseCode();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                line = reader.readLine();
                System.out.println("respon:" + line);
                reader.close();
                connection.disconnect();

            } catch (Exception e) {
                e.printStackTrace();
            }
            Gson gson = new Gson();
            JsonObject[] jsonObjects = gson.fromJson(line, JsonObject[].class);
            panel13.removeAll();
            // 讀取訊息
            Integer i = 0;
            for (JsonObject jsonObject : jsonObjects) {
                i += 1;
                searchlist = gson.fromJson(jsonObject, searchjson.class);
                objectgroup customComponent = new objectgroup(searchlist.sname, searchlist.sid, searchlist.status,
                        choosecid, i);
                customComponent.setBounds(30, 60 * i, 240, 40);
                panel13.add(customComponent);
            }
            i = 0;
            cardLayout.show(cardPanel, "Page 5");

        }
    }

    class searchjson {
        String status;
        int sid;
        String sname;
    }

    // 返回鍵
    private class backPageHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            panel5.setVisible(false);
            cardLayout.show(cardPanel, "Page 2");
        }
    }

    public class backidHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            panel1.setVisible(false);
            cardLayout.show(cardPanel, "Page 6");
        }
    }

    // 學生返回選單
    public class backmenuHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            cardLayout.show(cardPanel, "Page 7");
        }
    }

    // 老師登入
    private class teacherbutHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            idnum = 0;
            System.out.println("我是" + idnum);
            cardLayout.show(cardPanel, "Page 1");
        }
    }

    // 學生登入
    private class studentbutHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            idnum = 1;
            System.out.println("我是" + idnum);
            cardLayout.show(cardPanel, "Page 1");
        }
    }

    private class prestartHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            cardLayout.show(cardPanel, "Page 8");
        }
    }

    // 學生確定是否開啟點名
    private class AttendCheckButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            String sid = usernameField.getText();
            String line3 = "not connect";
            // Getnumjson people = new TGetnumjson();
            try {
                // 创建URL对象
                URL Scheckurl = new URL(
                        "https://approject2.azurewebsites.net/ishavingatt/" + choosecid + "/" + sid);

                // 打开连接
                HttpURLConnection connection = (HttpURLConnection) Scheckurl.openConnection();

                // 设置请求方法为GET
                connection.setRequestMethod("GET");

                // 发送请求
                int responseCode = connection.getResponseCode();
                // 读取响应内容
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                StringBuilder response = new StringBuilder();
                line3 = reader.readLine();
                Scheck = Boolean.parseBoolean(line3);
                System.out.println("respon:" + Scheck);
                reader.close();
                connection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // if 有開放點名
            if (Scheck == true) {
                cardLayout.show(cardPanel, "Page 8");
            }
            // else 跳出尚未開始點名
            else {
                cardLayout.show(cardPanel, "Page 12");
            }

        }
    }

    // 學生顯示五個數字
    private class SArriveButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            cardLayout.show(cardPanel, "Page 9");
            // 顯示5個數字button
            String sid = usernameField.getText();
            String line4 = "not connect";
            // Getnumjson people = new TGetnumjson();
            try {
                // 创建URL对象
                URL Sarriveurl = new URL("https://approject2.azurewebsites.net/iamhere/" + choosecid + "/" + sid);

                // 打开连接
                HttpURLConnection connection = (HttpURLConnection) Sarriveurl.openConnection();

                // 设置请求方法为GET
                connection.setRequestMethod("GET");

                // 发送请求
                int responseCode = connection.getResponseCode();
                // 读取响应内容
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                StringBuilder response = new StringBuilder();
                line4 = reader.readLine();
                System.out.println("respon:" + line4);
                reader.close();
                // 输出响应内容
                // line2 = response.toString();
                line4 = line4.substring(1, line4.length() - 1);
                System.out.println("respon:" + line4);
                SArray = line4.split(",");
                for (String s : SArray) {
                    System.out.println(s);
                }
                // 断开连接
                connection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
            SConfirmButton1.setText(SArray[0]);
            SConfirmButton2.setText(SArray[1]);
            SConfirmButton3.setText(SArray[2]);
            SConfirmButton4.setText(SArray[3]);
            SConfirmButton5.setText(SArray[4]);
        }
    }

    // 驗證學生數字
    private class SConfirmButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            // 傳回數字 拿正確或失敗
            // 對9錯10
            String sid = usernameField.getText();
            String line5 = "not connect";
            JButton selectedButton = (JButton) event.getSource();
            String userInput = selectedButton.getText();
            // Getnumjson people = new TGetnumjson();
            try {
                // 创建URL对象
                URL SConfirmurl = new URL(
                        "https://approject2.azurewebsites.net/checknum/" + choosecid + "/" + sid + "/" + userInput);

                // 打开连接
                HttpURLConnection connection = (HttpURLConnection) SConfirmurl.openConnection();

                // 设置请求方法为GET
                connection.setRequestMethod("GET");

                // 发送请求
                int responseCode = connection.getResponseCode();
                // 读取响应内容
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                StringBuilder response = new StringBuilder();
                line5 = reader.readLine();
                isCorrect = Boolean.parseBoolean(line5);
                System.out.println("respon:" + isCorrect);
                reader.close();
                connection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (isCorrect == true) {
                cardLayout.show(cardPanel, "Page 10"); // 正確
            } else {
                cardLayout.show(cardPanel, "Page 11"); // 錯誤
            }

        }
    }
}

//////////////////
class objectgroup extends JComponent {
    private JLabel label1;
    private JLabel label2;
    private JButton button1;
    private JButton button2;
    private JButton button3;

    public objectgroup(String name, Integer sid, String status, Integer choose, Integer num) {
        Font font = new Font("Microsoft YaHei", Font.PLAIN, 15); // 设置字体为 Yuanti SC Regular，字体样式为普通，字体大小为 16

        // setLayout(null); // 取消布局管理器，使用自定义位置和大小
        // setLayout(new FlowLayout());
        setLayout(new GridLayout(1, 5));
        label1 = new JLabel(sid.toString());
        label1.setFont(font);
        // label1.setBounds(40, 60*num, 40, 40); // 设置位置和大小

        label2 = new JLabel(name);
        // label2.setBounds(100, 60*num, 40 ,40); // 设置位置和大小
        label2.setFont(font);
        button1 = new JButton("");
        button1.setPreferredSize(new Dimension(40, 40));
        ImageIcon button1Image = new ImageIcon("src/v.png");
        ImageIcon button1Imagex = new ImageIcon("src/vx.png");
        button1.setBounds(335, 360, 120, 50);
        button1.setOpaque(false); // 設為透明
        button1.setBorderPainted(false); // 按鈕邊框不可見
        button1.setContentAreaFilled(false);
        button1.setIcon(button1Image);
        // button1.setBounds(160, 60*num, 40, 40); // 设置位置和大小

        button2 = new JButton("");
        button2.setPreferredSize(new Dimension(40, 40));
        ImageIcon button2Image = new ImageIcon("src/t.png");
        ImageIcon button2Imagex = new ImageIcon("src/tx.png");
        button2.setBounds(335, 360, 120, 50);
        button2.setOpaque(false); // 設為透明
        button2.setBorderPainted(false); // 按鈕邊框不可見
        button2.setContentAreaFilled(false);
        button2.setIcon(button2Image);
        // button2.setBounds(220, 60*num, 40, 40); // 设置位置和大小

        button3 = new JButton("");
        button3.setPreferredSize(new Dimension(40, 40));
        ImageIcon button3Image = new ImageIcon("src/x.png");
        ImageIcon button3Imagex = new ImageIcon("src/xx.png");
        button3.setBounds(335, 360, 120, 50);
        button3.setOpaque(false); // 設為透明
        button3.setBorderPainted(false); // 按鈕邊框不可見
        button3.setContentAreaFilled(false);
        button3.setIcon(button3Image);
        // button3.setBounds(280, 60*num, 40, 40); // 设置位置和大小

        Integer chooseid = choose;
        if (status.equals("1")) {
            button1.setIcon(button1Image);
            button2.setIcon(button2Imagex);
            button3.setIcon(button3Imagex);
        } else if (status.equals("2")) {
            button1.setIcon(button1Imagex);
            button2.setIcon(button2Image);
            button3.setIcon(button3Imagex);
        } else if (status.equals("0")) {
            button1.setIcon(button1Imagex);
            button2.setIcon(button2Imagex);
            button3.setIcon(button3Image);
        }
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println(label1.getText() + "出席");
                button1.setIcon(button1Image);
                button2.setIcon(button2Imagex);
                button3.setIcon(button3Imagex);
                try {
                    String line = "";
                    URL searchurl = new URL("https://approject2.azurewebsites.net/alteratt/" + chooseid + "/"
                            + label1.getText() + "/1");
                    HttpURLConnection connection = (HttpURLConnection) searchurl.openConnection();
                    connection.setRequestMethod("GET");
                    int responseCode = connection.getResponseCode();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    line = reader.readLine();
                    System.out.println("respon:" + line);
                    reader.close();
                    connection.disconnect();
                    // 讀取訊息

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println(label1.getText() + "遲到");
                button1.setIcon(button1Imagex);
                button2.setIcon(button2Image);
                button3.setIcon(button3Imagex);
                try {
                    String line = "";
                    URL searchurl = new URL(
                            "https://approject2.azurewebsites.net/alteratt/" + chooseid + "/" + label1.getText()
                                    + "/2");
                    HttpURLConnection connection = (HttpURLConnection) searchurl.openConnection();
                    connection.setRequestMethod("GET");
                    int responseCode = connection.getResponseCode();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    line = reader.readLine();
                    System.out.println("respon:" + line);
                    reader.close();
                    connection.disconnect();
                    // 讀取訊息

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println(label1.getText() + "缺席");
                button1.setIcon(button1Imagex);
                button2.setIcon(button2Imagex);
                button3.setIcon(button3Image);
                try {
                    String line = "";
                    URL searchurl = new URL(
                            "https://approject2.azurewebsites.net/alteratt/" + chooseid + "/" + label1.getText()
                                    + "/0");
                    HttpURLConnection connection = (HttpURLConnection) searchurl.openConnection();
                    connection.setRequestMethod("GET");
                    int responseCode = connection.getResponseCode();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    line = reader.readLine();
                    System.out.println("respon:" + line);
                    reader.close();
                    connection.disconnect();
                    // 讀取訊息

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // 添加标签和按钮到自定义组件
        add(label1);
        add(label2);
        add(button1);
        add(button2);
        add(button3);

        // 进行其他的自定义设置和布局
        // ...
    }
}
