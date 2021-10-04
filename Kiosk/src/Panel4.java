import javax.swing.JLabel;
import javax.swing.JPanel;
//마지막에 주문 내역(메뉴 이름, 가격, 수량)을 라벨에 출력하는 창
public class Panel4 extends JPanel{	
	
	JLabel lbl;
	public Panel4(McMain mmain) {
		lbl = new JLabel();
		String str="";
		
		for(int i=0;i<mmain.tbl2.getRowCount();i++){
			str+=mmain.tbl2.getValueAt(i, 0);
			str+=mmain.tbl2.getValueAt(i, 1);
			str+=mmain.tbl2.getValueAt(i, 2);
			//System.out.println(mmain.tbl2.getValueAt(i, 0)+" "+mmain.tbl2.getValueAt(i, 1));
		}
		for(int i=0;i<mmain.tbl3.getRowCount();i++){
			str+=mmain.tbl3.getValueAt(i, 0);
			str+=mmain.tbl3.getValueAt(i, 1);
			str+=mmain.tbl3.getValueAt(i, 2);
			//System.out.println(mmain.tbl2.getValueAt(i, 0)+" "+mmain.tbl2.getValueAt(i, 1));
		}
		lbl.setText(str);
		
		//이미 이전페이지에서 넘어온 값은 str에 담겨서 lbl에 출력되었다
		FileUtil.saveFile(str);//파일저장 끝
		
		
		this.add(lbl);
		
		// 여기 까지 오면 tbl2 와 tbl3의 모든 내용을 가지고 있다.
		// 이 내용을 파일에 출력하면 프로그램 끝.
	}
}
