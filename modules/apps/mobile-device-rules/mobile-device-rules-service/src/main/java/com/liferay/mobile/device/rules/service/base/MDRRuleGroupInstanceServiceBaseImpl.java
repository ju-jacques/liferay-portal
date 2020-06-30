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

package com.liferay.mobile.device.rules.service.base;

import com.liferay.mobile.device.rules.model.MDRRuleGroupInstance;
import com.liferay.mobile.device.rules.service.MDRRuleGroupInstanceService;
import com.liferay.mobile.device.rules.service.persistence.MDRActionPersistence;
import com.liferay.mobile.device.rules.service.persistence.MDRRuleGroupFinder;
import com.liferay.mobile.device.rules.service.persistence.MDRRuleGroupInstancePersistence;
import com.liferay.mobile.device.rules.service.persistence.MDRRuleGroupPersistence;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.LayoutPersistence;
import com.liferay.portal.kernel.service.persistence.LayoutSetPersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the mdr rule group instance remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.mobile.device.rules.service.impl.MDRRuleGroupInstanceServiceImpl}.
 * </p>
 *
 * @author Edward C. Han
 * @see com.liferay.mobile.device.rules.service.impl.MDRRuleGroupInstanceServiceImpl
 * @generated
 */
public abstract class MDRRuleGroupInstanceServiceBaseImpl
	extends BaseServiceImpl
	implements IdentifiableOSGiService, MDRRuleGroupInstanceService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>MDRRuleGroupInstanceService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.mobile.device.rules.service.MDRRuleGroupInstanceServiceUtil</code>.
	 */

	/**
	 * Returns the mdr rule group instance local service.
	 *
	 * @return the mdr rule group instance local service
	 */
	public
		com.liferay.mobile.device.rules.service.MDRRuleGroupInstanceLocalService
			getMDRRuleGroupInstanceLocalService() {

		return mdrRuleGroupInstanceLocalService;
	}

	/**
	 * Sets the mdr rule group instance local service.
	 *
	 * @param mdrRuleGroupInstanceLocalService the mdr rule group instance local service
	 */
	public void setMDRRuleGroupInstanceLocalService(
		com.liferay.mobile.device.rules.service.MDRRuleGroupInstanceLocalService
			mdrRuleGroupInstanceLocalService) {

		this.mdrRuleGroupInstanceLocalService =
			mdrRuleGroupInstanceLocalService;
	}

	/**
	 * Returns the mdr rule group instance remote service.
	 *
	 * @return the mdr rule group instance remote service
	 */
	public MDRRuleGroupInstanceService getMDRRuleGroupInstanceService() {
		return mdrRuleGroupInstanceService;
	}

	/**
	 * Sets the mdr rule group instance remote service.
	 *
	 * @param mdrRuleGroupInstanceService the mdr rule group instance remote service
	 */
	public void setMDRRuleGroupInstanceService(
		MDRRuleGroupInstanceService mdrRuleGroupInstanceService) {

		this.mdrRuleGroupInstanceService = mdrRuleGroupInstanceService;
	}

	/**
	 * Returns the mdr rule group instance persistence.
	 *
	 * @return the mdr rule group instance persistence
	 */
	public MDRRuleGroupInstancePersistence
		getMDRRuleGroupInstancePersistence() {

		return mdrRuleGroupInstancePersistence;
	}

	/**
	 * Sets the mdr rule group instance persistence.
	 *
	 * @param mdrRuleGroupInstancePersistence the mdr rule group instance persistence
	 */
	public void setMDRRuleGroupInstancePersistence(
		MDRRuleGroupInstancePersistence mdrRuleGroupInstancePersistence) {

		this.mdrRuleGroupInstancePersistence = mdrRuleGroupInstancePersistence;
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
	 * Returns the mdr action local service.
	 *
	 * @return the mdr action local service
	 */
	public com.liferay.mobile.device.rules.service.MDRActionLocalService
		getMDRActionLocalService() {

		return mdrActionLocalService;
	}

	/**
	 * Sets the mdr action local service.
	 *
	 * @param mdrActionLocalService the mdr action local service
	 */
	public void setMDRActionLocalService(
		com.liferay.mobile.device.rules.service.MDRActionLocalService
			mdrActionLocalService) {

		this.mdrActionLocalService = mdrActionLocalService;
	}

	/**
	 * Returns the mdr action remote service.
	 *
	 * @return the mdr action remote service
	 */
	public com.liferay.mobile.device.rules.service.MDRActionService
		getMDRActionService() {

		return mdrActionService;
	}

	/**
	 * Sets the mdr action remote service.
	 *
	 * @param mdrActionService the mdr action remote service
	 */
	public void setMDRActionService(
		com.liferay.mobile.device.rules.service.MDRActionService
			mdrActionService) {

		this.mdrActionService = mdrActionService;
	}

	/**
	 * Returns the mdr action persistence.
	 *
	 * @return the mdr action persistence
	 */
	public MDRActionPersistence getMDRActionPersistence() {
		return mdrActionPersistence;
	}

	/**
	 * Sets the mdr action persistence.
	 *
	 * @param mdrActionPersistence the mdr action persistence
	 */
	public void setMDRActionPersistence(
		MDRActionPersistence mdrActionPersistence) {

		this.mdrActionPersistence = mdrActionPersistence;
	}

	/**
	 * Returns the mdr rule group local service.
	 *
	 * @return the mdr rule group local service
	 */
	public com.liferay.mobile.device.rules.service.MDRRuleGroupLocalService
		getMDRRuleGroupLocalService() {

		return mdrRuleGroupLocalService;
	}

	/**
	 * Sets the mdr rule group local service.
	 *
	 * @param mdrRuleGroupLocalService the mdr rule group local service
	 */
	public void setMDRRuleGroupLocalService(
		com.liferay.mobile.device.rules.service.MDRRuleGroupLocalService
			mdrRuleGroupLocalService) {

		this.mdrRuleGroupLocalService = mdrRuleGroupLocalService;
	}

	/**
	 * Returns the mdr rule group remote service.
	 *
	 * @return the mdr rule group remote service
	 */
	public com.liferay.mobile.device.rules.service.MDRRuleGroupService
		getMDRRuleGroupService() {

		return mdrRuleGroupService;
	}

	/**
	 * Sets the mdr rule group remote service.
	 *
	 * @param mdrRuleGroupService the mdr rule group remote service
	 */
	public void setMDRRuleGroupService(
		com.liferay.mobile.device.rules.service.MDRRuleGroupService
			mdrRuleGroupService) {

		this.mdrRuleGroupService = mdrRuleGroupService;
	}

	/**
	 * Returns the mdr rule group persistence.
	 *
	 * @return the mdr rule group persistence
	 */
	public MDRRuleGroupPersistence getMDRRuleGroupPersistence() {
		return mdrRuleGroupPersistence;
	}

	/**
	 * Sets the mdr rule group persistence.
	 *
	 * @param mdrRuleGroupPersistence the mdr rule group persistence
	 */
	public void setMDRRuleGroupPersistence(
		MDRRuleGroupPersistence mdrRuleGroupPersistence) {

		this.mdrRuleGroupPersistence = mdrRuleGroupPersistence;
	}

	/**
	 * Returns the mdr rule group finder.
	 *
	 * @return the mdr rule group finder
	 */
	public MDRRuleGroupFinder getMDRRuleGroupFinder() {
		return mdrRuleGroupFinder;
	}

	/**
	 * Sets the mdr rule group finder.
	 *
	 * @param mdrRuleGroupFinder the mdr rule group finder
	 */
	public void setMDRRuleGroupFinder(MDRRuleGroupFinder mdrRuleGroupFinder) {
		this.mdrRuleGroupFinder = mdrRuleGroupFinder;
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
	 * Returns the class name remote service.
	 *
	 * @return the class name remote service
	 */
	public com.liferay.portal.kernel.service.ClassNameService
		getClassNameService() {

		return classNameService;
	}

	/**
	 * Sets the class name remote service.
	 *
	 * @param classNameService the class name remote service
	 */
	public void setClassNameService(
		com.liferay.portal.kernel.service.ClassNameService classNameService) {

		this.classNameService = classNameService;
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
	 * Returns the layout local service.
	 *
	 * @return the layout local service
	 */
	public com.liferay.portal.kernel.service.LayoutLocalService
		getLayoutLocalService() {

		return layoutLocalService;
	}

	/**
	 * Sets the layout local service.
	 *
	 * @param layoutLocalService the layout local service
	 */
	public void setLayoutLocalService(
		com.liferay.portal.kernel.service.LayoutLocalService
			layoutLocalService) {

		this.layoutLocalService = layoutLocalService;
	}

	/**
	 * Returns the layout remote service.
	 *
	 * @return the layout remote service
	 */
	public com.liferay.portal.kernel.service.LayoutService getLayoutService() {
		return layoutService;
	}

	/**
	 * Sets the layout remote service.
	 *
	 * @param layoutService the layout remote service
	 */
	public void setLayoutService(
		com.liferay.portal.kernel.service.LayoutService layoutService) {

		this.layoutService = layoutService;
	}

	/**
	 * Returns the layout persistence.
	 *
	 * @return the layout persistence
	 */
	public LayoutPersistence getLayoutPersistence() {
		return layoutPersistence;
	}

	/**
	 * Sets the layout persistence.
	 *
	 * @param layoutPersistence the layout persistence
	 */
	public void setLayoutPersistence(LayoutPersistence layoutPersistence) {
		this.layoutPersistence = layoutPersistence;
	}

	/**
	 * Returns the layout set local service.
	 *
	 * @return the layout set local service
	 */
	public com.liferay.portal.kernel.service.LayoutSetLocalService
		getLayoutSetLocalService() {

		return layoutSetLocalService;
	}

	/**
	 * Sets the layout set local service.
	 *
	 * @param layoutSetLocalService the layout set local service
	 */
	public void setLayoutSetLocalService(
		com.liferay.portal.kernel.service.LayoutSetLocalService
			layoutSetLocalService) {

		this.layoutSetLocalService = layoutSetLocalService;
	}

	/**
	 * Returns the layout set remote service.
	 *
	 * @return the layout set remote service
	 */
	public com.liferay.portal.kernel.service.LayoutSetService
		getLayoutSetService() {

		return layoutSetService;
	}

	/**
	 * Sets the layout set remote service.
	 *
	 * @param layoutSetService the layout set remote service
	 */
	public void setLayoutSetService(
		com.liferay.portal.kernel.service.LayoutSetService layoutSetService) {

		this.layoutSetService = layoutSetService;
	}

	/**
	 * Returns the layout set persistence.
	 *
	 * @return the layout set persistence
	 */
	public LayoutSetPersistence getLayoutSetPersistence() {
		return layoutSetPersistence;
	}

	/**
	 * Sets the layout set persistence.
	 *
	 * @param layoutSetPersistence the layout set persistence
	 */
	public void setLayoutSetPersistence(
		LayoutSetPersistence layoutSetPersistence) {

		this.layoutSetPersistence = layoutSetPersistence;
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
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.kernel.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.kernel.service.UserService userService) {

		this.userService = userService;
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
	}

	public void destroy() {
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return MDRRuleGroupInstanceService.class.getName();
	}

	protected Class<?> getModelClass() {
		return MDRRuleGroupInstance.class;
	}

	protected String getModelClassName() {
		return MDRRuleGroupInstance.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				mdrRuleGroupInstancePersistence.getDataSource();

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

	@BeanReference(
		type = com.liferay.mobile.device.rules.service.MDRRuleGroupInstanceLocalService.class
	)
	protected
		com.liferay.mobile.device.rules.service.MDRRuleGroupInstanceLocalService
			mdrRuleGroupInstanceLocalService;

	@BeanReference(type = MDRRuleGroupInstanceService.class)
	protected MDRRuleGroupInstanceService mdrRuleGroupInstanceService;

	@BeanReference(type = MDRRuleGroupInstancePersistence.class)
	protected MDRRuleGroupInstancePersistence mdrRuleGroupInstancePersistence;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@BeanReference(
		type = com.liferay.mobile.device.rules.service.MDRActionLocalService.class
	)
	protected com.liferay.mobile.device.rules.service.MDRActionLocalService
		mdrActionLocalService;

	@BeanReference(
		type = com.liferay.mobile.device.rules.service.MDRActionService.class
	)
	protected com.liferay.mobile.device.rules.service.MDRActionService
		mdrActionService;

	@BeanReference(type = MDRActionPersistence.class)
	protected MDRActionPersistence mdrActionPersistence;

	@BeanReference(
		type = com.liferay.mobile.device.rules.service.MDRRuleGroupLocalService.class
	)
	protected com.liferay.mobile.device.rules.service.MDRRuleGroupLocalService
		mdrRuleGroupLocalService;

	@BeanReference(
		type = com.liferay.mobile.device.rules.service.MDRRuleGroupService.class
	)
	protected com.liferay.mobile.device.rules.service.MDRRuleGroupService
		mdrRuleGroupService;

	@BeanReference(type = MDRRuleGroupPersistence.class)
	protected MDRRuleGroupPersistence mdrRuleGroupPersistence;

	@BeanReference(type = MDRRuleGroupFinder.class)
	protected MDRRuleGroupFinder mdrRuleGroupFinder;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ClassNameLocalService.class
	)
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ClassNameService.class
	)
	protected com.liferay.portal.kernel.service.ClassNameService
		classNameService;

	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.LayoutLocalService.class
	)
	protected com.liferay.portal.kernel.service.LayoutLocalService
		layoutLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.LayoutService.class
	)
	protected com.liferay.portal.kernel.service.LayoutService layoutService;

	@ServiceReference(type = LayoutPersistence.class)
	protected LayoutPersistence layoutPersistence;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.LayoutSetLocalService.class
	)
	protected com.liferay.portal.kernel.service.LayoutSetLocalService
		layoutSetLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.LayoutSetService.class
	)
	protected com.liferay.portal.kernel.service.LayoutSetService
		layoutSetService;

	@ServiceReference(type = LayoutSetPersistence.class)
	protected LayoutSetPersistence layoutSetPersistence;

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

	@ServiceReference(
		type = com.liferay.portal.kernel.service.UserService.class
	)
	protected com.liferay.portal.kernel.service.UserService userService;

	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;

}