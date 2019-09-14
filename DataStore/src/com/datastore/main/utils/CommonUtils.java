package com.datastore.main.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.management.ManagementFactory;
import java.util.Date;
import java.util.HashMap;

import org.json.simple.JSONObject;

import com.datastore.main.bean.Data;
/**
 * @author John Britto
 *
 */
public class CommonUtils {

	public static String getProcessName() {
		String processName = ManagementFactory.getRuntimeMXBean().getName();
		return processName;
	}
	
	public static boolean isKeyNameValid(String key){
		if(key.length()>Constants.KEY_MAX_LENGTH){
			return false;
		}
		return true;
	}
	
	public static boolean isKeyExists(String key, String filePath){
		boolean isKeyExists=false;
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;
		FileOutputStream fileOutputStream = null;
		ObjectOutputStream objectOutputStream = null;
		HashMap<String, Data> dataMap = new HashMap<String, Data>();
		try{
			File file = new File(filePath);
			//check if files exists
			if(file.exists()){
				fileInputStream = new FileInputStream(file);
				objectInputStream = new ObjectInputStream(fileInputStream);
				dataMap = (HashMap<String, Data>) objectInputStream.readObject();
				//check if key exists
				if(dataMap.containsKey(key)){
					isKeyExists = true;
				}
				
				fileInputStream.close();
				objectInputStream.close();
			}
			 
			//validate against time to live and destroy the object if time expired
			if(isKeyExists){
				Data data = dataMap.get(key);
				long currentDateTimeMillis = new Date().getTime();
				if( data.getTimeToLive()>0 && (currentDateTimeMillis-data.getCreationDateTimeMillis())>=(data.getTimeToLive()*Constants.MILLISECONDS) ){
					//the object is expired, So remove from datastore
					dataMap.remove(key);
					fileOutputStream = new FileOutputStream(file);
					objectOutputStream = new ObjectOutputStream(fileOutputStream);
					objectOutputStream.writeObject(dataMap);
					fileOutputStream.close();
					objectOutputStream.close();
					
					//Since object is removed the key is available for storage
					isKeyExists=false;
				}
			}
		}catch(Exception exception){
			exception.printStackTrace();
		}finally{
			if(fileInputStream!=null){
				try {
					fileInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(objectInputStream!=null){
				try {
					objectInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return isKeyExists;
	}
	
	public static boolean writeData(Data data, String filePath){
		FileOutputStream fileOutputStream = null;
		ObjectOutputStream objectOutputStream = null;
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;
		HashMap<String, Data> dataMap = null;
		try{
			File file = new File(filePath);
			if(file.exists()){
				//read the existing file data
				fileInputStream = new FileInputStream(file);
				objectInputStream = new ObjectInputStream(fileInputStream);
				dataMap = (HashMap<String, Data>) objectInputStream.readObject();
				
				fileInputStream.close();
				objectInputStream.close();
				
				//add the new element
				dataMap.put(data.getKey(), data);
				
				//write the data to file
				fileOutputStream = new FileOutputStream(file);
				objectOutputStream = new ObjectOutputStream(fileOutputStream);
				objectOutputStream.writeObject(dataMap);
				fileOutputStream.close();
				objectOutputStream.close();
				
				return true;
			}else{
				dataMap = new HashMap<String, Data>();
				dataMap.put(data.getKey(), data);
				
				//write the data to file
				fileOutputStream = new FileOutputStream(file);
				objectOutputStream = new ObjectOutputStream(fileOutputStream);
				objectOutputStream.writeObject(dataMap);
				fileOutputStream.close();
				objectOutputStream.close();
				
				return true;
			}
		}catch(Exception exception){
			return false;
		}finally{
			if(fileInputStream!=null){
				try {
					fileInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(objectInputStream!=null){
				try {
					objectInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(fileOutputStream!=null){
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(objectOutputStream!=null){
				try {
					objectOutputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static Data readData(String key, String filePath){
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;
		HashMap<String, Data> dataMap = null;
		try{
			File file = new File(filePath);
			if(file.exists()){
				//read the existing file data
				fileInputStream = new FileInputStream(file);
				objectInputStream = new ObjectInputStream(fileInputStream);
				dataMap = (HashMap<String, Data>) objectInputStream.readObject();
				
				fileInputStream.close();
				objectInputStream.close();
				return dataMap.get(key);
			}else{
				return null;
			}
		}catch(Exception exception){
			exception.printStackTrace();
			return null;
		}finally{
			if(fileInputStream!=null){
				try {
					fileInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(objectInputStream!=null){
				try {
					objectInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static boolean deleteData(String key, String filePath){

		FileOutputStream fileOutputStream = null;
		ObjectOutputStream objectOutputStream = null;
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;
		HashMap<String, Data> dataMap = null;
		try{
			File file = new File(filePath);
			if(file.exists()){
				//read the existing file data
				fileInputStream = new FileInputStream(file);
				objectInputStream = new ObjectInputStream(fileInputStream);
				dataMap = (HashMap<String, Data>) objectInputStream.readObject();
				
				fileInputStream.close();
				objectInputStream.close();
				
				//add the new element
				dataMap.remove(key);
				
				//write the data to file
				fileOutputStream = new FileOutputStream(file);
				objectOutputStream = new ObjectOutputStream(fileOutputStream);
				objectOutputStream.writeObject(dataMap);
				fileOutputStream.close();
				objectOutputStream.close();
				
				return true;
			}				
			return false;
		}catch(Exception exception){
			return false;
		}finally{
			if(fileInputStream!=null){
				try {
					fileInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(objectInputStream!=null){
				try {
					objectInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(fileOutputStream!=null){
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(objectOutputStream!=null){
				try {
					objectOutputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	
	}
}
