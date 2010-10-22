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
package org.floggy.synchronization.jme.core;

/**
* DOCUMENT ME!
*
* @author <a href="mailto:thiago.moreira@floggy.org">Thiago Moreira</a>
* @version $Revision$
 */
public abstract class SynchronizationManager {
	/** The single instance of SynchronizationManager. */
	private static SynchronizationManager instance;

	/** DOCUMENT ME! */
	protected String url;

	/**
	* Returns the current instance of SynchronizationManager.
	*
	* @return The current instance of SynchronizationManager.
	*
	* @throws RuntimeException DOCUMENT ME!
	*/
	public static SynchronizationManager getInstance() {
		if (instance == null) {
			try {
				Class pmClass =
					Class.forName(
						"org.floggy.synchronization.jme.core.impl.SynchronizationManagerImpl");
				instance = (SynchronizationManager) pmClass.newInstance();
			} catch (ClassNotFoundException cnfex) {
				throw new RuntimeException(
					"No SynchronizationManager implementation was found. Please check the weaver execution.");
			} catch (RuntimeException rex) {
				throw rex;
			} catch (Exception ex) {
				String message = ex.getMessage();

				if (message == null) {
					message = ex.getClass().getName();
				}

				throw new RuntimeException(message);
			}
		}

		return instance;
	}

	/**
	* DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*/
	public String getUrl() {
		return url;
	}

	/**
	* Check if the object is already synchronized. <br>
	*
	* @param synchronizable Object to be checked the synchronizable state.
	*
	* @return true if the object is synchronized, false otherwise.
	*/
	public abstract boolean isSynced(Synchronizable synchronizable);

	/**
	* Synchronize an object with the remote server.
	*
	* @param synchronizableClass Object to be synchronized.
	*
	* @return The ID of the object.
	*
	* @throws SynchronizationException Exception thrown if an error occurs while
	* 				synchronizing the object.
	*/
	public abstract int receive(Class synchronizableClass)
		throws SynchronizationException;

	/**
	* DOCUMENT ME!
	*
	* @param url DOCUMENT ME!
	*/
	public void setUrl(String url) {
		this.url = url;
	}
}
