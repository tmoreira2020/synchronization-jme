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

import java.util.Hashtable;

/**
* DOCUMENT ME!
*
* @author <a href="mailto:thiago.moreira@floggy.org">Thiago Moreira</a>
* @version $Revision$
 */
public class SynchronizableMetadataManager {
	public static final int VERSION_0_0_1 = 1;
	public static final int CURRENT_VERSION = VERSION_0_0_1;
	private static Hashtable synchronizableMetadatas;

	/**
	* DOCUMENT ME!
	*
	* @param metadata DOCUMENT ME!
	*/
	public static void addSynchronizableMetadata(SynchronizableMetadata metadata) {
		synchronizableMetadatas.put(metadata.getClassName(), metadata);
	}

	/**
	* DOCUMENT ME!
	*
	* @param metadata DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*/
	public static boolean containsRMSMetadata(SynchronizableMetadata metadata) {
		return synchronizableMetadatas.containsKey(metadata.getClassName());
	}

	/**
	* DOCUMENT ME!
	*
	* @param className DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*/
	public static SynchronizableMetadata getSynchronizableMetadata(
		String className) {
		return (SynchronizableMetadata) synchronizableMetadatas.get(className);
	}

	/**
	* DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public static void init() throws Exception {
		synchronizableMetadatas = new Hashtable();
	}
}
