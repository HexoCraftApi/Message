package com.github.hexocraftapi.message.predifined.prefix;

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

import com.github.hexocraftapi.message.predifined.prefix.colored.PrefixAqua;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class PluginPrefix extends PrefixAqua
{
	public PluginPrefix(JavaPlugin plugin)
	{
		super(plugin.getDescription().getName() + " " + plugin.getDescription().getVersion() + ChatColor.RESET);
		this.startSymbol	= ChatColor.BOLD + "[";
		this.endSymbol 		= ChatColor.BOLD + "]";
	}
}
