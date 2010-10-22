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

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/**
* DOCUMENT ME!
*
* @author <a href="mailto:thiago.moreira@floggy.org">Thiago Moreira</a>
* @version $Revision$
 */
public class SynchronizationServlet extends HttpServlet {
	/**
	* DOCUMENT ME!
	*
	* @param request DOCUMENT ME!
	* @param response DOCUMENT ME!
	*
	* @throws ServletException DOCUMENT ME!
	* @throws IOException DOCUMENT ME!
	*/
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String accept = request.getHeader("Accept");

		if (accept == null) {
			accept = "text/json";
		}

		try {
			JSONObject beanDescription = getBeanDescription(request);

			Exporter exporter =
				getExporter(beanDescription.getString("className") + "Exporter");

			PrintWriter writer = response.getWriter();

			int size = exporter.export(accept, writer);

			writer.flush();
			writer.close();

			response.setStatus(HttpServletResponse.SC_OK);
		} catch (IOException e) {
			throw e;
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	* DOCUMENT ME!
	*
	* @param request DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	* @throws SynchronizationException DOCUMENT ME!
	*/
	protected JSONObject getBeanDescription(HttpServletRequest request)
		throws Exception {
		InputStream in = request.getInputStream();

		if (in == null) {
			throw new SynchronizationException("You must provide a bean description");
		}

		DataInputStream dis = new DataInputStream(in);

		String jsonString = dis.readUTF();

		JSONObject jsonObject = JSONObject.fromObject(jsonString);

		return jsonObject;
	}

	/**
	* DOCUMENT ME!
	*
	* @param className DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*
	* @throws Exception DOCUMENT ME!
	*/
	protected Exporter getExporter(String className) throws Exception {
		return (Exporter) Class.forName(className).newInstance();
	}
}
