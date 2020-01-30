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

package com.liferay.portal.kernel.model;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the UserGroupGroupRole service. Represents a row in the &quot;UserGroupGroupRole&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portal.model.impl.UserGroupGroupRoleModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portal.model.impl.UserGroupGroupRoleImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserGroupGroupRole
 * @generated
 */
@ProviderType
public interface UserGroupGroupRoleModel
	extends BaseModel<UserGroupGroupRole>, MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a user group group role model instance should use the {@link UserGroupGroupRole} interface instead.
	 */

	/**
	 * Returns the primary key of this user group group role.
	 *
	 * @return the primary key of this user group group role
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this user group group role.
	 *
	 * @param primaryKey the primary key of this user group group role
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this user group group role.
	 *
	 * @return the mvcc version of this user group group role
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this user group group role.
	 *
	 * @param mvccVersion the mvcc version of this user group group role
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the user group group role ID of this user group group role.
	 *
	 * @return the user group group role ID of this user group group role
	 */
	public long getUserGroupGroupRoleId();

	/**
	 * Sets the user group group role ID of this user group group role.
	 *
	 * @param userGroupGroupRoleId the user group group role ID of this user group group role
	 */
	public void setUserGroupGroupRoleId(long userGroupGroupRoleId);

	/**
	 * Returns the company ID of this user group group role.
	 *
	 * @return the company ID of this user group group role
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this user group group role.
	 *
	 * @param companyId the company ID of this user group group role
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user group ID of this user group group role.
	 *
	 * @return the user group ID of this user group group role
	 */
	public long getUserGroupId();

	/**
	 * Sets the user group ID of this user group group role.
	 *
	 * @param userGroupId the user group ID of this user group group role
	 */
	public void setUserGroupId(long userGroupId);

	/**
	 * Returns the group ID of this user group group role.
	 *
	 * @return the group ID of this user group group role
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this user group group role.
	 *
	 * @param groupId the group ID of this user group group role
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the role ID of this user group group role.
	 *
	 * @return the role ID of this user group group role
	 */
	public long getRoleId();

	/**
	 * Sets the role ID of this user group group role.
	 *
	 * @param roleId the role ID of this user group group role
	 */
	public void setRoleId(long roleId);

}