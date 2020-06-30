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

package com.liferay.polls.service.base;

import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.polls.model.PollsChoice;
import com.liferay.polls.service.PollsChoiceLocalService;
import com.liferay.polls.service.persistence.PollsChoicePersistence;
import com.liferay.polls.service.persistence.PollsQuestionFinder;
import com.liferay.polls.service.persistence.PollsQuestionPersistence;
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
 * Provides the base implementation for the polls choice local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.polls.service.impl.PollsChoiceLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.polls.service.impl.PollsChoiceLocalServiceImpl
 * @generated
 */
public abstract class PollsChoiceLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements IdentifiableOSGiService, PollsChoiceLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>PollsChoiceLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.polls.service.PollsChoiceLocalServiceUtil</code>.
	 */

	/**
	 * Adds the polls choice to the database. Also notifies the appropriate model listeners.
	 *
	 * @param pollsChoice the polls choice
	 * @return the polls choice that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public PollsChoice addPollsChoice(PollsChoice pollsChoice) {
		pollsChoice.setNew(true);

		return pollsChoicePersistence.update(pollsChoice);
	}

	/**
	 * Creates a new polls choice with the primary key. Does not add the polls choice to the database.
	 *
	 * @param choiceId the primary key for the new polls choice
	 * @return the new polls choice
	 */
	@Override
	@Transactional(enabled = false)
	public PollsChoice createPollsChoice(long choiceId) {
		return pollsChoicePersistence.create(choiceId);
	}

	/**
	 * Deletes the polls choice with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param choiceId the primary key of the polls choice
	 * @return the polls choice that was removed
	 * @throws PortalException if a polls choice with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public PollsChoice deletePollsChoice(long choiceId) throws PortalException {
		return pollsChoicePersistence.remove(choiceId);
	}

	/**
	 * Deletes the polls choice from the database. Also notifies the appropriate model listeners.
	 *
	 * @param pollsChoice the polls choice
	 * @return the polls choice that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public PollsChoice deletePollsChoice(PollsChoice pollsChoice) {
		return pollsChoicePersistence.remove(pollsChoice);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			PollsChoice.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return pollsChoicePersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.polls.model.impl.PollsChoiceModelImpl</code>.
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

		return pollsChoicePersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.polls.model.impl.PollsChoiceModelImpl</code>.
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

		return pollsChoicePersistence.findWithDynamicQuery(
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
		return pollsChoicePersistence.countWithDynamicQuery(dynamicQuery);
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

		return pollsChoicePersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public PollsChoice fetchPollsChoice(long choiceId) {
		return pollsChoicePersistence.fetchByPrimaryKey(choiceId);
	}

	/**
	 * Returns the polls choice matching the UUID and group.
	 *
	 * @param uuid the polls choice's UUID
	 * @param groupId the primary key of the group
	 * @return the matching polls choice, or <code>null</code> if a matching polls choice could not be found
	 */
	@Override
	public PollsChoice fetchPollsChoiceByUuidAndGroupId(
		String uuid, long groupId) {

		return pollsChoicePersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the polls choice with the primary key.
	 *
	 * @param choiceId the primary key of the polls choice
	 * @return the polls choice
	 * @throws PortalException if a polls choice with the primary key could not be found
	 */
	@Override
	public PollsChoice getPollsChoice(long choiceId) throws PortalException {
		return pollsChoicePersistence.findByPrimaryKey(choiceId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(pollsChoiceLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(PollsChoice.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("choiceId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			pollsChoiceLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(PollsChoice.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("choiceId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(pollsChoiceLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(PollsChoice.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("choiceId");
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
			new ActionableDynamicQuery.PerformActionMethod<PollsChoice>() {

				@Override
				public void performAction(PollsChoice pollsChoice)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, pollsChoice);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(PollsChoice.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return pollsChoiceLocalService.deletePollsChoice(
			(PollsChoice)persistedModel);
	}

	public BasePersistence<PollsChoice> getBasePersistence() {
		return pollsChoicePersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return pollsChoicePersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the polls choices matching the UUID and company.
	 *
	 * @param uuid the UUID of the polls choices
	 * @param companyId the primary key of the company
	 * @return the matching polls choices, or an empty list if no matches were found
	 */
	@Override
	public List<PollsChoice> getPollsChoicesByUuidAndCompanyId(
		String uuid, long companyId) {

		return pollsChoicePersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of polls choices matching the UUID and company.
	 *
	 * @param uuid the UUID of the polls choices
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of polls choices
	 * @param end the upper bound of the range of polls choices (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching polls choices, or an empty list if no matches were found
	 */
	@Override
	public List<PollsChoice> getPollsChoicesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<PollsChoice> orderByComparator) {

		return pollsChoicePersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the polls choice matching the UUID and group.
	 *
	 * @param uuid the polls choice's UUID
	 * @param groupId the primary key of the group
	 * @return the matching polls choice
	 * @throws PortalException if a matching polls choice could not be found
	 */
	@Override
	public PollsChoice getPollsChoiceByUuidAndGroupId(String uuid, long groupId)
		throws PortalException {

		return pollsChoicePersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the polls choices.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.polls.model.impl.PollsChoiceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of polls choices
	 * @param end the upper bound of the range of polls choices (not inclusive)
	 * @return the range of polls choices
	 */
	@Override
	public List<PollsChoice> getPollsChoices(int start, int end) {
		return pollsChoicePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of polls choices.
	 *
	 * @return the number of polls choices
	 */
	@Override
	public int getPollsChoicesCount() {
		return pollsChoicePersistence.countAll();
	}

	/**
	 * Updates the polls choice in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param pollsChoice the polls choice
	 * @return the polls choice that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public PollsChoice updatePollsChoice(PollsChoice pollsChoice) {
		return pollsChoicePersistence.update(pollsChoice);
	}

	/**
	 * Returns the polls choice local service.
	 *
	 * @return the polls choice local service
	 */
	public PollsChoiceLocalService getPollsChoiceLocalService() {
		return pollsChoiceLocalService;
	}

	/**
	 * Sets the polls choice local service.
	 *
	 * @param pollsChoiceLocalService the polls choice local service
	 */
	public void setPollsChoiceLocalService(
		PollsChoiceLocalService pollsChoiceLocalService) {

		this.pollsChoiceLocalService = pollsChoiceLocalService;
	}

	/**
	 * Returns the polls choice persistence.
	 *
	 * @return the polls choice persistence
	 */
	public PollsChoicePersistence getPollsChoicePersistence() {
		return pollsChoicePersistence;
	}

	/**
	 * Sets the polls choice persistence.
	 *
	 * @param pollsChoicePersistence the polls choice persistence
	 */
	public void setPollsChoicePersistence(
		PollsChoicePersistence pollsChoicePersistence) {

		this.pollsChoicePersistence = pollsChoicePersistence;
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
	 * Returns the polls question local service.
	 *
	 * @return the polls question local service
	 */
	public com.liferay.polls.service.PollsQuestionLocalService
		getPollsQuestionLocalService() {

		return pollsQuestionLocalService;
	}

	/**
	 * Sets the polls question local service.
	 *
	 * @param pollsQuestionLocalService the polls question local service
	 */
	public void setPollsQuestionLocalService(
		com.liferay.polls.service.PollsQuestionLocalService
			pollsQuestionLocalService) {

		this.pollsQuestionLocalService = pollsQuestionLocalService;
	}

	/**
	 * Returns the polls question persistence.
	 *
	 * @return the polls question persistence
	 */
	public PollsQuestionPersistence getPollsQuestionPersistence() {
		return pollsQuestionPersistence;
	}

	/**
	 * Sets the polls question persistence.
	 *
	 * @param pollsQuestionPersistence the polls question persistence
	 */
	public void setPollsQuestionPersistence(
		PollsQuestionPersistence pollsQuestionPersistence) {

		this.pollsQuestionPersistence = pollsQuestionPersistence;
	}

	/**
	 * Returns the polls question finder.
	 *
	 * @return the polls question finder
	 */
	public PollsQuestionFinder getPollsQuestionFinder() {
		return pollsQuestionFinder;
	}

	/**
	 * Sets the polls question finder.
	 *
	 * @param pollsQuestionFinder the polls question finder
	 */
	public void setPollsQuestionFinder(
		PollsQuestionFinder pollsQuestionFinder) {

		this.pollsQuestionFinder = pollsQuestionFinder;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register(
			"com.liferay.polls.model.PollsChoice", pollsChoiceLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.polls.model.PollsChoice");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return PollsChoiceLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return PollsChoice.class;
	}

	protected String getModelClassName() {
		return PollsChoice.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = pollsChoicePersistence.getDataSource();

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

	@BeanReference(type = PollsChoiceLocalService.class)
	protected PollsChoiceLocalService pollsChoiceLocalService;

	@BeanReference(type = PollsChoicePersistence.class)
	protected PollsChoicePersistence pollsChoicePersistence;

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
		type = com.liferay.polls.service.PollsQuestionLocalService.class
	)
	protected com.liferay.polls.service.PollsQuestionLocalService
		pollsQuestionLocalService;

	@BeanReference(type = PollsQuestionPersistence.class)
	protected PollsQuestionPersistence pollsQuestionPersistence;

	@BeanReference(type = PollsQuestionFinder.class)
	protected PollsQuestionFinder pollsQuestionFinder;

	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry
		persistedModelLocalServiceRegistry;

}