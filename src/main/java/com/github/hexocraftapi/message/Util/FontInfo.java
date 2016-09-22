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

import java.util.HashMap;
import java.util.Map;

/**
 * @author <b>Hexosse</b> (<a href="https://github.com/hexosse">on GitHub</a>))
 */
public enum FontInfo
{

	DEFAULT(0, '\0', 4),

	SPACE(32, ' ', 4),
	EXCLAMATION_MARK(33, '!', 2),
	QUOTATION_MARK(34, '"', 5),
	HASH(35, '#', 6),
	DOLLAR_SIGN(36, '$', 6),
	PERCENT_SIGN(37, '%', 6),
	AMPERSAND(38, '&', 6),
	APOSTROPHE(39, '\'', 3),
	LEFT_PARENTHESIS(40, '(', 5),
	RIGHT_PARENTHESIS(41, ')', 5),
	ASTERISK(42, '*', 5),
	PLUS_SIGN(43, '+', 6),
	COMMA(44, ',', 2),
	MINUS(45, '-', 6),
	PERIOD(46, '.', 2),
	SLASH(47, '/', 6),
	DIGIT_0(48, '0', 6),
	DIGIT_1(49, '1', 6),
	DIGIT_2(50, '2', 6),
	DIGIT_3(51, '3', 6),
	DIGIT_4(52, '4', 6),
	DIGIT_5(53, '5', 6),
	DIGIT_6(54, '6', 6),
	DIGIT_7(55, '7', 6),
	DIGIT_8(56, '8', 6),
	DIGIT_9(57, '9', 6),
	COLON(58, ':', 2),
	SEMI_COLON(59, ';', 2),
	LESS_THAN_SIGN(60, '<', 5),
	EQUAL_SIGN(61, '=', 6),
	GREATER_THAN_SIGN(62, '>', 5),
	QUESTION_MARK(63, '?', 6),
	AT_SIGN(64, '@', 7),
	A(65, 'A', 6),
	B(66, 'B', 6),
	C(67, 'C', 6),
	D(68, 'D', 6),
	E(69, 'E', 6),
	F(70, 'F', 6),
	G(71, 'G', 6),
	H(72, 'H', 6),
	I(73, 'I', 4),
	J(74, 'J', 6),
	K(75, 'K', 6),
	L(76, 'L', 6),
	M(77, 'M', 6),
	N(78, 'N', 6),
	O(79, 'O', 6),
	P(80, 'P', 6),
	Q(81, 'Q', 6),
	R(82, 'R', 6),
	S(83, 'S', 6),
	T(84, 'T', 6),
	U(85, 'U', 6),
	V(86, 'V', 6),
	W(87, 'W', 6),
	X(88, 'X', 6),
	Y(89, 'Y', 6),
	Z(90, 'Z', 6),
	LEFT_SQUARE_BRACKET(91, '[', 4),
	BACKSLASH(92, '\\', 6),
	RIGHT_SQUARE_BRACKET(93, ']', 4),
	CIRCUMFLEX_ACCENT(94, '^', 6),
	UNDERSCORE(95, '_', 6),
	GRAVE_ACCENT(96, '`', 3),
	a(97, 'a', 6),
	b(98, 'b', 6),
	c(99, 'c', 6),
	d(100, 'd', 6),
	e(101, 'e', 6),
	f(102, 'f', 5),
	g(103, 'g', 6),
	h(104, 'h', 6),
	i(105, 'i', 2),
	j(106, 'j', 6),
	k(107, 'k', 5),
	l(108, 'l', 3),
	m(109, 'm', 6),
	n(110, 'n', 6),
	o(111, 'o', 6),
	p(112, 'p', 6),
	q(113, 'q', 6),
	r(114, 'r', 6),
	s(115, 's', 6),
	t(116, 't', 4),
	u(117, 'u', 6),
	v(118, 'v', 6),
	w(119, 'w', 6),
	x(120, 'x', 6),
	y(121, 'y', 6),
	z(122, 'z', 6),
	LEFT_CURLY_BRACE(123, '{', 5),
	VERTICAL_BAR(124, '|', 2),
	RIGHT_CURLY_BRACE(125, '}', 5),
	TILDE(126, '~', 7);

	private static final Map<Integer, FontInfo> ASCII_MAP = new HashMap<Integer, FontInfo>(values().length, 1.1f);
	private static final Map<Character, FontInfo> CHAR_MAP = new HashMap<Character, FontInfo>(values().length, 1.1f);

	static {
		for(FontInfo fontInfo : values()) {
			ASCII_MAP.put(fontInfo.asciiCode, fontInfo);
			CHAR_MAP.put(fontInfo.character, fontInfo);
		}
	}


	private int asciiCode;
	private char character;
	private int width;


	FontInfo(int asciiCode, char character, int width) {
		this.asciiCode = asciiCode;
		this.character = character;
		this.width = width;
	}


	public int getAsciiCode() { return this.asciiCode; }
	public char getCharacter() { return this.character; }
	public int getWidth() { return this.width; }


	public int getBoldWidth() {
		if (this == FontInfo.SPACE) return this.getWidth();
		return this.width + 1;
	}

	public int getWidth(boolean bold) {
		if (bold) return this.getBoldWidth();
		return this.getWidth();
	}

	public static FontInfo getFontInfo(int asciiCode) {
		return (ASCII_MAP.containsKey(asciiCode) ? ASCII_MAP.get(asciiCode) : DEFAULT);
	}

	public static FontInfo getFontInfo(char character) {
		return (CHAR_MAP.containsKey(character)==true ? CHAR_MAP.get(character) : DEFAULT);
	}
}
