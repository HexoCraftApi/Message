package com.github.hexocraftapi.message.predifined.line;

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

import com.github.hexocraftapi.chat.MessageBuilder;
import com.github.hexocraftapi.message.Line;
import com.github.hexocraftapi.message.Prefix;
import com.github.hexocraftapi.message.Util.FontUtil;
import com.github.hexocraftapi.message.predifined.line.utils.LineUtils;
import org.bukkit.ChatColor;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
public class Straight extends Line
{
	private static char symbol = '-';
	private char c;
	private ChatColor color;

	public Straight()
	{
		this(null, symbol, null);
	}

	public Straight(Prefix prefix)
	{
		this(prefix, symbol, null);
	}

	public Straight(ChatColor color)
	{
		this(null, symbol, null);
	}

	public Straight(Prefix prefix, ChatColor color)
	{
		this(prefix, symbol, color);
	}
	public Straight(char c, ChatColor color)
	{
		this(null, symbol, color);
	}

	public Straight(Prefix prefix, char c, ChatColor color)
	{
		super(prefix);
		this.c = c;
		this.color = color;
	}

	private Line fontLine(Prefix prefix, ChatColor format, char c)
	{
		int prefixWidth = FontUtil.stringWidth(prefix != null ? prefix.toLegacyText() : "");
		int charWidth = FontUtil.stringWidth(""  + c);
		int spaceWidth = FontUtil.stringWidth(" ");
		boolean strike = format!=null&&format==ChatColor.STRIKETHROUGH;

		int maxWidth = FontUtil.LINE_WIDTH - (prefixWidth == 0 ? 0 : prefixWidth + spaceWidth) - (strike?1:0);

		String line = "";
		for(int i = 0; i + charWidth <= maxWidth; i += charWidth)
			line += c;

		return new Line(prefix, line);
	}

	@Override
	public String toLegacyText()
	{
		String line = super.toLegacyText();
		if(getPrefix() != null && line.endsWith(" ")==false) line += " ";
		line += this.color;
		line += ChatColor.STRIKETHROUGH;
		line += LineUtils.legacyLine(getPrefix(), this.c).getSentences().get(0).toLegacyText();
		line += ChatColor.RESET;

		return line;
	}

	@Override
	public MessageBuilder build(MessageBuilder builder)
	{
		// Add prefix
		if(getPrefix() != null)
			getPrefix().build(builder);

		// Space between prefix and line
		if(getPrefix() != null)
			builder.append(" ");

		// Add sentences
		String line = fontLine(getPrefix(), ChatColor.STRIKETHROUGH, this.c).getSentences().get(0).toLegacyText();
		builder.append(ChatColor.STRIKETHROUGH + line);
		builder.color(this.color);

		return builder;
	}
}
