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
package org.floggy.synchronization.jme.weaver;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import java.net.URL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.Modifier;
import javassist.NotFoundException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.floggy.synchronization.jme.core.impl.SynchronizableMetadata;
import org.floggy.synchronization.jme.core.impl.SynchronizableMetadataManager;
import org.floggy.synchronization.jme.weaver.codegen.json.CodeGenerator;

import net.sourceforge.floggy.persistence.pool.InputPool;
import net.sourceforge.floggy.persistence.pool.OutputPool;
import net.sourceforge.floggy.persistence.pool.PoolFactory;

/**
* DOCUMENT ME!
*
* @author <a href="mailto:thiago.moreira@floggy.org">Thiago Moreira</a>
* @version $Revision$
 */
public class Weaver {
	private static final Log LOG = LogFactory.getLog(Weaver.class);
	public static final String __SYNCHRONIZABLE_CLASSNAME =
		"org.floggy.synchronization.jme.core.impl.__Synchronizable";
	public static final String SYNCHRONIZABLE_CLASSNAME =
		"org.floggy.synchronization.jme.core.Synchronizable";

	/** DOCUMENT ME! */
	protected ClassPool classpathPool;

	/** DOCUMENT ME! */
	protected Configuration configuration = new Configuration();

	/** DOCUMENT ME! */
	protected File configurationFile;

	/** DOCUMENT ME! */
	protected InputPool inputPool;

	/** DOCUMENT ME! */
	protected OutputPool embeddedClassesOutputPool;

	/** DOCUMENT ME! */
	protected OutputPool outputPool;

	/** DOCUMENT ME! */
	protected Set alreadyProcessedMetadatas = new HashSet();

/**
   * Creates a new instance
   * 
   * @param args
   */
	public Weaver() {
		this(new ClassPool());
	}

/**
   * Creates a new instance
   * 
   * @param args
   */
	public Weaver(ClassPool classPool) {
		this.classpathPool = classPool;
	}

	/**
	* DOCUMENT ME!
	*
	* @param ctClass DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*
	* @throws NotFoundException DOCUMENT ME!
	*/
	public SynchronizableMetadata createSynchronizableMetadata(CtClass ctClass)
		throws NotFoundException {
		String className = ctClass.getName();

		CtField[] fields = ctClass.getFields();

		List fieldNames = new ArrayList(fields.length);
		List fieldTypes = new ArrayList(fields.length);

		for (int i = 0; i < fields.length; i++) {
			CtField field = (CtField) fields[i];

			if (ignoreField(field)) {
				continue;
			}

			fieldNames.add(field.getName());

			Integer type = buildFloggyFieldType(field.getType());
			fieldTypes.add(type);
		}

		int[] temp = new int[fieldTypes.size()];

		for (int i = 0; i < fieldTypes.size(); i++) {
			temp[i] = ((Integer) fieldTypes.get(i)).intValue();
		}

		SynchronizableMetadata metadata =
			new SynchronizableMetadata(className,
				(String[]) fieldNames.toArray(new String[fieldNames.size()]), temp);

		return metadata;
	}

	/**
	* DOCUMENT ME!
	*
	* @throws WeaverException DOCUMENT ME!
	*/
	public void execute() throws WeaverException {
		long time = System.currentTimeMillis();
		LOG.info("Floggy Synchronization JME Weaver - "
			+ SynchronizableMetadataManager.CURRENT_VERSION);
		LOG.info("CLDC version: " + ((isCLDC10()) ? "1.0" : "1.1"));

		try {
			URL fileURL =
				getClass()
				 .getResource("/org/floggy/synchronization/jme/core/impl/SynchronizableMetadataManager.class");
			classpathPool.makeClass(fileURL.openStream());

			embeddedUnderlineCoreClasses();

			List list = getClassThatImplementsSynchronizable();

			int classCount = list.size();
			LOG.info("Processing " + classCount + " bytecodes!");

			for (int i = 0; i < classCount; i++) {
				String className = (String) list.get(i);

				CtClass ctClass = this.classpathPool.get(className);

				LOG.info("Processing bytecode " + className + "!");

				CodeGenerator codeGenerator =
					new CodeGenerator(ctClass, classpathPool, configuration);

				codeGenerator.generateCode();

				if (configuration.isGenerateSource()) {
					byte[] source = codeGenerator.getSource().getBytes();
					String fileName = className.replace('.', File.separatorChar) + ".txt";
					outputPool.addResource(new ByteArrayInputStream(source), fileName);
				}

				this.outputPool.addClass(ctClass);

				if (LOG.isDebugEnabled())
					LOG.debug("Bytecode modified.");
			}

			addSynchronizableMetadataManagerClass();

			if (embeddedClassesOutputPool != outputPool) {
				embeddedClassesOutputPool.finish();
			}

			outputPool.finish();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new WeaverException(e.getMessage(), e);
		}

		time = System.currentTimeMillis() - time;
		LOG.info("Time elapsed: " + time + "ms");
	}

	/**
	* Sets the classpath.
	*
	* @param classpath DOCUMENT ME!
	*/
	public void setClasspath(String[] classpath) {
		if ((classpath != null) && (classpath.length > 0)) {
			for (int i = classpath.length - 1; i >= 0; i--) {
				try {
					this.classpathPool.insertClassPath(classpath[i]);
				} catch (NotFoundException e) {
				}
			}
		}
	}

	/**
	* DOCUMENT ME!
	*
	* @param configuration DOCUMENT ME!
	*/
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	/**
	* DOCUMENT ME!
	*
	* @param configurationFile DOCUMENT ME!
	*/
	public void setConfigurationFile(File configurationFile) {
		this.configurationFile = configurationFile;
	}

	/**
	* DOCUMENT ME!
	*
	* @param embeddedClassesOutputFile DOCUMENT ME!
	*/
	public void setEmbeddedClassesOutputPool(File embeddedClassesOutputFile)
		throws net.sourceforge.floggy.persistence.WeaverException {
		embeddedClassesOutputPool = PoolFactory.createOutputPool(embeddedClassesOutputFile);
	}

	/**
	* Sets the input file.
	*
	* @param inputFile DOCUMENT ME!
	*/
	public void setInputFile(File inputFile)
		throws net.sourceforge.floggy.persistence.WeaverException {
		this.inputPool = PoolFactory.createInputPool(inputFile);

		try {
			this.classpathPool.insertClassPath(inputFile.getCanonicalPath());
		} catch (NotFoundException e) {
		} catch (IOException e) {
		}
	}

	/**
	* Sets the output file.
	*
	* @param outputFile
	*/
	public void setOutputFile(File outputFile)
		throws net.sourceforge.floggy.persistence.WeaverException {
		outputPool = PoolFactory.createOutputPool(outputFile);

		if (embeddedClassesOutputPool == null) {
			embeddedClassesOutputPool = outputPool;
		}
	}

	/**
	* DOCUMENT ME!
	*
	* @throws CannotCompileException DOCUMENT ME!
	* @throws IOException DOCUMENT ME!
	* @throws NotFoundException DOCUMENT ME!
	*/
	protected void addSynchronizableMetadataManagerClass()
		throws CannotCompileException, IOException, NotFoundException {
		alreadyProcessedMetadatas.addAll(configuration.getSynchronizableMetadatas());

		Set metadatas = alreadyProcessedMetadatas;
		StringBuffer buffer = new StringBuffer();

		buffer.append("public static void init() throws Exception {\n");
		buffer.append("synchronizableMetadatas = new java.util.Hashtable();\n");

		for (Iterator iterator = metadatas.iterator(); iterator.hasNext();) {
			SynchronizableMetadata metadata =
				(SynchronizableMetadata) iterator.next();
			String className = metadata.getClassName();
			String[] fieldNames = metadata.getFieldNames();
			int[] fieldTypes = metadata.getFieldTypes();

			StringBuffer fieldNamesBuffer = new StringBuffer("new String[");
			StringBuffer fieldTypesBuffer = new StringBuffer("new int[");
			boolean addHeader = true;

			for (int i = 0; i < fieldNames.length; i++) {
				if (addHeader) {
					fieldNamesBuffer.append("]{");
					fieldTypesBuffer.append("]{");
					addHeader = false;
				}

				fieldNamesBuffer.append("\"");
				fieldNamesBuffer.append(fieldNames[i]);
				fieldNamesBuffer.append("\",");

				fieldTypesBuffer.append(fieldTypes[i]);
				fieldTypesBuffer.append(",");
			}

			if (addHeader) {
				fieldNamesBuffer.append("0]");
				fieldTypesBuffer.append("0]");
			} else {
				fieldNamesBuffer.deleteCharAt(fieldNamesBuffer.length() - 1);
				fieldNamesBuffer.append("}");
				fieldTypesBuffer.deleteCharAt(fieldTypesBuffer.length() - 1);
				fieldTypesBuffer.append("}");
			}

			buffer.append("synchronizableMetadatas.put(\"" + className
				+ "\", new org.floggy.synchronization.jme.core.impl.SynchronizableMetadata(\""
				+ className + "\", " + fieldNamesBuffer.toString() + ", "
				+ fieldTypesBuffer.toString() + "));\n");
		}

		buffer.append("}\n");

		CtClass ctClass =
			this.classpathPool.get(
				"org.floggy.synchronization.jme.core.impl.SynchronizableMetadataManager");
		CtMethod[] methods = ctClass.getMethods();

		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getName().equals("init")) {
				ctClass.removeMethod(methods[i]);
			}
		}

		System.out.println(buffer.toString());
		ctClass.addMethod(CtNewMethod.make(buffer.toString(), ctClass));
		embeddedClassesOutputPool.addClass(ctClass);
	}

	/**
	* DOCUMENT ME!
	*
	* @param ctClass DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*
	* @throws NotFoundException DOCUMENT ME!
	*/
	protected List buildClassTree(CtClass ctClass) throws NotFoundException {
		final CtClass synchronizable =
			classpathPool.get(Weaver.SYNCHRONIZABLE_CLASSNAME);
		List list = new ArrayList();
		CtClass superClass = ctClass;
		String superName = ctClass.getName();

		do {
			list.add(superName);
			superClass = superClass.getSuperclass();
			superName = superClass.getName();
		} while (!"java.lang.Object".equals(superName)
			 && superClass.subtypeOf(synchronizable));

		Collections.reverse(list);

		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			String className = (String) iterator.next();

			if (!configuration.containsSynchronizable(className)) {
				CtClass clazz = classpathPool.get(className);
				configuration.addSynchronizableMetadata(createSynchronizableMetadata(
						clazz));
			}
		}

		return list;
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
	protected Integer buildFloggyFieldType(CtClass fieldType)
		throws NotFoundException {
		int floggyFieldType = 0;

		if (fieldType.isArray()) {
			fieldType = fieldType.getComponentType();

			if (fieldType.isPrimitive()) {
				floggyFieldType = SynchronizableMetadata.PRIMITIVE
					 | buildFloggyPrimitiveFieldType(fieldType);
			} else {
				floggyFieldType = buildFloggyObjectFieldType(fieldType);
			}

			floggyFieldType = floggyFieldType | SynchronizableMetadata.ARRAY;
		} else {
			if (fieldType.isPrimitive()) {
				floggyFieldType = SynchronizableMetadata.PRIMITIVE
					 | buildFloggyPrimitiveFieldType(fieldType);
			} else {
				floggyFieldType = buildFloggyObjectFieldType(fieldType);
			}
		}

		if (floggyFieldType == 0) {
			throw new NotFoundException(fieldType.getName());
		}

		return Integer.valueOf(floggyFieldType);
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
	protected int buildFloggyObjectFieldType(CtClass fieldType)
		throws NotFoundException {
		int floggyFieldType = 0;
		String typeName = fieldType.getName();

		if ("java.lang.Boolean".equals(typeName)) {
			floggyFieldType = SynchronizableMetadata.BOOLEAN;
		}

		if ("java.lang.Byte".equals(typeName)) {
			floggyFieldType = SynchronizableMetadata.BYTE;
		}

		if ("java.lang.Character".equals(typeName)) {
			floggyFieldType = SynchronizableMetadata.CHARACTER;
		}

		if ("java.lang.Double".equals(typeName)) {
			floggyFieldType = SynchronizableMetadata.DOUBLE;
		}

		if ("java.lang.Float".equals(typeName)) {
			floggyFieldType = SynchronizableMetadata.FLOAT;
		}

		if ("java.lang.Integer".equals(typeName)) {
			floggyFieldType = SynchronizableMetadata.INT;
		}

		if ("java.lang.Long".equals(typeName)) {
			floggyFieldType = SynchronizableMetadata.LONG;
		}

		if ("java.lang.Short".equals(typeName)) {
			floggyFieldType = SynchronizableMetadata.SHORT;
		}

		if ("java.lang.String".equals(typeName)) {
			floggyFieldType = SynchronizableMetadata.STRING;
		}

		if ("java.util.Calendar".equals(typeName)) {
			floggyFieldType = SynchronizableMetadata.CALENDAR;
		}

		if ("java.util.Date".equals(typeName)) {
			floggyFieldType = SynchronizableMetadata.DATE;
		}

		if ("java.util.Hashtable".equals(typeName)) {
			floggyFieldType = SynchronizableMetadata.HASHTABLE;
		}

		if (fieldType.subtypeOf(fieldType.getClassPool()
				 .get(Weaver.SYNCHRONIZABLE_CLASSNAME))) {
			floggyFieldType = SynchronizableMetadata.SYNCHRONIZABLE;
		}

		if ("java.lang.StringBuffer".equals(typeName)) {
			floggyFieldType = SynchronizableMetadata.STRINGBUFFER;
		}

		if ("java.util.Stack".equals(typeName)) {
			floggyFieldType = SynchronizableMetadata.STACK;
		}

		if ("java.util.TimeZone".equals(typeName)) {
			floggyFieldType = SynchronizableMetadata.TIMEZONE;
		}

		if ("java.util.Vector".equals(typeName)) {
			floggyFieldType = SynchronizableMetadata.VECTOR;
		}

		return floggyFieldType;
	}

	/**
	* DOCUMENT ME!
	*
	* @param fieldType DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*/
	protected int buildFloggyPrimitiveFieldType(CtClass fieldType) {
		int floggyFieldType = 0;

		if (CtClass.booleanType.equals(fieldType)) {
			floggyFieldType = SynchronizableMetadata.BOOLEAN;
		}

		if (CtClass.byteType.equals(fieldType)) {
			floggyFieldType = SynchronizableMetadata.BYTE;
		}

		if (CtClass.charType.equals(fieldType)) {
			floggyFieldType = SynchronizableMetadata.CHARACTER;
		}

		if (CtClass.doubleType.equals(fieldType)) {
			floggyFieldType = SynchronizableMetadata.DOUBLE;
		}

		if (CtClass.floatType.equals(fieldType)) {
			floggyFieldType = SynchronizableMetadata.FLOAT;
		}

		if (CtClass.intType.equals(fieldType)) {
			floggyFieldType = SynchronizableMetadata.INT;
		}

		if (CtClass.longType.equals(fieldType)) {
			floggyFieldType = SynchronizableMetadata.LONG;
		}

		if (CtClass.shortType.equals(fieldType)) {
			floggyFieldType = SynchronizableMetadata.SHORT;
		}

		return floggyFieldType;
	}

	/**
	* DOCUMENT ME!
	*
	* @param className DOCUMENT ME!
	* @param fieldName DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*/
	protected boolean containsField(String className, String fieldName) {
		try {
			CtClass ctClass = classpathPool.get(className);
			ctClass.getField(fieldName);

			return true;
		} catch (NotFoundException e) {
			return false;
		}
	}

	/**
	* DOCUMENT ME!
	*
	* @param fileName DOCUMENT ME!
	*
	* @throws IOException DOCUMENT ME!
	*/
	protected void embeddedClass(String fileName) throws IOException {
		URL fileURL = getClass().getResource(fileName);

		if (fileURL != null) {
			fileName = fileName.replace('/', File.separatorChar);
			embeddedClassesOutputPool.addFile(fileURL, fileName);
			classpathPool.makeClass(fileURL.openStream());
		}
	}

	/**
	* DOCUMENT ME!
	*
	* @throws IOException DOCUMENT ME!
	*/
	protected void embeddedUnderlineCoreClasses() throws IOException {
		embeddedClass("/org/floggy/org/json/me/JSONArray.class");
		embeddedClass("/org/floggy/org/json/me/JSONException.class");
		embeddedClass("/org/floggy/org/json/me/JSONObject.class");
		embeddedClass("/org/floggy/org/json/me/JSONObject$1.class");
		embeddedClass("/org/floggy/org/json/me/JSONObject$Null.class");
		embeddedClass("/org/floggy/org/json/me/JSONString.class");
		embeddedClass("/org/floggy/org/json/me/JSONStringer.class");
		embeddedClass("/org/floggy/org/json/me/JSONTokener.class");
		embeddedClass("/org/floggy/org/json/me/JSONWriter.class");
		embeddedClass("/org/floggy/org/json/me/StringWriter.class");
		embeddedClass(
			"/org/floggy/synchronization/jme/core/impl/JSONSerializationManager.class");
		embeddedClass(
			"/org/floggy/synchronization/jme/core/impl/__Synchronizable.class");
		embeddedClass(
			"/org/floggy/synchronization/jme/core/impl/SynchronizableMetadata.class");
		embeddedClass(
			"/org/floggy/synchronization/jme/core/impl/SynchronizableMetadataManager.class");
		embeddedClass(
			"/org/floggy/synchronization/jme/core/impl/SynchronizationManagerImpl.class");
		embeddedClass("/org/floggy/synchronization/jme/core/impl/Utils.class");
		embeddedClass("/org/json/synchronization/jme/core/impl/Utils.class");
	}

	/**
	* Returns the class name given a file name.
	*
	* @param fileName
	*
	* @return
	*/
	protected String getClassName(String fileName) {
		if (fileName.endsWith(".class")) {
			String className = fileName.replace(File.separatorChar, '.');

			return className.substring(0, className.length() - 6);
		}

		return null;
	}

	/**
	* DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*
	* @throws NotFoundException DOCUMENT ME!
	* @throws IOException DOCUMENT ME!
	*/
	protected List getClassThatImplementsSynchronizable()
		throws NotFoundException, IOException {
		int classCount = this.inputPool.getFileCount();
		LOG.info("Look up for classes that implements Synchronizable!");

		List list = new LinkedList();
		final CtClass synchronizable =
			classpathPool.get(Weaver.SYNCHRONIZABLE_CLASSNAME);
		final CtClass __synchronizable =
			classpathPool.get(Weaver.__SYNCHRONIZABLE_CLASSNAME);

		for (int i = 0; i < classCount; i++) {
			String fileName = this.inputPool.getFileName(i);
			String className = getClassName(fileName);

			if (className == null) {
				this.outputPool.addFile(inputPool.getFileURL(i), fileName);

				continue;
			}

			CtClass ctClass = this.classpathPool.get(className);

			if (ctClass.subtypeOf(synchronizable)
				 && !ctClass.subtypeOf(__synchronizable) && !ctClass.isInterface()) {
				List tree = buildClassTree(ctClass);

				for (int j = 0; j < tree.size(); j++) {
					Object object = tree.get(j);

					if (!list.contains(object)) {
						list.add(object);
					}
				}
			} else {
				LOG.debug("Bytecode NOT modified.");

				if (ctClass.subtypeOf(__synchronizable)
					 && !ctClass.equals(__synchronizable)) {
					List temp = buildClassTree(ctClass);
					Iterator iterator = temp.iterator();

					while (iterator.hasNext()) {
						className = (String) iterator.next();

						SynchronizableMetadata metadata =
							configuration.getSynchronizableMetadata(className);
						alreadyProcessedMetadatas.add(metadata);
					}
				}

				this.outputPool.addFile(inputPool.getFileURL(i), fileName);
			}
		}

		return list;
	}

	/**
	* DOCUMENT ME!
	*
	* @param field DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*/
	protected boolean ignoreField(CtField field) {
		int modifier = field.getModifiers();

		return field.getName().equals("__id") || Modifier.isTransient(modifier)
		 || Modifier.isStatic(modifier);
	}

	/**
	* DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*/
	protected boolean isCLDC10() {
		try {
			CtClass ctClass = classpathPool.get("java.io.DataInput");
			ctClass.getMethod("readFloat", "()F");
		} catch (NotFoundException nfex) {
			return true;
		}

		return false;
	}
}
