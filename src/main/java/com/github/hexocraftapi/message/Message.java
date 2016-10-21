package com.github.hexocraftapi.message;

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

import com.github.hexocraftapi.chat.Chat;
import com.github.hexocraftapi.chat.MessageBuilder;
import com.github.hexocraftapi.chat.component.BaseComponent;
import com.github.hexocraftapi.util.PlayerUtil;
import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
public class Message
{
	private List<Line> lines;                // Lines of the message
	protected boolean log = false;           // If set to true, this message will be logged
	protected Level severity = Level.OFF;    // Log level

	public Message() { this.lines = new ArrayList<Line>(); }

	public Message(Line line) { this.lines = Lists.newArrayList(line); }

	public Message(String message)
	{
		//todo : FontUtil.wordWrap
		this.lines = Lists.newArrayList(new Line(message));
	}

	public Message(String message, ChatColor color)
	{
		//todo : FontUtil.wordWrap
		this.lines = Lists.newArrayList(new Line(message, color));
	}

	public Message add(Line line)
	{
		this.lines.add(line);
		return this;
	}

	public Message add(Line... lines)
	{
		for(Line line : lines)
			this.lines.add(line);
		return this;
	}

	public Message add(String message)
	{
		//todo : FontUtil.wordWrap
		this.lines.add(new Line(message));
		return this;
	}

	public Message add(String message, ChatColor color)
	{
		//todo : FontUtil.wordWrap
		this.lines.add(new Line(message, color));
		return this;
	}

	public List<Line> getLines()
	{
		return this.lines;
	}

	public boolean isLog() { return log; }

	public void setLog(boolean log) { this.log = log; }

	public void setLog(boolean log, Level severity)
	{
		this.log = log;
		this.severity = severity;
	}

	public void send(CommandSender receiver)
	{
		if(receiver instanceof Player)
			sendToPlayer((Player) receiver);
		else
			sendToConsole(receiver);
	}

	public void send(CommandSender[] receivers)
	{
		List<Player> players = new ArrayList<>(receivers.length);
		CommandSender console = null;

		for(CommandSender receiver : receivers)
		{
			if(receiver instanceof Player)
				players.add((Player) receiver);
			else
				console = receiver;
		}

		if(players.size()>0)
			sendToPlayer(players.toArray(new Player[players.size()] ));
		if(console!=null)
			sendToConsole(console);
	}

	public void broadcast()
	{
		for(Player player : PlayerUtil.getOnlinePlayers())
			sendToPlayer(player);
	}

	private void sendToPlayer(final Player... players)
	{
		for(Line line : lines)
		{
			// Build the line
			MessageBuilder builder = new MessageBuilder("");
			line.build(builder);

			// Create the message
			BaseComponent[] message = builder.create();

			// Send the message
			for(Player player : players)
				Chat.sendMessage(player, message);
		}
	}

	private void sendToConsole(final CommandSender receiver)
	{
		for(Line line : lines)
		{
			String legacy = line.toLegacyText();
			receiver.sendMessage(legacy);

			if(this.isLog())
				Logger.getLogger("Minecraft").log(this.severity, ChatColor.stripColor(legacy));
		}
	}
}