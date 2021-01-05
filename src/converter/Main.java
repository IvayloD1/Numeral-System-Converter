package converter;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    try {
      // check source radix input
      int sourceRadix = scanner.nextInt();
      if (sourceRadix < 1 || sourceRadix > Character.MAX_RADIX) {
        throw new InputMismatchException("Error: source radix should be between 1 and 36!");
      }

      // check source number input
      String sourceNumber = scanner.next();
      if (!sourceNumber.matches("[A-Za-z0-9.]+")) {
        throw new InputMismatchException("Error: Source number is wrong!");
      }

      // check target radix input
      int targetRadix = scanner.nextInt();
      if (targetRadix < 1 || targetRadix > Character.MAX_RADIX) {
        throw new InputMismatchException("Error: target radix should be between 1 and 36!");
      }

      // convert number to decimal
      if (sourceRadix != 10) {
        sourceNumber = Converter.convertToDecimal(sourceNumber, sourceRadix);
      }
      // convert decimal number to target radix
      String result = Converter.convertDecimalToRadix(sourceNumber, targetRadix);
      System.out.println(result);
    } catch (InputMismatchException e) {
      System.out.println("Error: radix should be a number!");

    } catch (NoSuchElementException e) {
      System.out.println(
          "Error: you should enter three numbers: sourceRadix, sourceNumber, targetRadix!");
    }
  }
}