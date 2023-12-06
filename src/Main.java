import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String request = scanner.nextLine();
        String answer = calc(request);
        System.out.println(answer);
    }


    public static String calc(String request) throws IOException {
        String[] line = request.split(" ");
        boolean isFirstNumberRoman = false;
        boolean isSecondNumberRoman = false;
        int firstNumber;
        int secondNumber;
        int resultNumber;
        String resultChar = "";
        String firstChar = line[0];
        String operand = line[1];
        String secondChar = line[2];
        int[] ARABIC_NUMBERS = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] ROMAN_NUMBERS = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        if (line.length != 3) throw new IOException();

        try {
            firstNumber = Integer.parseInt(firstChar);
            if (firstNumber > 10 || firstNumber < 0) throw new IOException();
        } catch (NumberFormatException e) {
            isFirstNumberRoman = true;
            firstNumber = switch (firstChar) {
                case "I" -> 1;
                case "II" -> 2;
                case "III" -> 3;
                case "IV" -> 4;
                case "V" -> 5;
                case "VI" -> 6;
                case "VII" -> 7;
                case "VIII" -> 8;
                case "IX" -> 9;
                case "X" -> 10;
                default -> throw new IOException();
            };
        }

        try {
            secondNumber = Integer.parseInt(secondChar);
            if (secondNumber > 10 || secondNumber < 0) throw new IOException();
        } catch (NumberFormatException ex) {
            isSecondNumberRoman = true;
            secondNumber = switch (secondChar) {
                case "I" -> 1;
                case "II" -> 2;
                case "III" -> 3;
                case "IV" -> 4;
                case "V" -> 5;
                case "VI" -> 6;
                case "VII" -> 7;
                case "VIII" -> 8;
                case "IX" -> 9;
                case "X" -> 10;
                default -> throw new IOException();
            };
        }

        resultNumber = switch (operand) {
            case "+" -> firstNumber + secondNumber;
            case "-" -> firstNumber - secondNumber;
            case "*" -> firstNumber * secondNumber;
            case "/" -> firstNumber / secondNumber;
            default -> throw new IOException();
        };

        if (isFirstNumberRoman && resultNumber <= 0) {
            System.out.println("В римской системе нет отрицательных чисел и нуля!");
            throw new IOException();
        }

        if (!isFirstNumberRoman) {
            resultChar = Integer.toString(resultNumber);
        } else {
            StringBuilder romanNumber = new StringBuilder();
            for (int i = 0; i < ARABIC_NUMBERS.length; i++) {
                while (resultNumber >= ARABIC_NUMBERS[i]) {
                    romanNumber.append(ROMAN_NUMBERS[i]);
                    resultNumber -= ARABIC_NUMBERS[i];
                }
                resultChar = romanNumber.toString();
            }
        }

        if (isFirstNumberRoman != isSecondNumberRoman) {
            System.out.println("Введены данные разных типов!");
            throw new IOException();
        }
        return resultChar;


    }

}