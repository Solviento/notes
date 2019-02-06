import java.util.Random;
import java.util.ArrayList;

public class drunk {
  private int currentAve, currentSt, initAve, initSt;
  public drunk(int avenue, int st) {
    // Initialize and save user grid location in the format (x, y)
    currentAve = avenue; // Our "y" value
    initAve = avenue;
    currentSt = st; // Our "x" value
    initSt = st;
  }

  public void step() {
    // Order of grid directions
    // 1 - North, 2 - South, 3 - West, 4 - East
    int x = randInt(1, 4);
    if (x == 1) {
      currentAve += 1; // Go up one y value
    }
    if (x == 2) {
      currentSt += 1; // Go up one x value
    }
    if (x == 3) {
      currentAve -= 1; // Go down one y value
    }
    if (x == 4) {
      currentSt -= 1; // Go down one value
    }
  }

  // randInt will generate a random number between the min and max
  public static int randInt(int min, int max) {
    Random rand = new Random();
    int randomNum = rand.nextInt((max - min) + 1) + min;
    return randomNum;
  }

  // fastForward will run step() for any number of iterations
  public void fastForward(int steps) {
    for (int i = 0; i < steps; i++) {
      step();
    }
  }

  // getLocation returns user grid location as a string
  public String getLocation() {
    int x = getStreet(), y = getAve();
    String g = Integer.toString(x), h = Integer.toString(y);
    return g + ", " + h;
  }

  // getStreet and getAve returns street and avenue values
  public int getStreet() {
    return currentSt;
  }

  public int getAve() {
    return currentAve;
  }

  // howFar returns Manhattan distance for current position vs starting position
  public int howFar() {
    int dist;
    int x = Math.abs(initAve - currentAve);
    int y = Math.abs(initSt - currentSt);
    dist = x + y;
    return dist;
  }
}