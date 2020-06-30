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

package com.liferay.journal.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.AttachedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ResourcedModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedGroupedModel;
import com.liferay.portal.kernel.model.TrashedModel;
import com.liferay.portal.kernel.model.WorkflowedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the JournalArticle service. Represents a row in the &quot;JournalArticle&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.journal.model.impl.JournalArticleModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.journal.model.impl.JournalArticleImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JournalArticle
 * @generated
 */
@ProviderType
public interface JournalArticleModel
	extends AttachedModel, BaseModel<JournalArticle>, ResourcedModel,
			ShardedModel, StagedGroupedModel, TrashedModel, WorkflowedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a journal article model instance should use the {@link JournalArticle} interface instead.
	 */

	/**
	 * Returns the primary key of this journal article.
	 *
	 * @return the primary key of this journal article
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this journal article.
	 *
	 * @param primaryKey the primary key of this journal article
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this journal article.
	 *
	 * @return the uuid of this journal article
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this journal article.
	 *
	 * @param uuid the uuid of this journal article
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the ID of this journal article.
	 *
	 * @return the ID of this journal article
	 */
	public long getId();

	/**
	 * Sets the ID of this journal article.
	 *
	 * @param id the ID of this journal article
	 */
	public void setId(long id);

	/**
	 * Returns the resource prim key of this journal article.
	 *
	 * @return the resource prim key of this journal article
	 */
	@Override
	public long getResourcePrimKey();

	/**
	 * Sets the resource prim key of this journal article.
	 *
	 * @param resourcePrimKey the resource prim key of this journal article
	 */
	@Override
	public void setResourcePrimKey(long resourcePrimKey);

	@Override
	public boolean isResourceMain();

	/**
	 * Returns the group ID of this journal article.
	 *
	 * @return the group ID of this journal article
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this journal article.
	 *
	 * @param groupId the group ID of this journal article
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this journal article.
	 *
	 * @return the company ID of this journal article
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this journal article.
	 *
	 * @param companyId the company ID of this journal article
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this journal article.
	 *
	 * @return the user ID of this journal article
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this journal article.
	 *
	 * @param userId the user ID of this journal article
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this journal article.
	 *
	 * @return the user uuid of this journal article
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this journal article.
	 *
	 * @param userUuid the user uuid of this journal article
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this journal article.
	 *
	 * @return the user name of this journal article
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this journal article.
	 *
	 * @param userName the user name of this journal article
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this journal article.
	 *
	 * @return the create date of this journal article
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this journal article.
	 *
	 * @param createDate the create date of this journal article
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this journal article.
	 *
	 * @return the modified date of this journal article
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this journal article.
	 *
	 * @param modifiedDate the modified date of this journal article
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the folder ID of this journal article.
	 *
	 * @return the folder ID of this journal article
	 */
	public long getFolderId();

	/**
	 * Sets the folder ID of this journal article.
	 *
	 * @param folderId the folder ID of this journal article
	 */
	public void setFolderId(long folderId);

	/**
	 * Returns the fully qualified class name of this journal article.
	 *
	 * @return the fully qualified class name of this journal article
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this journal article.
	 *
	 * @return the class name ID of this journal article
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this journal article.
	 *
	 * @param classNameId the class name ID of this journal article
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class pk of this journal article.
	 *
	 * @return the class pk of this journal article
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class pk of this journal article.
	 *
	 * @param classPK the class pk of this journal article
	 */
	@Override
	public void setClassPK(long classPK);

	/**
	 * Returns the tree path of this journal article.
	 *
	 * @return the tree path of this journal article
	 */
	@AutoEscape
	public String getTreePath();

	/**
	 * Sets the tree path of this journal article.
	 *
	 * @param treePath the tree path of this journal article
	 */
	public void setTreePath(String treePath);

	/**
	 * Returns the article ID of this journal article.
	 *
	 * @return the article ID of this journal article
	 */
	@AutoEscape
	public String getArticleId();

	/**
	 * Sets the article ID of this journal article.
	 *
	 * @param articleId the article ID of this journal article
	 */
	public void setArticleId(String articleId);

	/**
	 * Returns the version of this journal article.
	 *
	 * @return the version of this journal article
	 */
	public double getVersion();

	/**
	 * Sets the version of this journal article.
	 *
	 * @param version the version of this journal article
	 */
	public void setVersion(double version);

	/**
	 * Returns the url title of this journal article.
	 *
	 * @return the url title of this journal article
	 */
	@AutoEscape
	public String getUrlTitle();

	/**
	 * Sets the url title of this journal article.
	 *
	 * @param urlTitle the url title of this journal article
	 */
	public void setUrlTitle(String urlTitle);

	/**
	 * Returns the content of this journal article.
	 *
	 * @return the content of this journal article
	 */
	@AutoEscape
	public String getContent();

	/**
	 * Sets the content of this journal article.
	 *
	 * @param content the content of this journal article
	 */
	public void setContent(String content);

	/**
	 * Returns the ddm structure key of this journal article.
	 *
	 * @return the ddm structure key of this journal article
	 */
	@AutoEscape
	public String getDDMStructureKey();

	/**
	 * Sets the ddm structure key of this journal article.
	 *
	 * @param DDMStructureKey the ddm structure key of this journal article
	 */
	public void setDDMStructureKey(String DDMStructureKey);

	/**
	 * Returns the ddm template key of this journal article.
	 *
	 * @return the ddm template key of this journal article
	 */
	@AutoEscape
	public String getDDMTemplateKey();

	/**
	 * Sets the ddm template key of this journal article.
	 *
	 * @param DDMTemplateKey the ddm template key of this journal article
	 */
	public void setDDMTemplateKey(String DDMTemplateKey);

	/**
	 * Returns the default language ID of this journal article.
	 *
	 * @return the default language ID of this journal article
	 */
	@AutoEscape
	public String getDefaultLanguageId();

	/**
	 * Sets the default language ID of this journal article.
	 *
	 * @param defaultLanguageId the default language ID of this journal article
	 */
	public void setDefaultLanguageId(String defaultLanguageId);

	/**
	 * Returns the layout uuid of this journal article.
	 *
	 * @return the layout uuid of this journal article
	 */
	@AutoEscape
	public String getLayoutUuid();

	/**
	 * Sets the layout uuid of this journal article.
	 *
	 * @param layoutUuid the layout uuid of this journal article
	 */
	public void setLayoutUuid(String layoutUuid);

	/**
	 * Returns the display date of this journal article.
	 *
	 * @return the display date of this journal article
	 */
	public Date getDisplayDate();

	/**
	 * Sets the display date of this journal article.
	 *
	 * @param displayDate the display date of this journal article
	 */
	public void setDisplayDate(Date displayDate);

	/**
	 * Returns the expiration date of this journal article.
	 *
	 * @return the expiration date of this journal article
	 */
	public Date getExpirationDate();

	/**
	 * Sets the expiration date of this journal article.
	 *
	 * @param expirationDate the expiration date of this journal article
	 */
	public void setExpirationDate(Date expirationDate);

	/**
	 * Returns the review date of this journal article.
	 *
	 * @return the review date of this journal article
	 */
	public Date getReviewDate();

	/**
	 * Sets the review date of this journal article.
	 *
	 * @param reviewDate the review date of this journal article
	 */
	public void setReviewDate(Date reviewDate);

	/**
	 * Returns the indexable of this journal article.
	 *
	 * @return the indexable of this journal article
	 */
	public boolean getIndexable();

	/**
	 * Returns <code>true</code> if this journal article is indexable.
	 *
	 * @return <code>true</code> if this journal article is indexable; <code>false</code> otherwise
	 */
	public boolean isIndexable();

	/**
	 * Sets whether this journal article is indexable.
	 *
	 * @param indexable the indexable of this journal article
	 */
	public void setIndexable(boolean indexable);

	/**
	 * Returns the small image of this journal article.
	 *
	 * @return the small image of this journal article
	 */
	public boolean getSmallImage();

	/**
	 * Returns <code>true</code> if this journal article is small image.
	 *
	 * @return <code>true</code> if this journal article is small image; <code>false</code> otherwise
	 */
	public boolean isSmallImage();

	/**
	 * Sets whether this journal article is small image.
	 *
	 * @param smallImage the small image of this journal article
	 */
	public void setSmallImage(boolean smallImage);

	/**
	 * Returns the small image ID of this journal article.
	 *
	 * @return the small image ID of this journal article
	 */
	public long getSmallImageId();

	/**
	 * Sets the small image ID of this journal article.
	 *
	 * @param smallImageId the small image ID of this journal article
	 */
	public void setSmallImageId(long smallImageId);

	/**
	 * Returns the small image url of this journal article.
	 *
	 * @return the small image url of this journal article
	 */
	@AutoEscape
	public String getSmallImageURL();

	/**
	 * Sets the small image url of this journal article.
	 *
	 * @param smallImageURL the small image url of this journal article
	 */
	public void setSmallImageURL(String smallImageURL);

	/**
	 * Returns the last publish date of this journal article.
	 *
	 * @return the last publish date of this journal article
	 */
	@Override
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this journal article.
	 *
	 * @param lastPublishDate the last publish date of this journal article
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate);

	/**
	 * Returns the status of this journal article.
	 *
	 * @return the status of this journal article
	 */
	@Override
	public int getStatus();

	/**
	 * Sets the status of this journal article.
	 *
	 * @param status the status of this journal article
	 */
	@Override
	public void setStatus(int status);

	/**
	 * Returns the status by user ID of this journal article.
	 *
	 * @return the status by user ID of this journal article
	 */
	@Override
	public long getStatusByUserId();

	/**
	 * Sets the status by user ID of this journal article.
	 *
	 * @param statusByUserId the status by user ID of this journal article
	 */
	@Override
	public void setStatusByUserId(long statusByUserId);

	/**
	 * Returns the status by user uuid of this journal article.
	 *
	 * @return the status by user uuid of this journal article
	 */
	@Override
	public String getStatusByUserUuid();

	/**
	 * Sets the status by user uuid of this journal article.
	 *
	 * @param statusByUserUuid the status by user uuid of this journal article
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid);

	/**
	 * Returns the status by user name of this journal article.
	 *
	 * @return the status by user name of this journal article
	 */
	@AutoEscape
	@Override
	public String getStatusByUserName();

	/**
	 * Sets the status by user name of this journal article.
	 *
	 * @param statusByUserName the status by user name of this journal article
	 */
	@Override
	public void setStatusByUserName(String statusByUserName);

	/**
	 * Returns the status date of this journal article.
	 *
	 * @return the status date of this journal article
	 */
	@Override
	public Date getStatusDate();

	/**
	 * Sets the status date of this journal article.
	 *
	 * @param statusDate the status date of this journal article
	 */
	@Override
	public void setStatusDate(Date statusDate);

	/**
	 * Returns the trash entry created when this journal article was moved to the Recycle Bin. The trash entry may belong to one of the ancestors of this journal article.
	 *
	 * @return the trash entry created when this journal article was moved to the Recycle Bin
	 */
	@Override
	public com.liferay.trash.kernel.model.TrashEntry getTrashEntry()
		throws PortalException;

	/**
	 * Returns the class primary key of the trash entry for this journal article.
	 *
	 * @return the class primary key of the trash entry for this journal article
	 */
	@Override
	public long getTrashEntryClassPK();

	/**
	 * Returns the trash handler for this journal article.
	 *
	 * @return the trash handler for this journal article
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public com.liferay.portal.kernel.trash.TrashHandler getTrashHandler();

	/**
	 * Returns <code>true</code> if this journal article is in the Recycle Bin.
	 *
	 * @return <code>true</code> if this journal article is in the Recycle Bin; <code>false</code> otherwise
	 */
	@Override
	public boolean isInTrash();

	/**
	 * Returns <code>true</code> if the parent of this journal article is in the Recycle Bin.
	 *
	 * @return <code>true</code> if the parent of this journal article is in the Recycle Bin; <code>false</code> otherwise
	 */
	@Override
	public boolean isInTrashContainer();

	@Override
	public boolean isInTrashExplicitly();

	@Override
	public boolean isInTrashImplicitly();

	/**
	 * Returns <code>true</code> if this journal article is approved.
	 *
	 * @return <code>true</code> if this journal article is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved();

	/**
	 * Returns <code>true</code> if this journal article is denied.
	 *
	 * @return <code>true</code> if this journal article is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied();

	/**
	 * Returns <code>true</code> if this journal article is a draft.
	 *
	 * @return <code>true</code> if this journal article is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft();

	/**
	 * Returns <code>true</code> if this journal article is expired.
	 *
	 * @return <code>true</code> if this journal article is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired();

	/**
	 * Returns <code>true</code> if this journal article is inactive.
	 *
	 * @return <code>true</code> if this journal article is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive();

	/**
	 * Returns <code>true</code> if this journal article is incomplete.
	 *
	 * @return <code>true</code> if this journal article is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete();

	/**
	 * Returns <code>true</code> if this journal article is pending.
	 *
	 * @return <code>true</code> if this journal article is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending();

	/**
	 * Returns <code>true</code> if this journal article is scheduled.
	 *
	 * @return <code>true</code> if this journal article is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled();

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(JournalArticle journalArticle);

	@Override
	public int hashCode();

	@Override
	public CacheModel<JournalArticle> toCacheModel();

	@Override
	public JournalArticle toEscapedModel();

	@Override
	public JournalArticle toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();

}