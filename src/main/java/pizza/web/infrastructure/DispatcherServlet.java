package pizza.web.infrastructure;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class DispatcherServlet extends HttpServlet {
	
	private HandlerMapping handlerMapping;
	
	private ConfigurableApplicationContext repositoryContext;
	
	private ConfigurableApplicationContext appContext;
	
	private ConfigurableApplicationContext webContext;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		
		String contextConfigLocation = this.getServletContext()
				.getInitParameter("contextConfigLocation");
		
		String webContextLocation = 
				this.getInitParameter("contextConfigLocation");
		
		String[] contextConfigs = contextConfigLocation.split(" ");
		
		repositoryContext = new ClassPathXmlApplicationContext(contextConfigs[0]); // parent for appContext
		appContext = new ClassPathXmlApplicationContext(new String[]{contextConfigs[1]}, repositoryContext);
		ConfigurableEnvironment configurableEnvironment = appContext.getEnvironment();
//		configurableEnvironment.setActiveProfiles("prod");
		
		webContext = new ClassPathXmlApplicationContext(new String[] {webContextLocation}, appContext);
		
		handlerMapping = new UrlHandlerMapping();
	}
	
	@Override
    public void service(HttpServletRequest req, HttpServletResponse res) 
    		throws ServletException, IOException {
        
    	String url = req.getRequestURI();
    	System.out.println(url);
    	String controllerName = getControllerName(url);
    	
    	System.out.println(controllerName);
    	HandlerMapping handlerMapping = new UrlHandlerMapping();
    	
    	Controller controller = handlerMapping.getController(controllerName);
    	if (controller != null) {
    		controller.handleRequest(req, res);
    	}
    }
	
	@Override
	public void destroy() {
		super.destroy();
		appContext.close();
		repositoryContext.close();
		webContext.close();
	}

	private String getControllerName(String url) {
		String str = url.substring(url.lastIndexOf("/"));
		return str;
	}
	
}
