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
import com.github.hexocraftapi.chat.component.BaseComponent;
import com.github.hexocraftapi.chat.event.ClickEvent;
import com.github.hexocraftapi.chat.event.HoverEvent;
import org.bukkit.ChatColor;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
public class Sentence
{
	private String sentence = "";
	private ChatColor color;
	private ClickEvent clickEvent;
	private HoverEvent hoverEvent;


	public Sentence(final String sentence)
	{
		this.sentence = sentence;
	}

	public Sentence(final String sentence, final ChatColor color)
	{
		this.sentence = sentence;
		this.color = color;
	}

	public Sentence(final Sentence sentence)
	{
		this.sentence = sentence.sentence;
		this.color = sentence.color == null ? null : sentence.color;
		this.clickEvent = sentence.clickEvent == null ? null : new ClickEvent(sentence.clickEvent);
		this.hoverEvent = sentence.hoverEvent == null ? null : new HoverEvent(sentence.hoverEvent);
	}


	public String getSentence() { return this.sentence; }
	public void setSentence(String sentence) { this.sentence = sentence; }

	public ChatColor getColor() { return this.color; }
	public Sentence color(final ChatColor color)
	{
		this.color = color;
		return this;
	}

	public ClickEvent getClickEvent() { return clickEvent; }
	public Sentence event(final ClickEvent clickEvent)
	{
		this.clickEvent = clickEvent;
		return this;
	}

	public HoverEvent getHoverEvent() { return hoverEvent; }
	public Sentence event(final HoverEvent hoverEvent)
	{
		this.hoverEvent = hoverEvent;
		return this;
	}


	public String toLegacyText()
	{
		MessageBuilder builder = new MessageBuilder();
		return BaseComponent.toLegacyText(build(builder).create());
	}

	public MessageBuilder build(MessageBuilder builder)
	{
		return builder.append(this.sentence).color(this.color).event(this.clickEvent).event(this.hoverEvent);
	}
}
