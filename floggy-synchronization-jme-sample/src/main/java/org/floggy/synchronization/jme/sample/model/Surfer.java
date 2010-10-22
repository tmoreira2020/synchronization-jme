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
package org.floggy.synchronization.jme.sample.model;

import org.floggy.synchronization.jme.core.Synchronizable;

import net.sourceforge.floggy.persistence.Persistable;

/**
* DOCUMENT ME!
*
* @author <a href="mailto:thiago.moreira@floggy.org">Thiago Moreira</a>
* @version $Revision$
 */
public class Surfer extends Person implements Persistable, Synchronizable {
	private String boardSize;
	private boolean waxedHair;

	/**
	* DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*/
	public String getBoardSize() {
		return boardSize;
	}

	/**
	* DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*/
	public boolean getWaxedHair() {
		return waxedHair;
	}

	/**
	* DOCUMENT ME!
	*
	* @param boardSize DOCUMENT ME!
	*/
	public void setBoardSize(String boardSize) {
		this.boardSize = boardSize;
	}

	/**
	* DOCUMENT ME!
	*
	* @param waxedHair DOCUMENT ME!
	*/
	public void setWaxedHair(boolean waxedHair) {
		this.waxedHair = waxedHair;
	}
}
