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
//메인 메뉴 선택 패널
public class Panel2 extends JPanel implements ActionListener, MouseListener{
	JLabel lbl;
	McMain mmain;
	JTable tbl;
	DefaultTableModel model;
	JButton btnPrev,btnNext;
	JPopupMenu pop;//팝업메뉴
	JMenuItem item;//삭제
	int currentRow = -1; //-1이 하나도 없는거임
	
	public Panel2(McMain mmain) {
		this.mmain=mmain;
		
		this.setLayout(new BorderLayout());
		this.setBackground(Color.BLUE);
		
		lbl=new JLabel("메인메뉴 선택");
		this.add(lbl,"North");
		
		JPanel pnl = new JPanel();// 센터 패널
		
		//static 메서드는 생성없이 바로 사용가능
		String[][] data = FileUtil.getFile("mainmenu.txt"); //클래스.메소드(변수); 형태임
		
		/*
		for(int i=0;i<data.length;i++){//전체 줄의 길이
			for(int j=0;j<data[i].length;j++){//컬럼의 길이
				// 이 이중for문이 이중 배열의 전체값을 읽어오는 부분				
				System.out.print(data[i][j]);
			}
			System.out.println();
		}*/
		
		for(int i=0; i<data.length; i++){ //전체 줄의 길이
			JButton btn = new JButton(data[i][0]+" "+data[i][1]);
			btn.addActionListener(this);
			btn.setPreferredSize(new Dimension(200,50)); //컴포넌트의 크기 조정가능한 setPreferredSize
			pnl.add(btn);
		}
		
		this.add(pnl,"Center");
		
		
		//---------------여기부터는  남쪽의 JTable설정-------------
		String[] header={"품목","가격","수량"};
		String[][] contents={};
		
		model = new DefaultTableModel(contents,header);
		tbl = new JTable(model);
		JScrollPane sp = new JScrollPane(tbl);
		
		JPanel pnlSouth = new JPanel(new BorderLayout());
		pnlSouth.add(sp,"Center");
		
		
		//--------------같은 패널에 버튼 추가-------------------
		btnPrev = new JButton("이전");
		btnNext = new JButton("다음");
		btnPrev.addActionListener(this);
		btnNext.addActionListener(this);
		
		JPanel btnPnl = new JPanel();
		btnPnl.add(btnPrev);
		btnPnl.add(btnNext);
		pnlSouth.add(btnPnl,"South");
		
		
		////////////////////////////
		// (tbl.setComponentPopupMenu(popmenu); 빼도됨)
		
		tbl.addMouseListener(this);
		item = new JMenuItem("삭제");
		
		item.addActionListener(this);
		pop = new JPopupMenu();
		pop.add(item);
		
		// actionPerformed, mouseClicked,전역변수 currentRow 추가
		////////////////////////////
		
		this.add(pnlSouth,"South");
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnPrev){
			mmain.add(mmain.pnl1);// 메인을 내가 들고 있으니까 명령을 메인에게 내릴수 있다.
			mmain.remove(this);// 메인이 모든 패널을 들고 있으니까 그냥 들고 있는거 중에서 출력할 패널을 적어주기만 하면 됨
			mmain.repaint();
			mmain.revalidate();
		}else if(e.getSource()==btnNext){
			mmain.setTbl2(tbl); //Panel4와 연결시키는 줄임
			
			mmain.add(mmain.pnl3);
			mmain.remove(this);
			mmain.repaint();
			mmain.revalidate();
		}else if(e.getSource()==item){
			model.removeRow(currentRow);
		}else{ //12개의 메뉴 버튼이 여기 적용됨
			JButton btn=(JButton)e.getSource();//내가 누른 버튼을 btn변수에담는다.
			//System.out.println(btn.getText());
			boolean isExist=false;
			int findRow=0;
			
			for(int i=0; i<tbl.getRowCount(); i++){
				if(btn.getText().split(" ")[0].equals(tbl.getValueAt(i, 0))){
					// 버튼의 햄버거이름이랑 테이블의 내용중에 첫번째 컬럼(품목명)이랑 같으면
					findRow=i;// 만약에 찾은게 있다면 그 줄번호를 fineRow에 넣어주고
					isExist=true;// 존재여부의 변수를 true 바꿔준다.
					break;// 이미 찾았으니까 더이상 찾을 필요가 없으므로 break로 for문을 나옴 =>여기서 고른 버튼이 밑에 테이블에 있나없나 유무 확인함
				}
			}
			//////////////////////////////////////
			//테이블에 있는지 없는지 유무에 따라, 있을경우 한줄을 추가하거나 없을 경우 숫자만 1개 올림
			
			if(isExist==false){
				// 테이블의 없을때 추가될 내용
				String [] str = new String[3];
				str[0]=btn.getText().split(" ")[0];
				str[1]=btn.getText().split(" ")[1];
				str[2]="1";
				model.addRow(str);
			}else{
				// 테이블에 있을때 추가될 내용
				int cnt = Integer.parseInt((tbl.getValueAt(findRow, 2)+""));//수량
				// 현재 수량을 가져오는 부분 //(tbl.getValueAt(findRow, 2)는 오브젝트(타입이 없음)므로 문자로 바꾼다음 숫자로 바꿔준거임
				cnt++;			
				tbl.setValueAt(cnt+"", findRow, 2);
			}	
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		currentRow=tbl.getSelectedRow();
		pop.show(this, e.getX(), e.getY()+300);//기준이 프레임기준이므로 +300해줌
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
