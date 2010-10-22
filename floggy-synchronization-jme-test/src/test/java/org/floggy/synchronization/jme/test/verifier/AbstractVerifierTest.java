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
package org.floggy.synchronization.jme.test.verifier;

import junit.framework.TestCase;

/**
* DOCUMENT ME!
*
* @author <a href="mailto:thiago.moreira@floggy.org">Thiago Moreira</a>
* @version $Revision$
 */
public abstract class AbstractVerifierTest extends TestCase {
	/**
	* DOCUMENT ME!
	*/
	public void testDate() {
		evaluate("org.floggy.synchronization.jme.test.FloggyDate", true);
		evaluate("org.floggy.synchronization.jme.test.FloggyDateArray", true);
	}

	/**
	* DOCUMENT ME!
	*/
	public void testFloggyException() {
		evaluate("org.floggy.synchronization.jme.core.SynchronizationException",
			false);
	}

	/**
	* DOCUMENT ME!
	*/
	public void testFloggyInterface() {
		evaluate("org.floggy.synchronization.jme.test.FloggyInterface", false);
	}

	/**
	* DOCUMENT ME!
	*/
	public void testNoneFields() {
		evaluate("org.floggy.synchronization.jme.test.FloggyNoneFields", true);
	}

	/**
	* DOCUMENT ME!
	*/
	public void testPersistable() {
		evaluate("org.floggy.synchronization.jme.test.FloggyPersistable", true);
		evaluate("org.floggy.synchronization.jme.test.FloggyPersistableArray", true);
	}

	/**
	* DOCUMENT ME!
	*/
	public void testPersistableInterface() {
		evaluate("net.sourceforge.floggy.persistence.Persistable", false);
	}

	/**
	* DOCUMENT ME!
	*/
	public void testPerson() {
		evaluate("org.floggy.synchronization.jme.test.Person", true);
		evaluate("org.floggy.synchronization.jme.test.PersonArray", true);
	}

	/**
	* DOCUMENT ME!
	*/
	public void testPrimitiveBoolean() {
		evaluate("org.floggy.synchronization.jme.test.primitive.FloggyBoolean", true);
		evaluate("org.floggy.synchronization.jme.test.primitive.array.FloggyBoolean",
			true);
	}

	/**
	* DOCUMENT ME!
	*/
	public void testPrimitiveByte() {
		evaluate("org.floggy.synchronization.jme.test.primitive.FloggyByte", true);
		evaluate("org.floggy.synchronization.jme.test.primitive.array.FloggyByte",
			true);
	}

	/**
	* DOCUMENT ME!
	*/
	public void testPrimitiveChar() {
		evaluate("org.floggy.synchronization.jme.test.primitive.FloggyChar", true);
		evaluate("org.floggy.synchronization.jme.test.primitive.array.FloggyChar",
			true);
	}

	/**
	* DOCUMENT ME!
	*/
	public void testPrimitiveDouble() {
		evaluate("org.floggy.synchronization.jme.test.primitive.FloggyDouble", true);
		evaluate("org.floggy.synchronization.jme.test.primitive.array.FloggyDouble",
			true);
	}

	/**
	* DOCUMENT ME!
	*/
	public void testPrimitiveFloat() {
		evaluate("org.floggy.synchronization.jme.test.primitive.FloggyFloat", true);
		evaluate("org.floggy.synchronization.jme.test.primitive.array.FloggyFloat",
			true);
	}

	/**
	* DOCUMENT ME!
	*/
	public void testPrimitiveInt() {
		evaluate("org.floggy.synchronization.jme.test.primitive.FloggyInt", true);
		evaluate("org.floggy.synchronization.jme.test.primitive.array.FloggyInt",
			true);
	}

	/**
	* DOCUMENT ME!
	*/
	public void testPrimitiveLong() {
		evaluate("org.floggy.synchronization.jme.test.primitive.FloggyLong", true);
		evaluate("org.floggy.synchronization.jme.test.primitive.array.FloggyLong",
			true);
	}

	/**
	* DOCUMENT ME!
	*/
	public void testPrimitiveShort() {
		evaluate("org.floggy.synchronization.jme.test.primitive.FloggyShort", true);
		evaluate("org.floggy.synchronization.jme.test.primitive.array.FloggyShort",
			true);
	}

	/**
	* DOCUMENT ME!
	*/
	public void testStaticAttribute() {
		evaluate("org.floggy.synchronization.jme.test.FloggyStaticAttribute", true);
		evaluate("org.floggy.synchronization.jme.test.FloggyStaticAttributeArray",
			true);
	}

	/**
	* DOCUMENT ME!
	*/
	public void testString() {
		evaluate("org.floggy.synchronization.jme.test.FloggyString", true);
		evaluate("org.floggy.synchronization.jme.test.FloggyStringArray", true);
	}

	/**
	* DOCUMENT ME!
	*/
	public void testSubNoneFields() {
		evaluate("org.floggy.synchronization.jme.test.FloggySubNoneFields", true);
	}

	/**
	* DOCUMENT ME!
	*/
	public void testSubWithFields() {
		evaluate("org.floggy.synchronization.jme.test.FloggySubWithFields", true);
	}

	/**
	* DOCUMENT ME!
	*/
	public void testTransientAttribute() {
		evaluate("org.floggy.synchronization.jme.test.FloggyTransient", true);
		evaluate("org.floggy.synchronization.jme.test.FloggyTransientArray", true);
	}

	/**
	* DOCUMENT ME!
	*/
	public void testVector() {
		evaluate("org.floggy.synchronization.jme.test.FloggyVector", true);
		evaluate("org.floggy.synchronization.jme.test.FloggyVectorArray", true);
	}

	/**
	* DOCUMENT ME!
	*/
	public void testWrapperBoolean() {
		evaluate("org.floggy.synchronization.jme.test.wrapper.FloggyBoolean", true);
		evaluate("org.floggy.synchronization.jme.test.wrapper.array.FloggyBoolean",
			true);
	}

	/**
	* DOCUMENT ME!
	*/
	public void testWrapperByte() {
		evaluate("org.floggy.synchronization.jme.test.wrapper.FloggyByte", true);
		evaluate("org.floggy.synchronization.jme.test.wrapper.array.FloggyByte",
			true);
	}

	/**
	* DOCUMENT ME!
	*/
	public void testWrapperCharacter() {
		evaluate("org.floggy.synchronization.jme.test.wrapper.FloggyCharacter", true);
		evaluate("org.floggy.synchronization.jme.test.wrapper.array.FloggyCharacter",
			true);
	}

	/**
	* DOCUMENT ME!
	*/
	public void testWrapperDouble() {
		evaluate("org.floggy.synchronization.jme.test.wrapper.FloggyDouble", true);
		evaluate("org.floggy.synchronization.jme.test.wrapper.array.FloggyDouble",
			true);
	}

	/**
	* DOCUMENT ME!
	*/
	public void testWrapperFloat() {
		evaluate("org.floggy.synchronization.jme.test.wrapper.FloggyFloat", true);
		evaluate("org.floggy.synchronization.jme.test.wrapper.array.FloggyFloat",
			true);
	}

	/**
	* DOCUMENT ME!
	*/
	public void testWrapperInteger() {
		evaluate("org.floggy.synchronization.jme.test.wrapper.FloggyInteger", true);
		evaluate("org.floggy.synchronization.jme.test.wrapper.array.FloggyInteger",
			true);
	}

	/**
	* DOCUMENT ME!
	*/
	public void testWrapperLong() {
		evaluate("org.floggy.synchronization.jme.test.wrapper.FloggyLong", true);
		evaluate("org.floggy.synchronization.jme.test.wrapper.array.FloggyLong",
			true);
	}

	/**
	* DOCUMENT ME!
	*/
	public void testWrapperShort() {
		evaluate("org.floggy.synchronization.jme.test.wrapper.FloggyShort", true);
		evaluate("org.floggy.synchronization.jme.test.wrapper.array.FloggyShort",
			true);
	}

	/**
	* DOCUMENT ME!
	*
	* @param className DOCUMENT ME!
	* @param createInstance DOCUMENT ME!
	*/
	protected abstract void evaluate(String className, boolean createInstance);
}
