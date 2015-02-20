package org.jlib.persistence.hibernate.usertype;

import org.jlib.core.message.Message;

import org.jlib.persistence.hibernate.JlibHibernateException;

public class UserTypeConversionException
extends JlibHibernateException {

    private static final long serialVersionUID = 7383877450249912912L;

    public UserTypeConversionException(final Message message) {
        super(message);
    }

    public UserTypeConversionException(final Exception cause) {
        super(cause);
    }

    public UserTypeConversionException(final Message message, final Throwable cause) {
        super(message, cause);
    }
}
