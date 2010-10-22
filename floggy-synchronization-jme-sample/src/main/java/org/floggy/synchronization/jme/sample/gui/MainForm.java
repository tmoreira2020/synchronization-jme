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
package org.floggy.synchronization.jme.sample.gui;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;

import org.floggy.synchronization.jme.sample.WCTMIDlet;

/**
* DOCUMENT ME!
*
* @author <a href="mailto:thiago.moreira@floggy.org">Thiago Moreira</a>
* @version $Revision$
 */
public class MainForm extends List implements CommandListener {
	/** DOCUMENT ME! */
	protected Command cmdExit;

/**
   * Creates a new MainForm object.
   */
	public MainForm() {
		super("WCT", List.IMPLICIT);

		startComponents();
	}

	/**
	* DOCUMENT ME!
	*
	* @param cmd DOCUMENT ME!
	* @param dsp DOCUMENT ME!
	*/
	public void commandAction(Command cmd, Displayable dsp) {
		if (cmd == this.cmdExit) {
			WCTMIDlet.exit();
		} else if (cmd == List.SELECT_COMMAND) {
			switch (this.getSelectedIndex()) {
			case 0:
				WCTMIDlet.setCurrent(new SurferList());

				break;
			}
		}
	}

	/**
	* DOCUMENT ME!
	*/
	protected void startComponents() {
		this.append("Surfers", null);

		this.cmdExit = new Command("Exit", Command.ITEM, 3);
		this.addCommand(this.cmdExit);

		this.setCommandListener(this);
	}
}
