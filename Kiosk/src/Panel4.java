import javax.swing.JLabel;
import javax.swing.JPanel;
//�������� �ֹ� ����(�޴� �̸�, ����, ����)�� �󺧿� ����ϴ� â
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
		
		//�̹� �������������� �Ѿ�� ���� str�� ��ܼ� lbl�� ��µǾ���
		FileUtil.saveFile(str);//�������� ��
		
		
		this.add(lbl);
		
		// ���� ���� ���� tbl2 �� tbl3�� ��� ������ ������ �ִ�.
		// �� ������ ���Ͽ� ����ϸ� ���α׷� ��.
	}
}
