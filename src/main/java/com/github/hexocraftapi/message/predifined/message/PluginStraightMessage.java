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
import com.github.hexocraftapi.message.predifined.MessageColor;
import com.github.hexocraftapi.message.predifined.line.Straight;
import com.github.hexocraftapi.message.predifined.prefix.PluginPrefix;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class PluginStraightMessage extends Message
{
	private JavaPlugin plugin;
	private PluginPrefix prefix;

	public PluginStraightMessage(JavaPlugin plugin)
	{
		this(plugin, MessageColor.INFO.color());
	}

	public PluginStraightMessage(JavaPlugin plugin, ChatColor color)
	{
		this(plugin, '-', color);
	}

	public PluginStraightMessage(JavaPlugin plugin, char c, ChatColor color)
	{
		this.plugin = plugin;

		build(c, color);
	}

	private void build(char c, ChatColor color)
	{
		// Prefix
		if(this.prefix == null)
			prefix = new PluginPrefix(this.plugin);

		// Line
		this.add(new Straight(prefix, c, color));
	}

	public static void toConsole(JavaPlugin plugin)
	{
		new PluginStraightMessage(plugin).send(plugin.getServer().getConsoleSender());
	}

	public static void toConsole(JavaPlugin plugin, ChatColor color)
	{
		new PluginStraightMessage(plugin, color).send(plugin.getServer().getConsoleSender());
	}

	public static void toConsole(JavaPlugin plugin, char c, ChatColor color)
	{
		new PluginStraightMessage(plugin, c, color).send(plugin.getServer().getConsoleSender());
	}

	public static void toPlayer(Player player, JavaPlugin plugin)
	{
		new PluginStraightMessage(plugin).send(player);
	}

	public static void toPlayer(Player player, JavaPlugin plugin, ChatColor color)
	{
		new PluginStraightMessage(plugin, color).send(player);
	}

	public static void toPlayer(Player player, JavaPlugin plugin, char c, ChatColor color)
	{
		new PluginStraightMessage(plugin, c, color).send(player);
	}

	public static void toSender(CommandSender sender, JavaPlugin plugin)
	{
		new PluginStraightMessage(plugin).send(sender);
	}

	public static void toSender(CommandSender sender, JavaPlugin plugin, ChatColor color)
	{
		new PluginStraightMessage(plugin, color).send(sender);
	}

	public static void toSender(CommandSender sender, JavaPlugin plugin, char c, ChatColor color)
	{
		new PluginStraightMessage(plugin, c, color).send(sender);
	}

	public static void toSenders(CommandSender[] senders, JavaPlugin plugin)
	{
		new PluginStraightMessage(plugin).send(senders);
	}

	public static void toSenders(CommandSender[] senders, JavaPlugin plugin, ChatColor color)
	{
		new PluginStraightMessage(plugin, color).send(senders);
	}

	public static void toSenders(CommandSender[] senders, JavaPlugin plugin, char c, ChatColor color)
	{
		new PluginStraightMessage(plugin, c, color).send(senders);
	}
}
