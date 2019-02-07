// Java implementation of Counting Sort
class CountingSortExample
{
  static void sort(int arr[])
  {
    int n = arr.length;
    
    // The output character array that will have sorted arr
    int output[] = new int[n];
    
    // Create a count array to store count of inidividul
    // characters and initialize count array as 0
    int count[] = new int[256];
    for (int i=0; i<256; ++i)
      count[i] = 0;
    
    // store count of each character
    for (int i=0; i<n; ++i)
      ++count[arr[i]];
    
    // Change count[i] so that count[i] now contains actual
    // position of this character in output array
    for (int i=1; i<=255; ++i)
      count[i] += count[i-1];
    
    // Build the output character array
    for (int i = 0; i<n; ++i)
    {
      output[count[arr[i]]-1] = arr[i];
      --count[arr[i]];
    }
    
    // Copy the output array to arr, so that arr now
    // contains sorted characters
    for (int i = 0; i<n; ++i)
      arr[i] = output[i];
  }
  
  // Driver method
  public static void main(String args[])
  {
    //CountingSort ob = new CountingSort();
    int arr[] = {1, 4, 1, 2, 7, 5, 2
    };
    
    sort(arr);
    
    System.out.print("Sorted integer array is ");
    for (int i=0; i<arr.length; ++i)
      System.out.print(arr[i]);
  }
}
/*This code is contributed by Rajat Mishra */