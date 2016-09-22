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

import com.github.hexocraftapi.chat.MessageBuilder;
import com.github.hexocraftapi.message.Util.FontUtil;
import com.google.common.collect.Lists;
import org.bukkit.ChatColor;

import java.util.List;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
public class Line
{
	private Prefix prefix;
	private List<Sentence> sentences;


	public Line()
	{
		this.sentences = Lists.newArrayList();
	}

	public Line(final Prefix prefix) { this.prefix = prefix; this.sentences = Lists.newArrayList(); }

	public Line(final Prefix prefix, final Sentence sentences) { this.prefix = prefix; this.sentences = Lists.newArrayList(sentences); }

	public Line(final Prefix prefix, final List<Sentence> sentences) { this.prefix = prefix; this.sentences = sentences; }

	public Line(final Prefix prefix, final Sentence... sentences) { this.prefix = prefix; this.sentences = Lists.newArrayList(sentences); }

	public Line(final Sentence sentences) { this.sentences = Lists.newArrayList(sentences); }

	public Line(final List<Sentence> sentences) { this.sentences = sentences; }

	public Line(final Sentence...sentences) { this.sentences = Lists.newArrayList(sentences); }

	public Line(final Prefix prefix, final String sentence) { this.prefix = prefix; this.sentences = Lists.newArrayList(new Sentence(sentence)); }

	public Line(final Prefix prefix, final String... sentences)
	{
		this.prefix = prefix;
		this.sentences = Lists.newArrayList();
		for(String sentence : sentences)
			this.sentences.add(new Sentence(sentence));
	}

	public Line(final Prefix prefix, final String sentence, final ChatColor color) { this.prefix = prefix; this.sentences = Lists.newArrayList(new Sentence(sentence, color)); }

	public Line(final String sentence) { this.sentences = Lists.newArrayList(new Sentence(sentence)); }

	public Line(final String sentence, final ChatColor color) { this.sentences = Lists.newArrayList(new Sentence(sentence, color)); }

	public Line add(final Sentence sentence)
	{
		this.sentences.add(sentence);
		return this;
	}

	public Line add(final String sentence)
	{
		return add(new Sentence(sentence));
	}

	public Line add(final String sentence, ChatColor color)
	{
		return add(new Sentence(sentence, color));
	}

	public void setPrefix(final Prefix prefix) { this.prefix = prefix; }

	public Prefix getPrefix() { return this.prefix; }

	public List<Sentence> getSentences()
	{
		return this.sentences;
	}


	/**
	 * Converts the fontLine to a string that uses the old formatting codes
	 *
	 * @return the string in the old format
	 */
	public String toLegacyText()
	{
		String line = "";
		String prefix = "";
		String sentences = "";

		// Préfix
		if(this.prefix != null)
			prefix = this.prefix.toLegacyText();

		// Sentences
		for(Sentence sentence : this.sentences)
		{
			sentences += (sentences.isEmpty()==false) ? " " :"";
			sentences += sentence.toLegacyText();
		}

		// Line
		line = prefix + (prefix.length()==0?"":" ") + sentences;

		return line;
	}

	private int lineWidth(Line line)
	{
		String sl = (this.prefix != null) ? this.prefix.toLegacyText(): "";
		for(Sentence sentence : this.sentences)
		{
			if(sl.length() > 0) sl += " ";
			sl += sentence.toLegacyText();
		}

		return FontUtil.stringWidth(sl);
	}

	private int lineLength(Line line)
	{
		int spaceLength = 1;
		int prefixLength = (this.prefix != null) ? ChatColor.stripColor(this.prefix.toLegacyText()).length() + spaceLength : 0;
		int sentencesLength = 0;
		for(Sentence sentence : this.sentences)
		{
			if(sentencesLength > 0) sentencesLength += spaceLength;
			sentencesLength += ChatColor.stripColor(sentence.toLegacyText()).length();
		}

		return prefixLength + sentencesLength;
	}

	public MessageBuilder build(MessageBuilder builder)
	{
		/* ************************************************************************************************************
		 *		This part use character LENGTH
		 * ********************************************************************************************************* */
		/*
		// The line fit
		if(lineLength(this) <= Chat.NO_WRAP_CHAT_PAGE_WIDTH) return build(builder, this);

		// Oups the line is too big.

		int spaceLength = 1;
		int prefixLength = (this.prefix != null) ? ChatColor.stripColor(this.prefix.toLegacyText()).length() + spaceLength : 0;
		int maxLength = Chat.NO_WRAP_CHAT_PAGE_WIDTH - prefixLength;

		// Create a new line that feet the length
		Line line = null;
		Sentence sentence = null;
		int lineLength = 0;
		int sentenceLength = 0;
		String stripString; String rawString;
		for(int i = 0; i < this.sentences.size(); i++)
		{
			sentence = this.sentences.get(i);

			// Create the line
			if(line==null) line = new Line(this.prefix);

			// RawString of the sentence
			rawString = sentence.toLegacyText();
			stripString = ChatColor.stripColor(rawString);
			// Get the sentence width
			sentenceLength = stripString.length();

			// Add the sentence if it feet
			if(sentenceLength <= maxLength)
			{
				line.add(sentence);
				lineLength += sentenceLength;
			}
			else
			{
				// get the free space in the line
				int free = maxLength - lineLength;

				// Split the sentence
				String newSentence = Chat.wordWrap(rawString, free - 1)[0] + '\n';

				// The first part feet the line
				sentence.setSentence(newSentence);
				line.add(sentence);
				// So build it
				build(builder, line);

				// And start hover with the rest
				sentence.setSentence(rawString.substring(newSentence.length(), rawString.length()));
				line = null;
				lineLength = 0;
				i--;
			}
		}

		if(line !=null)
			build(builder, line);

		return builder;*/
		/* ********************************************************************************************************* */



		/* ************************************************************************************************************
		 *		This part use character WIDTH
		 * ********************************************************************************************************* */
		/*
		// The line fit
		if(lineWidth(this) <= LINE_WIDTH) return build(builder, this);

		// Oups the line is too big.

		int spaceWidth = FontUtil.stringWidth(" ");
		int prefixWidth = (this.prefix != null) ? FontUtil.stringWidth(this.prefix.toLegacyText()) + spaceWidth : 0;
		int maxWidth = LINE_WIDTH - prefixWidth;

		// Create a new line that feet the width
		Line line = null;
		Sentence sentence = null;
		int lineWidth = 0;
		int sentenceWidth = 0;
		String rawString;
		for(int i = 0; i < this.sentences.size(); i++)
		{
			sentence = this.sentences.get(i);

			// Create the line
			if(line==null) line = new Line(this.prefix);

			// RawString of the sentence
			rawString = sentence.toLegacyText();
			// Get the sentence width
			sentenceWidth = FontUtil.stringWidth(rawString);

			// Add the sentence if it feet
			if(sentenceWidth <= maxWidth)
			{
				line.add(sentence);
				lineWidth += sentenceWidth;
			}
			else
			{
				// get the free space in the line
				int free = maxWidth - lineWidth;

				// Split the sentence
				String newSentence = FontUtil.wordWrap(rawString, free - 20)[0].trim() + '\n';

				// The first part feet the line
				sentence.setSentence(newSentence);
				line.add(sentence);
				// So build it
				build(builder, line);

				// And start hover with the rest
				sentence.setSentence(rawString.substring(newSentence.length(), rawString.length()));
				line = null;
				lineWidth = 0;
				i--;
			}
		}

		if(line !=null)
			build(builder, line);

		return builder; */
		/* ********************************************************************************************************* */

		return build(builder, this);
	}

	public MessageBuilder build(MessageBuilder builder, Line line)
	{
		// Add prefix
		if(line.getPrefix() != null)
			line.getPrefix().build(builder);

		//
		if(line.getPrefix() != null && line.getSentences().size() > 0)
			builder.append(" ", MessageBuilder.FormatRetention.NONE);

		// Add sentences
		for(int i = 0; i < line.getSentences().size(); i++)
		{
			if(i>0 && i<=line.getSentences().size()-1)
				builder.append(" ");

			Sentence sentence = sentences.get(i);
			sentence.build(builder);
		}

		return builder;
	}
}
