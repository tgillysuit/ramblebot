import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * A tokenizer that converts text input to lowercase and splits it 
 * into a list of tokens, where each token is either a word or a period.
 */
public class LowercaseSentenceTokenizer implements Tokenizer {
  /**
   * Tokenizes the text from the given Scanner. The method should 
   * convert the text to lowercase and split it into words and periods.
   * Words are separated by spaces, and periods are treated as separate tokens.
   * 
   * For example:
   * If the input text is: "Hello world. This is an example."
   * The tokenized output should be: ["hello", "world", ".", "this", "is", "an", "example", "."]
   * 
   * Notice that the text is converted to lowercase, and each period is treated as a separate token.
   * 
   * However, a period should only be considered a separate token if it occurs at the end
   * of a word. For example:
   * 
   * If the input text is: "Hello world. This is Dr.Smith's example."
   * The tokenized output should be: ["hello", "world", ".", "this", "is", "dr.smith's", "example", "."]
   * 
   * The internal period in Dr.Smith's is not treated as its own token because it does not occur at the end of the word.
   * 
   * @param scanner the Scanner to read the input text from
   * @return a list of tokens, where each token is a word or a period
   */
   public List<String> tokenize(Scanner scanner) {
    // TODO: Implement this function to convert the scanner's input to a list of words and periods
    List<String> tokens = new ArrayList<>();

    // Reading the input from the scanner, while using .useDelimiter to separates the tokens that are being scanned.
    // Also converting the scanned tokens to lower case
    String input = scanner.useDelimiter("\\A").next().toLowerCase();

    // Created a new variable to build tokens
    String currentToken = "";

    // Looping through each character in the input string
    for (int i = 0; i < input.length(); i++) {
      char currentChar = input.charAt(i);

      // Checking if the current character is a period, also checking if there is a space after the period.
      // Java Docs on the .isWhitespace method from the Character class. vvv
      // Link: https://docs.oracle.com/javase/6/docs/api/java/lang/Character.html#isWhitespace(int)
      if (currentChar == '.' && (i == input.length() - 1 || Character.isWhitespace(input.charAt(i + 1)))) {
        if (!currentToken.isEmpty()) {
          tokens.add(currentToken);
          currentToken = "";
          }
          tokens.add(".");
        }

        // Checking the current character for whitespace, and if there is a word the program will add the token.
        else if (Character.isWhitespace(currentChar)) {
          if (!currentToken.isEmpty()) {
            tokens.add(currentToken);
            currentToken = "";
          }
        }

        // If the current character is part of a word, the program will be added to currentToken
        else {
          currentToken += currentChar;
        }
      }

      // Adding the last token if it exists
      if (!currentToken.isEmpty()) {
        tokens.add(currentToken);
      }
    return tokens;
  }
}


