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

/**
* DOCUMENT ME!
*
* @author <a href="mailto:thiago.moreira@floggy.org">Thiago Moreira</a>
* @version $Revision$
 */
public class SynchronizableMetadata {
	public static final int BOOLEAN = 1;
	public static final int BYTE = 2;
	public static final int CALENDAR = 4;
	public static final int CHARACTER = 8;
	public static final int DATE = 16;
	public static final int DOUBLE = 32;
	public static final int FLOAT = 64;
	public static final int HASHTABLE = 128;
	public static final int INT = 256;
	public static final int LONG = 512;
	public static final int SYNCHRONIZABLE = 1024;
	public static final int SHORT = 2048;
	public static final int STACK = 4096;
	public static final int STRING = 8192;
	public static final int STRINGBUFFER = 16384;
	public static final int TIMEZONE = 32768;
	public static final int VECTOR = 65536;
	public static final int ARRAY = 131072;
	public static final int PRIMITIVE = 262144;
	private String className;
	private String[] fieldNames;
	private int[] fieldTypes;

/**
   * Creates a new SynchronizableMetadata object.
   *
   * @param className DOCUMENT ME!
   * @param fieldNames DOCUMENT ME!
   * @param fieldTypes DOCUMENT ME!
   */
	public SynchronizableMetadata(String className, String[] fieldNames,
		int[] fieldTypes) {
		super();
		this.className = className;
		this.fieldNames = fieldNames;
		this.fieldTypes = fieldTypes;
	}

	/**
	* DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*/
	public String getClassName() {
		return className;
	}

	/**
	* DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*/
	public String[] getFieldNames() {
		return fieldNames;
	}

	/**
	* DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*/
	public int[] getFieldTypes() {
		return fieldTypes;
	}

	/**
	* DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*/
	public String toJSON() {
		StringBuffer buffer = new StringBuffer();

		buffer.append("{\"className\": \"" + className + "\", \"fields\":[");

		for (int i = 0; i < fieldTypes.length; i++) {
			buffer.append("{\"");
			buffer.append(fieldNames[i]);
			buffer.append("\":");
			buffer.append(fieldTypes[i]);
			buffer.append("}");

			if ((i + 1) != fieldTypes.length) {
				buffer.append(",");
			}
		}

		buffer.append("]}");

		return buffer.toString();
	}
}
