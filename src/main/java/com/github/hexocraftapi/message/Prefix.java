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
import org.bukkit.ChatColor;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
public class Prefix
{
	protected String prefix			= "";
	protected String startSymbol 	= "";
	protected String endSymbol 		= "";
	protected ChatColor prefixColor = ChatColor.RESET;
	protected ChatColor startColor 	= ChatColor.RESET;
	protected ChatColor endColor 	= ChatColor.RESET;


	public Prefix(final String prefix)
	{
		this.prefix = prefix;
	}


	public String getPrefix() { return prefix; }
	public Prefix setPrefix(final String prefix) { this.prefix = prefix; return this; }

	public String getStartSymbol() { return startSymbol; }
	public Prefix setStartSymbol(final String startSymbol) { this.startSymbol = startSymbol; return this; }

	public String getEndSymbol() { return endSymbol; }
	public Prefix setEndSymbol(final String endSymbol) { this.endSymbol = endSymbol; return this; }

	public ChatColor getStartColor() { return startColor; }
	public Prefix setStartColor(final ChatColor startColor) { this.startColor = startColor; return this; }

	public ChatColor getEndColor() { return endColor; }
	public Prefix setEndColor(final ChatColor endColor)  { this.endColor = endColor; return this; }

	public ChatColor getPrefixColor() { return prefixColor; }
	public Prefix setPrefixColor(final ChatColor prefixColor) { this.prefixColor = prefixColor; return this; }


	public String toLegacyText()
	{
		MessageBuilder builder = new MessageBuilder();
		return BaseComponent.toLegacyText(build(builder).create());
	}

	public MessageBuilder build(MessageBuilder builder)
	{
		if(this.startSymbol!=null && this.startSymbol.length()>0)
			builder.append(this.startSymbol).color(this.startColor);

		if(this.prefix!=null && this.prefix.length()>0)
			builder.append(this.prefix).color(this.prefixColor);

		if(this.endSymbol!=null && this.endSymbol.length()>0)
			builder.append(this.endSymbol).color(this.endColor);

		builder.append("").color(ChatColor.RESET);

		return builder;
	}

}
