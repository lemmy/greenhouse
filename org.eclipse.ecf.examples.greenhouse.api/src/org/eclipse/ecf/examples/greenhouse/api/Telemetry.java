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

public class Telemetry {
	
	private double luminosity;
	private double temperature;
	private double humidity;
	private boolean isOpen;

	public Telemetry(double luminosity, double temperature, double humidity, boolean isOpen) {
		this.luminosity = luminosity;
		this.temperature = temperature;
		this.humidity = humidity;
		this.isOpen = isOpen;
	}

	/**
	 * an integer indicating the luminosity level in the greenhouse
	 */
	public double getLuminosity() {
		return luminosity;
	}

	/**
	 * @return an integer indicating the humidity level in the greenhouse
	 */
	public double getHumidity() {
		return humidity;
	}

	/**
	 * @return an integer indicating the temperature in the greenhouse
	 */
	public double getTemperature() {
		return temperature;
	}

	/**
	 * @return a boolean indicating whether the roof is opened or not
	 */
	public boolean isOpen() {
		return isOpen;
	}
}
