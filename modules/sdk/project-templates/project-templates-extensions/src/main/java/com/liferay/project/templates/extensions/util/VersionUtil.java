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

package com.liferay.project.templates.extensions.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Gregory Amerson
 * @author Lawrence Lee
 */
public class VersionUtil {

	public static int getMajorVersion(String targetPlatformVersion) {
		Matcher matcher = _liferayVersionPattern.matcher(targetPlatformVersion);

		if (matcher.matches()) {
			return Integer.parseInt(matcher.group(1));
		}

		return 0;
	}

	public static int getMicroVersion(String targetPlatformVersion) {
		Matcher matcher = _liferayVersionPattern.matcher(targetPlatformVersion);

		if (matcher.matches()) {
			return Integer.parseInt(matcher.group(3));
		}

		return 0;
	}

	public static int getMinorVersion(String targetPlatformVersion) {
		Matcher matcher = _liferayVersionPattern.matcher(targetPlatformVersion);

		if (matcher.matches()) {
			return Integer.parseInt(matcher.group(2));
		}

		return 0;
	}

	public static boolean isLiferayVersion(String targetPlatformVersion) {
		Matcher matcher = _liferayVersionPattern.matcher(targetPlatformVersion);

		return matcher.matches();
	}

	private static final Pattern _liferayVersionPattern = Pattern.compile(
		"^(\\d+)\\.(\\d+)\\.(\\d+)(-\\d+|(\\.((e|f)p)?[0-9]+(-[0-9]+)?))?$");

}