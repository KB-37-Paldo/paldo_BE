package com.example.portfolioservice.service;

import com.example.portfolioservice.mapper.PortfolioMapper;
import com.example.portfolioservice.mapper.UserMapper;
import com.example.portfolioservice.model.PortfolioDto;
import com.example.portfolioservice.model.UserDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecommendationServiceImpl implements RecommendationService{
	
	@Autowired
	SqlSession sqlsession;
	
	@Override
	public PortfolioDto recommendByPortfolio(long user_id) throws IOException, ParseException{
		PortfolioDto portfolio = sqlsession.getMapper(PortfolioMapper.class).findByUserId(user_id);
//		UserDto user = sqlsession.getMapper(UserMapper.class).findByUserId(user_id);
		long income = portfolio.getTarget_price();
//		long age = user.getAge();
		long age = 30;
		long cardType = 0;
		String param = "["+income + ", " + age + ", " + cardType + "]";
		String jsonStringScoring = createConnection(param);
		JSONParser parser = new JSONParser();
		Object Scoring = parser.parse(jsonStringScoring);
		JSONObject jsonObj = (JSONObject) Scoring;
		JSONArray predictions = (JSONArray) jsonObj.get("predictions");
		JSONObject objValue = (JSONObject) predictions.get(0);
		JSONArray values = (JSONArray) objValue.get("values");
		JSONArray value = (JSONArray) values.get(0);
		long cluster_id = (long) value.get(0);
		PortfolioDto recommendedPortfolio = findByCluster(cluster_id);
		return recommendedPortfolio;
	}
	
	public PortfolioDto findByCluster(long cluster_id) {
//		return sqlsession.getMapper(PortfolioMapper.class).findByCluster(cluster_id);
		return null;
	}
	
	public String createConnection(String param) throws IOException {

		// NOTE: you must manually set API_KEY below using information retrieved from your IBM Cloud account.

		String API_KEY = "SuXtEiff0bWEKQNES-iyrKc497Uf4_T0iU2WQQsOwkUu";

		HttpURLConnection tokenConnection = null;
		HttpURLConnection scoringConnection = null;
		BufferedReader tokenBuffer = null;
		BufferedReader scoringBuffer = null;
		try {
			// Getting IAM token
			URL tokenUrl = new URL("https://iam.cloud.ibm.com/identity/token?grant_type=urn:ibm:params:oauth:grant-type:apikey&apikey=" + API_KEY);
			tokenConnection = (HttpURLConnection) tokenUrl.openConnection();
			tokenConnection.setDoInput(true);
			tokenConnection.setDoOutput(true);
			tokenConnection.setRequestMethod("POST");
			tokenConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			tokenConnection.setRequestProperty("Accept", "application/json");
			tokenBuffer = new BufferedReader(new InputStreamReader(tokenConnection.getInputStream()));
			StringBuffer jsonString = new StringBuffer();
			String line;
			while ((line = tokenBuffer.readLine()) != null) {
				jsonString.append(line);
			}
			// Scoring request
            URL scoringUrl = new URL("https://us-south.ml.cloud.ibm.com/ml/v4/deployments/de45f82c-e80b-4429-92b9-994e54f4c024/predictions?version=2021-10-21");
			String iam_token = "Bearer " + jsonString.toString().split(":")[1].split("\"")[1];
			scoringConnection = (HttpURLConnection) scoringUrl.openConnection();
			scoringConnection.setDoInput(true);
			scoringConnection.setDoOutput(true);
			scoringConnection.setRequestMethod("POST");
			scoringConnection.setRequestProperty("Accept", "application/json");
			scoringConnection.setRequestProperty("Authorization", iam_token);
			scoringConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			OutputStreamWriter writer = new OutputStreamWriter(scoringConnection.getOutputStream(), "UTF-8");

			// NOTE: manually define and pass the array(s) of values to be scored in the next line
			String payload = "{\"input_data\": [{\"fields\": [\"income\", \"age\", \"card type\"], \"values\": ["+param+"]}]}";
			writer.write(payload);
			writer.close();

			scoringBuffer = new BufferedReader(new InputStreamReader(scoringConnection.getInputStream()));
			StringBuffer jsonStringScoring = new StringBuffer();
			String lineScoring;
			while ((lineScoring = scoringBuffer.readLine()) != null) {
				jsonStringScoring.append(lineScoring);
			}
			System.out.println(jsonStringScoring);
			return jsonStringScoring.toString();
		} catch (IOException e) {
			System.out.println("The URL is not valid.");
			System.out.println(e.getMessage());
		}
		finally {
			if (tokenConnection != null) {
				tokenConnection.disconnect();
			}
			if (tokenBuffer != null) {
				tokenBuffer.close();
			}
			if (scoringConnection != null) {
				scoringConnection.disconnect();
			}
			if (scoringBuffer != null) {
				scoringBuffer.close();
			}
		}
		return null;
	}
}
