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

package com.liferay.polls.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.polls.model.PollsQuestion;
import com.liferay.polls.model.PollsQuestionModel;
import com.liferay.polls.model.PollsQuestionSoap;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the PollsQuestion service. Represents a row in the &quot;PollsQuestion&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>PollsQuestionModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link PollsQuestionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PollsQuestionImpl
 * @generated
 */
@JSON(strict = true)
public class PollsQuestionModelImpl
	extends BaseModelImpl<PollsQuestion> implements PollsQuestionModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a polls question model instance should use the <code>PollsQuestion</code> interface instead.
	 */
	public static final String TABLE_NAME = "PollsQuestion";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"questionId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"title", Types.VARCHAR}, {"description", Types.VARCHAR},
		{"expirationDate", Types.TIMESTAMP},
		{"lastPublishDate", Types.TIMESTAMP}, {"lastVoteDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("questionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("title", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("expirationDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("lastVoteDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table PollsQuestion (uuid_ VARCHAR(75) null,questionId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,title STRING null,description STRING null,expirationDate DATE null,lastPublishDate DATE null,lastVoteDate DATE null)";

	public static final String TABLE_SQL_DROP = "drop table PollsQuestion";

	public static final String ORDER_BY_JPQL =
		" ORDER BY pollsQuestion.createDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY PollsQuestion.createDate DESC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.polls.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.com.liferay.polls.model.PollsQuestion"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.polls.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.com.liferay.polls.model.PollsQuestion"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.polls.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.com.liferay.polls.model.PollsQuestion"),
		true);

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long GROUPID_COLUMN_BITMASK = 2L;

	public static final long UUID_COLUMN_BITMASK = 4L;

	public static final long CREATEDATE_COLUMN_BITMASK = 8L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static PollsQuestion toModel(PollsQuestionSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		PollsQuestion model = new PollsQuestionImpl();

		model.setUuid(soapModel.getUuid());
		model.setQuestionId(soapModel.getQuestionId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setTitle(soapModel.getTitle());
		model.setDescription(soapModel.getDescription());
		model.setExpirationDate(soapModel.getExpirationDate());
		model.setLastPublishDate(soapModel.getLastPublishDate());
		model.setLastVoteDate(soapModel.getLastVoteDate());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<PollsQuestion> toModels(PollsQuestionSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<PollsQuestion> models = new ArrayList<PollsQuestion>(
			soapModels.length);

		for (PollsQuestionSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.polls.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.polls.model.PollsQuestion"));

	public PollsQuestionModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _questionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setQuestionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _questionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return PollsQuestion.class;
	}

	@Override
	public String getModelClassName() {
		return PollsQuestion.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<PollsQuestion, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<PollsQuestion, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<PollsQuestion, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((PollsQuestion)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<PollsQuestion, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<PollsQuestion, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(PollsQuestion)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<PollsQuestion, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<PollsQuestion, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, PollsQuestion>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			PollsQuestion.class.getClassLoader(), PollsQuestion.class,
			ModelWrapper.class);

		try {
			Constructor<PollsQuestion> constructor =
				(Constructor<PollsQuestion>)proxyClass.getConstructor(
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

	private static final Map<String, Function<PollsQuestion, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<PollsQuestion, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<PollsQuestion, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<PollsQuestion, Object>>();
		Map<String, BiConsumer<PollsQuestion, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<PollsQuestion, ?>>();

		attributeGetterFunctions.put(
			"uuid",
			new Function<PollsQuestion, Object>() {

				@Override
				public Object apply(PollsQuestion pollsQuestion) {
					return pollsQuestion.getUuid();
				}

			});
		attributeSetterBiConsumers.put(
			"uuid",
			new BiConsumer<PollsQuestion, Object>() {

				@Override
				public void accept(
					PollsQuestion pollsQuestion, Object uuidObject) {

					pollsQuestion.setUuid((String)uuidObject);
				}

			});
		attributeGetterFunctions.put(
			"questionId",
			new Function<PollsQuestion, Object>() {

				@Override
				public Object apply(PollsQuestion pollsQuestion) {
					return pollsQuestion.getQuestionId();
				}

			});
		attributeSetterBiConsumers.put(
			"questionId",
			new BiConsumer<PollsQuestion, Object>() {

				@Override
				public void accept(
					PollsQuestion pollsQuestion, Object questionIdObject) {

					pollsQuestion.setQuestionId((Long)questionIdObject);
				}

			});
		attributeGetterFunctions.put(
			"groupId",
			new Function<PollsQuestion, Object>() {

				@Override
				public Object apply(PollsQuestion pollsQuestion) {
					return pollsQuestion.getGroupId();
				}

			});
		attributeSetterBiConsumers.put(
			"groupId",
			new BiConsumer<PollsQuestion, Object>() {

				@Override
				public void accept(
					PollsQuestion pollsQuestion, Object groupIdObject) {

					pollsQuestion.setGroupId((Long)groupIdObject);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<PollsQuestion, Object>() {

				@Override
				public Object apply(PollsQuestion pollsQuestion) {
					return pollsQuestion.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<PollsQuestion, Object>() {

				@Override
				public void accept(
					PollsQuestion pollsQuestion, Object companyIdObject) {

					pollsQuestion.setCompanyId((Long)companyIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userId",
			new Function<PollsQuestion, Object>() {

				@Override
				public Object apply(PollsQuestion pollsQuestion) {
					return pollsQuestion.getUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"userId",
			new BiConsumer<PollsQuestion, Object>() {

				@Override
				public void accept(
					PollsQuestion pollsQuestion, Object userIdObject) {

					pollsQuestion.setUserId((Long)userIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userName",
			new Function<PollsQuestion, Object>() {

				@Override
				public Object apply(PollsQuestion pollsQuestion) {
					return pollsQuestion.getUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"userName",
			new BiConsumer<PollsQuestion, Object>() {

				@Override
				public void accept(
					PollsQuestion pollsQuestion, Object userNameObject) {

					pollsQuestion.setUserName((String)userNameObject);
				}

			});
		attributeGetterFunctions.put(
			"createDate",
			new Function<PollsQuestion, Object>() {

				@Override
				public Object apply(PollsQuestion pollsQuestion) {
					return pollsQuestion.getCreateDate();
				}

			});
		attributeSetterBiConsumers.put(
			"createDate",
			new BiConsumer<PollsQuestion, Object>() {

				@Override
				public void accept(
					PollsQuestion pollsQuestion, Object createDateObject) {

					pollsQuestion.setCreateDate((Date)createDateObject);
				}

			});
		attributeGetterFunctions.put(
			"modifiedDate",
			new Function<PollsQuestion, Object>() {

				@Override
				public Object apply(PollsQuestion pollsQuestion) {
					return pollsQuestion.getModifiedDate();
				}

			});
		attributeSetterBiConsumers.put(
			"modifiedDate",
			new BiConsumer<PollsQuestion, Object>() {

				@Override
				public void accept(
					PollsQuestion pollsQuestion, Object modifiedDateObject) {

					pollsQuestion.setModifiedDate((Date)modifiedDateObject);
				}

			});
		attributeGetterFunctions.put(
			"title",
			new Function<PollsQuestion, Object>() {

				@Override
				public Object apply(PollsQuestion pollsQuestion) {
					return pollsQuestion.getTitle();
				}

			});
		attributeSetterBiConsumers.put(
			"title",
			new BiConsumer<PollsQuestion, Object>() {

				@Override
				public void accept(
					PollsQuestion pollsQuestion, Object titleObject) {

					pollsQuestion.setTitle((String)titleObject);
				}

			});
		attributeGetterFunctions.put(
			"description",
			new Function<PollsQuestion, Object>() {

				@Override
				public Object apply(PollsQuestion pollsQuestion) {
					return pollsQuestion.getDescription();
				}

			});
		attributeSetterBiConsumers.put(
			"description",
			new BiConsumer<PollsQuestion, Object>() {

				@Override
				public void accept(
					PollsQuestion pollsQuestion, Object descriptionObject) {

					pollsQuestion.setDescription((String)descriptionObject);
				}

			});
		attributeGetterFunctions.put(
			"expirationDate",
			new Function<PollsQuestion, Object>() {

				@Override
				public Object apply(PollsQuestion pollsQuestion) {
					return pollsQuestion.getExpirationDate();
				}

			});
		attributeSetterBiConsumers.put(
			"expirationDate",
			new BiConsumer<PollsQuestion, Object>() {

				@Override
				public void accept(
					PollsQuestion pollsQuestion, Object expirationDateObject) {

					pollsQuestion.setExpirationDate((Date)expirationDateObject);
				}

			});
		attributeGetterFunctions.put(
			"lastPublishDate",
			new Function<PollsQuestion, Object>() {

				@Override
				public Object apply(PollsQuestion pollsQuestion) {
					return pollsQuestion.getLastPublishDate();
				}

			});
		attributeSetterBiConsumers.put(
			"lastPublishDate",
			new BiConsumer<PollsQuestion, Object>() {

				@Override
				public void accept(
					PollsQuestion pollsQuestion, Object lastPublishDateObject) {

					pollsQuestion.setLastPublishDate(
						(Date)lastPublishDateObject);
				}

			});
		attributeGetterFunctions.put(
			"lastVoteDate",
			new Function<PollsQuestion, Object>() {

				@Override
				public Object apply(PollsQuestion pollsQuestion) {
					return pollsQuestion.getLastVoteDate();
				}

			});
		attributeSetterBiConsumers.put(
			"lastVoteDate",
			new BiConsumer<PollsQuestion, Object>() {

				@Override
				public void accept(
					PollsQuestion pollsQuestion, Object lastVoteDateObject) {

					pollsQuestion.setLastVoteDate((Date)lastVoteDateObject);
				}

			});

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		_columnBitmask |= UUID_COLUMN_BITMASK;

		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@JSON
	@Override
	public long getQuestionId() {
		return _questionId;
	}

	@Override
	public void setQuestionId(long questionId) {
		_questionId = questionId;
	}

	@JSON
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
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_columnBitmask = -1L;

		_createDate = createDate;
	}

	@JSON
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

	@JSON
	@Override
	public String getTitle() {
		if (_title == null) {
			return "";
		}
		else {
			return _title;
		}
	}

	@Override
	public String getTitle(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getTitle(languageId);
	}

	@Override
	public String getTitle(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getTitle(languageId, useDefault);
	}

	@Override
	public String getTitle(String languageId) {
		return LocalizationUtil.getLocalization(getTitle(), languageId);
	}

	@Override
	public String getTitle(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(
			getTitle(), languageId, useDefault);
	}

	@Override
	public String getTitleCurrentLanguageId() {
		return _titleCurrentLanguageId;
	}

	@JSON
	@Override
	public String getTitleCurrentValue() {
		Locale locale = getLocale(_titleCurrentLanguageId);

		return getTitle(locale);
	}

	@Override
	public Map<Locale, String> getTitleMap() {
		return LocalizationUtil.getLocalizationMap(getTitle());
	}

	@Override
	public void setTitle(String title) {
		_title = title;
	}

	@Override
	public void setTitle(String title, Locale locale) {
		setTitle(title, locale, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setTitle(String title, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(title)) {
			setTitle(
				LocalizationUtil.updateLocalization(
					getTitle(), "Title", title, languageId, defaultLanguageId));
		}
		else {
			setTitle(
				LocalizationUtil.removeLocalization(
					getTitle(), "Title", languageId));
		}
	}

	@Override
	public void setTitleCurrentLanguageId(String languageId) {
		_titleCurrentLanguageId = languageId;
	}

	@Override
	public void setTitleMap(Map<Locale, String> titleMap) {
		setTitleMap(titleMap, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setTitleMap(
		Map<Locale, String> titleMap, Locale defaultLocale) {

		if (titleMap == null) {
			return;
		}

		setTitle(
			LocalizationUtil.updateLocalization(
				titleMap, getTitle(), "Title",
				LocaleUtil.toLanguageId(defaultLocale)));
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
	public String getDescription(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDescription(languageId);
	}

	@Override
	public String getDescription(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDescription(languageId, useDefault);
	}

	@Override
	public String getDescription(String languageId) {
		return LocalizationUtil.getLocalization(getDescription(), languageId);
	}

	@Override
	public String getDescription(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(
			getDescription(), languageId, useDefault);
	}

	@Override
	public String getDescriptionCurrentLanguageId() {
		return _descriptionCurrentLanguageId;
	}

	@JSON
	@Override
	public String getDescriptionCurrentValue() {
		Locale locale = getLocale(_descriptionCurrentLanguageId);

		return getDescription(locale);
	}

	@Override
	public Map<Locale, String> getDescriptionMap() {
		return LocalizationUtil.getLocalizationMap(getDescription());
	}

	@Override
	public void setDescription(String description) {
		_description = description;
	}

	@Override
	public void setDescription(String description, Locale locale) {
		setDescription(description, locale, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setDescription(
		String description, Locale locale, Locale defaultLocale) {

		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(description)) {
			setDescription(
				LocalizationUtil.updateLocalization(
					getDescription(), "Description", description, languageId,
					defaultLanguageId));
		}
		else {
			setDescription(
				LocalizationUtil.removeLocalization(
					getDescription(), "Description", languageId));
		}
	}

	@Override
	public void setDescriptionCurrentLanguageId(String languageId) {
		_descriptionCurrentLanguageId = languageId;
	}

	@Override
	public void setDescriptionMap(Map<Locale, String> descriptionMap) {
		setDescriptionMap(descriptionMap, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setDescriptionMap(
		Map<Locale, String> descriptionMap, Locale defaultLocale) {

		if (descriptionMap == null) {
			return;
		}

		setDescription(
			LocalizationUtil.updateLocalization(
				descriptionMap, getDescription(), "Description",
				LocaleUtil.toLanguageId(defaultLocale)));
	}

	@JSON
	@Override
	public Date getExpirationDate() {
		return _expirationDate;
	}

	@Override
	public void setExpirationDate(Date expirationDate) {
		_expirationDate = expirationDate;
	}

	@JSON
	@Override
	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;
	}

	@JSON
	@Override
	public Date getLastVoteDate() {
		return _lastVoteDate;
	}

	@Override
	public void setLastVoteDate(Date lastVoteDate) {
		_lastVoteDate = lastVoteDate;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(PollsQuestion.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), PollsQuestion.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

		Map<Locale, String> titleMap = getTitleMap();

		for (Map.Entry<Locale, String> entry : titleMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		Map<Locale, String> descriptionMap = getDescriptionMap();

		for (Map.Entry<Locale, String> entry : descriptionMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		return availableLanguageIds.toArray(
			new String[availableLanguageIds.size()]);
	}

	@Override
	public String getDefaultLanguageId() {
		String xml = getTitle();

		if (xml == null) {
			return "";
		}

		Locale defaultLocale = LocaleUtil.getSiteDefault();

		return LocalizationUtil.getDefaultLanguageId(xml, defaultLocale);
	}

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException {
		Locale defaultLocale = LocaleUtil.fromLanguageId(
			getDefaultLanguageId());

		Locale[] availableLocales = LocaleUtil.fromLanguageIds(
			getAvailableLanguageIds());

		Locale defaultImportLocale = LocalizationUtil.getDefaultImportLocale(
			PollsQuestion.class.getName(), getPrimaryKey(), defaultLocale,
			availableLocales);

		prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	@SuppressWarnings("unused")
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException {

		Locale defaultLocale = LocaleUtil.getSiteDefault();

		String modelDefaultLanguageId = getDefaultLanguageId();

		String title = getTitle(defaultLocale);

		if (Validator.isNull(title)) {
			setTitle(getTitle(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setTitle(getTitle(defaultLocale), defaultLocale, defaultLocale);
		}

		String description = getDescription(defaultLocale);

		if (Validator.isNull(description)) {
			setDescription(
				getDescription(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setDescription(
				getDescription(defaultLocale), defaultLocale, defaultLocale);
		}
	}

	@Override
	public PollsQuestion toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, PollsQuestion>
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
		PollsQuestionImpl pollsQuestionImpl = new PollsQuestionImpl();

		pollsQuestionImpl.setUuid(getUuid());
		pollsQuestionImpl.setQuestionId(getQuestionId());
		pollsQuestionImpl.setGroupId(getGroupId());
		pollsQuestionImpl.setCompanyId(getCompanyId());
		pollsQuestionImpl.setUserId(getUserId());
		pollsQuestionImpl.setUserName(getUserName());
		pollsQuestionImpl.setCreateDate(getCreateDate());
		pollsQuestionImpl.setModifiedDate(getModifiedDate());
		pollsQuestionImpl.setTitle(getTitle());
		pollsQuestionImpl.setDescription(getDescription());
		pollsQuestionImpl.setExpirationDate(getExpirationDate());
		pollsQuestionImpl.setLastPublishDate(getLastPublishDate());
		pollsQuestionImpl.setLastVoteDate(getLastVoteDate());

		pollsQuestionImpl.resetOriginalValues();

		return pollsQuestionImpl;
	}

	@Override
	public int compareTo(PollsQuestion pollsQuestion) {
		int value = 0;

		value = DateUtil.compareTo(
			getCreateDate(), pollsQuestion.getCreateDate());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PollsQuestion)) {
			return false;
		}

		PollsQuestion pollsQuestion = (PollsQuestion)obj;

		long primaryKey = pollsQuestion.getPrimaryKey();

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
		PollsQuestionModelImpl pollsQuestionModelImpl = this;

		pollsQuestionModelImpl._originalUuid = pollsQuestionModelImpl._uuid;

		pollsQuestionModelImpl._originalGroupId =
			pollsQuestionModelImpl._groupId;

		pollsQuestionModelImpl._setOriginalGroupId = false;

		pollsQuestionModelImpl._originalCompanyId =
			pollsQuestionModelImpl._companyId;

		pollsQuestionModelImpl._setOriginalCompanyId = false;

		pollsQuestionModelImpl._setModifiedDate = false;

		pollsQuestionModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<PollsQuestion> toCacheModel() {
		PollsQuestionCacheModel pollsQuestionCacheModel =
			new PollsQuestionCacheModel();

		pollsQuestionCacheModel.uuid = getUuid();

		String uuid = pollsQuestionCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			pollsQuestionCacheModel.uuid = null;
		}

		pollsQuestionCacheModel.questionId = getQuestionId();

		pollsQuestionCacheModel.groupId = getGroupId();

		pollsQuestionCacheModel.companyId = getCompanyId();

		pollsQuestionCacheModel.userId = getUserId();

		pollsQuestionCacheModel.userName = getUserName();

		String userName = pollsQuestionCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			pollsQuestionCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			pollsQuestionCacheModel.createDate = createDate.getTime();
		}
		else {
			pollsQuestionCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			pollsQuestionCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			pollsQuestionCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		pollsQuestionCacheModel.title = getTitle();

		String title = pollsQuestionCacheModel.title;

		if ((title != null) && (title.length() == 0)) {
			pollsQuestionCacheModel.title = null;
		}

		pollsQuestionCacheModel.description = getDescription();

		String description = pollsQuestionCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			pollsQuestionCacheModel.description = null;
		}

		Date expirationDate = getExpirationDate();

		if (expirationDate != null) {
			pollsQuestionCacheModel.expirationDate = expirationDate.getTime();
		}
		else {
			pollsQuestionCacheModel.expirationDate = Long.MIN_VALUE;
		}

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			pollsQuestionCacheModel.lastPublishDate = lastPublishDate.getTime();
		}
		else {
			pollsQuestionCacheModel.lastPublishDate = Long.MIN_VALUE;
		}

		Date lastVoteDate = getLastVoteDate();

		if (lastVoteDate != null) {
			pollsQuestionCacheModel.lastVoteDate = lastVoteDate.getTime();
		}
		else {
			pollsQuestionCacheModel.lastVoteDate = Long.MIN_VALUE;
		}

		return pollsQuestionCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<PollsQuestion, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<PollsQuestion, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<PollsQuestion, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((PollsQuestion)this));
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
		Map<String, Function<PollsQuestion, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<PollsQuestion, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<PollsQuestion, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((PollsQuestion)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, PollsQuestion>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private String _uuid;
	private String _originalUuid;
	private long _questionId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _title;
	private String _titleCurrentLanguageId;
	private String _description;
	private String _descriptionCurrentLanguageId;
	private Date _expirationDate;
	private Date _lastPublishDate;
	private Date _lastVoteDate;
	private long _columnBitmask;
	private PollsQuestion _escapedModel;

}