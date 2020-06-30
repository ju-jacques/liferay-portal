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
CommerceShippingFixedOptionRelsDisplayContext commerceShippingFixedOptionRelsDisplayContext = (CommerceShippingFixedOptionRelsDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<c:choose>
	<c:when test="<%= commerceShippingFixedOptionRelsDisplayContext.isVisible() %>">

		<%
		Map<String, String> contextParams = new HashMap<>();

		contextParams.put("commerceShippingMethodId", String.valueOf(commerceShippingFixedOptionRelsDisplayContext.getCommerceShippingMethodId()));
		%>

		<commerce-ui:dataset-display
			clayCreationMenu="<%= commerceShippingFixedOptionRelsDisplayContext.getClayCreationMenu() %>"
			contextParams="<%= contextParams %>"
			dataProviderKey="<%= CommerceShippingFixedOptionSettingClayTable.NAME %>"
			id="<%= CommerceShippingFixedOptionSettingClayTable.NAME %>"
			itemsPerPage="<%= 10 %>"
			namespace="<%= renderResponse.getNamespace() %>"
			pageNumber="<%= 1 %>"
			portletURL="<%= commerceShippingFixedOptionRelsDisplayContext.getPortletURL() %>"
			showManagementBar="<%= true %>"
		/>
	</c:when>
	<c:otherwise>
		<div class="alert alert-info">
			<liferay-ui:message key="there-are-no-shipping-options" />
			<liferay-ui:message key="please-add-at-least-one-shipping-option" />
		</div>
	</c:otherwise>
</c:choose>