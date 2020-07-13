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

import process from 'process';

export {default as render} from './render.es';
export {default as useEventListener} from './hooks/useEventListener.es';
export {default as useInterval} from './hooks/useInterval.es';
export {default as useIsMounted} from './hooks/useIsMounted.es';
export {default as usePrevious} from './hooks/usePrevious.es';
export {default as useStateSafe} from './hooks/useStateSafe.es';
export {default as useThunk} from './hooks/useThunk.es';
export {default as useTimeout} from './hooks/useTimeout.es';

// Egregious hack because react-dnd expects `window.process` to exist:
//
// https://github.com/react-dnd/asap/blob/b6bebeb734/src/node/asap.ts#L24

if (!window.process) {
	window.process = process;
}
