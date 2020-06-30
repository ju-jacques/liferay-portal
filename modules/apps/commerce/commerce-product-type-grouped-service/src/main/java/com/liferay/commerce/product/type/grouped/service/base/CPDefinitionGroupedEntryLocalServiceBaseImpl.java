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

package com.liferay.commerce.product.type.grouped.service.base;

import com.liferay.commerce.product.type.grouped.model.CPDefinitionGroupedEntry;
import com.liferay.commerce.product.type.grouped.service.CPDefinitionGroupedEntryLocalService;
import com.liferay.commerce.product.type.grouped.service.persistence.CPDefinitionGroupedEntryPersistence;
import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
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
 * Provides the base implementation for the cp definition grouped entry local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.commerce.product.type.grouped.service.impl.CPDefinitionGroupedEntryLocalServiceImpl}.
 * </p>
 *
 * @author Andrea Di Giorgi
 * @see com.liferay.commerce.product.type.grouped.service.impl.CPDefinitionGroupedEntryLocalServiceImpl
 * @generated
 */
public abstract class CPDefinitionGroupedEntryLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements CPDefinitionGroupedEntryLocalService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>CPDefinitionGroupedEntryLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.commerce.product.type.grouped.service.CPDefinitionGroupedEntryLocalServiceUtil</code>.
	 */

	/**
	 * Adds the cp definition grouped entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param cpDefinitionGroupedEntry the cp definition grouped entry
	 * @return the cp definition grouped entry that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPDefinitionGroupedEntry addCPDefinitionGroupedEntry(
		CPDefinitionGroupedEntry cpDefinitionGroupedEntry) {

		cpDefinitionGroupedEntry.setNew(true);

		return cpDefinitionGroupedEntryPersistence.update(
			cpDefinitionGroupedEntry);
	}

	/**
	 * Creates a new cp definition grouped entry with the primary key. Does not add the cp definition grouped entry to the database.
	 *
	 * @param CPDefinitionGroupedEntryId the primary key for the new cp definition grouped entry
	 * @return the new cp definition grouped entry
	 */
	@Override
	@Transactional(enabled = false)
	public CPDefinitionGroupedEntry createCPDefinitionGroupedEntry(
		long CPDefinitionGroupedEntryId) {

		return cpDefinitionGroupedEntryPersistence.create(
			CPDefinitionGroupedEntryId);
	}

	/**
	 * Deletes the cp definition grouped entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param CPDefinitionGroupedEntryId the primary key of the cp definition grouped entry
	 * @return the cp definition grouped entry that was removed
	 * @throws PortalException if a cp definition grouped entry with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CPDefinitionGroupedEntry deleteCPDefinitionGroupedEntry(
			long CPDefinitionGroupedEntryId)
		throws PortalException {

		return cpDefinitionGroupedEntryPersistence.remove(
			CPDefinitionGroupedEntryId);
	}

	/**
	 * Deletes the cp definition grouped entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cpDefinitionGroupedEntry the cp definition grouped entry
	 * @return the cp definition grouped entry that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CPDefinitionGroupedEntry deleteCPDefinitionGroupedEntry(
		CPDefinitionGroupedEntry cpDefinitionGroupedEntry) {

		return cpDefinitionGroupedEntryPersistence.remove(
			cpDefinitionGroupedEntry);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			CPDefinitionGroupedEntry.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return cpDefinitionGroupedEntryPersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.type.grouped.model.impl.CPDefinitionGroupedEntryModelImpl</code>.
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

		return cpDefinitionGroupedEntryPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.type.grouped.model.impl.CPDefinitionGroupedEntryModelImpl</code>.
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

		return cpDefinitionGroupedEntryPersistence.findWithDynamicQuery(
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
		return cpDefinitionGroupedEntryPersistence.countWithDynamicQuery(
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

		return cpDefinitionGroupedEntryPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public CPDefinitionGroupedEntry fetchCPDefinitionGroupedEntry(
		long CPDefinitionGroupedEntryId) {

		return cpDefinitionGroupedEntryPersistence.fetchByPrimaryKey(
			CPDefinitionGroupedEntryId);
	}

	/**
	 * Returns the cp definition grouped entry matching the UUID and group.
	 *
	 * @param uuid the cp definition grouped entry's UUID
	 * @param groupId the primary key of the group
	 * @return the matching cp definition grouped entry, or <code>null</code> if a matching cp definition grouped entry could not be found
	 */
	@Override
	public CPDefinitionGroupedEntry
		fetchCPDefinitionGroupedEntryByUuidAndGroupId(
			String uuid, long groupId) {

		return cpDefinitionGroupedEntryPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the cp definition grouped entry with the primary key.
	 *
	 * @param CPDefinitionGroupedEntryId the primary key of the cp definition grouped entry
	 * @return the cp definition grouped entry
	 * @throws PortalException if a cp definition grouped entry with the primary key could not be found
	 */
	@Override
	public CPDefinitionGroupedEntry getCPDefinitionGroupedEntry(
			long CPDefinitionGroupedEntryId)
		throws PortalException {

		return cpDefinitionGroupedEntryPersistence.findByPrimaryKey(
			CPDefinitionGroupedEntryId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			cpDefinitionGroupedEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CPDefinitionGroupedEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"CPDefinitionGroupedEntryId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			cpDefinitionGroupedEntryLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(
			CPDefinitionGroupedEntry.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"CPDefinitionGroupedEntryId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			cpDefinitionGroupedEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CPDefinitionGroupedEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"CPDefinitionGroupedEntryId");
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
				}

			});

		exportActionableDynamicQuery.setCompanyId(
			portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<CPDefinitionGroupedEntry>() {

				@Override
				public void performAction(
						CPDefinitionGroupedEntry cpDefinitionGroupedEntry)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, cpDefinitionGroupedEntry);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(
					CPDefinitionGroupedEntry.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return cpDefinitionGroupedEntryLocalService.
			deleteCPDefinitionGroupedEntry(
				(CPDefinitionGroupedEntry)persistedModel);
	}

	public BasePersistence<CPDefinitionGroupedEntry> getBasePersistence() {
		return cpDefinitionGroupedEntryPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return cpDefinitionGroupedEntryPersistence.findByPrimaryKey(
			primaryKeyObj);
	}

	/**
	 * Returns all the cp definition grouped entries matching the UUID and company.
	 *
	 * @param uuid the UUID of the cp definition grouped entries
	 * @param companyId the primary key of the company
	 * @return the matching cp definition grouped entries, or an empty list if no matches were found
	 */
	@Override
	public List<CPDefinitionGroupedEntry>
		getCPDefinitionGroupedEntriesByUuidAndCompanyId(
			String uuid, long companyId) {

		return cpDefinitionGroupedEntryPersistence.findByUuid_C(
			uuid, companyId);
	}

	/**
	 * Returns a range of cp definition grouped entries matching the UUID and company.
	 *
	 * @param uuid the UUID of the cp definition grouped entries
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of cp definition grouped entries
	 * @param end the upper bound of the range of cp definition grouped entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching cp definition grouped entries, or an empty list if no matches were found
	 */
	@Override
	public List<CPDefinitionGroupedEntry>
		getCPDefinitionGroupedEntriesByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			OrderByComparator<CPDefinitionGroupedEntry> orderByComparator) {

		return cpDefinitionGroupedEntryPersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the cp definition grouped entry matching the UUID and group.
	 *
	 * @param uuid the cp definition grouped entry's UUID
	 * @param groupId the primary key of the group
	 * @return the matching cp definition grouped entry
	 * @throws PortalException if a matching cp definition grouped entry could not be found
	 */
	@Override
	public CPDefinitionGroupedEntry getCPDefinitionGroupedEntryByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException {

		return cpDefinitionGroupedEntryPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the cp definition grouped entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.type.grouped.model.impl.CPDefinitionGroupedEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cp definition grouped entries
	 * @param end the upper bound of the range of cp definition grouped entries (not inclusive)
	 * @return the range of cp definition grouped entries
	 */
	@Override
	public List<CPDefinitionGroupedEntry> getCPDefinitionGroupedEntries(
		int start, int end) {

		return cpDefinitionGroupedEntryPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of cp definition grouped entries.
	 *
	 * @return the number of cp definition grouped entries
	 */
	@Override
	public int getCPDefinitionGroupedEntriesCount() {
		return cpDefinitionGroupedEntryPersistence.countAll();
	}

	/**
	 * Updates the cp definition grouped entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param cpDefinitionGroupedEntry the cp definition grouped entry
	 * @return the cp definition grouped entry that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CPDefinitionGroupedEntry updateCPDefinitionGroupedEntry(
		CPDefinitionGroupedEntry cpDefinitionGroupedEntry) {

		return cpDefinitionGroupedEntryPersistence.update(
			cpDefinitionGroupedEntry);
	}

	/**
	 * Returns the cp definition grouped entry local service.
	 *
	 * @return the cp definition grouped entry local service
	 */
	public CPDefinitionGroupedEntryLocalService
		getCPDefinitionGroupedEntryLocalService() {

		return cpDefinitionGroupedEntryLocalService;
	}

	/**
	 * Sets the cp definition grouped entry local service.
	 *
	 * @param cpDefinitionGroupedEntryLocalService the cp definition grouped entry local service
	 */
	public void setCPDefinitionGroupedEntryLocalService(
		CPDefinitionGroupedEntryLocalService
			cpDefinitionGroupedEntryLocalService) {

		this.cpDefinitionGroupedEntryLocalService =
			cpDefinitionGroupedEntryLocalService;
	}

	/**
	 * Returns the cp definition grouped entry persistence.
	 *
	 * @return the cp definition grouped entry persistence
	 */
	public CPDefinitionGroupedEntryPersistence
		getCPDefinitionGroupedEntryPersistence() {

		return cpDefinitionGroupedEntryPersistence;
	}

	/**
	 * Sets the cp definition grouped entry persistence.
	 *
	 * @param cpDefinitionGroupedEntryPersistence the cp definition grouped entry persistence
	 */
	public void setCPDefinitionGroupedEntryPersistence(
		CPDefinitionGroupedEntryPersistence
			cpDefinitionGroupedEntryPersistence) {

		this.cpDefinitionGroupedEntryPersistence =
			cpDefinitionGroupedEntryPersistence;
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

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register(
			"com.liferay.commerce.product.type.grouped.model.CPDefinitionGroupedEntry",
			cpDefinitionGroupedEntryLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.commerce.product.type.grouped.model.CPDefinitionGroupedEntry");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CPDefinitionGroupedEntryLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CPDefinitionGroupedEntry.class;
	}

	protected String getModelClassName() {
		return CPDefinitionGroupedEntry.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				cpDefinitionGroupedEntryPersistence.getDataSource();

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

	@BeanReference(type = CPDefinitionGroupedEntryLocalService.class)
	protected CPDefinitionGroupedEntryLocalService
		cpDefinitionGroupedEntryLocalService;

	@BeanReference(type = CPDefinitionGroupedEntryPersistence.class)
	protected CPDefinitionGroupedEntryPersistence
		cpDefinitionGroupedEntryPersistence;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

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

	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry
		persistedModelLocalServiceRegistry;

}