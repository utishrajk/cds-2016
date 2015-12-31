package com.feisystems.bham.weka;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weka.classifiers.functions.Logistic;
import weka.core.Instance;
import weka.core.Instances;
import java.text.DecimalFormat;

import com.feisystems.bham.domain.decisionfacts.ProbabilityDistribution;

public class LogisticRegression {
	
    	private static final Logger logger = LoggerFactory.getLogger(LogisticRegression.class);

		Instances trainData;
		ProbabilityDistribution pd;

		private List <ProbabilityDistribution> distributionList = new ArrayList<>();
		private String suggestedProcedure;
		
    	public String getSuggestedProcedure() {
			return suggestedProcedure;
		}

		public void setSuggestedProcedure(String suggestedProcedure) {
			this.suggestedProcedure = suggestedProcedure;
		}

		public List<ProbabilityDistribution> getDistributionList() {
			return distributionList;
		}

		public void setDistributionList(List<ProbabilityDistribution> distributionList) {
			this.distributionList = distributionList;
		}


		public LogisticRegression(String age, String administrativeGenderCode, String zipCode, String race, String sCode, String pCode  ) {
			try {
				loadTrainData();
			} catch (IOException e) {
				e.printStackTrace();
			}
			addInstance(age, administrativeGenderCode, zipCode, race, sCode, pCode);
			this.setDistributionList(buildAndClassify());  
		}


		public void loadTrainData() throws IOException {
		    //load train data
			InputStream inputStream = LogisticRegression.class.getResourceAsStream("/weka/dataset/opioidTrain.arff");
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		    Reader reader = new BufferedReader(inputStreamReader); 

		    try {
				trainData = new Instances(reader);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
		         if(inputStream != null)
		        	 inputStream.close();
		         if(inputStreamReader != null)
		        	 inputStreamReader.close();
		         if(reader != null)
			            reader.close();
		      } 
		    trainData.setClassIndex(trainData.numAttributes() - 1);
		}
		

		public void addInstance(String age, String administrativeGenderCode, String zipCode, String race, String sCode, String pCode) {
		    
		    Instance inst = new Instance(8);
		    inst.setValue(trainData.attribute(0), age);
		    inst.setValue(trainData.attribute(1), administrativeGenderCode);
		    inst.setValue(trainData.attribute(2), zipCode);
		    inst.setValue(trainData.attribute(3), race);
	        inst.setValue(trainData.attribute(4), sCode);
		    inst.setValue(trainData.attribute(5), pCode);
		    inst.setValue(trainData.attribute(6), "YES");
		    
		    logger.info("Adding Instance from Client Request");
		    
		    // add
		    trainData.add(inst);

		}

		/*
		 * Build and Classify 	
		 */
		public List<ProbabilityDistribution> buildAndClassify() {
		    Logistic model = new Logistic();
		    
		    
		    try {
			    	model.buildClassifier(trainData);
			    
					Instances labeled = new Instances(trainData);
			    	double clsLabel = model.classifyInstance(trainData.lastInstance());
			    	labeled.lastInstance().setClassValue(clsLabel);
			    	
			    	double[] distribution = model.distributionForInstance(trainData.lastInstance());
			    	DecimalFormat df = new DecimalFormat("###.##");
			    	String serviceCode;
			    	//output predictions
			        for(int i=0; i<distribution.length; i=i+1)
			        {
			        	pd = new ProbabilityDistribution();
			        	serviceCode = trainData.lastInstance().classAttribute().value(i);
			        	pd.setService(serviceCode);
			        	pd.setProbability(df.format(distribution[i]));
			        	if(serviceCode != null) {
			        		pd.setDescription(ServiceCodesEnum.valueOf(serviceCode).getServiceDescription());
			        	}
			        	distributionList.add(pd);
			        }

			        this.setSuggestedProcedure(labeled.lastInstance().stringValue(7));
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			    return distributionList;
			}		
}
