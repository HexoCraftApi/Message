package com.github.hexocraftapi.message.Util;

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

import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Class for font functions including font width specifications.
 */
public class FontUtil
{

    /**
     * Provides the char for a Section sign.
     */
	public static final char SECTION_SIGN = ChatColor.COLOR_CHAR;

	/**
	 * Line feed char.
	 */
	public static final char LINE_FEED = '\n';

	/**
	 * Max length allowed per line.
	 */
	public static final int LINE_WIDTH = 320;


    private FontUtil() {
    }


    /**
     * Get width of string in pixels.
     *
     * @param rawString String.
     * @return Length of string in pixels.
     */
    public static int stringWidth(String rawString)
    {
		if(rawString == null) return 0;

		int width = 0;
		char previous = 0;
		boolean bold = false;
		boolean strike = false;
		boolean reset = false;

    	for(char ch : rawString.toCharArray())
    	{
    		if(reset) strike = false;

			if(isColor(previous, ch)) 			bold = false;
			if(isBold(previous, ch)) 			bold = true;
			if(isStrikeThrough(previous, ch)) 	strike = true;
			if(isReset(previous, ch)) 			reset = true;

    		if(!isColor(previous, ch))			width += getWidth(ch, bold);

			if(reset) 							bold = false;

			previous = ch;
		}

		if(strike) width += 1;

        return width;
    }

	/**
	 * Find the last color format in a string.
	 *
	 * @param rawString String
	 * @return List of Strings
	 */
	public static String getLastColorFormat(String rawString)
	{
		if (rawString == null) return "";

		String colorFormat = "";
		int colorIndex = rawString.lastIndexOf(SECTION_SIGN);

		while(colorIndex>=0)
		{
			ChatColor color = getColor(rawString.charAt(colorIndex + 1));

			// Stop on reset format
			if(color.equals(ChatColor.RESET))
				break;

			// Update the color format
			colorFormat = color + colorFormat;

			// Stop when color found as format should be after color
			if(color.isColor())
				break;

			// Continue
			colorIndex = rawString.lastIndexOf(SECTION_SIGN, colorIndex-1);
		}

		return colorFormat;
	}


	/**
	 * Split in a List of Strings where none exceed the maximum fontLine length.
	 *
	 * @param rawString String
	 * @param width maximum width
	 * @return List of Strings
	 */
	public static String[] split(String rawString, int width)
	{
		// A null string is a single fontLine
		if (rawString == null)
			return new String[] {""};

		// A string shorter than the lineWidth is a single line
		if(stringWidth(rawString)<=width)
			return new String[] {rawString};

		// Split into lines
		List<String> lines = new LinkedList<String>();
		String line = ""; int lineWidth = 0; int chWidth = 0;
		char previous = 0; boolean bold = false; boolean strike = false; boolean reset = false;
		for(char ch : rawString.toCharArray())
		{
			if(reset) strike = false;

			if(isColor(previous, ch)) 			bold = false;
			if(isBold(previous, ch)) 			bold = true;
			if(isStrikeThrough(previous, ch)) 	strike = true;
			if(isReset(previous, ch)) 			reset = true;

			if(!isColor(previous, ch))			lineWidth += getWidth(ch, bold);

			if(reset) 							bold = false;

			// New fontLine
			if(ch == LINE_FEED)
			{
				lines.add(StringUtils.strip(line));
				line = getLastColorFormat(line);
				lineWidth = 0;
			}
			// Max width
			else if(lineWidth + (strike?1:0) < width)
				line += ch;
			// New fontLine beginning with the char
			else
			{
				lines.add(StringUtils.strip(line));
				line = getLastColorFormat(line) + ch;
				lineWidth = chWidth;
			}

			previous = ch;
		}

		// The last fontLine
		lines.add(line);

		return lines.toArray(new String[lines.size()]);
	}

	/**
	 * Get a List of Strings where none exceed the maximum fontLine width.
	 *
	 * @param rawString String
	 * @return List of Strings
	 */
	public static String[] wordWrap(String rawString)
	{
		return wordWrap(rawString, LINE_WIDTH);
	}

	/**
	 * Get a List of Strings where none exceed the width.
	 *
	 * @param rawString String
	 * @param width Maximum width
	 * @return List of Strings
	 */
	public static String[] wordWrap(String rawString, int width)
	{
		// A null string is a single fontLine
		if (rawString == null)
			return new String[] {""};

		// A string shorter than the lineWidth is a single fontLine
		if(stringWidth(rawString)<=width)
			return new String[] {rawString};

		// First we get all words in rawString
		List<String> words = new LinkedList<String>();
		for(String word : rawString.split(" "))
		{
			// special case: extremely long word
			if(stringWidth(word)>width)
				words.addAll(Arrays.asList(split(word, width)));
			else if(word.lastIndexOf(LINE_FEED)>0)
			{
				int index = word.lastIndexOf(LINE_FEED);
				words.add(word.substring(0,index+1));
				words.add(word.substring(index+1,word.length()));
			}
			else
				words.add(word);
		}

		// A fontLine must not exceed the maximum width
		List<String> lines = new ArrayList<String>();
		String line = ""; int wordWidth = 0; int lineWidth = 0;
		int spaceWidth = FontInfo.SPACE.getWidth();
		for(String word : words)
		{
			wordWidth = stringWidth(word);

			// New fontLine forced
			if(word.charAt(word.length()-1) == LINE_FEED)
			{
				if(lineWidth + (lineWidth == 0 ? 0 : spaceWidth) + wordWidth <= width)
				{
					line += (lineWidth==0?"":" ");
					line += word.substring(0, word.length()-1);

					lines.add(line);
					line = getLastColorFormat(line);
					lineWidth = 0;
				}
				else
				{
					lines.add(line);
					line = getLastColorFormat(line);
					line = word.substring(0, word.length()-1);
					lineWidth = 0;
				}
			}
			else
			{
				if(lineWidth + (lineWidth == 0 ? 0 : spaceWidth) + wordWidth <= width)
				{
					line += (lineWidth == 0 ? "" : " ");
					line += word;
					lineWidth += (lineWidth == 0 ? 0 : spaceWidth) + wordWidth;
				}
				else
				{
					lines.add(line);
					line = getLastColorFormat(line);
					line = word;
					lineWidth = 0;
				}
			}
		}

		// The last fontLine
		lines.add(line);

		return lines.toArray(new String[lines.size()]);
	}

	/**
	 * Get a List of centered strings.
	 *
	 * @param rawString String
	 * @return a centered strings
	 */
	public static String center(String rawString)
	{
		return center(rawString, LINE_WIDTH);
	}

	/**
	 * Get a List of centered strings.
	 *
	 * @param rawString String
	 * @param width Maximum width
	 * @return a centered strings
	 */
	public static String center(String rawString, int width)
	{
		// A null string is a single fontLine
		if (rawString == null) return "";

		// A string longer than the lineWidth then it cannot be centered
		int strWidth = stringWidth(rawString);
		if(stringWidth(rawString)>=width) return rawString;

		// Free space before
		int free = width - strWidth;
		int before = free / 2;

		int spaceWidth = stringWidth(" ");

		String spaces = "";
		for(int i = 0; (i <= before && i + spaceWidth <= before); i += spaceWidth)
			spaces += " ";

		return spaces + rawString;
	}

	private static boolean isColor(char c1, char c2)
	{
		if(c1 != SECTION_SIGN && c2 != SECTION_SIGN)
			return false;
		return true;
	}

	private static boolean isBold(char c1, char c2)
	{
		if(c1 == SECTION_SIGN  && ChatColor.getByChar(Character.toLowerCase(c2)).equals(ChatColor.BOLD))
			return true;
		return false;
	}

	private static boolean isStrikeThrough(char c1, char c2)
	{
		if(c1 == SECTION_SIGN  && ChatColor.getByChar(Character.toLowerCase(c2)).equals(ChatColor.STRIKETHROUGH))
			return true;
		return false;
	}

	private static boolean isReset(char c1, char c2)
	{
		if(c1 == SECTION_SIGN  && ChatColor.getByChar(Character.toLowerCase(c2)).equals(ChatColor.RESET))
			return true;
		return false;
	}

	private static int getWidth(char ch, boolean bold) {
		return FontInfo.getFontInfo(ch).getWidth(bold);
	}

	private static ChatColor getColor(char ch) {
		return ChatColor.getByChar(Character.toLowerCase(ch));
	}

}

