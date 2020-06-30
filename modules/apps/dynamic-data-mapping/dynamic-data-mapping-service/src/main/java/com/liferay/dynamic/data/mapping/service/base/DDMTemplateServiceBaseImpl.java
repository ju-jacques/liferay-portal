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

package com.liferay.dynamic.data.mapping.service.base;

import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMTemplateService;
import com.liferay.dynamic.data.mapping.service.persistence.DDMTemplateFinder;
import com.liferay.dynamic.data.mapping.service.persistence.DDMTemplateLinkPersistence;
import com.liferay.dynamic.data.mapping.service.persistence.DDMTemplatePersistence;
import com.liferay.dynamic.data.mapping.service.persistence.DDMTemplateVersionPersistence;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.service.persistence.ImagePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the ddm template remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.dynamic.data.mapping.service.impl.DDMTemplateServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.dynamic.data.mapping.service.impl.DDMTemplateServiceImpl
 * @generated
 */
public abstract class DDMTemplateServiceBaseImpl
	extends BaseServiceImpl
	implements DDMTemplateService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>DDMTemplateService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.dynamic.data.mapping.service.DDMTemplateServiceUtil</code>.
	 */

	/**
	 * Returns the ddm template local service.
	 *
	 * @return the ddm template local service
	 */
	public com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService
		getDDMTemplateLocalService() {

		return ddmTemplateLocalService;
	}

	/**
	 * Sets the ddm template local service.
	 *
	 * @param ddmTemplateLocalService the ddm template local service
	 */
	public void setDDMTemplateLocalService(
		com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService
			ddmTemplateLocalService) {

		this.ddmTemplateLocalService = ddmTemplateLocalService;
	}

	/**
	 * Returns the ddm template remote service.
	 *
	 * @return the ddm template remote service
	 */
	public DDMTemplateService getDDMTemplateService() {
		return ddmTemplateService;
	}

	/**
	 * Sets the ddm template remote service.
	 *
	 * @param ddmTemplateService the ddm template remote service
	 */
	public void setDDMTemplateService(DDMTemplateService ddmTemplateService) {
		this.ddmTemplateService = ddmTemplateService;
	}

	/**
	 * Returns the ddm template persistence.
	 *
	 * @return the ddm template persistence
	 */
	public DDMTemplatePersistence getDDMTemplatePersistence() {
		return ddmTemplatePersistence;
	}

	/**
	 * Sets the ddm template persistence.
	 *
	 * @param ddmTemplatePersistence the ddm template persistence
	 */
	public void setDDMTemplatePersistence(
		DDMTemplatePersistence ddmTemplatePersistence) {

		this.ddmTemplatePersistence = ddmTemplatePersistence;
	}

	/**
	 * Returns the ddm template finder.
	 *
	 * @return the ddm template finder
	 */
	public DDMTemplateFinder getDDMTemplateFinder() {
		return ddmTemplateFinder;
	}

	/**
	 * Sets the ddm template finder.
	 *
	 * @param ddmTemplateFinder the ddm template finder
	 */
	public void setDDMTemplateFinder(DDMTemplateFinder ddmTemplateFinder) {
		this.ddmTemplateFinder = ddmTemplateFinder;
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
	 * Returns the image local service.
	 *
	 * @return the image local service
	 */
	public com.liferay.portal.kernel.service.ImageLocalService
		getImageLocalService() {

		return imageLocalService;
	}

	/**
	 * Sets the image local service.
	 *
	 * @param imageLocalService the image local service
	 */
	public void setImageLocalService(
		com.liferay.portal.kernel.service.ImageLocalService imageLocalService) {

		this.imageLocalService = imageLocalService;
	}

	/**
	 * Returns the image remote service.
	 *
	 * @return the image remote service
	 */
	public com.liferay.portal.kernel.service.ImageService getImageService() {
		return imageService;
	}

	/**
	 * Sets the image remote service.
	 *
	 * @param imageService the image remote service
	 */
	public void setImageService(
		com.liferay.portal.kernel.service.ImageService imageService) {

		this.imageService = imageService;
	}

	/**
	 * Returns the image persistence.
	 *
	 * @return the image persistence
	 */
	public ImagePersistence getImagePersistence() {
		return imagePersistence;
	}

	/**
	 * Sets the image persistence.
	 *
	 * @param imagePersistence the image persistence
	 */
	public void setImagePersistence(ImagePersistence imagePersistence) {
		this.imagePersistence = imagePersistence;
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

	/**
	 * Returns the ddm template link local service.
	 *
	 * @return the ddm template link local service
	 */
	public com.liferay.dynamic.data.mapping.service.DDMTemplateLinkLocalService
		getDDMTemplateLinkLocalService() {

		return ddmTemplateLinkLocalService;
	}

	/**
	 * Sets the ddm template link local service.
	 *
	 * @param ddmTemplateLinkLocalService the ddm template link local service
	 */
	public void setDDMTemplateLinkLocalService(
		com.liferay.dynamic.data.mapping.service.DDMTemplateLinkLocalService
			ddmTemplateLinkLocalService) {

		this.ddmTemplateLinkLocalService = ddmTemplateLinkLocalService;
	}

	/**
	 * Returns the ddm template link persistence.
	 *
	 * @return the ddm template link persistence
	 */
	public DDMTemplateLinkPersistence getDDMTemplateLinkPersistence() {
		return ddmTemplateLinkPersistence;
	}

	/**
	 * Sets the ddm template link persistence.
	 *
	 * @param ddmTemplateLinkPersistence the ddm template link persistence
	 */
	public void setDDMTemplateLinkPersistence(
		DDMTemplateLinkPersistence ddmTemplateLinkPersistence) {

		this.ddmTemplateLinkPersistence = ddmTemplateLinkPersistence;
	}

	/**
	 * Returns the ddm template version local service.
	 *
	 * @return the ddm template version local service
	 */
	public
		com.liferay.dynamic.data.mapping.service.DDMTemplateVersionLocalService
			getDDMTemplateVersionLocalService() {

		return ddmTemplateVersionLocalService;
	}

	/**
	 * Sets the ddm template version local service.
	 *
	 * @param ddmTemplateVersionLocalService the ddm template version local service
	 */
	public void setDDMTemplateVersionLocalService(
		com.liferay.dynamic.data.mapping.service.DDMTemplateVersionLocalService
			ddmTemplateVersionLocalService) {

		this.ddmTemplateVersionLocalService = ddmTemplateVersionLocalService;
	}

	/**
	 * Returns the ddm template version remote service.
	 *
	 * @return the ddm template version remote service
	 */
	public com.liferay.dynamic.data.mapping.service.DDMTemplateVersionService
		getDDMTemplateVersionService() {

		return ddmTemplateVersionService;
	}

	/**
	 * Sets the ddm template version remote service.
	 *
	 * @param ddmTemplateVersionService the ddm template version remote service
	 */
	public void setDDMTemplateVersionService(
		com.liferay.dynamic.data.mapping.service.DDMTemplateVersionService
			ddmTemplateVersionService) {

		this.ddmTemplateVersionService = ddmTemplateVersionService;
	}

	/**
	 * Returns the ddm template version persistence.
	 *
	 * @return the ddm template version persistence
	 */
	public DDMTemplateVersionPersistence getDDMTemplateVersionPersistence() {
		return ddmTemplateVersionPersistence;
	}

	/**
	 * Sets the ddm template version persistence.
	 *
	 * @param ddmTemplateVersionPersistence the ddm template version persistence
	 */
	public void setDDMTemplateVersionPersistence(
		DDMTemplateVersionPersistence ddmTemplateVersionPersistence) {

		this.ddmTemplateVersionPersistence = ddmTemplateVersionPersistence;
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
		return DDMTemplateService.class.getName();
	}

	protected Class<?> getModelClass() {
		return DDMTemplate.class;
	}

	protected String getModelClassName() {
		return DDMTemplate.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = ddmTemplatePersistence.getDataSource();

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
		type = com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService.class
	)
	protected com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService
		ddmTemplateLocalService;

	@BeanReference(type = DDMTemplateService.class)
	protected DDMTemplateService ddmTemplateService;

	@BeanReference(type = DDMTemplatePersistence.class)
	protected DDMTemplatePersistence ddmTemplatePersistence;

	@BeanReference(type = DDMTemplateFinder.class)
	protected DDMTemplateFinder ddmTemplateFinder;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ImageLocalService.class
	)
	protected com.liferay.portal.kernel.service.ImageLocalService
		imageLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ImageService.class
	)
	protected com.liferay.portal.kernel.service.ImageService imageService;

	@ServiceReference(type = ImagePersistence.class)
	protected ImagePersistence imagePersistence;

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

	@BeanReference(
		type = com.liferay.dynamic.data.mapping.service.DDMTemplateLinkLocalService.class
	)
	protected
		com.liferay.dynamic.data.mapping.service.DDMTemplateLinkLocalService
			ddmTemplateLinkLocalService;

	@BeanReference(type = DDMTemplateLinkPersistence.class)
	protected DDMTemplateLinkPersistence ddmTemplateLinkPersistence;

	@BeanReference(
		type = com.liferay.dynamic.data.mapping.service.DDMTemplateVersionLocalService.class
	)
	protected
		com.liferay.dynamic.data.mapping.service.DDMTemplateVersionLocalService
			ddmTemplateVersionLocalService;

	@BeanReference(
		type = com.liferay.dynamic.data.mapping.service.DDMTemplateVersionService.class
	)
	protected com.liferay.dynamic.data.mapping.service.DDMTemplateVersionService
		ddmTemplateVersionService;

	@BeanReference(type = DDMTemplateVersionPersistence.class)
	protected DDMTemplateVersionPersistence ddmTemplateVersionPersistence;

}