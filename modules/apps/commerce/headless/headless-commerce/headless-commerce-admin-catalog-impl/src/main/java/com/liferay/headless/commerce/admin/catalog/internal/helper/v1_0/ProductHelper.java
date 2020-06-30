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

package com.liferay.headless.commerce.admin.catalog.internal.helper.v1_0;

import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.CommerceCatalogLocalService;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Product;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.util.SearchUtil;

import java.util.List;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Ferrari
 */
@Component(immediate = true, service = ProductHelper.class)
public class ProductHelper {

	public Page<Product> getProductsPage(
			long companyId, String search, Filter filter, Pagination pagination,
			Sort[] sorts,
			UnsafeFunction<Document, Product, Exception>
				transformUnsafeFunction)
		throws Exception {

		return SearchUtil.search(
			null, booleanQuery -> booleanQuery.getPreBooleanFilter(), filter,
			CPDefinition.class, search, pagination,
			queryConfig -> queryConfig.setSelectedFieldNames(
				Field.ENTRY_CLASS_PK),
			searchContext -> {
				searchContext.setCompanyId(companyId);

				long[] getCommerceCatalogGroupIds = _getCommerceCatalogGroupIds(
					companyId);

				if ((getCommerceCatalogGroupIds != null) &&
					(getCommerceCatalogGroupIds.length > 0)) {

					searchContext.setGroupIds(getCommerceCatalogGroupIds);
				}
			},
			sorts, transformUnsafeFunction);
	}

	private long[] _getCommerceCatalogGroupIds(long companyId)
		throws PortalException {

		List<CommerceCatalog> commerceCatalogs =
			_commerceCatalogLocalService.searchCommerceCatalogs(companyId);

		Stream<CommerceCatalog> stream = commerceCatalogs.stream();

		return stream.mapToLong(
			CommerceCatalog::getGroupId
		).toArray();
	}

	@Reference
	private CommerceCatalogLocalService _commerceCatalogLocalService;

}