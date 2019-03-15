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

package com.liferay.data.engine.service.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.data.engine.exception.DEDataLayoutException;
import com.liferay.data.engine.model.DEDataDefinition;
import com.liferay.data.engine.model.DEDataLayout;
import com.liferay.data.engine.model.DEDataLayoutColumn;
import com.liferay.data.engine.model.DEDataLayoutPage;
import com.liferay.data.engine.model.DEDataLayoutRow;
import com.liferay.data.engine.service.DEDataDefinitionService;
import com.liferay.data.engine.service.DEDataLayoutCountRequest;
import com.liferay.data.engine.service.DEDataLayoutCountResponse;
import com.liferay.data.engine.service.DEDataLayoutDeleteRequest;
import com.liferay.data.engine.service.DEDataLayoutDeleteResponse;
import com.liferay.data.engine.service.DEDataLayoutGetRequest;
import com.liferay.data.engine.service.DEDataLayoutGetResponse;
import com.liferay.data.engine.service.DEDataLayoutListRequest;
import com.liferay.data.engine.service.DEDataLayoutListResponse;
import com.liferay.data.engine.service.DEDataLayoutRequestBuilder;
import com.liferay.data.engine.service.DEDataLayoutSaveRequest;
import com.liferay.data.engine.service.DEDataLayoutSaveResponse;
import com.liferay.data.engine.service.DEDataLayoutService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Jeyvison Nascimento
 */
@RunWith(Arquillian.class)
public class DEDataLayoutServiceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		_user = UserTestUtil.addOmniAdminUser();
	}

	@Test
	public void testCount() throws Exception {
		saveDataLayout(_group, _user, "layout1", "this is a layout1");
		saveDataLayout(_group, _user, "layout2", "this is a layout2");

		int total = countDEDataLayouts(_group);

		Assert.assertEquals(2, total);
	}

	@Test
	public void testCountAfterDelete() throws Exception {
		DEDataLayout deDataLayout =
			saveDataLayout(_group, _user, "layout", "this is a layout");

		int total = countDEDataLayouts(_group);

		Assert.assertEquals(1, total);

		deleteDEDataLayout(_user, _group, deDataLayout.getDEDataLayoutId());

		int totalAfterDelete = countDEDataLayouts(_group);

		Assert.assertEquals(0, totalAfterDelete);
	}

	@Test
	public void testCountWithNoRecords() throws Exception {
		int total = countDEDataLayouts(_group);

		Assert.assertEquals(0, total);
	}

	@Test
	public void testCountWithoutPermission() throws Exception {
		saveDataLayout(_group, _user, "layout1", "this is a layout1");

		Group otherGroup = GroupTestUtil.addGroup();

		int total = countDEDataLayouts(otherGroup);

		Assert.assertEquals(0, total);
	}

	@Test
	public void testCreateDEDataLayout() throws Exception {
		DEDataLayout deDataLayout = saveDataLayout(
			_group, _user, "layout", "this is a layout");

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group, _user.getGroupId());

		ServiceContextThreadLocal.pushServiceContext(serviceContext);

		DEDataLayoutGetRequest deDataLayoutGetRequest =
			DEDataLayoutRequestBuilder.getBuilder(
			).byId(
				deDataLayout.getDEDataLayoutId()
			).build();

		DEDataLayoutGetResponse deDataLayoutGetResponse =
			_deDataLayoutService.execute(deDataLayoutGetRequest);

		Assert.assertEquals(
			deDataLayout, deDataLayoutGetResponse.getDEDataLayout());
	}

	@Test
	public void testCreateTwoDEDataLayoutsForOneDEDataDefinition()
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group, _user.getGroupId());

		ServiceContextThreadLocal.pushServiceContext(serviceContext);

		DEDataDefinition deDataDefinition =
			DEDataEngineTestUtil.insertDEDataDefinition(
				_user, _group, "description", "definition",
				_deDataDefinitionService);

		DEDataLayout deDataLayout1 = _createDEDataLayout(
			"layout", "this is a layout", "wizard", "en_US");

		deDataLayout1.setDEDataDefinitionId(
			deDataDefinition.getDEDataDefinitionId());

		DEDataLayoutSaveRequest deDataLayoutSaveRequest =
			DEDataLayoutRequestBuilder.saveBuilder(
				deDataLayout1
			).inGroup(
				_user.getGroupId()
			).onBehalfOf(
				_user.getUserId()
			).build();

		DEDataLayoutSaveResponse deDataLayoutSaveResponse =
			_deDataLayoutService.execute(deDataLayoutSaveRequest);

		DEDataLayout deDataLayout2 = _createDEDataLayout(
			"layout", "this is a layout", "wizard", "en_US");

		deDataLayout2.setDEDataDefinitionId(
			deDataDefinition.getDEDataDefinitionId());

		deDataLayoutSaveRequest = DEDataLayoutRequestBuilder.saveBuilder(
			deDataLayout2
		).inGroup(
			_user.getGroupId()
		).onBehalfOf(
			_user.getUserId()
		).build();

		deDataLayoutSaveResponse = _deDataLayoutService.execute(
			deDataLayoutSaveRequest);

		DEDataLayoutGetRequest deDataLayoutGetRequest =
			DEDataLayoutRequestBuilder.getBuilder(
			).byId(
				deDataLayoutSaveResponse.getDEDataLayoutId()
			).build();

		DEDataLayoutGetResponse deDataLayoutGetResponse =
			_deDataLayoutService.execute(deDataLayoutGetRequest);

		deDataLayout2 = deDataLayoutGetResponse.getDEDataLayout();

		Assert.assertEquals(
			deDataLayout1.getDEDataDefinitionId(),
			deDataLayout2.getDEDataDefinitionId());
	}

	@Test
	public void testDeleteDEDataLayout() throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group, _user.getGroupId());

		ServiceContextThreadLocal.pushServiceContext(serviceContext);

		DEDataDefinition deDataDefinition =
			DEDataEngineTestUtil.insertDEDataDefinition(
				_user, _group, "description", "definition",
				_deDataDefinitionService);

		DEDataLayout deDataLayout = _createDEDataLayout(
			"layout", "this is a layout", "wizard", "en_US");

		deDataLayout.setDEDataDefinitionId(
			deDataDefinition.getDEDataDefinitionId());

		DEDataLayoutSaveRequest deDataLayoutSaveRequest =
			DEDataLayoutRequestBuilder.saveBuilder(
				deDataLayout
			).inGroup(
				_user.getGroupId()
			).onBehalfOf(
				_user.getUserId()
			).build();

		DEDataLayoutSaveResponse deDataLayoutSaveResponse =
			_deDataLayoutService.execute(deDataLayoutSaveRequest);

		deDataLayout.setDEDataLayoutId(
			deDataLayoutSaveResponse.getDEDataLayoutId());

		DEDataLayoutDeleteRequest deDataLayoutDeleteRequest =
			DEDataLayoutRequestBuilder.deleteBuilder(
			).byId(
				deDataLayoutSaveResponse.getDEDataLayoutId()
			).build();

		DEDataLayoutDeleteResponse deDataLayoutDeleteResponse =
			_deDataLayoutService.execute(deDataLayoutDeleteRequest);

		Assert.assertEquals(
			deDataLayout.getDEDataLayoutId(),
			deDataLayoutDeleteResponse.getDEDataLayoutId());
	}

	@Test(expected = DEDataLayoutException.class)
	public void testDeleteMultiplesDEDataLayout() throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group, _user.getGroupId());

		ServiceContextThreadLocal.pushServiceContext(serviceContext);

		List<DEDataLayoutSaveResponse> deDataLayoutSaveResponses =
			new ArrayList<>();

		ServiceContextThreadLocal.pushServiceContext(serviceContext);

		for (int i = 0; i < 5; i++) {
			DEDataDefinition deDataDefinition =
				DEDataEngineTestUtil.insertDEDataDefinition(
					_user, _group, "description" + i, "definition" + i,
					_deDataDefinitionService);

			DEDataLayout deDataLayout = _createDEDataLayout(
				"layout", "this is a layout", "wizard", "en_US");

			deDataLayout.setDEDataDefinitionId(
				deDataDefinition.getDEDataDefinitionId());

			DEDataLayoutSaveRequest deDataLayoutSaveRequest =
				DEDataLayoutRequestBuilder.saveBuilder(
					deDataLayout
				).inGroup(
					_user.getGroupId()
				).onBehalfOf(
					_user.getUserId()
				).build();

			DEDataLayoutSaveResponse deDataLayoutSaveResponse =
				_deDataLayoutService.execute(deDataLayoutSaveRequest);

			deDataLayout.setDEDataLayoutId(
				deDataLayoutSaveResponse.getDEDataLayoutId());

			deDataLayoutSaveResponses.add(deDataLayoutSaveResponse);
		}

		for (DEDataLayoutSaveResponse deDataLayoutSaveResponse :
				deDataLayoutSaveResponses) {

			DEDataLayoutDeleteRequest deDataLayoutDeleteRequest =
				DEDataLayoutRequestBuilder.deleteBuilder(
				).byId(
					deDataLayoutSaveResponse.getDEDataLayoutId()
				).build();

			_deDataLayoutService.execute(deDataLayoutDeleteRequest);
		}

		DEDataLayoutGetRequest deDataLayoutGetRequest =
			DEDataLayoutRequestBuilder.getBuilder(
			).byId(
				deDataLayoutSaveResponses.get(
					0
				).getDEDataLayoutId()
			).build();

		_deDataLayoutService.execute(deDataLayoutGetRequest);
	}

	@Test(expected = DEDataLayoutException.class)
	public void testDeleteOneInCollectionDEDataLayout() throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group, _user.getGroupId());

		ServiceContextThreadLocal.pushServiceContext(serviceContext);

		List<DEDataLayoutSaveResponse> deDataLayoutSaveResponses =
			new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			DEDataDefinition deDataDefinition =
				DEDataEngineTestUtil.insertDEDataDefinition(
					_user, _group, "description" + i, "definition" + i,
					_deDataDefinitionService);

			DEDataLayout deDataLayout = _createDEDataLayout(
				"layout", "this is a layout", "wizard", "en_US");

			deDataLayout.setDEDataDefinitionId(
				deDataDefinition.getDEDataDefinitionId());

			DEDataLayoutSaveRequest deDataLayoutSaveRequest =
				DEDataLayoutRequestBuilder.saveBuilder(
					deDataLayout
				).inGroup(
					_user.getGroupId()
				).onBehalfOf(
					_user.getUserId()
				).build();

			DEDataLayoutSaveResponse deDataLayoutSaveResponse =
				_deDataLayoutService.execute(deDataLayoutSaveRequest);

			deDataLayout.setDEDataLayoutId(
				deDataLayoutSaveResponse.getDEDataLayoutId());

			deDataLayoutSaveResponses.add(deDataLayoutSaveResponse);
		}

		DEDataLayoutDeleteRequest deDataLayoutDeleteRequest =
			DEDataLayoutRequestBuilder.deleteBuilder(
			).byId(
				deDataLayoutSaveResponses.get(
					0
				).getDEDataLayoutId()
			).build();

		_deDataLayoutService.execute(deDataLayoutDeleteRequest);

		for (DEDataLayoutSaveResponse deDataLayoutSaveResponse :
				deDataLayoutSaveResponses) {

			DEDataLayoutGetRequest deDataLayoutGetRequest =
				DEDataLayoutRequestBuilder.getBuilder(
				).byId(
					deDataLayoutSaveResponse.getDEDataLayoutId()
				).build();

			_deDataLayoutService.execute(deDataLayoutGetRequest);
		}
	}

	@Test(expected = DEDataLayoutException.class)
	public void testDoNotSaveDEDataLayoutWithInvalidDEDataDefinition()
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group, _user.getGroupId());

		ServiceContextThreadLocal.pushServiceContext(serviceContext);

		DEDataLayout deDataLayout1 = _createDEDataLayout(
			"layout", "this is a layout", "wizard", "en_US");

		deDataLayout1.setDEDataDefinitionId(-1L);

		DEDataLayoutSaveRequest deDataLayoutSaveRequest =
			DEDataLayoutRequestBuilder.saveBuilder(
				deDataLayout1
			).inGroup(
				_user.getGroupId()
			).onBehalfOf(
				_user.getUserId()
			).build();

		_deDataLayoutService.execute(deDataLayoutSaveRequest);
	}

	@Test(expected = DEDataLayoutException.class)
	public void testDoNotSaveDEDataLayoutWithInvalidName() throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group, _user.getGroupId());

		ServiceContextThreadLocal.pushServiceContext(serviceContext);

		DEDataDefinition deDataDefinition =
			DEDataEngineTestUtil.insertDEDataDefinition(
				_user, _group, "description", "definition",
				_deDataDefinitionService);

		DEDataLayout deDataLayout = _createDEDataLayout(
			"layout", "this is a layout", "wizard", "en_US");

		deDataLayout.setDEDataDefinitionId(
			deDataDefinition.getDEDataDefinitionId());

		deDataLayout.setName(null);

		DEDataLayoutSaveRequest deDataLayoutSaveRequest =
			DEDataLayoutRequestBuilder.saveBuilder(
				deDataLayout
			).inGroup(
				_user.getGroupId()
			).onBehalfOf(
				_user.getUserId()
			).build();

		_deDataLayoutService.execute(deDataLayoutSaveRequest);
	}

	@Test(expected = DEDataLayoutException.class)
	public void testDoNotSaveRemovedDEDataLayout() throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group, _user.getGroupId());

		ServiceContextThreadLocal.pushServiceContext(serviceContext);

		DEDataDefinition deDataDefinition =
			DEDataEngineTestUtil.insertDEDataDefinition(
				_user, _group, "description", "definition",
				_deDataDefinitionService);

		DEDataLayout deDataLayout = _createDEDataLayout(
			"layout", "this is a layout", "wizard", "en_US");

		deDataLayout.setDEDataDefinitionId(
			deDataDefinition.getDEDataDefinitionId());

		deDataLayout.setDEDataLayoutId(-1L);

		DEDataLayoutSaveRequest deDataLayoutSaveRequest =
			DEDataLayoutRequestBuilder.saveBuilder(
				deDataLayout
			).inGroup(
				_user.getGroupId()
			).onBehalfOf(
				_user.getUserId()
			).build();

		_deDataLayoutService.execute(deDataLayoutSaveRequest);
	}

	@Test
	public void testListDEDataLayout() throws Exception {
		int total = 5;

		for (int i = 0; i < total; i++) {
			saveDataLayout(_group, _user, "layout", "this is a layout");
		}

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group, _user.getGroupId());

		ServiceContextThreadLocal.pushServiceContext(serviceContext);

		DEDataLayoutListRequest deDataLayoutListRequest =
			DEDataLayoutRequestBuilder.listBuilder(
			).inGroup(
				_group.getGroupId()
			).build();

		DEDataLayoutListResponse deDataLayoutListResponse =
			_deDataLayoutService.execute(deDataLayoutListRequest);

		List<DEDataLayout> deDataLayouts =
			deDataLayoutListResponse.getDEDataLayouts();

		Assert.assertEquals(deDataLayouts.toString(), 5, deDataLayouts.size());
	}

	@Test
	public void testListDEDataLayoutInvalidGroup() throws Exception {
		int total = 5;

		for (int i = 0; i < total; i++) {
			saveDataLayout(_group, _user, "layout", "this is a layout");
		}

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group, _user.getGroupId());

		ServiceContextThreadLocal.pushServiceContext(serviceContext);

		DEDataLayoutListRequest deDataLayoutListRequest =
			DEDataLayoutRequestBuilder.listBuilder(
			).inGroup(
				-1
			).build();

		DEDataLayoutListResponse deDataLayoutListResponse =
			_deDataLayoutService.execute(deDataLayoutListRequest);

		List<DEDataLayout> deDataLayouts =
			deDataLayoutListResponse.getDEDataLayouts();

		Assert.assertEquals(deDataLayouts.toString(), 0, deDataLayouts.size());
	}

	@Test
	public void testListDEDataLayoutWithNoData() throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group, _user.getGroupId());

		ServiceContextThreadLocal.pushServiceContext(serviceContext);

		DEDataLayoutListRequest deDataLayoutListRequest =
			DEDataLayoutRequestBuilder.listBuilder(
			).inGroup(
				_group.getGroupId()
			).build();

		DEDataLayoutListResponse deDataLayoutListResponse =
			_deDataLayoutService.execute(deDataLayoutListRequest);

		List<DEDataLayout> deDataLayouts =
			deDataLayoutListResponse.getDEDataLayouts();

		Assert.assertEquals(deDataLayouts.toString(), 0, deDataLayouts.size());
	}

	@Test
	public void testListDEDataLayoutWithNoGroup() throws Exception {
		int total = 5;

		for (int i = 0; i < total; i++) {
			saveDataLayout(_group, _user, "layout", "this is a layout");
		}

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group, _user.getGroupId());

		ServiceContextThreadLocal.pushServiceContext(serviceContext);

		DEDataLayoutListRequest deDataLayoutListRequest =
			DEDataLayoutRequestBuilder.listBuilder(
			).build();

		DEDataLayoutListResponse deDataLayoutListResponse =
			_deDataLayoutService.execute(deDataLayoutListRequest);

		List<DEDataLayout> deDataLayouts =
			deDataLayoutListResponse.getDEDataLayouts();

		Assert.assertEquals(deDataLayouts.toString(), 0, deDataLayouts.size());
	}

	@Test
	public void testListDEDataLayoutWithPagination() throws Exception {
		int total = 5;

		for (int i = 0; i < total; i++) {
			saveDataLayout(_group, _user, "layout", "this is a layout");
		}

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group, _user.getGroupId());

		ServiceContextThreadLocal.pushServiceContext(serviceContext);

		DEDataLayoutListRequest deDataLayoutListRequest =
			DEDataLayoutRequestBuilder.listBuilder(
			).startingAt(
				4
			).endingAt(
				5
			).inGroup(
				_group.getGroupId()
			).build();

		DEDataLayoutListResponse deDataLayoutListResponse =
			_deDataLayoutService.execute(deDataLayoutListRequest);

		List<DEDataLayout> deDataLayouts =
			deDataLayoutListResponse.getDEDataLayouts();

		Assert.assertEquals(deDataLayouts.toString(), 1, deDataLayouts.size());
	}

	@Test(expected = DEDataLayoutException.class)
	public void testSaveDEDataLayoutWithNoDataDefinition() throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group, _user.getGroupId());

		ServiceContextThreadLocal.pushServiceContext(serviceContext);

		DEDataLayout deDataLayout = _createDEDataLayout(
			"layout", "this is a layout", "wizard", "en_US");

		DEDataLayoutSaveRequest deDataLayoutSaveRequest =
			DEDataLayoutRequestBuilder.saveBuilder(
				deDataLayout
			).inGroup(
				_user.getGroupId()
			).onBehalfOf(
				_user.getUserId()
			).build();

		_deDataLayoutService.execute(deDataLayoutSaveRequest);
	}

	@Test(expected = DEDataLayoutException.class)
	public void testSaveDEDataLayoutWithNonexistentUser() throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group, _user.getGroupId());

		ServiceContextThreadLocal.pushServiceContext(serviceContext);

		DEDataDefinition deDataDefinition =
			DEDataEngineTestUtil.insertDEDataDefinition(
				_user, _group, "description", "definition",
				_deDataDefinitionService);

		DEDataLayout deDataLayout = _createDEDataLayout(
			"layout", "this is a layout", "wizard", "en_US");

		deDataLayout.setDEDataDefinitionId(
			deDataDefinition.getDEDataDefinitionId());

		DEDataLayoutSaveRequest deDataLayoutSaveRequest =
			DEDataLayoutRequestBuilder.saveBuilder(
				deDataLayout
			).inGroup(
				_user.getGroupId()
			).onBehalfOf(
				-1L
			).build();

		_deDataLayoutService.execute(deDataLayoutSaveRequest);
	}

	@Test
	public void testUpdateDEDataLayout() throws Exception {
		DEDataLayout deDataLayout = saveDataLayout(
			_group, _user, "layout", "this is a layout");

		deDataLayout.setPaginationMode("pagination");

		DEDataLayoutSaveRequest deDataLayoutSaveRequest =
			DEDataLayoutRequestBuilder.saveBuilder(
				deDataLayout
			).inGroup(
				_user.getGroupId()
			).onBehalfOf(
				_user.getUserId()
			).build();

		DEDataLayoutSaveResponse deDataLayoutSaveResponse =
			_deDataLayoutService.execute(deDataLayoutSaveRequest);

		DEDataLayoutGetRequest deDataLayoutGetRequest =
			DEDataLayoutRequestBuilder.getBuilder(
			).byId(
				deDataLayoutSaveResponse.getDEDataLayoutId()
			).build();

		DEDataLayoutGetResponse deDataLayoutGetResponse =
			_deDataLayoutService.execute(deDataLayoutGetRequest);

		Assert.assertEquals(
			deDataLayout, deDataLayoutGetResponse.getDEDataLayout());
	}

	protected int countDEDataLayouts(Group group) throws Exception {
		DEDataLayoutCountRequest deDataLayoutCountRequest =
			DEDataLayoutRequestBuilder.countBuilder(
			).inGroup(
				group.getGroupId()
			).build();

		DEDataLayoutCountResponse deDataLayoutCountResponse =
			_deDataLayoutService.execute(deDataLayoutCountRequest);

		return deDataLayoutCountResponse.getTotal();
	}

	protected DEDataLayout saveDataLayout(
			Group group, User user, String nameLayout, String descriptionLayout)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(group, user.getGroupId());

		ServiceContextThreadLocal.pushServiceContext(serviceContext);

		DEDataDefinition deDataDefinition =
			DEDataEngineTestUtil.insertDEDataDefinition(
				user, group, "description", "definition",
				_deDataDefinitionService);

		DEDataLayout deDataLayout = _createDEDataLayout(
			nameLayout, descriptionLayout, "wizard", "en_US");

		deDataLayout.setDEDataDefinitionId(
			deDataDefinition.getDEDataDefinitionId());

		DEDataLayoutSaveRequest deDataLayoutSaveRequest =
			DEDataLayoutRequestBuilder.saveBuilder(
				deDataLayout
			).inGroup(
				group.getGroupId()
			).onBehalfOf(
				user.getUserId()
			).build();

		DEDataLayoutSaveResponse deDataLayoutSaveResponse =
			_deDataLayoutService.execute(deDataLayoutSaveRequest);

		deDataLayout.setDEDataLayoutId(
			deDataLayoutSaveResponse.getDEDataLayoutId());

		return deDataLayout;
	}

	private DEDataLayout _createDEDataLayout(
		String name, String description, String paginationMode,
		String languageId) {

		DEDataLayoutColumn deDataLayoutColumn = _createDEDataLayoutColumn(
			12, "field");

		Queue<DEDataLayoutColumn> deDataLayoutColumns = new ArrayDeque<>();

		deDataLayoutColumns.add(deDataLayoutColumn);

		DEDataLayoutRow deDataLayoutRow = _createDEDataLayoutRow(
			deDataLayoutColumns);

		Queue<DEDataLayoutRow> deDataLayoutRows = new ArrayDeque<>();

		deDataLayoutRows.add(deDataLayoutRow);

		DEDataLayoutPage deDataLayoutPage = _createDEDataLayoutPage(
			StringPool.BLANK, "Page", deDataLayoutRows);

		Queue<DEDataLayoutPage> deDataLayoutPages = new ArrayDeque<>();

		deDataLayoutPages.add(deDataLayoutPage);

		Map<Locale, String> nameMap = new HashMap<>();

		nameMap.put(LocaleUtil.US, name);

		Map<Locale, String> descriptionMap = new HashMap<>();

		descriptionMap.put(LocaleUtil.US, description);

		DEDataLayout deDataLayout = new DEDataLayout();

		deDataLayout.setName(nameMap);
		deDataLayout.setDescription(descriptionMap);
		deDataLayout.setDEDataLayoutPages(deDataLayoutPages);
		deDataLayout.setPaginationMode(paginationMode);
		deDataLayout.setDefaultLanguageId(languageId);

		return deDataLayout;
	}

	private DEDataLayoutColumn _createDEDataLayoutColumn(
		int size, String fieldName) {

		DEDataLayoutColumn deDataLayoutColumn = new DEDataLayoutColumn();

		deDataLayoutColumn.setColumnSize(size);

		List<String> fieldsName = new ArrayList<>();

		fieldsName.add(fieldName);

		deDataLayoutColumn.setFieldsName(fieldsName);

		return deDataLayoutColumn;
	}

	private DEDataLayoutPage _createDEDataLayoutPage(
		String description, String title,
		Queue<DEDataLayoutRow> deDataLayoutRows) {

		DEDataLayoutPage deDataLayoutPage = new DEDataLayoutPage();

		Map<String, String> titleMap = new HashMap<>();

		titleMap.put("en_US", title);

		Map<String, String> descriptionMap = new HashMap<>();

		descriptionMap.put("en_US", description);

		deDataLayoutPage.setTitle(titleMap);
		deDataLayoutPage.setDescription(descriptionMap);
		deDataLayoutPage.setDEDataLayoutRows(deDataLayoutRows);

		return deDataLayoutPage;
	}

	protected void deleteDEDataLayout(User user, Group group,
			long deDataLayoutId)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				group.getGroupId(), user.getUserId());

		ServiceContextThreadLocal.pushServiceContext(serviceContext);

		try {
			DEDataLayoutDeleteRequest deDataLayoutDeleteRequest =
				DEDataLayoutRequestBuilder.deleteBuilder(
				).byId(
					deDataLayoutId
				).build();

				_deDataLayoutService.execute(deDataLayoutDeleteRequest);
		}
		finally {
			ServiceContextThreadLocal.popServiceContext();
		}
	}

	private DEDataLayoutRow _createDEDataLayoutRow(
		Queue<DEDataLayoutColumn> deDataLayoutColumns) {

		DEDataLayoutRow deDataLayoutRow = new DEDataLayoutRow();

		deDataLayoutRow.setDEDataLayoutColumns(deDataLayoutColumns);

		return deDataLayoutRow;
	}

	@Inject(type = DEDataDefinitionService.class)
	private DEDataDefinitionService _deDataDefinitionService;

	@Inject(type = DEDataLayoutService.class)
	private DEDataLayoutService _deDataLayoutService;

	private Group _group;
	private User _user;

}