<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
String referringPortletResource = ParamUtil.getString(request, "referringPortletResource");

SearchContainer articleSearchContainer = journalDisplayContext.getSearchContainer(false);

String displayStyle = journalDisplayContext.getDisplayStyle();
%>

<liferay-ui:search-container
	emptyResultsMessage="no-web-content-was-found"
	id='<%= ParamUtil.getString(request, "searchContainerId") %>'
	searchContainer="<%= articleSearchContainer %>"
>
	<liferay-ui:search-container-row
		className="Object"
		cssClass="entry-display-style"
		modelVar="object"
	>

		<%
		JournalArticle curArticle = null;
		JournalFolder curFolder = null;

		Object result = row.getObject();

		if (result instanceof JournalFolder) {
			curFolder = (JournalFolder)result;
		}
		else {
			curArticle = journalDisplayContext.getLatestArticle((JournalArticle)result);
		}
		%>

		<c:choose>
			<c:when test="<%= curArticle != null %>">

				<%
				Map<String, Object> rowData = new HashMap<String, Object>();

				if (journalDisplayContext.isShowEditActions()) {
					rowData.put("draggable", !BrowserSnifferUtil.isMobile(request) && (JournalArticlePermission.contains(permissionChecker, curArticle, ActionKeys.DELETE) || JournalArticlePermission.contains(permissionChecker, curArticle, ActionKeys.UPDATE)));
				}

				String title = curArticle.getTitle(locale);

				if (Validator.isNull(title)) {
					title = curArticle.getTitle(LocaleUtil.fromLanguageId(curArticle.getDefaultLanguageId()));
				}

				rowData.put("title", HtmlUtil.escape(title));

				row.setData(rowData);

				row.setPrimaryKey(HtmlUtil.escape(curArticle.getArticleId()));

				String editURL = StringPool.BLANK;

				if (JournalArticlePermission.contains(permissionChecker, curArticle, ActionKeys.UPDATE)) {
					PortletURL editArticleURL = liferayPortletResponse.createRenderURL();

					editArticleURL.setParameter("mvcPath", "/edit_article.jsp");
					editArticleURL.setParameter("redirect", currentURL);
					editArticleURL.setParameter("referringPortletResource", referringPortletResource);
					editArticleURL.setParameter("groupId", String.valueOf(curArticle.getGroupId()));
					editArticleURL.setParameter("folderId", String.valueOf(curArticle.getFolderId()));
					editArticleURL.setParameter("articleId", curArticle.getArticleId());
					editArticleURL.setParameter("version", String.valueOf(curArticle.getVersion()));

					editURL = editArticleURL.toString();
				}
				%>

				<c:choose>
					<c:when test='<%= displayStyle.equals("descriptive") %>'>
						<liferay-ui:search-container-column-text>
							<liferay-ui:user-portrait
								userId="<%= curArticle.getUserId() %>"
							/>
						</liferay-ui:search-container-column-text>

						<liferay-ui:search-container-column-text
							colspan="<%= 2 %>"
						>

							<%
							Date createDate = curArticle.getModifiedDate();

							String modifiedDateDescription = LanguageUtil.getTimeDescription(request, System.currentTimeMillis() - createDate.getTime(), true);
							%>

							<h6 class="text-default">
								<liferay-ui:message arguments="<%= new String[] {HtmlUtil.escape(curArticle.getUserName()), modifiedDateDescription} %>" key="x-modified-x-ago" />
							</h6>

							<h5>
								<aui:a href="<%= editURL %>">
									<%= HtmlUtil.escape(title) %>
								</aui:a>
							</h5>

							<c:if test="<%= journalDisplayContext.isSearch() && ((curArticle.getFolderId() <= 0) || JournalFolderPermission.contains(permissionChecker, curArticle.getFolder(), ActionKeys.VIEW)) %>">
								<h5>
									<%= JournalHelperUtil.getAbsolutePath(liferayPortletRequest, curArticle.getFolderId()) %>
								</h5>
							</c:if>

							<h6 class="text-default">
								<aui:workflow-status markupView="lexicon" showIcon="<%= false %>" showLabel="<%= false %>" status="<%= curArticle.getStatus() %>" />
							</h6>
						</liferay-ui:search-container-column-text>

						<c:if test="<%= journalDisplayContext.isShowEditActions() %>">
							<liferay-ui:search-container-column-jsp
								path="/article_action.jsp"
							/>
						</c:if>
					</c:when>
					<c:when test='<%= displayStyle.equals("icon") %>'>

						<%
						row.setCssClass("entry-card lfr-asset-item " + row.getCssClass());
						%>

						<liferay-ui:search-container-column-text>

							<%
							String articleImageURL = curArticle.getArticleImageURL(themeDisplay);
							%>

							<c:choose>
								<c:when test="<%= Validator.isNotNull(articleImageURL) %>">
									<liferay-frontend:vertical-card
										actionJsp='<%= journalDisplayContext.isShowEditActions() ? "/article_action.jsp" : null %>'
										actionJspServletContext="<%= application %>"
										imageUrl="<%= HtmlUtil.escape(articleImageURL) %>"
										resultRow="<%= row %>"
										rowChecker="<%= articleSearchContainer.getRowChecker() %>"
										title="<%= title %>"
										url="<%= editURL %>"
									>
										<%@ include file="/article_vertical_card.jspf" %>
									</liferay-frontend:vertical-card>
								</c:when>
								<c:otherwise>
									<liferay-frontend:icon-vertical-card
										actionJsp='<%= journalDisplayContext.isShowEditActions() ? "/article_action.jsp" : null %>'
										actionJspServletContext="<%= application %>"
										icon="web-content"
										resultRow="<%= row %>"
										rowChecker="<%= articleSearchContainer.getRowChecker() %>"
										title="<%= title %>"
										url="<%= editURL %>"
									>
										<%@ include file="/article_vertical_card.jspf" %>
									</liferay-frontend:icon-vertical-card>
								</c:otherwise>
							</c:choose>
						</liferay-ui:search-container-column-text>
					</c:when>
					<c:otherwise>
						<c:if test="<%= !journalWebConfiguration.journalArticleForceAutogenerateId() %>">
							<liferay-ui:search-container-column-text
								name="id"
								value="<%= HtmlUtil.escape(curArticle.getArticleId()) %>"
							/>
						</c:if>

						<liferay-ui:search-container-column-jsp
							cssClass="table-cell-expand table-cell-minw-200 table-title"
							href="<%= editURL %>"
							name="title"
							path="/article_title.jsp"
						/>

						<liferay-ui:search-container-column-text
							cssClass="table-cell-expand table-cell-minw-200"
							name="description"
							value="<%= StringUtil.shorten(HtmlUtil.stripHtml(curArticle.getDescription(locale)), 200) %>"
						/>

						<c:if test="<%= journalDisplayContext.isSearch() && ((curArticle.getFolderId() <= 0) || JournalFolderPermission.contains(permissionChecker, curArticle.getFolder(), ActionKeys.VIEW)) %>">
							<liferay-ui:search-container-column-text
								cssClass="table-cell-expand-smallest table-cell-minw-200"
								name="path"
								value="<%= JournalHelperUtil.getAbsolutePath(liferayPortletRequest, curArticle.getFolderId()) %>"
							/>
						</c:if>

						<liferay-ui:search-container-column-text
							cssClass="table-cell-expand-smallest table-cell-minw-100"
							name="author"
							value="<%= HtmlUtil.escape(PortalUtil.getUserName(curArticle)) %>"
						/>

						<liferay-ui:search-container-column-status
							name="status"
						/>

						<liferay-ui:search-container-column-date
							cssClass="table-cell-expand-smallest table-cell-ws-nowrap"
							name="modified-date"
							value="<%= curArticle.getModifiedDate() %>"
						/>

						<liferay-ui:search-container-column-date
							cssClass="table-cell-expand-smallest table-cell-ws-nowrap"
							name="display-date"
							value="<%= curArticle.getDisplayDate() %>"
						/>

						<%
						DDMStructure ddmStructure = curArticle.getDDMStructure();
						%>

						<liferay-ui:search-container-column-text
							cssClass="table-cell-expand-smallest table-cell-minw-100"
							name="type"
							value="<%= HtmlUtil.escape(ddmStructure.getName(locale)) %>"
						/>

						<c:if test="<%= journalDisplayContext.isShowEditActions() %>">
							<liferay-ui:search-container-column-jsp
								path="/article_action.jsp"
							/>
						</c:if>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:when test="<%= curFolder != null %>">

				<%
				Map<String, Object> rowData = new HashMap<String, Object>();

				rowData.put("draggable", !BrowserSnifferUtil.isMobile(request) && (JournalFolderPermission.contains(permissionChecker, curFolder, ActionKeys.DELETE) || JournalFolderPermission.contains(permissionChecker, curFolder, ActionKeys.UPDATE)));
				rowData.put("folder", true);
				rowData.put("folder-id", curFolder.getFolderId());
				rowData.put("title", HtmlUtil.escape(curFolder.getName()));

				row.setData(rowData);
				row.setPrimaryKey(String.valueOf(curFolder.getPrimaryKey()));

				PortletURL rowURL = liferayPortletResponse.createRenderURL();

				rowURL.setParameter("redirect", currentURL);
				rowURL.setParameter("groupId", String.valueOf(curFolder.getGroupId()));
				rowURL.setParameter("folderId", String.valueOf(curFolder.getFolderId()));
				rowURL.setParameter("displayStyle", displayStyle);
				rowURL.setParameter("showEditActions", String.valueOf(journalDisplayContext.isShowEditActions()));
				%>

				<c:choose>
					<c:when test='<%= displayStyle.equals("descriptive") %>'>
						<liferay-ui:search-container-column-icon
							icon="folder"
							toggleRowChecker="<%= true %>"
						/>

						<liferay-ui:search-container-column-text
							colspan="<%= 2 %>"
						>

							<%
							Date createDate = curFolder.getCreateDate();

							String createDateDescription = LanguageUtil.getTimeDescription(request, System.currentTimeMillis() - createDate.getTime(), true);
							%>

							<h6 class="text-default">
								<liferay-ui:message arguments="<%= new String[] {HtmlUtil.escape(curFolder.getUserName()), createDateDescription} %>" key="x-modified-x-ago" />
							</h6>

							<h5>
								<aui:a href="<%= (rowURL != null) ? rowURL.toString() : null %>">
									<%= HtmlUtil.escape(curFolder.getName()) %>
								</aui:a>
							</h5>

							<c:if test="<%= journalDisplayContext.isSearch() && ((curFolder.getParentFolderId() <= 0) || JournalFolderPermission.contains(permissionChecker, curFolder.getParentFolder(), ActionKeys.VIEW)) %>">
								<h5>
									<%= JournalHelperUtil.getAbsolutePath(liferayPortletRequest, curFolder.getParentFolderId()) %>
								</h5>
							</c:if>

							<h6 class="text-default">
								<aui:workflow-status markupView="lexicon" showIcon="<%= false %>" showLabel="<%= false %>" status="<%= curFolder.getStatus() %>" />
							</h6>
						</liferay-ui:search-container-column-text>

						<c:if test="<%= journalDisplayContext.isShowEditActions() %>">
							<liferay-ui:search-container-column-jsp
								path="/folder_action.jsp"
							/>
						</c:if>
					</c:when>
					<c:when test='<%= displayStyle.equals("icon") %>'>

						<%
						row.setCssClass("entry-card lfr-asset-folder " + row.getCssClass());
						%>

						<liferay-ui:search-container-column-text
							colspan="<%= 2 %>"
						>
							<liferay-frontend:horizontal-card
								actionJsp='<%= journalDisplayContext.isShowEditActions() ? "/folder_action.jsp" : null %>'
								actionJspServletContext="<%= application %>"
								resultRow="<%= row %>"
								rowChecker="<%= articleSearchContainer.getRowChecker() %>"
								text="<%= HtmlUtil.escape(curFolder.getName()) %>"
								url="<%= rowURL.toString() %>"
							>
								<liferay-frontend:horizontal-card-col>
									<liferay-frontend:horizontal-card-icon
										icon="folder"
									/>
								</liferay-frontend:horizontal-card-col>
							</liferay-frontend:horizontal-card>
						</liferay-ui:search-container-column-text>
					</c:when>
					<c:otherwise>
						<c:if test="<%= !journalWebConfiguration.journalArticleForceAutogenerateId() %>">
							<liferay-ui:search-container-column-text
								name="id"
								value="<%= HtmlUtil.escape(String.valueOf(curFolder.getFolderId())) %>"
							/>
						</c:if>

						<liferay-ui:search-container-column-text
							cssClass="table-cell-expand table-cell-minw-200 table-list-title"
							href="<%= rowURL.toString() %>"
							name="title"
							value="<%= HtmlUtil.escape(curFolder.getName()) %>"
						/>

						<liferay-ui:search-container-column-text
							cssClass="table-cell-expand table-cell-minw-200"
							name="description"
							value="<%= HtmlUtil.escape(curFolder.getDescription()) %>"
						/>

						<c:if test="<%= journalDisplayContext.isSearch() && ((curFolder.getParentFolderId() <= 0) || JournalFolderPermission.contains(permissionChecker, curFolder.getParentFolder(), ActionKeys.VIEW)) %>">
							<liferay-ui:search-container-column-text
								cssClass="table-cell-expand-smallest table-cell-minw-200"
								name="path"
								value="<%= JournalHelperUtil.getAbsolutePath(liferayPortletRequest, curFolder.getParentFolderId()) %>"
							/>
						</c:if>

						<liferay-ui:search-container-column-text
							cssClass="table-cell-expand-smallest table-cell-minw-150"
							name="author"
							value="<%= HtmlUtil.escape(PortalUtil.getUserName(curFolder)) %>"
						/>

						<liferay-ui:search-container-column-text
							name="status"
							value="--"
						/>

						<liferay-ui:search-container-column-date
							cssClass="table-cell-expand-smallest table-cell-ws-nowrap"
							name="modified-date"
							value="<%= curFolder.getModifiedDate() %>"
						/>

						<liferay-ui:search-container-column-text
							cssClass="table-cell-expand-smallest table-cell-ws-nowrap"
							name="display-date"
							value="--"
						/>

						<liferay-ui:search-container-column-text
							cssClass="table-cell-expand-smallest table-cell-minw-150"
							name="type"
							value='<%= LanguageUtil.get(request, "folder") %>'
						/>

						<c:if test="<%= journalDisplayContext.isShowEditActions() %>">
							<liferay-ui:search-container-column-jsp
								path="/folder_action.jsp"
							/>
						</c:if>
					</c:otherwise>
				</c:choose>
			</c:when>
		</c:choose>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		displayStyle="<%= displayStyle %>"
		markupView="lexicon"
		resultRowSplitter="<%= journalDisplayContext.isSearch() ? null : new JournalResultRowSplitter() %>"
		searchContainer="<%= articleSearchContainer %>"
	/>
</liferay-ui:search-container>

<aui:script use="liferay-journal-navigation">
	var journalNavigation = new Liferay.Portlet.JournalNavigation(
		{
			editEntryUrl: '<portlet:actionURL />',
			form: {
					method: 'POST',
					node: A.one(document.<portlet:namespace />fm)
			},
			moveEntryUrl: '<portlet:renderURL><portlet:param name="mvcPath" value="/move_entries.jsp" /><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:renderURL>',
			namespace: '<portlet:namespace />',
			searchContainerId: 'articles'
		}
	);

	var clearJournalNavigationHandles = function(event) {
		if (event.portletId === '<%= portletDisplay.getRootPortletId() %>') {
			journalNavigation.destroy();

			Liferay.detach('destroyPortlet', clearJournalNavigationHandles);
		}
	};

	Liferay.on('destroyPortlet', clearJournalNavigationHandles);
</aui:script>