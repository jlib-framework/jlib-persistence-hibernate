package org.jlib.persistence.hibernate.usertype;

import java.util.Properties;

import static org.jlib.message.MessageUtility.message;

public class InvalidUserTypeParameterValueException
extends InvalidUserTypeParametersException {

    private static final long serialVersionUID = - 8900643107183979776L;

    public InvalidUserTypeParameterValueException(final Properties parameters, final String parameterName,
                                                  final Exception cause) {
        super(parameters, message().with("name", parameterName)
                                   .with("value", parameters.getProperty(parameterName)), cause);
    }
}
