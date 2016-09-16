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
import com.github.hexocraftapi.message.predifined.prefix.PluginPrefix;
import com.github.hexocraftapi.message.Line;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class PluginMessage extends Message
{
	private JavaPlugin plugin;
	private PluginPrefix prefix;
	private String text;

	public PluginMessage(JavaPlugin plugin, String text)
	{
		this(plugin, text, MessageColor.INFO.color());
	}

	public PluginMessage(JavaPlugin plugin, String text, Line... lines)
	{
		this(plugin, text, MessageColor.INFO.color(), lines);
	}

	public PluginMessage(JavaPlugin plugin, String text, ChatColor color, Line... lines)
	{
		this.plugin = plugin;
		this.text = text;

		build(color, lines);
	}

	private void build(ChatColor color, Line... lines)
	{
		// Prefix
		if(this.prefix == null)
			prefix = new PluginPrefix(this.plugin);

		// Line
		this.add(new Line(prefix, this.text, color));

		// Lines
		for(Line line : lines)
		{
			line.setPrefix(this.prefix);
			this.add(line);
		}
	}

	public static void toConsole(JavaPlugin plugin, String text, ChatColor color, Line... lines)
	{
		new PluginMessage(plugin, text, color, lines).send(plugin.getServer().getConsoleSender());
	}

	public static void toPlayer(Player player, JavaPlugin plugin, String text, ChatColor color, Line... lines)
	{
		new PluginMessage(plugin, text, color, lines).send(player);
	}
}
