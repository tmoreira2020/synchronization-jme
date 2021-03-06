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
package org.floggy.synchronization.jme.weaver.codegen.json;

import javassist.CtClass;
import javassist.NotFoundException;

/**
* DOCUMENT ME!
*
* @author <a href="mailto:thiago.moreira@floggy.org">Thiago Moreira</a>
* @version $Revision$
 */
public class PrimitiveTypeGenerator extends SourceCodeGenerator {
/**
   * Creates a new PrimitiveTypeGenerator object.
   * 
   * @param fieldName
   *            DOCUMENT ME!
   * @param classType
   *            DOCUMENT ME!
   */
	public PrimitiveTypeGenerator(String fieldName, CtClass classType) {
		super(fieldName, classType);
	}

	/**
	* DOCUMENT ME!
	*
	* @param fieldType DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*
	* @throws NotFoundException DOCUMENT ME!
	*/
	public static String getType(CtClass fieldType) throws NotFoundException {
		String name = fieldType.getName();

		if (name.equals(Boolean.class.getName())
			 || name.equals(boolean.class.getName())) {
			return "Boolean";
		}

		if (name.equals(Byte.class.getName()) || name.equals(byte.class.getName())) {
			return "Byte";
		}

		if (name.equals(Character.class.getName())
			 || name.equals(char.class.getName())) {
			return "Char";
		}

		if (name.equals(Double.class.getName())
			 || name.equals(double.class.getName())) {
			return "Double";
		}

		if (name.equals(Float.class.getName())
			 || name.equals(float.class.getName())) {
			return "Float";
		}

		if (name.equals(Integer.class.getName())
			 || name.equals(int.class.getName())) {
			return "Int";
		}

		if (name.equals(Long.class.getName()) || name.equals(long.class.getName())) {
			return "Long";
		}

		if (name.equals(Short.class.getName())
			 || name.equals(short.class.getName())) {
			return "Short";
		}

		throw new NotFoundException("Type not found: " + name);
	}

	/**
	* DOCUMENT ME!
	*
	* @throws NotFoundException DOCUMENT ME!
	*/
	public void initReceiveCode() throws NotFoundException {
		String name = fieldType.getName();

		if (name.equals(char.class.getName()) || name.equals(byte.class.getName())
			 || name.equals(short.class.getName())
			 || name.equals(float.class.getName())) {
			addLineOfCodeToReceiveOperation("this." + fieldName + " = (" + name
				+ ") jsonObject.getInt(\"" + fieldName + "\");");
		} else {
			addLineOfCodeToReceiveOperation("this." + fieldName + " = jsonObject.get"
				+ PrimitiveTypeGenerator.getType(fieldType) + "(\"" + fieldName
				+ "\");");
		}
	}

	/**
	* DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*
	* @throws NotFoundException DOCUMENT ME!
	*/
	public String initSendArrayCode() throws NotFoundException {
		String name = fieldType.getName();

		if (name.equals(float.class.getName())) {
			return "stringer.value((double)" + fieldName + ");";
		} else {
			return "stringer.value(" + fieldName + ");";
		}
	}

	/**
	* DOCUMENT ME!
	*
	* @throws NotFoundException DOCUMENT ME!
	*/
	public void initSendCode() throws NotFoundException {
		String name = fieldType.getName();

		if (name.equals(float.class.getName())) {
			addLineOfCodeToSendOperation("stringer.key(\"" + fieldName + "\").value((double)"
				+ fieldName + ");");
		} else {
			addLineOfCodeToSendOperation("stringer.key(\"" + fieldName + "\").value(" + fieldName
				+ ");");
		}
	}
}
