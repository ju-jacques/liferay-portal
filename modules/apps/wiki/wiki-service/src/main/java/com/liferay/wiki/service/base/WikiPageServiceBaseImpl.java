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

package com.liferay.wiki.service.base;

import com.liferay.asset.kernel.service.persistence.AssetCategoryPersistence;
import com.liferay.asset.kernel.service.persistence.AssetEntryPersistence;
import com.liferay.asset.kernel.service.persistence.AssetLinkPersistence;
import com.liferay.asset.kernel.service.persistence.AssetTagPersistence;
import com.liferay.expando.kernel.service.persistence.ExpandoRowPersistence;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.service.persistence.SystemEventPersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.service.persistence.WorkflowInstanceLinkPersistence;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.ratings.kernel.service.persistence.RatingsStatsPersistence;
import com.liferay.wiki.model.WikiPage;
import com.liferay.wiki.service.WikiPageService;
import com.liferay.wiki.service.persistence.WikiNodePersistence;
import com.liferay.wiki.service.persistence.WikiPageFinder;
import com.liferay.wiki.service.persistence.WikiPagePersistence;
import com.liferay.wiki.service.persistence.WikiPageResourcePersistence;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the wiki page remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.wiki.service.impl.WikiPageServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.wiki.service.impl.WikiPageServiceImpl
 * @generated
 */
public abstract class WikiPageServiceBaseImpl
	extends BaseServiceImpl
	implements IdentifiableOSGiService, WikiPageService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>WikiPageService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.wiki.service.WikiPageServiceUtil</code>.
	 */

	/**
	 * Returns the wiki page local service.
	 *
	 * @return the wiki page local service
	 */
	public com.liferay.wiki.service.WikiPageLocalService
		getWikiPageLocalService() {

		return wikiPageLocalService;
	}

	/**
	 * Sets the wiki page local service.
	 *
	 * @param wikiPageLocalService the wiki page local service
	 */
	public void setWikiPageLocalService(
		com.liferay.wiki.service.WikiPageLocalService wikiPageLocalService) {

		this.wikiPageLocalService = wikiPageLocalService;
	}

	/**
	 * Returns the wiki page remote service.
	 *
	 * @return the wiki page remote service
	 */
	public WikiPageService getWikiPageService() {
		return wikiPageService;
	}

	/**
	 * Sets the wiki page remote service.
	 *
	 * @param wikiPageService the wiki page remote service
	 */
	public void setWikiPageService(WikiPageService wikiPageService) {
		this.wikiPageService = wikiPageService;
	}

	/**
	 * Returns the wiki page persistence.
	 *
	 * @return the wiki page persistence
	 */
	public WikiPagePersistence getWikiPagePersistence() {
		return wikiPagePersistence;
	}

	/**
	 * Sets the wiki page persistence.
	 *
	 * @param wikiPagePersistence the wiki page persistence
	 */
	public void setWikiPagePersistence(
		WikiPagePersistence wikiPagePersistence) {

		this.wikiPagePersistence = wikiPagePersistence;
	}

	/**
	 * Returns the wiki page finder.
	 *
	 * @return the wiki page finder
	 */
	public WikiPageFinder getWikiPageFinder() {
		return wikiPageFinder;
	}

	/**
	 * Sets the wiki page finder.
	 *
	 * @param wikiPageFinder the wiki page finder
	 */
	public void setWikiPageFinder(WikiPageFinder wikiPageFinder) {
		this.wikiPageFinder = wikiPageFinder;
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
	 * Returns the system event local service.
	 *
	 * @return the system event local service
	 */
	public com.liferay.portal.kernel.service.SystemEventLocalService
		getSystemEventLocalService() {

		return systemEventLocalService;
	}

	/**
	 * Sets the system event local service.
	 *
	 * @param systemEventLocalService the system event local service
	 */
	public void setSystemEventLocalService(
		com.liferay.portal.kernel.service.SystemEventLocalService
			systemEventLocalService) {

		this.systemEventLocalService = systemEventLocalService;
	}

	/**
	 * Returns the system event persistence.
	 *
	 * @return the system event persistence
	 */
	public SystemEventPersistence getSystemEventPersistence() {
		return systemEventPersistence;
	}

	/**
	 * Sets the system event persistence.
	 *
	 * @param systemEventPersistence the system event persistence
	 */
	public void setSystemEventPersistence(
		SystemEventPersistence systemEventPersistence) {

		this.systemEventPersistence = systemEventPersistence;
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
	 * Returns the workflow instance link local service.
	 *
	 * @return the workflow instance link local service
	 */
	public com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalService
		getWorkflowInstanceLinkLocalService() {

		return workflowInstanceLinkLocalService;
	}

	/**
	 * Sets the workflow instance link local service.
	 *
	 * @param workflowInstanceLinkLocalService the workflow instance link local service
	 */
	public void setWorkflowInstanceLinkLocalService(
		com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalService
			workflowInstanceLinkLocalService) {

		this.workflowInstanceLinkLocalService =
			workflowInstanceLinkLocalService;
	}

	/**
	 * Returns the workflow instance link persistence.
	 *
	 * @return the workflow instance link persistence
	 */
	public WorkflowInstanceLinkPersistence
		getWorkflowInstanceLinkPersistence() {

		return workflowInstanceLinkPersistence;
	}

	/**
	 * Sets the workflow instance link persistence.
	 *
	 * @param workflowInstanceLinkPersistence the workflow instance link persistence
	 */
	public void setWorkflowInstanceLinkPersistence(
		WorkflowInstanceLinkPersistence workflowInstanceLinkPersistence) {

		this.workflowInstanceLinkPersistence = workflowInstanceLinkPersistence;
	}

	/**
	 * Returns the asset category local service.
	 *
	 * @return the asset category local service
	 */
	public com.liferay.asset.kernel.service.AssetCategoryLocalService
		getAssetCategoryLocalService() {

		return assetCategoryLocalService;
	}

	/**
	 * Sets the asset category local service.
	 *
	 * @param assetCategoryLocalService the asset category local service
	 */
	public void setAssetCategoryLocalService(
		com.liferay.asset.kernel.service.AssetCategoryLocalService
			assetCategoryLocalService) {

		this.assetCategoryLocalService = assetCategoryLocalService;
	}

	/**
	 * Returns the asset category remote service.
	 *
	 * @return the asset category remote service
	 */
	public com.liferay.asset.kernel.service.AssetCategoryService
		getAssetCategoryService() {

		return assetCategoryService;
	}

	/**
	 * Sets the asset category remote service.
	 *
	 * @param assetCategoryService the asset category remote service
	 */
	public void setAssetCategoryService(
		com.liferay.asset.kernel.service.AssetCategoryService
			assetCategoryService) {

		this.assetCategoryService = assetCategoryService;
	}

	/**
	 * Returns the asset category persistence.
	 *
	 * @return the asset category persistence
	 */
	public AssetCategoryPersistence getAssetCategoryPersistence() {
		return assetCategoryPersistence;
	}

	/**
	 * Sets the asset category persistence.
	 *
	 * @param assetCategoryPersistence the asset category persistence
	 */
	public void setAssetCategoryPersistence(
		AssetCategoryPersistence assetCategoryPersistence) {

		this.assetCategoryPersistence = assetCategoryPersistence;
	}

	/**
	 * Returns the asset entry local service.
	 *
	 * @return the asset entry local service
	 */
	public com.liferay.asset.kernel.service.AssetEntryLocalService
		getAssetEntryLocalService() {

		return assetEntryLocalService;
	}

	/**
	 * Sets the asset entry local service.
	 *
	 * @param assetEntryLocalService the asset entry local service
	 */
	public void setAssetEntryLocalService(
		com.liferay.asset.kernel.service.AssetEntryLocalService
			assetEntryLocalService) {

		this.assetEntryLocalService = assetEntryLocalService;
	}

	/**
	 * Returns the asset entry remote service.
	 *
	 * @return the asset entry remote service
	 */
	public com.liferay.asset.kernel.service.AssetEntryService
		getAssetEntryService() {

		return assetEntryService;
	}

	/**
	 * Sets the asset entry remote service.
	 *
	 * @param assetEntryService the asset entry remote service
	 */
	public void setAssetEntryService(
		com.liferay.asset.kernel.service.AssetEntryService assetEntryService) {

		this.assetEntryService = assetEntryService;
	}

	/**
	 * Returns the asset entry persistence.
	 *
	 * @return the asset entry persistence
	 */
	public AssetEntryPersistence getAssetEntryPersistence() {
		return assetEntryPersistence;
	}

	/**
	 * Sets the asset entry persistence.
	 *
	 * @param assetEntryPersistence the asset entry persistence
	 */
	public void setAssetEntryPersistence(
		AssetEntryPersistence assetEntryPersistence) {

		this.assetEntryPersistence = assetEntryPersistence;
	}

	/**
	 * Returns the asset link local service.
	 *
	 * @return the asset link local service
	 */
	public com.liferay.asset.kernel.service.AssetLinkLocalService
		getAssetLinkLocalService() {

		return assetLinkLocalService;
	}

	/**
	 * Sets the asset link local service.
	 *
	 * @param assetLinkLocalService the asset link local service
	 */
	public void setAssetLinkLocalService(
		com.liferay.asset.kernel.service.AssetLinkLocalService
			assetLinkLocalService) {

		this.assetLinkLocalService = assetLinkLocalService;
	}

	/**
	 * Returns the asset link persistence.
	 *
	 * @return the asset link persistence
	 */
	public AssetLinkPersistence getAssetLinkPersistence() {
		return assetLinkPersistence;
	}

	/**
	 * Sets the asset link persistence.
	 *
	 * @param assetLinkPersistence the asset link persistence
	 */
	public void setAssetLinkPersistence(
		AssetLinkPersistence assetLinkPersistence) {

		this.assetLinkPersistence = assetLinkPersistence;
	}

	/**
	 * Returns the asset tag local service.
	 *
	 * @return the asset tag local service
	 */
	public com.liferay.asset.kernel.service.AssetTagLocalService
		getAssetTagLocalService() {

		return assetTagLocalService;
	}

	/**
	 * Sets the asset tag local service.
	 *
	 * @param assetTagLocalService the asset tag local service
	 */
	public void setAssetTagLocalService(
		com.liferay.asset.kernel.service.AssetTagLocalService
			assetTagLocalService) {

		this.assetTagLocalService = assetTagLocalService;
	}

	/**
	 * Returns the asset tag remote service.
	 *
	 * @return the asset tag remote service
	 */
	public com.liferay.asset.kernel.service.AssetTagService
		getAssetTagService() {

		return assetTagService;
	}

	/**
	 * Sets the asset tag remote service.
	 *
	 * @param assetTagService the asset tag remote service
	 */
	public void setAssetTagService(
		com.liferay.asset.kernel.service.AssetTagService assetTagService) {

		this.assetTagService = assetTagService;
	}

	/**
	 * Returns the asset tag persistence.
	 *
	 * @return the asset tag persistence
	 */
	public AssetTagPersistence getAssetTagPersistence() {
		return assetTagPersistence;
	}

	/**
	 * Sets the asset tag persistence.
	 *
	 * @param assetTagPersistence the asset tag persistence
	 */
	public void setAssetTagPersistence(
		AssetTagPersistence assetTagPersistence) {

		this.assetTagPersistence = assetTagPersistence;
	}

	/**
	 * Returns the expando row local service.
	 *
	 * @return the expando row local service
	 */
	public com.liferay.expando.kernel.service.ExpandoRowLocalService
		getExpandoRowLocalService() {

		return expandoRowLocalService;
	}

	/**
	 * Sets the expando row local service.
	 *
	 * @param expandoRowLocalService the expando row local service
	 */
	public void setExpandoRowLocalService(
		com.liferay.expando.kernel.service.ExpandoRowLocalService
			expandoRowLocalService) {

		this.expandoRowLocalService = expandoRowLocalService;
	}

	/**
	 * Returns the expando row persistence.
	 *
	 * @return the expando row persistence
	 */
	public ExpandoRowPersistence getExpandoRowPersistence() {
		return expandoRowPersistence;
	}

	/**
	 * Sets the expando row persistence.
	 *
	 * @param expandoRowPersistence the expando row persistence
	 */
	public void setExpandoRowPersistence(
		ExpandoRowPersistence expandoRowPersistence) {

		this.expandoRowPersistence = expandoRowPersistence;
	}

	/**
	 * Returns the ratings stats local service.
	 *
	 * @return the ratings stats local service
	 */
	public com.liferay.ratings.kernel.service.RatingsStatsLocalService
		getRatingsStatsLocalService() {

		return ratingsStatsLocalService;
	}

	/**
	 * Sets the ratings stats local service.
	 *
	 * @param ratingsStatsLocalService the ratings stats local service
	 */
	public void setRatingsStatsLocalService(
		com.liferay.ratings.kernel.service.RatingsStatsLocalService
			ratingsStatsLocalService) {

		this.ratingsStatsLocalService = ratingsStatsLocalService;
	}

	/**
	 * Returns the ratings stats persistence.
	 *
	 * @return the ratings stats persistence
	 */
	public RatingsStatsPersistence getRatingsStatsPersistence() {
		return ratingsStatsPersistence;
	}

	/**
	 * Sets the ratings stats persistence.
	 *
	 * @param ratingsStatsPersistence the ratings stats persistence
	 */
	public void setRatingsStatsPersistence(
		RatingsStatsPersistence ratingsStatsPersistence) {

		this.ratingsStatsPersistence = ratingsStatsPersistence;
	}

	/**
	 * Returns the wiki node local service.
	 *
	 * @return the wiki node local service
	 */
	public com.liferay.wiki.service.WikiNodeLocalService
		getWikiNodeLocalService() {

		return wikiNodeLocalService;
	}

	/**
	 * Sets the wiki node local service.
	 *
	 * @param wikiNodeLocalService the wiki node local service
	 */
	public void setWikiNodeLocalService(
		com.liferay.wiki.service.WikiNodeLocalService wikiNodeLocalService) {

		this.wikiNodeLocalService = wikiNodeLocalService;
	}

	/**
	 * Returns the wiki node remote service.
	 *
	 * @return the wiki node remote service
	 */
	public com.liferay.wiki.service.WikiNodeService getWikiNodeService() {
		return wikiNodeService;
	}

	/**
	 * Sets the wiki node remote service.
	 *
	 * @param wikiNodeService the wiki node remote service
	 */
	public void setWikiNodeService(
		com.liferay.wiki.service.WikiNodeService wikiNodeService) {

		this.wikiNodeService = wikiNodeService;
	}

	/**
	 * Returns the wiki node persistence.
	 *
	 * @return the wiki node persistence
	 */
	public WikiNodePersistence getWikiNodePersistence() {
		return wikiNodePersistence;
	}

	/**
	 * Sets the wiki node persistence.
	 *
	 * @param wikiNodePersistence the wiki node persistence
	 */
	public void setWikiNodePersistence(
		WikiNodePersistence wikiNodePersistence) {

		this.wikiNodePersistence = wikiNodePersistence;
	}

	/**
	 * Returns the wiki page resource local service.
	 *
	 * @return the wiki page resource local service
	 */
	public com.liferay.wiki.service.WikiPageResourceLocalService
		getWikiPageResourceLocalService() {

		return wikiPageResourceLocalService;
	}

	/**
	 * Sets the wiki page resource local service.
	 *
	 * @param wikiPageResourceLocalService the wiki page resource local service
	 */
	public void setWikiPageResourceLocalService(
		com.liferay.wiki.service.WikiPageResourceLocalService
			wikiPageResourceLocalService) {

		this.wikiPageResourceLocalService = wikiPageResourceLocalService;
	}

	/**
	 * Returns the wiki page resource persistence.
	 *
	 * @return the wiki page resource persistence
	 */
	public WikiPageResourcePersistence getWikiPageResourcePersistence() {
		return wikiPageResourcePersistence;
	}

	/**
	 * Sets the wiki page resource persistence.
	 *
	 * @param wikiPageResourcePersistence the wiki page resource persistence
	 */
	public void setWikiPageResourcePersistence(
		WikiPageResourcePersistence wikiPageResourcePersistence) {

		this.wikiPageResourcePersistence = wikiPageResourcePersistence;
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
		return WikiPageService.class.getName();
	}

	protected Class<?> getModelClass() {
		return WikiPage.class;
	}

	protected String getModelClassName() {
		return WikiPage.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = wikiPagePersistence.getDataSource();

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

	@BeanReference(type = com.liferay.wiki.service.WikiPageLocalService.class)
	protected com.liferay.wiki.service.WikiPageLocalService
		wikiPageLocalService;

	@BeanReference(type = WikiPageService.class)
	protected WikiPageService wikiPageService;

	@BeanReference(type = WikiPagePersistence.class)
	protected WikiPagePersistence wikiPagePersistence;

	@BeanReference(type = WikiPageFinder.class)
	protected WikiPageFinder wikiPageFinder;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ResourceLocalService.class
	)
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.SystemEventLocalService.class
	)
	protected com.liferay.portal.kernel.service.SystemEventLocalService
		systemEventLocalService;

	@ServiceReference(type = SystemEventPersistence.class)
	protected SystemEventPersistence systemEventPersistence;

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

	@ServiceReference(
		type = com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalService.class
	)
	protected com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalService
		workflowInstanceLinkLocalService;

	@ServiceReference(type = WorkflowInstanceLinkPersistence.class)
	protected WorkflowInstanceLinkPersistence workflowInstanceLinkPersistence;

	@ServiceReference(
		type = com.liferay.asset.kernel.service.AssetCategoryLocalService.class
	)
	protected com.liferay.asset.kernel.service.AssetCategoryLocalService
		assetCategoryLocalService;

	@ServiceReference(
		type = com.liferay.asset.kernel.service.AssetCategoryService.class
	)
	protected com.liferay.asset.kernel.service.AssetCategoryService
		assetCategoryService;

	@ServiceReference(type = AssetCategoryPersistence.class)
	protected AssetCategoryPersistence assetCategoryPersistence;

	@ServiceReference(
		type = com.liferay.asset.kernel.service.AssetEntryLocalService.class
	)
	protected com.liferay.asset.kernel.service.AssetEntryLocalService
		assetEntryLocalService;

	@ServiceReference(
		type = com.liferay.asset.kernel.service.AssetEntryService.class
	)
	protected com.liferay.asset.kernel.service.AssetEntryService
		assetEntryService;

	@ServiceReference(type = AssetEntryPersistence.class)
	protected AssetEntryPersistence assetEntryPersistence;

	@ServiceReference(
		type = com.liferay.asset.kernel.service.AssetLinkLocalService.class
	)
	protected com.liferay.asset.kernel.service.AssetLinkLocalService
		assetLinkLocalService;

	@ServiceReference(type = AssetLinkPersistence.class)
	protected AssetLinkPersistence assetLinkPersistence;

	@ServiceReference(
		type = com.liferay.asset.kernel.service.AssetTagLocalService.class
	)
	protected com.liferay.asset.kernel.service.AssetTagLocalService
		assetTagLocalService;

	@ServiceReference(
		type = com.liferay.asset.kernel.service.AssetTagService.class
	)
	protected com.liferay.asset.kernel.service.AssetTagService assetTagService;

	@ServiceReference(type = AssetTagPersistence.class)
	protected AssetTagPersistence assetTagPersistence;

	@ServiceReference(
		type = com.liferay.expando.kernel.service.ExpandoRowLocalService.class
	)
	protected com.liferay.expando.kernel.service.ExpandoRowLocalService
		expandoRowLocalService;

	@ServiceReference(type = ExpandoRowPersistence.class)
	protected ExpandoRowPersistence expandoRowPersistence;

	@ServiceReference(
		type = com.liferay.ratings.kernel.service.RatingsStatsLocalService.class
	)
	protected com.liferay.ratings.kernel.service.RatingsStatsLocalService
		ratingsStatsLocalService;

	@ServiceReference(type = RatingsStatsPersistence.class)
	protected RatingsStatsPersistence ratingsStatsPersistence;

	@BeanReference(type = com.liferay.wiki.service.WikiNodeLocalService.class)
	protected com.liferay.wiki.service.WikiNodeLocalService
		wikiNodeLocalService;

	@BeanReference(type = com.liferay.wiki.service.WikiNodeService.class)
	protected com.liferay.wiki.service.WikiNodeService wikiNodeService;

	@BeanReference(type = WikiNodePersistence.class)
	protected WikiNodePersistence wikiNodePersistence;

	@BeanReference(
		type = com.liferay.wiki.service.WikiPageResourceLocalService.class
	)
	protected com.liferay.wiki.service.WikiPageResourceLocalService
		wikiPageResourceLocalService;

	@BeanReference(type = WikiPageResourcePersistence.class)
	protected WikiPageResourcePersistence wikiPageResourcePersistence;

}