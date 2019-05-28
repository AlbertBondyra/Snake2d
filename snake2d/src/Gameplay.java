import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import java.awt.*;
import java.util.Random;


public class Gameplay extends JPanel implements KeyListener,  ActionListener{
    private int[] snakeXlength= new int[750];
    private int[] snakeYlength= new int[750];
    private boolean left=false;
    private boolean right=false;
    private boolean up=false;
    private boolean down=false;
    private ImageIcon rightmouth;
    private ImageIcon downtmouth;
    private ImageIcon lefttmouth;
    private ImageIcon upmouth;
    private int [] enemyypos={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};
    private int [] enemyxpos={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,
            675,700,725,750,775,800,825,850};
    private int  lengthofsnake = 3;
    private ImageIcon enemyimage;
    private Random random= new Random();
    //domyslna lokalizacja jedzenia
    private int xpos= random.nextInt(34);
    private int ypos= random.nextInt(23);
    private int score=0;
    private Timer timer;
    private int delay=100;
    private boolean GameLive=true;
    private ImageIcon snakeimage;
    private int moves=0;
    private ImageIcon titleImage,titleImage2,titleImage3;
    public Gameplay(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer=new Timer(delay,this);
        timer.start();
    }
    public void paint (Graphics g )
    {
        //poruszanie sie
        if(moves == 0)
        {
            snakeXlength[2]=50;
            snakeXlength[1]=75;
            snakeXlength[0]=100;

            snakeYlength[2]=100;
            snakeYlength[1]=100;
            snakeYlength[0]=100;
        }
        g.setColor(Color.white);
        g.drawRect(24,10,851,55);
        titleImage=new ImageIcon("snaketitle.gif");
        titleImage2=new ImageIcon("tlo.gif");
        titleImage3=new ImageIcon("tlo2.gif");
        titleImage.paintIcon(this, g,25,11);
        titleImage3.paintIcon(this, g,25,11);
        titleImage2.paintIcon(this, g,825,11);
        //rysuj granice
        g.setColor(Color.white);
        g.fillRect(24,74,852,577);
        //rysuj tlo
        g.setColor(Color.black);
        g.fillRect(25,75,850,575);
        //rysowanie wyniku
        g.setColor(Color.white);
        g.setFont(new Font("arial",Font.PLAIN,14));
        g.drawString("Punkty:"+score,705,30);
        g.setColor(Color.white);
        g.setFont(new Font("arial",Font.PLAIN,14));
        g.drawString("Dlugosc:"+lengthofsnake,705,50);

        rightmouth=new ImageIcon("rightmouth.gif");
        rightmouth.paintIcon(this,g,snakeXlength[0],snakeYlength[0]);
        for (int k=0; k<lengthofsnake; k++)
        {
            //rysowanie prawej glowy
            if(k==0 && right)
            {
                rightmouth=new ImageIcon("rightmouth.gif");
                rightmouth.paintIcon(this,g,snakeXlength[k],snakeYlength[k]);
            }
            //rysowanie lewej glowy
            if(k==0 && left)
            {
                lefttmouth=new ImageIcon("leftmouth.gif");
                lefttmouth.paintIcon(this,g,snakeXlength[k],snakeYlength[k]);
            }
            //rysowanie dolnej glowy
            if(k==0 && down)
            {
                downtmouth=new ImageIcon("downmouth.gif");
                downtmouth.paintIcon(this,g,snakeXlength[k],snakeYlength[k]);
            }
            //rysowanie gornej glowy
            if(k==0 && up)
            {
                upmouth=new ImageIcon("upmouth.gif");
                upmouth.paintIcon(this,g,snakeXlength[k],snakeYlength[k]);
            }
            //rysowanie ciala
            if(k!=0)
            {
                snakeimage=new ImageIcon("snakeimage.gif");
                snakeimage.paintIcon(this,g,snakeXlength[k],snakeYlength[k]);
            }
            //rysowanie jedzenia


        }
        enemyimage=new ImageIcon("enemy_jablko.gif");
        if((enemyxpos[xpos]==snakeXlength[0]) && enemyypos[ypos]==snakeYlength[0]){
            score++;
            lengthofsnake++;
            xpos=random.nextInt(30);
            ypos=random.nextInt(22);

        }
        enemyimage.paintIcon(this,g,enemyxpos[xpos],enemyypos[ypos]);
        for(int b=1; b<lengthofsnake; b++)
        {
            if(snakeXlength[b]==snakeXlength[0] && snakeYlength[b]==snakeYlength[0])
            {
                right=false;
                up=false;
                down=false;
                left=false;
                GameLive=false;
                g.setColor(Color.white);
                g.setFont(new Font("arial",Font.BOLD,50));
                g.drawString("KONIEC GRY!",300,300);
                g.setFont(new Font("arial",Font.BOLD,20));
                g.drawString("Restart-> Spacja",300,350);

            }
        }

        g.dispose();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(right)
        {
            for (int r=lengthofsnake-1; r>=0; r--)
            {
                snakeYlength[r+1]=snakeYlength[r];
            }
            for (int r=lengthofsnake; r>=0; r--)
            {
                if(r==0)
                {
                    snakeXlength[r]=snakeXlength[r]+25;
                }
                else {
                    snakeXlength[r]=snakeXlength[r-1];
                }
                if(snakeXlength[r]>850)
                {
                    snakeXlength[r]=25;
                }
            }
            repaint();
        }
        if(left)
        {
            for (int r=lengthofsnake-1; r>=0; r--)
            {
                snakeYlength[r+1]=snakeYlength[r];
            }
            for (int r=lengthofsnake; r>=0; r--)
            {
                if(r==0)
                {
                    snakeXlength[r]=snakeXlength[r]-25;
                }
                else {
                    snakeXlength[r]=snakeXlength[r-1];
                }
                if(snakeXlength[r]<25)
                {
                    snakeXlength[r]=850;
                }
            }
            repaint();
        }
        if(down)
        {
            for (int r=lengthofsnake-1; r>=0; r--)
            {
                snakeXlength[r+1]=snakeXlength[r];
            }
            for (int r=lengthofsnake; r>=0; r--)
            {
                if(r==0)
                {
                    snakeYlength[r]=snakeYlength[r]+25;
                }
                else {
                    snakeYlength[r]=snakeYlength[r-1];
                }
                if(snakeYlength[r]>=650)
                {
                    snakeYlength[r]=75;
                }
            }
            repaint();
        }
        if(up)
        {
            for (int r=lengthofsnake-1; r>=0; r--)
            {
                snakeXlength[r+1]=snakeXlength[r];
            }
            for (int r=lengthofsnake; r>=0; r--)
            {
                if(r==0)
                {
                    snakeYlength[r]=snakeYlength[r]-25;
                }
                else {
                    snakeYlength[r]=snakeYlength[r-1];
                }
                if(snakeYlength[r]<=50)
                {
                    snakeYlength[r]=625;
                }
            }
            repaint();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_SPACE)
        {
            right=true;
            up=false;
            down=false;
            left=false;
            moves=0;
            score=0;
            lengthofsnake=3;
            repaint();
            GameLive=true;

        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT && GameLive)
        {
            moves++;
            right=true;
            if(!left)
            {
                right=true;
            }
            else {
                right=false;
                left=true;
            }
            down=false;
            up=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT && GameLive)
        {
            moves++;
            left=true;
            if(!right)
            {
                left=true;
            }
            else {
                left=false;
                right=true;
            }
            down=false;
            up=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_DOWN && GameLive)
        {
            moves++;
            down=true;
            if(!up)
            {
                down=true;
            }
            else {
                down=false;
                up=true;
            }
            left=false;
            right=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_UP && GameLive)
        {
            moves++;
            up=true;
            if(!down)
            {
                up=true;
            }
            else {
                up=false;
                down=true;
            }
            left=false;
            right=false;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
