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

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.restlet.ext.gson.GsonConverter;
import org.restlet.ext.gson.GsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.Variant;
import org.restlet.resource.Resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

public class OSGiConverterHelper extends GsonConverter implements TypeAdapterFactory {
	
	private final BundleContext bundleContext;

	public OSGiConverterHelper() {
		bundleContext = FrameworkUtil.getBundle(this.getClass())
				.getBundleContext();
	}

	/* (non-Javadoc)
	 * @see org.restlet.ext.gson.GsonConverter#score(java.lang.Object, org.restlet.representation.Variant, org.restlet.resource.Resource)
	 */
	@Override
	public float score(Object source, Variant target, Resource resource) {
		return super.score(source, target, resource) + 0.01F;
	}

	/* (non-Javadoc)
	 * @see org.restlet.ext.gson.GsonConverter#score(org.restlet.representation.Representation, java.lang.Class, org.restlet.resource.Resource)
	 */
	@Override
	public <T> float score(Representation source, Class<T> target,
			Resource resource) {
		return super.score(source, target, resource) + 0.01F;
	}

	/* (non-Javadoc)
	 * @see org.restlet.ext.gson.GsonConverter#create(org.restlet.representation.Representation, java.lang.Class)
	 */
	@Override
	protected <T> GsonRepresentation<T> create(Representation source,
			Class<T> objectClass) {
		final GsonRepresentation<T> gsonRepresentation = super.create(source, objectClass);
		final GsonBuilder builder = gsonRepresentation.getBuilder();
		builder.registerTypeAdapterFactory(this);
		return gsonRepresentation;
	}

	/* (non-Javadoc)
	 * @see org.restlet.ext.gson.GsonConverter#create(java.lang.Object)
	 */
	@Override
	protected <T> GsonRepresentation<T> create(T source) {
		final GsonRepresentation<T> gsonRepresentation = super.create(source);
		final GsonBuilder builder = gsonRepresentation.getBuilder();
		builder.registerTypeAdapterFactory(this);
		return gsonRepresentation;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
		try {
			String filter = "(Type=" + type.toString() + ")";
			Collection<ServiceReference<TypeAdapter>> references = bundleContext
					.getServiceReferences(TypeAdapter.class, filter);
			if (references != null) {
				for (ServiceReference<TypeAdapter> serviceReference : references) {
					return bundleContext.getService(serviceReference);
				}
			}
		} catch (InvalidSyntaxException doesNotHappen) {
			doesNotHappen.printStackTrace();
		}
		return null;
	}
}
