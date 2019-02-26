class bits{
    public static void main(String... args){
        int x = 1;
        int y = 12;
        int mask = 0x8;
        // x = x >> 1;
        int z = y & mask;
        String s = Integer.toBinaryString(z);
        System.out.println(z);
        System.out.println(s);
    }
}