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
package org.floggy.synchronization.jme.server;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import org.floggy.synchronization.jme.core.impl.SynchronizableMetadata;
import org.floggy.synchronization.jme.core.impl.SynchronizableMetadataManager;

import org.junit.Assert;
import org.junit.Test;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import net.sf.json.JSONObject;

/**
* DOCUMENT ME!
*
* @author <a href="mailto:thiago.moreira@floggy.org">Thiago Moreira</a>
* @version $Revision$
 */
public class SynchronizationServletTest {
	/**
	* DOCUMENT ME!
	*
	* @param synchronizableClass DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	public byte[] getContent(Class synchronizableClass) throws Exception {
		SynchronizableMetadataManager.init();

		SynchronizableMetadata metadata =
			SynchronizableMetadataManager.getSynchronizableMetadata(synchronizableClass
				 .getName());

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		DataOutputStream dos = new DataOutputStream(baos);

		dos.flush();
		dos.writeUTF(metadata.toJSON());

		return baos.toByteArray();
	}

	/**
	* DOCUMENT ME!
	*/
	@Test
	public void testDoGetServletRequestServletResponseException() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();

		SynchronizationServlet servlet = new SynchronizationServlet();

		try {
			request.setContent(getContent(Person.class));

			servlet.doGet(request, response);
		} catch (Exception e) {
			Assert.assertEquals(e.getCause().getClass(),
				SynchronizationException.class);
		}
	}

	/**
	* DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	@Test
	public void testDoGetServletRequestServletResponseReceive()
		throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();

		SynchronizationServlet servlet = new SynchronizationServlet();

		request.setContent(getContent(Person.class));

		servlet.doGet(request, response);

		JSONObject jsonObject =
			JSONObject.fromObject(response.getContentAsString());

		Person person = (Person) JSONObject.toBean(jsonObject, Person.class);

		Assert.assertEquals(23, person.getAge());
		Assert.assertEquals("Floggy", person.getName());
	}
}
