class FizzBuzz {
    public static String[] fizzBuzz(int n) {
        String[] fizz = new String[n];
        for(int j = 0; j < n; j++){
            int i = j+1;
            if (i%5 == 0 && i%3 == 0){
                fizz[j] = "FizzBuzz";
            }
            else if (i%5 == 0){
                fizz[j] = "Buzz";
            }
            else if(i%3 == 0){
                fizz[j] = "Fizz";
            }
            else{
                fizz[j] = String.valueOf(i);
            }
        }
        return fizz;
        // List<String> f = Arrays.asList(fizz);
        // return f;
    }
    public static void main(String... args){
        int x = 15;
        String[] s = fizzBuzz(x);
        for(String e: s){
            System.out.print(e + " ");
        }
    }
}