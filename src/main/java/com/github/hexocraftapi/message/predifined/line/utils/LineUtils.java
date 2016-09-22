package com.github.hexocraftapi.message.predifined.line.utils;

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
import org.bukkit.ChatColor;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
public class LineUtils
{
	public static int CONSOLE_WIDTH = 120;

	public static Line legacyLine(char c)
	{
		return legacyLine(null, c);
	}

	public static Line legacyLine(Prefix prefix, char c)
	{
		String stripPrefix = ChatColor.stripColor(prefix!=null?prefix.toLegacyText():"");
		int max = CONSOLE_WIDTH - (prefix != null ? stripPrefix.length() + 1 : 0);

		String line = "";
		for(int i = 0; i < max; i++)
			line += c;

		return new Line(prefix, line);
	}

	public static Line legacyLine(Prefix prefix, char c, String title)
	{
		String stripPrefix = ChatColor.stripColor(prefix!=null?prefix.toString():"");
		int max = CONSOLE_WIDTH - (prefix != null ? stripPrefix.length() + 1 : 0);

		title = ChatColor.stripColor(title);
		int max1 = (max - (title.length()+2)) /2;
		int max2 = (max - (title.length()+2)) - max1;

		String line1 = "";
		for(int i = 0; i < max1; i++)
			line1 += c;

		String line2 = "";
		for(int i = 0; i < max2; i++)
			line2 += c;

		return new Line(prefix, line1, title, line2);
	}
}
