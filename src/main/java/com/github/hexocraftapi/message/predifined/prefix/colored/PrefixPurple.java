package com.github.hexocraftapi.message.predifined.prefix.colored;

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

import com.github.hexocraftapi.message.Prefix;
import org.bukkit.ChatColor;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
public class PrefixPurple extends Prefix
{
	public PrefixPurple()
	{
		this("");
	}

	public PrefixPurple(String prefix)
	{
		super(prefix);
		this.startSymbol	= "[";
		this.endSymbol 		= "]";
		this.startColor 	= ChatColor.DARK_PURPLE;
		this.endColor 		= ChatColor.DARK_PURPLE;
		this.prefixColor 	= ChatColor.LIGHT_PURPLE;
	}
}
