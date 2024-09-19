package br.com.matheus.services;

import br.com.matheus.utils.MathUtils;

public class MathServices {

	public static Double sum(String numberOne, String numberTwo) {
		MathUtils.isNumberValid(numberOne, numberTwo);

		return MathUtils.convetToDouble(numberOne) + MathUtils.convetToDouble(numberTwo);
	}

	public static Double sub(String numberOne, String numberTwo) {
		MathUtils.isNumberValid(numberOne, numberTwo);

		return MathUtils.convetToDouble(numberOne) - MathUtils.convetToDouble(numberTwo);
	}

	public static Double mult(String numberOne, String numberTwo) {
		MathUtils.isNumberValid(numberOne, numberTwo);

		return MathUtils.convetToDouble(numberOne) * MathUtils.convetToDouble(numberTwo);
	}

	public static Double div(String numberOne, String numberTwo) {
		MathUtils.isNumberValid(numberOne, numberTwo);

		return MathUtils.convetToDouble(numberOne) / MathUtils.convetToDouble(numberTwo);
	}

	public static Double root(String number) {
		MathUtils.isNumberValid(number);

		return Math.sqrt(MathUtils.convetToDouble(number));
	}

}
