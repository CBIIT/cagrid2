package org.cagrid.dorian.idp;

import junit.framework.TestCase;

import org.cagrid.dorian.common.Lifetime;
import org.cagrid.dorian.model.exceptions.DorianInternalException;
import org.cagrid.dorian.model.idp.PasswordSecurity;
import org.cagrid.dorian.model.idp.PasswordStatus;
import org.cagrid.dorian.service.idp.PasswordSecurityManager;
import org.cagrid.dorian.service.idp.PasswordSecurityPolicy;
import org.cagrid.gaards.dorian.test.Utils;
import org.cagrid.tools.database.Database;

public class TestPasswordSecurityManager extends TestCase {
	private Database db;

	public void testGetAndDeleteEntry() {
		PasswordSecurityManager psm = null;
		try {
			psm = new PasswordSecurityManager(db, getPolicy());
			String uid = "user";
			assertEquals(false, psm.entryExists(uid));
			PasswordSecurity entry = psm.getEntry(uid);
			assertEquals(true, psm.entryExists(uid));
			validateEntry(entry, 0, 0, false, PasswordStatus.VALID);
			assertEquals(PasswordStatus.VALID, psm.getPasswordStatus(uid));
			psm.deleteEntry(uid);
			assertEquals(false, psm.entryExists(uid));
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		} finally {
			try {
				psm.clearDatabase();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void testSuspendedPassword() {
		PasswordSecurityManager psm = null;
		try {
			PasswordSecurityPolicy policy = getPolicy();
			psm = new PasswordSecurityManager(db, policy);
			for (int j = 0; j < 2; j++) {
				String uid = "user" + j;
				assertEquals(false, psm.entryExists(uid));
				validateEntry(psm.getEntry(uid), 0, 0, false,
						PasswordStatus.VALID);
				assertEquals(true, psm.entryExists(uid));
				assertEquals(PasswordStatus.VALID, psm.getPasswordStatus(uid));

				int localCount = 0;
				boolean expiredOnce = false;
				for (int i = 1; i <= (policy.getTotalInvalidLogins() + 1); i++) {
					psm.reportInvalidLoginAttempt(uid);
					localCount = localCount + 1;
					if (i >= policy.getTotalInvalidLogins()) {
						if (localCount == policy.getConsecutiveInvalidLogins()) {
							localCount = 0;
						}
						validateEntry(psm.getEntry(uid), localCount, i,
								expiredOnce, PasswordStatus.LOCKED_UNTIL_CHANGED);
						assertEquals(PasswordStatus.LOCKED_UNTIL_CHANGED, psm
								.getPasswordStatus(uid));
					} else if (localCount != policy
							.getConsecutiveInvalidLogins()) {
						validateEntry(psm.getEntry(uid), localCount, i,
								expiredOnce, PasswordStatus.VALID);
						assertEquals(PasswordStatus.VALID, psm.getPasswordStatus(uid));
					} else {
						localCount = 0;
						expiredOnce = true;
						validateEntry(psm.getEntry(uid), localCount, i,
								expiredOnce, PasswordStatus.LOCKED);
						assertEquals(PasswordStatus.LOCKED, psm
								.getPasswordStatus(uid));

						psm.reportSuccessfulLoginAttempt(uid);
						validateEntry(psm.getEntry(uid), localCount, i,
								expiredOnce, PasswordStatus.LOCKED);
						assertEquals(PasswordStatus.LOCKED, psm
								.getPasswordStatus(uid));

						Thread
								.sleep((policy.getLockout().getSeconds() * 1000) + 100);
						assertEquals(PasswordStatus.VALID, psm
								.getPasswordStatus(uid));
						validateEntry(psm.getEntry(uid), localCount, i,
								expiredOnce, PasswordStatus.VALID);
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		} finally {
			try {
				psm.clearDatabase();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void testResetPassword() {
		PasswordSecurityManager psm = null;
		try {
			PasswordSecurityPolicy policy = getPolicy();
			psm = new PasswordSecurityManager(db, policy);
			String uid = "user";
			assertEquals(false, psm.entryExists(uid));
			validateEntry(psm.getEntry(uid), 0, 0, false, PasswordStatus.VALID);
			assertEquals(PasswordStatus.VALID, psm.getPasswordStatus(uid));

			psm.reportInvalidLoginAttempt(uid);
			validateEntry(psm.getEntry(uid), 1, 1, false, PasswordStatus.VALID);
			assertEquals(PasswordStatus.VALID, psm.getPasswordStatus(uid));

			psm.reportInvalidLoginAttempt(uid);
			validateEntry(psm.getEntry(uid), 2, 2, false, PasswordStatus.VALID);
			assertEquals(PasswordStatus.VALID, psm.getPasswordStatus(uid));

			psm.reportSuccessfulLoginAttempt(uid);
			validateEntry(psm.getEntry(uid), 0, 2, false, PasswordStatus.VALID);
			assertEquals(PasswordStatus.VALID, psm.getPasswordStatus(uid));

		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		} finally {
			try {
				psm.clearDatabase();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	protected void validateEntry(PasswordSecurity entry, long count, long totalCount, boolean expired, PasswordStatus status) {
		assertEquals(count, entry.getConsecutiveInvalidLogins());
		assertEquals(totalCount, entry.getTotalInvalidLogins());
		assertEquals(status, entry.getPasswordStatus());
		if (expired) {
			if (entry.getLockoutExpiration() <= 0) {
				fail("Password should be locked.");
			}
		} else {
			assertEquals(0, entry.getLockoutExpiration());
		}
	}

	private PasswordSecurityPolicy getPolicy() throws DorianInternalException {
		Lifetime time = new Lifetime();
		time.setHours(0);
		time.setMinutes(0);
		time.setSeconds(3);
		PasswordSecurityPolicy policy = new PasswordSecurityPolicy();
		policy.setLockout(time);
		policy.setConsecutiveInvalidLogins(3);
		policy.setTotalInvalidLogins(8);
		return policy;
	}

	protected void setUp() throws Exception {
		super.setUp();
		try {

			db = Utils.getDB();
			assertEquals(0, db.getUsedConnectionCount());
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	protected void tearDown() throws Exception {
		super.setUp();
		try {
			assertEquals(0, db.getUsedConnectionCount());
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}
