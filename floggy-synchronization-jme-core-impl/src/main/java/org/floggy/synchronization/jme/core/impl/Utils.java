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

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.floggy.synchronization.jme.core.Synchronizable;
import org.floggy.synchronization.jme.core.SynchronizationException;

/**
* DOCUMENT ME!
*
* @author <a href="mailto:thiago.moreira@floggy.org">Thiago Moreira</a>
* @version $Revision$
 */
public class Utils {
	private static Class __synchronizableClass;

	static {
		try {
			__synchronizableClass = Class.forName(
					"org.floggy.synchronization.jme.core.impl.__Synchronizable");
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	/**
	* DOCUMENT ME!
	*
	* @param synchronizable DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*
	* @throws IllegalArgumentException DOCUMENT ME!
	*/
	public static __Synchronizable checkArgumentAndCast(
		Synchronizable synchronizable) {
		if (synchronizable == null) {
			throw new IllegalArgumentException(
				"The synchronizable object cannot be null!");
		}

		if (synchronizable instanceof __Synchronizable) {
			return (__Synchronizable) synchronizable;
		} else {
			throw new IllegalArgumentException(synchronizable.getClass().getName()
				+ " is not a valid synchronizable class. Check the weaver execution!");
		}
	}

	/**
	* DOCUMENT ME!
	*
	* @param exception DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*/
	public static SynchronizationException handleException(Exception exception) {
		if (exception instanceof RuntimeException) {
			throw ((RuntimeException) exception);
		}

		if (exception instanceof SynchronizationException) {
			return ((SynchronizationException) exception);
		}

		String str = exception.getMessage();

		if (str == null) {
			str = exception.getClass().getName();
		}

		return new SynchronizationException(str, exception);
	}

	/**
	* DOCUMENT ME!
	*
	* @param in DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public static String readInputStream(InputStream in)
		throws Exception {
		Reader reader = new InputStreamReader(in);
		StringBuffer buffer = new StringBuffer();
		char[] temp = new char[512];

		while (reader.read(temp) != -1) {
			buffer.append(temp);
		}

		return buffer.toString();
	}

	/**
	* DOCUMENT ME!
	*
	* @param synchronizableClass DOCUMENT ME!
	*
	* @throws IllegalArgumentException DOCUMENT ME!
	*/
	public static void validateSynchronizableClassArgument(
		Class synchronizableClass) throws IllegalArgumentException {
		if (synchronizableClass == null) {
			throw new IllegalArgumentException(
				"The synchronizable class cannot be null!");
		}

		if (!__synchronizableClass.isAssignableFrom(synchronizableClass)) {
			throw new IllegalArgumentException(synchronizableClass.getName()
				+ " is not a valid synchronizable class. Check the weaver execution!");
		}
	}
}
