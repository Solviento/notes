// Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.

class bitwiseOperations {
  public int getSum(int a, int b) {
    while (b != 0) {
      int carry = a & b;
      a = a ^ b;
      b = carry << 1;
    }
    return a;
  }
}