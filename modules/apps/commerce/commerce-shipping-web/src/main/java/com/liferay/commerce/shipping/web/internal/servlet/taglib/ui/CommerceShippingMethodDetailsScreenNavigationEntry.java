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

package com.liferay.commerce.shipping.web.internal.servlet.taglib.ui;

import com.liferay.commerce.model.CommerceShippingMethod;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationCategory;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationEntry;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.language.LanguageUtil;

import java.io.IOException;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = {
		"screen.navigation.category.order:Integer=10",
		"screen.navigation.entry.order:Integer=10"
	},
	service = {ScreenNavigationCategory.class, ScreenNavigationEntry.class}
)
public class CommerceShippingMethodDetailsScreenNavigationEntry
	implements ScreenNavigationCategory,
			   ScreenNavigationEntry<CommerceShippingMethod> {

	@Override
	public String getCategoryKey() {
		return CommerceShippingScreenNavigationConstants.
			CATEGORY_KEY_COMMERCE_SHIPPING_METHOD_DETAILS;
	}

	@Override
	public String getEntryKey() {
		return CommerceShippingScreenNavigationConstants.
			ENTRY_KEY_COMMERCE_SHIPPING_METHOD_DETAILS;
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(
			locale,
			CommerceShippingScreenNavigationConstants.
				ENTRY_KEY_COMMERCE_SHIPPING_METHOD_DETAILS);
	}

	@Override
	public String getScreenNavigationKey() {
		return CommerceShippingScreenNavigationConstants.
			SCREEN_NAVIGATION_KEY_COMMERCE_SHIPPING_METHOD;
	}

	@Override
	public void render(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		_jspRenderer.renderJSP(
			httpServletRequest, httpServletResponse,
			"/shipping_method/details.jsp");
	}

	@Reference
	private JSPRenderer _jspRenderer;

}