/******************************************************************************
 * Copyright (c) 2002, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.gmf.runtime.diagram.core.commands;

import java.util.Collection;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractModelCommand;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;

/**
 * Command that sets a connections end points.
 * 
 * @author melaasar
 * 
 */
public class SetConnectionEndsCommand
	extends AbstractModelCommand {

	private IAdaptable edgeAdaptor;

	private IAdaptable newSourceAdaptor;

	private IAdaptable newTargetAdaptor;

	/**
	 * constructor
	 * 
	 * @param label
	 *            the command label
	 */
	public SetConnectionEndsCommand(String label) {
		super(label, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gmf.runtime.common.core.command.ICommand#getAffectedObjects()
	 */
	public Collection getAffectedObjects() {
		View view = (View) edgeAdaptor.getAdapter(View.class);
		if (view != null)
			return getWorkspaceFilesFor(view);
		return super.getAffectedObjects();
	}

	/**
	 * gets the edge adaptor.
	 * 
	 * @return the edge adapter
	 */
	public IAdaptable getEdgeAdaptor() {
		return edgeAdaptor;
	}

	/**
	 * gets the new source adaptor.
	 * 
	 * @return new source adaptor.
	 */
	public IAdaptable getNewSourceAdaptor() {
		return newSourceAdaptor;
	}

	/**
	 * gets the new target adaptor.
	 * 
	 * @return the new target adaptor.
	 */
	public IAdaptable getNewTargetAdaptor() {
		return newTargetAdaptor;
	}

	/**
	 * Sets the edge adaptor.
	 * 
	 * @param edgeAdaptor
	 *            the edgeAdaptor to set
	 */
	public void setEdgeAdaptor(IAdaptable edgeAdaptor) {
		this.edgeAdaptor = edgeAdaptor;
	}

	/**
	 * Sets the new source adaptor.
	 * 
	 * @param newSourceAdaptor
	 *            The newSourceAdaptor to set
	 */
	public void setNewSourceAdaptor(IAdaptable newSourceAdaptor) {
		this.newSourceAdaptor = newSourceAdaptor;
	}

	/**
	 * Sets the new target adaptor.
	 * 
	 * @param newTargetAdaptor
	 *            The newTargetAdaptor to set
	 */
	public void setNewTargetAdaptor(IAdaptable newTargetAdaptor) {
		this.newTargetAdaptor = newTargetAdaptor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gmf.runtime.common.core.command.AbstractCommand#doExecute(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected CommandResult doExecute(IProgressMonitor progressMonitor) {
		assert null != edgeAdaptor : "Null child in SetConnectionEndsCommand";//$NON-NLS-1$

		Edge edge = (Edge) getEdgeAdaptor().getAdapter(Edge.class);
		assert null != edge : "Null edge in SetConnectionEndsCommand";//$NON-NLS-1$

		if (getNewSourceAdaptor() != null) {
			View newSourceView = (View) getNewSourceAdaptor().getAdapter(
				View.class);
			edge.setSource(newSourceView);
		}
		if (getNewTargetAdaptor() != null) {
			View newTargetView = (View) getNewTargetAdaptor().getAdapter(
				View.class);
			edge.setTarget(newTargetView);
		}
		return newOKCommandResult();
	}

}