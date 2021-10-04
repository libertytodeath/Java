import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileUtil { //기능만 가지고 있는 메서드
	
   static int getRowCount(String filename){ //1.행의 수를 구하는 메서드
      int cnt = 0; //cnt=count개수
      File f = new File(filename);//파일읽어오기
      FileReader fr = null;// 한글자씩
      BufferedReader br = null;// 한줄씩
      
      try {
         fr = new FileReader(f);
         br = new BufferedReader(fr);// 읽기위한 세팅 끝
         
         //---------이하 코드가 실제로 읽어오는 부분----------------
         while(br.readLine()!=null){
            cnt++;
         }
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } finally{         
         try {
            if(br!=null){
               br.close();
            }
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
      return cnt;
   }
   
   
   static int getColumnCount(String filename){ //2.열의 수를 구하는 메서드(이 메서드는 쓰지는 않을 거임,참고만해)
         int cnt = 0;
         File f = new File(filename);
         FileReader fr = null;
         BufferedReader br = null;
         
         try {
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            //---------이하 코드가 실제로 읽어오는 부분----------------
            cnt = br.readLine().split("/").length;
            // 한줄을 읽어와서 /로 자른 배열의 길이 == 컬럼의 길이
            
         } catch (FileNotFoundException e) {
            e.printStackTrace();
         } catch (IOException e) {
            e.printStackTrace();
         } finally{         
            try {
               if(br!=null){
                  br.close();
               }
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
         return cnt;
      }
   
   
   static String[][] getFile(String filename){
      // 미리 getRowCount를 실행해서 파일의 총 줄의 수를 알아오고, 그 줄수에 맞게 배열을 만든다.
      String[][] data = new String[getRowCount(filename)][2]; //이부분 이해안감 테스트해보기
      File f = new File(filename);
      FileReader fr = null;
      BufferedReader br = null;
      
      try {
         fr = new FileReader(f);
         br = new BufferedReader(fr);
         //-----------이하 코드가 실제로 읽어오는 부분---------------
         String l=null;
         int i=0;
         while((l=br.readLine())!=null){
            data[i][0] =l.split("/")[0]; //이부분 이해안감 테스트해보기
            data[i][1] =l.split("/")[1];
            i++;
         }      
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } finally{         
         try {
            if(br!=null){
               br.close();
            }
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
      return data;      
   }
   static void saveFile(String str){
	   File f = new File("savemenu.txt");
	   FileWriter fw = null;
	   PrintWriter pw = null;
	   
	   try {
		fw = new FileWriter(f);
		pw = new PrintWriter(fw);
		
		str=str.replace("<html>","");
		str=str.replace("<html>","");
		
		//처음부터 시작해서 미지막 <br>앞에까지 자르기
		str = str.substring(0,str.lastIndexOf("<br>"));

		pw.println(str);
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		if(pw!=null){
			pw.close();
		}
	}
   }
   
   
   
   
   
   
}