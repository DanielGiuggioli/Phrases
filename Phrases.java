public class Phrases{
	
	static char separator = ' ';
	static String starting = "ciaoatutti";
	static String[] dictionary = {"ciao", "a", "tutti", "puppa", "puppami", "la", "fava", "mila", "va"};
	static String[] result = new String[0];
	
	public static void main(String[] args){
	
		System.out.println();
		getAllPhrases();
		
		for(int x = 0; x < result.length; x++)
			System.out.println(result[x]);
	}
	
	static void getAllPhrases(){
		
		int y = 0;
		do{
			
			int z = -1;
			String currentWord = getWordUntil(y);
			boolean firstWord = false;
			String newPhrase = "";
			String newPhrase_temp = "";
			
			for(int x = y; x < starting.length(); x++){
				currentWord += starting.charAt(x);
				
				if(!firstWord)
					y++;
				
				if(isInTheDictionary(currentWord)){
					newPhrase_temp += currentWord + separator;
					currentWord = "";
					firstWord = true;
					z++;
					
					if(x == starting.length() - 1){
						updateResult(newPhrase_temp);
						newPhrase = newPhrase_temp;
					}
				}
					
				if(x == starting.length() - 1){
					
					if(z > 1){
						String[] phrase = split(newPhrase, separator);
						x -= getXValue(z, phrase);
						currentWord = phrase[z - 1];
						newPhrase_temp = newPhrase_temp.substring(0, x + z - (currentWord.length()));
					}
					z-= 2;
				}
				
			}
			
		}while(y < starting.length());
	}
	
	private static int getXValue(int z, String[] phrase){
		
		int res = 0;
		for(int x = z; x < phrase.length; x++){
			res += phrase[x].length();
		}
		
		return res;
	}
	
	private static String getWordUntil(int index){
		
		String res = "";
		for(int x = 0; x < index; x++)
			res += starting.charAt(x);
		
		return res;
	}
	
	private static boolean isInTheDictionary(String word){
		
		for(int x = 0; x < dictionary.length; x++)
			if(equals(dictionary[x], word))
				return true;
		return false;
	}
	
	static boolean equals(String str1, String str2){
		
		if(str1.length() != str2.length())
			return false;
		for(int x = 0; x < str1.length(); x++)
			if(str1.charAt(x) != str2.charAt(x))
				return false;
		return true;
	}
	
	private static void updateResult(String word){
		
		String[] newResult = new String[result.length + 1];
		for(int x = 0; x < result.length; x++)
			newResult[x] = result[x];
		newResult[result.length] = word;
		result = newResult;
	}
	
	private static String[] split(String word, char c){

		if(word.length() == 0)
		  return new String[0];
	  
		int occurrences = 0;
		char previousChar = word.charAt(0);
		for(int x = 1; x < word.length(); x++){
		  if(word.charAt(x) == c && previousChar != c)
			occurrences++;
		  previousChar = word.charAt(x);
		}

		String[] arr = new String[occurrences];
		int index = 0;
		String currentString = "";
		for(int x = 0; x < word.length(); x++){
		  if(word.charAt(x) != c)
			currentString += word.charAt(x);
		  else if(currentString != ""){
			arr[index++] = currentString;
			currentString = "";
		  }
		}
		return arr;
  }
}