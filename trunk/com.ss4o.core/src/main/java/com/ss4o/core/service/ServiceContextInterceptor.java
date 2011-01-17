/**
 * 
 */
package com.ss4o.core.service;

import java.io.Serializable;
import java.sql.SQLException;

import com.ss4o.core.exception.BusinessException;
import com.ss4o.core.exception.SystemException;
import com.ss4o.core.security.User;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;


/**
 * @author kakusuke
 *
 */
public class ServiceContextInterceptor implements MethodInterceptor, Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 3010743816553605427L;

	/* (非 Javadoc)
	 * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
	 */
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		ServiceContext.start(invocation.getThis().getClass());
		Object _return = null;
		try {
			User user = ServiceContext.getUser();

			if (!user.hasAuthentication(invocation.getMethod().getName()))
				throw new BusinessException();

			_return = invocation.proceed();
		} catch (SQLException e) {
			if (ServiceContext.isNestedService()) {
				throw e;
			}
			ServiceContext.appendException(new BusinessException(e));
		} catch (BusinessException e) {
			if (ServiceContext.isNestedService()) {
				throw e;
			}
			ServiceContext.appendException(e);
		} catch (SystemException e) {
			throw e;
		} catch (RuntimeException e) {
			if (ServiceContext.isNestedService()) {
				throw e;
			}
			ServiceContext.appendException(new BusinessException(e));
		} catch (Throwable e) {
			throw new SystemException(e);
		} finally {
			ServiceContext.end();
		}
		return _return;
	}

}
