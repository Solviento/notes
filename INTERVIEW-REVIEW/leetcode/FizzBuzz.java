// Write a program that outputs the string representation of numbers from 1 to n.
// But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”. For numbers which are multiples of both three and five output “FizzBuzz”.

class FizzBuzz {
    // straightforward if conditions
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
    }
    // simplifying work by using concatenations
    static String[] fizzBuzzStr(int n){
        String[] fizz = new String[n];
        for(int i = 1; i < n; i++){
            String tmp = "";
            if(i%3 == 0){
                tmp += "Fizz";
            }
            if(i%5 == 0){
                tmp += "Buzz";
            }
            if(tmp.equals("")){
                tmp += String.valueOf(i);
            }
            fizz[i-1] = tmp;
        }
        return fizz;
    }
    // using a hash map to further simply future mappings
    static String[] fizzBuzzHash(int n){
        String[] fizz = new String[n];
        HashMap<Integer, String> map = 
            new HashMap<>(){
                {
                    put(3, "Fizz");
                    put(5, "Buzz");
                    put(7, "Zarr");
                }
            };
        for(int i = 1; i < n; i++){
            String tmp = "";
            // this may not work for hashmap due non-ordering, need to use hashSet for ordering
            for(Integer key: map.keySet()){
                if(i%key==0){
                    tmp += map.get(key);
                }
            }
            if(tmp.equals("")){
                tmp += String.valueOf(i);
            }
            fizz[i-1] = tmp;
        }
    }
    public static void main(String... args){
        int x = 15;
        String[] s = fizzBuzz(x);
        for(String e: s){
            System.out.print(e + " ");
        }
    }
}