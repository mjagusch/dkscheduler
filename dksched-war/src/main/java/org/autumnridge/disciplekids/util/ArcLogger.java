package org.autumnridge.disciplekids.util;

import org.apache.log4j.Logger;

import com.google.common.annotations.VisibleForTesting;

public final class ArcLogger {
	
	/**TODO: you will need to get a logger name from middleware and put that in here, you'll also need ot update in log4j.xml */
	public static final Logger DEFAULT = Logger.getLogger("dksched");

	/**
	 * We can't instantiate this utility class
	 */
	@VisibleForTesting ArcLogger(){}
}
