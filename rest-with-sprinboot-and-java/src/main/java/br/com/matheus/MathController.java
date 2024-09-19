package br.com.matheus;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.matheus.exceptions.UnsupportedMathOperationException;

@RestController // RestController add um response body e um controller.
public class MathController {

	private static final AtomicLong counter = new AtomicLong(); // Isso aqui é só pra mockar um id.

	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double sum(@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo) throws Exception {

		if (!isNumerico(numberOne) || !isNumerico(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value.");
		}

		return convetToDouble(numberOne) + convetToDouble(numberTwo);
	}

	@RequestMapping(value = "/sub/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double sub(@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo) throws Exception {

		if (!isNumerico(numberOne) || !isNumerico(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value.");
		}

		return convetToDouble(numberOne) - convetToDouble(numberTwo);
	}

	@RequestMapping(value = "/mult/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double mult(@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo) throws Exception {

		if (!isNumerico(numberOne) || !isNumerico(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value.");
		}

		return convetToDouble(numberOne) * convetToDouble(numberTwo);
	}

	@RequestMapping(value = "/div/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double div(@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo) throws Exception {

		if (!isNumerico(numberOne) || !isNumerico(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value.");
		}

		return convetToDouble(numberOne) / convetToDouble(numberTwo);
	}

	@RequestMapping(value = "/root/{numberOne}", method = RequestMethod.GET)
	public Double root(@PathVariable(value = "numberOne") String numberOne) throws Exception {

		if (!isNumerico(numberOne)) {
			throw new UnsupportedMathOperationException("Please set a numeric value.");
		}

		return Math.sqrt(convetToDouble(numberOne));
	}

	private Double convetToDouble(String strNumber) {
		if (strNumber == null)
			return 0D;
		// Trabalhando com multiplas moedas diferentes.
		String number = strNumber.replaceAll(",", ".");
		if (isNumerico(number))
			return Double.parseDouble(number);

		return null;
	}

	private boolean isNumerico(String strNumber) {
		if (strNumber == null)
			return false;
		String number = strNumber.replaceAll(",", ".");
		// Está verfificando se é positivo ou negativo, de 0 a 9 e sua fracionaria
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}

}
