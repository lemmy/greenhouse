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

import java.io.IOException;
import java.lang.reflect.Type;

import org.eclipse.ecf.examples.greenhouse.api.Telemetry;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class TelemetryTypeAdapter extends TypeAdapter<Telemetry> implements JsonDeserializer<Telemetry> {

	@Override
	public void write(JsonWriter out, Telemetry value) throws IOException {
		throw new UnsupportedOperationException("not (yet) implemented");
	}

	@Override
	public Telemetry read(JsonReader in) throws IOException {
	      JsonElement value = Streams.parse(in);
	      if (value.isJsonNull()) {
	        return null;
	      }
	      return deserialize(value, null, null);
	}

	public Telemetry deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		
		final double luminosity = getDoubleValue(json, "greenhouse.data.luminosity", "value");
		final double temperature = getDoubleValue(json, "greenhouse.data.temperature", "value");
		final double humidity = getDoubleValue(json, "greenhouse.data.humidity", "value");
		
		final boolean isOpen = getBooleanValue(json, "greenhouse.data.open", "value");
		
		return new Telemetry(luminosity, temperature, humidity, isOpen);
	}

	private boolean getBooleanValue(JsonElement json, String memberName,
			String key) {
		final JsonElement je = getJsonElement(json, memberName, key);
		return je.getAsBoolean();
	}

	private double getDoubleValue(JsonElement json, String memberName, String key) {
		final JsonElement je = getJsonElement(json, memberName, key);
		return je.getAsDouble();
	}

	private JsonElement getJsonElement(JsonElement json, String memberName,
			String key) {
		final JsonObject jo = json.getAsJsonObject();
		final JsonArray ja = jo.get(memberName).getAsJsonArray();
		// Only interested in most recent value, thus 0
		return ja.get(0).getAsJsonObject().get(key);
	}
}
