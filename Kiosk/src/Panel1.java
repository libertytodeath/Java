import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
//첫번째 창- 매장, 포장 선택
public class Panel1 extends JPanel implements ActionListener{
   JButton btnHere, btnTogo;
   McMain mmain; //이게 있으면 메인에서 패널을 관리할 수 있다. '메인에서 4->2페이지로 가라'도 시킬수 있음
   
   public Panel1(McMain mmain) {
      this.mmain = mmain;
      btnHere = new JButton("매장");
      btnTogo = new JButton("포장");
      btnHere.addActionListener(this);
      btnTogo.addActionListener(this);
      
      this.add(btnHere);
      this.add(btnTogo);
      this.setBackground(Color.GREEN);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getSource()==btnHere){
         mmain.add(mmain.pnl2); //전체를 메인에서 관리함
         
         /*Panel2 pnl2 = new Panel2();
         mmain.add(pnl2);*/ //이렇게 하면 패널1이 안되면 2도 안되므로 위의 코드를 써서 전체를 메인에서 관리함
         
         mmain.remove(this);
         mmain.repaint();
         mmain.revalidate();
      }else if(e.getSource()==btnTogo){
         
      }
      
   }//현재페이지(패널)만 실행안되므로 이거만들어서(직접 여기서 프레임을 생성해서) 테스트 가능
   public static void main(String[] args) {
	   //메인은 프로그램이 시작될 때 생성자보다 먼저 실행됨(전체 코드 실행안되면 이코드 지울것)
	   Panel1 p = new Panel1(null);
	   JFrame f = new JFrame();
	   f.setDefaultCloseOperation(3);
	   f.setSize(500,500);
	   f.add(p);
	   f.setVisible(true);
   }

}