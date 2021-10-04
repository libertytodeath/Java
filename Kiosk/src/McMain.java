import javax.swing.JFrame;
import javax.swing.JTable;

public class McMain extends JFrame{
	Panel1 pnl1;
	Panel2 pnl2;
	Panel3 pnl3;
	Panel4 pnl4;
	JTable tbl2,tbl3;
	
	
	public McMain() {
		this.setDefaultCloseOperation(3);
		this.setSize(700,800);
		
		pnl1 = new Panel1(this);// 패널만 가져다 붙일꺼기때문에 패널의 내용을 전부 다른클래스로 이동
		this.add(pnl1);//첫화면이므로 미리 만들어 붙임
		
		pnl2 = new Panel2(this);//두번째패널을 만들어만둠
		pnl3 = new Panel3(this);
		
		this.setVisible(true);
	}
	
	void setPnl4(Panel4 pnl4){
		this.pnl4=pnl4;
	}
	
	void setTbl2(JTable tbl2){
		this.tbl2=tbl2;
	}
	void setTbl3(JTable tbl3){
		this.tbl3=tbl3;
	}
	
	public static void main(String[] args) {
		new McMain();
	}
}
