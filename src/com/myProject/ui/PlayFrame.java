package com.myProject.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class PlayFrame extends JFrame implements KeyListener, ActionListener {

    int wy = 0;

    //创建二维数组，目的是管理图片数据
    int[][] data = new int[4][4];

    //定义空白方格的位置
    int x = 0;
    int y = 0;

    //定义路径
    String path = "..\\JigsawPuzzleGame\\image\\animal\\animal4\\";

    //定义一个正确的数组
    int[][] win = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};

    //定义步数
    int count = 0;

    //创建选项下面的条目对象
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reSignInItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");

    JMenuItem appletItem = new JMenuItem("小程序");

    JMenuItem RechargeItem1 = new JMenuItem("首充6元，一键通关");
    JMenuItem RechargeItem2 = new JMenuItem("充值30元，选择添加图片");
    JMenuItem girl = new JMenuItem("好看的");
    JMenuItem animal = new JMenuItem("动物");
    JMenuItem sport = new JMenuItem("运动");

    public PlayFrame() {
        //初始化界面
        initFrame();

        //初始化菜单
        initJMenuBar();

        //打乱图片
        initData();

        //初始化图片
        initImage();

        //显示界面
        this.setVisible(true);
    }

    //初始化二维数组
    private void initData() {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            int index = r.nextInt(15);
            int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                x = i / 4;
                y = i % 4;
            }
            data[i / 4][i % 4] = arr[i];
        }
    }

    //初始化图片
    private void initImage() {
        //因为该函数要重复调用，用来保存玩家改变的图片，需先清空原有的图片
        this.getContentPane().removeAll();

        //判断玩家是否胜利
        if (victory()) {
            JLabel winJlabel = new JLabel(new ImageIcon("..\\JigsawPuzzleGame\\image\\win.png"));
            winJlabel.setBounds(203, 283, 197, 73);
            this.getContentPane().add(winJlabel);
        }

        //设置记步器
        JLabel countJLabel = new JLabel("步数：" + count);
        countJLabel.setBounds(50, 30, 100, 20);
        this.getContentPane().add(countJLabel);

        //注意：先加载的图片在上方，后加载的图片在下面
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //创建一个JLabel的对象（管理容器）
                JLabel jLabel = new JLabel(new ImageIcon(path + data[i][j] + ".jpg"));
                //指定图像位置
                jLabel.setBounds(105 * j + 83, 105 * i + 134, 105, 105);
                //RAISED：图片突起
                //LOWERED：图片凹下
                jLabel.setBorder(new BevelBorder(BevelBorder.RAISED));
                //把管理容器添加到界面中
                this.getContentPane().add(jLabel);

                //刷新一下界面
                this.getContentPane().repaint();
            }
        }

        JLabel backGround = new JLabel(new ImageIcon("..\\JigsawPuzzleGame\\image\\background.png"));
        backGround.setBounds(40, 40, 508, 560);
        this.getContentPane().add(backGround);
    }

    //创建菜单
    private void initJMenuBar() {
        //创建菜单选项对象
        JMenuBar jMenuBar = new JMenuBar();

        JMenu functionJmenu = new JMenu("功能");
        JMenu aboutJmenu = new JMenu("关于我们");
        JMenu RechargeJmenu = new JMenu("充值渠道");

        //创建更换图片
        JMenu changeImage = new JMenu("更换图片");

        //把美女，动物，运动添加到更换图片当中
        changeImage.add(girl);
        changeImage.add(animal);
        changeImage.add(sport);

        //给按钮绑定监听事件
        replayItem.addActionListener(this);
        reSignInItem.addActionListener(this);
        closeItem.addActionListener(this);

        girl.addActionListener(this);
        animal.addActionListener(this);
        sport.addActionListener(this);

        appletItem.addActionListener(this);

        RechargeItem1.addActionListener(this);
        RechargeItem2.addActionListener(this);

        //把更换图片，重新游戏，重新登录，关闭游戏添加到功能当中
        functionJmenu.add(changeImage);
        //将对象依次添加
        functionJmenu.add(replayItem);
        functionJmenu.add(reSignInItem);
        functionJmenu.add(closeItem);

        aboutJmenu.add(appletItem);

        RechargeJmenu.add(RechargeItem1);
        RechargeJmenu.add(RechargeItem2);

        jMenuBar.add(functionJmenu);
        jMenuBar.add(aboutJmenu);
        jMenuBar.add(RechargeJmenu);

        //给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    //创建游戏窗口
    private void initFrame() {
        //设置界面宽高
        this.setSize(603, 680);
        //设置界面标题
        this.setTitle("拼图游戏单机版 v1.0（测试版）");
        //设置界面置顶（鼠标点击其他界面时该界面不会被覆盖）
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置界面关闭方式（在界面被关闭时，同时关闭控制台）
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认的居中放置，只有取消了才能按照xy轴方式放置
        this.setLayout(null);
        //给整个界面添加监听事件
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    //按住A显示整张图片
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode();
        if (code == 65) {
            //清空原先图片
            this.getContentPane().removeAll();
            //创建新的整张图片
            JLabel jLabel = new JLabel(new ImageIcon(path + "all.jpg"));
            //设置图片大小
            jLabel.setBounds(83, 134, 420, 420);
            //把图片添加到虚拟容器
            this.getContentPane().add(jLabel);

            //添加背景图片
            JLabel backGround = new JLabel(new ImageIcon("A:\\java项目\\JigsawPuzzleGame\\image\\background.png"));
            backGround.setBounds(40, 40, 508, 560);
            this.getContentPane().add(backGround);

            //刷新图片
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        //如果游戏胜利，就不可以再移动图片
        if (victory()) {
            return;
        }

        //对上下左右进行判断
        //左：37 上：38 右：39 下：40
        int code = keyEvent.getKeyCode();
        if (code == 37) {
            System.out.println("向左移动");

            if (y == 3) {//空白方块到达边界时退出方法
                return;
            }
            data[x][y] = data[x][y + 1];
            data[x][y + 1] = 0;
            y++;

            //记步器自增
            count++;

            initImage();
        } else if (code == 38) {
            System.out.println("向上移动");

            if (x == 3) {//空白方块到达边界时退出方法
                return;
            }
            data[x][y] = data[x + 1][y];
            data[x + 1][y] = 0;
            x++;

            //记步器自增
            count++;

            initImage();
        } else if (code == 39) {
            System.out.println("向右移动");

            if (y == 0) {//空白方块到达边界时退出方法
                return;
            }
            data[x][y] = data[x][y - 1];
            data[x][y - 1] = 0;
            y--;

            //记步器自增
            count++;

            initImage();
        } else if (code == 40) {
            System.out.println("向下移动");

            if (x == 0) {//空白方块到达边界时退出方法
                return;
            }
            data[x][y] = data[x - 1][y];
            data[x - 1][y] = 0;
            x--;

            //记步器自增
            count++;

            initImage();
        } else if (code == 65) {//玩家松开A时刷新界面
            initImage();
        } else if (code == 87 && wy == 0) {//玩家松开W
            wy++;

        } else if (code == 89 && wy == 1) {//玩家松开Y，且已经按过
            wy++;
        }

        if (wy == 2) {
            data = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};
            initImage();
        }
    }

    public boolean victory() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != win[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (source == replayItem) {
            //给一键通关按键清空
            wy = 0;
            //计步器清零
            count = 0;
            //重新打乱二维数组的数据
            initData();
            //重新加载图片
            initImage();
        } else if (source == reSignInItem) {
            //关闭当前界面
            this.setVisible(false);
            //打开登录界面
            new SignInFrame();
        } else if (source == closeItem) {
            System.exit(0);
        } else if (source == appletItem) {
            //创建一个弹框对象
            JDialog jDialog = new JDialog();
            //创建一个理管
            JLabel jLabel = new JLabel(new ImageIcon("..\\JigsawPuzzleGame\\image\\about.png"));
            jLabel.setBounds(0, 0, 258, 258);
            jDialog.getContentPane().add(jLabel);
            jDialog.setSize(344, 344);
            //让弹框置顶
            jDialog.setAlwaysOnTop(true);
            //让弹框居中
            jDialog.setLocationRelativeTo(null);
            //弹框不关闭无法操作下面的界面
            jDialog.setModal(true);
            //让弹框显示
            jDialog.setVisible(true);
        } else if (source == RechargeItem1) {
            //创建一个弹框对象
            JDialog jDialog = new JDialog();
            //创建一个理管
            JLabel jLabel = new JLabel(new ImageIcon("..\\JigsawPuzzleGame\\image\\recharge.png"));
            jLabel.setBounds(0, 0, 240, 250);
            jDialog.getContentPane().add(jLabel);
            jDialog.setSize(326, 336);
            //让弹框置顶
            jDialog.setAlwaysOnTop(true);
            //让弹框居中
            jDialog.setLocationRelativeTo(null);
            //弹框不关闭无法操作下面的界面
            jDialog.setModal(true);
            //让弹框显示
            jDialog.setVisible(true);
        } else if (source == RechargeItem2) {
            //创建一个弹框对象
            JDialog jDialog = new JDialog();
            //创建一个理管
            JLabel jLabel = new JLabel(new ImageIcon("..\\JigsawPuzzleGame\\image\\newRecharge.png"));
            jLabel.setBounds(0, 0, 300, 360);
            jDialog.getContentPane().add(jLabel);
            jDialog.setSize(386, 446);
            //让弹框置顶
            jDialog.setAlwaysOnTop(true);
            //让弹框居中
            jDialog.setLocationRelativeTo(null);
            //弹框不关闭无法操作下面的界面
            jDialog.setModal(true);
            //让弹框显示
            jDialog.setVisible(true);
        } else if (source == girl) {
            path = "..\\JigsawPuzzleGame\\image\\goodLooking\\nice";
            //给一键通关按键清空
            wy = 0;
            //计步器清零
            count = 0;
            //重新打乱二维数组的数据
            initData();
            //重新加载图片
            initImage();
        }
    }
}
