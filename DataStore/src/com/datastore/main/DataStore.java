package com.datastore.main;

import java.util.Date;

import org.json.simple.JSONObject;

import com.datastore.main.bean.Data;
import com.datastore.main.utils.CommonUtils;
import com.datastore.main.utils.Constants;

/**
 * 
 */
/**
 * @author John Britto
 *
 */

/*
 * 
 * 
 */

public final class DataStore {
	
	private String dataStoreLoc="";
	private String dataStoreName="";
	
	public DataStore(){
		try{
			dataStoreLoc=Constants.defaultDataStoreLoc;
			dataStoreName="datastore-"+CommonUtils.getProcessName();
		}catch(Exception exception){
			
		}
	}
	
	public DataStore(String filePath){
		try{
			dataStoreLoc=filePath;
			dataStoreName="datastore-"+CommonUtils.getProcessName();
		}catch(Exception exception){
			
		}
		
	}
	
	//Operations
	public synchronized String create(String key, JSONObject value){
		try{
			return create(key, value, -1);
		}catch(Exception exception){
			return Constants.FAILURE_CREATE;
		}
	}
	public synchronized String create(String key, JSONObject value, int timeToLive){
		try{
			String filePath = dataStoreLoc+"/"+dataStoreName;
			//validate the key
			if( !CommonUtils.isKeyNameValid(key) ){
				return Constants.FAILURE_KEY_LENGTH_EXCEEDED;
			}
			if(CommonUtils.isKeyExists(key, filePath)){
				return Constants.FAILURE_KEY_ALREADY_AVAILABLE;
			}
			//success flow
			Data data = new Data();
			data.setKey(key);
			if(timeToLive>0){
				data.setTimeToLive(timeToLive);
			}else{
				data.setTimeToLive(-1);
			}
			data.setValue(value);
			data.setCreationDateTimeMillis(new Date().getTime());
			
			if(CommonUtils.writeData(data, filePath)){
				return Constants.SUCCESS_CREATE;
			}else{
				return Constants.FAILURE_CREATE;
			}
		}catch(Exception exception){
			return Constants.FAILURE_CREATE;
		}
	}
	
	public synchronized Object read(String key){
		try{
			String filePath = dataStoreLoc+"/"+dataStoreName;
			//validate the key
			if( !CommonUtils.isKeyNameValid(key) ){
				return Constants.FAILURE_KEY_LENGTH_EXCEEDED;
			}
			if(!CommonUtils.isKeyExists(key, filePath)){
				return Constants.FAILURE_KEY_NOT_AVAILABLE;
			}
			//success flow
			
			Data data = CommonUtils.readData(key, filePath);
			if(null!=data){				
				return data.getValue();
			}
			return Constants.FAILURE_READ;
		}catch(Exception exception){
			exception.printStackTrace();
			return Constants.FAILURE_READ;
		}
	}
	
	public synchronized Object delete(String key){
		try{
			String filePath = dataStoreLoc+"/"+dataStoreName;
			//validate the key
			if( !CommonUtils.isKeyNameValid(key) ){
				return Constants.FAILURE_KEY_LENGTH_EXCEEDED;
			}
			if(!CommonUtils.isKeyExists(key, filePath)){
				return Constants.FAILURE_KEY_NOT_AVAILABLE;
			}
			//success flow
			
			if(CommonUtils.deleteData(key, filePath)){
				return Constants.SUCCESS_DELETE;
			}
			return Constants.FAILURE_DELETE;
		}catch(Exception exception){
			exception.printStackTrace();
			return Constants.FAILURE_DELETE;
		}
	}
}
