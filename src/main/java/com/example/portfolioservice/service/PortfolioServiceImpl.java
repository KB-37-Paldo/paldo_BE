package com.example.portfolioservice.service;


import com.example.portfolioservice.form.PortfolioCreateForm;
import com.example.portfolioservice.form.PortfolioDetailForm;
import com.example.portfolioservice.form.PortfolioUpdateForm;
import com.example.portfolioservice.mapper.PortfolioMapper;
import com.example.portfolioservice.model.*;
import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    @Autowired
    SqlSession sqlsession;

    @Override
    public long deleteByUserId(long userId) {
        return sqlsession.getMapper(PortfolioMapper.class).deleteByUserId(userId);
    }

    @Override
    public PortfolioResponseDto findByUserId(long userId) {
        Optional<PortfolioDto> portfolio = sqlsession.getMapper(PortfolioMapper.class).findByUserId(userId);
        System.out.println(portfolio.get().getDescription());
        
        PortfolioResponseDto portfolioResponseDto = new PortfolioResponseDto(portfolio.get());
        return portfolioResponseDto;
    }

    @Override
    public long createPortfolio(PortfolioCreateForm portfolioCreateForm) throws IOException, ParseException{
    	
        long targetPrice = portfolioCreateForm.getTargetPrice();
        Date targetDate = portfolioCreateForm.getTargetPeriod();
        Date toDate = new Date();
        long yearGap = (targetDate.getTime() - toDate.getTime()) / 1000 / 60 / 60 / 24 / 365;
        String strYearGap = String.valueOf(yearGap);
        String strTargetPrice = String.valueOf(targetPrice);
        String param = "[[25, "+ strTargetPrice +", "+ strYearGap +"]]";
        
        String jsonStringScoring = createConnection(param);
        JSONParser parser = new JSONParser();
		Object Scoring = parser.parse(jsonStringScoring);
		JSONObject jsonObj = (JSONObject) Scoring;
		JSONArray predictions = (JSONArray) jsonObj.get("predictions");
		JSONObject objValue = (JSONObject) predictions.get(0);
		JSONArray values = (JSONArray) objValue.get("values");
		JSONArray value = (JSONArray) values.get(0);
		long clusterId = (long) value.get(0);
		System.out.println(clusterId);
		
		String strClusterAnswer = "{\"0\" : {\"cash\": 64, \"stock\": 13, \"gold\": 3, \"bond\": 2, \"fund\": 15, \"realEstate\": 3}, \"1\": {\"cash\": 27, \"stock\": 24, \"gold\": 7, \"bond\": 14, \"fund\": 16, \"realEstate\": 13}, \"2\": {\"cash\": 6, \"stock\": 30, \"gold\": 10, \"bond\": 17, \"fund\": 20, \"realEstate\": 17}, \"3\": {\"cash\": 36, \"stock\": 21, \"gold\": 3, \"bond\": 11, \"fund\": 19, \"realEstate\": 10}, \"4\": {\"cash\": 6, \"stock\": 29, \"gold\": 10, \"bond\": 17, \"fund\": 20, \"realEstate\": 17}, \"5\": {\"cash\": 53, \"stock\": 17, \"gold\": 2, \"bond\": 8, \"fund\": 16, \"realEstate\": 4}}";
		Object clusterAnswer = parser.parse(strClusterAnswer);
		JSONObject jsonCluster = (JSONObject) clusterAnswer;
		JSONObject predictionAnswers = (JSONObject) jsonCluster.get(String.valueOf(clusterId));
		System.out.println(predictionAnswers);
		if(clusterId == 0 || clusterId == 5) {
			System.out.println("3");
			portfolioCreateForm.setDescription("안정적인 목표를 선택하셨습니다. 위험성이 적은 포트폴리오에 투자성향을 반영하여 추천드렸습니다.");
		} else if(clusterId == 1 || clusterId == 3) {
			System.out.println("2");
			portfolioCreateForm.setDescription("일반적인 목표를 선택하셨습니다. 표준적인 포트폴리오에 투자성향을 반영하여 추천드렸습니다.");
		} else {
			System.out.println("1");
			portfolioCreateForm.setDescription("도전적인 목표를 선택하셨습니다. 높은 수익률의 포트폴리오에 투자성향을 반영하여 추천드렸습니다.");
		}
		sqlsession.getMapper(PortfolioMapper.class).createPortfolio(portfolioCreateForm);
		
		long cash = (long) predictionAnswers.get("cash");
		long stock = (long) predictionAnswers.get("stock");
		long gold = (long) predictionAnswers.get("gold");
		long bond = (long) predictionAnswers.get("bond"); 
		long fund = (long) predictionAnswers.get("fund");
		long realEstate= (long) predictionAnswers.get("realEstate"); 


		int icash = Long.valueOf(cash).intValue();
		int istock = Long.valueOf(stock).intValue();
		int igold = Long.valueOf(gold).intValue();
		int ibond = Long.valueOf(bond).intValue();
		int ifund = Long.valueOf(fund).intValue();
		int irealEstate = Long.valueOf(realEstate).intValue();
		
        InvestType investType = InvestType.valueOfTitle(portfolioCreateForm.getInvestType());
        long id = portfolioCreateForm.getPortfolioId();
        sqlsession.getMapper(PortfolioMapper.class).createPortfolioDetail(new PortfolioDetailForm(id, AssetType.cash.getTypeId(), icash + investType.getCash()));
        sqlsession.getMapper(PortfolioMapper.class).createPortfolioDetail(new PortfolioDetailForm(id, AssetType.stock.getTypeId(), istock + investType.getStock()));
        sqlsession.getMapper(PortfolioMapper.class).createPortfolioDetail(new PortfolioDetailForm(id, AssetType.gold.getTypeId(), igold + investType.getGold()));
        sqlsession.getMapper(PortfolioMapper.class).createPortfolioDetail(new PortfolioDetailForm(id, AssetType.bond.getTypeId(), ibond + investType.getBond()));
        sqlsession.getMapper(PortfolioMapper.class).createPortfolioDetail(new PortfolioDetailForm(id, AssetType.fund.getTypeId(), ifund + investType.getFund()));
        sqlsession.getMapper(PortfolioMapper.class).createPortfolioDetail(new PortfolioDetailForm(id, AssetType.realEstate.getTypeId(), irealEstate + investType.getRealEstate()));

        return id;
    }

    @Override
    public long updatePortfolio(PortfolioUpdateForm portfolioUpdateForm) {
        return sqlsession.getMapper(PortfolioMapper.class).updatePortfolio(portfolioUpdateForm);
    }

    @Override
    public boolean exists(long userId) {
        return sqlsession.getMapper(PortfolioMapper.class).exists(userId);
    }

    @Override
    public List<PortfolioResponseDto> findAgePortfolio(int age) {
        return sqlsession.getMapper(PortfolioMapper.class).findByAge((int)(age/10)).stream()
                .map(PortfolioResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<PortfolioResponseDto> findAssetPortfolio(long asset) {
        return sqlsession.getMapper(PortfolioMapper.class).findByAsset(asset).stream()
                .map(PortfolioResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<PortfolioResponseDto> findInvestTypePortfolio(String investType) {
        return sqlsession.getMapper(PortfolioMapper.class).findByInvestType(investType).stream()
                .map(PortfolioResponseDto::new)
                .collect(Collectors.toList());
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
			URL scoringUrl = new URL("https://us-south.ml.cloud.ibm.com/ml/v4/deployments/caada731-c242-4ba8-83c4-7ea4afdb204e/predictions?version=2021-10-26");
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
			String payload = "{\"input_data\": [{\"fields\": [\"age\", \"targetPrice\", \"targetPeriod\"], \"values\": "+ param +"}]}";
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
