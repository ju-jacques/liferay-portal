/**
 * Copyright (c) 2000-2007 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portal.service.persistence;

/**
 * <a href="ServiceComponentPersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface ServiceComponentPersistence {
	public com.liferay.portal.model.ServiceComponent create(
		long serviceComponentId);

	public com.liferay.portal.model.ServiceComponent remove(
		long serviceComponentId)
		throws com.liferay.portal.NoSuchServiceComponentException, 
			com.liferay.portal.SystemException;

	public com.liferay.portal.model.ServiceComponent remove(
		com.liferay.portal.model.ServiceComponent serviceComponent)
		throws com.liferay.portal.SystemException;

	public com.liferay.portal.model.ServiceComponent update(
		com.liferay.portal.model.ServiceComponent serviceComponent)
		throws com.liferay.portal.SystemException;

	public com.liferay.portal.model.ServiceComponent update(
		com.liferay.portal.model.ServiceComponent serviceComponent,
		boolean merge) throws com.liferay.portal.SystemException;

	public com.liferay.portal.model.ServiceComponent updateImpl(
		com.liferay.portal.model.ServiceComponent serviceComponent,
		boolean merge) throws com.liferay.portal.SystemException;

	public com.liferay.portal.model.ServiceComponent findByPrimaryKey(
		long serviceComponentId)
		throws com.liferay.portal.NoSuchServiceComponentException, 
			com.liferay.portal.SystemException;

	public com.liferay.portal.model.ServiceComponent fetchByPrimaryKey(
		long serviceComponentId) throws com.liferay.portal.SystemException;

	public java.util.List findByBuildNamespace(java.lang.String buildNamespace)
		throws com.liferay.portal.SystemException;

	public java.util.List findByBuildNamespace(
		java.lang.String buildNamespace, int begin, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List findByBuildNamespace(
		java.lang.String buildNamespace, int begin, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.portal.model.ServiceComponent findByBuildNamespace_First(
		java.lang.String buildNamespace,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.NoSuchServiceComponentException, 
			com.liferay.portal.SystemException;

	public com.liferay.portal.model.ServiceComponent findByBuildNamespace_Last(
		java.lang.String buildNamespace,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.NoSuchServiceComponentException, 
			com.liferay.portal.SystemException;

	public com.liferay.portal.model.ServiceComponent[] findByBuildNamespace_PrevAndNext(
		long serviceComponentId, java.lang.String buildNamespace,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.NoSuchServiceComponentException, 
			com.liferay.portal.SystemException;

	public com.liferay.portal.model.ServiceComponent findByBNS_BNU(
		java.lang.String buildNamespace, long buildNumber)
		throws com.liferay.portal.NoSuchServiceComponentException, 
			com.liferay.portal.SystemException;

	public com.liferay.portal.model.ServiceComponent fetchByBNS_BNU(
		java.lang.String buildNamespace, long buildNumber)
		throws com.liferay.portal.SystemException;

	public java.util.List findWithDynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer)
		throws com.liferay.portal.SystemException;

	public java.util.List findWithDynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer,
		int begin, int end) throws com.liferay.portal.SystemException;

	public java.util.List findAll() throws com.liferay.portal.SystemException;

	public java.util.List findAll(int begin, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List findAll(int begin, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public void removeByBuildNamespace(java.lang.String buildNamespace)
		throws com.liferay.portal.SystemException;

	public void removeByBNS_BNU(java.lang.String buildNamespace,
		long buildNumber)
		throws com.liferay.portal.NoSuchServiceComponentException, 
			com.liferay.portal.SystemException;

	public void removeAll() throws com.liferay.portal.SystemException;

	public int countByBuildNamespace(java.lang.String buildNamespace)
		throws com.liferay.portal.SystemException;

	public int countByBNS_BNU(java.lang.String buildNamespace, long buildNumber)
		throws com.liferay.portal.SystemException;

	public int countAll() throws com.liferay.portal.SystemException;
}