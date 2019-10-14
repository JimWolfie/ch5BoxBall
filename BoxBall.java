import java.awt.*;
import java.awt.geom.*;
import java.util.concurrent.ThreadLocalRandom;
/**
 * BoxBall draws and redraws circles in a box to animate them
 * bouncing off the walls of a box.
 *
 * @author Nicholas Lindgre
 * @version 10/13/19 1.0. 
 */

public class BoxBall
{
    // instance variables - replace the example below with your own
    private Ellipse2D.Double circle;
    private Color color;
    private int diameter;
    private int xPosition;
    private int yPosition;
    private static int top_y;
    private static int bottom_y;
    private static int left_x;
    private static int right_x;
    private Canvas canvas;
    private int ySpeed;
    private int xSpeed;

    /**
     * Constructor for objects of class BoxBall
     * @param Color - color of the ball
     * @param diameter - diameter of the ball
     * @param canvas - canvas this is drawn in.
     * @param box - dimensions of the canvas object 
     * constructor represents the circle somewhere inside the bounds
     * then draws the box. this should be its own class
     * does not draw circle on the line. 
     * top line < bottom line
     * left line < right line 
     * intital speed is 1-7 becasue assignemnt asks that the balls only move around 7ish units
     * 
     */
    
    public BoxBall(Color color, int diameter, Dimension box, Canvas canvas)
    {
        this.color = color;
        this.diameter = diameter;
        this.canvas = canvas;
        this.top_y = 10;
        this.bottom_y = (int)box.getHeight()-10;
        this.left_x = 10;
        this.right_x = (int)box.getWidth()-10;
        this.xPosition = ThreadLocalRandom.current().nextInt(left_x + diameter, right_x - diameter);
        this.yPosition = ThreadLocalRandom.current().nextInt(top_y + diameter, bottom_y - diameter);
        this.ySpeed = ThreadLocalRandom.current().nextInt(1, 8);
        this.xSpeed = ThreadLocalRandom.current().nextInt(1, 8);
        canvas.drawLine(left_x,top_y, right_x, top_y); //top line
        canvas.drawLine(left_x, bottom_y, right_x, bottom_y); //bottom line
        canvas.drawLine(left_x, top_y, left_x, bottom_y); //left 
        canvas.drawLine(right_x, top_y, right_x, bottom_y); //right
    }

    /**
     * Draw this ball at its current position onto the canvas.
     *  @author Michael Kölling (mik)
     * @author David J. Barnes
     * @author Bruce Quig
     * copied this over from bouncing ball
     **/
    public void draw()
    {
        canvas.setForegroundColor(color);
        canvas.fillCircle(xPosition, yPosition, diameter);
    }

    /**
     * Draw this ball at its current position onto the canvas.
     *  @author Michael Kölling (mik)
     * @author David J. Barnes
     * @author Bruce Quig
     * copied this over from bouncing ball
     **/
    public void erase()
    {
        canvas.eraseCircle(xPosition, yPosition, diameter);
    }
    /**
     * move the balls. 
     */
    public void move()
    {
        //get the ball's max legal distance 
        int x_min = left_x+1; //left
        int x_max = right_x-diameter; //right
        int y_min = top_y+1; //top
        int y_max = bottom_y-diameter; //bottom
        //erase the ball
        erase();
        // update the ball
        xPosition += xSpeed;
        yPosition += ySpeed;
        
        
        //check if hit wall x axis first
        if (xPosition < x_min)
        {
            xSpeed = -xSpeed;
            xPosition = x_min;
        }else if(xPosition > x_max)
        {
            xSpeed = -xSpeed;
            xPosition = x_max;
        }
        if (yPosition < y_min)
        {
            ySpeed = -ySpeed;
            yPosition = y_min;
        }else if(yPosition > y_max)
        {
            ySpeed = -ySpeed;
            yPosition = y_max;
        }
        //redraw the ball
        draw();
    }
}
