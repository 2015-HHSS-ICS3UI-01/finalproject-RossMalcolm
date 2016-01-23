
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author malcr1272
 */
public class FinalProject extends JComponent implements KeyListener {

    // Height and Width of the screen for the game
    static final int WIDTH = 1280;
    static final int HEIGHT = 1024;
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;
    //create integer for changing between screens
    int screen = 0;
    //create integer to keep score of keys
    int score = 0;
    Font myFont = new Font("Arial", Font.BOLD, 72);
    //player position variables
    int x = 0;
    int y = 390;
    //Create Array to store rocks
    ArrayList<Rectangle> Rocks = new ArrayList<>();
    //create array to store keys
    ArrayList<Rectangle> Keys = new ArrayList<>();
    //create player Rectangle at the player position
    Rectangle player = new Rectangle(x, y, 262, 193);
    //movement variable
    int moveY = 0;
    //keyboard variables
    //up arrow variable
    boolean up = false;
    //down arrow variable
    boolean down = false;
    boolean keyPressed = false;
    //start
    boolean reset = false;
    //variable for camera
    int camX = 0;
    //import control screen
    BufferedImage controls = loadImage("Controls.jpg");
    //import title screen
    BufferedImage Title = loadImage("Title Screen.jpg");
    //import background image
    BufferedImage water = loadImage("water background.jpg");
    //import picture of player
    BufferedImage Khaled = loadImage("khaled.png");
    //import rocks
    BufferedImage rock = loadImage("rock.png");
    //import keys
    BufferedImage key = loadImage("key.png");
    //import death screen
    BufferedImage DeathScreen = loadImage("congrats.jpg");
    //create method to return image when code says to do

    public BufferedImage loadImage(String filename) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(filename));
        } catch (Exception e) {
            //create error message if file wont load
            System.out.println("Error loading " + filename);
        }
        //return image
        return img;
    }

    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE 
        //make title screen appear when game starts
        if (screen == 0) {
            g.drawImage(Title, 0, 0, this);
        }
        //make the control screen the 2nd screen
        if (screen == 1) {
            g.drawImage(controls, 0, 0, this);
        }
        //set screen 2 as the game screen
        if (screen == 2) {
            //make water background on screen g.drawImage(DeathScreen, 0, 0, this);
            for (int x = 0; x < WIDTH; x = x + 50) {
                for (int y = 0; y < HEIGHT; y = y + 50) {
                    g.drawImage(water, x, y, null);
                }
            }
            //add the player in the game screen       
            g.drawImage(Khaled, player.x - camX, player.y, player.width, player.height, null);

            //draw rocks to doge but leave a space open with a key
            // insert rocks into an array
            for (Rectangle obstacle : Rocks) {
                // draw the rocks in the array when code says to          
                g.drawImage(rock, obstacle.x - camX, obstacle.y, this);
            }
            //insert keys into an array
            for (Rectangle points : Keys) {
                //draw the keys in the array when code says to
                g.drawImage(key, points.x - camX, points.y, points.width, points.height, this);
            }
            //output score
            g.setFont(myFont);
            g.setColor(Color.WHITE);
            g.drawString("" + score, 1000, 100);
        }

        //draw death screen
        if (screen == 3) {
            g.drawImage(DeathScreen, 0, 0, this);
        }
        // GAME DRAWING ENDS HERE


    }

    // The main game loop
    // In here is where all the logic for my game will go
    public void run() {

        // Used to keep track of time used to draw and update the game
        // This is used to limit the framerate later on
        long startTime;
        long deltaTime;

        // the main game loop section
        // game will end if you set done = false;
        boolean done = false;
        while (!done) {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();

            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 
            //make the game only run when screen 2 is up
            if (screen == 2) {
            if (reset == true) {
               start();
        }
             
            
                //when the up arrow is pressed move up
                if (up && !keyPressed) {
                    player.y = player.y - 195;
                    keyPressed = true;
                } else if (down && !keyPressed) {
                    //when the down arrow is pressed move down
                    player.y = player.y + 195;
                    keyPressed = true;
                }

                //change how fast the player is going based on score
                //set a speed before a score of 3
                if (score <= 3) {
                    //make camera move forward
                    camX = camX + 4;
                    //make player move with the camera
                    player.x = player.x + 4;
                    //increase speed after a score of 3
                } else if (score > 3 && score <= 8) {
                    //make camera move forward faster
                    camX = camX + 5;
                    //make player move with the camera faster
                    player.x = player.x + 5;
                    //increase speed after a score of 8
                } else if (score > 8 && score <= 12) {
                    //make camera move forward faster
                    camX = camX + 6;
                    //make player move with the camera faster
                    player.x = player.x + 6;
                    //increase speed after a score of 12
                } else if (score > 12 && score <= 15) {
                    //make camera move forward faster
                    camX = camX + 7;
                    //make player move with the camera faster
                    player.x = player.x + 7;
                    //increase speed after a score of 15
                } else if (score > 15 && score <= 30) {
                    //make camera move forward faster
                    camX = camX + 8;
                    //make player move with the camera faster
                    player.x = player.x + 8;
                    //increase speed after a score of 30
                } else if (score > 30 && score < 35) {
                    //make camera move forward faster
                    camX = camX + 9;
                    //make player move with the camera faster
                    player.x = player.x + 9;
                    //increase speed after a score of 30
                } else if (score > 35 && score < 40) {
                    //make camera move forward faster
                    camX = camX + 10;
                    //make player move with the camera faster
                    player.x = player.x + 10;
                    //increase speed after a score of 35
                } else if (score > 40) {
                    //make camera move forward faster
                    camX = camX + 11;
                    //make player move with the camera faster
                    player.x = player.x + 11;
                    //increase speed after a score of 35
                }


                //use an iterator to remove keys when the player intersects them
                Iterator<Rectangle> it = Keys.iterator();
                while (it.hasNext()) {
                    Rectangle points = it.next();
                    if (points.intersects(player)) {
                        it.remove();
                        //add 1 score everytime player gets a key
                        score = score + 1;
                        System.out.println(score);
                    }
                }

                //make player and rocks collide
                for (Rectangle obstacle : Rocks) {
                    if (obstacle.intersects(player)) {
                        screen = 3;

                    }
                }


            }
            //make player die when he hits the rocks


            // GAME LOGIC ENDS HERE 

            // update the drawing (calls paintComponent)
            repaint();



            // SLOWS DOWN THE GAME BASED ON THE FRAMERATE ABOVE
            // USING SOME SIMPLE MATH
            deltaTime = System.currentTimeMillis() - startTime;
            if (deltaTime > desiredTime) {
                //took too much time, don't wait
            } else {
                try {
                    Thread.sleep(desiredTime - deltaTime);
                } catch (Exception e) {
                };
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creates a windows to show my game
        JFrame frame = new JFrame("My Game");

        // creates an instance of my game
        FinalProject game = new FinalProject();
        // sets the size of my game
        game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // adds the game to the window
        frame.add(game);

        // sets some options and size of the window automatically
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        // shows the window to the user
        frame.setVisible(true);

        //add the keyboard listener
        frame.addKeyListener(game);

        // starts my game loop
        game.run();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP && !(player.y <= 25)) {
            up = true;
        } else if (key == KeyEvent.VK_DOWN && (player.y <= 700)) {
            down = true;
        }
        //make screen go from 0 - 1 after pressing enter
        if (screen == 0 && key == KeyEvent.VK_ENTER) {
            screen = 1;
        } else if (screen == 1 && key == KeyEvent.VK_ENTER) {
            //make screen go from 1 - 2 after pressing enter
            screen = 2;
            reset = true;
        } else if (screen == 3 && key == KeyEvent.VK_ENTER) {
            //make game restart if you press enter  
            screen = 2;
            reset = true;
        } else if (screen == 3 && key == KeyEvent.VK_ESCAPE) {
            //make game restart if you press enter  
            screen = 0;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            keyPressed = false;
            up = false;
            down = false;
        }
        if (key == KeyEvent.VK_ENTER) {
            reset = false;
        }


    }

    public void start() {
        int rockX = 500;
        int keyX = 575;
        //creat for loop to repeat drawing the rocks
        for (int i = 0; i <= 100; i = i + 1) {
            //make random number generator to randomize pattern of rocks
            int randomNumber = (int) (Math.random() * 5) + 1;
            //add the position of the rocks

            if (randomNumber == 1) {
                //missing rock at Y position 390
                Rocks.add(new Rectangle(rockX, 0, 303, 195));
                Rocks.add(new Rectangle(rockX, 195, 303, 195));
                Rocks.add(new Rectangle(rockX, 585, 303, 195));
                Rocks.add(new Rectangle(rockX, 780, 303, 195));
                Keys.add(new Rectangle(keyX - camX, 420, 120, 120));
                //assign these rocks to the number 1 from the number generator
            } else if (randomNumber == 2) {
                //Missing rock at Y position 0          
                Rocks.add(new Rectangle(rockX, 390, 303, 195));
                Rocks.add(new Rectangle(rockX, 195, 303, 195));
                Rocks.add(new Rectangle(rockX, 585, 303, 195));
                Rocks.add(new Rectangle(rockX, 780, 303, 195));
                Keys.add(new Rectangle(keyX - camX, 0, 120, 120));
                //assign these rocks to the number 2 from the number generator
            } else if (randomNumber == 3) {
                ////missing rock at Y position 195         
                Rocks.add(new Rectangle(rockX, 0, 303, 195));
                Rocks.add(new Rectangle(rockX, 390, 303, 195));
                Rocks.add(new Rectangle(rockX, 585, 303, 195));
                Rocks.add(new Rectangle(rockX, 780, 303, 195));
                Keys.add(new Rectangle(keyX - camX, 195, 120, 120));
                //assign these rocks to the number 3 from the number generator
            } else if (randomNumber == 4) {
                //missing rock at Y position 585         
                Rocks.add(new Rectangle(rockX, 0, 303, 195));
                Rocks.add(new Rectangle(rockX, 195, 303, 195));
                Rocks.add(new Rectangle(rockX, 390, 303, 195));
                Rocks.add(new Rectangle(rockX, 780, 303, 195));
                Keys.add(new Rectangle(keyX - camX, 585, 120, 120));
                //assign these rocks to the number 4 from the number generator
            } else if (randomNumber == 5) {
                //missing rock at Y position 780          
                Rocks.add(new Rectangle(rockX, 0, 303, 195));
                Rocks.add(new Rectangle(rockX, 195, 303, 195));
                Rocks.add(new Rectangle(rockX, 585, 303, 195));
                Rocks.add(new Rectangle(rockX, 390, 303, 195));
                Keys.add(new Rectangle(keyX - camX, 780, 120, 120));
                //assign these rocks to the number 5 from the number generator
            }

            //increase X value of rocks for next loop
            rockX = rockX + 1000;
            //increase X value of key for next loop
            keyX = keyX + 1000;
        }


        //Reset variables
        score = 0;
        //movement variable
        moveY = 0;
        //keyboard variables
        //up arrow variable
        up = false;
        //player postition variables
        x = 0;
        y = 390;
        //down arrow variable
        down = false;
        keyPressed = false;

        //variable for camera
        camX = 0;

        player = new Rectangle(x, y, 262, 193);


    }
}