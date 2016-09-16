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

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class EmptyMessage extends SimpleMessage
{
	public EmptyMessage() {
		super("");
	}

	public static void toConsole(JavaPlugin plugin)
	{
		new EmptyMessage().send(plugin.getServer().getConsoleSender());
	}

	public static void toPlayer(Player player)
	{
		new EmptyMessage().send(player);
	}

	public static void toSenders(CommandSender[] senders)
	{
		new EmptyMessage().send(senders);
	}
}
