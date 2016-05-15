package pizza.web;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import pizza.repository.PizzaRepository;
import pizza.web.infrastructure.Controller;


public class GetPizzaController implements Controller {
	
	@Autowired
	private PizzaRepository pizzaRepository;

	@Override
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) {
		try (PrintWriter out = response.getWriter()) {
        	out.println();
        } catch(Exception e) {
        	throw new RuntimeException();
        }
	}
	
	

}
