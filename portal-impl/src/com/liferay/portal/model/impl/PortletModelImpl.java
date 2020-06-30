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
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.PortletModel;
import com.liferay.portal.kernel.model.PortletSoap;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
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
 * The base model implementation for the Portlet service. Represents a row in the &quot;Portlet&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>PortletModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link PortletImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PortletImpl
 * @generated
 */
@JSON(strict = true)
public class PortletModelImpl
	extends BaseModelImpl<Portlet> implements PortletModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a portlet model instance should use the <code>Portlet</code> interface instead.
	 */
	public static final String TABLE_NAME = "Portlet";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"id_", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"portletId", Types.VARCHAR},
		{"roles", Types.VARCHAR}, {"active_", Types.BOOLEAN}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("id_", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("portletId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("roles", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("active_", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Portlet (mvccVersion LONG default 0 not null,id_ LONG not null primary key,companyId LONG,portletId VARCHAR(200) null,roles STRING null,active_ BOOLEAN)";

	public static final String TABLE_SQL_DROP = "drop table Portlet";

	public static final String ORDER_BY_JPQL = " ORDER BY portlet.id ASC";

	public static final String ORDER_BY_SQL = " ORDER BY Portlet.id_ ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.entity.cache.enabled.com.liferay.portal.kernel.model.Portlet"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.finder.cache.enabled.com.liferay.portal.kernel.model.Portlet"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.column.bitmask.enabled.com.liferay.portal.kernel.model.Portlet"),
		true);

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long PORTLETID_COLUMN_BITMASK = 2L;

	public static final long ID_COLUMN_BITMASK = 4L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static Portlet toModel(PortletSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Portlet model = new PortletImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setId(soapModel.getId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setPortletId(soapModel.getPortletId());
		model.setRoles(soapModel.getRoles());
		model.setActive(soapModel.isActive());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Portlet> toModels(PortletSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Portlet> models = new ArrayList<Portlet>(soapModels.length);

		for (PortletSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.portal.kernel.model.Portlet"));

	public PortletModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _id;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _id;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Portlet.class;
	}

	@Override
	public String getModelClassName() {
		return Portlet.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Portlet, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Portlet, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Portlet, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((Portlet)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Portlet, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Portlet, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(Portlet)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Portlet, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Portlet, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, Portlet>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			Portlet.class.getClassLoader(), Portlet.class, ModelWrapper.class);

		try {
			Constructor<Portlet> constructor =
				(Constructor<Portlet>)proxyClass.getConstructor(
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

	private static final Map<String, Function<Portlet, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Portlet, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Portlet, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Portlet, Object>>();
		Map<String, BiConsumer<Portlet, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Portlet, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion",
			new Function<Portlet, Object>() {

				@Override
				public Object apply(Portlet portlet) {
					return portlet.getMvccVersion();
				}

			});
		attributeSetterBiConsumers.put(
			"mvccVersion",
			new BiConsumer<Portlet, Object>() {

				@Override
				public void accept(Portlet portlet, Object mvccVersionObject) {
					portlet.setMvccVersion((Long)mvccVersionObject);
				}

			});
		attributeGetterFunctions.put(
			"id",
			new Function<Portlet, Object>() {

				@Override
				public Object apply(Portlet portlet) {
					return portlet.getId();
				}

			});
		attributeSetterBiConsumers.put(
			"id",
			new BiConsumer<Portlet, Object>() {

				@Override
				public void accept(Portlet portlet, Object idObject) {
					portlet.setId((Long)idObject);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<Portlet, Object>() {

				@Override
				public Object apply(Portlet portlet) {
					return portlet.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<Portlet, Object>() {

				@Override
				public void accept(Portlet portlet, Object companyIdObject) {
					portlet.setCompanyId((Long)companyIdObject);
				}

			});
		attributeGetterFunctions.put(
			"portletId",
			new Function<Portlet, Object>() {

				@Override
				public Object apply(Portlet portlet) {
					return portlet.getPortletId();
				}

			});
		attributeSetterBiConsumers.put(
			"portletId",
			new BiConsumer<Portlet, Object>() {

				@Override
				public void accept(Portlet portlet, Object portletIdObject) {
					portlet.setPortletId((String)portletIdObject);
				}

			});
		attributeGetterFunctions.put(
			"roles",
			new Function<Portlet, Object>() {

				@Override
				public Object apply(Portlet portlet) {
					return portlet.getRoles();
				}

			});
		attributeSetterBiConsumers.put(
			"roles",
			new BiConsumer<Portlet, Object>() {

				@Override
				public void accept(Portlet portlet, Object rolesObject) {
					portlet.setRoles((String)rolesObject);
				}

			});
		attributeGetterFunctions.put(
			"active",
			new Function<Portlet, Object>() {

				@Override
				public Object apply(Portlet portlet) {
					return portlet.getActive();
				}

			});
		attributeSetterBiConsumers.put(
			"active",
			new BiConsumer<Portlet, Object>() {

				@Override
				public void accept(Portlet portlet, Object activeObject) {
					portlet.setActive((Boolean)activeObject);
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
	public long getId() {
		return _id;
	}

	@Override
	public void setId(long id) {
		_id = id;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@JSON
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

	@JSON
	@Override
	public String getRoles() {
		if (_roles == null) {
			return "";
		}
		else {
			return _roles;
		}
	}

	@Override
	public void setRoles(String roles) {
		_roles = roles;
	}

	@JSON
	@Override
	public boolean getActive() {
		return _active;
	}

	@JSON
	@Override
	public boolean isActive() {
		return _active;
	}

	@Override
	public void setActive(boolean active) {
		_active = active;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), Portlet.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Portlet toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, Portlet>
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
		PortletImpl portletImpl = new PortletImpl();

		portletImpl.setMvccVersion(getMvccVersion());
		portletImpl.setId(getId());
		portletImpl.setCompanyId(getCompanyId());
		portletImpl.setPortletId(getPortletId());
		portletImpl.setRoles(getRoles());
		portletImpl.setActive(isActive());

		portletImpl.resetOriginalValues();

		return portletImpl;
	}

	@Override
	public int compareTo(Portlet portlet) {
		long primaryKey = portlet.getPrimaryKey();

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

		if (!(obj instanceof Portlet)) {
			return false;
		}

		Portlet portlet = (Portlet)obj;

		long primaryKey = portlet.getPrimaryKey();

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
		PortletModelImpl portletModelImpl = this;

		portletModelImpl._originalCompanyId = portletModelImpl._companyId;

		portletModelImpl._setOriginalCompanyId = false;

		portletModelImpl._originalPortletId = portletModelImpl._portletId;

		portletModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Portlet> toCacheModel() {
		PortletCacheModel portletCacheModel = new PortletCacheModel();

		portletCacheModel.mvccVersion = getMvccVersion();

		portletCacheModel.id = getId();

		portletCacheModel.companyId = getCompanyId();

		portletCacheModel.portletId = getPortletId();

		String portletId = portletCacheModel.portletId;

		if ((portletId != null) && (portletId.length() == 0)) {
			portletCacheModel.portletId = null;
		}

		portletCacheModel.roles = getRoles();

		String roles = portletCacheModel.roles;

		if ((roles != null) && (roles.length() == 0)) {
			portletCacheModel.roles = null;
		}

		portletCacheModel.active = isActive();

		return portletCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Portlet, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Portlet, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Portlet, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((Portlet)this));
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
		Map<String, Function<Portlet, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<Portlet, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Portlet, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((Portlet)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, Portlet>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _id;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private String _portletId;
	private String _originalPortletId;
	private String _roles;
	private boolean _active;
	private long _columnBitmask;
	private Portlet _escapedModel;

}