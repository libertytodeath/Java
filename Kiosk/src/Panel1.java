import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
//ù��° â- ����, ���� ����
public class Panel1 extends JPanel implements ActionListener{
   JButton btnHere, btnTogo;
   McMain mmain; //�̰� ������ ���ο��� �г��� ������ �� �ִ�. '���ο��� 4->2�������� ����'�� ��ų�� ����
   
   public Panel1(McMain mmain) {
      this.mmain = mmain;
      btnHere = new JButton("����");
      btnTogo = new JButton("����");
      btnHere.addActionListener(this);
      btnTogo.addActionListener(this);
      
      this.add(btnHere);
      this.add(btnTogo);
      this.setBackground(Color.GREEN);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getSource()==btnHere){
         mmain.add(mmain.pnl2); //��ü�� ���ο��� ������
         
         /*Panel2 pnl2 = new Panel2();
         mmain.add(pnl2);*/ //�̷��� �ϸ� �г�1�� �ȵǸ� 2�� �ȵǹǷ� ���� �ڵ带 �Ἥ ��ü�� ���ο��� ������
         
         mmain.remove(this);
         mmain.repaint();
         mmain.revalidate();
      }else if(e.getSource()==btnTogo){
         
      }
      
   }//����������(�г�)�� ����ȵǹǷ� �̰Ÿ���(���� ���⼭ �������� �����ؼ�) �׽�Ʈ ����
   public static void main(String[] args) {
	   //������ ���α׷��� ���۵� �� �����ں��� ���� �����(��ü �ڵ� ����ȵǸ� ���ڵ� �����)
	   Panel1 p = new Panel1(null);
	   JFrame f = new JFrame();
	   f.setDefaultCloseOperation(3);
	   f.setSize(500,500);
	   f.add(p);
	   f.setVisible(true);
   }

}