/*
 * findMax - Will locate largest generic objects 
 */

 public class findMaxRect{
  public static <T extends Comparable<T>> T findMax(T[] arr) {
    /*
     * Since we are using the compareTo function we must use an 'extends' on
     * Comparable to override it. Comparable is included in the Java library
     */
    int maxIndex = 0;
    for (int i = 1; i < arr.length; i++) {
      /* Note compareTo requires two comparable generic objects */
      if (arr[i].compareTo(arr[maxIndex]) > 0)
        maxIndex = i;
    }
    return arr[maxIndex];
  }

  public static final void main(String... args) {

    /* Creates three rectangle objects */
    Rectangle rect1 = new Rectangle(22, 4);
    Rectangle rect2 = new Rectangle(10, 4);
    Rectangle rect3 = new Rectangle(23, 5);
    Rectangle rect4 = new Rectangle(24, 25);
    // /* Creating a rectangle array is just like any other type array */
    Rectangle[] rects = { rect1, rect2, rect3, rect4 };
    // // Should print out rect4
    System.out.println(findMax(rects));
  }
}