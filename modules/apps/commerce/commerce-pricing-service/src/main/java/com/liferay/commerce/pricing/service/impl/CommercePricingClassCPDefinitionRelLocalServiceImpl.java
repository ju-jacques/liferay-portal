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

package com.liferay.commerce.pricing.service.impl;

import com.liferay.commerce.pricing.model.CommercePricingClassCPDefinitionRel;
import com.liferay.commerce.pricing.service.base.CommercePricingClassCPDefinitionRelLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * @author Riccardo Alberti
 * @see CommercePricingClassCPDefinitionRelLocalServiceBaseImpl
 */
public class CommercePricingClassCPDefinitionRelLocalServiceImpl
	extends CommercePricingClassCPDefinitionRelLocalServiceBaseImpl {

	@Override
	public CommercePricingClassCPDefinitionRel
			addCommercePricingClassCPDefinitionRel(
				long commercePricingClassId, long cpDefinitionId,
				ServiceContext serviceContext)
		throws PortalException {

		// Commerce pricing class cp definition rel

		User user = userLocalService.getUser(serviceContext.getUserId());

		long commercePricingClassCPDefinitionRelId =
			counterLocalService.increment();

		CommercePricingClassCPDefinitionRel
			commercePricingClassCPDefinitionRel =
				commercePricingClassCPDefinitionRelPersistence.create(
					commercePricingClassCPDefinitionRelId);

		commercePricingClassCPDefinitionRel.setCompanyId(user.getCompanyId());
		commercePricingClassCPDefinitionRel.setUserId(user.getUserId());
		commercePricingClassCPDefinitionRel.setUserName(user.getFullName());
		commercePricingClassCPDefinitionRel.setCommercePricingClassId(
			commercePricingClassId);
		commercePricingClassCPDefinitionRel.setCPDefinitionId(cpDefinitionId);

		return commercePricingClassCPDefinitionRelPersistence.update(
			commercePricingClassCPDefinitionRel);
	}

	@Override
	public CommercePricingClassCPDefinitionRel
			deleteCommercePricingClassCPDefinitionRel(
				CommercePricingClassCPDefinitionRel
					commercePricingClassCPDefinitionRel)
		throws PortalException {

		return commercePricingClassCPDefinitionRelPersistence.remove(
			commercePricingClassCPDefinitionRel);
	}

	@Override
	public CommercePricingClassCPDefinitionRel
			deleteCommercePricingClassCPDefinitionRel(
				long commercePricingClassCPDefinitionRelId)
		throws PortalException {

		CommercePricingClassCPDefinitionRel
			commercePricingClassCPDefinitionRel =
				commercePricingClassCPDefinitionRelPersistence.findByPrimaryKey(
					commercePricingClassCPDefinitionRelId);

		return commercePricingClassCPDefinitionRelLocalService.
			deleteCommercePricingClassCPDefinitionRel(
				commercePricingClassCPDefinitionRel);
	}

	@Override
	public void deleteCommercePricingClassCPDefinitionRels(
			long commercePricingClassId)
		throws PortalException {

		List<CommercePricingClassCPDefinitionRel>
			commercePricingClassCPDefinitionRels =
				commercePricingClassCPDefinitionRelPersistence.
					findByCommercePricingClassId(commercePricingClassId);

		for (CommercePricingClassCPDefinitionRel
				commercePricingClassCPDefinitionRel :
					commercePricingClassCPDefinitionRels) {

			commercePricingClassCPDefinitionRelLocalService.
				deleteCommercePricingClassCPDefinitionRel(
					commercePricingClassCPDefinitionRel);
		}
	}

	@Override
	public List<CommercePricingClassCPDefinitionRel>
		getCommercePricingClassByCPDefinitionId(long cpDefinitionId) {

		return commercePricingClassCPDefinitionRelPersistence.
			findByCPDefinitionId(cpDefinitionId);
	}

	@Override
	public List<CommercePricingClassCPDefinitionRel>
		getCommercePricingClassCPDefinitionRels(long commercePricingClassId) {

		return commercePricingClassCPDefinitionRelPersistence.
			findByCommercePricingClassId(commercePricingClassId);
	}

	@Override
	public List<CommercePricingClassCPDefinitionRel>
		getCommercePricingClassCPDefinitionRels(
			long commercePricingClassId, int start, int end,
			OrderByComparator<CommercePricingClassCPDefinitionRel>
				orderByComparator) {

		return commercePricingClassCPDefinitionRelPersistence.
			findByCommercePricingClassId(
				commercePricingClassId, start, end, orderByComparator);
	}

	@Override
	public int getCommercePricingClassCPDefinitionRelsCount(
		long commercePricingClassId) {

		return commercePricingClassCPDefinitionRelPersistence.
			countByCommercePricingClassId(commercePricingClassId);
	}

	@Override
	public long[] getCPDefinitionIds(long commercePricingClassId) {
		return ListUtil.toLongArray(
			commercePricingClassCPDefinitionRelPersistence.
				findByCommercePricingClassId(commercePricingClassId),
			CommercePricingClassCPDefinitionRel::getCPDefinitionId);
	}

}