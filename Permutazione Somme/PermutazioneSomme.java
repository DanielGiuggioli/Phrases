public class PermutazioneSomme{

  private static int permutationsQuantity = 0;
  private static int index = 1;

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

  static String[] split(String str, char c){
    if(str.length() == 0)
      return new String[0];
    int occurrences = 0;
    char previousChar = str.charAt(0);
    for(int x = 1; x < str.length(); x++){

      if(str.charAt(x) == c && previousChar != c)
        occurrences++;
      previousChar = str.charAt(x);
    }
    System.out.println("occurrences of " + c + " = " + occurrences);
    String[] arr = new String[occurrences];
    int index = 0;
    String currentString = "";
    for(int x = 0; x < str.length(); x++){
      if(str.charAt(x) != c)
        currentString += str.charAt(x);
      else if (str.charAt(x) == c){
        arr[index++] = currentString;
        currentString = "";
      }
    }
    return arr;
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






  // 1111 --> 211 --> 31 --> end  + call g for each result
  static void f(int[] arr, int target, int[][] result){

    if(arr.length == 2) return;

    var a = join(arr, target);
    result[index++] = a;

    //addPermutations(a, result);
    addPermutations(a.length, a);

    g(a, target, result);

    // recursive step
    f(a, target, result);

    return;
  }

  static void g(int[] arr, int target, int[][] result){

    if(arr.length - target <= 2) return;

    var b = join(arr, ++target);
    result[index++] = b;

    //addPermutations(b.length, b);
    //addPermutations(b, result);

    h(b, target, result);

    // recursive step
    g(b, target, result);
  }

  static void h(int[] arr, int target, int[][] result){

    if(arr.length - target < 2) return;

    if(arr[target - 1] < (arr[target] + arr[target + 1])) return;

    var c = join(arr, target);
    result[index++] = c;

    //addPermutations(c, result);
    //addPermutations(c.length, c);

    g(c, target, result);

    // recursive step
    h(c, target, result);
  }

  // 3 2 1 1 (arr = startingArr) -->
  /*static void addPermutations(int[] arr, int[][] result){

    int[] startingArr = copyArr(arr);
    int[] currentArr;

    // 2 2 1 --> 2 2 1 --> 2 1 2 -->
    // 4 3 2 1 --> 3 4 2 1 --> 3 2 4 1 --> 3 2 1 4 --> 4 3 2 1 NO -->
    //                      --> 2 3 4 1 --> 2 4 3 1 --> 2 4 1 3 --> 3 2 4 1 NO
    for(int x = 0; x < arr.length; x++){
      currentArr = copyArr(startingArr);
      for(int y = 0; y < (arr.length - x - 1); y++){

          int[] d = switchArr(currentArr, x, y);
          if(!equals(d, currentArr)){
            result[index++] = d;

            System.out.println();
            System.out.println("Matrix: ");
            printMatrix(result);
            System.out.println();

            currentArr = copyArr(d);
          }
      }
    }
  }*/

  private static void swap(int[] elements, int a, int b) {
    int tmp = elements[a];
    elements[a] = elements[b];
    elements[b] = tmp;
  }
  private static void p(int[] elements) {
    for(int i = 0; i < elements.length; i++) {
        System.out.print(elements[i] + " ");
    }
    System.out.print('\n');
}

    public static void addPermutations(int n, int[] arr){

      if(n == 1) {
        System.out.println();
        System.out.println("!!!");
        p(arr);
      }
      else{
        for(int i = 0; i < n - 1; i++) {
            addPermutations(n - 1, arr);
            if(n % 2 == 0)
              swap(arr, i, n-1);
            else
              swap(arr, 0, n-1);
        }
        addPermutations(n - 1, arr);
      }
    }


    /*if(target)
    int[] startingArr = copyArr(arr);
    int[] currentArr;


    // 2 2 1 --> 2 2 1 --> 2 1 2 -->
    // 4 3 2 1 --> 3 4 2 1 --> 3 2 4 1 --> 3 2 1 4 --> 4 3 2 1 NO -->
    //                      --> 2 3 4 1 --> 2 4 3 1 --> 2 4 1 3 --> 3 2 4 1 NO
    //                                   -->
    for(int x = 0; x < arr.length; x++){
      currentArr = copyArr(startingArr);
      for(int y = 0; y < (arr.length - x - 1); y++){

          int[] d = switchArr(currentArr, x, y);
          if(!equals(d, currentArr)){
            result[index++] = d;

            System.out.println();
            System.out.println("Matrix: ");
            printMatrix(result);
            System.out.println();

            currentArr = copyArr(d);
          }
      }
    }
  }*/

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

  static int[] switchArr(int[] arr, int x, int y){

    int[] result = new int[arr.length];


    int i = x + y;
    // arr.len = 4
    // i = 0
    if(i > arr.length - 1)
      i = i - (arr.length - 1);

    int i_next; // i_next = 1
    if(i == arr.length - 1)
      i_next = 0;
    else
      i_next = i + 1;

    for(int z = 0; z < result.length; z++){

        if(z == i)
          result[z] = arr[i_next];
        else if(z == i_next)
          result[z] = arr[i];
        else
          result[z] = arr[z];
    }

    return result;
    /*boolean change = false;
    if(arr[i] != arr[i_next]){
      int temp = arr[i_next];
      arr[i_next] = arr[i];
      arr[i] = temp;
      change = true;
    }

    if(change){
      for(int z = 0; z < result.length; z++)
        result[z] = arr[z];
        return result;
    }
    return null;*/
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
