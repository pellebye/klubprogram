package common;

/**
 * Following possibilities:
 *
 * @formatter:off <blockquote>
 *                <ul>
 *                <li>ERROR = An error exists.</li>
 *                <li>CANCEL = Used to distinguish between errors.</li>
 *                <li>NO</li>
 *                <li>UNDEFINED</li>
 *                <li></li>
 *                <li>NOERROR = No error exists.</li>
 *                <li>OK = Used to distinguish between no errors.</li>
 *                <li>YES</li>
 *                <li></li>
 *                <li>STARTUP</li>
 *                <li>NOSTARTUP</li>
 *                </ul>
 *                </blockquote> @formatter:on
 */
public enum Status {
	// ---------------------------------------------------------------------------------------------
	// NEGATVE
	// ---------------------------------------------------------------------------------------------
	ERROR, CANCEL, NO, FILEDOESNOTEXIST, UNDEFINED,

	// ---------------------------------------------------------------------------------------------
	// POSITIVE
	// ---------------------------------------------------------------------------------------------
	NOERROR, OK, YES, FILEEXISTS,

	// ---------------------------------------------------------------------------------------------
	// SPECIAL
	// ---------------------------------------------------------------------------------------------
	STARTUP, NOSTARTUP,
	// Signal return.
	RETURN,
}
