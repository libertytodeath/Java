import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;
//���� �޴� ���� �г�
public class Panel2 extends JPanel implements ActionListener, MouseListener{
	JLabel lbl;
	McMain mmain;
	JTable tbl;
	DefaultTableModel model;
	JButton btnPrev,btnNext;
	JPopupMenu pop;//�˾��޴�
	JMenuItem item;//����
	int currentRow = -1; //-1�� �ϳ��� ���°���
	
	public Panel2(McMain mmain) {
		this.mmain=mmain;
		
		this.setLayout(new BorderLayout());
		this.setBackground(Color.BLUE);
		
		lbl=new JLabel("���θ޴� ����");
		this.add(lbl,"North");
		
		JPanel pnl = new JPanel();// ���� �г�
		
		//static �޼���� �������� �ٷ� ��밡��
		String[][] data = FileUtil.getFile("mainmenu.txt"); //Ŭ����.�޼ҵ�(����); ������
		
		/*
		for(int i=0;i<data.length;i++){//��ü ���� ����
			for(int j=0;j<data[i].length;j++){//�÷��� ����
				// �� ����for���� ���� �迭�� ��ü���� �о���� �κ�				
				System.out.print(data[i][j]);
			}
			System.out.println();
		}*/
		
		for(int i=0; i<data.length; i++){ //��ü ���� ����
			JButton btn = new JButton(data[i][0]+" "+data[i][1]);
			btn.addActionListener(this);
			btn.setPreferredSize(new Dimension(200,50)); //������Ʈ�� ũ�� ���������� setPreferredSize
			pnl.add(btn);
		}
		
		this.add(pnl,"Center");
		
		
		//---------------������ʹ�  ������ JTable����-------------
		String[] header={"ǰ��","����","����"};
		String[][] contents={};
		
		model = new DefaultTableModel(contents,header);
		tbl = new JTable(model);
		JScrollPane sp = new JScrollPane(tbl);
		
		JPanel pnlSouth = new JPanel(new BorderLayout());
		pnlSouth.add(sp,"Center");
		
		
		//--------------���� �гο� ��ư �߰�-------------------
		btnPrev = new JButton("����");
		btnNext = new JButton("����");
		btnPrev.addActionListener(this);
		btnNext.addActionListener(this);
		
		JPanel btnPnl = new JPanel();
		btnPnl.add(btnPrev);
		btnPnl.add(btnNext);
		pnlSouth.add(btnPnl,"South");
		
		
		////////////////////////////
		// (tbl.setComponentPopupMenu(popmenu); ������)
		
		tbl.addMouseListener(this);
		item = new JMenuItem("����");
		
		item.addActionListener(this);
		pop = new JPopupMenu();
		pop.add(item);
		
		// actionPerformed, mouseClicked,�������� currentRow �߰�
		////////////////////////////
		
		this.add(pnlSouth,"South");
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnPrev){
			mmain.add(mmain.pnl1);// ������ ���� ��� �����ϱ� ����� ���ο��� ������ �ִ�.
			mmain.remove(this);// ������ ��� �г��� ��� �����ϱ� �׳� ��� �ִ°� �߿��� ����� �г��� �����ֱ⸸ �ϸ� ��
			mmain.repaint();
			mmain.revalidate();
		}else if(e.getSource()==btnNext){
			mmain.setTbl2(tbl); //Panel4�� �����Ű�� ����
			
			mmain.add(mmain.pnl3);
			mmain.remove(this);
			mmain.repaint();
			mmain.revalidate();
		}else if(e.getSource()==item){
			model.removeRow(currentRow);
		}else{ //12���� �޴� ��ư�� ���� �����
			JButton btn=(JButton)e.getSource();//���� ���� ��ư�� btn��������´�.
			//System.out.println(btn.getText());
			boolean isExist=false;
			int findRow=0;
			
			for(int i=0; i<tbl.getRowCount(); i++){
				if(btn.getText().split(" ")[0].equals(tbl.getValueAt(i, 0))){
					// ��ư�� �ܹ����̸��̶� ���̺��� �����߿� ù��° �÷�(ǰ���)�̶� ������
					findRow=i;// ���࿡ ã���� �ִٸ� �� �ٹ�ȣ�� fineRow�� �־��ְ�
					isExist=true;// ���翩���� ������ true �ٲ��ش�.
					break;// �̹� ã�����ϱ� ���̻� ã�� �ʿ䰡 �����Ƿ� break�� for���� ���� =>���⼭ �� ��ư�� �ؿ� ���̺� �ֳ����� ���� Ȯ����
				}
			}
			//////////////////////////////////////
			//���̺� �ִ��� ������ ������ ����, ������� ������ �߰��ϰų� ���� ��� ���ڸ� 1�� �ø�
			
			if(isExist==false){
				// ���̺��� ������ �߰��� ����
				String [] str = new String[3];
				str[0]=btn.getText().split(" ")[0];
				str[1]=btn.getText().split(" ")[1];
				str[2]="1";
				model.addRow(str);
			}else{
				// ���̺� ������ �߰��� ����
				int cnt = Integer.parseInt((tbl.getValueAt(findRow, 2)+""));//����
				// ���� ������ �������� �κ� //(tbl.getValueAt(findRow, 2)�� ������Ʈ(Ÿ���� ����)�Ƿ� ���ڷ� �ٲ۴��� ���ڷ� �ٲ��ذ���
				cnt++;			
				tbl.setValueAt(cnt+"", findRow, 2);
			}	
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		currentRow=tbl.getSelectedRow();
		pop.show(this, e.getX(), e.getY()+300);//������ �����ӱ����̹Ƿ� +300����
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
