package ca.jrvs.practice.codingChallenge;


/**
 * ticket: https://www.notion.so/jarvisdev/Sample-Check-if-a-number-is-even-or-odd-655990582bcc4e029c8062d137437078
 */
public class OddEven {

  /**
   * Big-O: O(1)
   * Justification: Arithmetic Operation
   * @param i integer
   * @return 'even' if integer is even, 'odd' if integer is odd
   */
  public String oddEvenMod(int i){
    if(i % 2 == 0){
      return "even";
    }
    return "odd";
  }

  /**
   * XOR operator
   * Big-O: O(1)
   * Justification: Arithmetic Operation
   * @param i integer
   * @return 'even' if integer is even, 'odd' if integer is odd
   */
  public String oddEvenBit(int i){
    if ((i ^ 1) == i + 1)
      return "even";
    else
      return "odd";
  }

}
