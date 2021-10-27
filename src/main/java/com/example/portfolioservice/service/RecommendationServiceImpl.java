package com.example.portfolioservice.service;

import com.example.portfolioservice.mapper.AssetMapper;
import com.example.portfolioservice.mapper.PortfolioMapper;
import com.example.portfolioservice.mapper.ProductMapper;
import com.example.portfolioservice.mapper.UserMapper;
import com.example.portfolioservice.model.PortfolioDto;
import com.example.portfolioservice.model.PortfolioResponseDto;
import com.example.portfolioservice.model.ProductDto;
import com.example.portfolioservice.model.ProductResponseDto;
import com.example.portfolioservice.model.UserDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
	
//	@Override
//	public ProductResponseDto recommendByPortfolio(long user_id) throws IOException, ParseException{
//		Optional<PortfolioDto> portfolio = sqlsession.getMapper(PortfolioMapper.class).findByUserId(user_id);
////		UserDto user = sqlsession.getMapper(UserMapper.class).findByUserId(user_id);
//		long income = portfolio.get().getTargetPrice();
////		long age = user.getAge();
//		long age = 30;
//		long cardType = 0;
//		String param = "["+income + ", " + age + ", " + cardType + "]";
//		String jsonStringScoring = createConnection(param);
//		JSONParser parser = new JSONParser();
//		Object Scoring = parser.parse(jsonStringScoring);
//		JSONObject jsonObj = (JSONObject) Scoring;
//		JSONArray predictions = (JSONArray) jsonObj.get("predictions");
//		JSONObject objValue = (JSONObject) predictions.get(0);
//		JSONArray values = (JSONArray) objValue.get("values");
//		JSONArray value = (JSONArray) values.get(0);
//		long cluster_id = (long) value.get(0);
//		PortfolioDto recommendedPortfolio = findByCluster(cluster_id);
//		return recommendedPortfolio;
//	}
	
	@Override
	public ProductResponseDto recommendByProduct(long userId) throws IOException, ParseException{
		ProductResponseDto productResponseDto = new ProductResponseDto();
		
		List<Map<String, BigDecimal>> aiData = sqlsession.getMapper(AssetMapper.class).getAiData(userId);
		StringBuffer strString = new StringBuffer();
		for (int i = 0;i < 5; ++i) {
			strString.append("[");
			for (int j = 1;j < 29; ++j) {
				BigDecimal p = aiData.get(i).get(Integer.toString(j));
				strString.append(p.toString());
				strString.append(", ");
			}
			strString.append(aiData.get(i).get("29"));
			if (i == 4) {
				strString.append("]");
			} else {
				strString.append("], ");
			}
		};
		
		String param = strString.toString();
		System.out.println(param);
		String jsonStringScoring = createConnection(param);
		JSONParser parser = new JSONParser();
		Object Scoring = parser.parse(jsonStringScoring);
		JSONObject jsonObj = (JSONObject) Scoring;
		JSONArray predictions = (JSONArray) jsonObj.get("predictions");
		JSONObject objValue = (JSONObject) predictions.get(0);
		JSONArray values = (JSONArray) objValue.get("values");
		JSONArray value = (JSONArray) values.get(0);
		long clusterId = (long) value.get(0);
		
		List<Integer> data1 = Arrays.asList(15, 17, 27, 12, 28);         
		List<Integer> data2 = Arrays.asList(17, 22, 7, 13, 19);      
		List<Integer> data3 = Arrays.asList(25, 13, 6, 26, 19);          
		List<Integer> data4 = Arrays.asList(25, 13, 6, 26, 19);          
		List<Integer> data5 = Arrays.asList(12, 26, 18, 2, 11);          
		List<Integer> data6 = Arrays.asList(1, 22, 13, 5, 26);           
		List<Integer> data7 = Arrays.asList(12, 13, 16, 3, 18);          
		List<Integer> data8 = Arrays.asList(9, 29, 17, 1, 13);           
		List<Integer> data9 = Arrays.asList(16, 6, 17, 29, 2);           
		List<Integer> data10 = Arrays.asList(2, 25, 7, 15, 22);          
		List<Integer> data11 = Arrays.asList(28, 1, 7, 6, 15);           
		List<Integer> data12 = Arrays.asList(2, 6, 1, 26, 18);           
		List<Integer> data13 = Arrays.asList(29, 13, 15, 7, 26);         
		List<Integer> data14 = Arrays.asList(17, 18, 2, 28, 22);         
		List<Integer> data15 = Arrays.asList(13, 2, 6, 15, 7);
		List<List<Integer>> datas = Arrays.asList(data1, data2, data3, data4, data5, data6, data7, data8, data9, data10, data11, data12, data13, data14, data15);
		
		int intClusterId = Long.valueOf(clusterId).intValue();
		List<Integer> groupIds = datas.get(intClusterId);
		
		System.out.println(groupIds);
		Map<String, Object> mapParam = new HashMap<String, Object>();
		List<Integer> assetTypeIds = Arrays.asList(1);
		List<Integer> assetTypeIds2 = Arrays.asList(3, 5);
		
		mapParam.put("assetTypeIds", assetTypeIds);
		mapParam.put("groupIds", groupIds);
		
		
		
			
		List<ProductDto> cash = sqlsession.getMapper(ProductMapper.class).getProduct(mapParam);
        
		mapParam.put("assetTypeIds", assetTypeIds2);
		List<ProductDto> invest = sqlsession.getMapper(ProductMapper.class).getProduct(mapParam);
		
		
		productResponseDto.setCash(cash);
		productResponseDto.setInvest(invest);
		return productResponseDto;
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
			URL scoringUrl = new URL("https://us-south.ml.cloud.ibm.com/ml/v4/deployments/aa510d35-c806-4f6e-8ec5-fd938ef880c6/predictions?version=2021-10-27");
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
			String payload = "{\"input_data\": [{\"fields\": [\"1\", \"2\", \"3\", \"4\", \"5\", \"6\", \"7\", \"8\", \"9\", \"10\", \"11\", \"12\", \"13\", \"14\", \"15\", \"16\", \"17\", \"18\", \"19\", \"20\", \"21\", \"22\", \"23\", \"24\", \"25\", \"26\", \"27\", \"28\", \"29\"], \"values\": ["+ param +"]}]}";
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
