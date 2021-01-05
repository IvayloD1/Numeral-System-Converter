package converter;

public class Converter {

  private Converter() {
  }

  public static String convertDecimalToRadix(String number, int radix) {
    // check if the number has a floating part
    if (number.contains(".")) {
      // convert number to double
      double givenNumber = Double.parseDouble(number);
      // calculate the integer and fractional parts of the number
      int integerPart = (int) givenNumber;
      double fractionalPart = givenNumber - integerPart;
      // convert the integer and fractional parts of the number
      String convertedIntegerPart = integerToRadix(integerPart, radix);
      String convertedFractionalPart = fractionalToRadix(fractionalPart, radix);
      // return result
      return convertedIntegerPart + "." + convertedFractionalPart;
    } else {
      // convert number to integer
      int integer = Integer.parseInt(number);
      // convert the integer and return result
      return integerToRadix(integer, radix);
    }
  }

  public static String convertToDecimal(String number, int radix) {
    // check if the number has a floating part
    if (number.contains(".")) {
      // split a number to an integer and a fraction
      String[] numberParts = number.split("\\.");
      // convert both parts to decimal numbers
      int integerPart = integerToDecimal(numberParts[0], radix);
      double fractionalPart = fractionalToDecimal(numberParts[1], radix);
      // sum up both parts and return the result
      return Double.toString(integerPart + fractionalPart);
    } else {
      // convert integer to decimal and return result
      return Integer.toString(integerToDecimal(number, radix));
    }
  }

  private static int integerToDecimal(String integer, int radix) {
    return radix == 1 ? integer.length() : Integer.parseInt(integer, radix);
  }

  private static double fractionalToDecimal(String fractionalPart, int radix) {
    double fractional = 0;
    for (int i = 1; i <= fractionalPart.length(); i++) {
      fractional += Character.digit(fractionalPart.charAt(i - 1), radix) / Math.pow(radix, i);
    }
    return fractional;
  }

  private static String integerToRadix(int number, int radix) {
    return radix == 1 ? "1".repeat(number) : Integer.toString(number, radix);
  }

  private static String fractionalToRadix(double fractionalPart, int radix) {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < 5; i++) { // should output only 5 digits (letters) in the fraction
      // calculate the integer part
      int integerPart = (int) Math.floor(fractionalPart * radix);
      // add the integer part represented by the specified radix to string builder
      sb.append(Character.forDigit(integerPart, radix));
      // calculate fractional part (10_000 is used to avoid incorrect fractional calculations)
      fractionalPart = fractionalPart * 10_000 * radix / 10_000 - integerPart;
    }
    return sb.toString();
  }
}