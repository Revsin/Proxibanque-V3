/**
 * 
 */
package fr.proxibanque.metier.tests;

import org.junit.Assert;
import org.junit.Test;

import fr.proxibanque.metier.Client;

/**
 * @author SEBASTIENM
 *
 */
public class ClientTest {

	/**
	 * Test method for
	 * {@link fr.proxibanque.metier.gtm.proxibanquesi.metier.Client#Client()}.
	 * ClientID should be a unique positive integer and increment with each new
	 * client
	 */
	@Test
	public void testClient() {
		// fail("Not yet implemented");

		Client c1 = new Client(), c2 = new Client(), c3 = new Client();

		int result = -1;

		result = c1.getClientID();
		Assert.assertEquals("ERROR CLIENT ID", 0, result, 0);

		result = c2.getClientID();
		Assert.assertEquals("ERROR CLIENT ID", 1, result, 0);

		result = c3.getClientID();
		Assert.assertEquals("ERROR CLIENT ID", 2, result, 0);

	}

}
