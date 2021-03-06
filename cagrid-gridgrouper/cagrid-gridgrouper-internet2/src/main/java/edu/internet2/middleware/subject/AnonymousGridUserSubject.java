package edu.internet2.middleware.subject;

import edu.internet2.middleware.subject.provider.SubjectTypeEnum;


/**
 * @author <A HREF="MAILTO:langella@bmi.osu.edu">Stephen Langella</A>
 * @author <A HREF="MAILTO:oster@bmi.osu.edu">Scott Oster</A>
 * @author <A HREF="MAILTO:hastings@bmi.osu.edu">Shannon Hastings</A>
 * @author <A HREF="MAILTO:ervin@bmi.osu.edu">David W. Ervin</A>
 * @version $Id: GridGrouperBaseTreeNode.java,v 1.1 2006/08/04 03:49:26 langella
 *          Exp $
 */
public class AnonymousGridUserSubject extends GridSubject {

	public static final String ANONYMOUS_GRID_USER_ID = "<anonymous>";


	public AnonymousGridUserSubject(Source source) {
		super(ANONYMOUS_GRID_USER_ID, SubjectTypeEnum.PERSON, source);
	}
}
