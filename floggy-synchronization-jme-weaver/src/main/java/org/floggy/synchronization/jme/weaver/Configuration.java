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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.floggy.synchronization.jme.core.impl.SynchronizableMetadata;

/**
* DOCUMENT ME!
*
* @author <a href="mailto:thiago.moreira@floggy.org">Thiago Moreira</a>
* @version $Revision$
 */
public class Configuration {
	private List synchronizables = new ArrayList();
	private boolean generateSource = false;

	/**
	* DOCUMENT ME!
	*
	* @param metadata DOCUMENT ME!
	*/
	public void addSynchronizableMetadata(SynchronizableMetadata metadata) {
		synchronizables.add(metadata);
	}

	/**
	* DOCUMENT ME!
	*
	* @param className DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*/
	public boolean containsSynchronizable(String className) {
		return getSynchronizableMetadata(className) != null;
	}

	/**
	* DOCUMENT ME!
	*
	* @param className DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*/
	public SynchronizableMetadata getSynchronizableMetadata(String className) {
		for (Iterator iter = synchronizables.iterator(); iter.hasNext();) {
			SynchronizableMetadata metadata = (SynchronizableMetadata) iter.next();

			if (className.equals(metadata.getClassName())) {
				return metadata;
			}
		}

		return null;
	}

	/**
	* DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*/
	public List getSynchronizableMetadatas() {
		return synchronizables;
	}

	/**
	* DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*/
	public boolean isGenerateSource() {
		return generateSource;
	}

	/**
	* DOCUMENT ME!
	*
	* @param generateSource DOCUMENT ME!
	*/
	public void setGenerateSource(boolean generateSource) {
		this.generateSource = generateSource;
	}

	/**
	* DOCUMENT ME!
	*
	* @param synchronizables DOCUMENT ME!
	*/
	public void setSynchronizables(List synchronizables) {
		this.synchronizables = synchronizables;
	}

	/**
	* DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*/
	public String toString() {
		return "Configuration [generateSource=" + generateSource
		+ ", synchronizables=" + synchronizables + "]";
	}
}
