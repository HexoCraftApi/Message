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

import com.github.hexocraftapi.message.Message;
import com.github.hexocraftapi.message.predifined.line.Straight;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
public class StraightMessage extends Message
{
	public StraightMessage()
	{
		super();
		add(new Straight());
	}

	public StraightMessage(ChatColor color)
	{
		super();
		add(new Straight(color));
	}

	public StraightMessage(char c, ChatColor color)
	{
		super();
		add(new Straight(c, color));
	}

	public static void toConsole(JavaPlugin plugin)
	{
		new StraightMessage().send(plugin.getServer().getConsoleSender());
	}

	public static void toConsole(JavaPlugin plugin, ChatColor color)
	{
		new StraightMessage(color).send(plugin.getServer().getConsoleSender());
	}
	public static void toConsole(JavaPlugin plugin, char c, ChatColor color)
	{
		new StraightMessage(c, color).send(plugin.getServer().getConsoleSender());
	}

	public static void toPlayer(Player player)
	{
		new StraightMessage().send(player);
	}

	public static void toPlayer(Player player, ChatColor color)
	{
		new StraightMessage(color).send(player);
	}

	public static void toPlayer(Player player, char c, ChatColor color)
	{
		new StraightMessage(c, color).send(player);
	}

	public static void toSender(CommandSender sender)
	{
		new StraightMessage().send(sender);
	}

	public static void toSender(CommandSender sender, ChatColor color)
	{
		new StraightMessage(color).send(sender);
	}

	public static void toSender(CommandSender sender, char c, ChatColor color)
	{
		new StraightMessage(c, color).send(sender);
	}

	public static void toSenders(CommandSender[] senders)
	{
		new StraightMessage().send(senders);
	}

	public static void toSenders(CommandSender[] senders, ChatColor color)
	{
		new StraightMessage(color).send(senders);
	}

	public static void toSenders(CommandSender[] senders, char c, ChatColor color)
	{
		new StraightMessage(c, color).send(senders);
	}
}
