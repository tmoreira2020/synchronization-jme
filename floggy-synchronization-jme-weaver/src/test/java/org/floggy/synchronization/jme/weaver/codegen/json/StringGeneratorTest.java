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

import javassist.ClassPool;
import javassist.CtClass;

import junit.framework.TestCase;

/**
* DOCUMENT ME!
*
* @author <a href="mailto:thiago.moreira@floggy.org">Thiago Moreira</a>
* @version $Revision$
 */
public class StringGeneratorTest extends TestCase {
	/**
	* DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public void testSendCodeMethod() throws Exception {
		ClassPool pool = new ClassPool(true);

		CtClass ctClass = pool.get(Floggy.class.getName());

		String fieldName = "name";

		SourceCodeGenerator generator = new StringGenerator(fieldName, ctClass);

		String expected =
			"org.floggy.synchronization.jme.core.impl.JSONSerializationManager.send(\""
			+ fieldName + "\", this." + fieldName + ", stringer);\n";

		assertEquals(expected, generator.getSendCode());
	}
}
