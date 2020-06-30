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

package com.liferay.commerce.product.type.virtual.order.service.base;

import com.liferay.commerce.product.type.virtual.order.model.CommerceVirtualOrderItem;
import com.liferay.commerce.product.type.virtual.order.service.CommerceVirtualOrderItemLocalService;
import com.liferay.commerce.product.type.virtual.order.service.persistence.CommerceVirtualOrderItemFinder;
import com.liferay.commerce.product.type.virtual.order.service.persistence.CommerceVirtualOrderItemPersistence;
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
 * Provides the base implementation for the commerce virtual order item local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.commerce.product.type.virtual.order.service.impl.CommerceVirtualOrderItemLocalServiceImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see com.liferay.commerce.product.type.virtual.order.service.impl.CommerceVirtualOrderItemLocalServiceImpl
 * @generated
 */
public abstract class CommerceVirtualOrderItemLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements CommerceVirtualOrderItemLocalService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>CommerceVirtualOrderItemLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.commerce.product.type.virtual.order.service.CommerceVirtualOrderItemLocalServiceUtil</code>.
	 */

	/**
	 * Adds the commerce virtual order item to the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceVirtualOrderItem the commerce virtual order item
	 * @return the commerce virtual order item that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceVirtualOrderItem addCommerceVirtualOrderItem(
		CommerceVirtualOrderItem commerceVirtualOrderItem) {

		commerceVirtualOrderItem.setNew(true);

		return commerceVirtualOrderItemPersistence.update(
			commerceVirtualOrderItem);
	}

	/**
	 * Creates a new commerce virtual order item with the primary key. Does not add the commerce virtual order item to the database.
	 *
	 * @param commerceVirtualOrderItemId the primary key for the new commerce virtual order item
	 * @return the new commerce virtual order item
	 */
	@Override
	@Transactional(enabled = false)
	public CommerceVirtualOrderItem createCommerceVirtualOrderItem(
		long commerceVirtualOrderItemId) {

		return commerceVirtualOrderItemPersistence.create(
			commerceVirtualOrderItemId);
	}

	/**
	 * Deletes the commerce virtual order item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceVirtualOrderItemId the primary key of the commerce virtual order item
	 * @return the commerce virtual order item that was removed
	 * @throws PortalException if a commerce virtual order item with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CommerceVirtualOrderItem deleteCommerceVirtualOrderItem(
			long commerceVirtualOrderItemId)
		throws PortalException {

		return commerceVirtualOrderItemPersistence.remove(
			commerceVirtualOrderItemId);
	}

	/**
	 * Deletes the commerce virtual order item from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceVirtualOrderItem the commerce virtual order item
	 * @return the commerce virtual order item that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CommerceVirtualOrderItem deleteCommerceVirtualOrderItem(
		CommerceVirtualOrderItem commerceVirtualOrderItem) {

		return commerceVirtualOrderItemPersistence.remove(
			commerceVirtualOrderItem);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			CommerceVirtualOrderItem.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return commerceVirtualOrderItemPersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.type.virtual.order.model.impl.CommerceVirtualOrderItemModelImpl</code>.
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

		return commerceVirtualOrderItemPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.type.virtual.order.model.impl.CommerceVirtualOrderItemModelImpl</code>.
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

		return commerceVirtualOrderItemPersistence.findWithDynamicQuery(
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
		return commerceVirtualOrderItemPersistence.countWithDynamicQuery(
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

		return commerceVirtualOrderItemPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public CommerceVirtualOrderItem fetchCommerceVirtualOrderItem(
		long commerceVirtualOrderItemId) {

		return commerceVirtualOrderItemPersistence.fetchByPrimaryKey(
			commerceVirtualOrderItemId);
	}

	/**
	 * Returns the commerce virtual order item matching the UUID and group.
	 *
	 * @param uuid the commerce virtual order item's UUID
	 * @param groupId the primary key of the group
	 * @return the matching commerce virtual order item, or <code>null</code> if a matching commerce virtual order item could not be found
	 */
	@Override
	public CommerceVirtualOrderItem
		fetchCommerceVirtualOrderItemByUuidAndGroupId(
			String uuid, long groupId) {

		return commerceVirtualOrderItemPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the commerce virtual order item with the primary key.
	 *
	 * @param commerceVirtualOrderItemId the primary key of the commerce virtual order item
	 * @return the commerce virtual order item
	 * @throws PortalException if a commerce virtual order item with the primary key could not be found
	 */
	@Override
	public CommerceVirtualOrderItem getCommerceVirtualOrderItem(
			long commerceVirtualOrderItemId)
		throws PortalException {

		return commerceVirtualOrderItemPersistence.findByPrimaryKey(
			commerceVirtualOrderItemId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			commerceVirtualOrderItemLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CommerceVirtualOrderItem.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"commerceVirtualOrderItemId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			commerceVirtualOrderItemLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(
			CommerceVirtualOrderItem.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"commerceVirtualOrderItemId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			commerceVirtualOrderItemLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CommerceVirtualOrderItem.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"commerceVirtualOrderItemId");
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
				<CommerceVirtualOrderItem>() {

				@Override
				public void performAction(
						CommerceVirtualOrderItem commerceVirtualOrderItem)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, commerceVirtualOrderItem);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(
					CommerceVirtualOrderItem.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return commerceVirtualOrderItemLocalService.
			deleteCommerceVirtualOrderItem(
				(CommerceVirtualOrderItem)persistedModel);
	}

	public BasePersistence<CommerceVirtualOrderItem> getBasePersistence() {
		return commerceVirtualOrderItemPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return commerceVirtualOrderItemPersistence.findByPrimaryKey(
			primaryKeyObj);
	}

	/**
	 * Returns all the commerce virtual order items matching the UUID and company.
	 *
	 * @param uuid the UUID of the commerce virtual order items
	 * @param companyId the primary key of the company
	 * @return the matching commerce virtual order items, or an empty list if no matches were found
	 */
	@Override
	public List<CommerceVirtualOrderItem>
		getCommerceVirtualOrderItemsByUuidAndCompanyId(
			String uuid, long companyId) {

		return commerceVirtualOrderItemPersistence.findByUuid_C(
			uuid, companyId);
	}

	/**
	 * Returns a range of commerce virtual order items matching the UUID and company.
	 *
	 * @param uuid the UUID of the commerce virtual order items
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of commerce virtual order items
	 * @param end the upper bound of the range of commerce virtual order items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching commerce virtual order items, or an empty list if no matches were found
	 */
	@Override
	public List<CommerceVirtualOrderItem>
		getCommerceVirtualOrderItemsByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			OrderByComparator<CommerceVirtualOrderItem> orderByComparator) {

		return commerceVirtualOrderItemPersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the commerce virtual order item matching the UUID and group.
	 *
	 * @param uuid the commerce virtual order item's UUID
	 * @param groupId the primary key of the group
	 * @return the matching commerce virtual order item
	 * @throws PortalException if a matching commerce virtual order item could not be found
	 */
	@Override
	public CommerceVirtualOrderItem getCommerceVirtualOrderItemByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException {

		return commerceVirtualOrderItemPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the commerce virtual order items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.product.type.virtual.order.model.impl.CommerceVirtualOrderItemModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce virtual order items
	 * @param end the upper bound of the range of commerce virtual order items (not inclusive)
	 * @return the range of commerce virtual order items
	 */
	@Override
	public List<CommerceVirtualOrderItem> getCommerceVirtualOrderItems(
		int start, int end) {

		return commerceVirtualOrderItemPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of commerce virtual order items.
	 *
	 * @return the number of commerce virtual order items
	 */
	@Override
	public int getCommerceVirtualOrderItemsCount() {
		return commerceVirtualOrderItemPersistence.countAll();
	}

	/**
	 * Updates the commerce virtual order item in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param commerceVirtualOrderItem the commerce virtual order item
	 * @return the commerce virtual order item that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceVirtualOrderItem updateCommerceVirtualOrderItem(
		CommerceVirtualOrderItem commerceVirtualOrderItem) {

		return commerceVirtualOrderItemPersistence.update(
			commerceVirtualOrderItem);
	}

	/**
	 * Returns the commerce virtual order item local service.
	 *
	 * @return the commerce virtual order item local service
	 */
	public CommerceVirtualOrderItemLocalService
		getCommerceVirtualOrderItemLocalService() {

		return commerceVirtualOrderItemLocalService;
	}

	/**
	 * Sets the commerce virtual order item local service.
	 *
	 * @param commerceVirtualOrderItemLocalService the commerce virtual order item local service
	 */
	public void setCommerceVirtualOrderItemLocalService(
		CommerceVirtualOrderItemLocalService
			commerceVirtualOrderItemLocalService) {

		this.commerceVirtualOrderItemLocalService =
			commerceVirtualOrderItemLocalService;
	}

	/**
	 * Returns the commerce virtual order item persistence.
	 *
	 * @return the commerce virtual order item persistence
	 */
	public CommerceVirtualOrderItemPersistence
		getCommerceVirtualOrderItemPersistence() {

		return commerceVirtualOrderItemPersistence;
	}

	/**
	 * Sets the commerce virtual order item persistence.
	 *
	 * @param commerceVirtualOrderItemPersistence the commerce virtual order item persistence
	 */
	public void setCommerceVirtualOrderItemPersistence(
		CommerceVirtualOrderItemPersistence
			commerceVirtualOrderItemPersistence) {

		this.commerceVirtualOrderItemPersistence =
			commerceVirtualOrderItemPersistence;
	}

	/**
	 * Returns the commerce virtual order item finder.
	 *
	 * @return the commerce virtual order item finder
	 */
	public CommerceVirtualOrderItemFinder getCommerceVirtualOrderItemFinder() {
		return commerceVirtualOrderItemFinder;
	}

	/**
	 * Sets the commerce virtual order item finder.
	 *
	 * @param commerceVirtualOrderItemFinder the commerce virtual order item finder
	 */
	public void setCommerceVirtualOrderItemFinder(
		CommerceVirtualOrderItemFinder commerceVirtualOrderItemFinder) {

		this.commerceVirtualOrderItemFinder = commerceVirtualOrderItemFinder;
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
			"com.liferay.commerce.product.type.virtual.order.model.CommerceVirtualOrderItem",
			commerceVirtualOrderItemLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.commerce.product.type.virtual.order.model.CommerceVirtualOrderItem");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CommerceVirtualOrderItemLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CommerceVirtualOrderItem.class;
	}

	protected String getModelClassName() {
		return CommerceVirtualOrderItem.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				commerceVirtualOrderItemPersistence.getDataSource();

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

	@BeanReference(type = CommerceVirtualOrderItemLocalService.class)
	protected CommerceVirtualOrderItemLocalService
		commerceVirtualOrderItemLocalService;

	@BeanReference(type = CommerceVirtualOrderItemPersistence.class)
	protected CommerceVirtualOrderItemPersistence
		commerceVirtualOrderItemPersistence;

	@BeanReference(type = CommerceVirtualOrderItemFinder.class)
	protected CommerceVirtualOrderItemFinder commerceVirtualOrderItemFinder;

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

	@ServiceReference(
		type = com.liferay.document.library.kernel.service.DLAppLocalService.class
	)
	protected com.liferay.document.library.kernel.service.DLAppLocalService
		dlAppLocalService;

	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry
		persistedModelLocalServiceRegistry;

}