// Shuffle a set of numbers without duplicates.
class ShuffleArray{
    private int[] shuffleArray;
    private int[] original;
    Random rand = new Random();
    
    // returns random index value between min and max
    public int randomIndex(int min, int max){
        // 50-100 range (100-50=50 .. random int from 0 to 50, then + min to get random int in range)
        // add 1 to make it exclusive, DO NOT ADD 1 FOR THIS PROBLEM, WILL CAUSE INDEX OUT OF BOUNDS
        // FOR ARRAY.LENGTH
        return rand.nextInt((max-min))+min;
    }
    
    public Solution(int[] nums) {
        shuffleArray = nums;
        original = nums.clone();
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        shuffleArray = original;
        original = original.clone();
        return shuffleArray;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        // uses yates-fisher algorithm
        for(int i = 0; i < shuffleArray.length; i++){
            int randomizedIndex = randomIndex(i, shuffleArray.length);
            swap(i, randomizedIndex);
        }
        return shuffleArray;
    }
    public void swap(int x, int y){
        int tmp = shuffleArray[x];
        shuffleArray[x] = shuffleArray[y];
        shuffleArray[y] = tmp;
    }
}