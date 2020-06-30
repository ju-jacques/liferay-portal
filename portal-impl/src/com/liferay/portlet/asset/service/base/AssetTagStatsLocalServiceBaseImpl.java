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

package com.liferay.portlet.asset.service.base;

import com.liferay.asset.kernel.model.AssetTagStats;
import com.liferay.asset.kernel.service.AssetTagStatsLocalService;
import com.liferay.asset.kernel.service.persistence.AssetTagStatsPersistence;
import com.liferay.counter.kernel.service.persistence.CounterPersistence;
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

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the asset tag stats local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portlet.asset.service.impl.AssetTagStatsLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portlet.asset.service.impl.AssetTagStatsLocalServiceImpl
 * @deprecated As of Judson (7.1.x), replaced by {@link
 com.liferay.asset.tag.stats.service.impl.AssetTagStatsLocalServiceImpl}
 * @generated
 */
@Deprecated
public abstract class AssetTagStatsLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AssetTagStatsLocalService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>AssetTagStatsLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.asset.kernel.service.AssetTagStatsLocalServiceUtil</code>.
	 */

	/**
	 * Adds the asset tag stats to the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetTagStats the asset tag stats
	 * @return the asset tag stats that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public AssetTagStats addAssetTagStats(AssetTagStats assetTagStats) {
		assetTagStats.setNew(true);

		return assetTagStatsPersistence.update(assetTagStats);
	}

	/**
	 * Creates a new asset tag stats with the primary key. Does not add the asset tag stats to the database.
	 *
	 * @param tagStatsId the primary key for the new asset tag stats
	 * @return the new asset tag stats
	 */
	@Override
	@Transactional(enabled = false)
	public AssetTagStats createAssetTagStats(long tagStatsId) {
		return assetTagStatsPersistence.create(tagStatsId);
	}

	/**
	 * Deletes the asset tag stats with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param tagStatsId the primary key of the asset tag stats
	 * @return the asset tag stats that was removed
	 * @throws PortalException if a asset tag stats with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public AssetTagStats deleteAssetTagStats(long tagStatsId)
		throws PortalException {

		return assetTagStatsPersistence.remove(tagStatsId);
	}

	/**
	 * Deletes the asset tag stats from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetTagStats the asset tag stats
	 * @return the asset tag stats that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public AssetTagStats deleteAssetTagStats(AssetTagStats assetTagStats) {
		return assetTagStatsPersistence.remove(assetTagStats);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			AssetTagStats.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return assetTagStatsPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portlet.asset.model.impl.AssetTagStatsModelImpl</code>.
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

		return assetTagStatsPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portlet.asset.model.impl.AssetTagStatsModelImpl</code>.
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

		return assetTagStatsPersistence.findWithDynamicQuery(
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
		return assetTagStatsPersistence.countWithDynamicQuery(dynamicQuery);
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

		return assetTagStatsPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public AssetTagStats fetchAssetTagStats(long tagStatsId) {
		return assetTagStatsPersistence.fetchByPrimaryKey(tagStatsId);
	}

	/**
	 * Returns the asset tag stats with the primary key.
	 *
	 * @param tagStatsId the primary key of the asset tag stats
	 * @return the asset tag stats
	 * @throws PortalException if a asset tag stats with the primary key could not be found
	 */
	@Override
	public AssetTagStats getAssetTagStats(long tagStatsId)
		throws PortalException {

		return assetTagStatsPersistence.findByPrimaryKey(tagStatsId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(assetTagStatsLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(AssetTagStats.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("tagStatsId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			assetTagStatsLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(AssetTagStats.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("tagStatsId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(assetTagStatsLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(AssetTagStats.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("tagStatsId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return assetTagStatsLocalService.deleteAssetTagStats(
			(AssetTagStats)persistedModel);
	}

	public BasePersistence<AssetTagStats> getBasePersistence() {
		return assetTagStatsPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return assetTagStatsPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the asset tag statses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portlet.asset.model.impl.AssetTagStatsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset tag statses
	 * @param end the upper bound of the range of asset tag statses (not inclusive)
	 * @return the range of asset tag statses
	 */
	@Override
	public List<AssetTagStats> getAssetTagStatses(int start, int end) {
		return assetTagStatsPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of asset tag statses.
	 *
	 * @return the number of asset tag statses
	 */
	@Override
	public int getAssetTagStatsesCount() {
		return assetTagStatsPersistence.countAll();
	}

	/**
	 * Updates the asset tag stats in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param assetTagStats the asset tag stats
	 * @return the asset tag stats that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public AssetTagStats updateAssetTagStats(AssetTagStats assetTagStats) {
		return assetTagStatsPersistence.update(assetTagStats);
	}

	/**
	 * Returns the asset tag stats local service.
	 *
	 * @return the asset tag stats local service
	 */
	public AssetTagStatsLocalService getAssetTagStatsLocalService() {
		return assetTagStatsLocalService;
	}

	/**
	 * Sets the asset tag stats local service.
	 *
	 * @param assetTagStatsLocalService the asset tag stats local service
	 */
	public void setAssetTagStatsLocalService(
		AssetTagStatsLocalService assetTagStatsLocalService) {

		this.assetTagStatsLocalService = assetTagStatsLocalService;
	}

	/**
	 * Returns the asset tag stats persistence.
	 *
	 * @return the asset tag stats persistence
	 */
	public AssetTagStatsPersistence getAssetTagStatsPersistence() {
		return assetTagStatsPersistence;
	}

	/**
	 * Sets the asset tag stats persistence.
	 *
	 * @param assetTagStatsPersistence the asset tag stats persistence
	 */
	public void setAssetTagStatsPersistence(
		AssetTagStatsPersistence assetTagStatsPersistence) {

		this.assetTagStatsPersistence = assetTagStatsPersistence;
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
	 * Returns the counter persistence.
	 *
	 * @return the counter persistence
	 */
	public CounterPersistence getCounterPersistence() {
		return counterPersistence;
	}

	/**
	 * Sets the counter persistence.
	 *
	 * @param counterPersistence the counter persistence
	 */
	public void setCounterPersistence(CounterPersistence counterPersistence) {
		this.counterPersistence = counterPersistence;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register(
			"com.liferay.asset.kernel.model.AssetTagStats",
			assetTagStatsLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.asset.kernel.model.AssetTagStats");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return AssetTagStatsLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return AssetTagStats.class;
	}

	protected String getModelClassName() {
		return AssetTagStats.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = assetTagStatsPersistence.getDataSource();

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

	@BeanReference(type = AssetTagStatsLocalService.class)
	protected AssetTagStatsLocalService assetTagStatsLocalService;

	@BeanReference(type = AssetTagStatsPersistence.class)
	protected AssetTagStatsPersistence assetTagStatsPersistence;

	@BeanReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@BeanReference(type = CounterPersistence.class)
	protected CounterPersistence counterPersistence;

	@BeanReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry
		persistedModelLocalServiceRegistry;

}