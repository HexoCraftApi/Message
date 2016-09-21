package com.github.hexocraftapi.message.predifined.line;

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

import com.github.hexocraftapi.chat.MessageBuilder;
import com.github.hexocraftapi.message.Line;
import com.github.hexocraftapi.message.Prefix;
import com.github.hexocraftapi.message.Sentence;
import com.github.hexocraftapi.message.Util.FontUtil;
import org.bukkit.ChatColor;

import java.util.Random;

import static com.github.hexocraftapi.message.predifined.line.utils.LineUtils.CONSOLE_WIDTH;

/**
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class Magic extends Line
{
	private int count;

	public Magic(int count)
	{
		this(null, count);
	}

	public Magic(Prefix prefix, int count)
	{
		super(prefix);
		this.count = count;
	}

	private static final Random RANDOM = new Random();

	protected String randomAscii(int count)
	{
		return randomAscii(count, 33, 126, false, false);
	}

	protected String randomAscii(int count, int start, int end, boolean letters, boolean numbers)
	{
		return randomAscii(count, start, end, letters, numbers, null, RANDOM);
	}

	protected String randomAscii(int count, int start, int end, boolean letters, boolean numbers, char[] chars)
	{
		return randomAscii(count, start, end, letters, numbers, chars, RANDOM);
	}

	protected String randomAscii(int count, int start, int end, boolean letters, boolean numbers, char[] chars, Random random)
	{
		if(count == 0)
			return "";
		else if(count < 0)
			throw new IllegalArgumentException("Requested randomAscii string length " + count + " is less than 0.");

		if((start == 0) && (end == 0))
		{
			end = 'z' + 1; start = ' ';
			if(!letters && !numbers)
			{
				start = 0;
				end = Integer.MAX_VALUE;
			}
		}

		StringBuffer buffer = new StringBuffer();
		int gap = end - start;

		while(count-- != 0)
		{
			char ch;
			if(chars == null)
				ch = (char) (random.nextInt(gap) + start);
			else
				ch = chars[random.nextInt(gap) + start];

			if((letters && numbers && Character.isLetterOrDigit(ch)) || (letters && Character.isLetter(ch)) || (numbers && Character.isDigit(ch)) || (!letters && !numbers))
				buffer.append(ch);
			else
				count++;
		}
		return buffer.toString();
	}

	private String legacyLine()
	{
		String stripPrefix = ChatColor.stripColor(getPrefix()!=null?getPrefix().toString():"");
		int max = CONSOLE_WIDTH - (getPrefix() != null ? stripPrefix.length() + 1 : 0);

		String line = "";
		for(int i = 0; i < max; i++)
			line += randomAscii(1);

		return line;
	}

	private String fontLine()
	{
		int prefixWidth = FontUtil.stringWidth(getPrefix() != null ? getPrefix().toString() : "");
		int spaceWidth = FontUtil.stringWidth(" ");

		int maxWidth = FontUtil.LINE_WIDTH - (prefixWidth == 0 ? 0 : prefixWidth + spaceWidth);

		String line = "";
		int width = 0;
		do
		{
			char c = randomAscii(1).charAt(0);
			int charWidth = FontUtil.stringWidth("" + c);

			if(width + charWidth <= maxWidth) {
				line += c;
				width += charWidth;
			}
		}
		while(width  <= maxWidth);

		return line;
	}

	@Override
	public String toLegacyText()
	{
		add(legacyLine(), ChatColor.MAGIC);

		return super.toLegacyText();
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
		Sentence sentence = new Sentence(fontLine());
		sentence.build(builder);

		return builder;
	}
}