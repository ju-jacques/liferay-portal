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
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.LayoutBranch;
import com.liferay.portal.kernel.model.LayoutBranchModel;
import com.liferay.portal.kernel.model.LayoutBranchSoap;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the LayoutBranch service. Represents a row in the &quot;LayoutBranch&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>LayoutBranchModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link LayoutBranchImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutBranchImpl
 * @generated
 */
@JSON(strict = true)
public class LayoutBranchModelImpl
	extends BaseModelImpl<LayoutBranch> implements LayoutBranchModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a layout branch model instance should use the <code>LayoutBranch</code> interface instead.
	 */
	public static final String TABLE_NAME = "LayoutBranch";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"layoutBranchId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"layoutSetBranchId", Types.BIGINT}, {"plid", Types.BIGINT},
		{"name", Types.VARCHAR}, {"description", Types.VARCHAR},
		{"master", Types.BOOLEAN}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("layoutBranchId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("layoutSetBranchId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("plid", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("master", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE =
		"create table LayoutBranch (mvccVersion LONG default 0 not null,layoutBranchId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,layoutSetBranchId LONG,plid LONG,name VARCHAR(75) null,description STRING null,master BOOLEAN)";

	public static final String TABLE_SQL_DROP = "drop table LayoutBranch";

	public static final String ORDER_BY_JPQL =
		" ORDER BY layoutBranch.layoutBranchId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY LayoutBranch.layoutBranchId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.entity.cache.enabled.com.liferay.portal.kernel.model.LayoutBranch"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.finder.cache.enabled.com.liferay.portal.kernel.model.LayoutBranch"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.column.bitmask.enabled.com.liferay.portal.kernel.model.LayoutBranch"),
		true);

	public static final long LAYOUTSETBRANCHID_COLUMN_BITMASK = 1L;

	public static final long MASTER_COLUMN_BITMASK = 2L;

	public static final long NAME_COLUMN_BITMASK = 4L;

	public static final long PLID_COLUMN_BITMASK = 8L;

	public static final long LAYOUTBRANCHID_COLUMN_BITMASK = 16L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static LayoutBranch toModel(LayoutBranchSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		LayoutBranch model = new LayoutBranchImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setLayoutBranchId(soapModel.getLayoutBranchId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setLayoutSetBranchId(soapModel.getLayoutSetBranchId());
		model.setPlid(soapModel.getPlid());
		model.setName(soapModel.getName());
		model.setDescription(soapModel.getDescription());
		model.setMaster(soapModel.isMaster());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<LayoutBranch> toModels(LayoutBranchSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<LayoutBranch> models = new ArrayList<LayoutBranch>(
			soapModels.length);

		for (LayoutBranchSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.portal.kernel.model.LayoutBranch"));

	public LayoutBranchModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _layoutBranchId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setLayoutBranchId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _layoutBranchId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return LayoutBranch.class;
	}

	@Override
	public String getModelClassName() {
		return LayoutBranch.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<LayoutBranch, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<LayoutBranch, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LayoutBranch, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((LayoutBranch)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<LayoutBranch, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<LayoutBranch, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(LayoutBranch)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<LayoutBranch, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<LayoutBranch, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, LayoutBranch>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			LayoutBranch.class.getClassLoader(), LayoutBranch.class,
			ModelWrapper.class);

		try {
			Constructor<LayoutBranch> constructor =
				(Constructor<LayoutBranch>)proxyClass.getConstructor(
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

	private static final Map<String, Function<LayoutBranch, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<LayoutBranch, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<LayoutBranch, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<LayoutBranch, Object>>();
		Map<String, BiConsumer<LayoutBranch, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<LayoutBranch, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion",
			new Function<LayoutBranch, Object>() {

				@Override
				public Object apply(LayoutBranch layoutBranch) {
					return layoutBranch.getMvccVersion();
				}

			});
		attributeSetterBiConsumers.put(
			"mvccVersion",
			new BiConsumer<LayoutBranch, Object>() {

				@Override
				public void accept(
					LayoutBranch layoutBranch, Object mvccVersionObject) {

					layoutBranch.setMvccVersion((Long)mvccVersionObject);
				}

			});
		attributeGetterFunctions.put(
			"layoutBranchId",
			new Function<LayoutBranch, Object>() {

				@Override
				public Object apply(LayoutBranch layoutBranch) {
					return layoutBranch.getLayoutBranchId();
				}

			});
		attributeSetterBiConsumers.put(
			"layoutBranchId",
			new BiConsumer<LayoutBranch, Object>() {

				@Override
				public void accept(
					LayoutBranch layoutBranch, Object layoutBranchIdObject) {

					layoutBranch.setLayoutBranchId((Long)layoutBranchIdObject);
				}

			});
		attributeGetterFunctions.put(
			"groupId",
			new Function<LayoutBranch, Object>() {

				@Override
				public Object apply(LayoutBranch layoutBranch) {
					return layoutBranch.getGroupId();
				}

			});
		attributeSetterBiConsumers.put(
			"groupId",
			new BiConsumer<LayoutBranch, Object>() {

				@Override
				public void accept(
					LayoutBranch layoutBranch, Object groupIdObject) {

					layoutBranch.setGroupId((Long)groupIdObject);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<LayoutBranch, Object>() {

				@Override
				public Object apply(LayoutBranch layoutBranch) {
					return layoutBranch.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<LayoutBranch, Object>() {

				@Override
				public void accept(
					LayoutBranch layoutBranch, Object companyIdObject) {

					layoutBranch.setCompanyId((Long)companyIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userId",
			new Function<LayoutBranch, Object>() {

				@Override
				public Object apply(LayoutBranch layoutBranch) {
					return layoutBranch.getUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"userId",
			new BiConsumer<LayoutBranch, Object>() {

				@Override
				public void accept(
					LayoutBranch layoutBranch, Object userIdObject) {

					layoutBranch.setUserId((Long)userIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userName",
			new Function<LayoutBranch, Object>() {

				@Override
				public Object apply(LayoutBranch layoutBranch) {
					return layoutBranch.getUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"userName",
			new BiConsumer<LayoutBranch, Object>() {

				@Override
				public void accept(
					LayoutBranch layoutBranch, Object userNameObject) {

					layoutBranch.setUserName((String)userNameObject);
				}

			});
		attributeGetterFunctions.put(
			"layoutSetBranchId",
			new Function<LayoutBranch, Object>() {

				@Override
				public Object apply(LayoutBranch layoutBranch) {
					return layoutBranch.getLayoutSetBranchId();
				}

			});
		attributeSetterBiConsumers.put(
			"layoutSetBranchId",
			new BiConsumer<LayoutBranch, Object>() {

				@Override
				public void accept(
					LayoutBranch layoutBranch, Object layoutSetBranchIdObject) {

					layoutBranch.setLayoutSetBranchId(
						(Long)layoutSetBranchIdObject);
				}

			});
		attributeGetterFunctions.put(
			"plid",
			new Function<LayoutBranch, Object>() {

				@Override
				public Object apply(LayoutBranch layoutBranch) {
					return layoutBranch.getPlid();
				}

			});
		attributeSetterBiConsumers.put(
			"plid",
			new BiConsumer<LayoutBranch, Object>() {

				@Override
				public void accept(
					LayoutBranch layoutBranch, Object plidObject) {

					layoutBranch.setPlid((Long)plidObject);
				}

			});
		attributeGetterFunctions.put(
			"name",
			new Function<LayoutBranch, Object>() {

				@Override
				public Object apply(LayoutBranch layoutBranch) {
					return layoutBranch.getName();
				}

			});
		attributeSetterBiConsumers.put(
			"name",
			new BiConsumer<LayoutBranch, Object>() {

				@Override
				public void accept(
					LayoutBranch layoutBranch, Object nameObject) {

					layoutBranch.setName((String)nameObject);
				}

			});
		attributeGetterFunctions.put(
			"description",
			new Function<LayoutBranch, Object>() {

				@Override
				public Object apply(LayoutBranch layoutBranch) {
					return layoutBranch.getDescription();
				}

			});
		attributeSetterBiConsumers.put(
			"description",
			new BiConsumer<LayoutBranch, Object>() {

				@Override
				public void accept(
					LayoutBranch layoutBranch, Object descriptionObject) {

					layoutBranch.setDescription((String)descriptionObject);
				}

			});
		attributeGetterFunctions.put(
			"master",
			new Function<LayoutBranch, Object>() {

				@Override
				public Object apply(LayoutBranch layoutBranch) {
					return layoutBranch.getMaster();
				}

			});
		attributeSetterBiConsumers.put(
			"master",
			new BiConsumer<LayoutBranch, Object>() {

				@Override
				public void accept(
					LayoutBranch layoutBranch, Object masterObject) {

					layoutBranch.setMaster((Boolean)masterObject);
				}

			});

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	@JSON
	@Override
	public long getLayoutBranchId() {
		return _layoutBranchId;
	}

	@Override
	public void setLayoutBranchId(long layoutBranchId) {
		_layoutBranchId = layoutBranchId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@JSON
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

	@JSON
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

	@JSON
	@Override
	public long getLayoutSetBranchId() {
		return _layoutSetBranchId;
	}

	@Override
	public void setLayoutSetBranchId(long layoutSetBranchId) {
		_columnBitmask |= LAYOUTSETBRANCHID_COLUMN_BITMASK;

		if (!_setOriginalLayoutSetBranchId) {
			_setOriginalLayoutSetBranchId = true;

			_originalLayoutSetBranchId = _layoutSetBranchId;
		}

		_layoutSetBranchId = layoutSetBranchId;
	}

	public long getOriginalLayoutSetBranchId() {
		return _originalLayoutSetBranchId;
	}

	@JSON
	@Override
	public long getPlid() {
		return _plid;
	}

	@Override
	public void setPlid(long plid) {
		_columnBitmask |= PLID_COLUMN_BITMASK;

		if (!_setOriginalPlid) {
			_setOriginalPlid = true;

			_originalPlid = _plid;
		}

		_plid = plid;
	}

	public long getOriginalPlid() {
		return _originalPlid;
	}

	@JSON
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

	@JSON
	@Override
	public String getDescription() {
		if (_description == null) {
			return "";
		}
		else {
			return _description;
		}
	}

	@Override
	public void setDescription(String description) {
		_description = description;
	}

	@JSON
	@Override
	public boolean getMaster() {
		return _master;
	}

	@JSON
	@Override
	public boolean isMaster() {
		return _master;
	}

	@Override
	public void setMaster(boolean master) {
		_columnBitmask |= MASTER_COLUMN_BITMASK;

		if (!_setOriginalMaster) {
			_setOriginalMaster = true;

			_originalMaster = _master;
		}

		_master = master;
	}

	public boolean getOriginalMaster() {
		return _originalMaster;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), LayoutBranch.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public LayoutBranch toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, LayoutBranch>
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
		LayoutBranchImpl layoutBranchImpl = new LayoutBranchImpl();

		layoutBranchImpl.setMvccVersion(getMvccVersion());
		layoutBranchImpl.setLayoutBranchId(getLayoutBranchId());
		layoutBranchImpl.setGroupId(getGroupId());
		layoutBranchImpl.setCompanyId(getCompanyId());
		layoutBranchImpl.setUserId(getUserId());
		layoutBranchImpl.setUserName(getUserName());
		layoutBranchImpl.setLayoutSetBranchId(getLayoutSetBranchId());
		layoutBranchImpl.setPlid(getPlid());
		layoutBranchImpl.setName(getName());
		layoutBranchImpl.setDescription(getDescription());
		layoutBranchImpl.setMaster(isMaster());

		layoutBranchImpl.resetOriginalValues();

		return layoutBranchImpl;
	}

	@Override
	public int compareTo(LayoutBranch layoutBranch) {
		long primaryKey = layoutBranch.getPrimaryKey();

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

		if (!(obj instanceof LayoutBranch)) {
			return false;
		}

		LayoutBranch layoutBranch = (LayoutBranch)obj;

		long primaryKey = layoutBranch.getPrimaryKey();

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
		LayoutBranchModelImpl layoutBranchModelImpl = this;

		layoutBranchModelImpl._originalLayoutSetBranchId =
			layoutBranchModelImpl._layoutSetBranchId;

		layoutBranchModelImpl._setOriginalLayoutSetBranchId = false;

		layoutBranchModelImpl._originalPlid = layoutBranchModelImpl._plid;

		layoutBranchModelImpl._setOriginalPlid = false;

		layoutBranchModelImpl._originalName = layoutBranchModelImpl._name;

		layoutBranchModelImpl._originalMaster = layoutBranchModelImpl._master;

		layoutBranchModelImpl._setOriginalMaster = false;

		layoutBranchModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<LayoutBranch> toCacheModel() {
		LayoutBranchCacheModel layoutBranchCacheModel =
			new LayoutBranchCacheModel();

		layoutBranchCacheModel.mvccVersion = getMvccVersion();

		layoutBranchCacheModel.layoutBranchId = getLayoutBranchId();

		layoutBranchCacheModel.groupId = getGroupId();

		layoutBranchCacheModel.companyId = getCompanyId();

		layoutBranchCacheModel.userId = getUserId();

		layoutBranchCacheModel.userName = getUserName();

		String userName = layoutBranchCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			layoutBranchCacheModel.userName = null;
		}

		layoutBranchCacheModel.layoutSetBranchId = getLayoutSetBranchId();

		layoutBranchCacheModel.plid = getPlid();

		layoutBranchCacheModel.name = getName();

		String name = layoutBranchCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			layoutBranchCacheModel.name = null;
		}

		layoutBranchCacheModel.description = getDescription();

		String description = layoutBranchCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			layoutBranchCacheModel.description = null;
		}

		layoutBranchCacheModel.master = isMaster();

		return layoutBranchCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<LayoutBranch, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<LayoutBranch, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LayoutBranch, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((LayoutBranch)this));
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
		Map<String, Function<LayoutBranch, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<LayoutBranch, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LayoutBranch, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((LayoutBranch)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, LayoutBranch>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _layoutBranchId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private long _layoutSetBranchId;
	private long _originalLayoutSetBranchId;
	private boolean _setOriginalLayoutSetBranchId;
	private long _plid;
	private long _originalPlid;
	private boolean _setOriginalPlid;
	private String _name;
	private String _originalName;
	private String _description;
	private boolean _master;
	private boolean _originalMaster;
	private boolean _setOriginalMaster;
	private long _columnBitmask;
	private LayoutBranch _escapedModel;

}