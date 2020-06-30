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

package com.liferay.commerce.model.impl;

import com.liferay.commerce.model.CommerceSubscriptionEntry;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CommerceSubscriptionEntry in entity cache.
 *
 * @author Alessio Antonio Rendina
 * @generated
 */
public class CommerceSubscriptionEntryCacheModel
	implements CacheModel<CommerceSubscriptionEntry>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceSubscriptionEntryCacheModel)) {
			return false;
		}

		CommerceSubscriptionEntryCacheModel
			commerceSubscriptionEntryCacheModel =
				(CommerceSubscriptionEntryCacheModel)obj;

		if (commerceSubscriptionEntryId ==
				commerceSubscriptionEntryCacheModel.
					commerceSubscriptionEntryId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, commerceSubscriptionEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(59);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", commerceSubscriptionEntryId=");
		sb.append(commerceSubscriptionEntryId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", CPInstanceUuid=");
		sb.append(CPInstanceUuid);
		sb.append(", CProductId=");
		sb.append(CProductId);
		sb.append(", commerceOrderItemId=");
		sb.append(commerceOrderItemId);
		sb.append(", subscriptionLength=");
		sb.append(subscriptionLength);
		sb.append(", subscriptionType=");
		sb.append(subscriptionType);
		sb.append(", subscriptionTypeSettings=");
		sb.append(subscriptionTypeSettings);
		sb.append(", currentCycle=");
		sb.append(currentCycle);
		sb.append(", maxSubscriptionCycles=");
		sb.append(maxSubscriptionCycles);
		sb.append(", subscriptionStatus=");
		sb.append(subscriptionStatus);
		sb.append(", lastIterationDate=");
		sb.append(lastIterationDate);
		sb.append(", nextIterationDate=");
		sb.append(nextIterationDate);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", deliverySubscriptionLength=");
		sb.append(deliverySubscriptionLength);
		sb.append(", deliverySubscriptionType=");
		sb.append(deliverySubscriptionType);
		sb.append(", deliverySubscriptionTypeSettings=");
		sb.append(deliverySubscriptionTypeSettings);
		sb.append(", deliveryCurrentCycle=");
		sb.append(deliveryCurrentCycle);
		sb.append(", deliveryMaxSubscriptionCycles=");
		sb.append(deliveryMaxSubscriptionCycles);
		sb.append(", deliverySubscriptionStatus=");
		sb.append(deliverySubscriptionStatus);
		sb.append(", deliveryLastIterationDate=");
		sb.append(deliveryLastIterationDate);
		sb.append(", deliveryNextIterationDate=");
		sb.append(deliveryNextIterationDate);
		sb.append(", deliveryStartDate=");
		sb.append(deliveryStartDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommerceSubscriptionEntry toEntityModel() {
		CommerceSubscriptionEntryImpl commerceSubscriptionEntryImpl =
			new CommerceSubscriptionEntryImpl();

		if (uuid == null) {
			commerceSubscriptionEntryImpl.setUuid("");
		}
		else {
			commerceSubscriptionEntryImpl.setUuid(uuid);
		}

		commerceSubscriptionEntryImpl.setCommerceSubscriptionEntryId(
			commerceSubscriptionEntryId);
		commerceSubscriptionEntryImpl.setGroupId(groupId);
		commerceSubscriptionEntryImpl.setCompanyId(companyId);
		commerceSubscriptionEntryImpl.setUserId(userId);

		if (userName == null) {
			commerceSubscriptionEntryImpl.setUserName("");
		}
		else {
			commerceSubscriptionEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			commerceSubscriptionEntryImpl.setCreateDate(null);
		}
		else {
			commerceSubscriptionEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commerceSubscriptionEntryImpl.setModifiedDate(null);
		}
		else {
			commerceSubscriptionEntryImpl.setModifiedDate(
				new Date(modifiedDate));
		}

		if (CPInstanceUuid == null) {
			commerceSubscriptionEntryImpl.setCPInstanceUuid("");
		}
		else {
			commerceSubscriptionEntryImpl.setCPInstanceUuid(CPInstanceUuid);
		}

		commerceSubscriptionEntryImpl.setCProductId(CProductId);
		commerceSubscriptionEntryImpl.setCommerceOrderItemId(
			commerceOrderItemId);
		commerceSubscriptionEntryImpl.setSubscriptionLength(subscriptionLength);

		if (subscriptionType == null) {
			commerceSubscriptionEntryImpl.setSubscriptionType("");
		}
		else {
			commerceSubscriptionEntryImpl.setSubscriptionType(subscriptionType);
		}

		if (subscriptionTypeSettings == null) {
			commerceSubscriptionEntryImpl.setSubscriptionTypeSettings("");
		}
		else {
			commerceSubscriptionEntryImpl.setSubscriptionTypeSettings(
				subscriptionTypeSettings);
		}

		commerceSubscriptionEntryImpl.setCurrentCycle(currentCycle);
		commerceSubscriptionEntryImpl.setMaxSubscriptionCycles(
			maxSubscriptionCycles);
		commerceSubscriptionEntryImpl.setSubscriptionStatus(subscriptionStatus);

		if (lastIterationDate == Long.MIN_VALUE) {
			commerceSubscriptionEntryImpl.setLastIterationDate(null);
		}
		else {
			commerceSubscriptionEntryImpl.setLastIterationDate(
				new Date(lastIterationDate));
		}

		if (nextIterationDate == Long.MIN_VALUE) {
			commerceSubscriptionEntryImpl.setNextIterationDate(null);
		}
		else {
			commerceSubscriptionEntryImpl.setNextIterationDate(
				new Date(nextIterationDate));
		}

		if (startDate == Long.MIN_VALUE) {
			commerceSubscriptionEntryImpl.setStartDate(null);
		}
		else {
			commerceSubscriptionEntryImpl.setStartDate(new Date(startDate));
		}

		commerceSubscriptionEntryImpl.setDeliverySubscriptionLength(
			deliverySubscriptionLength);

		if (deliverySubscriptionType == null) {
			commerceSubscriptionEntryImpl.setDeliverySubscriptionType("");
		}
		else {
			commerceSubscriptionEntryImpl.setDeliverySubscriptionType(
				deliverySubscriptionType);
		}

		if (deliverySubscriptionTypeSettings == null) {
			commerceSubscriptionEntryImpl.setDeliverySubscriptionTypeSettings(
				"");
		}
		else {
			commerceSubscriptionEntryImpl.setDeliverySubscriptionTypeSettings(
				deliverySubscriptionTypeSettings);
		}

		commerceSubscriptionEntryImpl.setDeliveryCurrentCycle(
			deliveryCurrentCycle);
		commerceSubscriptionEntryImpl.setDeliveryMaxSubscriptionCycles(
			deliveryMaxSubscriptionCycles);
		commerceSubscriptionEntryImpl.setDeliverySubscriptionStatus(
			deliverySubscriptionStatus);

		if (deliveryLastIterationDate == Long.MIN_VALUE) {
			commerceSubscriptionEntryImpl.setDeliveryLastIterationDate(null);
		}
		else {
			commerceSubscriptionEntryImpl.setDeliveryLastIterationDate(
				new Date(deliveryLastIterationDate));
		}

		if (deliveryNextIterationDate == Long.MIN_VALUE) {
			commerceSubscriptionEntryImpl.setDeliveryNextIterationDate(null);
		}
		else {
			commerceSubscriptionEntryImpl.setDeliveryNextIterationDate(
				new Date(deliveryNextIterationDate));
		}

		if (deliveryStartDate == Long.MIN_VALUE) {
			commerceSubscriptionEntryImpl.setDeliveryStartDate(null);
		}
		else {
			commerceSubscriptionEntryImpl.setDeliveryStartDate(
				new Date(deliveryStartDate));
		}

		commerceSubscriptionEntryImpl.resetOriginalValues();

		return commerceSubscriptionEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		uuid = objectInput.readUTF();

		commerceSubscriptionEntryId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		CPInstanceUuid = objectInput.readUTF();

		CProductId = objectInput.readLong();

		commerceOrderItemId = objectInput.readLong();

		subscriptionLength = objectInput.readInt();
		subscriptionType = objectInput.readUTF();
		subscriptionTypeSettings = (String)objectInput.readObject();

		currentCycle = objectInput.readLong();

		maxSubscriptionCycles = objectInput.readLong();

		subscriptionStatus = objectInput.readInt();
		lastIterationDate = objectInput.readLong();
		nextIterationDate = objectInput.readLong();
		startDate = objectInput.readLong();

		deliverySubscriptionLength = objectInput.readInt();
		deliverySubscriptionType = objectInput.readUTF();
		deliverySubscriptionTypeSettings = objectInput.readUTF();

		deliveryCurrentCycle = objectInput.readLong();

		deliveryMaxSubscriptionCycles = objectInput.readLong();

		deliverySubscriptionStatus = objectInput.readInt();
		deliveryLastIterationDate = objectInput.readLong();
		deliveryNextIterationDate = objectInput.readLong();
		deliveryStartDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(commerceSubscriptionEntryId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (CPInstanceUuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(CPInstanceUuid);
		}

		objectOutput.writeLong(CProductId);

		objectOutput.writeLong(commerceOrderItemId);

		objectOutput.writeInt(subscriptionLength);

		if (subscriptionType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(subscriptionType);
		}

		if (subscriptionTypeSettings == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(subscriptionTypeSettings);
		}

		objectOutput.writeLong(currentCycle);

		objectOutput.writeLong(maxSubscriptionCycles);

		objectOutput.writeInt(subscriptionStatus);
		objectOutput.writeLong(lastIterationDate);
		objectOutput.writeLong(nextIterationDate);
		objectOutput.writeLong(startDate);

		objectOutput.writeInt(deliverySubscriptionLength);

		if (deliverySubscriptionType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(deliverySubscriptionType);
		}

		if (deliverySubscriptionTypeSettings == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(deliverySubscriptionTypeSettings);
		}

		objectOutput.writeLong(deliveryCurrentCycle);

		objectOutput.writeLong(deliveryMaxSubscriptionCycles);

		objectOutput.writeInt(deliverySubscriptionStatus);
		objectOutput.writeLong(deliveryLastIterationDate);
		objectOutput.writeLong(deliveryNextIterationDate);
		objectOutput.writeLong(deliveryStartDate);
	}

	public String uuid;
	public long commerceSubscriptionEntryId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String CPInstanceUuid;
	public long CProductId;
	public long commerceOrderItemId;
	public int subscriptionLength;
	public String subscriptionType;
	public String subscriptionTypeSettings;
	public long currentCycle;
	public long maxSubscriptionCycles;
	public int subscriptionStatus;
	public long lastIterationDate;
	public long nextIterationDate;
	public long startDate;
	public int deliverySubscriptionLength;
	public String deliverySubscriptionType;
	public String deliverySubscriptionTypeSettings;
	public long deliveryCurrentCycle;
	public long deliveryMaxSubscriptionCycles;
	public int deliverySubscriptionStatus;
	public long deliveryLastIterationDate;
	public long deliveryNextIterationDate;
	public long deliveryStartDate;

}