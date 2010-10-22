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

import java.io.File;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.lang.reflect.Method;

import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.floggy.org.json.me.JSONStringer;

import org.floggy.synchronization.jme.core.Synchronizable;
import org.floggy.synchronization.jme.core.impl.__Synchronizable;

import net.sourceforge.floggy.persistence.Filter;
import net.sourceforge.floggy.persistence.Persistable;

/**
* DOCUMENT ME!
*
* @author <a href="mailto:thiago.moreira@floggy.org">Thiago Moreira</a>
* @version $Revision$
 */
public abstract class AbstractTest extends FloggyBaseTest {
	/**
	* DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*
	* @throws RuntimeException DOCUMENT ME!
	*/
	public Filter getFilter() {
		return new Filter() {
				public boolean matches(Persistable persistable) {
					Object temp = getValueForSetMethod();
					Object array = null;

					try {
						array = getX(persistable);
					} catch (Exception e) {
						throw new RuntimeException(e.getMessage());
					}

					if (temp != null) {
						if (temp.getClass().isArray()) {
							Class clazz = temp.getClass().getComponentType();

							if (clazz == short.class) {
								return Arrays.equals((short[]) temp, (short[]) array);
							} else if (clazz == boolean.class) {
								return Arrays.equals((boolean[]) temp, (boolean[]) array);
							} else if (clazz == byte.class) {
								return Arrays.equals((byte[]) temp, (byte[]) array);
							} else if (clazz == char.class) {
								return Arrays.equals((char[]) temp, (char[]) array);
							} else if (clazz == double.class) {
								return Arrays.equals((double[]) temp, (double[]) array);
							} else if (clazz == int.class) {
								return Arrays.equals((int[]) temp, (int[]) array);
							} else if (clazz == float.class) {
								return Arrays.equals((float[]) temp, (float[]) array);
							} else if (clazz == long.class) {
								return Arrays.equals((long[]) temp, (long[]) array);
							} else {
								return Arrays.equals((Object[]) temp, (Object[]) array);
							}
						} else {
							return temp.equals(array);
						}
					} else

						return temp == array;
				}
			};
	}

	/**
	* DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*/
	public abstract Object getValueForSetMethod();

	/**
	* DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*/
	public Object getNewValueForSetMethod() {
		return null;
	}

	/**
	* DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*/
	public abstract Synchronizable newInstance();

	/**
	* DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*/
	public String getNameForGetMethod() {
		return "getX";
	}

	/**
	* DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*/
	public String getNameForSetMethod() {
		return "setX";
	}

	/**
	* DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public void testBuildJSONFiles() throws Exception {
		__Synchronizable synchronizable = (__Synchronizable) newInstance();

		JSONStringer stringer = new JSONStringer();

		setX(synchronizable, getValueForSetMethod());

		synchronizable.__send(stringer);

		String className = getClass().getName();
		String dir = getClass().getPackage().getName();
		
		dir = "src/main/resources/" + dir.replace('.', '/');
		
		className = "src/main/resources/" + className.replace('.', '/').concat(".json");

		FileUtils.forceMkdir(new File(dir));
		IOUtils.copy(new StringReader(stringer.toString()), new FileOutputStream(className));
	}

	/**
	* DOCUMENT ME!
	*
	* @param o1 DOCUMENT ME!
	* @param o2 DOCUMENT ME!
	*/
	protected void equals(Object o1, Object o2) {
		if ((o1 != null) && o2.getClass().isArray()) {
			Class clazz = o1.getClass().getComponentType();

			if (clazz == short.class) {
				assertTrue("Deveria ser igual(arrays) !",
					Arrays.equals((short[]) o1, (short[]) o2));
			} else if (clazz == boolean.class) {
				assertTrue("Deveria ser igual(o2s) !",
					Arrays.equals((boolean[]) o1, (boolean[]) o2));
			} else if (clazz == byte.class) {
				assertTrue("Deveria ser igual(arrays) !",
					Arrays.equals((byte[]) o1, (byte[]) o2));
			} else if (clazz == char.class) {
				assertTrue("Deveria ser igual(arrays) !",
					Arrays.equals((char[]) o1, (char[]) o2));
			} else if (clazz == double.class) {
				assertTrue("Deveria ser igual(arrays) !",
					Arrays.equals((double[]) o1, (double[]) o2));
			} else if (clazz == int.class) {
				assertTrue("Deveria ser igual(arrays) !",
					Arrays.equals((int[]) o1, (int[]) o2));
			} else if (clazz == float.class) {
				assertTrue("Deveria ser igual(arrays) !",
					Arrays.equals((float[]) o1, (float[]) o2));
			} else if (clazz == long.class) {
				assertTrue("Deveria ser igual(arrays) !",
					Arrays.equals((long[]) o1, (long[]) o2));
			} else {
				assertTrue("Deveria ser igual(arrays) !",
					Arrays.equals((Object[]) o1, (Object[]) o2));
			}
		} else {
			assertEquals("Deveria ser igual!", o1, o2);
		}
	}

	/**
	* DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*/
	protected abstract Class getParameterType();

	/**
	* DOCUMENT ME!
	*
	* @param object DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	protected Object getX(Object object) throws Exception {
		Method method =
			object.getClass().getMethod(getNameForGetMethod(), new Class[0]);

		return method.invoke(object, new Object[0]);
	}

	/**
	* DOCUMENT ME!
	*
	* @param object DOCUMENT ME!
	* @param param DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	protected void setX(Object object, Object param) throws Exception {
		Method method =
			object.getClass()
			 .getMethod(getNameForSetMethod(), new Class[] { getParameterType() });
		method.invoke(object, new Object[] { param });
	}
}
