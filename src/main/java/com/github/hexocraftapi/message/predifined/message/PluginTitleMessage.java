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

import com.github.hexocraftapi.message.Line;
import com.github.hexocraftapi.message.Message;
import com.github.hexocraftapi.message.Sentence;
import com.github.hexocraftapi.message.predifined.MessageColor;
import com.github.hexocraftapi.message.predifined.line.Straight;
import com.github.hexocraftapi.message.predifined.line.Title;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
public class PluginTitleMessage extends Message
{
	private JavaPlugin plugin;
	private String text;

	public PluginTitleMessage(JavaPlugin plugin, String text)
	{
		this(plugin, text, MessageColor.INFO.color());
	}

	public PluginTitleMessage(JavaPlugin plugin, String text, Line... lines)
	{
		this(plugin, text, MessageColor.INFO.color(), lines);
	}

	public PluginTitleMessage(JavaPlugin plugin, String text, ChatColor color, Line... lines)
	{
		this.plugin = plugin;
		this.text = text;

		build(color, lines);
	}

	@Override
	public Message add(Line line)
	{
		return super.add(getLines().size()-1, line);
	}

	@Override
	public Message add(Line... lines)
	{
		return super.add(getLines().size()-1, lines);
	}

	@Override
	public Message add(String message)
	{
		return super.add(getLines().size()-1, message);
	}

	@Override
	public Message add(String message, ChatColor color)
	{
		return super.add(getLines().size()-1, message, color);
	}

	private void build(ChatColor color, Line... lines)
	{
		// Title : line first
		super.add(new Title('-',ChatColor.AQUA,  new Sentence(plugin.getDescription().getName() + " " + plugin.getDescription().getVersion())));

		// Line
		super.add(new Line(this.text, color));

		// Lines
		for(Line line : lines)
			super.add(line);

		// Straight line last
		super.add(new Straight('-', ChatColor.AQUA));
	}

	public static void toConsole(JavaPlugin plugin, String text, ChatColor color, Line... lines)
	{
		new PluginTitleMessage(plugin, text, color, lines).send(plugin.getServer().getConsoleSender());
	}

	public static void toPlayer(Player player, JavaPlugin plugin, String text, ChatColor color, Line... lines)
	{
		new PluginTitleMessage(plugin, text, color, lines).send(player);
	}

	public static void toSender(CommandSender sender, JavaPlugin plugin, String text, ChatColor color, Line... lines)
	{
		new PluginTitleMessage(plugin, text, color, lines).send(sender);
	}

	public static void toSenders(CommandSender[] senders, JavaPlugin plugin, String text, ChatColor color, Line... lines)
	{
		new PluginTitleMessage(plugin, text, color, lines).send(senders);
	}
}
