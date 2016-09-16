package com.github.hexocraftapi.message.predifined;

/*
 * Copyright 2015 hexosse
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

import org.bukkit.ChatColor;

/**
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public enum MessageColor
{
	// Default color used when not specified
	DEFAULT(ChatColor.WHITE),

	// Colors used with commands
	COMMAND(ChatColor.AQUA),
	SUBCOMMAND(ChatColor.AQUA),
	MANDATORY_ARGUMENT(ChatColor.DARK_GREEN),
	OPTIONAL_ARGUMENT(ChatColor.DARK_AQUA),
	DESCRIPTION(ChatColor.YELLOW),

	// Colors used for severity
	INFO(ChatColor.WHITE),
	WARNING(ChatColor.GOLD),
	ERROR(ChatColor.RED);

	//
	private ChatColor color;

	//
	MessageColor(ChatColor color) { this.color = color; }
	MessageColor(MessageColor color) { this.color = color.color(); }

	public ChatColor color() { return this.color; }
	public String toString() { return color().toString(); }

}
