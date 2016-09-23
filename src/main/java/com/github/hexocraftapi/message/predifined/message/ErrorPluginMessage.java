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
import com.github.hexocraftapi.message.Prefix;
import com.github.hexocraftapi.message.predifined.MessageColor;
import com.github.hexocraftapi.message.predifined.prefix.PluginPrefix;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
public class ErrorPluginMessage extends SimplePrefixedMessage
{
	public ErrorPluginMessage(JavaPlugin plugin, String sentence)
	{
		super(new PluginPrefix(plugin), sentence, MessageColor.ERROR.color());
		setLog(true, Level.SEVERE);
	}

	public static void toConsole(JavaPlugin plugin, String sentence)
	{
		new ErrorPluginMessage(plugin, sentence).send(plugin.getServer().getConsoleSender());
	}

	public static void toPlayer(Player player, JavaPlugin plugin, String sentence)
	{
		new ErrorPluginMessage(plugin, sentence).send(player);
	}

	public static void toSender(CommandSender sender, JavaPlugin plugin, String sentence)
	{
		new ErrorPluginMessage(plugin, sentence).send(sender);
	}

	public static void toSenders(CommandSender[] senders, JavaPlugin plugin, String sentence)
	{
		new ErrorPluginMessage(plugin, sentence).send(senders);
	}
}
