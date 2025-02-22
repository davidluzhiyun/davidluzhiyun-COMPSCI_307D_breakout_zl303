package breakout;

import javafx.scene.paint.Color;

public class LifeSaverPowerUp extends PowerUp{
  public LifeSaverPowerUp(double X, double Y){
    super(X, Y, Color.RED);
  }

  /**
   * Activates powerup effect in getMyFieldEdge
   * to enable one free missed ball
   */
  @Override
  protected void powerup(Game game){
    game.getMyFieldEdge().powerup();
  }
}
