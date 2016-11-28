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
import com.github.hexocraftapi.message.Sentence;
import com.github.hexocraftapi.message.Util.FontUtil;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.github.hexocraftapi.message.predifined.line.utils.LineUtils.legacyLine;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
public class Title extends Line
{
	private char c;
	private ChatColor color;
	private List<Sentence> title;

	public Title(final Prefix prefix, final char c, final Sentence... sentences)
	{
		this(prefix, c, null, sentences);
	}

	public Title(final char c, final Sentence... sentences)
	{
		this(null, c, null, sentences);
	}

	public Title(final char c, final ChatColor color, final Sentence... sentences)
	{
		this(null, c, color, sentences);
	}

	public Title(final Prefix prefix, final char c, final ChatColor color, final Sentence... sentences)
	{
		super(prefix);

		this.c = c;
		this.color = color;
		this.title = Arrays.asList(sentences);
	}

	private Line fontTitleLine(final Prefix prefix, final char c, final List<Sentence> title)
	{
		String legacyTitle = "";
		for(Sentence sentence : title) legacyTitle += sentence.getSentence();

		int prefixWidth = FontUtil.stringWidth(prefix != null ? prefix.toLegacyText() : "");
		int titleWidth = FontUtil.stringWidth(title != null ? legacyTitle.toString() : "");
		int charWidth = FontUtil.stringWidth("" + c);
		int spaceWidth = FontUtil.stringWidth(" ");
		boolean strike = false;

		int maxLength = FontUtil.LINE_WIDTH - (prefixWidth == 0 ? 0 : prefixWidth + spaceWidth);

		int length1 = (maxLength - ( spaceWidth + titleWidth + spaceWidth + (strike?1:0) )) / 2;

		String sLine1 = "";
		for(int i = 0; (i <= length1 && i + charWidth <= length1); i += charWidth)
			sLine1 += c;
		length1 = FontUtil.stringWidth(sLine1) + (strike?1:0);

		int length2 = (maxLength - (spaceWidth + titleWidth + spaceWidth) - (strike?1:0)) - length1;
		String sLine2 = "";
		for(int i = 0; (i <= length2 && i + charWidth <= length2); i += charWidth)
			sLine2 += c;

		Sentence line1 = new Sentence(ChatColor.STRIKETHROUGH + sLine1 + ChatColor.RESET).color(this.color);
		Sentence line2 = new Sentence(ChatColor.STRIKETHROUGH + sLine2 + ChatColor.RESET).color(this.color);

		List<Sentence> fullTitle = new ArrayList<>();
		fullTitle.add(line1);
		for(Sentence sentence : title) fullTitle.add(sentence);
		fullTitle.add(line2);

		return new Line(prefix, fullTitle);
	}

	@Override
	public String toLegacyText()
	{
		String title = "";
		for(Sentence sentence : this.title)
			title += sentence.getSentence();

		List<Sentence> sentences = legacyLine(getPrefix(), this.c, title).getSentences();

		String line = super.toLegacyText();
		if(getPrefix() != null) line += "";

		line += (this.color != null ? this.color : "");
		line += ChatColor.STRIKETHROUGH;
		line += sentences.get(0).toLegacyText();
		line += ChatColor.RESET;
		line += " ";
		line += (this.color != null ? this.color : "");
		line += sentences.get(1).toLegacyText();
		line += ChatColor.RESET;
		line += " ";
		line += (this.color != null ? this.color : "");
		line += ChatColor.STRIKETHROUGH;
		line += sentences.get(2).toLegacyText();
		line += ChatColor.RESET;

		return line;
	}

	@Override
	public MessageBuilder build(MessageBuilder builder)
	{
		// Add prefix
		if(getPrefix() != null)
		{
			getPrefix().build(builder);
			builder.append(" ");
		}

		// Add sentences
		List<Sentence> sentences = fontTitleLine(getPrefix(), this.c, this.title).getSentences();
		for(int i=0; i<sentences.size(); i++)
		{
			Sentence sentence = sentences.get(i);
			sentence.build(builder);
			if(i > 0 && i < sentences.size() - 1) builder.append(" ");
		}

		/*String line = "";
		line += (this.color != null ? this.color : "");
		line += ChatColor.STRIKETHROUGH;
		line += sentences.get(0).toLegacyText();
		line += ChatColor.RESET;
		line += " ";
		line += (this.color != null ? this.color : "");
		line += sentences.get(1).toLegacyText();
		line += ChatColor.RESET;
		line += " ";
		line += (this.color != null ? this.color : "");
		line += ChatColor.STRIKETHROUGH;
		line += sentences.get(2).toLegacyText();
		line += ChatColor.RESET;

		builder.append(line);*/

		return builder;
	}
}
