package org.jlib.persistence.hibernate.usertype;

import java.util.Properties;

import static org.jlib.core.message.MessageUtility.message;

public class InvalidUserTypeParametersCountException
extends InvalidUserTypeParametersException {

    private static final long serialVersionUID = 3741554431902453570L;

    public InvalidUserTypeParametersCountException(final Properties parameters, final int expectedParametersCount) {
        super(parameters, message().with("count", parameters.size())
                                   .with("expected", expectedParametersCount));
    }
}
