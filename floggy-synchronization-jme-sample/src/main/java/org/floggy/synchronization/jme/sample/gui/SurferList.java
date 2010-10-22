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

import org.floggy.synchronization.jme.core.SynchronizationManager;
import org.floggy.synchronization.jme.sample.WCTMIDlet;
import org.floggy.synchronization.jme.sample.model.Surfer;

import net.sourceforge.floggy.persistence.Comparator;
import net.sourceforge.floggy.persistence.FloggyException;
import net.sourceforge.floggy.persistence.ObjectSet;
import net.sourceforge.floggy.persistence.Persistable;
import net.sourceforge.floggy.persistence.PersistableManager;

/**
* DOCUMENT ME!
*
* @author <a href="mailto:thiago.moreira@floggy.org">Thiago Moreira</a>
* @version $Revision$
 */
public class SurferList extends List implements CommandListener {
	/** DOCUMENT ME! */
	protected Command cmdBack;

	/** DOCUMENT ME! */
	protected Command cmdCreate;

	/** DOCUMENT ME! */
	protected Command cmdDelete;

	/** DOCUMENT ME! */
	protected Command cmdEdit;

	/** DOCUMENT ME! */
	protected Command cmdReceive;

	/** DOCUMENT ME! */
	protected Command cmdSearch;

	/** DOCUMENT ME! */
	protected ObjectSet surfers;

/**
   * Creates a new SurferList object.
   */
	public SurferList() {
		super("Surfers list", List.IMPLICIT);

		startData(null);
		startComponents();
	}

/**
   * Creates a new SurferList object.
   *
   * @param surfers DOCUMENT ME!
   */
	public SurferList(ObjectSet surfers) {
		super("Surfers list", List.IMPLICIT);

		;

		startData(surfers);
		startComponents();
	}

	/**
	* DOCUMENT ME!
	*
	* @param cmd DOCUMENT ME!
	* @param dsp DOCUMENT ME!
	*/
	public void commandAction(Command cmd, Displayable dsp) {
		if (cmd == this.cmdBack) {
			MainForm mainForm = new MainForm();
			WCTMIDlet.setCurrent(mainForm);
		} else if (cmd == this.cmdCreate) {
			Surfer surfer = new Surfer();
			WCTMIDlet.setCurrent(new SurferForm(surfer));
		} else if (cmd == this.cmdEdit) {
			if (this.getSelectedIndex() != -1) {
				Surfer surfer = null;

				try {
					surfer = (Surfer) surfers.get(this.getSelectedIndex());
					WCTMIDlet.setCurrent(new SurferForm(surfer));
				} catch (FloggyException e) {
					WCTMIDlet.showException(e);
				}
			}
		} else if (cmd == this.cmdDelete) {
			if (this.getSelectedIndex() != -1) {
				try {
					Surfer surfer = (Surfer) surfers.get(this.getSelectedIndex());
					PersistableManager.getInstance().delete(surfer);
					this.startData(null);
				} catch (FloggyException e) {
					WCTMIDlet.showException(e);
				}
			}
		} else if (cmd == this.cmdSearch) {
			WCTMIDlet.setCurrent(new SurferSearchForm());
		} else if (cmd == this.cmdReceive) {
			SynchronizationManager sm = SynchronizationManager.getInstance();

			try {
				int size = sm.receive(Surfer.class);
				System.out.println(size);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	private void startComponents() {
		this.cmdBack = new Command("Back", Command.BACK, 0);
		this.addCommand(this.cmdBack);

		this.cmdCreate = new Command("Create", Command.ITEM, 1);
		this.addCommand(this.cmdCreate);

		this.cmdEdit = new Command("Edit", Command.ITEM, 2);
		this.addCommand(this.cmdEdit);

		this.cmdDelete = new Command("Delete", Command.ITEM, 3);
		this.addCommand(this.cmdDelete);

		this.cmdReceive = new Command("Receive", Command.ITEM, 4);
		this.addCommand(this.cmdReceive);

		this.cmdSearch = new Command("Search", Command.ITEM, 5);
		this.addCommand(this.cmdSearch);

		this.setCommandListener(this);
	}

	private void startData(ObjectSet surfers) {
		try {
			this.deleteAll();

			if (surfers == null) {
				PersistableManager pm = PersistableManager.getInstance();
				surfers =
					pm.find(Surfer.class, null,
						new Comparator() {
							public int compare(Persistable arg0, Persistable arg1) {
								String s1 = (arg0 == null) ? "" : ((Surfer) arg0).getName();
								String s2 = (arg1 == null) ? "" : ((Surfer) arg1).getName();

								return s1.compareTo(s2);
							}
						});
			}

			this.surfers = surfers;

			for (int i = 0; i < surfers.size(); i++) {
				Surfer surfer = (Surfer) surfers.get(i);
				String type;

				if (surfer.getWaxedHair()) {
					type = "Yes";
				} else {
					type = "No";
				}

				this.append(surfer.getName() + " - " + type, null);
			}
		} catch (FloggyException e) {
			WCTMIDlet.showException(e);
		}
	}
}
