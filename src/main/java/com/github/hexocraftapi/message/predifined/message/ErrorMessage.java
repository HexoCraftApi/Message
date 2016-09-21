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

import com.github.hexocraftapi.message.predifined.MessageColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

import static sun.audio.AudioPlayer.player;

/**
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class ErrorMessage extends SimpleMessage
{
	public ErrorMessage(String sentence)
	{
		super(sentence, MessageColor.ERROR.color());
		setLog(true, Level.SEVERE);
	}

	public static void toConsole(JavaPlugin plugin, String sentence)
	{
		new ErrorMessage(sentence).send(plugin.getServer().getConsoleSender());
	}

	public static void toPlayer(Player player, String sentence)
	{
		new ErrorMessage(sentence).send(player);
	}

	public static void toSender(CommandSender sender, String sentence)
	{
		new ErrorMessage(sentence).send(sender);
	}

	public static void toSenders(CommandSender[] senders, String sentence)
	{
		new ErrorMessage(sentence).send(senders);
	}
}
