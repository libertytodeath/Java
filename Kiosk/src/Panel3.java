import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
//사이드 메뉴 선택 패널
public class Panel3 extends JPanel implements ActionListener{
	JLabel lbl;
	McMain mmain;
	JTable tbl;
	DefaultTableModel model;
	JButton btnPrev,btnNext;
	
	public Panel3(McMain mmain) {
		this.mmain=mmain;
		
		this.setLayout(new BorderLayout());
		this.setBackground(Color.BLUE);
		lbl=new JLabel("사이드메뉴 선택");
		this.add(lbl,"North");
		
		JPanel pnl = new JPanel();// 센터 패널		
	
		String[][] data = FileUtil.getFile("sidemenu.txt");		
		
		
		for(int i=0;i<data.length;i++){//전체 줄의 길이
			JButton btn = new JButton(data[i][0]+" "+data[i][1]);
			btn.addActionListener(this);
			btn.setPreferredSize(new Dimension(200,50));
			pnl.add(btn);
		}
		
		this.add(pnl,"Center");
		
		String[] header={"품목","가격","수량"};
		String[][] contents={};
		model = new DefaultTableModel(contents,header);
		tbl = new JTable(model);
		JScrollPane sp = new JScrollPane(tbl);
		JPanel pnlSouth = new JPanel(new BorderLayout());
		pnlSouth.add(sp,"Center");
		
		btnPrev = new JButton("이전");
		btnNext = new JButton("다음");
		btnPrev.addActionListener(this);
		btnNext.addActionListener(this);
		JPanel btnPnl = new JPanel();
		btnPnl.add(btnPrev);
		btnPnl.add(btnNext);
		pnlSouth.add(btnPnl,"South");
		
		this.add(pnlSouth,"South");
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnPrev){
			mmain.add(mmain.pnl2);// 메인을 내가 들고 있으니까 명령을 메인에게 내릴수 있다.
			mmain.remove(this);// 메인이 모든 패널을 들고 있으니까 그냥 들고 있는거 중에서
			mmain.repaint();// 출력할 패널을 적어주기만 하면된다. 따로 값을 넘기거나 할게 없다.
			mmain.revalidate();
		}else if(e.getSource()==btnNext){
			mmain.setTbl3(tbl); //Panel4와 연결시키는 줄임
			
			Panel4 pnl4 = new Panel4(mmain);
			mmain.setPnl4(pnl4);
			
			mmain.add(mmain.pnl4);
			mmain.remove(this);
			mmain.repaint();
			mmain.revalidate();
		}else{
			JButton btn=(JButton)e.getSource();
			boolean isExist=false;
			int findRow=0;
			for(int i=0;i<tbl.getRowCount();i++){
				if(btn.getText().split(" ")[0].equals(tbl.getValueAt(i, 0))){
					findRow=i;
					isExist=true;
					break;
				}
			}
			
			if(isExist==false){
				String [] str = new String[3];
				str[0]=btn.getText().split(" ")[0];
				str[1]=btn.getText().split(" ")[1];
				str[2]="1";
				model.addRow(str);
			}else{
				int cnt = Integer.parseInt((tbl.getValueAt(findRow, 2)+""));//수량
				cnt++;			
				tbl.setValueAt(cnt+"", findRow, 2);
			}
		}
		
	}

}
