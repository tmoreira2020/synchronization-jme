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
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

import org.floggy.synchronization.jme.sample.WCTMIDlet;
import org.floggy.synchronization.jme.sample.model.Surfer;

import net.sourceforge.floggy.persistence.FloggyException;
import net.sourceforge.floggy.persistence.IndexFilter;
import net.sourceforge.floggy.persistence.ObjectSet;
import net.sourceforge.floggy.persistence.PersistableManager;

/**
* DOCUMENT ME!
*
* @author <a href="mailto:thiago.moreira@floggy.org">Thiago Moreira</a>
* @version $Revision$
 */
public class SurferSearchForm extends Form implements CommandListener {
	/** DOCUMENT ME! */
	protected Command cmdCancel;

	/** DOCUMENT ME! */
	protected Command cmdOk;

	/** DOCUMENT ME! */
	protected TextField txtSearch;

/**
   * Creates a new PatientSearchForm object.
   */
	public SurferSearchForm() {
		super("Search");
		startComponents();
	}

	/**
	* DOCUMENT ME!
	*
	* @param cmd DOCUMENT ME!
	* @param arg1 DOCUMENT ME!
	*/
	public void commandAction(Command cmd, Displayable arg1) {
		if (cmd == this.cmdOk) {
			PersistableManager pm = PersistableManager.getInstance();

			try {
				IndexFilter filter = new IndexFilter("byName", txtSearch.getString());
				ObjectSet patients = pm.find(Surfer.class, filter, false);

				WCTMIDlet.setCurrent(new SurferList(patients));
			} catch (FloggyException e) {
				WCTMIDlet.showException(e);
			}
		}
	}

	private void startComponents() {
		this.txtSearch = new TextField("Name", null, 20, TextField.ANY);
		this.append(this.txtSearch);

		this.cmdOk = new Command("Ok", Command.OK, 0);
		this.addCommand(this.cmdOk);

		this.cmdCancel = new Command("Cancel", Command.CANCEL, 1);
		this.addCommand(this.cmdCancel);

		this.setCommandListener(this);
	}
}
