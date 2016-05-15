package pizza.web.infrastructure;

public interface HandlerMapping {

	Controller getController(String url);

}
