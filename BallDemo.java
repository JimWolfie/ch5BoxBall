import java.awt.Color;

/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Bill Crosbie
 * @version 2015-March-BB
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 * 
 * @author Nicholas Lindgren
 * @version 2019-October-14 1.0  
 */

public class BallDemo   
{
    private Canvas myCanvas;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
    }

    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.drawLine(50, ground, 550, ground);

        // crate and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        ball2.draw();

        // make them bounce
        boolean finished =  false;
        while(!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }
    /**
     * simulates balls bouncing in a box.
     * @param numBalls - how many balls to load in box
     */
    public void boxBounce(int numBalls)
    {
        //test for 100 bounces on 1 ball
        //define the box
        myCanvas.setVisible(true);
        int top_y = 10;
        int bottom_y = 490;
        int left_x=10;
        int right_x = 490;
        myCanvas.drawLine(left_x,top_y, right_x, top_y); //top line
        myCanvas.drawLine(left_x, bottom_y, right_x, bottom_y); //bottom line
        myCanvas.drawLine(left_x, top_y, left_x, bottom_y); //left 
        myCanvas.drawLine(right_x, top_y, right_x, bottom_y); //right
    }
}
