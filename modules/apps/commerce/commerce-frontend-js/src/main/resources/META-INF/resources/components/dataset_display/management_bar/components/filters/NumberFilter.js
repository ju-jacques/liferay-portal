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

import ClayButton from '@clayui/button';
import classNames from 'classnames';
import PropTypes from 'prop-types';
import React, {useState} from 'react';

import getAppContext from '../Context';

function NumberFilter(props) {
	const {actions} = getAppContext();
	const [value, setValue] = useState(props.value);

	return (
		<div className="form-group">
			<div className="input-group">
				<div
					className={classNames('input-group-item', {
						'input-group-prepend': props.inputText
					})}
				>
					<input
						className="form-control"
						max={props.max}
						min={props.min}
						onChange={e => setValue(e.target.value)}
						type="number"
						value={value || ''}
					/>
				</div>
				{props.inputText && (
					<div className="input-group-append input-group-item input-group-item-shrink">
						<span className="input-group-text">
							{props.inputText}
						</span>
					</div>
				)}
			</div>
			<div className="mt-3">
				<ClayButton
					className="btn-sm"
					disabled={Number(value) === props.value}
					onClick={() =>
						actions.updateFilterValue(props.id, Number(value))
					}
				>
					{props.panelType === 'edit'
						? Liferay.Language.get('edit-filter')
						: Liferay.Language.get('add-filter')}
				</ClayButton>
			</div>
		</div>
	);
}

NumberFilter.propTypes = {
	id: PropTypes.string.isRequired,
	inputText: PropTypes.string,
	invisible: PropTypes.bool,
	label: PropTypes.string.isRequired,
	max: PropTypes.number,
	min: PropTypes.number,
	operator: PropTypes.oneOf(['eq', 'ne', 'gt', 'ge', 'lt', 'le']).isRequired,
	type: PropTypes.oneOf(['number']).isRequired,
	value: PropTypes.number
};

export default NumberFilter;
