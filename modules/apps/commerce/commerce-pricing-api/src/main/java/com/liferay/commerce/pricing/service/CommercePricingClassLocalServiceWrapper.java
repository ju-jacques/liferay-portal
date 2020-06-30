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

package com.liferay.commerce.pricing.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommercePricingClassLocalService}.
 *
 * @author Riccardo Alberti
 * @see CommercePricingClassLocalService
 * @generated
 */
public class CommercePricingClassLocalServiceWrapper
	implements CommercePricingClassLocalService,
			   ServiceWrapper<CommercePricingClassLocalService> {

	public CommercePricingClassLocalServiceWrapper(
		CommercePricingClassLocalService commercePricingClassLocalService) {

		_commercePricingClassLocalService = commercePricingClassLocalService;
	}

	/**
	 * Adds the commerce pricing class to the database. Also notifies the appropriate model listeners.
	 *
	 * @param commercePricingClass the commerce pricing class
	 * @return the commerce pricing class that was added
	 */
	@Override
	public com.liferay.commerce.pricing.model.CommercePricingClass
		addCommercePricingClass(
			com.liferay.commerce.pricing.model.CommercePricingClass
				commercePricingClass) {

		return _commercePricingClassLocalService.addCommercePricingClass(
			commercePricingClass);
	}

	@Override
	public com.liferay.commerce.pricing.model.CommercePricingClass
			addCommercePricingClass(
				long userId, long groupId, String title, String description,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePricingClassLocalService.addCommercePricingClass(
			userId, groupId, title, description, serviceContext);
	}

	/**
	 * Creates a new commerce pricing class with the primary key. Does not add the commerce pricing class to the database.
	 *
	 * @param commercePricingClassId the primary key for the new commerce pricing class
	 * @return the new commerce pricing class
	 */
	@Override
	public com.liferay.commerce.pricing.model.CommercePricingClass
		createCommercePricingClass(long commercePricingClassId) {

		return _commercePricingClassLocalService.createCommercePricingClass(
			commercePricingClassId);
	}

	/**
	 * Deletes the commerce pricing class from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commercePricingClass the commerce pricing class
	 * @return the commerce pricing class that was removed
	 * @throws PortalException
	 */
	@Override
	public com.liferay.commerce.pricing.model.CommercePricingClass
			deleteCommercePricingClass(
				com.liferay.commerce.pricing.model.CommercePricingClass
					commercePricingClass)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePricingClassLocalService.deleteCommercePricingClass(
			commercePricingClass);
	}

	/**
	 * Deletes the commerce pricing class with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commercePricingClassId the primary key of the commerce pricing class
	 * @return the commerce pricing class that was removed
	 * @throws PortalException if a commerce pricing class with the primary key could not be found
	 */
	@Override
	public com.liferay.commerce.pricing.model.CommercePricingClass
			deleteCommercePricingClass(long commercePricingClassId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePricingClassLocalService.deleteCommercePricingClass(
			commercePricingClassId);
	}

	@Override
	public void deleteCommercePricingClasses(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commercePricingClassLocalService.deleteCommercePricingClasses(
			companyId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePricingClassLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _commercePricingClassLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _commercePricingClassLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.pricing.model.impl.CommercePricingClassModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _commercePricingClassLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.pricing.model.impl.CommercePricingClassModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _commercePricingClassLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _commercePricingClassLocalService.dynamicQueryCount(
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
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _commercePricingClassLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.commerce.pricing.model.CommercePricingClass
		fetchByExternalReferenceCode(
			long companyId, String externalReferenceCode) {

		return _commercePricingClassLocalService.fetchByExternalReferenceCode(
			companyId, externalReferenceCode);
	}

	@Override
	public com.liferay.commerce.pricing.model.CommercePricingClass
		fetchCommercePricingClass(long commercePricingClassId) {

		return _commercePricingClassLocalService.fetchCommercePricingClass(
			commercePricingClassId);
	}

	/**
	 * Returns the commerce pricing class with the matching external reference code and company.
	 *
	 * @param companyId the primary key of the company
	 * @param externalReferenceCode the commerce pricing class's external reference code
	 * @return the matching commerce pricing class, or <code>null</code> if a matching commerce pricing class could not be found
	 */
	@Override
	public com.liferay.commerce.pricing.model.CommercePricingClass
		fetchCommercePricingClassByReferenceCode(
			long companyId, String externalReferenceCode) {

		return _commercePricingClassLocalService.
			fetchCommercePricingClassByReferenceCode(
				companyId, externalReferenceCode);
	}

	/**
	 * Returns the commerce pricing class matching the UUID and group.
	 *
	 * @param uuid the commerce pricing class's UUID
	 * @param groupId the primary key of the group
	 * @return the matching commerce pricing class, or <code>null</code> if a matching commerce pricing class could not be found
	 */
	@Override
	public com.liferay.commerce.pricing.model.CommercePricingClass
		fetchCommercePricingClassByUuidAndGroupId(String uuid, long groupId) {

		return _commercePricingClassLocalService.
			fetchCommercePricingClassByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _commercePricingClassLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the commerce pricing class with the primary key.
	 *
	 * @param commercePricingClassId the primary key of the commerce pricing class
	 * @return the commerce pricing class
	 * @throws PortalException if a commerce pricing class with the primary key could not be found
	 */
	@Override
	public com.liferay.commerce.pricing.model.CommercePricingClass
			getCommercePricingClass(long commercePricingClassId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePricingClassLocalService.getCommercePricingClass(
			commercePricingClassId);
	}

	@Override
	public long[] getCommercePricingClassByCPDefinition(long cpDefinitionId) {
		return _commercePricingClassLocalService.
			getCommercePricingClassByCPDefinition(cpDefinitionId);
	}

	/**
	 * Returns the commerce pricing class matching the UUID and group.
	 *
	 * @param uuid the commerce pricing class's UUID
	 * @param groupId the primary key of the group
	 * @return the matching commerce pricing class
	 * @throws PortalException if a matching commerce pricing class could not be found
	 */
	@Override
	public com.liferay.commerce.pricing.model.CommercePricingClass
			getCommercePricingClassByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePricingClassLocalService.
			getCommercePricingClassByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Returns a range of all the commerce pricing classes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.pricing.model.impl.CommercePricingClassModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce pricing classes
	 * @param end the upper bound of the range of commerce pricing classes (not inclusive)
	 * @return the range of commerce pricing classes
	 */
	@Override
	public java.util.List
		<com.liferay.commerce.pricing.model.CommercePricingClass>
			getCommercePricingClasses(int start, int end) {

		return _commercePricingClassLocalService.getCommercePricingClasses(
			start, end);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.pricing.model.CommercePricingClass>
			getCommercePricingClasses(
				long companyId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.pricing.model.CommercePricingClass>
						orderByComparator) {

		return _commercePricingClassLocalService.getCommercePricingClasses(
			companyId, start, end, orderByComparator);
	}

	/**
	 * Returns all the commerce pricing classes matching the UUID and company.
	 *
	 * @param uuid the UUID of the commerce pricing classes
	 * @param companyId the primary key of the company
	 * @return the matching commerce pricing classes, or an empty list if no matches were found
	 */
	@Override
	public java.util.List
		<com.liferay.commerce.pricing.model.CommercePricingClass>
			getCommercePricingClassesByUuidAndCompanyId(
				String uuid, long companyId) {

		return _commercePricingClassLocalService.
			getCommercePricingClassesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of commerce pricing classes matching the UUID and company.
	 *
	 * @param uuid the UUID of the commerce pricing classes
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of commerce pricing classes
	 * @param end the upper bound of the range of commerce pricing classes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching commerce pricing classes, or an empty list if no matches were found
	 */
	@Override
	public java.util.List
		<com.liferay.commerce.pricing.model.CommercePricingClass>
			getCommercePricingClassesByUuidAndCompanyId(
				String uuid, long companyId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.pricing.model.CommercePricingClass>
						orderByComparator) {

		return _commercePricingClassLocalService.
			getCommercePricingClassesByUuidAndCompanyId(
				uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of commerce pricing classes.
	 *
	 * @return the number of commerce pricing classes
	 */
	@Override
	public int getCommercePricingClassesCount() {
		return _commercePricingClassLocalService.
			getCommercePricingClassesCount();
	}

	@Override
	public int getCommercePricingClassesCount(long companyId) {
		return _commercePricingClassLocalService.getCommercePricingClassesCount(
			companyId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _commercePricingClassLocalService.
			getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _commercePricingClassLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commercePricingClassLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePricingClassLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Updates the commerce pricing class in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param commercePricingClass the commerce pricing class
	 * @return the commerce pricing class that was updated
	 */
	@Override
	public com.liferay.commerce.pricing.model.CommercePricingClass
		updateCommercePricingClass(
			com.liferay.commerce.pricing.model.CommercePricingClass
				commercePricingClass) {

		return _commercePricingClassLocalService.updateCommercePricingClass(
			commercePricingClass);
	}

	@Override
	public com.liferay.commerce.pricing.model.CommercePricingClass
			updateCommercePricingClass(
				long commercePricingClassId, long userId, long groupId,
				String title, String description,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePricingClassLocalService.updateCommercePricingClass(
			commercePricingClassId, userId, groupId, title, description,
			serviceContext);
	}

	@Override
	public com.liferay.commerce.pricing.model.CommercePricingClass
			upsertCommercePricingClass(
				long commercePricingClassId, long userId, long groupId,
				String title, String description, String externalReferenceCode,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePricingClassLocalService.upsertCommercePricingClass(
			commercePricingClassId, userId, groupId, title, description,
			externalReferenceCode, serviceContext);
	}

	@Override
	public CommercePricingClassLocalService getWrappedService() {
		return _commercePricingClassLocalService;
	}

	@Override
	public void setWrappedService(
		CommercePricingClassLocalService commercePricingClassLocalService) {

		_commercePricingClassLocalService = commercePricingClassLocalService;
	}

	private CommercePricingClassLocalService _commercePricingClassLocalService;

}