/*******************************************************************************
 * Copyright (c) 2013 Markus Alexander Kuppe and others. All rights reserved. 
 * This program and the accompanying materials are made available under the terms 
 * of the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Markus Alexander Kuppe - initial API and implementation
 ******************************************************************************/
package org.eclipse.ecf.examples.greenhouse.consumer;

import org.eclipse.ecf.examples.greenhouse.api.IGreenhouse;
import org.eclipse.ecf.examples.greenhouse.api.Telemetry;

public class GreenhouseConsumer {

	public void setGreenhouse(IGreenhouse gh) {
		Telemetry telemetry = gh.getTelemetry();

		System.out.println(telemetry.getHumidity());
		System.out.println(telemetry.getLuminosity());
		System.out.println(telemetry.getTemperature());
		
		boolean isOpen = telemetry.isOpen();
		System.out.println(isOpen);
		
		System.out.println("Switch roof state to: " + !isOpen);
		gh.setOpen(!isOpen);
	}
}
