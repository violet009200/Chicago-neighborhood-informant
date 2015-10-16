

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Database {

	private int size;
	private ArrayList<HouseInfo> houses;
	public Database(String s){
		houses = new ArrayList<HouseInfo>();
		setupHouse(s);
		size = houses.size();
	}
	
	
	public int getSize(){
		return size;
	}
	public ArrayList<HouseInfo> getHouses(){
		return houses;
	}
	private void setupHouse(String s){
		BufferedReader br = null;
		String line = "";
		String split = ",";
		try{
			br = new BufferedReader(new FileReader(s));
			line = br.readLine();
			int index = 0;
			while((line = br.readLine())!=null){
				int i = 0;
				HouseInfo house = new HouseInfo();
				String info[] = line.split(split);
				if(info[0].charAt(0)!='('){
					house.setAreaName(info[i++]);
					if(info[i]!=null&&!info[i].equals(""))
						house.setAreaNumber(Integer.parseInt(info[i++]));
					else{
						i++;
					}
					house.setpType(info[i++]);
					String tempS = info[i++];
					if(tempS.charAt(0) == '"'){
						String ss = info[i++];
						while(ss.charAt(ss.length()-1)!='"'){
							tempS+=","+ss;
							ss = info[i++];
						}
						tempS += ","+ss;
					}
					
					house.setpName(tempS);
					house.setAddress(info[i++]);
					house.setZipCode(info[i++]);
					house.setPhoneNum(info[i++]);
					tempS = info[i++];
					if(tempS.charAt(0) == '"'){
						String ss = info[i++];
						while(ss.charAt(ss.length()-1)!='"'){
							tempS+=","+ss;
							ss = info[i++];
						}
						tempS += ","+ss;
					}
					house.setCompany(tempS);
					house.setUnit(Integer.parseInt(info[i]));
					if(info.length > i+1){
						i++;
						house.setxCoord(info[i++]);
					}
					if(info.length > i+1){
						i++;
						house.setyCoord(info[i++]);
					}
					if(info.length > i+1){
						i++;
						house.setLatitude(info[i]);
					}
					if(info.length == i+1)
						house.setLocation(info[i]);
					houses.add(house);
					
					index++;
				}
				else{
					String ss = info[0];
					i=1;
					while(i < info.length){
						ss+=","+info[i];
						i++;
					}
					houses.get(index-1).addLocation(ss);

				}
				
			}
		}catch(FileNotFoundException ffe){
			ffe.printStackTrace();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			if(br!=null){
				try{
					br.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public static void main(String args[]){
		Database db = new Database("/Users/bora88_920/Documents/JAVA/CS440/1.csv");
	}
}
