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
		
		pnl1 = new Panel1(this);// �гθ� ������ ���ϲ��⶧���� �г��� ������ ���� �ٸ�Ŭ������ �̵�
		this.add(pnl1);//ùȭ���̹Ƿ� �̸� ����� ����
		
		pnl2 = new Panel2(this);//�ι�°�г��� ������
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
