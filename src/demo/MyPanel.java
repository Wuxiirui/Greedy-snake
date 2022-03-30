package demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import com.sun.org.apache.bcel.internal.generic.SWITCH;
import org.junit.*;
public class MyPanel extends JPanel implements KeyListener, ActionListener {
     ImageIcon rigth=new ImageIcon("Game/images/right.png");
     ImageIcon body=new ImageIcon("Game/images/body.png");
     ImageIcon top=new ImageIcon("Game/images/top.png");
     ImageIcon left=new ImageIcon("Game/images/left.png");
     ImageIcon bottom=new ImageIcon("Game/images/bottom.png");
     int len=3;
     int[] snakeX=new int[1008];
     int[] snakeY=new int[1008];
     Direction direction=Direction.top;
     //创建定时器
    Timer timer=new Timer(100,this);
    boolean isstart=false;
    int foodX;
    int foodY;
    Random random=new Random();
    ImageIcon food=new ImageIcon("Game/images/food.png");
    public MyPanel() {
        snakeX[0]=100;
        snakeY[0]=100;
        snakeX[1]=75;
        snakeY[1]=100;
        snakeX[2]=50;
        snakeY[2]=100;
        //获取键盘事件
        this.setFocusable(true);
        //进行监听
        this.addKeyListener(this);
        //启动定时器
        timer.start();
        //随机生成食物
        foodX=25+25*random.nextInt(20);
        foodY=25+25*random.nextInt(20);
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.setBackground(Color.red);
        g.fillRect(0,0,700,900);
      //  rigth.paintIcon(this,g,snakeX[0],snakeY[0]);
        switch (direction){
            case top:top.paintIcon(this,g,snakeX[0],snakeY[0]);
            break;
            case left:left.paintIcon(this,g,snakeX[0],snakeY[0]);
            break;
            case right:rigth.paintIcon(this,g,snakeX[0],snakeY[0]);
            break;
            case bottom:bottom.paintIcon(this,g,snakeX[0],snakeY[0]);
            break;
        }
        //添加身体
        for(int i=1;i<len;i++){
             body.paintIcon(this,g,snakeX[i],snakeY[i]);
        }
        //标记游戏开始
     if(!isstart){
        g.setColor(Color.white);
        g.setFont(new Font("宋体",Font.BOLD,50));
        g.drawString("请按空格键开始",50,500);
     }
     //添加食物
        food.paintIcon(this,g,foodX,foodY);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
           int keycode=e.getKeyCode();
           if(keycode==32){
               isstart=!isstart;
               repaint();
           }else if(keycode==KeyEvent.VK_UP){
               direction=Direction.top;
           }else if(keycode==KeyEvent.VK_DOWN){
               direction=Direction.bottom;
           }else if(keycode==KeyEvent.VK_RIGHT){
               direction=Direction.right;
           }else if(keycode==KeyEvent.VK_LEFT){
               direction=Direction.left;
           }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    @Override
    public void actionPerformed(ActionEvent e) {
       if(isstart){
           //移动身体
        for(int i=len-1;i>0;i--){
            snakeX[i]=snakeX[i-1];
            snakeY[i]=snakeY[i-1];
        }
        //移动蛇头
           switch (direction){
               case bottom:
                   if(snakeY[0]>=900) snakeY[0]=0;
                   snakeY[0]+=25;
                   repaint();
                   timer.start();
                   break;
               case right:
                   if(snakeX[0]>=700) snakeX[0]=0;
                   snakeX[0]+=25;
                   repaint();
                   timer.start();
                   break;
               case left:
                   if(snakeX[0]<=0) snakeX[0]=700;
                   snakeX[0]-=25;
                   repaint();
                   timer.start();
                   break;
               case top:
                   if(snakeY[0]<=0) snakeY[0]=900;
                   snakeY[0]-=25;
                   repaint();
                   timer.start();
                   break;
           }
        if(snakeX[0]==foodX&&snakeY[0]==foodY){
            len++;
            foodX=25+25*random.nextInt(20);
            foodY=25+25*random.nextInt(20);
        }

       }
    }
}
