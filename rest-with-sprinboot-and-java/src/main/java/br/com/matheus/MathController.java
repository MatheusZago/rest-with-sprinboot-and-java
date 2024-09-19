package br.com.matheus;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.matheus.services.MathServices;

@RestController // RestController add um response body e um controller.
public class MathController {

	private static final AtomicLong counter = new AtomicLong(); // Isso aqui é só pra mockar um id.

	@GetMapping("/sum/{numberOne}/{numberTwo}")
	public Double sum(@PathVariable String numberOne, @PathVariable String numberTwo) throws Exception {

		return MathServices.sum(numberOne, numberTwo);
	}

	@GetMapping("/sub/{numberOne}/{numberTwo}")
	public Double sub(@PathVariable String numberOne, @PathVariable String numberTwo) throws Exception {

		return MathServices.sub(numberOne, numberTwo);
	}

	@GetMapping("/mult/{numberOne}/{numberTwo}")
	public Double mult(@PathVariable String numberOne, @PathVariable String numberTwo) throws Exception {

		return MathServices.mult(numberOne, numberTwo);
	}

	@GetMapping("/div/{numberOne}/{numberTwo}")
	public Double div(@PathVariable String numberOne, @PathVariable String numberTwo) throws Exception {

		return MathServices.div(numberOne, numberTwo);
	}

	@GetMapping("/root/{numberOne}")
	public Double root(@PathVariable String numberOne) throws Exception {

		return MathServices.root(numberOne);
	}

}
