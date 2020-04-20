package com.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.thymeleaf.TemplateEngine;

//import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class LegiDoxServiceImpl {
	
	final TemplateEngine templateEngine = new TemplateEngine();

	public Map<String, String> getMap() {
		
		Map<String, String> map = new HashMap<>();
		try {


			MongoClient mongoClient = MongoClients.create(
							"mongodb+srv://user_dummy:dummy@ketancluster-rv7sp.gcp.mongodb.net/test?retryWrites=true&w=majority");
			MongoDatabase database = mongoClient.getDatabase("legidox_db");

			
			
			//MongoClient mongo = new MongoClient("localhost", 27017);
			//MongoDatabase dbs = mongo.getDatabase("legidox_db");
			MongoCollection<Document> contentDocuments = database.getCollection("Content");
			MongoCollection<Document> factsDocuments = database.getCollection("Facts");

			FindIterable<Document> iterDoc = contentDocuments.find();
			MongoCursor<Document> it = iterDoc.iterator();
			
			while (it.hasNext()) {

				Document document = it.next();

				List<String> facts = (List<String>) document.get("Facts");
				
				String contentText = (String) document.get("ContentText");

				System.out.println(contentText);
				
				FindIterable<Document> iterDocFcats = factsDocuments.find();
				MongoCursor<Document> factDocs = iterDocFcats.iterator();
				
				
				while (factDocs.hasNext()) {
					Document factDoc = factDocs.next();
					if(facts.contains(factDoc.get("_id").toString())) {
						String sudoName = (String) factDoc.get("SudoName");
						String factName = (String) factDoc.get("FactName");
						String type = (String) factDoc.get("Type");
						System.out.println(sudoName);
						map.put(factName, type);
					}
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
		
	}

}
