/* *****************************************************************************
 *Copyright (c) 2008-2009 The Bioclipse Team and others.
 *All rights reserved. This program and the accompanying materials
 *are made available under the terms of the Eclipse Public License v1.0
 *which accompanies this distribution, and is available at
 *http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package net.bioclipse.recording;

import net.bioclipse.core.domain.IBioObject;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;

public class WrapInProxyAdvice implements IWrapInProxyAdvice {

    
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object returnValue = invocation.proceed();
        if(  returnValue instanceof IBioObject && 
           !(returnValue instanceof net.sf.cglib.proxy.Factory) ) {
            ProxyFactory pf = new ProxyFactory();
            pf.addAdvice(this);
//            pf.addAdvice(Activator.getDefault().getRecordingAdvice());
            pf.setTarget(returnValue);
            returnValue = pf.getProxy();
        }
        return returnValue;
    }
}
