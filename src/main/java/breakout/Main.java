package breakout;

import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * Feel free to completely change this code or delete it entirely.
 * Modified from Main.java by Robert C. Duvall
 * Structure based on ExampleAnimation by Robert C. Duvall
 * @author David Lu
 */
public class Main extends Application {
    // useful names for constant values used
    public static final String TITLE = "Example JavaFX Animation";
    public static final int SIZE = 400;
    // many resources may be in the same shared folder
    // note, leading slash means automatically start in "src/main/resources" folder
    // note, Java always uses forward slash, "/", (even for Windows)
    public static final String RESOURCE_PATH = "/breakout/";
    public static final String BALL_IMAGE = RESOURCE_PATH + "ball.gif";
    public static final String WALL_IMAGE = RESOURCE_PATH + "wall.png";
    public static final int BALL_SIZE = 14;
    public static final int WALL_SIZE = 25;
    /**
     * Initialize what will be displayed.
     */
    @Override
    public void start (Stage stage) {
        ImageView ball = new ImageView(new Image(BALL_IMAGE));
        ball.setFitWidth(BALL_SIZE);
        ball.setFitHeight(BALL_SIZE);
        ball.setX(SIZE / 2 - ball.getBoundsInLocal().getWidth() / 2);
        ball.setY(SIZE / 2 - ball.getBoundsInLocal().getHeight() / 2);

        Group walls = wallBuilder(16,3);

        Group root = new Group(ball,walls);
        Scene scene = new Scene(root, SIZE, SIZE, Color.DARKBLUE);
        stage.setScene(scene);

        stage.setTitle(TITLE);
        stage.show();
    }
    /**
     * Build the Group walls.
     */
    public static Group wallBuilder (int numX, int numY) {
        Image wall_image = new Image(WALL_IMAGE);
        Group walls = new Group();
        for (int i = 0; i < numX; i++) {
            for (int j = 0; j < numY; j++) {
                ImageView wall = new ImageView(wall_image);
                wall.setFitWidth(WALL_SIZE);
                wall.setFitHeight(WALL_SIZE);
                wall.setX(i * WALL_SIZE);
                wall.setY(j * WALL_SIZE);
                walls.getChildren().add(wall);
            }
        }
        return walls;
    }
    /**
    * Collision detector, returns an int.
     * 0 means no collision between ball and object
     * 1 means collision on the top/bottom bound
     * 2 means collision on the left/right bound,
     * deals
    * */
    public static int collisionDetector (ImageView ball, ImageView object) {
        double ballX = ball.getX() + ball.getBoundsInLocal().getWidth()/2;
        double ballY = ball.getY() + ball.getBoundsInLocal().getHeight()/2;
        //Inspired by https://docs.oracle.com/javase/8/javafx/api/javafx/geometry/Bounds.html and
        // https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html
        //Works because the Group walls and the Group root shares the same coordinate system
        Bounds objectBound = object.getBoundsInParent();
        if (! objectBound.contains(ballX,ballY)) {
            return 0;
        }
        else {
            double objectX = object.getX() + objectBound.getWidth() / 2;
            double objectY = object.getY() + objectBound.getHeight() / 2;
            boolean region = Math.abs((ballY - objectY) / (ballX - objectY)) <= Math.abs(objectBound.getHeight() / objectBound.getWidth());
            if (region) {
                return 2;
            }
            else {
                return 1;
            }
        }
    }

    /**
     * Start the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}
