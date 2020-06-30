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

package com.liferay.commerce.product.type.virtual.service.base;

import com.liferay.commerce.product.type.virtual.model.CPDefinitionVirtualSetting;
import com.liferay.commerce.product.type.virtual.service.CPDefinitionVirtualSettingLocalService;
import com.liferay.commerce.product.type.virtual.service.persistence.CPDefinitionVirtualSettingPersistence;
import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.journal.service.persistence.JournalArticlePersistence;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the cp definition virtual setting local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.commerce.product.type.virtual.service.impl.CPDefinitionVirtualSettingLocalServiceImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see com.liferay.commerce.product.type.virtual.service.impl.CPDefinitionVirtualSettingLocalServiceImpl
 * @generated
 */
public abstract class CPDefinitionVirtualSettingLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements CPDefinitionVirtualSettingLocalService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>CPDefinitionVirtualSettingLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.commerce.product.type.virtual.service.CPDefinitionVirtualSettingLocalServiceUtil</code>.
	 */

	/**
	 * Adds the cp definition virtual setting to the database. Also notifies the appropriate model listeners.
	 *
	 * @param cpDefinitionVirtualSetting the cp definition virtual setting
	 * @return the cp definition virtual setting that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPDefinitionVirtualSetting addCPDefinitionVirtualSetting(
		CPDefinitionVirtualSetting cpDefinitionVirtualSetting) {

		cpDefinitionVirtualSetting.setNew(true);

		return cpDefinitionVirtualSettingPersistence.update(
			cpDefinitionVirtualSetting);
	}

	/**
	 * Creates a new cp definition virtual setting with the primary key. Does not add the cp definition virtual setting to the database.
	 *
	 * @param CPDefinitionVirtualSettingId the primary key for the new cp definition virtual setting
	 * @return the new cp definition virtual setting
	 */
	@Override
	@Transactional(enabled = false)
	public CPDefinitionVirtualSetting createCPDefinitionVirtualSetting(
		long CPDefinitionVirtualSettingId) {

		return cpDefinitionVirtualSettingPersistence.create(
			CPDefinitionVirtualSettingId);
	}

	/**
	 * Deletes the cp definition virtual setting with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param CPDefinitionVirtualSettingId the primary key of the cp definition virtual setting
	 * @return the cp definition virtual setting that was removed
	 * @throws PortalException if a cp definition virtual setting with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CPDefinitionVirtualSetting deleteCPDefinitionVirtualSetting(
			long CPDefinitionVirtualSettingId)
		throws PortalException {

		return cpDefinitionVirtualSettingPersistence.remove(
			CPDefinitionVirtualSettingId);
	}

	/**
	 * Deletes the cp definition virtual setting from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cpDefinitionVirtualSetting the cp definition virtual setting
	 * @return the cp definition virtual setting that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CPDefinitionVirtualSetting deleteCPDefinitionVirtualSetting(
		CPDefinitionVirtualSetting cpDefinitionVirtualSetting) {

		return cpDefinitionVirtualSettingPersistence.remove(
			cpDefinitionVirtualSetting);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			CPDefinitionVirtualSetting.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return cpDefinitionVirtualSettingPersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.type.virtual.model.impl.CPDefinitionVirtualSettingModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return cpDefinitionVirtualSettingPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.type.virtual.model.impl.CPDefinitionVirtualSettingModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return cpDefinitionVirtualSettingPersistence.findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return cpDefinitionVirtualSettingPersistence.countWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection) {

		return cpDefinitionVirtualSettingPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public CPDefinitionVirtualSetting fetchCPDefinitionVirtualSetting(
		long CPDefinitionVirtualSettingId) {

		return cpDefinitionVirtualSettingPersistence.fetchByPrimaryKey(
			CPDefinitionVirtualSettingId);
	}

	/**
	 * Returns the cp definition virtual setting matching the UUID and group.
	 *
	 * @param uuid the cp definition virtual setting's UUID
	 * @param groupId the primary key of the group
	 * @return the matching cp definition virtual setting, or <code>null</code> if a matching cp definition virtual setting could not be found
	 */
	@Override
	public CPDefinitionVirtualSetting
		fetchCPDefinitionVirtualSettingByUuidAndGroupId(
			String uuid, long groupId) {

		return cpDefinitionVirtualSettingPersistence.fetchByUUID_G(
			uuid, groupId);
	}

	/**
	 * Returns the cp definition virtual setting with the primary key.
	 *
	 * @param CPDefinitionVirtualSettingId the primary key of the cp definition virtual setting
	 * @return the cp definition virtual setting
	 * @throws PortalException if a cp definition virtual setting with the primary key could not be found
	 */
	@Override
	public CPDefinitionVirtualSetting getCPDefinitionVirtualSetting(
			long CPDefinitionVirtualSettingId)
		throws PortalException {

		return cpDefinitionVirtualSettingPersistence.findByPrimaryKey(
			CPDefinitionVirtualSettingId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			cpDefinitionVirtualSettingLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CPDefinitionVirtualSetting.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"CPDefinitionVirtualSettingId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			cpDefinitionVirtualSettingLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(
			CPDefinitionVirtualSetting.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"CPDefinitionVirtualSettingId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			cpDefinitionVirtualSettingLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CPDefinitionVirtualSetting.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"CPDefinitionVirtualSettingId");
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		final PortletDataContext portletDataContext) {

		final ExportActionableDynamicQuery exportActionableDynamicQuery =
			new ExportActionableDynamicQuery() {

				@Override
				public long performCount() throws PortalException {
					ManifestSummary manifestSummary =
						portletDataContext.getManifestSummary();

					StagedModelType stagedModelType = getStagedModelType();

					long modelAdditionCount = super.performCount();

					manifestSummary.addModelAdditionCount(
						stagedModelType, modelAdditionCount);

					long modelDeletionCount =
						ExportImportHelperUtil.getModelDeletionCount(
							portletDataContext, stagedModelType);

					manifestSummary.addModelDeletionCount(
						stagedModelType, modelDeletionCount);

					return modelAdditionCount;
				}

			};

		initActionableDynamicQuery(exportActionableDynamicQuery);

		exportActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					portletDataContext.addDateRangeCriteria(
						dynamicQuery, "modifiedDate");

					StagedModelType stagedModelType =
						exportActionableDynamicQuery.getStagedModelType();

					long referrerClassNameId =
						stagedModelType.getReferrerClassNameId();

					Property classNameIdProperty = PropertyFactoryUtil.forName(
						"classNameId");

					if ((referrerClassNameId !=
							StagedModelType.REFERRER_CLASS_NAME_ID_ALL) &&
						(referrerClassNameId !=
							StagedModelType.REFERRER_CLASS_NAME_ID_ANY)) {

						dynamicQuery.add(
							classNameIdProperty.eq(
								stagedModelType.getReferrerClassNameId()));
					}
					else if (referrerClassNameId ==
								StagedModelType.REFERRER_CLASS_NAME_ID_ANY) {

						dynamicQuery.add(classNameIdProperty.isNotNull());
					}
				}

			});

		exportActionableDynamicQuery.setCompanyId(
			portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setGroupId(
			portletDataContext.getScopeGroupId());

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<CPDefinitionVirtualSetting>() {

				@Override
				public void performAction(
						CPDefinitionVirtualSetting cpDefinitionVirtualSetting)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, cpDefinitionVirtualSetting);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(
					CPDefinitionVirtualSetting.class.getName()),
				StagedModelType.REFERRER_CLASS_NAME_ID_ALL));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return cpDefinitionVirtualSettingLocalService.
			deleteCPDefinitionVirtualSetting(
				(CPDefinitionVirtualSetting)persistedModel);
	}

	public BasePersistence<CPDefinitionVirtualSetting> getBasePersistence() {
		return cpDefinitionVirtualSettingPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return cpDefinitionVirtualSettingPersistence.findByPrimaryKey(
			primaryKeyObj);
	}

	/**
	 * Returns all the cp definition virtual settings matching the UUID and company.
	 *
	 * @param uuid the UUID of the cp definition virtual settings
	 * @param companyId the primary key of the company
	 * @return the matching cp definition virtual settings, or an empty list if no matches were found
	 */
	@Override
	public List<CPDefinitionVirtualSetting>
		getCPDefinitionVirtualSettingsByUuidAndCompanyId(
			String uuid, long companyId) {

		return cpDefinitionVirtualSettingPersistence.findByUuid_C(
			uuid, companyId);
	}

	/**
	 * Returns a range of cp definition virtual settings matching the UUID and company.
	 *
	 * @param uuid the UUID of the cp definition virtual settings
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of cp definition virtual settings
	 * @param end the upper bound of the range of cp definition virtual settings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching cp definition virtual settings, or an empty list if no matches were found
	 */
	@Override
	public List<CPDefinitionVirtualSetting>
		getCPDefinitionVirtualSettingsByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			OrderByComparator<CPDefinitionVirtualSetting> orderByComparator) {

		return cpDefinitionVirtualSettingPersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the cp definition virtual setting matching the UUID and group.
	 *
	 * @param uuid the cp definition virtual setting's UUID
	 * @param groupId the primary key of the group
	 * @return the matching cp definition virtual setting
	 * @throws PortalException if a matching cp definition virtual setting could not be found
	 */
	@Override
	public CPDefinitionVirtualSetting
			getCPDefinitionVirtualSettingByUuidAndGroupId(
				String uuid, long groupId)
		throws PortalException {

		return cpDefinitionVirtualSettingPersistence.findByUUID_G(
			uuid, groupId);
	}

	/**
	 * Returns a range of all the cp definition virtual settings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.type.virtual.model.impl.CPDefinitionVirtualSettingModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp definition virtual settings
	 * @param end the upper bound of the range of cp definition virtual settings (not inclusive)
	 * @return the range of cp definition virtual settings
	 */
	@Override
	public List<CPDefinitionVirtualSetting> getCPDefinitionVirtualSettings(
		int start, int end) {

		return cpDefinitionVirtualSettingPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of cp definition virtual settings.
	 *
	 * @return the number of cp definition virtual settings
	 */
	@Override
	public int getCPDefinitionVirtualSettingsCount() {
		return cpDefinitionVirtualSettingPersistence.countAll();
	}

	/**
	 * Updates the cp definition virtual setting in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param cpDefinitionVirtualSetting the cp definition virtual setting
	 * @return the cp definition virtual setting that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPDefinitionVirtualSetting updateCPDefinitionVirtualSetting(
		CPDefinitionVirtualSetting cpDefinitionVirtualSetting) {

		return cpDefinitionVirtualSettingPersistence.update(
			cpDefinitionVirtualSetting);
	}

	/**
	 * Returns the cp definition virtual setting local service.
	 *
	 * @return the cp definition virtual setting local service
	 */
	public CPDefinitionVirtualSettingLocalService
		getCPDefinitionVirtualSettingLocalService() {

		return cpDefinitionVirtualSettingLocalService;
	}

	/**
	 * Sets the cp definition virtual setting local service.
	 *
	 * @param cpDefinitionVirtualSettingLocalService the cp definition virtual setting local service
	 */
	public void setCPDefinitionVirtualSettingLocalService(
		CPDefinitionVirtualSettingLocalService
			cpDefinitionVirtualSettingLocalService) {

		this.cpDefinitionVirtualSettingLocalService =
			cpDefinitionVirtualSettingLocalService;
	}

	/**
	 * Returns the cp definition virtual setting persistence.
	 *
	 * @return the cp definition virtual setting persistence
	 */
	public CPDefinitionVirtualSettingPersistence
		getCPDefinitionVirtualSettingPersistence() {

		return cpDefinitionVirtualSettingPersistence;
	}

	/**
	 * Sets the cp definition virtual setting persistence.
	 *
	 * @param cpDefinitionVirtualSettingPersistence the cp definition virtual setting persistence
	 */
	public void setCPDefinitionVirtualSettingPersistence(
		CPDefinitionVirtualSettingPersistence
			cpDefinitionVirtualSettingPersistence) {

		this.cpDefinitionVirtualSettingPersistence =
			cpDefinitionVirtualSettingPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService
		getCounterLocalService() {

		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService
			counterLocalService) {

		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the journal article local service.
	 *
	 * @return the journal article local service
	 */
	public com.liferay.journal.service.JournalArticleLocalService
		getJournalArticleLocalService() {

		return journalArticleLocalService;
	}

	/**
	 * Sets the journal article local service.
	 *
	 * @param journalArticleLocalService the journal article local service
	 */
	public void setJournalArticleLocalService(
		com.liferay.journal.service.JournalArticleLocalService
			journalArticleLocalService) {

		this.journalArticleLocalService = journalArticleLocalService;
	}

	/**
	 * Returns the journal article persistence.
	 *
	 * @return the journal article persistence
	 */
	public JournalArticlePersistence getJournalArticlePersistence() {
		return journalArticlePersistence;
	}

	/**
	 * Sets the journal article persistence.
	 *
	 * @param journalArticlePersistence the journal article persistence
	 */
	public void setJournalArticlePersistence(
		JournalArticlePersistence journalArticlePersistence) {

		this.journalArticlePersistence = journalArticlePersistence;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService
		getClassNameLocalService() {

		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService
			classNameLocalService) {

		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {

		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService
		getResourceLocalService() {

		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService
			resourceLocalService) {

		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService
		getUserLocalService() {

		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {

		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	/**
	 * Returns the dl app local service.
	 *
	 * @return the dl app local service
	 */
	public com.liferay.document.library.kernel.service.DLAppLocalService
		getDLAppLocalService() {

		return dlAppLocalService;
	}

	/**
	 * Sets the dl app local service.
	 *
	 * @param dlAppLocalService the dl app local service
	 */
	public void setDLAppLocalService(
		com.liferay.document.library.kernel.service.DLAppLocalService
			dlAppLocalService) {

		this.dlAppLocalService = dlAppLocalService;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register(
			"com.liferay.commerce.product.type.virtual.model.CPDefinitionVirtualSetting",
			cpDefinitionVirtualSettingLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.commerce.product.type.virtual.model.CPDefinitionVirtualSetting");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CPDefinitionVirtualSettingLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CPDefinitionVirtualSetting.class;
	}

	protected String getModelClassName() {
		return CPDefinitionVirtualSetting.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				cpDefinitionVirtualSettingPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	@BeanReference(type = CPDefinitionVirtualSettingLocalService.class)
	protected CPDefinitionVirtualSettingLocalService
		cpDefinitionVirtualSettingLocalService;

	@BeanReference(type = CPDefinitionVirtualSettingPersistence.class)
	protected CPDefinitionVirtualSettingPersistence
		cpDefinitionVirtualSettingPersistence;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@ServiceReference(
		type = com.liferay.journal.service.JournalArticleLocalService.class
	)
	protected com.liferay.journal.service.JournalArticleLocalService
		journalArticleLocalService;

	@ServiceReference(type = JournalArticlePersistence.class)
	protected JournalArticlePersistence journalArticlePersistence;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ClassNameLocalService.class
	)
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ResourceLocalService.class
	)
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.UserLocalService.class
	)
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;

	@ServiceReference(
		type = com.liferay.document.library.kernel.service.DLAppLocalService.class
	)
	protected com.liferay.document.library.kernel.service.DLAppLocalService
		dlAppLocalService;

	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry
		persistedModelLocalServiceRegistry;

}