/*******************************************************************************
 * Copyright (c) 2013 Markus Alexander Kuppe and others. All rights reserved. 
 * This program and the accompanying materials are made available under the terms 
 * of the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Markus Alexander Kuppe - initial API and implementation
 ******************************************************************************/
package org.eclipse.ecf.examples.greenhouse.internal.consumer;

import java.util.Collection;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.restlet.engine.Engine;
import org.restlet.engine.converter.ConverterHelper;
import org.restlet.service.ConverterService;

public class OSGiConverterService extends ConverterService {

	public OSGiConverterService() {
		final BundleContext bundleContext = FrameworkUtil.getBundle(
				this.getClass()).getBundleContext();
		
		
		try {

			final Collection<ServiceReference<ConverterHelper>> serviceReferences = bundleContext.getServiceReferences(ConverterHelper.class, null);
			if(serviceReferences != null) {
				final List<ConverterHelper> registeredConverters = Engine.getInstance().getRegisteredConverters();
				
				for (ServiceReference<ConverterHelper> serviceReference : serviceReferences) {
					final ConverterHelper service = bundleContext.getService(serviceReference);
					registeredConverters.add(service);
				}

				Engine.getInstance().setRegisteredConverters(registeredConverters);
			}
			
		} catch (InvalidSyntaxException doesNotHappen) {
			doesNotHappen.printStackTrace();
		}
	}
}
