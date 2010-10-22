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

import java.io.IOException;
import java.io.PrintWriter;

import net.sf.json.JSONObject;

/**
* DOCUMENT ME!
*
* @author <a href="mailto:thiago.moreira@floggy.org">Thiago Moreira</a>
* @version $Revision$
 */
public class PersonExporter implements Exporter {
	/**
	* DOCUMENT ME!
	*
	* @param contentType DOCUMENT ME!
	* @param writer DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*
	* @throws IOException DOCUMENT ME!
	*/
	public int export(String contentType, PrintWriter writer)
		throws IOException {
		Person person = new Person();

		person.setAge(23);
		person.setName("Floggy");

		JSONObject jsonObject = JSONObject.fromObject(person);

		String json = jsonObject.toString();

		writer.append(json);

		return 1;
	}
}
