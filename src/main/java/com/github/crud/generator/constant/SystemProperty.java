package com.github.crud.generator.constant;

import java.nio.file.FileSystems;

public class SystemProperty {

	public final static String BREAK_LINE = System.getProperty("line.separator");

	public final static String SEPATATOR = FileSystems.getDefault().getSeparator();
}
