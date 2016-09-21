package com.github.hexocraftapi.message.predifined.message;

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

import com.github.hexocraftapi.message.Prefix;
import com.github.hexocraftapi.message.locale.Locale;
import com.github.hexocraftapi.message.predifined.MessageColor;
import org.bukkit.entity.Player;

/**
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class WarnPermissionPrefixedMessage extends SimplePrefixedMessage
{
	public WarnPermissionPrefixedMessage(Prefix prefix)
	{
		super(prefix, Locale.command_no_permission, MessageColor.WARNING.color());
	}

	public static void toPlayer(Player player, Prefix prefix, String sentence)
	{
		new WarnPermissionPrefixedMessage(prefix).send(player);
	}
}
