package dataScienceAssignment2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Assignment2 {
	private static final String MISSING004 = "assignment2_dataset_missing004.csv";
	private static final String MISSING20 = "assignment2_dataset_missing20.csv";
	private static final String COMMA_DELIMITER = ",";
	static String[][] array004 = new String[3588][85];
	static String[][] arrayImp004 = new String[3588][85];
	static String[][] arrayImpC004 = new String[3588][85];
	static String[][] array20 = new String[3588][85];
	static String[][] arrayImp20 = new String[3588][85];
	static String[][] arrayImpC20 = new String[3588][85];
	static int count = 1;
	static int countC = 1;
	static int countF = 1;

	public static void initalize004(){
		String line = null;
		BufferedReader br = null;

		int counter = 0;
		try
		{
			br = new BufferedReader(new FileReader(MISSING004));

			while ((line = br.readLine()) != null) 
			{
				array004[counter] = line.split(COMMA_DELIMITER);
				//System.out.println(counter);
				if(array004.length < 0 ){
					System.out.println("sup");
				}
				counter++;
			}

			for(int y = 0; y<85;y++){
				for(int x =0; x<3588; x++){
					arrayImp004[x][y] = array004[x][y];
					arrayImpC004[x][y] = array004[x][y];
				}
			}


		}catch(Exception ee){
			ee.printStackTrace();
		}finally{
			try{
				br.close();
			}catch(IOException ie){
				System.out.println("Error occured while closing the BufferedReader");
				ie.printStackTrace();
			}
		}		
	}
	public static void initalize20(){
		String line = null;
		BufferedReader br = null;

		int counter = 0;
		try
		{
			br = new BufferedReader(new FileReader(MISSING20));

			while ((line = br.readLine()) != null) 
			{
				array20[counter] = line.split(COMMA_DELIMITER);
				//System.out.println(counter);
				if(array20.length < 0 ){
					System.out.println("sup");
				}
				counter++;
			}

			for(int y = 0; y<85;y++){
				for(int x =0; x<3588; x++){
					arrayImp20[x][y] = array20[x][y];
					arrayImpC20[x][y] = array20[x][y];
				}
			}


		}catch(Exception ee){
			ee.printStackTrace();
		}finally{
			try{
				br.close();
			}catch(IOException ie){
				System.out.println("Error occured while closing the BufferedReader");
				ie.printStackTrace();
			}
		}		
	}

	public static void calculateImputedMean004(){
		double vals = 0;
		int totalNum= 0;
		boolean complete = false;
		for(int y = 0; y<85;y++){
			for(int x =0; x<3588; x++){
				if(arrayImp004[x][y].charAt(0) == '?'){
					if(complete == false){
						while(count<3557){
							if((arrayImp004[count][y].charAt(0)) != '?'){
								vals += Double.parseDouble((arrayImp004[count][y]));
								totalNum++;
								count++;
							}
							else{
								count++;
							}
						}
						vals = vals/totalNum;
						complete = true;
						arrayImp004[x][y] = String.valueOf(vals);
					}
					else{
						arrayImp004[x][y] = String.valueOf(vals);
					}
				}

			}
			vals = 0;
			count = 1;
			totalNum = 0;
			complete = false;
		}


		BufferedWriter bufferedWriter = null;

		try {

			File file = new File("V00666733_a2_missing004_imputed_mean.csv");
			if(!file.exists()){
				file.createNewFile();
			}

			FileWriter fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);
			for(int x =1; x<3588; x++){
				for(int y = 0; y<84;y++){
					bufferedWriter.write(arrayImp004[x][y]);
					bufferedWriter.write(",");
				}
				bufferedWriter.newLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedWriter != null){
					bufferedWriter.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}


	public static void calculateImputedMeanConditional004(){
		double valsC = 0;
		int totalNumC= 0;
		double valsF = 0;
		int totalNumF= 0;
		boolean completeC = false;
		boolean completeF = false;

		for(int y = 0; y<85;y++){
			for(int x =0; x<3588; x++){
				if(arrayImpC004[x][y].charAt(0) == '?'){
					if(arrayImpC004[x][84].charAt(0) == 'C'){
						if(completeC == false){
							while(countC<3557){
								if((arrayImpC004[countC][y].charAt(0)) != '?' && arrayImpC004[countC][84].charAt(0) == 'C'){
									valsC += Double.parseDouble((arrayImpC004[countC][y]));
									totalNumC++;
									countC++;
								}
								else{
									countC++;
								}
							}
							valsC = valsC/totalNumC;
							countC = 1;
							totalNumC = 0;
							completeC = true;
							arrayImpC004[x][y] = String.valueOf(valsC);
						}
						else{
							arrayImpC004[x][y] = String.valueOf(valsC);
						}
					}

					else{
						if(arrayImpC004[x][y].charAt(0) == '?'){
							if(completeF == false){
								while(countF<3557){
									if((arrayImpC004[countF][y].charAt(0)) != '?' && arrayImpC004[countC][84].charAt(0) == 'F'){
										valsF += Double.parseDouble((arrayImpC004[countF][y]));
										totalNumF++;
										countF++;
									}
									else{
										countF++;
									}
								}
								valsF = valsF/totalNumF;
								countF = 1;
								totalNumF = 0;
								completeF = true;
								arrayImpC004[x][y] = String.valueOf(valsF);
							}
							else{
								arrayImpC004[x][y] = String.valueOf(valsF);
							}
						}
					}
				}
			}
			valsC = 0;
			totalNumC= 0;
			valsF = 0;
			totalNumF= 0;
			completeC = false;
			completeF = false;
			countF = 1;
			countC = 1;
		}

		BufferedWriter bufferedWriter = null;

		try {

			File file = new File("V00666733_a2_missing004_imputed_mean_conditional.csv");
			if(!file.exists()){
				file.createNewFile();
			}

			FileWriter fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);
			for(int x =1; x<3588; x++){
				for(int y = 0; y<85;y++){
					bufferedWriter.write(arrayImpC004[x][y]);
					bufferedWriter.write(",");
				}
				bufferedWriter.newLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedWriter != null){
					bufferedWriter.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}
	public static void calculateImputedMean20(){
		double vals = 0;
		int totalNum= 0;
		boolean complete = false;
		for(int y = 0; y<85;y++){
			for(int x =0; x<3588; x++){
				if(arrayImp20[x][y].charAt(0) == '?'){
					if(complete == false){
						while(count<3557){
							if((arrayImp20[count][y].charAt(0)) != '?'){
								vals += Double.parseDouble((arrayImp20[count][y]));
								totalNum++;
								count++;
							}
							else{
								count++;
							}
						}
						vals = vals/totalNum;
						complete = true;
						arrayImp20[x][y] = String.valueOf(vals);
					}
					else{
						arrayImp20[x][y] = String.valueOf(vals);
					}
				}

			}
			vals = 0;
			count = 1;
			totalNum = 0;
			complete = false;
		}


		BufferedWriter bufferedWriter = null;

		try {

			File file = new File("V00666733_a2_missing20_imputed_mean.csv");
			if(!file.exists()){
				file.createNewFile();
			}

			FileWriter fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);
			for(int x =1; x<3588; x++){
				for(int y = 0; y<84;y++){
					bufferedWriter.write(arrayImp20[x][y]);
					bufferedWriter.write(",");
				}
				bufferedWriter.newLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedWriter != null){
					bufferedWriter.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}


	public static void calculateImputedMeanConditional20(){
		double valsC = 0;
		int totalNumC= 0;
		double valsF = 0;
		int totalNumF= 0;
		boolean completeC = false;
		boolean completeF = false;

		for(int y = 0; y<85;y++){
			for(int x =0; x<3588; x++){
				if(arrayImpC004[x][y].charAt(0) == '?'){
					if(arrayImpC004[x][84].charAt(0) == 'C'){
						if(completeC == false){
							while(countC<3557){
								if((arrayImpC004[countC][y].charAt(0)) != '?' && arrayImpC004[countC][84].charAt(0) == 'C'){
									valsC += Double.parseDouble((arrayImpC004[countC][y]));
									totalNumC++;
									countC++;
								}
								else{
									countC++;
								}
							}
							valsC = valsC/totalNumC;
							countC = 1;
							totalNumC = 0;
							completeC = true;
							arrayImpC004[x][y] = String.valueOf(valsC);
						}
						else{
							arrayImpC004[x][y] = String.valueOf(valsC);
						}
					}

					else{
						if(arrayImpC004[x][y].charAt(0) == '?'){
							if(completeF == false){
								while(countF<3557){
									if((arrayImpC004[countF][y].charAt(0)) != '?' && arrayImpC004[countC][84].charAt(0) == 'F'){
										valsF += Double.parseDouble((arrayImpC004[countF][y]));
										totalNumF++;
										countF++;
									}
									else{
										countF++;
									}
								}
								valsF = valsF/totalNumF;
								countF = 1;
								totalNumF = 0;
								completeF = true;
								arrayImpC004[x][y] = String.valueOf(valsF);
							}
							else{
								arrayImpC004[x][y] = String.valueOf(valsF);
							}
						}
					}
				}
			}
			valsC = 0;
			totalNumC= 0;
			valsF = 0;
			totalNumF= 0;
			completeC = false;
			completeF = false;
			countF = 1;
			countC = 1;
		}

		BufferedWriter bufferedWriter = null;

		try {

			File file = new File("V00666733_a2_missing20_imputed_mean_conditional.csv");
			if(!file.exists()){
				file.createNewFile();
			}

			FileWriter fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);
			for(int x =1; x<3588; x++){
				for(int y = 0; y<85;y++){
					bufferedWriter.write(arrayImpC004[x][y]);
					bufferedWriter.write(",");
				}
				bufferedWriter.newLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedWriter != null){
					bufferedWriter.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

	public static void main(String[] args) throws IOException {
		initalize004();
		initalize20();
		calculateImputedMean004();
		calculateImputedMeanConditional004();
		calculateImputedMean20();
		calculateImputedMeanConditional20();

	}

}
