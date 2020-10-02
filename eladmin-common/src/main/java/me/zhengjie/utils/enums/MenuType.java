/*
 *  Copyright 2019-2020 Fang Jin Biao
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package me.zhengjie.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 菜单类型：目录、菜单、按钮
 * 
 * @author adyfang
 * @date 2020年9月12日
 */
@Getter
@AllArgsConstructor
public enum MenuType {
	FOLDER(0, "目录"), MENU(1, "菜单"), BUTTON(2, "按钮");

	private final int value;
	private final String description;

	public static MenuType find(int code) {
		for (MenuType value : MenuType.values()) {
			if (code == value.getValue()) {
				return value;
			}
		}
		return null;
	}
}
