package pizza.web;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pizza.web.infrastructure.Controller;

public class HelloController implements Controller {

	@Override
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) {
		try (PrintWriter out = response.getWriter()) {
        	out.println("Hello from pizza.web.HelloController");
        } catch(Exception e) {
        	throw new RuntimeException();
        }
	}

}
