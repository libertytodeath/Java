import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

class JPopMenuTable extends JFrame implements ActionListener, MouseListener{
	JTable tbl;
	JMenuItem item1,item2;
	JPopupMenu popmenu;
	DefaultTableModel model;
	int row = -1;// table�� �������� �ʾ����� ���� �⺻���� -1 ��!!
	
	public JPopMenuTable() {
		this.setDefaultCloseOperation(3);
		this.setSize(500, 500);
		
		String[] header={"����","����"};
		String[][] contents={
								{"aaa","111"},
								{"bbb","222"},
								{"ccc","333"}				
							};
		
		model = new DefaultTableModel(contents,header);
		tbl = new JTable(model);
		JScrollPane sp = new JScrollPane(tbl);
		tbl.addMouseListener(this);
		
		this.add(sp);
		
		
		//----------�˾��޴� �߰�--------------
		popmenu = new JPopupMenu();
		item1 = new JMenuItem("����");
		item2 = new JMenuItem("�߰�");
		item1.addActionListener(this);
		item2.addActionListener(this);
		
		popmenu.add(item1);
		popmenu.add(item2);
		//--------------------------------
		
		tbl.setComponentPopupMenu(popmenu);
		
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new JPopMenuTable();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==item1){
			System.out.println("����");
			model.removeRow(row);//�̸� ���õ� �ٹ�ȣ�� �̿��ؼ� ����
			
		}else if(e.getSource()==item2){
			System.out.println("�߰�");// �߰��� ����������
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) { //JTable�� Ŭ���ϸ� �����
		popmenu.show(this, e.getX()+10, e.getY()+50);//x �� x��ǥ y�� y��ǥ w�� ���� h�� ����, �˾�â�� �߰��ϴ� ����
		row=tbl.getSelectedRow(); //������ �ٹ�ȣ�� �������� row�� �־��ش�.
	}
	
	
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
}







