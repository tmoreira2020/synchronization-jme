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

import java.io.OutputStreamWriter;
import java.io.Writer;

import java.util.Hashtable;

import javax.microedition.io.Connection;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.io.InputConnection;
import javax.microedition.io.StreamConnection;

import org.floggy.org.json.me.JSONObject;
import org.floggy.org.json.me.JSONTokener;

import org.floggy.synchronization.jme.core.Synchronizable;
import org.floggy.synchronization.jme.core.SynchronizationException;
import org.floggy.synchronization.jme.core.SynchronizationManager;

/**
* DOCUMENT ME!
*
* @author <a href="mailto:thiago.moreira@floggy.org">Thiago Moreira</a>
* @version $Revision$
 */
public class SynchronizationManagerImpl extends SynchronizationManager {
	/** DOCUMENT ME! */
	protected Hashtable urls = new Hashtable();

/**
   * Creates a new instance of SynchronizationManager.
   */
	public SynchronizationManagerImpl() throws Exception {
		SynchronizableMetadataManager.init();
	}

	/**
	 * DOCUMENT ME!
	*
	* @param synchronizableClass DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*/
	public String getUrl(Class synchronizableClass) {
		Utils.validateSynchronizableClassArgument(synchronizableClass);

		return (String) urls.get(synchronizableClass);
	}

	/**
	* DOCUMENT ME!
	*
	* @param synchronizable DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*/
	public boolean isSynced(Synchronizable synchronizable) {
		return false;
	}

	/**
	* DOCUMENT ME!
	*
	* @param synchronizableClass DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*
	* @throws SynchronizationException DOCUMENT ME!
	*/
	public int receive(Class synchronizableClass) throws SynchronizationException {
		Utils.validateSynchronizableClassArgument(synchronizableClass);

		String className = synchronizableClass.getName();

		SynchronizableMetadata metadata =
			SynchronizableMetadataManager.getSynchronizableMetadata(className);

		String jsonClassRepresentation = metadata.toJSON();

		try {
			Connection connection = Connector.open(getUrl(synchronizableClass));

			if (connection instanceof HttpConnection) {
				HttpConnection httpConnection = (HttpConnection) connection;

				httpConnection.setRequestMethod(HttpConnection.POST);

				Writer writer =
					new OutputStreamWriter(httpConnection.openOutputStream());

				writer.write(jsonClassRepresentation);
				writer.close();

				int responseCode = httpConnection.getResponseCode();

				if (responseCode == HttpConnection.HTTP_OK) {
					JSONTokener tokener =
						new JSONTokener(Utils.readInputStream(
								httpConnection.openInputStream()));

					while (tokener.more()) {
						Object object = tokener.nextValue();
						System.out.println(object);
					}
				}
			} else if (connection instanceof StreamConnection) {
				StreamConnection streamConnection = (StreamConnection) connection;

				JSONTokener tokener =
					new JSONTokener(Utils.readInputStream(
							streamConnection.openInputStream()));

				while (tokener.more()) {
					Object object = tokener.nextValue();
					System.out.println(object);
				}
			} else if (connection instanceof InputConnection) {
				InputConnection inputConnection = (InputConnection) connection;

				JSONTokener tokener =
					new JSONTokener(Utils.readInputStream(
							inputConnection.openInputStream()));

				while (tokener.more()) {
					JSONObject jsonObject = (JSONObject)tokener.nextValue();
					System.out.println(jsonObject);
					
					__Synchronizable synchronizable = Utils.createInstance(synchronizableClass);
					
					synchronizable.__receive(jsonObject);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw Utils.handleException(e);
		}

		return 0;
	}

	/**
	 * DOCUMENT ME!
	*
	* @param synchronizableClass DOCUMENT ME!
	* @param url DOCUMENT ME!
	*/
	public void setUrl(Class synchronizableClass, String url) {
		Utils.validateSynchronizableClassArgument(synchronizableClass);

		urls.put(synchronizableClass, url);
	}
}
