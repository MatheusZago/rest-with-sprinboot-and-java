package br.com.matheus.utils;

import br.com.matheus.exceptions.UnsupportedMathOperationException;

public class MathUtils {

	public static Double convetToDouble(String strNumber) {
		if (strNumber == null)
			return 0D;
		// Trabalhando com multiplas moedas diferentes.
		String number = strNumber.replaceAll(",", ".");
		if (isNumerico(number))
			return Double.parseDouble(number);

		return null;
	}

	public static boolean isNumerico(String strNumber) {
		if (strNumber == null)
			return false;
		String number = strNumber.replaceAll(",", ".");
		// Está verfificando se é positivo ou negativo, de 0 a 9 e sua fracionaria
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}

	public static void isNumberValid(String numberOne, String numberTwo) {
		if (!MathUtils.isNumerico(numberOne) || !MathUtils.isNumerico(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value.");
		}
	}

	public static void isNumberValid(String numberOne) {
		if (!MathUtils.isNumerico(numberOne)) {
			throw new UnsupportedMathOperationException("Please set a numeric value.");
		}
	}

}
