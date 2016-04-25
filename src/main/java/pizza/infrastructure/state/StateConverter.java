package pizza.infrastructure.state;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class StateConverter implements AttributeConverter<State, String> {

	public String convertToDatabaseColumn(State attribute) {
		return attribute.state;
	}

	public State convertToEntityAttribute(String dbData) {
		return new State(dbData);
	}

}
