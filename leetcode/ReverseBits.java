// Reverse bits of a given 32 bits unsigned integer.
// the idea, an int is a 32 long bit
// so to transform 0000 ... 1000 to 0001 ... 0000
// We traverse the bit representation (32 iterations)
class ReverseBits{
    public int reverseBits(int x){
        int reversed = 0;
        int position = 32-1;    // used to iterate through each bit in a 32 bit representation
        while(n != 0 && position >= 0){
            int a = n & 1;
            a <<= position;
            reversed = reversed | a;
            position--;
            n >>= 1;
        }
        return reversed;
    }
    public static void main(String... args){
        int x = 10;
        String s = Integer.toBinaryString(x);
        System.out.println(s);
    }
}