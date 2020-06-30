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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for CommercePricingClassCPDefinitionRel. This utility wraps
 * <code>com.liferay.commerce.pricing.service.impl.CommercePricingClassCPDefinitionRelLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Riccardo Alberti
 * @see CommercePricingClassCPDefinitionRelLocalService
 * @generated
 */
public class CommercePricingClassCPDefinitionRelLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.pricing.service.impl.CommercePricingClassCPDefinitionRelLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the commerce pricing class cp definition rel to the database. Also notifies the appropriate model listeners.
	 *
	 * @param commercePricingClassCPDefinitionRel the commerce pricing class cp definition rel
	 * @return the commerce pricing class cp definition rel that was added
	 */
	public static
		com.liferay.commerce.pricing.model.CommercePricingClassCPDefinitionRel
			addCommercePricingClassCPDefinitionRel(
				com.liferay.commerce.pricing.model.
					CommercePricingClassCPDefinitionRel
						commercePricingClassCPDefinitionRel) {

		return getService().addCommercePricingClassCPDefinitionRel(
			commercePricingClassCPDefinitionRel);
	}

	public static
		com.liferay.commerce.pricing.model.CommercePricingClassCPDefinitionRel
				addCommercePricingClassCPDefinitionRel(
					long commercePricingClassId, long cpDefinitionId,
					com.liferay.portal.kernel.service.ServiceContext
						serviceContext)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCommercePricingClassCPDefinitionRel(
			commercePricingClassId, cpDefinitionId, serviceContext);
	}

	/**
	 * Creates a new commerce pricing class cp definition rel with the primary key. Does not add the commerce pricing class cp definition rel to the database.
	 *
	 * @param CommercePricingClassCPDefinitionRelId the primary key for the new commerce pricing class cp definition rel
	 * @return the new commerce pricing class cp definition rel
	 */
	public static
		com.liferay.commerce.pricing.model.CommercePricingClassCPDefinitionRel
			createCommercePricingClassCPDefinitionRel(
				long CommercePricingClassCPDefinitionRelId) {

		return getService().createCommercePricingClassCPDefinitionRel(
			CommercePricingClassCPDefinitionRelId);
	}

	/**
	 * Deletes the commerce pricing class cp definition rel from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commercePricingClassCPDefinitionRel the commerce pricing class cp definition rel
	 * @return the commerce pricing class cp definition rel that was removed
	 * @throws PortalException
	 */
	public static
		com.liferay.commerce.pricing.model.CommercePricingClassCPDefinitionRel
				deleteCommercePricingClassCPDefinitionRel(
					com.liferay.commerce.pricing.model.
						CommercePricingClassCPDefinitionRel
							commercePricingClassCPDefinitionRel)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCommercePricingClassCPDefinitionRel(
			commercePricingClassCPDefinitionRel);
	}

	/**
	 * Deletes the commerce pricing class cp definition rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param CommercePricingClassCPDefinitionRelId the primary key of the commerce pricing class cp definition rel
	 * @return the commerce pricing class cp definition rel that was removed
	 * @throws PortalException if a commerce pricing class cp definition rel with the primary key could not be found
	 */
	public static
		com.liferay.commerce.pricing.model.CommercePricingClassCPDefinitionRel
				deleteCommercePricingClassCPDefinitionRel(
					long CommercePricingClassCPDefinitionRelId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCommercePricingClassCPDefinitionRel(
			CommercePricingClassCPDefinitionRelId);
	}

	public static void deleteCommercePricingClassCPDefinitionRels(
			long commercePricingClassId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteCommercePricingClassCPDefinitionRels(
			commercePricingClassId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.pricing.model.impl.CommercePricingClassCPDefinitionRelModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.pricing.model.impl.CommercePricingClassCPDefinitionRelModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static
		com.liferay.commerce.pricing.model.CommercePricingClassCPDefinitionRel
			fetchCommercePricingClassCPDefinitionRel(
				long CommercePricingClassCPDefinitionRelId) {

		return getService().fetchCommercePricingClassCPDefinitionRel(
			CommercePricingClassCPDefinitionRelId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static java.util.List
		<com.liferay.commerce.pricing.model.CommercePricingClassCPDefinitionRel>
			getCommercePricingClassByCPDefinitionId(long cpDefinitionId) {

		return getService().getCommercePricingClassByCPDefinitionId(
			cpDefinitionId);
	}

	/**
	 * Returns the commerce pricing class cp definition rel with the primary key.
	 *
	 * @param CommercePricingClassCPDefinitionRelId the primary key of the commerce pricing class cp definition rel
	 * @return the commerce pricing class cp definition rel
	 * @throws PortalException if a commerce pricing class cp definition rel with the primary key could not be found
	 */
	public static
		com.liferay.commerce.pricing.model.CommercePricingClassCPDefinitionRel
				getCommercePricingClassCPDefinitionRel(
					long CommercePricingClassCPDefinitionRelId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCommercePricingClassCPDefinitionRel(
			CommercePricingClassCPDefinitionRelId);
	}

	/**
	 * Returns a range of all the commerce pricing class cp definition rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.pricing.model.impl.CommercePricingClassCPDefinitionRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce pricing class cp definition rels
	 * @param end the upper bound of the range of commerce pricing class cp definition rels (not inclusive)
	 * @return the range of commerce pricing class cp definition rels
	 */
	public static java.util.List
		<com.liferay.commerce.pricing.model.CommercePricingClassCPDefinitionRel>
			getCommercePricingClassCPDefinitionRels(int start, int end) {

		return getService().getCommercePricingClassCPDefinitionRels(start, end);
	}

	public static java.util.List
		<com.liferay.commerce.pricing.model.CommercePricingClassCPDefinitionRel>
			getCommercePricingClassCPDefinitionRels(
				long commercePricingClassId) {

		return getService().getCommercePricingClassCPDefinitionRels(
			commercePricingClassId);
	}

	public static java.util.List
		<com.liferay.commerce.pricing.model.CommercePricingClassCPDefinitionRel>
			getCommercePricingClassCPDefinitionRels(
				long commercePricingClassId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.pricing.model.
						CommercePricingClassCPDefinitionRel>
							orderByComparator) {

		return getService().getCommercePricingClassCPDefinitionRels(
			commercePricingClassId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of commerce pricing class cp definition rels.
	 *
	 * @return the number of commerce pricing class cp definition rels
	 */
	public static int getCommercePricingClassCPDefinitionRelsCount() {
		return getService().getCommercePricingClassCPDefinitionRelsCount();
	}

	public static int getCommercePricingClassCPDefinitionRelsCount(
		long commercePricingClassId) {

		return getService().getCommercePricingClassCPDefinitionRelsCount(
			commercePricingClassId);
	}

	public static long[] getCPDefinitionIds(long commercePricingClassId) {
		return getService().getCPDefinitionIds(commercePricingClassId);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the commerce pricing class cp definition rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param commercePricingClassCPDefinitionRel the commerce pricing class cp definition rel
	 * @return the commerce pricing class cp definition rel that was updated
	 */
	public static
		com.liferay.commerce.pricing.model.CommercePricingClassCPDefinitionRel
			updateCommercePricingClassCPDefinitionRel(
				com.liferay.commerce.pricing.model.
					CommercePricingClassCPDefinitionRel
						commercePricingClassCPDefinitionRel) {

		return getService().updateCommercePricingClassCPDefinitionRel(
			commercePricingClassCPDefinitionRel);
	}

	public static CommercePricingClassCPDefinitionRelLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CommercePricingClassCPDefinitionRelLocalService,
		 CommercePricingClassCPDefinitionRelLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CommercePricingClassCPDefinitionRelLocalService.class);

		ServiceTracker
			<CommercePricingClassCPDefinitionRelLocalService,
			 CommercePricingClassCPDefinitionRelLocalService> serviceTracker =
				new ServiceTracker
					<CommercePricingClassCPDefinitionRelLocalService,
					 CommercePricingClassCPDefinitionRelLocalService>(
						 bundle.getBundleContext(),
						 CommercePricingClassCPDefinitionRelLocalService.class,
						 null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}