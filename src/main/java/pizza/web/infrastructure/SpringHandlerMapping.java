package pizza.web.infrastructure;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;

import pizza.web.GetPizzaController;

public class SpringHandlerMapping implements HandlerMapping {
	
	private ApplicationContext webContext;
	
	private static Map<String, Controller> mapping = new HashMap<>(); 
	
	private SpringHandlerMapping(ApplicationContext webContext) {
		this.webContext = webContext;
		mapping.put("/hello", webContext.getBean(GetPizzaController.class));
	}

	@Override
	public Controller getController(String url) {
		return mapping.get(url);
	}

}
