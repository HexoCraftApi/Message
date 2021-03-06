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
import com.github.hexocraftapi.message.predifined.line.SimpleLine;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
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
	public SimpleMessage(Sentence sentence)
	{
		super();
		add(new Line(sentence));
	}


	public static void toConsole(JavaPlugin plugin, String sentence)
	{
		new SimpleMessage(sentence).send(plugin.getServer().getConsoleSender());
	}

	public static void toConsole(JavaPlugin plugin, String sentence, ChatColor color)
	{
		new SimpleMessage(sentence, color).send(plugin.getServer().getConsoleSender());
	}

	public static void toConsole(JavaPlugin plugin, Sentence sentence)
	{
		new SimpleMessage(sentence).send(plugin.getServer().getConsoleSender());
	}

	public static void toPlayer(Player player, String sentence)
	{
		new SimpleMessage(sentence).send(player);
	}

	public static void toPlayer(Player player, String sentence, ChatColor color)
	{
		new SimpleMessage(sentence, color).send(player);
	}

	public static void toPlayer(Player player, Sentence sentence)
	{
		new SimpleMessage(sentence).send(player);
	}

	public static void toSender(CommandSender sender, String sentence)
	{
		new SimpleMessage(sentence).send(sender);
	}

	public static void toSender(CommandSender sender, String sentence, ChatColor color)
	{
		new SimpleMessage(sentence, color).send(sender);
	}

	public static void toSender(CommandSender sender, Sentence sentence)
	{
		new SimpleMessage(sentence).send(sender);
	}

	public static void toSenders(CommandSender[] senders, String sentence)
	{
		new SimpleMessage(sentence).send(senders);
	}

	public static void toSenders(CommandSender[] senders, String sentence, ChatColor color)
	{
		new SimpleMessage(sentence, color).send(senders);
	}

	public static void toSenders(CommandSender[] senders, Sentence sentence)
	{
		new SimpleMessage(sentence).send(senders);
	}
}
