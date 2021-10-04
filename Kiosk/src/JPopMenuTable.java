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
	int row = -1;// table을 선택하지 않았을때 원래 기본값이 -1 임!!
	
	public JPopMenuTable() {
		this.setDefaultCloseOperation(3);
		this.setSize(500, 500);
		
		String[] header={"제목","저자"};
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
		
		
		//----------팝업메뉴 추가--------------
		popmenu = new JPopupMenu();
		item1 = new JMenuItem("삭제");
		item2 = new JMenuItem("추가");
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
			System.out.println("삭제");
			model.removeRow(row);//미리 선택된 줄번호를 이용해서 삭제
			
		}else if(e.getSource()==item2){
			System.out.println("추가");// 추가는 하지마세요
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) { //JTable을 클릭하면 실행됨
		popmenu.show(this, e.getX()+10, e.getY()+50);//x 는 x좌표 y는 y좌표 w는 가로 h는 세로, 팝업창이 뜨게하는 줄임
		row=tbl.getSelectedRow(); //선택한 줄번호를 전역변수 row에 넣어준다.
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







