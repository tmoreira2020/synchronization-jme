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
public class DateGenerator extends SourceCodeGenerator {
/**
   * Creates a new DateGenerator object.
   * 
   * @param fieldName
   *            DOCUMENT ME!
   * @param classType
   *            DOCUMENT ME!
   */
	public DateGenerator(String fieldName, CtClass classType) {
		super(fieldName, classType);
	}

	/**
	* DOCUMENT ME!
	*
	* @throws NotFoundException DOCUMENT ME!
	*/
	public void initReceiveCode() throws NotFoundException {
		addReceiveCode("this." + fieldName
			+ "= org.floggy.synchronization.jme.core.impl.JSONSerializationManager.receiveDate(\""
			+ fieldName + "\", jsonObject);");
	}

	/**
	* DOCUMENT ME!
	*
	* @throws NotFoundException DOCUMENT ME!
	*/
	public void initSendCode() throws NotFoundException {
		addSendCode(
			"org.floggy.synchronization.jme.core.impl.JSONSerializationManager.sendDate(\""
			+ fieldName + "\", this." + fieldName + ", stringer);");
	}
}
