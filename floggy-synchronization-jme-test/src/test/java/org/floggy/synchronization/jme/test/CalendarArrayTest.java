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
package org.floggy.synchronization.jme.test;

import java.util.Calendar;

import org.floggy.synchronization.jme.core.Synchronizable;

/**
* DOCUMENT ME!
*
* @author <a href="mailto:thiago.moreira@floggy.org">Thiago Moreira</a>
* @version $Revision$
 */
public class CalendarArrayTest extends AbstractTest {
	/** DOCUMENT ME! */
	protected Calendar[] calendars =
		new Calendar[] { CalendarTest.calendar, null, Calendar.getInstance() };

	/**
	* DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*/
	public Object getValueForSetMethod() {
		return calendars;
	}

	/**
	* DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*/
	public Synchronizable newInstance() {
		return new FloggyCalendarArray();
	}

	/**
	* DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*/
	protected Class getParameterType() {
		return Calendar[].class;
	}
}
