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

import com.github.hexocraftapi.message.Message;
import com.github.hexocraftapi.message.predifined.line.SimpleLine;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class SimpleMessage extends Message
{
	public SimpleMessage(String sentence)
	{
		super();
		add(new SimpleLine(sentence));
	}

	public SimpleMessage(String sentence, ChatColor color)
	{
		super();
		add(new SimpleLine(sentence, color));
	}

	public static void toConsole(JavaPlugin plugin, String sentence)
	{
		new SimpleMessage(sentence).send(plugin.getServer().getConsoleSender());
	}

	public static void toConsole(JavaPlugin plugin, String sentence, ChatColor color)
	{
		new SimpleMessage(sentence, color).send(plugin.getServer().getConsoleSender());
	}

	public static void toPlayer(Player player, String sentence)
	{
		new SimpleMessage(sentence).send(player);
	}

	public static void toPlayer(Player player, String sentence, ChatColor color)
	{
		new SimpleMessage(sentence, color).send(player);
	}
}
