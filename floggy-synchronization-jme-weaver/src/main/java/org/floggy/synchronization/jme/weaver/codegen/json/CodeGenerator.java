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

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtNewMethod;
import javassist.Modifier;
import javassist.NotFoundException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.floggy.synchronization.jme.weaver.Configuration;
import org.floggy.synchronization.jme.weaver.Weaver;

import net.sourceforge.floggy.persistence.formatter.CodeFormatter;

/**
* DOCUMENT ME!
*
* @author <a href="mailto:thiago.moreira@floggy.org">Thiago Moreira</a>
* @version $Revision$
 */
public class CodeGenerator {
	private static final Log LOG = LogFactory.getLog(CodeGenerator.class);

	/** DOCUMENT ME! */
	protected ClassPool classPool;

	/** DOCUMENT ME! */
	protected Configuration configuration;

	/** Class to be modified; */
	protected CtClass ctClass;

	/** DOCUMENT ME! */
	protected StringBuffer source;

/**
   * Creates a new code generator for the class.
   * 
   * @param ctClass
   *            Class to be modified.
   * @param configuration
   *            the configuration object
   */
	public CodeGenerator(CtClass ctClass, ClassPool classPool,
		Configuration configuration) {
		this.ctClass = ctClass;
		this.classPool = classPool;
		this.configuration = configuration;
	}

	/**
	* Generate all the necessary source code for this class.
	*
	* @throws NotFoundException
	* @throws CannotCompileException
	*/
	public final void generateCode()
		throws NotFoundException, CannotCompileException {
		if (configuration.isGenerateSource()) {
			source = new StringBuffer();
		}

		this.generateSynchronizableInterface();
		this.generateSendMethod();
		this.generateReceiveMethod();
	}

	/**
	* DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*/
	public String getSource() {
		return source.toString();
	}

	/**
	* DOCUMENT ME!
	*
	* @param buffer DOCUMENT ME!
	*
	* @throws CannotCompileException DOCUMENT ME!
	*/
	protected final void addField(StringBuffer buffer)
		throws CannotCompileException {
		String temp = buffer.toString();

		if (configuration.isGenerateSource()) {
			source.append(CodeFormatter.format(temp));
		}

		try {
			ctClass.addField(CtField.make(temp, ctClass));
		} catch (CannotCompileException ccex) {
			LOG.error("Adding field: \n" + CodeFormatter.format(temp));
			throw ccex;
		}
	}

	/**
	* DOCUMENT ME!
	*
	* @param buffer DOCUMENT ME!
	*
	* @throws CannotCompileException DOCUMENT ME!
	*/
	protected final void addMethod(StringBuffer buffer)
		throws CannotCompileException {
		String temp = buffer.toString();

		if (configuration.isGenerateSource()) {
			source.append(CodeFormatter.format(temp));
		}

		try {
			ctClass.addMethod(CtNewMethod.make(temp, ctClass));
		} catch (CannotCompileException ccex) {
			LOG.error("Adding method: \n" + CodeFormatter.format(temp));
			throw ccex;
		}
	}

	/**
	* DOCUMENT ME!
	*
	* @throws CannotCompileException DOCUMENT ME!
	* @throws NotFoundException DOCUMENT ME!
	*/
	protected void generateReceiveMethod()
		throws CannotCompileException, NotFoundException {
		StringBuffer buffer = new StringBuffer();

		buffer.append(
			"public void __receive(org.floggy.org.json.me.JSONObject jsonObject) throws java.lang.Exception {\n");

		CtField[] fields = ctClass.getFields();

		if ((fields != null) && (fields.length > 0)) {
			SourceCodeGenerator generator;
			CtField field;

			for (int i = 0; i < fields.length; i++) {
				field = fields[i];

				if (ignoreField(field)) {
					continue;
				}

				generator = SourceCodeGeneratorFactory.getSourceCodeGenerator(ctClass,
						field.getName(), field.getType());

				if (generator != null) {
					buffer.append(generator.getReceiveCode());
				}
			}
		}

		buffer.append("}\n");

		addMethod(buffer);
	}

	/**
	* DOCUMENT ME!
	*
	* @throws CannotCompileException DOCUMENT ME!
	* @throws NotFoundException DOCUMENT ME!
	*/
	protected void generateSendMethod()
		throws CannotCompileException, NotFoundException {
		StringBuffer buffer = new StringBuffer();

		buffer.append(
			"public void __send(org.floggy.org.json.me.JSONStringer stringer) throws java.lang.Exception {\n");
		buffer.append("stringer.object();\n");

		CtField[] fields = ctClass.getFields();

		if ((fields != null) && (fields.length > 0)) {
			SourceCodeGenerator generator;
			CtField field;

			for (int i = 0; i < fields.length; i++) {
				field = fields[i];

				if (ignoreField(field)) {
					continue;
				}

				generator = SourceCodeGeneratorFactory.getSourceCodeGenerator(ctClass,
						field.getName(), field.getType());

				if (generator != null) {
					buffer.append(generator.getSendCode());
				}
			}
		}

		buffer.append("stringer.endObject();\n");
		buffer.append("}\n");

		addMethod(buffer);
	}

	/**
	* DOCUMENT ME!
	*
	* @throws NotFoundException
	*/
	protected void generateSynchronizableInterface() throws NotFoundException {
		this.ctClass.addInterface(this.ctClass.getClassPool()
			 .get(Weaver.__SYNCHRONIZABLE_CLASSNAME));
	}

	/**
	* DOCUMENT ME!
	*
	* @param field DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*/
	protected boolean ignoreField(CtField field) {
		boolean ignore = false;

		if (field.getName().equals("__id")) {
			ignore = true;
		}

		int modifier = field.getModifiers();

		if (Modifier.isTransient(modifier) || Modifier.isStatic(modifier)) {
			ignore = true;
		}

		return ignore;
	}
}
