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

package com.liferay.portal.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.PortletItem;
import com.liferay.portal.kernel.model.PortletItemModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the PortletItem service. Represents a row in the &quot;PortletItem&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>PortletItemModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link PortletItemImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PortletItemImpl
 * @generated
 */
public class PortletItemModelImpl
	extends BaseModelImpl<PortletItem> implements PortletItemModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a portlet item model instance should use the <code>PortletItem</code> interface instead.
	 */
	public static final String TABLE_NAME = "PortletItem";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"portletItemId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"name", Types.VARCHAR}, {"portletId", Types.VARCHAR},
		{"classNameId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("portletItemId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("portletId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table PortletItem (mvccVersion LONG default 0 not null,portletItemId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,name VARCHAR(75) null,portletId VARCHAR(200) null,classNameId LONG)";

	public static final String TABLE_SQL_DROP = "drop table PortletItem";

	public static final String ORDER_BY_JPQL =
		" ORDER BY portletItem.portletItemId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY PortletItem.portletItemId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.entity.cache.enabled.com.liferay.portal.kernel.model.PortletItem"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.finder.cache.enabled.com.liferay.portal.kernel.model.PortletItem"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.column.bitmask.enabled.com.liferay.portal.kernel.model.PortletItem"),
		true);

	public static final long CLASSNAMEID_COLUMN_BITMASK = 1L;

	public static final long GROUPID_COLUMN_BITMASK = 2L;

	public static final long NAME_COLUMN_BITMASK = 4L;

	public static final long PORTLETID_COLUMN_BITMASK = 8L;

	public static final long PORTLETITEMID_COLUMN_BITMASK = 16L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.portal.kernel.model.PortletItem"));

	public PortletItemModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _portletItemId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setPortletItemId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _portletItemId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return PortletItem.class;
	}

	@Override
	public String getModelClassName() {
		return PortletItem.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<PortletItem, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<PortletItem, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<PortletItem, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((PortletItem)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<PortletItem, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<PortletItem, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(PortletItem)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<PortletItem, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<PortletItem, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, PortletItem>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			PortletItem.class.getClassLoader(), PortletItem.class,
			ModelWrapper.class);

		try {
			Constructor<PortletItem> constructor =
				(Constructor<PortletItem>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException
							reflectiveOperationException) {

					throw new InternalError(reflectiveOperationException);
				}
			};
		}
		catch (NoSuchMethodException noSuchMethodException) {
			throw new InternalError(noSuchMethodException);
		}
	}

	private static final Map<String, Function<PortletItem, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<PortletItem, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<PortletItem, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<PortletItem, Object>>();
		Map<String, BiConsumer<PortletItem, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<PortletItem, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion",
			new Function<PortletItem, Object>() {

				@Override
				public Object apply(PortletItem portletItem) {
					return portletItem.getMvccVersion();
				}

			});
		attributeSetterBiConsumers.put(
			"mvccVersion",
			new BiConsumer<PortletItem, Object>() {

				@Override
				public void accept(
					PortletItem portletItem, Object mvccVersionObject) {

					portletItem.setMvccVersion((Long)mvccVersionObject);
				}

			});
		attributeGetterFunctions.put(
			"portletItemId",
			new Function<PortletItem, Object>() {

				@Override
				public Object apply(PortletItem portletItem) {
					return portletItem.getPortletItemId();
				}

			});
		attributeSetterBiConsumers.put(
			"portletItemId",
			new BiConsumer<PortletItem, Object>() {

				@Override
				public void accept(
					PortletItem portletItem, Object portletItemIdObject) {

					portletItem.setPortletItemId((Long)portletItemIdObject);
				}

			});
		attributeGetterFunctions.put(
			"groupId",
			new Function<PortletItem, Object>() {

				@Override
				public Object apply(PortletItem portletItem) {
					return portletItem.getGroupId();
				}

			});
		attributeSetterBiConsumers.put(
			"groupId",
			new BiConsumer<PortletItem, Object>() {

				@Override
				public void accept(
					PortletItem portletItem, Object groupIdObject) {

					portletItem.setGroupId((Long)groupIdObject);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<PortletItem, Object>() {

				@Override
				public Object apply(PortletItem portletItem) {
					return portletItem.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<PortletItem, Object>() {

				@Override
				public void accept(
					PortletItem portletItem, Object companyIdObject) {

					portletItem.setCompanyId((Long)companyIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userId",
			new Function<PortletItem, Object>() {

				@Override
				public Object apply(PortletItem portletItem) {
					return portletItem.getUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"userId",
			new BiConsumer<PortletItem, Object>() {

				@Override
				public void accept(
					PortletItem portletItem, Object userIdObject) {

					portletItem.setUserId((Long)userIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userName",
			new Function<PortletItem, Object>() {

				@Override
				public Object apply(PortletItem portletItem) {
					return portletItem.getUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"userName",
			new BiConsumer<PortletItem, Object>() {

				@Override
				public void accept(
					PortletItem portletItem, Object userNameObject) {

					portletItem.setUserName((String)userNameObject);
				}

			});
		attributeGetterFunctions.put(
			"createDate",
			new Function<PortletItem, Object>() {

				@Override
				public Object apply(PortletItem portletItem) {
					return portletItem.getCreateDate();
				}

			});
		attributeSetterBiConsumers.put(
			"createDate",
			new BiConsumer<PortletItem, Object>() {

				@Override
				public void accept(
					PortletItem portletItem, Object createDateObject) {

					portletItem.setCreateDate((Date)createDateObject);
				}

			});
		attributeGetterFunctions.put(
			"modifiedDate",
			new Function<PortletItem, Object>() {

				@Override
				public Object apply(PortletItem portletItem) {
					return portletItem.getModifiedDate();
				}

			});
		attributeSetterBiConsumers.put(
			"modifiedDate",
			new BiConsumer<PortletItem, Object>() {

				@Override
				public void accept(
					PortletItem portletItem, Object modifiedDateObject) {

					portletItem.setModifiedDate((Date)modifiedDateObject);
				}

			});
		attributeGetterFunctions.put(
			"name",
			new Function<PortletItem, Object>() {

				@Override
				public Object apply(PortletItem portletItem) {
					return portletItem.getName();
				}

			});
		attributeSetterBiConsumers.put(
			"name",
			new BiConsumer<PortletItem, Object>() {

				@Override
				public void accept(PortletItem portletItem, Object nameObject) {
					portletItem.setName((String)nameObject);
				}

			});
		attributeGetterFunctions.put(
			"portletId",
			new Function<PortletItem, Object>() {

				@Override
				public Object apply(PortletItem portletItem) {
					return portletItem.getPortletId();
				}

			});
		attributeSetterBiConsumers.put(
			"portletId",
			new BiConsumer<PortletItem, Object>() {

				@Override
				public void accept(
					PortletItem portletItem, Object portletIdObject) {

					portletItem.setPortletId((String)portletIdObject);
				}

			});
		attributeGetterFunctions.put(
			"classNameId",
			new Function<PortletItem, Object>() {

				@Override
				public Object apply(PortletItem portletItem) {
					return portletItem.getClassNameId();
				}

			});
		attributeSetterBiConsumers.put(
			"classNameId",
			new BiConsumer<PortletItem, Object>() {

				@Override
				public void accept(
					PortletItem portletItem, Object classNameIdObject) {

					portletItem.setClassNameId((Long)classNameIdObject);
				}

			});

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	@Override
	public long getPortletItemId() {
		return _portletItemId;
	}

	@Override
	public void setPortletItemId(long portletItemId) {
		_portletItemId = portletItemId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_columnBitmask |= NAME_COLUMN_BITMASK;

		if (_originalName == null) {
			_originalName = _name;
		}

		_name = name;
	}

	public String getOriginalName() {
		return GetterUtil.getString(_originalName);
	}

	@Override
	public String getPortletId() {
		if (_portletId == null) {
			return "";
		}
		else {
			return _portletId;
		}
	}

	@Override
	public void setPortletId(String portletId) {
		_columnBitmask |= PORTLETID_COLUMN_BITMASK;

		if (_originalPortletId == null) {
			_originalPortletId = _portletId;
		}

		_portletId = portletId;
	}

	public String getOriginalPortletId() {
		return GetterUtil.getString(_originalPortletId);
	}

	@Override
	public String getClassName() {
		if (getClassNameId() <= 0) {
			return "";
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	@Override
	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	@Override
	public long getClassNameId() {
		return _classNameId;
	}

	@Override
	public void setClassNameId(long classNameId) {
		_columnBitmask |= CLASSNAMEID_COLUMN_BITMASK;

		if (!_setOriginalClassNameId) {
			_setOriginalClassNameId = true;

			_originalClassNameId = _classNameId;
		}

		_classNameId = classNameId;
	}

	public long getOriginalClassNameId() {
		return _originalClassNameId;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), PortletItem.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public PortletItem toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, PortletItem>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		PortletItemImpl portletItemImpl = new PortletItemImpl();

		portletItemImpl.setMvccVersion(getMvccVersion());
		portletItemImpl.setPortletItemId(getPortletItemId());
		portletItemImpl.setGroupId(getGroupId());
		portletItemImpl.setCompanyId(getCompanyId());
		portletItemImpl.setUserId(getUserId());
		portletItemImpl.setUserName(getUserName());
		portletItemImpl.setCreateDate(getCreateDate());
		portletItemImpl.setModifiedDate(getModifiedDate());
		portletItemImpl.setName(getName());
		portletItemImpl.setPortletId(getPortletId());
		portletItemImpl.setClassNameId(getClassNameId());

		portletItemImpl.resetOriginalValues();

		return portletItemImpl;
	}

	@Override
	public int compareTo(PortletItem portletItem) {
		long primaryKey = portletItem.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PortletItem)) {
			return false;
		}

		PortletItem portletItem = (PortletItem)obj;

		long primaryKey = portletItem.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		PortletItemModelImpl portletItemModelImpl = this;

		portletItemModelImpl._originalGroupId = portletItemModelImpl._groupId;

		portletItemModelImpl._setOriginalGroupId = false;

		portletItemModelImpl._setModifiedDate = false;

		portletItemModelImpl._originalName = portletItemModelImpl._name;

		portletItemModelImpl._originalPortletId =
			portletItemModelImpl._portletId;

		portletItemModelImpl._originalClassNameId =
			portletItemModelImpl._classNameId;

		portletItemModelImpl._setOriginalClassNameId = false;

		portletItemModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<PortletItem> toCacheModel() {
		PortletItemCacheModel portletItemCacheModel =
			new PortletItemCacheModel();

		portletItemCacheModel.mvccVersion = getMvccVersion();

		portletItemCacheModel.portletItemId = getPortletItemId();

		portletItemCacheModel.groupId = getGroupId();

		portletItemCacheModel.companyId = getCompanyId();

		portletItemCacheModel.userId = getUserId();

		portletItemCacheModel.userName = getUserName();

		String userName = portletItemCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			portletItemCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			portletItemCacheModel.createDate = createDate.getTime();
		}
		else {
			portletItemCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			portletItemCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			portletItemCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		portletItemCacheModel.name = getName();

		String name = portletItemCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			portletItemCacheModel.name = null;
		}

		portletItemCacheModel.portletId = getPortletId();

		String portletId = portletItemCacheModel.portletId;

		if ((portletId != null) && (portletId.length() == 0)) {
			portletItemCacheModel.portletId = null;
		}

		portletItemCacheModel.classNameId = getClassNameId();

		return portletItemCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<PortletItem, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<PortletItem, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<PortletItem, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((PortletItem)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<PortletItem, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<PortletItem, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<PortletItem, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((PortletItem)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, PortletItem>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _portletItemId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _name;
	private String _originalName;
	private String _portletId;
	private String _originalPortletId;
	private long _classNameId;
	private long _originalClassNameId;
	private boolean _setOriginalClassNameId;
	private long _columnBitmask;
	private PortletItem _escapedModel;

}