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
import java.util.Hashtable;
import java.util.Stack;
import java.util.TimeZone;
import java.util.Vector;

import org.floggy.org.json.me.JSONObject;
import org.floggy.org.json.me.JSONStringer;

import junit.framework.TestCase;

/**
* DOCUMENT ME!
*
* @author <a href="mailto:thiago.moreira@floggy.org">Thiago Moreira</a>
* @version $Revision$
 */
public class JSONSerializationManagerTest extends TestCase {
	/**
	* DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public void testReceiveBooleanNotNull() throws Exception {
		JSONObject jsonObject = new JSONObject();
		String name = "firstName";
		Boolean value = Boolean.TRUE;

		jsonObject.put(name, value);

		Boolean actual = JSONSerializationManager.receiveBoolean(name, jsonObject);

		assertEquals(value, actual);
	}

	/**
	* DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public void testReceiveBooleanNull() throws Exception {
		JSONObject jsonObject = new JSONObject();
		String name = "firstName";
		Boolean value = null;

		jsonObject.put(name, value);

		Boolean actual = JSONSerializationManager.receiveBoolean(name, jsonObject);

		assertNull(actual);
	}

	/**
	* DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public void testSendBooleanNotNull() throws Exception {
		JSONStringer stringer = new JSONStringer();
		String name = "firstName";
		Boolean value = Boolean.TRUE;

		stringer.object();
		JSONSerializationManager.send(name, value, stringer);
		stringer.endObject();

		String expected = "{\"" + name + "\":" + value + "}";

		assertEquals(expected, stringer.toString());
	}

	/**
	* DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public void testSendBooleanNull() throws Exception {
		JSONStringer stringer = new JSONStringer();
		String name = "firstName";
		Boolean value = null;

		stringer.object();
		JSONSerializationManager.send(name, value, stringer);
		stringer.endObject();

		String expected = "{}";

		assertEquals(expected, stringer.toString());
	}

	/**
	* DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public void testSendByteNotNull() throws Exception {
		JSONStringer stringer = new JSONStringer();
		String name = "firstName";
		Byte value = new Byte((byte) 12);

		stringer.object();
		JSONSerializationManager.send(name, value, stringer);
		stringer.endObject();

		String expected = "{\"" + name + "\":" + value + "}";

		assertEquals(expected, stringer.toString());
	}

	/**
	* DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public void testSendByteNull() throws Exception {
		JSONStringer stringer = new JSONStringer();
		String name = "firstName";
		Byte value = null;

		stringer.object();
		JSONSerializationManager.send(name, value, stringer);
		stringer.endObject();

		String expected = "{}";

		assertEquals(expected, stringer.toString());
	}

	/**
	* DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public void testSendCalendarNotNull() throws Exception {
		JSONStringer stringer = new JSONStringer();
		String name = "bornDate";
		Calendar value = Calendar.getInstance();

		value.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
		value.setTimeInMillis(1234567890);

		stringer.object();
		JSONSerializationManager.sendCalendar(name, value, stringer);
		stringer.endObject();

		String expected =
			"{\"bornDate\":{\"timeZone\":{\"ID\":\"America/Sao_Paulo\"},\"time\":1234567890}}";

		assertEquals(expected, stringer.toString());
	}

	/**
	* DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public void testSendCalendarNull() throws Exception {
		JSONStringer stringer = new JSONStringer();
		String name = "bornDate";
		Calendar value = null;

		stringer.object();
		JSONSerializationManager.sendCalendar(name, value, stringer);
		stringer.endObject();

		String expected = "{}";

		assertEquals(expected, stringer.toString());
	}

	/**
	* DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public void testSendCharNotNull() throws Exception {
		JSONStringer stringer = new JSONStringer();
		String name = "firstName";
		Character value = new Character('F');

		stringer.object();
		JSONSerializationManager.send(name, value, stringer);
		stringer.endObject();

		String expected = "{\"" + name + "\":\"" + value + "\"}";

		assertEquals(expected, stringer.toString());
	}

	/**
	* DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public void testSendCharNull() throws Exception {
		JSONStringer stringer = new JSONStringer();
		String name = "firstName";
		Character value = null;

		stringer.object();
		JSONSerializationManager.send(name, value, stringer);
		stringer.endObject();

		String expected = "{}";

		assertEquals(expected, stringer.toString());
	}

	/**
	* DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public void testSendDateNotNull() throws Exception {
		JSONStringer stringer = new JSONStringer();
		String name = "bornDate";
		Date value = new Date();

		stringer.object();
		JSONSerializationManager.sendDate(name, value, stringer);
		stringer.endObject();

		String expected = "{\"" + name + "\":{\"time\":" + value.getTime() + "}}";

		assertEquals(expected, stringer.toString());
	}

	/**
	* DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public void testSendDateNull() throws Exception {
		JSONStringer stringer = new JSONStringer();
		String name = "bornDate";
		Date value = null;

		stringer.object();
		JSONSerializationManager.sendDate(name, value, stringer);
		stringer.endObject();

		String expected = "{}";

		assertEquals(expected, stringer.toString());
	}

	/**
	* DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public void testSendDoubleNotNull() throws Exception {
		JSONStringer stringer = new JSONStringer();
		String name = "firstName";
		Double value = new Double(712348712634.2342);

		stringer.object();
		JSONSerializationManager.send(name, value, stringer);
		stringer.endObject();

		String expected = "{\"" + name + "\":" + value + "}";

		assertEquals(expected, stringer.toString());
	}

	/**
	* DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public void testSendDoubleNull() throws Exception {
		JSONStringer stringer = new JSONStringer();
		String name = "firstName";
		Double value = null;

		stringer.object();
		JSONSerializationManager.send(name, value, stringer);
		stringer.endObject();

		String expected = "{}";

		assertEquals(expected, stringer.toString());
	}

	/**
	* DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public void testSendEmptyHashtable() throws Exception {
		JSONStringer stringer = new JSONStringer();
		String name = "firstName";
		Hashtable value = new Hashtable();

		stringer.object();
		JSONSerializationManager.sendHashtable(name, value, stringer);
		stringer.endObject();

		String expected = "{\"" + name + "\":[]}";

		assertEquals(expected, stringer.toString());
	}

	/**
	* DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public void testSendEmptyStack() throws Exception {
		JSONStringer stringer = new JSONStringer();
		String name = "stack";
		Stack value = new Stack();

		stringer.object();
		JSONSerializationManager.sendStack(name, value, stringer);
		stringer.endObject();

		String expected = "{\"" + name + "\":[]}";

		assertEquals(expected, stringer.toString());
	}

	/**
	* DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public void testSendEmptyVector() throws Exception {
		JSONStringer stringer = new JSONStringer();
		String name = "vector";
		Vector value = new Vector();

		stringer.object();
		JSONSerializationManager.sendVector(name, value, stringer);
		stringer.endObject();

		String expected = "{\"" + name + "\":[]}";

		assertEquals(expected, stringer.toString());
	}

	/**
	* DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public void testSendFloatNotNull() throws Exception {
		JSONStringer stringer = new JSONStringer();
		String name = "firstName";
		Float value = new Float(712348712634.2342);

		stringer.object();
		JSONSerializationManager.send(name, value, stringer);
		stringer.endObject();

		String expected = "{\"" + name + "\":" + value + "}";

		assertEquals(expected, stringer.toString());
	}

	/**
	* DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public void testSendFloatNull() throws Exception {
		JSONStringer stringer = new JSONStringer();
		String name = "firstName";
		Double value = null;

		stringer.object();
		JSONSerializationManager.send(name, value, stringer);
		stringer.endObject();

		String expected = "{}";

		assertEquals(expected, stringer.toString());
	}

	/**
	* DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public void testSendHashtableNotNull() throws Exception {
		JSONStringer stringer = new JSONStringer();
		String name = "firstName";
		Hashtable value = new Hashtable();

		Calendar c1 = Calendar.getInstance();
		c1.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
		c1.setTime(new Date(1234567890));

		Calendar c2 = Calendar.getInstance();
		c2.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
		c2.setTime(new Date(1234567890));

		value.put(Boolean.TRUE, Boolean.TRUE);
		value.put(new Byte((byte) 90), new Byte((byte) 90));
		value.put(new Character('2'), new Character('2'));
		value.put(new Double(23d), new Double(23d));
		value.put(new Float(45f), new Float(45f));
		value.put(new Integer(87), new Integer(87));
		value.put(new Long(89), new Long(89));
		value.put(new Short((short) 78), new Short((short) 78));
		value.put("key", "value");
		value.put(new Date(1234567890), new Date(9876543221L));
		value.put(c1, c2);
		value.put(TimeZone.getTimeZone("America/Sao_Paulo"),
			TimeZone.getTimeZone("America/Sao_Paulo"));

		stringer.object();
		JSONSerializationManager.sendHashtable(name, value, stringer);
		stringer.endObject();

		String expected =
			"{\"firstName\":[{\"key\":{\"className\":\"java.lang.Byte\",\"value\":90},\"value\":{\"className\":\"java.lang.Byte\",\"value\":90}},{\"key\":{\"className\":\"java.lang.Long\",\"value\":89},\"value\":{\"className\":\"java.lang.Long\",\"value\":89}},{\"key\":{\"className\":\"java.lang.Float\",\"value\":45},\"value\":{\"className\":\"java.lang.Float\",\"value\":45}},{\"key\":{\"className\":\"java.util.Date\",\"value\":{\"time\":1234567890}},\"value\":{\"className\":\"java.util.Date\",\"value\":{\"time\":9876543221}}},{\"key\":{\"className\":\"java.lang.Integer\",\"value\":87},\"value\":{\"className\":\"java.lang.Integer\",\"value\":87}},{\"key\":{\"className\":\"java.lang.Double\",\"value\":23},\"value\":{\"className\":\"java.lang.Double\",\"value\":23}},{\"key\":{\"className\":\"java.lang.Boolean\",\"value\":true},\"value\":{\"className\":\"java.lang.Boolean\",\"value\":true}},{\"key\":{\"className\":\"java.util.TimeZone\",\"value\":{\"ID\":\"America/Sao_Paulo\"}},\"value\":{\"className\":\"java.util.TimeZone\",\"value\":{\"ID\":\"America/Sao_Paulo\"}}},{\"key\":{\"className\":\"java.lang.Short\",\"value\":78},\"value\":{\"className\":\"java.lang.Short\",\"value\":78}},{\"key\":{\"className\":\"java.util.Calendar\",\"value\":{\"timeZone\":{\"ID\":\"America/Sao_Paulo\"},\"time\":1234567890}},\"value\":{\"className\":\"java.util.Calendar\",\"value\":{\"timeZone\":{\"ID\":\"America/Sao_Paulo\"},\"time\":1234567890}}},{\"key\":{\"className\":\"java.lang.Character\",\"value\":\"2\"},\"value\":{\"className\":\"java.lang.Character\",\"value\":\"2\"}},{\"key\":{\"className\":\"java.lang.String\",\"value\":\"key\"},\"value\":{\"className\":\"java.lang.String\",\"value\":\"value\"}}]}";

		assertEquals(expected, stringer.toString());
	}

	/**
	* DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public void testSendHashtableNull() throws Exception {
		JSONStringer stringer = new JSONStringer();
		String name = "hastable";
		Hashtable value = null;

		stringer.object();
		JSONSerializationManager.sendHashtable(name, value, stringer);
		stringer.endObject();

		String expected = "{}";

		assertEquals(expected, stringer.toString());
	}

	/**
	* DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public void testSendIntegerNotNull() throws Exception {
		JSONStringer stringer = new JSONStringer();
		String name = "firstName";
		Integer value = new Integer(12);

		stringer.object();
		JSONSerializationManager.send(name, value, stringer);
		stringer.endObject();

		String expected = "{\"" + name + "\":" + value + "}";

		assertEquals(expected, stringer.toString());
	}

	/**
	* DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public void testSendIntegerNull() throws Exception {
		JSONStringer stringer = new JSONStringer();
		String name = "firstName";
		Byte value = null;

		stringer.object();
		JSONSerializationManager.send(name, value, stringer);
		stringer.endObject();

		String expected = "{}";

		assertEquals(expected, stringer.toString());
	}

	/**
	* DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public void testSendLongNotNull() throws Exception {
		JSONStringer stringer = new JSONStringer();
		String name = "firstName";
		Long value = new Long(1234567890);

		stringer.object();
		JSONSerializationManager.send(name, value, stringer);
		stringer.endObject();

		String expected = "{\"" + name + "\":" + value + "}";

		assertEquals(expected, stringer.toString());
	}

	/**
	* DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public void testSendLongNull() throws Exception {
		JSONStringer stringer = new JSONStringer();
		String name = "firstName";
		Long value = null;

		stringer.object();
		JSONSerializationManager.send(name, value, stringer);
		stringer.endObject();

		String expected = "{}";

		assertEquals(expected, stringer.toString());
	}

	/**
	* DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public void testSendShortNotNull() throws Exception {
		JSONStringer stringer = new JSONStringer();
		String name = "firstName";
		Short value = new Short((short) 1212);

		stringer.object();
		JSONSerializationManager.send(name, value, stringer);
		stringer.endObject();

		String expected = "{\"" + name + "\":" + value + "}";

		assertEquals(expected, stringer.toString());
	}

	/**
	* DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public void testSendShortNull() throws Exception {
		JSONStringer stringer = new JSONStringer();
		String name = "firstName";
		Byte value = null;

		stringer.object();
		JSONSerializationManager.send(name, value, stringer);
		stringer.endObject();

		String expected = "{}";

		assertEquals(expected, stringer.toString());
	}

	/**
	* DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public void testSendStackNotNull() throws Exception {
		JSONStringer stringer = new JSONStringer();
		String name = "stack";
		Stack value = new Stack();

		value.push("test");
		value.push(new Long(9));
		value.push(new Date(1287774606417L));
		value.push(Boolean.TRUE);

		stringer.object();
		JSONSerializationManager.sendStack(name, value, stringer);
		stringer.endObject();

		String expected =
			"{\"stack\":[{\"className\":\"java.lang.String\",\"value\":\"test\"},{\"className\":\"java.lang.Long\",\"value\":9},{\"className\":\"java.util.Date\",\"value\":{\"time\":1287774606417}},{\"className\":\"java.lang.Boolean\",\"value\":true}]}";

		assertEquals(expected, stringer.toString());
	}

	/**
	* DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public void testSendStackNull() throws Exception {
		JSONStringer stringer = new JSONStringer();
		String name = "stack";
		Stack value = null;

		stringer.object();
		JSONSerializationManager.sendStack(name, value, stringer);
		stringer.endObject();

		String expected = "{}";

		assertEquals(expected, stringer.toString());
	}

	/**
	* DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public void testSendStringNotNull() throws Exception {
		JSONStringer stringer = new JSONStringer();
		String name = "firstName";
		String value = "Fry";

		stringer.object();
		JSONSerializationManager.send(name, value, stringer);
		stringer.endObject();

		String expected = "{\"" + name + "\":\"" + value + "\"}";

		assertEquals(expected, stringer.toString());
	}

	/**
	* DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public void testSendStringNull() throws Exception {
		JSONStringer stringer = new JSONStringer();
		String name = "firstName";
		String value = null;

		stringer.object();
		JSONSerializationManager.send(name, value, stringer);
		stringer.endObject();

		String expected = "{}";

		assertEquals(expected, stringer.toString());
	}

	/**
	* DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public void testSendVectorNotNull() throws Exception {
		JSONStringer stringer = new JSONStringer();
		String name = "vector";
		Vector value = new Vector();

		value.addElement("test");
		value.addElement(new Long(9));
		value.addElement(new Date(1287774606417L));
		value.addElement(Boolean.TRUE);

		stringer.object();
		JSONSerializationManager.sendVector(name, value, stringer);
		stringer.endObject();

		String expected =
			"{\"vector\":[{\"className\":\"java.lang.String\",\"value\":\"test\"},{\"className\":\"java.lang.Long\",\"value\":9},{\"className\":\"java.util.Date\",\"value\":{\"time\":1287774606417}},{\"className\":\"java.lang.Boolean\",\"value\":true}]}";

		assertEquals(expected, stringer.toString());
	}

	/**
	* DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public void testSendVectorNull() throws Exception {
		JSONStringer stringer = new JSONStringer();
		String name = "vector";
		Vector value = null;

		stringer.object();
		JSONSerializationManager.sendVector(name, value, stringer);
		stringer.endObject();

		String expected = "{}";

		assertEquals(expected, stringer.toString());
	}
}
