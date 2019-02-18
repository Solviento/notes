class CountPrimes {
    // sieve of eratosthenes, use empty boolean array, then utilize all prime numbers from 2 -> n^1.5
    // to mark off booleans as composites IFF they are multiples of those prime numbers
    // any remaining non-marked booleans will be prime
    public int countPrimes(int n) {
        boolean[] primeArr = new boolean[n];
        // we only care about 2, 3, 4... since we know 0 and 1 are non prime
        for(int i = 2; i < n; i++){
            primeArr[i] = true;     // all booleans are defaulted to true until proven as non prime
        }
        for(int i = 2; i*i<n; i++){
            // check if some previous iteration marked off current boolean[i] as non prime
            if(primeArr[i] == false){
                continue;
            }
            for(int j = i*i; j < n; j+=i){
                primeArr[j] = false;
            }
        }
        int count = 0;
        for(int i = 0; i < primeArr.length; i++){
            if(primeArr[i] == true){
                // System.out.println(i);
                count++;
            }
        }
        return count;
    }
    // simple O(n^1.5) function to verify primeness of a number
    private int isPrimeFunction(int n){
        if (n <= 1){
            return 0;
        }
        // the whole idea here is to divide a number by increments starting from 2
        // aka, if n is cleanly divisble by 2, not prime, is mod 3 == 0, not prime and so forth
        // the time optimizer is based on the condition that i <= sqrt(n) if you notice the pattern that we only care about increments up till the square root of that integer, do it by hand to remember
        for(int i = 2; i * i <= n; i++){
            if(n%i==0)
                return 0;
        }
        return 1;
    }
}