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
    private final int top_y;//walls for box 
    private final int bottom_y;
    private final int left_x;
    private final int right_x;
    private Canvas canvas;
    private int ySpeed;
    private int xSpeed;

    /**
     * Constructor for objects of class BoxBall
     * @param Color - color of the ball
     * @param diameter - diameter of the ball
     * @param top_y - y value of the top of box
     * @param bottom_y - y value of the bottom of box
     * @param left_x - x value of the left wall
     * @param right_x -x value of the right wall
     * @param canvas - canvas this is drawn in.
     * constructor predraws the circle somewhere inside the box
     * does not draw on the line. 
     * top line < bottom line
     * left line < right line 
     * intital speed is 1-7 becasue assignemnt asks that the balls only move around 7ish units
     * 
     */
    public BoxBall(Color color, int diameter, int top_y, int bottom_y, int left_x, int right_x,
        Canvas canvas)
    {
        this.color = color;
        this.diameter = diameter;
        this.top_y = top_y;
        this.bottom_y = bottom_y;
        this.left_x = left_x;
        this.right_x = right_x;
        this.canvas = canvas;
        this.xPosition = ThreadLocalRandom.current().nextInt(this.left_x + diameter, this.right_x - diameter);
        this.yPosition = ThreadLocalRandom.current().nextInt(this.top_y + diameter, this.bottom_y - diameter);
        this.ySpeed = ThreadLocalRandom.current().nextInt(1, 8);
        this.xSpeed = ThreadLocalRandom.current().nextInt(1, 8);
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
