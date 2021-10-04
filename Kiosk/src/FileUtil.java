import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileUtil { //��ɸ� ������ �ִ� �޼���
	
   static int getRowCount(String filename){ //1.���� ���� ���ϴ� �޼���
      int cnt = 0; //cnt=count����
      File f = new File(filename);//�����о����
      FileReader fr = null;// �ѱ��ھ�
      BufferedReader br = null;// ���پ�
      
      try {
         fr = new FileReader(f);
         br = new BufferedReader(fr);// �б����� ���� ��
         
         //---------���� �ڵ尡 ������ �о���� �κ�----------------
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
   
   
   static int getColumnCount(String filename){ //2.���� ���� ���ϴ� �޼���(�� �޼���� ������ ���� ����,������)
         int cnt = 0;
         File f = new File(filename);
         FileReader fr = null;
         BufferedReader br = null;
         
         try {
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            //---------���� �ڵ尡 ������ �о���� �κ�----------------
            cnt = br.readLine().split("/").length;
            // ������ �о�ͼ� /�� �ڸ� �迭�� ���� == �÷��� ����
            
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
      // �̸� getRowCount�� �����ؼ� ������ �� ���� ���� �˾ƿ���, �� �ټ��� �°� �迭�� �����.
      String[][] data = new String[getRowCount(filename)][2]; //�̺κ� ���ؾȰ� �׽�Ʈ�غ���
      File f = new File(filename);
      FileReader fr = null;
      BufferedReader br = null;
      
      try {
         fr = new FileReader(f);
         br = new BufferedReader(fr);
         //-----------���� �ڵ尡 ������ �о���� �κ�---------------
         String l=null;
         int i=0;
         while((l=br.readLine())!=null){
            data[i][0] =l.split("/")[0]; //�̺κ� ���ؾȰ� �׽�Ʈ�غ���
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
		
		//ó������ �����ؼ� ������ <br>�տ����� �ڸ���
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