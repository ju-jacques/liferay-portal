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
String notificationNavigationItem = ParamUtil.getString(request, "notificationNavigationItem", "view-all-notification-queue-entries");

CommerceNotificationQueueEntriesDisplayContext commerceNotificationQueueEntriesDisplayContext = (CommerceNotificationQueueEntriesDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

long commerceChannelId = commerceNotificationQueueEntriesDisplayContext.getCommerceChannelId();
PortletURL portletURL = commerceNotificationQueueEntriesDisplayContext.getPortletURL();

portletURL.setParameter("notificationNavigationItem", notificationNavigationItem);

Map<String, String> contextParams = new HashMap<>();

contextParams.put("commerceChannelId", String.valueOf(commerceChannelId));
%>

<%@ include file="/navbar.jspf" %>

<c:choose>
	<c:when test='<%= notificationNavigationItem.equals("view-all-notification-queue-entries") %>'>
		<commerce-ui:dataset-display
			contextParams="<%= contextParams %>"
			dataProviderKey="<%= CommerceNotificationEntryClayTable.NAME %>"
			id="<%= CommerceNotificationEntryClayTable.NAME %>"
			itemsPerPage="<%= 10 %>"
			namespace="<%= renderResponse.getNamespace() %>"
			pageNumber="<%= 1 %>"
			portletURL="<%= portletURL %>"
			showManagementBar="<%= false %>"
		/>
	</c:when>
	<c:when test='<%= notificationNavigationItem.equals("view-all-notification-templates") %>'>
		<commerce-ui:dataset-display
			clayCreationMenu="<%= commerceNotificationQueueEntriesDisplayContext.getNotificationTemplateClayCreationMenu() %>"
			contextParams="<%= contextParams %>"
			dataProviderKey="<%= CommerceNotificationTemplateClayTable.NAME %>"
			id="<%= CommerceNotificationTemplateClayTable.NAME %>"
			itemsPerPage="<%= 10 %>"
			namespace="<%= renderResponse.getNamespace() %>"
			pageNumber="<%= 1 %>"
			portletURL="<%= portletURL %>"
			showSearch="<%= false %>"
		/>
	</c:when>
	<c:otherwise>
	</c:otherwise>
</c:choose>