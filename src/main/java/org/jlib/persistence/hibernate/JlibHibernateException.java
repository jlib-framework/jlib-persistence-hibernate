package org.jlib.persistence.hibernate;

import org.jlib.core.message.Message;

import org.hibernate.HibernateException;

public class JlibHibernateException
extends HibernateException {

    private static final long serialVersionUID = 7870050394976364600L;

    public JlibHibernateException(final Message message) {
        super(message.toString());
    }

    public JlibHibernateException(final Throwable cause) {
        super(cause);
    }

    public JlibHibernateException(final Message message, final Throwable cause) {
        super(message.toString(), cause);
    }
}
