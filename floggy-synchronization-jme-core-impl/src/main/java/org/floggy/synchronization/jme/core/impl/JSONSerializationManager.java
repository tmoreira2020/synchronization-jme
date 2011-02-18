/**
 * Copyright (c) 2006-2010 Floggy Open Source Group. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.floggy.synchronization.jme.core.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Stack;
import java.util.TimeZone;
import java.util.Vector;

import org.floggy.org.json.me.JSONException;
import org.floggy.org.json.me.JSONObject;
import org.floggy.org.json.me.JSONStringer;

import org.floggy.synchronization.jme.core.SynchronizationException;

import net.sourceforge.floggy.persistence.Persistable;

/**
* DOCUMENT ME!
*
* @author <a href="mailto:thiago.moreira@floggy.org">Thiago Moreira</a>
* @version $Revision$
 */
public class JSONSerializationManager {
	private JSONSerializationManager() {
	}

	/**
	* DOCUMENT ME!
	*
	* @param name DOCUMENT ME!
	* @param jsonObject DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*
	* @throws JSONException DOCUMENT ME!
	*/
	public static Boolean receiveBoolean(String name, JSONObject jsonObject)
		throws JSONException {

		Object value = jsonObject.opt(name);

		if (value != null) {
			return (((Boolean)value).booleanValue()) ? Boolean.TRUE : Boolean.FALSE;
		}

		return null;

	}

	/**
	* DOCUMENT ME!
	*
	* @param name DOCUMENT ME!
	* @param jsonObject DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*
	* @throws JSONException DOCUMENT ME!
	*/
	public static Byte receiveByte(String name, JSONObject jsonObject)
		throws JSONException {

		Object value = jsonObject.opt(name);

		if (value != null) {
			return (Byte) value;
		}

		return null;
	}

	/**
	* DOCUMENT ME!
	*
	* @param name DOCUMENT ME!
	* @param jsonObject DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*
	* @throws JSONException DOCUMENT ME!
	*/
	public static Calendar receiveCalendar(String name, JSONObject jsonObject)
		throws JSONException {
		Calendar value = null;
		JSONObject calendar = jsonObject.getJSONObject(name);

		if (calendar != null) {
			value = Calendar.getInstance();
			value.setTimeZone(TimeZone.getTimeZone(calendar.getString("timeZone")));
			value.setTime(new Date(calendar.getLong("date")));
		}

		return value;
	}

	/**
	* DOCUMENT ME!
	*
	* @param name DOCUMENT ME!
	* @param jsonObject DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*
	* @throws JSONException DOCUMENT ME!
	*/
	public static Character receiveChar(String name, JSONObject jsonObject)
		throws JSONException {
		int value = jsonObject.getInt(name);

		return new Character((char) value);
	}

	/**
	* DOCUMENT ME!
	*
	* @param name DOCUMENT ME!
	* @param jsonObject DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*
	* @throws JSONException DOCUMENT ME!
	*/
	public static Date receiveDate(String name, JSONObject jsonObject)
		throws JSONException {
		long value = jsonObject.getLong(name);

		return new Date(value);
	}

	/**
	* DOCUMENT ME!
	*
	* @param name DOCUMENT ME!
	* @param jsonObject DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*
	* @throws JSONException DOCUMENT ME!
	*/
	public static Double receiveDouble(String name, JSONObject jsonObject)
		throws JSONException {
		double value = jsonObject.getDouble(name);

		return new Double(value);
	}

	/**
	* DOCUMENT ME!
	*
	* @param name DOCUMENT ME!
	* @param jsonObject DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*
	* @throws JSONException DOCUMENT ME!
	*/
	public static Float receiveFloat(String name, JSONObject jsonObject)
		throws JSONException {
		float value = (float) jsonObject.getDouble(name);

		return new Float(value);
	}

	/**
	* DOCUMENT ME!
	*
	* @param name DOCUMENT ME!
	* @param jsonObject DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*
	* @throws JSONException DOCUMENT ME!
	*/
	public static Hashtable receiveHashtable(String name, JSONObject jsonObject)
		throws JSONException {
		throw new JSONException("Not implemented!");
	}

	/**
	* DOCUMENT ME!
	*
	* @param name DOCUMENT ME!
	* @param jsonObject DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*
	* @throws JSONException DOCUMENT ME!
	*/
	public static int receiveInt(String name, JSONObject jsonObject)
		throws JSONException {
		return jsonObject.getInt(name);
	}

	/**
	* DOCUMENT ME!
	*
	* @param name DOCUMENT ME!
	* @param jsonObject DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*
	* @throws JSONException DOCUMENT ME!
	*/
	public static Integer receiveInteger(String name, JSONObject jsonObject)
		throws JSONException {
		int value = jsonObject.getInt(name);

		return new Integer(value);
	}

	/**
	* DOCUMENT ME!
	*
	* @param name DOCUMENT ME!
	* @param jsonObject DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*
	* @throws JSONException DOCUMENT ME!
	*/
	public static Long receiveLong(String name, JSONObject jsonObject)
		throws JSONException {
		long value = jsonObject.getLong(name);

		return new Long(value);
	}

	/**
	* DOCUMENT ME!
	*
	* @param name DOCUMENT ME!
	* @param jsonObject DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public static final Object receiveObject(String name, JSONObject jsonObject)
		throws Exception {
		Object value = null;

		return value;
	}

	/**
	* DOCUMENT ME!
	*
	* @param name DOCUMENT ME!
	* @param jsonObject DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*
	* @throws JSONException DOCUMENT ME!
	*/
	public static Persistable receivePersistable(String name,
		JSONObject jsonObject) throws JSONException {
		throw new JSONException("Not implemented!");
	}

	/**
	* DOCUMENT ME!
	*
	* @param name DOCUMENT ME!
	* @param jsonObject DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*
	* @throws JSONException DOCUMENT ME!
	*/
	public static Short receiveShort(String name, JSONObject jsonObject)
		throws JSONException {
		throw new JSONException("Not implemented!");
	}

	/**
	* DOCUMENT ME!
	*
	* @param name DOCUMENT ME!
	* @param jsonObject DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*
	* @throws JSONException DOCUMENT ME!
	*/
	public static Stack receiveStack(String name, JSONObject jsonObject)
		throws JSONException {
		throw new JSONException("Not implemented!");
	}

	/**
	* DOCUMENT ME!
	*
	* @param name DOCUMENT ME!
	* @param jsonObject DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*
	* @throws JSONException DOCUMENT ME!
	*/
	public static StringBuffer receiveStringBuffer(String name,
		JSONObject jsonObject) throws JSONException {
		String value = jsonObject.getString(name);

		return new StringBuffer(value);
	}

	/**
	* DOCUMENT ME!
	*
	* @param name DOCUMENT ME!
	* @param jsonObject DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*
	* @throws JSONException DOCUMENT ME!
	*/
	public static TimeZone receiveTimeZone(String name, JSONObject jsonObject)
		throws JSONException {
		String value = jsonObject.getString(name);

		return TimeZone.getTimeZone(value);
	}

	/**
	* DOCUMENT ME!
	*
	* @param name DOCUMENT ME!
	* @param jsonObject DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*
	* @throws JSONException DOCUMENT ME!
	*/
	public static Vector receiveVector(String name, JSONObject jsonObject)
		throws JSONException {
		throw new JSONException("Not implemented!");
	}

	/**
	* DOCUMENT ME!
	*
	* @param name DOCUMENT ME!
	* @param value DOCUMENT ME!
	* @param stringer DOCUMENT ME!
	*
	* @throws JSONException DOCUMENT ME!
	*/
	public static void send(String name, Object value, JSONStringer stringer)
		throws JSONException {
		if (value != null) {
			stringer.key(name).value(value);
		}
	}

	/**
	* DOCUMENT ME!
	*
	* @param name DOCUMENT ME!
	* @param value DOCUMENT ME!
	* @param stringer DOCUMENT ME!
	*
	* @throws JSONException DOCUMENT ME!
	*/
	public static void sendCalendar(String name, Calendar value,
		JSONStringer stringer) throws JSONException {
		if (value != null) {
			stringer.key(name);

			toJSON(value, stringer);
		}
	}

	/**
	* DOCUMENT ME!
	*
	* @param name DOCUMENT ME!
	* @param value DOCUMENT ME!
	* @param stringer DOCUMENT ME!
	*
	* @throws JSONException DOCUMENT ME!
	*/
	public static void sendDate(String name, Date value, JSONStringer stringer)
		throws JSONException {
		if (value != null) {
			stringer.key(name);

			toJSON(value, stringer);
		}
	}

	/**
	* DOCUMENT ME!
	*
	* @param name DOCUMENT ME!
	* @param value DOCUMENT ME!
	* @param stringer DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public static void sendHashtable(String name, Hashtable value,
		JSONStringer stringer) throws Exception {
		if (value != null) {
			stringer.key(name);

			toJSON(value, stringer);
		}
	}

	/**
	* DOCUMENT ME!
	*
	* @param value DOCUMENT ME!
	* @param stringer DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	* @throws SynchronizationException DOCUMENT ME!
	*/
	public static final void sendObject(Object value, JSONStringer stringer)
		throws Exception {
		String className = value.getClass().getName();

		if (value instanceof Calendar) {
			className = "java.util.Calendar";
		} else if (value instanceof TimeZone) {
			className = "java.util.TimeZone";
		}

		stringer.object();

		stringer.key("className").value(className);
		stringer.key("value");

		if (value instanceof Boolean) {
			stringer.value(value);
		} else if (value instanceof Byte) {
			stringer.value(value);
		} else if (value instanceof Character) {
			stringer.value(value);
		} else if (value instanceof Double) {
			stringer.value(value);
		} else if (value instanceof Float) {
			stringer.value(value);
		} else if (value instanceof Hashtable) {
			Hashtable hashtable = (Hashtable) value;
			toJSON(hashtable, stringer);
		} else if (value instanceof Integer) {
			stringer.value(value);
		} else if (value instanceof Long) {
			stringer.value(value);
		} else if (value instanceof Short) {
			stringer.value(value);
		} else if (value instanceof Stack) {
			Stack stack = (Stack) value;
			toJSON(stack, stringer);
		} else if (value instanceof String) {
			stringer.value(value);
		} else if (value instanceof StringBuffer) {
			stringer.value(value);
		} else if (value instanceof Calendar) {
			Calendar calendar = (Calendar) value;
			toJSON(calendar, stringer);
		} else if (value instanceof Date) {
			Date date = (Date) value;
			toJSON(date, stringer);
		} else if (value instanceof TimeZone) {
			TimeZone timeZone = (TimeZone) value;
			toJSON(timeZone, stringer);
		} else if (value instanceof Vector) {
			Vector vector = (Vector) value;
			toJSON(vector, stringer);
		} else {
			throw new SynchronizationException("The class " + className
				+ " doesn't is a persistable class!");
		}

		stringer.endObject();
	}

	/**
	* DOCUMENT ME!
	*
	* @param name DOCUMENT ME!
	* @param value DOCUMENT ME!
	* @param stringer DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public static void sendPersistable(String name, Persistable value,
		JSONStringer stringer) throws Exception {
		if (value != null) {
			stringer.key(name);

			toJSON(value, stringer);
		}
	}

	/**
	* DOCUMENT ME!
	*
	* @param name DOCUMENT ME!
	* @param value DOCUMENT ME!
	* @param stringer DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public static void sendStack(String name, Stack value, JSONStringer stringer)
		throws Exception {
		if (value != null) {
			stringer.key(name);

			toJSON(value, stringer);
		}
	}

	/**
	* DOCUMENT ME!
	*
	* @param name DOCUMENT ME!
	* @param value DOCUMENT ME!
	* @param stringer DOCUMENT ME!
	*
	* @throws JSONException DOCUMENT ME!
	*/
	public static void sendTimeZone(String name, TimeZone value,
		JSONStringer stringer) throws JSONException {
		if (value != null) {
			stringer.key(name);

			toJSON(value, stringer);
		}
	}

	/**
	* DOCUMENT ME!
	*
	* @param name DOCUMENT ME!
	* @param value DOCUMENT ME!
	* @param stringer DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public static void sendVector(String name, Vector value, JSONStringer stringer)
		throws Exception {
		if (value != null) {
			stringer.key(name);

			toJSON(value, stringer);
		}
	}

	/**
	* DOCUMENT ME!
	*
	* @param value DOCUMENT ME!
	* @param stringer DOCUMENT ME!
	*
	* @throws JSONException DOCUMENT ME!
	*/
	public static void toJSON(Calendar value, JSONStringer stringer)
		throws JSONException {
		if (value == null) {
			stringer.value(null);
		} else {
			stringer.object();
			stringer.key("timeZone");
			toJSON(value.getTimeZone(), stringer);
			stringer.key("time").value(value.getTime().getTime());
			stringer.endObject();
		}
	}

	/**
	* DOCUMENT ME!
	*
	* @param value DOCUMENT ME!
	* @param stringer DOCUMENT ME!
	*
	* @throws JSONException DOCUMENT ME!
	*/
	public static void toJSON(Date value, JSONStringer stringer)
		throws JSONException {
		if (value == null) {
			stringer.value(null);
		} else {
			stringer.object();
			stringer.key("time").value(value.getTime());
			stringer.endObject();
		}
	}

	/**
	* DOCUMENT ME!
	*
	* @param value DOCUMENT ME!
	* @param stringer DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public static void toJSON(Hashtable value, JSONStringer stringer)
		throws Exception {
		if (value == null) {
			stringer.value(null);
		} else {
			stringer.array();

			Enumeration keys = value.keys();

			while (keys.hasMoreElements()) {
				Object key = keys.nextElement();

				stringer.object();
				stringer.key("key");

				sendObject(key, stringer);

				stringer.key("value");

				sendObject(value.get(key), stringer);

				stringer.endObject();
			}

			stringer.endArray();
		}
	}

	/**
	* DOCUMENT ME!
	*
	* @param value DOCUMENT ME!
	* @param stringer DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public static void toJSON(Persistable value, JSONStringer stringer)
		throws Exception {
		if (value == null) {
			stringer.value(null);
		} else {
			stringer.object();

			stringer.key("className");
			stringer.value(value.getClass().getName());

			stringer.endObject();
		}
	}

	/**
	* DOCUMENT ME!
	*
	* @param value DOCUMENT ME!
	* @param stringer DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public static void toJSON(Stack value, JSONStringer stringer)
		throws Exception {
		if (value == null) {
			stringer.value(null);
		} else {
			stringer.array();

			Enumeration elements = value.elements();

			while (elements.hasMoreElements()) {
				Object object = elements.nextElement();
				sendObject(object, stringer);
			}

			stringer.endArray();
		}
	}

	/**
	* DOCUMENT ME!
	*
	* @param value DOCUMENT ME!
	* @param stringer DOCUMENT ME!
	*
	* @throws JSONException DOCUMENT ME!
	*/
	public static void toJSON(TimeZone value, JSONStringer stringer)
		throws JSONException {
		if (value == null) {
			stringer.value(null);
		} else {
			stringer.object();
			stringer.key("ID").value(value.getID());
			stringer.endObject();
		}
	}

	/**
	* DOCUMENT ME!
	*
	* @param value DOCUMENT ME!
	* @param stringer DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public static void toJSON(Vector value, JSONStringer stringer)
		throws Exception {
		if (value == null) {
			stringer.value(null);
		} else {
			stringer.array();

			Enumeration elements = value.elements();

			while (elements.hasMoreElements()) {
				Object object = elements.nextElement();
				sendObject(object, stringer);
			}

			stringer.endArray();
		}
	}
}
