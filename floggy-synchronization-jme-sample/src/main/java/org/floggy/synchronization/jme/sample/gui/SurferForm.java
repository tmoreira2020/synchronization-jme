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

import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

import org.floggy.synchronization.jme.sample.WCTMIDlet;
import org.floggy.synchronization.jme.sample.model.Surfer;

import net.sourceforge.floggy.persistence.FloggyException;
import net.sourceforge.floggy.persistence.PersistableManager;

/**
* DOCUMENT ME!
*
* @author <a href="mailto:thiago.moreira@floggy.org">Thiago Moreira</a>
* @version $Revision$
 */
public class SurferForm extends Form implements CommandListener {
	/** DOCUMENT ME! */
	protected ChoiceGroup cgWaxedHair;

	/** DOCUMENT ME! */
	protected Command cmdCancel;

	/** DOCUMENT ME! */
	protected Command cmdOk;

	/** DOCUMENT ME! */
	protected DateField dtBornDate;

	/** DOCUMENT ME! */
	protected Surfer surfer;

	/** DOCUMENT ME! */
	protected TextField txtBoardSize;

	/** DOCUMENT ME! */
	protected TextField txtName;

	/** DOCUMENT ME! */
	protected TextField txtPassport;

/**
   * Creates a new PatientForm object.
   *
   * @param surfer DOCUMENT ME!
   */
	public SurferForm(Surfer surfer) {
		super("Surfer");

		this.surfer = surfer;

		startComponents();
	}

	/**
	* DOCUMENT ME!
	*
	* @param cmd DOCUMENT ME!
	* @param dsp DOCUMENT ME!
	*/
	public void commandAction(Command cmd, Displayable dsp) {
		if (cmd == this.cmdOk) {
			PersistableManager pm = PersistableManager.getInstance();

			try {
				this.surfer.setName(this.txtName.getString());
				this.surfer.setPassport(this.txtPassport.getString());
				this.surfer.setBornDate(this.dtBornDate.getDate());
				this.surfer.setBoardSize(this.txtBoardSize.getString());
				this.surfer.setWaxedHair(this.cgWaxedHair.isSelected(0));
				pm.save(this.surfer);
			} catch (FloggyException e) {
				WCTMIDlet.showException(e);
			}
		}

		WCTMIDlet.setCurrent(new SurferList());
	}

	private void startComponents() {
		this.txtName = new TextField("Name", surfer.getName(), 30, TextField.ANY);
		this.append(this.txtName);

		this.txtPassport = new TextField("Passport", surfer.getPassport(), 30,
				TextField.ANY);
		this.append(this.txtPassport);

		this.dtBornDate = new DateField("Born date", DateField.DATE);
		this.dtBornDate.setDate(surfer.getBornDate());
		this.append(this.dtBornDate);

		this.txtBoardSize = new TextField("Board size", surfer.getBoardSize(), 100,
				TextField.ANY);
		this.append(txtBoardSize);

		this.cgWaxedHair = new ChoiceGroup("Waxed hair:", ChoiceGroup.EXCLUSIVE);
		this.cgWaxedHair.append("Yes", null);
		this.cgWaxedHair.append("No", null);
		this.cgWaxedHair.setSelectedIndex(0, surfer.getWaxedHair());
		this.cgWaxedHair.setSelectedIndex(1, !surfer.getWaxedHair());
		this.append(cgWaxedHair);

		this.cmdOk = new Command("Ok", Command.OK, 0);
		this.addCommand(this.cmdOk);

		this.cmdCancel = new Command("Cancel", Command.CANCEL, 1);
		this.addCommand(this.cmdCancel);

		this.setCommandListener(this);
	}
}
