/*******************************************************************************
 * Copyright (c) 2013 Markus Alexander Kuppe and others. All rights reserved. 
 * This program and the accompanying materials are made available under the terms 
 * of the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Markus Alexander Kuppe - initial API and implementation
 ******************************************************************************/
package org.eclipse.ecf.examples.greenhouse.api;

import org.restlet.resource.Get;

/*
 * http://wiki.eclipse.org/Machine-to-Machine/M2MIWG/Demonstrator#REST_API
 */
public interface IGreenhouse {
	
	@Get("json")
	Telemetry getTelemetry();

	/**
	 * @param open
	 *            a boolean indicating if the roof should be opened or not.
	 *            Has no effect if no state change occurs.
	 */
	@Get("json")
	void setOpen(boolean open);
}
