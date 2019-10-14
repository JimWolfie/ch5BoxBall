import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.Dimension;
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
    private ArrayList<BoxBall> balls;
   

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
        balls = new ArrayList<BoxBall>();
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
        
        //define the box
        int a = 0; //iterator for later
        myCanvas.setVisible(true);
        //I'm not loading that into the loop.
        //so cached
        Dimension box = myCanvas.getSize();
        
        //add them to list
        do
        {
            int r = ThreadLocalRandom.current().nextInt(20, 256);
            int g = ThreadLocalRandom.current().nextInt(20, 256);
            int b = ThreadLocalRandom.current().nextInt(20, 256);
             
           
           balls.add( new BoxBall(new Color(r, g, b), 20, box, myCanvas) );
           balls.get(a).draw();
           a+=1;
        }while (a < numBalls );
        
        //move them for test
        do 
        {
            myCanvas.wait(50);
            for(BoxBall b : balls)
            {
                b.move();
            }
            //a-=1; set to infinite because didn't specificy end condition 
        }while (a>= numBalls);
    }
}
