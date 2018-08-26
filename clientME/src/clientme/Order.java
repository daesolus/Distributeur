package clientME;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Order {

	ArrayList<Liquid> liquidList;
	String jsonOrder;
	String timeStamp_;

	// Initialization
	public Order(String jsonOrder) throws InterruptedException {
		this.jsonOrder = jsonOrder;
		liquidList = new ArrayList<Liquid>();
		JSONObject obj = new JSONObject(jsonOrder);
		JSONArray recette = obj.getJSONArray("recette");
		int taille = recette.length(); 

		System.out.println("L'objet recu contient " + Integer.toString(taille) + " ingredients!");

		jsonParsing(recette);

		System.out.println(jsonOrder);
		System.out.println(" ");

	}

	// Usage
	public void jsonParsing(JSONArray recette) throws InterruptedException {
		for (int i = 0; i < recette.length() - 1; i++) {
			if (recette.getJSONObject(i).getInt("pompe") == 32) {
				System.out.println("Requete de fermeture de l'application recue");
				Thread.sleep(1000);
				System.exit(1);
			}

			Liquid liq = new Liquid();
			liq.setName(recette.getJSONObject(i).getString("boisson"));
			liq.setQuantity(recette.getJSONObject(i).getDouble("qte"));
			liq.setPump(recette.getJSONObject(i).getInt("pompe"));

			liquidList.add(liq);
		}
		timeStamp_ = recette.getJSONObject(recette.length() - 1).getString("time");
	}

	// Getters
	final public ArrayList<Liquid> getLiquidList() {
		return liquidList;
	}

	public String getJSONOrder() {
		return jsonOrder;
	}
}
