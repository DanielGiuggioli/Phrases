public class PermutazioneSomme{

  private static int permutationsQuantity = 0;
  private static int index = 1;
  private static int permutationsIndex = 0;

  public static void main(String[] args) {

    int n = Integer.parseInt(args[0]);
    permutationsQuantity = permutations(n);
    int[][] permutations = getPermutations(n);
    printMatrix(permutations);

  }

  static int[][] getPermutations(int n){

    if(n == 0 || n == 1){
      int[][] res = {{n}};
      return res;
    }
    else{
      int[][] result = new int[permutationsQuantity][];
      int[] baseArray = getBaseArray(n);
      result[0] = baseArray;

      f(baseArray, 0, result);

      int[] lastArr = {n};
      result[permutationsQuantity - 1] = lastArr;
      return result;
    }
  }

  static int permutations(int n){

    return (int)Math.pow(2, n - 1);
  }

  static void printMatrix(int[][] matrix){

    for(int x = 0; x < matrix.length; x++){
      if(matrix[x] != null){
        System.out.print("matrix index " + x + " :\t");
        printArray(matrix[x]);
      }
    }
  }

  static void printArray(int[] arr){

    for(int x = 0; x < arr.length; x++){
      if(x != arr.length - 1)
        System.out.print(arr[x] + " + ");
      else
        System.out.print(arr[x]);
    }
    System.out.println();
  }

  static int[] getBaseArray(int n){

    int[] result = new int[n];
    for(int x = 0; x < n; x++)
      result[x] = 1;
    return result;
  }

  static void f(int[] arr, int target, int[][] result){

    if(arr.length == 2) return;

    var a = join(arr, target);
    addPermutations(a, result);
    g(a, target, result);
    f(a, target, result);
  }

  static void g(int[] arr, int target, int[][] result){

    if(arr.length - target <= 2) return;

    var b = join(arr, ++target);
    addPermutations(b, result);
    h(b, target, result);
    g(b, target, result);
  }

  static void h(int[] arr, int target, int[][] result){

    if(arr.length - target < 2) return;

    if(arr[target - 1] < (arr[target] + arr[target + 1])) return;

    var c = join(arr, target);
    addPermutations(c, result);
    g(c, target, result);
    h(c, target, result);
  }

  public static void addPermutations(int[] arr, int[][] result){

    int[][] p = new int[factorial(arr.length)][];
    getPermutations(copyArr(arr), 0, p);
    for(int x = 0; x < p.length; x++)
      if(p[x] != null)
        result[index++] = p[x];

    permutationsIndex = 0;
  }

  static void getPermutations(int[] arr, int k, int[][] p){

      for(int i = k; i < arr.length; i++){
          swap(arr, i, k);
          if(!contains(p, arr)){
            p[permutationsIndex++] = copyArr(arr);
          }
          getPermutations(copyArr(arr), k + 1, p);
          swap(arr, k, i);
      }
      if (k == arr.length - 1){
         //printArray(arr);
      }
  }

  public static int factorial(int n){

    if(n == 1) return 1;
    return n * factorial(n - 1);
  }

  private static void swap(int[] elements, int a, int b) {

    int tmp = elements[a];
    elements[a] = elements[b];
    elements[b] = tmp;
  }

  static boolean contains(int[][] matrix, int[] arr){

    for(int x = 0; x < matrix.length; x++){
      boolean breaked = false;
      if(matrix[x] != null){
          for(int y = 0; y < matrix[x].length; y++){
            if(matrix[x][y] != arr[y]){
              breaked = true;
              break;
            }
          }
          if(!breaked)
            return true;
        }
      }
    return false;
  }

  static int[] copyArr(int[] arr){

    int[] result = new int[arr.length];
    for(int x = 0; x < arr.length; x++)
      result[x] = arr[x];
    return result;
  }

  static boolean equals(int[] arr1, int[] arr2){

    if(arr1.length != arr2.length) return false;

    for(int x = 0; x < arr1.length; x++){
      if(arr1[x] != arr2[x])
        return false;
    }
    return true;
  }

  static int[] join(int[] arr, int target){

    int[] result = new int[arr.length - 1];
    int y = 0;
    for(int x = 0; x < result.length; x++){
      if(y == target)
        result[x] += arr[y] + arr[++y];
      else
        result[x] = arr[y];
      y++;
    }
    return result;
  }
}
