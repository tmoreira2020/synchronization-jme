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
public abstract class SourceCodeGenerator {
	/** DOCUMENT ME! */
	protected CtClass fieldType;

	/** DOCUMENT ME! */
	protected String fieldName;
	private StringBuffer receiveCode;
	private StringBuffer sendCode;

/**
   * Creates a new SourceCodeGenerator object.
   *
   * @param fieldName DOCUMENT ME!
   * @param fieldType DOCUMENT ME!
   */
	protected SourceCodeGenerator(String fieldName, CtClass fieldType) {
		this.fieldName = fieldName;
		this.fieldType = fieldType;
		this.receiveCode = new StringBuffer();
		this.sendCode = new StringBuffer();
	}

	/**
	* DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*
	* @throws NotFoundException DOCUMENT ME!
	*/
	public String getReceiveCode() throws NotFoundException {
		if (this.receiveCode.length() == 0) {
			this.initReceiveCode();
		}

		return this.receiveCode.toString();
	}

	/**
	* DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*
	* @throws NotFoundException DOCUMENT ME!
	*/
	public String getSendCode() throws NotFoundException {
		if (this.sendCode.length() == 0) {
			this.initSendCode();
		}

		return this.sendCode.toString();
	}

	/**
	* DOCUMENT ME!
	*
	* @throws NotFoundException DOCUMENT ME!
	*/
	public abstract void initReceiveCode() throws NotFoundException;

	/**
	* DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*
	* @throws NotFoundException DOCUMENT ME!
	*/
	public String initSendArrayCode() throws NotFoundException {
		return "org.floggy.synchronization.jme.core.impl.JSONSerializationManager.toJSON("
		+ fieldName + ", stringer);";
	}

	/**
	* DOCUMENT ME!
	*
	* @throws NotFoundException DOCUMENT ME!
	*/
	public abstract void initSendCode() throws NotFoundException;

	/**
	* DOCUMENT ME!
	*
	* @param part DOCUMENT ME!
	*/
	protected void addLineOfCodeToReceiveOperation(String part) {
		this.receiveCode.append(part);
		this.receiveCode.append('\n');
	}

	/**
	* DOCUMENT ME!
	*
	* @param part DOCUMENT ME!
	*/
	protected void addLineOfCodeToSendOperation(String part) {
		this.sendCode.append(part);
		this.sendCode.append('\n');
	}
}
