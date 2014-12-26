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

package com.liferay.portlet.blogs;

import com.liferay.portal.kernel.editor.EditorConstants;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.test.AggregateTestRule;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.TempFileEntryUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.test.DeleteAfterTestRun;
import com.liferay.portal.test.LiferayIntegrationTestRule;
import com.liferay.portal.test.MainServletTestRule;
import com.liferay.portal.util.test.GroupTestUtil;
import com.liferay.portal.util.test.RandomTestUtil;
import com.liferay.portal.util.test.UserTestUtil;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.blogs.util.test.BlogsTestUtil;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Roberto Díaz
 * @author Sergio González
 */
public class BlogsEntryAttachmentFileEntryHelperTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), MainServletTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
		_user = UserTestUtil.addGroupAdminUser(_group);
	}

	@Test
	public void testAddBlogsEntryAttachmentFileEntries() throws Exception {
		FileEntry tempFileEntry = TempFileEntryUtil.addTempFileEntry(
			_group.getGroupId(), _user.getUserId(), _TEMP_FOLDER_NAME,
			"image.jpg", _getInputStream(), ContentTypes.IMAGE_JPEG);

		List<BlogsEntryAttachmentFileEntryReference>
			blogsEntryAttachmentFileEntryReferences =
				getBlogsEntryAttachmentFileEntryReferences(tempFileEntry);

		Assert.assertEquals(1, blogsEntryAttachmentFileEntryReferences.size());

		BlogsEntryAttachmentFileEntryReference
			blogsEntryAttachmentFileEntryReference =
				blogsEntryAttachmentFileEntryReferences.get(0);

		Assert.assertEquals(
			tempFileEntry.getFileEntryId(),
			blogsEntryAttachmentFileEntryReference.
				getTempBlogsEntryAttachmentFileEntryId());

		FileEntry fileEntry =
			blogsEntryAttachmentFileEntryReference.
				getBlogsEntryAttachmentFileEntry();

		Assert.assertEquals(tempFileEntry.getTitle(), fileEntry.getTitle());
		Assert.assertEquals(
			tempFileEntry.getMimeType(), fileEntry.getMimeType());
		Assert.assertEquals(
			DigesterUtil.digestBase64(tempFileEntry.getContentStream()),
			DigesterUtil.digestBase64(fileEntry.getContentStream()));
	}

	@Test
	public void testGetBlogsEntryAttachmentFileEntryImgTag() throws Exception {
		FileEntry tempFileEntry = TempFileEntryUtil.addTempFileEntry(
			_group.getGroupId(), _user.getUserId(), _TEMP_FOLDER_NAME,
			"image.jpg", _getInputStream(), ContentTypes.IMAGE_JPEG);

		String fileEntryURL = PortletFileRepositoryUtil.getPortletFileEntryURL(
			null, tempFileEntry, StringPool.BLANK);

		Assert.assertEquals(
			"<img src=\"" + fileEntryURL + "\" />",
			_blogsEntryAttachmentFileEntryHelper.
				getBlogsEntryAttachmentFileEntryImgTag(tempFileEntry));
	}

	@Test
	public void testGetTempBlogsEntryAttachmentFileEntries() throws Exception {
		FileEntry tempFileEntry = TempFileEntryUtil.addTempFileEntry(
			_group.getGroupId(), _user.getUserId(), _TEMP_FOLDER_NAME,
			"image.jpg", _getInputStream(), ContentTypes.IMAGE_JPEG);

		String tempFileEntryImgTag = _getTempFileEntryImgTag(tempFileEntry);

		List<FileEntry> tempBlogsEntryAttachments =
			_blogsEntryAttachmentFileEntryHelper.
				getTempBlogsEntryAttachmentFileEntries(
					getContent(tempFileEntryImgTag));

		Assert.assertEquals(1, tempBlogsEntryAttachments.size());

		for (FileEntry tempBlogsEntryAttachment : tempBlogsEntryAttachments) {
			Assert.assertEquals(
				tempFileEntry.getFileEntryId(),
				tempBlogsEntryAttachment.getFileEntryId());
		}
	}

	@Test
	public void testGetTempBlogsEntryAttachmentFileEntriesWithModifiedImgTag()
		throws Exception {

		FileEntry tempFileEntry = TempFileEntryUtil.addTempFileEntry(
			_group.getGroupId(), _user.getUserId(), _TEMP_FOLDER_NAME,
			"image.jpg", _getInputStream(), ContentTypes.IMAGE_JPEG);

		String tempFileEntryImgTag = _getModifiedTempFileEntryImgTag(
			tempFileEntry);

		List<FileEntry> tempBlogsEntryAttachments =
			_blogsEntryAttachmentFileEntryHelper.
				getTempBlogsEntryAttachmentFileEntries(
					getContent(tempFileEntryImgTag));

		Assert.assertEquals(1, tempBlogsEntryAttachments.size());

		for (FileEntry tempBlogsEntryAttachment : tempBlogsEntryAttachments) {
			Assert.assertEquals(
				tempFileEntry.getFileEntryId(),
				tempBlogsEntryAttachment.getFileEntryId());
		}
	}

	@Test
	public void testUpdateContent() throws Exception {
		FileEntry tempFileEntry = TempFileEntryUtil.addTempFileEntry(
			_group.getGroupId(), _user.getUserId(), _TEMP_FOLDER_NAME,
			"image.jpg", _getInputStream(), ContentTypes.IMAGE_JPEG);

		String tempFileEntryImgTag = _getTempFileEntryImgTag(tempFileEntry);

		List<BlogsEntryAttachmentFileEntryReference>
			blogsEntryAttachmentFileEntryReferences =
				getBlogsEntryAttachmentFileEntryReferences(tempFileEntry);

		String content = _blogsEntryAttachmentFileEntryHelper.updateContent(
			getContent(tempFileEntryImgTag),
			blogsEntryAttachmentFileEntryReferences);

		Assert.assertFalse(content.contains(tempFileEntryImgTag));

		BlogsEntryAttachmentFileEntryReference
			blogsEntryAttachmentFileEntryReference =
				blogsEntryAttachmentFileEntryReferences.get(0);

		FileEntry fileEntry =
			blogsEntryAttachmentFileEntryReference.
				getBlogsEntryAttachmentFileEntry();

		Assert.assertTrue(
			content.contains(
				PortletFileRepositoryUtil.getPortletFileEntryURL(
					null, fileEntry, StringPool.BLANK)));
	}

	protected List<BlogsEntryAttachmentFileEntryReference>
		getBlogsEntryAttachmentFileEntryReferences(
			FileEntry tempFileEntry)
		throws Exception {

		BlogsEntry entry = BlogsTestUtil.addEntry(_group, true);

		List<FileEntry> tempFileEntries = new ArrayList<>();

		tempFileEntries.add(tempFileEntry);

		return
			_blogsEntryAttachmentFileEntryHelper.
				addBlogsEntryAttachmentFileEntries(
					_group.getGroupId(), _user.getUserId(), entry.getEntryId(),
					tempFileEntries);
	}

	protected String getContent(String tempFileEntryImgTag) {
		StringBundler sb = new StringBundler(10);

		sb.append("<p>");
		sb.append(RandomTestUtil.randomStrings(50));
		sb.append("</p>");
		sb.append("<a href=\"www.liferay.com\"><span>");
		sb.append(RandomTestUtil.randomStrings(50));
		sb.append("<img src=\"www.liferay.com/logo.png\" /><span>");
		sb.append(RandomTestUtil.randomStrings(50));
		sb.append("</span>");
		sb.append(tempFileEntryImgTag);
		sb.append("<span></a>");

		return sb.toString();
	}

	private InputStream _getInputStream() {
		Class<?> clazz = BlogsEntryAttachmentFileEntryHelperTest.class;

		ClassLoader classLoader = clazz.getClassLoader();

		return classLoader.getResourceAsStream(
			"com/liferay/portal/util/dependencies/test.jpg");
	}

	private String _getModifiedTempFileEntryImgTag(FileEntry tempFileEntry) {
		StringBundler sb = new StringBundler(7);

		sb.append("<img ");
		sb.append(EditorConstants.ATTRIBUTE_DATA_IMAGE_ID);
		sb.append("=\"");
		sb.append(tempFileEntry.getFileEntryId());
		sb.append("\" class=\"test-class\" id=\"test-id\" src=\"");
		sb.append(
			PortletFileRepositoryUtil.getPortletFileEntryURL(
				null, tempFileEntry, StringPool.BLANK));
		sb.append("\" title=\"test-title\" />");

		return sb.toString();
	}

	private String _getTempFileEntryImgTag(FileEntry tempFileEntry) {
		StringBundler sb = new StringBundler(7);

		sb.append("<img ");
		sb.append(EditorConstants.ATTRIBUTE_DATA_IMAGE_ID);
		sb.append("=\"");
		sb.append(tempFileEntry.getFileEntryId());
		sb.append("\" src=\"");
		sb.append(
			PortletFileRepositoryUtil.getPortletFileEntryURL(
				null, tempFileEntry, StringPool.BLANK));
		sb.append("\"/>");

		return sb.toString();
	}

	private static final String _TEMP_FOLDER_NAME = BlogsEntry.class.getName();

	private final BlogsEntryAttachmentFileEntryHelper
		_blogsEntryAttachmentFileEntryHelper =
			new BlogsEntryAttachmentFileEntryHelper();

	@DeleteAfterTestRun
	private Group _group;

	@DeleteAfterTestRun
	private User _user;

}