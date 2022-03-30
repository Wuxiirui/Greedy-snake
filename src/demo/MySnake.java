package demo;

import javax.swing.*;
import org.junit.*;

import java.awt.*;

public class MySnake {
    public static void main(String[] args) {
        //创建窗口
        JFrame jFrame=new JFrame();
        //指定窗口的高度宽度
        jFrame.setBounds(600,100,700,900);
        //不允许拖拽改变大小
        jFrame.setResizable(false);
        //  当点击窗口关闭按钮，执行操作推出
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MyPanel myPanel=new MyPanel();
        jFrame.add(myPanel);
        //显示出来
        jFrame.setVisible(true);

    }
}
