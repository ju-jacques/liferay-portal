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

AUI().ready(() => {
	const Speedwell = window.Speedwell;

	if (!!Speedwell && !!Speedwell.features) {
		Speedwell.features.init.initializeFeatures();

		Speedwell.features.sliders = [];

		if (
			'sliderCallbacks' in Speedwell.features &&
			Speedwell.features.sliderCallbacks.length
		) {
			Speedwell.features.sliderCallbacks.forEach(cb => {
				const componentReady = Liferay.component('SpeedwellSlider');

				if (componentReady) {
					Speedwell.features.sliders.push(cb(componentReady));
				}
			});

			Speedwell.features.sliderCallbacks = [];
		}
	}
});
