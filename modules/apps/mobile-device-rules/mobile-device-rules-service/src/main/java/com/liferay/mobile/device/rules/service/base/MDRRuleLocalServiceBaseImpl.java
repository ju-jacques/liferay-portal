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

import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.mobile.device.rules.model.MDRRule;
import com.liferay.mobile.device.rules.service.MDRRuleLocalService;
import com.liferay.mobile.device.rules.service.persistence.MDRRuleGroupFinder;
import com.liferay.mobile.device.rules.service.persistence.MDRRuleGroupPersistence;
import com.liferay.mobile.device.rules.service.persistence.MDRRulePersistence;
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
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the mdr rule local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.mobile.device.rules.service.impl.MDRRuleLocalServiceImpl}.
 * </p>
 *
 * @author Edward C. Han
 * @see com.liferay.mobile.device.rules.service.impl.MDRRuleLocalServiceImpl
 * @generated
 */
public abstract class MDRRuleLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements IdentifiableOSGiService, MDRRuleLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>MDRRuleLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.mobile.device.rules.service.MDRRuleLocalServiceUtil</code>.
	 */

	/**
	 * Adds the mdr rule to the database. Also notifies the appropriate model listeners.
	 *
	 * @param mdrRule the mdr rule
	 * @return the mdr rule that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public MDRRule addMDRRule(MDRRule mdrRule) {
		mdrRule.setNew(true);

		return mdrRulePersistence.update(mdrRule);
	}

	/**
	 * Creates a new mdr rule with the primary key. Does not add the mdr rule to the database.
	 *
	 * @param ruleId the primary key for the new mdr rule
	 * @return the new mdr rule
	 */
	@Override
	@Transactional(enabled = false)
	public MDRRule createMDRRule(long ruleId) {
		return mdrRulePersistence.create(ruleId);
	}

	/**
	 * Deletes the mdr rule with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ruleId the primary key of the mdr rule
	 * @return the mdr rule that was removed
	 * @throws PortalException if a mdr rule with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public MDRRule deleteMDRRule(long ruleId) throws PortalException {
		return mdrRulePersistence.remove(ruleId);
	}

	/**
	 * Deletes the mdr rule from the database. Also notifies the appropriate model listeners.
	 *
	 * @param mdrRule the mdr rule
	 * @return the mdr rule that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public MDRRule deleteMDRRule(MDRRule mdrRule) {
		return mdrRulePersistence.remove(mdrRule);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			MDRRule.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return mdrRulePersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.mobile.device.rules.model.impl.MDRRuleModelImpl</code>.
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

		return mdrRulePersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.mobile.device.rules.model.impl.MDRRuleModelImpl</code>.
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

		return mdrRulePersistence.findWithDynamicQuery(
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
		return mdrRulePersistence.countWithDynamicQuery(dynamicQuery);
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

		return mdrRulePersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public MDRRule fetchMDRRule(long ruleId) {
		return mdrRulePersistence.fetchByPrimaryKey(ruleId);
	}

	/**
	 * Returns the mdr rule matching the UUID and group.
	 *
	 * @param uuid the mdr rule's UUID
	 * @param groupId the primary key of the group
	 * @return the matching mdr rule, or <code>null</code> if a matching mdr rule could not be found
	 */
	@Override
	public MDRRule fetchMDRRuleByUuidAndGroupId(String uuid, long groupId) {
		return mdrRulePersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the mdr rule with the primary key.
	 *
	 * @param ruleId the primary key of the mdr rule
	 * @return the mdr rule
	 * @throws PortalException if a mdr rule with the primary key could not be found
	 */
	@Override
	public MDRRule getMDRRule(long ruleId) throws PortalException {
		return mdrRulePersistence.findByPrimaryKey(ruleId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(mdrRuleLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(MDRRule.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("ruleId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			mdrRuleLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(MDRRule.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("ruleId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(mdrRuleLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(MDRRule.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("ruleId");
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

		exportActionableDynamicQuery.setGroupId(
			portletDataContext.getScopeGroupId());

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<MDRRule>() {

				@Override
				public void performAction(MDRRule mdrRule)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, mdrRule);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(MDRRule.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return mdrRuleLocalService.deleteMDRRule((MDRRule)persistedModel);
	}

	public BasePersistence<MDRRule> getBasePersistence() {
		return mdrRulePersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return mdrRulePersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the mdr rules matching the UUID and company.
	 *
	 * @param uuid the UUID of the mdr rules
	 * @param companyId the primary key of the company
	 * @return the matching mdr rules, or an empty list if no matches were found
	 */
	@Override
	public List<MDRRule> getMDRRulesByUuidAndCompanyId(
		String uuid, long companyId) {

		return mdrRulePersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of mdr rules matching the UUID and company.
	 *
	 * @param uuid the UUID of the mdr rules
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of mdr rules
	 * @param end the upper bound of the range of mdr rules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching mdr rules, or an empty list if no matches were found
	 */
	@Override
	public List<MDRRule> getMDRRulesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<MDRRule> orderByComparator) {

		return mdrRulePersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the mdr rule matching the UUID and group.
	 *
	 * @param uuid the mdr rule's UUID
	 * @param groupId the primary key of the group
	 * @return the matching mdr rule
	 * @throws PortalException if a matching mdr rule could not be found
	 */
	@Override
	public MDRRule getMDRRuleByUuidAndGroupId(String uuid, long groupId)
		throws PortalException {

		return mdrRulePersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the mdr rules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.mobile.device.rules.model.impl.MDRRuleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of mdr rules
	 * @param end the upper bound of the range of mdr rules (not inclusive)
	 * @return the range of mdr rules
	 */
	@Override
	public List<MDRRule> getMDRRules(int start, int end) {
		return mdrRulePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of mdr rules.
	 *
	 * @return the number of mdr rules
	 */
	@Override
	public int getMDRRulesCount() {
		return mdrRulePersistence.countAll();
	}

	/**
	 * Updates the mdr rule in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param mdrRule the mdr rule
	 * @return the mdr rule that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public MDRRule updateMDRRule(MDRRule mdrRule) {
		return mdrRulePersistence.update(mdrRule);
	}

	/**
	 * Returns the mdr rule local service.
	 *
	 * @return the mdr rule local service
	 */
	public MDRRuleLocalService getMDRRuleLocalService() {
		return mdrRuleLocalService;
	}

	/**
	 * Sets the mdr rule local service.
	 *
	 * @param mdrRuleLocalService the mdr rule local service
	 */
	public void setMDRRuleLocalService(
		MDRRuleLocalService mdrRuleLocalService) {

		this.mdrRuleLocalService = mdrRuleLocalService;
	}

	/**
	 * Returns the mdr rule persistence.
	 *
	 * @return the mdr rule persistence
	 */
	public MDRRulePersistence getMDRRulePersistence() {
		return mdrRulePersistence;
	}

	/**
	 * Sets the mdr rule persistence.
	 *
	 * @param mdrRulePersistence the mdr rule persistence
	 */
	public void setMDRRulePersistence(MDRRulePersistence mdrRulePersistence) {
		this.mdrRulePersistence = mdrRulePersistence;
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

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register(
			"com.liferay.mobile.device.rules.model.MDRRule",
			mdrRuleLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.mobile.device.rules.model.MDRRule");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return MDRRuleLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return MDRRule.class;
	}

	protected String getModelClassName() {
		return MDRRule.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = mdrRulePersistence.getDataSource();

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

	@BeanReference(type = MDRRuleLocalService.class)
	protected MDRRuleLocalService mdrRuleLocalService;

	@BeanReference(type = MDRRulePersistence.class)
	protected MDRRulePersistence mdrRulePersistence;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.UserLocalService.class
	)
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;

	@BeanReference(
		type = com.liferay.mobile.device.rules.service.MDRRuleGroupLocalService.class
	)
	protected com.liferay.mobile.device.rules.service.MDRRuleGroupLocalService
		mdrRuleGroupLocalService;

	@BeanReference(type = MDRRuleGroupPersistence.class)
	protected MDRRuleGroupPersistence mdrRuleGroupPersistence;

	@BeanReference(type = MDRRuleGroupFinder.class)
	protected MDRRuleGroupFinder mdrRuleGroupFinder;

	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry
		persistedModelLocalServiceRegistry;

}