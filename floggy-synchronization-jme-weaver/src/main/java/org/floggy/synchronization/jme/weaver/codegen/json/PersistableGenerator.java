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
import javassist.Modifier;
import javassist.NotFoundException;

/**
* DOCUMENT ME!
*
* @author <a href="mailto:thiago.moreira@floggy.org">Thiago Moreira</a>
* @version $Revision$
 */
public class PersistableGenerator extends SourceCodeGenerator {
/**
   * Creates a new SynchronizableGenerator object.
   *
   * @param persistableType DOCUMENT ME!
   * @param fieldName DOCUMENT ME!
   * @param fieldType DOCUMENT ME!
   */
	public PersistableGenerator(CtClass persistableType, String fieldName,
		CtClass fieldType) {
		super(fieldName, fieldType);
	}

	/**
	* DOCUMENT ME!
	*
	* @throws NotFoundException DOCUMENT ME!
	*/
	public void initReceiveCode() throws NotFoundException {
		if (Modifier.isAbstract(fieldType.getModifiers())) {
			addReceiveCode("this." + fieldName + "= (" + fieldType.getName()
				+ ")org.floggy.synchronization.jme.core.impl.JSONSerializationManager.receivePersistable(\""
				+ fieldName + "\", jsonObject);");
		} else {
			addReceiveCode("this." + fieldName + "= (" + fieldType.getName()
				+ ")org.floggy.synchronization.jme.core.impl.JSONSerializationManager.receivePersistable(\""
				+ fieldName + "\", jsonObject);");
		}
	}

	/**
	* DOCUMENT ME!
	*
	* @throws NotFoundException DOCUMENT ME!
	*/
	public void initSendCode() throws NotFoundException {
		addSendCode(
			"org.floggy.synchronization.jme.core.impl.JSONSerializationManager.sendPersistable(\""
			+ fieldName + "\", this." + fieldName + ", stringer);");
	}
}
