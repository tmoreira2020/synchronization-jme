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
package org.floggy.synchronization.jme.test;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.Vector;

import org.floggy.synchronization.jme.core.Synchronizable;

/**
* DOCUMENT ME!
*
* @author <a href="mailto:thiago.moreira@floggy.org">Thiago Moreira</a>
* @version $Revision$
 */
public class VectorTest extends AbstractTest {
	private Vector vector = new Vector();

/**
   * Creates a new VectorTest object.
   */
	public VectorTest() {
		vector.addElement("floggy-framework");
		vector.addElement(new Boolean(true));
		vector.addElement(new Byte((byte) 34));
		vector.addElement(new Character('f'));
		vector.addElement(null);
		vector.addElement(new Double(23.87));
		vector.addElement(new Float(23.87));
		vector.addElement(new Integer(234));
		vector.addElement(new Long(Long.MAX_VALUE));
		vector.addElement(new Short(Short.MIN_VALUE));
		vector.addElement(new Date(12345678));
		vector.addElement(TimeZone.getDefault());

		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(233456567);
		vector.addElement(c);

		Person person = new Person();
		person.setCpf("23321654");
		person.setNome("�çõí");
		vector.addElement(person);
		vector.addElement(null);
	}

	/**
	* DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*/
	public Object getNewValueForSetMethod() {
		return new Vector();
	}

	/**
	* DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*/
	public Object getValueForSetMethod() {
		return vector;
	}

	/**
	* DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*/
	public Synchronizable newInstance() {
		return new FloggyVector();
	}

	/**
	* DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*/
	protected Class getParameterType() {
		return Vector.class;
	}
}
