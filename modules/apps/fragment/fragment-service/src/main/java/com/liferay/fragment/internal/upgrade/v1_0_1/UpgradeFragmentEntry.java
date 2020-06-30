/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.fragment.internal.upgrade.v1_0_1;

import com.liferay.fragment.internal.upgrade.v1_0_1.util.FragmentEntryLinkTable;
import com.liferay.fragment.internal.upgrade.v1_0_1.util.FragmentEntryTable;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Eudaldo Alonso
 */
public class UpgradeFragmentEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		alter(
			FragmentEntryTable.class, new AlterColumnType("css", "TEXT null"),
			new AlterColumnType("html", "TEXT null"),
			new AlterColumnType("js", "TEXT null"));

		alter(
			FragmentEntryLinkTable.class,
			new AlterColumnType("css", "TEXT null"),
			new AlterColumnType("html", "TEXT null"),
			new AlterColumnType("js", "TEXT null"),
			new AlterColumnType("editableValues", "TEXT null"));
	}

}