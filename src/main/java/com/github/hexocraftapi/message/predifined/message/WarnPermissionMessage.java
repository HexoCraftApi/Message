package com.github.hexocraftapi.message.predifined.message;

/*
 * Copyright 2016 hexosse
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.github.hexocraftapi.message.locale.Locale;
import com.github.hexocraftapi.message.predifined.MessageColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
public class WarnPermissionMessage extends SimpleMessage
{
	public WarnPermissionMessage()
	{
		super(Locale.command_no_permission, MessageColor.WARNING.color());
	}


	public static void toConsole(JavaPlugin plugin)
	{
		new WarnPermissionMessage().send(plugin.getServer().getConsoleSender());
	}

	public static void toPlayer(Player player)
	{
		new WarnPermissionMessage().send(player);
	}

	public static void toSender(CommandSender sender)
	{
		new WarnPermissionMessage().send(sender);
	}

	public static void toSenders(CommandSender[] senders)
	{
		new WarnPermissionMessage().send(senders);
	}
}
