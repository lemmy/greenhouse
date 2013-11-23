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

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.restlet.Application;
import org.restlet.service.ConverterService;
import org.restlet.service.Service;

public class OSGiApplication extends Application {
	
	private final BundleContext bundleContext;

	public OSGiApplication() {
		bundleContext = FrameworkUtil.getBundle(this.getClass())
				.getBundleContext();
	}
	
	private Service getService(Class<? extends Service> clazz) {
		final ServiceReference<? extends Service> reference = bundleContext
				.getServiceReference(clazz);
		if (reference != null) {
			return bundleContext.getService(reference);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.restlet.Application#getConverterService()
	 */
	@Override
	public ConverterService getConverterService() {
		final Service service = getService(ConverterService.class);
		return (ConverterService) (service == null ? super
				.getConverterService() : service);
	}
}
