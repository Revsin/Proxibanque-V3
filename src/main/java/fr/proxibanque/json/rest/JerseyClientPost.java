/**
 * @author SMALBRANCHE
 * Client WS REST Jersey POST
 */

package fr.proxibanque.json.rest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
//import com.sun.jersey.api.client.config.ClientConfig;
//import com.sun.jersey.api.client.config.DefaultClientConfig;
//import com.sun.jersey.api.json.JSONConfiguration;

public class JerseyClientPost {

	public JerseyClientPost() {
		super();
	}

	/**
	 * @author SMALBRANCHE Lance le client REST Post
	 * @param args[0]
	 *            Adresse IP
	 * @param args[1]
	 *            URL du service JSON
	 */
	public static void perform(String[] args, String requestEntity) {

		// public static void main(String[] args) {

		try {

			if (args != null) {
				System.out.println("Number of arguments: " + args.length);
			} else {
				System.out.println("Number of arguments: 0");
			}

			String ipAddress = "localhost";
			String port = "8080";
			String jsonWS = "/Jersey/rest/json/bankexec/post";

			if (args != null) {
				if (args.length == 2) {
					ipAddress = args[0];
					jsonWS = args[1];
				}
			}

			String url = "http://" + ipAddress + ":" + port + jsonWS;

			// System.out.println("Adresse IP: " + ipAddress);
			System.out.println("URL Post: " + url);

			// ClientConfig clientConfig = new DefaultClientConfig();
			// clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, true);

			Client client = Client.create();

			WebResource webResource = client.resource(url);

			// String input = "{\"nom\":\"Tyler\",\"prenom\":\"Steven\"}";
			System.out.println("Objet du client POST: " + requestEntity);

			// ClientResponse response =
			// webResource.type("text/plain").post(ClientResponse.class, input);
			ClientResponse response = webResource.type("application/json").post(ClientResponse.class, requestEntity);

			System.out.println("Réponse client POST reçue: " + response.getStatus());

			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			System.out.println("Output from Server .... \n");
			String output = response.getEntity(String.class);
			System.out.println(output);

		} catch (Exception e) {

			e.printStackTrace();

		}

		// }

	}
}
