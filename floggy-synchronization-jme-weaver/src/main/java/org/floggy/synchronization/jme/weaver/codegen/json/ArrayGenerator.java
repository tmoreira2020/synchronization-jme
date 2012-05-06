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
public class ArrayGenerator extends SourceCodeGenerator
	implements AttributeIterableGenerator {
	private CtClass persistableType;
	private String indexForIteration;

/**
   * Creates a new ArrayGenerator object.
   *
   * @param persistableType DOCUMENT ME!
   * @param fieldName DOCUMENT ME!
   * @param classType DOCUMENT ME!
   */
	public ArrayGenerator(CtClass persistableType, String fieldName,
		CtClass classType) {
		super(fieldName, classType);
		this.persistableType = persistableType;
	}

	/**
	* DOCUMENT ME!
	*
	* @throws NotFoundException DOCUMENT ME!
	*/
	public void initReceiveCode() throws NotFoundException {
		SourceCodeGenerator generator;

		addLineOfCodeToReceiveOperation(
			"org.floggy.org.json.me.JSONArray jsonArray = jsonObject.optJSONArray(\""
			+ fieldName + "\");");
		addLineOfCodeToReceiveOperation("if (jsonArray != null) {");
		addLineOfCodeToReceiveOperation("int count = jsonArray.length();");
		addLineOfCodeToReceiveOperation("this." + fieldName + " = new "
			+ fieldType.getComponentType().getName() + "[count];");
		addLineOfCodeToReceiveOperation("for(int " + indexForIteration + " = 0; "
			+ indexForIteration + " < count; " + indexForIteration + "++) {");
		generator = SourceCodeGeneratorFactory.getSourceCodeGenerator(persistableType,
				fieldName + "[" + indexForIteration + "]", fieldType.getComponentType());
		addLineOfCodeToReceiveOperation(generator.getReceiveArrayCode(indexForIteration));
		addLineOfCodeToReceiveOperation("}");
		addLineOfCodeToReceiveOperation("}");
		addLineOfCodeToReceiveOperation("else {");
		addLineOfCodeToReceiveOperation(fieldName + " = null;");
		addLineOfCodeToReceiveOperation("}");
	}

	/**
	* DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*
	* @throws NotFoundException DOCUMENT ME!
	*/
	public String initSendArrayCode() throws NotFoundException {
		return "stringer.value(" + fieldName + ");";
	}

	/**
	* DOCUMENT ME!
	*
	* @throws NotFoundException DOCUMENT ME!
	*/
	public void initSendCode() throws NotFoundException {
		SourceCodeGenerator generator;

		addLineOfCodeToSendOperation("if(" + "this." + fieldName + " != null) {");
		addLineOfCodeToSendOperation("stringer.key(\"" + fieldName + "\");");
		addLineOfCodeToSendOperation("stringer.array();");
		addLineOfCodeToSendOperation("int count = this." + fieldName + ".length;");
		addLineOfCodeToSendOperation("for(int " + indexForIteration + " = 0; " + indexForIteration
			+ " < count; " + indexForIteration + "++) {");
		generator = SourceCodeGeneratorFactory.getSourceCodeGenerator(persistableType,
				fieldName + "[" + indexForIteration + "]", fieldType.getComponentType());
		addLineOfCodeToSendOperation(generator.initSendArrayCode());
		addLineOfCodeToSendOperation("}");
		addLineOfCodeToSendOperation("stringer.endArray();");
		addLineOfCodeToSendOperation("}");
	}

	/**
	* DOCUMENT ME!
	*
	* @param indexForIteration DOCUMENT ME!
	*/
	public void setUpInterableVariable(String indexForIteration) {
		this.indexForIteration = indexForIteration;
	}
}
