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

package com.liferay.document.library.service.base;

import com.liferay.document.library.model.DLFileVersionPreview;
import com.liferay.document.library.service.DLFileVersionPreviewLocalService;
import com.liferay.document.library.service.persistence.DLFileVersionPreviewPersistence;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
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
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the dl file version preview local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.document.library.service.impl.DLFileVersionPreviewLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.document.library.service.impl.DLFileVersionPreviewLocalServiceImpl
 * @generated
 */
public abstract class DLFileVersionPreviewLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements DLFileVersionPreviewLocalService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>DLFileVersionPreviewLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.document.library.service.DLFileVersionPreviewLocalServiceUtil</code>.
	 */

	/**
	 * Adds the dl file version preview to the database. Also notifies the appropriate model listeners.
	 *
	 * @param dlFileVersionPreview the dl file version preview
	 * @return the dl file version preview that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DLFileVersionPreview addDLFileVersionPreview(
		DLFileVersionPreview dlFileVersionPreview) {

		dlFileVersionPreview.setNew(true);

		return dlFileVersionPreviewPersistence.update(dlFileVersionPreview);
	}

	/**
	 * Creates a new dl file version preview with the primary key. Does not add the dl file version preview to the database.
	 *
	 * @param dlFileVersionPreviewId the primary key for the new dl file version preview
	 * @return the new dl file version preview
	 */
	@Override
	@Transactional(enabled = false)
	public DLFileVersionPreview createDLFileVersionPreview(
		long dlFileVersionPreviewId) {

		return dlFileVersionPreviewPersistence.create(dlFileVersionPreviewId);
	}

	/**
	 * Deletes the dl file version preview with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dlFileVersionPreviewId the primary key of the dl file version preview
	 * @return the dl file version preview that was removed
	 * @throws PortalException if a dl file version preview with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DLFileVersionPreview deleteDLFileVersionPreview(
			long dlFileVersionPreviewId)
		throws PortalException {

		return dlFileVersionPreviewPersistence.remove(dlFileVersionPreviewId);
	}

	/**
	 * Deletes the dl file version preview from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dlFileVersionPreview the dl file version preview
	 * @return the dl file version preview that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DLFileVersionPreview deleteDLFileVersionPreview(
		DLFileVersionPreview dlFileVersionPreview) {

		return dlFileVersionPreviewPersistence.remove(dlFileVersionPreview);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			DLFileVersionPreview.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return dlFileVersionPreviewPersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.document.library.model.impl.DLFileVersionPreviewModelImpl</code>.
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

		return dlFileVersionPreviewPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.document.library.model.impl.DLFileVersionPreviewModelImpl</code>.
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

		return dlFileVersionPreviewPersistence.findWithDynamicQuery(
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
		return dlFileVersionPreviewPersistence.countWithDynamicQuery(
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

		return dlFileVersionPreviewPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public DLFileVersionPreview fetchDLFileVersionPreview(
		long dlFileVersionPreviewId) {

		return dlFileVersionPreviewPersistence.fetchByPrimaryKey(
			dlFileVersionPreviewId);
	}

	/**
	 * Returns the dl file version preview with the primary key.
	 *
	 * @param dlFileVersionPreviewId the primary key of the dl file version preview
	 * @return the dl file version preview
	 * @throws PortalException if a dl file version preview with the primary key could not be found
	 */
	@Override
	public DLFileVersionPreview getDLFileVersionPreview(
			long dlFileVersionPreviewId)
		throws PortalException {

		return dlFileVersionPreviewPersistence.findByPrimaryKey(
			dlFileVersionPreviewId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			dlFileVersionPreviewLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(DLFileVersionPreview.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"dlFileVersionPreviewId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			dlFileVersionPreviewLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(
			DLFileVersionPreview.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"dlFileVersionPreviewId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			dlFileVersionPreviewLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(DLFileVersionPreview.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"dlFileVersionPreviewId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return dlFileVersionPreviewLocalService.deleteDLFileVersionPreview(
			(DLFileVersionPreview)persistedModel);
	}

	public BasePersistence<DLFileVersionPreview> getBasePersistence() {
		return dlFileVersionPreviewPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return dlFileVersionPreviewPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the dl file version previews.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.document.library.model.impl.DLFileVersionPreviewModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of dl file version previews
	 * @param end the upper bound of the range of dl file version previews (not inclusive)
	 * @return the range of dl file version previews
	 */
	@Override
	public List<DLFileVersionPreview> getDLFileVersionPreviews(
		int start, int end) {

		return dlFileVersionPreviewPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of dl file version previews.
	 *
	 * @return the number of dl file version previews
	 */
	@Override
	public int getDLFileVersionPreviewsCount() {
		return dlFileVersionPreviewPersistence.countAll();
	}

	/**
	 * Updates the dl file version preview in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param dlFileVersionPreview the dl file version preview
	 * @return the dl file version preview that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DLFileVersionPreview updateDLFileVersionPreview(
		DLFileVersionPreview dlFileVersionPreview) {

		return dlFileVersionPreviewPersistence.update(dlFileVersionPreview);
	}

	/**
	 * Returns the dl file version preview local service.
	 *
	 * @return the dl file version preview local service
	 */
	public DLFileVersionPreviewLocalService
		getDLFileVersionPreviewLocalService() {

		return dlFileVersionPreviewLocalService;
	}

	/**
	 * Sets the dl file version preview local service.
	 *
	 * @param dlFileVersionPreviewLocalService the dl file version preview local service
	 */
	public void setDLFileVersionPreviewLocalService(
		DLFileVersionPreviewLocalService dlFileVersionPreviewLocalService) {

		this.dlFileVersionPreviewLocalService =
			dlFileVersionPreviewLocalService;
	}

	/**
	 * Returns the dl file version preview persistence.
	 *
	 * @return the dl file version preview persistence
	 */
	public DLFileVersionPreviewPersistence
		getDLFileVersionPreviewPersistence() {

		return dlFileVersionPreviewPersistence;
	}

	/**
	 * Sets the dl file version preview persistence.
	 *
	 * @param dlFileVersionPreviewPersistence the dl file version preview persistence
	 */
	public void setDLFileVersionPreviewPersistence(
		DLFileVersionPreviewPersistence dlFileVersionPreviewPersistence) {

		this.dlFileVersionPreviewPersistence = dlFileVersionPreviewPersistence;
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

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register(
			"com.liferay.document.library.model.DLFileVersionPreview",
			dlFileVersionPreviewLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.document.library.model.DLFileVersionPreview");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return DLFileVersionPreviewLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return DLFileVersionPreview.class;
	}

	protected String getModelClassName() {
		return DLFileVersionPreview.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				dlFileVersionPreviewPersistence.getDataSource();

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

	@BeanReference(type = DLFileVersionPreviewLocalService.class)
	protected DLFileVersionPreviewLocalService dlFileVersionPreviewLocalService;

	@BeanReference(type = DLFileVersionPreviewPersistence.class)
	protected DLFileVersionPreviewPersistence dlFileVersionPreviewPersistence;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry
		persistedModelLocalServiceRegistry;

}