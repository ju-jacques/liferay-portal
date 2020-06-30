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

import {OPEN_MODAL} from './eventsDefinitions.es';

export const iframeHandlerModalNamespace = 'iframe-handler-modal_';
export let counter = 0;
export const iframeInitialHandlerModalId = `${iframeHandlerModalNamespace}${counter}`;

Liferay.on('endNavigate', () => {
	counter = 0;
});

export function getIframeHandlerModalId() {
	return `${iframeHandlerModalNamespace}${counter++}`;
}

export function isPageInIframe() {
	return window.location !== window.parent.location;
}

export function initializeIframeListeners() {
	Liferay.on(OPEN_MODAL, payload => {
		window.top.Liferay.fire(OPEN_MODAL, {
			...payload,
			id: iframeInitialHandlerModalId
		});
	});
}
