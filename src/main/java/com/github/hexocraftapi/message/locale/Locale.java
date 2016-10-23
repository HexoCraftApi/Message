package com.github.hexocraftapi.message.locale;

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

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
public class Locale
{
	public static boolean En_en;
	public static boolean FR_fr;

	public static String command_aliases;
	public static String command_command;
	public static String command_click_copy_command;
	public static String command_description;
	public static String command_error;
	public static String command_help;
	public static String command_no_permission;
	public static String command_not_enough_parameters;
	public static String command_page;
	public static String command_reload;
	public static String command_too_many_parameters;
	public static String command_use_help;

	public static String argument_argument;
	public static String argument_arguments;
	public static String argument_description;
	public static String argument_mandatory;

	public static String help_for_command;
	public static String help_page;
	public static String help_page_number;

	static { init(); }

	public static  void init()
	{
		if(java.util.Locale.getDefault().getLanguage()=="fr")
			initFR_fr();
		else if(java.util.Locale.getDefault().getLanguage()=="en")
			initEN_en();
		else
			initEN_en();
	}

	public static void initEN_en()
	{
		En_en = true;
		FR_fr = false;

		command_aliases = "Aliases";
		command_command = "Command";
		command_click_copy_command = "Click to copy the command";
		command_description = "Description";
		command_error = "There is an error in your command";
		command_help = "help";
		command_no_permission = "You do not have permission to perform that command !";
		command_not_enough_parameters = "Not enough parameters for the command. You should do use";
		command_page = "page";
		command_reload = "Reload the plugin";
		command_too_many_parameters = "Too many parameters for the command. You should do use";
		command_use_help = "Use help for more details";

		argument_argument = "Argument";
		argument_arguments = "Arguments";
		argument_description = "Description";
		argument_mandatory = "This argument is required";

		help_for_command = "Help for";
		help_page = "Page";
		help_page_number = "Page number";
	}

	public static void initFR_fr()
	{
		En_en = false;
		FR_fr = true;

		command_aliases = "Alias";
		command_command = "Commande";
		command_click_copy_command = "Click pour copier la commande";
		command_description = "Description";
		command_error = "Il y a une erreur dans la commande";
		command_help = "aide";
		command_no_permission = "Vous n'avez pas les droits suffisants pour executer cette commande !";
		command_not_enough_parameters = "Pas assez de paramètres pour la commande, Vous devez utiliser";
		command_page = "page";
		command_reload = "Recharge le plugin";
		command_too_many_parameters = "Trop de paramètres pour la commande, Vous devez utiliser";
		command_use_help = "Utilisez l'aide pour plus de détails";

		argument_argument = "Argument";
		argument_arguments = "Arguments";
		argument_description = "Description";
		argument_mandatory = "Cette argument est obligatoire";

		help_for_command = "Aide pour";
		help_page = "Page";
		help_page_number = "Numero de page";
	}
}
