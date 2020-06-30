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

package com.liferay.headless.commerce.delivery.catalog.resource.v1_0.test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import com.liferay.headless.commerce.delivery.catalog.client.dto.v1_0.Attachment;
import com.liferay.headless.commerce.delivery.catalog.client.http.HttpInvoker;
import com.liferay.headless.commerce.delivery.catalog.client.pagination.Page;
import com.liferay.headless.commerce.delivery.catalog.client.pagination.Pagination;
import com.liferay.headless.commerce.delivery.catalog.client.resource.v1_0.AttachmentResource;
import com.liferay.headless.commerce.delivery.catalog.client.serdes.v1_0.AttachmentSerDes;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.vulcan.resource.EntityModelResource;

import java.lang.reflect.InvocationTargetException;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Generated;

import javax.ws.rs.core.MultivaluedHashMap;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang.time.DateUtils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Andrea Sbarra
 * @generated
 */
@Generated("")
public abstract class BaseAttachmentResourceTestCase {

	@ClassRule
	@Rule
	public static final LiferayIntegrationTestRule liferayIntegrationTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() throws Exception {
		_dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");
	}

	@Before
	public void setUp() throws Exception {
		irrelevantGroup = GroupTestUtil.addGroup();
		testGroup = GroupTestUtil.addGroup();

		testCompany = CompanyLocalServiceUtil.getCompany(
			testGroup.getCompanyId());

		_attachmentResource.setContextCompany(testCompany);

		AttachmentResource.Builder builder = AttachmentResource.builder();

		attachmentResource = builder.locale(
			LocaleUtil.getDefault()
		).build();
	}

	@After
	public void tearDown() throws Exception {
		GroupTestUtil.deleteGroup(irrelevantGroup);
		GroupTestUtil.deleteGroup(testGroup);
	}

	@Test
	public void testClientSerDesToDTO() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper() {
			{
				configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
				configure(
					SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
				enable(SerializationFeature.INDENT_OUTPUT);
				setDateFormat(new ISO8601DateFormat());
				setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				setSerializationInclusion(JsonInclude.Include.NON_NULL);
				setVisibility(
					PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
				setVisibility(
					PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
			}
		};

		Attachment attachment1 = randomAttachment();

		String json = objectMapper.writeValueAsString(attachment1);

		Attachment attachment2 = AttachmentSerDes.toDTO(json);

		Assert.assertTrue(equals(attachment1, attachment2));
	}

	@Test
	public void testClientSerDesToJSON() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper() {
			{
				configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
				configure(
					SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
				setDateFormat(new ISO8601DateFormat());
				setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				setSerializationInclusion(JsonInclude.Include.NON_NULL);
				setVisibility(
					PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
				setVisibility(
					PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
			}
		};

		Attachment attachment = randomAttachment();

		String json1 = objectMapper.writeValueAsString(attachment);
		String json2 = AttachmentSerDes.toJSON(attachment);

		Assert.assertEquals(
			objectMapper.readTree(json1), objectMapper.readTree(json2));
	}

	@Test
	public void testEscapeRegexInStringFields() throws Exception {
		String regex = "^[0-9]+(\\.[0-9]{1,2})\"?";

		Attachment attachment = randomAttachment();

		attachment.setAttachment(regex);
		attachment.setSrc(regex);
		attachment.setTitle(regex);

		String json = AttachmentSerDes.toJSON(attachment);

		Assert.assertFalse(json.contains(regex));

		attachment = AttachmentSerDes.toDTO(json);

		Assert.assertEquals(regex, attachment.getAttachment());
		Assert.assertEquals(regex, attachment.getSrc());
		Assert.assertEquals(regex, attachment.getTitle());
	}

	@Test
	public void testGetChannelProductAttachmentsPage() throws Exception {
		Page<Attachment> page =
			attachmentResource.getChannelProductAttachmentsPage(
				testGetChannelProductAttachmentsPage_getChannelId(),
				testGetChannelProductAttachmentsPage_getProductId(),
				Pagination.of(1, 2));

		Assert.assertEquals(0, page.getTotalCount());

		Long channelId = testGetChannelProductAttachmentsPage_getChannelId();
		Long irrelevantChannelId =
			testGetChannelProductAttachmentsPage_getIrrelevantChannelId();
		Long productId = testGetChannelProductAttachmentsPage_getProductId();
		Long irrelevantProductId =
			testGetChannelProductAttachmentsPage_getIrrelevantProductId();

		if ((irrelevantChannelId != null) && (irrelevantProductId != null)) {
			Attachment irrelevantAttachment =
				testGetChannelProductAttachmentsPage_addAttachment(
					irrelevantChannelId, irrelevantProductId,
					randomIrrelevantAttachment());

			page = attachmentResource.getChannelProductAttachmentsPage(
				irrelevantChannelId, irrelevantProductId, Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantAttachment),
				(List<Attachment>)page.getItems());
			assertValid(page);
		}

		Attachment attachment1 =
			testGetChannelProductAttachmentsPage_addAttachment(
				channelId, productId, randomAttachment());

		Attachment attachment2 =
			testGetChannelProductAttachmentsPage_addAttachment(
				channelId, productId, randomAttachment());

		page = attachmentResource.getChannelProductAttachmentsPage(
			channelId, productId, Pagination.of(1, 2));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(attachment1, attachment2),
			(List<Attachment>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetChannelProductAttachmentsPageWithPagination()
		throws Exception {

		Long channelId = testGetChannelProductAttachmentsPage_getChannelId();
		Long productId = testGetChannelProductAttachmentsPage_getProductId();

		Attachment attachment1 =
			testGetChannelProductAttachmentsPage_addAttachment(
				channelId, productId, randomAttachment());

		Attachment attachment2 =
			testGetChannelProductAttachmentsPage_addAttachment(
				channelId, productId, randomAttachment());

		Attachment attachment3 =
			testGetChannelProductAttachmentsPage_addAttachment(
				channelId, productId, randomAttachment());

		Page<Attachment> page1 =
			attachmentResource.getChannelProductAttachmentsPage(
				channelId, productId, Pagination.of(1, 2));

		List<Attachment> attachments1 = (List<Attachment>)page1.getItems();

		Assert.assertEquals(attachments1.toString(), 2, attachments1.size());

		Page<Attachment> page2 =
			attachmentResource.getChannelProductAttachmentsPage(
				channelId, productId, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<Attachment> attachments2 = (List<Attachment>)page2.getItems();

		Assert.assertEquals(attachments2.toString(), 1, attachments2.size());

		Page<Attachment> page3 =
			attachmentResource.getChannelProductAttachmentsPage(
				channelId, productId, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(attachment1, attachment2, attachment3),
			(List<Attachment>)page3.getItems());
	}

	protected Attachment testGetChannelProductAttachmentsPage_addAttachment(
			Long channelId, Long productId, Attachment attachment)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long testGetChannelProductAttachmentsPage_getChannelId()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long testGetChannelProductAttachmentsPage_getIrrelevantChannelId()
		throws Exception {

		return null;
	}

	protected Long testGetChannelProductAttachmentsPage_getProductId()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long testGetChannelProductAttachmentsPage_getIrrelevantProductId()
		throws Exception {

		return null;
	}

	@Test
	public void testGetChannelProductImagesPage() throws Exception {
		Page<Attachment> page = attachmentResource.getChannelProductImagesPage(
			testGetChannelProductImagesPage_getChannelId(),
			testGetChannelProductImagesPage_getProductId(),
			Pagination.of(1, 2));

		Assert.assertEquals(0, page.getTotalCount());

		Long channelId = testGetChannelProductImagesPage_getChannelId();
		Long irrelevantChannelId =
			testGetChannelProductImagesPage_getIrrelevantChannelId();
		Long productId = testGetChannelProductImagesPage_getProductId();
		Long irrelevantProductId =
			testGetChannelProductImagesPage_getIrrelevantProductId();

		if ((irrelevantChannelId != null) && (irrelevantProductId != null)) {
			Attachment irrelevantAttachment =
				testGetChannelProductImagesPage_addAttachment(
					irrelevantChannelId, irrelevantProductId,
					randomIrrelevantAttachment());

			page = attachmentResource.getChannelProductImagesPage(
				irrelevantChannelId, irrelevantProductId, Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantAttachment),
				(List<Attachment>)page.getItems());
			assertValid(page);
		}

		Attachment attachment1 = testGetChannelProductImagesPage_addAttachment(
			channelId, productId, randomAttachment());

		Attachment attachment2 = testGetChannelProductImagesPage_addAttachment(
			channelId, productId, randomAttachment());

		page = attachmentResource.getChannelProductImagesPage(
			channelId, productId, Pagination.of(1, 2));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(attachment1, attachment2),
			(List<Attachment>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetChannelProductImagesPageWithPagination()
		throws Exception {

		Long channelId = testGetChannelProductImagesPage_getChannelId();
		Long productId = testGetChannelProductImagesPage_getProductId();

		Attachment attachment1 = testGetChannelProductImagesPage_addAttachment(
			channelId, productId, randomAttachment());

		Attachment attachment2 = testGetChannelProductImagesPage_addAttachment(
			channelId, productId, randomAttachment());

		Attachment attachment3 = testGetChannelProductImagesPage_addAttachment(
			channelId, productId, randomAttachment());

		Page<Attachment> page1 = attachmentResource.getChannelProductImagesPage(
			channelId, productId, Pagination.of(1, 2));

		List<Attachment> attachments1 = (List<Attachment>)page1.getItems();

		Assert.assertEquals(attachments1.toString(), 2, attachments1.size());

		Page<Attachment> page2 = attachmentResource.getChannelProductImagesPage(
			channelId, productId, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<Attachment> attachments2 = (List<Attachment>)page2.getItems();

		Assert.assertEquals(attachments2.toString(), 1, attachments2.size());

		Page<Attachment> page3 = attachmentResource.getChannelProductImagesPage(
			channelId, productId, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(attachment1, attachment2, attachment3),
			(List<Attachment>)page3.getItems());
	}

	protected Attachment testGetChannelProductImagesPage_addAttachment(
			Long channelId, Long productId, Attachment attachment)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long testGetChannelProductImagesPage_getChannelId()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long testGetChannelProductImagesPage_getIrrelevantChannelId()
		throws Exception {

		return null;
	}

	protected Long testGetChannelProductImagesPage_getProductId()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long testGetChannelProductImagesPage_getIrrelevantProductId()
		throws Exception {

		return null;
	}

	protected Attachment testGraphQLAttachment_addAttachment()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(
		Attachment attachment1, Attachment attachment2) {

		Assert.assertTrue(
			attachment1 + " does not equal " + attachment2,
			equals(attachment1, attachment2));
	}

	protected void assertEquals(
		List<Attachment> attachments1, List<Attachment> attachments2) {

		Assert.assertEquals(attachments1.size(), attachments2.size());

		for (int i = 0; i < attachments1.size(); i++) {
			Attachment attachment1 = attachments1.get(i);
			Attachment attachment2 = attachments2.get(i);

			assertEquals(attachment1, attachment2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<Attachment> attachments1, List<Attachment> attachments2) {

		Assert.assertEquals(attachments1.size(), attachments2.size());

		for (Attachment attachment1 : attachments1) {
			boolean contains = false;

			for (Attachment attachment2 : attachments2) {
				if (equals(attachment1, attachment2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				attachments2 + " does not contain " + attachment1, contains);
		}
	}

	protected void assertEqualsJSONArray(
		List<Attachment> attachments, JSONArray jsonArray) {

		for (Attachment attachment : attachments) {
			boolean contains = false;

			for (Object object : jsonArray) {
				if (equalsJSONObject(attachment, (JSONObject)object)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				jsonArray + " does not contain " + attachment, contains);
		}
	}

	protected void assertValid(Attachment attachment) {
		boolean valid = true;

		if (attachment.getId() == null) {
			valid = false;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("attachment", additionalAssertFieldName)) {
				if (attachment.getAttachment() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("displayDate", additionalAssertFieldName)) {
				if (attachment.getDisplayDate() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("expirationDate", additionalAssertFieldName)) {
				if (attachment.getExpirationDate() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("neverExpire", additionalAssertFieldName)) {
				if (attachment.getNeverExpire() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("options", additionalAssertFieldName)) {
				if (attachment.getOptions() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("priority", additionalAssertFieldName)) {
				if (attachment.getPriority() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("src", additionalAssertFieldName)) {
				if (attachment.getSrc() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("title", additionalAssertFieldName)) {
				if (attachment.getTitle() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("type", additionalAssertFieldName)) {
				if (attachment.getType() == null) {
					valid = false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		Assert.assertTrue(valid);
	}

	protected void assertValid(Page<Attachment> page) {
		boolean valid = false;

		java.util.Collection<Attachment> attachments = page.getItems();

		int size = attachments.size();

		if ((page.getLastPage() > 0) && (page.getPage() > 0) &&
			(page.getPageSize() > 0) && (page.getTotalCount() > 0) &&
			(size > 0)) {

			valid = true;
		}

		Assert.assertTrue(valid);
	}

	protected String[] getAdditionalAssertFieldNames() {
		return new String[0];
	}

	protected List<GraphQLField> getGraphQLFields() {
		List<GraphQLField> graphQLFields = new ArrayList<>();

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			graphQLFields.add(new GraphQLField(additionalAssertFieldName));
		}

		return graphQLFields;
	}

	protected String[] getIgnoredEntityFieldNames() {
		return new String[0];
	}

	protected boolean equals(Attachment attachment1, Attachment attachment2) {
		if (attachment1 == attachment2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("attachment", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						attachment1.getAttachment(),
						attachment2.getAttachment())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("displayDate", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						attachment1.getDisplayDate(),
						attachment2.getDisplayDate())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("expirationDate", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						attachment1.getExpirationDate(),
						attachment2.getExpirationDate())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("id", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						attachment1.getId(), attachment2.getId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("neverExpire", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						attachment1.getNeverExpire(),
						attachment2.getNeverExpire())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("options", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						attachment1.getOptions(), attachment2.getOptions())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("priority", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						attachment1.getPriority(), attachment2.getPriority())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("src", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						attachment1.getSrc(), attachment2.getSrc())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("title", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						attachment1.getTitle(), attachment2.getTitle())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("type", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						attachment1.getType(), attachment2.getType())) {

					return false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		return true;
	}

	protected boolean equalsJSONObject(
		Attachment attachment, JSONObject jsonObject) {

		for (String fieldName : getAdditionalAssertFieldNames()) {
			if (Objects.equals("attachment", fieldName)) {
				if (!Objects.deepEquals(
						attachment.getAttachment(),
						jsonObject.getString("attachment"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("id", fieldName)) {
				if (!Objects.deepEquals(
						attachment.getId(), jsonObject.getLong("id"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("neverExpire", fieldName)) {
				if (!Objects.deepEquals(
						attachment.getNeverExpire(),
						jsonObject.getBoolean("neverExpire"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("priority", fieldName)) {
				if (!Objects.deepEquals(
						attachment.getPriority(),
						jsonObject.getDouble("priority"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("src", fieldName)) {
				if (!Objects.deepEquals(
						attachment.getSrc(), jsonObject.getString("src"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("title", fieldName)) {
				if (!Objects.deepEquals(
						attachment.getTitle(), jsonObject.getString("title"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("type", fieldName)) {
				if (!Objects.deepEquals(
						attachment.getType(), jsonObject.getInt("type"))) {

					return false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid field name " + fieldName);
		}

		return true;
	}

	protected java.util.Collection<EntityField> getEntityFields()
		throws Exception {

		if (!(_attachmentResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_attachmentResource;

		EntityModel entityModel = entityModelResource.getEntityModel(
			new MultivaluedHashMap());

		Map<String, EntityField> entityFieldsMap =
			entityModel.getEntityFieldsMap();

		return entityFieldsMap.values();
	}

	protected List<EntityField> getEntityFields(EntityField.Type type)
		throws Exception {

		java.util.Collection<EntityField> entityFields = getEntityFields();

		Stream<EntityField> stream = entityFields.stream();

		return stream.filter(
			entityField ->
				Objects.equals(entityField.getType(), type) &&
				!ArrayUtil.contains(
					getIgnoredEntityFieldNames(), entityField.getName())
		).collect(
			Collectors.toList()
		);
	}

	protected String getFilterString(
		EntityField entityField, String operator, Attachment attachment) {

		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		if (entityFieldName.equals("attachment")) {
			sb.append("'");
			sb.append(String.valueOf(attachment.getAttachment()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("displayDate")) {
			if (operator.equals("between")) {
				sb = new StringBundler();

				sb.append("(");
				sb.append(entityFieldName);
				sb.append(" gt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(attachment.getDisplayDate(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(attachment.getDisplayDate(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_dateFormat.format(attachment.getDisplayDate()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("expirationDate")) {
			if (operator.equals("between")) {
				sb = new StringBundler();

				sb.append("(");
				sb.append(entityFieldName);
				sb.append(" gt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(
							attachment.getExpirationDate(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(
							attachment.getExpirationDate(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_dateFormat.format(attachment.getExpirationDate()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("id")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("neverExpire")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("options")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("priority")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("src")) {
			sb.append("'");
			sb.append(String.valueOf(attachment.getSrc()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("title")) {
			sb.append("'");
			sb.append(String.valueOf(attachment.getTitle()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("type")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		throw new IllegalArgumentException(
			"Invalid entity field " + entityFieldName);
	}

	protected String invoke(String query) throws Exception {
		HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

		httpInvoker.body(
			JSONUtil.put(
				"query", query
			).toString(),
			"application/json");
		httpInvoker.httpMethod(HttpInvoker.HttpMethod.POST);
		httpInvoker.path("http://localhost:8080/o/graphql");
		httpInvoker.userNameAndPassword("test@liferay.com:test");

		HttpInvoker.HttpResponse httpResponse = httpInvoker.invoke();

		return httpResponse.getContent();
	}

	protected Attachment randomAttachment() throws Exception {
		return new Attachment() {
			{
				attachment = RandomTestUtil.randomString();
				displayDate = RandomTestUtil.nextDate();
				expirationDate = RandomTestUtil.nextDate();
				id = RandomTestUtil.randomLong();
				neverExpire = RandomTestUtil.randomBoolean();
				priority = RandomTestUtil.randomDouble();
				src = RandomTestUtil.randomString();
				title = RandomTestUtil.randomString();
				type = RandomTestUtil.randomInt();
			}
		};
	}

	protected Attachment randomIrrelevantAttachment() throws Exception {
		Attachment randomIrrelevantAttachment = randomAttachment();

		return randomIrrelevantAttachment;
	}

	protected Attachment randomPatchAttachment() throws Exception {
		return randomAttachment();
	}

	protected AttachmentResource attachmentResource;
	protected Group irrelevantGroup;
	protected Company testCompany;
	protected Group testGroup;

	protected class GraphQLField {

		public GraphQLField(String key, GraphQLField... graphQLFields) {
			this(key, new HashMap<>(), graphQLFields);
		}

		public GraphQLField(
			String key, Map<String, Object> parameterMap,
			GraphQLField... graphQLFields) {

			_key = key;
			_parameterMap = parameterMap;
			_graphQLFields = graphQLFields;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder(_key);

			if (!_parameterMap.isEmpty()) {
				sb.append("(");

				for (Map.Entry<String, Object> entry :
						_parameterMap.entrySet()) {

					sb.append(entry.getKey());
					sb.append(":");
					sb.append(entry.getValue());
					sb.append(",");
				}

				sb.setLength(sb.length() - 1);

				sb.append(")");
			}

			if (_graphQLFields.length > 0) {
				sb.append("{");

				for (GraphQLField graphQLField : _graphQLFields) {
					sb.append(graphQLField.toString());
					sb.append(",");
				}

				sb.setLength(sb.length() - 1);

				sb.append("}");
			}

			return sb.toString();
		}

		private final GraphQLField[] _graphQLFields;
		private final String _key;
		private final Map<String, Object> _parameterMap;

	}

	private static final Log _log = LogFactoryUtil.getLog(
		BaseAttachmentResourceTestCase.class);

	private static BeanUtilsBean _beanUtilsBean = new BeanUtilsBean() {

		@Override
		public void copyProperty(Object bean, String name, Object value)
			throws IllegalAccessException, InvocationTargetException {

			if (value != null) {
				super.copyProperty(bean, name, value);
			}
		}

	};
	private static DateFormat _dateFormat;

	@Inject
	private com.liferay.headless.commerce.delivery.catalog.resource.v1_0.
		AttachmentResource _attachmentResource;

}