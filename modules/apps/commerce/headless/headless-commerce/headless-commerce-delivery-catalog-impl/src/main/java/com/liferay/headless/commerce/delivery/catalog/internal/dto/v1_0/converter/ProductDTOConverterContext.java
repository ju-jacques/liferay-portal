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

package com.liferay.headless.commerce.delivery.catalog.internal.dto.v1_0.converter;

import com.liferay.commerce.product.catalog.CPCatalogEntry;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;

import java.util.Locale;

/**
 * @author Andrea Sbarra
 */
public class ProductDTOConverterContext extends DefaultDTOConverterContext {

	public ProductDTOConverterContext(
		Locale locale, long resourcePrimKey, CPCatalogEntry cpCatalogEntry) {

		super(locale, resourcePrimKey);

		_cpCatalogEntry = cpCatalogEntry;
	}

	public ProductDTOConverterContext(
		Locale locale, long resourcePrimKey, CPDefinition cpDefinition) {

		super(locale, resourcePrimKey);

		_cpDefinition = cpDefinition;
	}

	public CPCatalogEntry getCpCatalogEntry() {
		return _cpCatalogEntry;
	}

	public CPDefinition getCPDefinition() {
		return _cpDefinition;
	}

	private CPCatalogEntry _cpCatalogEntry;
	private CPDefinition _cpDefinition;

}