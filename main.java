package gam;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
public class MainClass {

	boolean turn;
	int count;
	int pl1,pl2;
	int[][] gamatrix;
	JLabel plyr,lftplyr,ritplyr;
	JButton reset;
	JFrame fr;
	JPanel mainPanel;
	JPanel nPanel;
	JPanel leftPanel;
	JPanel ritPanel;
	ArrayList<ImageIcon> iconList;
	ArrayList<JButton> btnList;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MainClass man=new MainClass();
		man.gui();
	}
	private void gui() {
		// TODO Auto-generated method stub
		fr=new JFrame();
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.setResizable(false);
		gamatrix=new int[3][3];
		pl1=pl2=0;
		lftplyr=new JLabel();
		lftplyr.setForeground(Color.RED);
		ritplyr=new JLabel();
		ritplyr.setForeground(Color.BLUE);
		leftPanel=new JPanel();
		leftPanel.add(lftplyr);
		ritPanel=new JPanel();
		ritPanel.add(ritplyr);
		mainPanel=new JPanel();
		mainPanel.setLayout(new GridLayout(3,3));
		nPanel=new JPanel();
		nPanel.setLayout(new FlowLayout());
		reset=new JButton("RESET");
		reset.addActionListener(new resetListener());
		btnList=new ArrayList<JButton>();
		iconList=new ArrayList<ImageIcon>();
		iconList.add(new ImageIcon("E:\\Eclips\\tttb.png"));
		iconList.add(new ImageIcon("E:\\Eclips\\tttc.png"));
		iconList.add(new ImageIcon("E:\\Eclips\\ttto.png"));
		nPanel.add(reset);
		plyr=new JLabel();
		plyr.setForeground(Color.GREEN);
		plyr.setText("PLAYER  1  TURN");
		nPanel.add(plyr);
		for(int i=0;i<9;i++)
		{
			btnList.add(new JButton());
			btnList.get(i).addActionListener(new crossListener(i));
			mainPanel.add(btnList.get(i));
		}
		fr.add(BorderLayout.NORTH,nPanel);
		fr.add(BorderLayout.CENTER,mainPanel);
		fr.add(BorderLayout.WEST,leftPanel);
		fr.add(BorderLayout.EAST,ritPanel);
		fr.setLocationRelativeTo(null);
		fr.setSize(500,300);
		fr.setVisible(true);
		start();
	}
	private void start() {
		// TODO Auto-generated method stub
		turn=false;
		count=0;
		plyr.setText("PLAYER  1  TURN (X)");
		lftplyr.setText("PLAYER 1 WINS: "+pl1);
		ritplyr.setText("PLAYER 2 WINS: "+pl2);
		for(int i=0;i<9;i++)
		{
			gamatrix[i/3][i%3]=-1;
			btnList.get(i).setEnabled(true);
			btnList.get(i).setIcon(new ImageIcon("E:\\Eclips\\tttb.png"));
		}
	}
	
	class resetListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			for(int i=0;i<9;i++)
			{
				gamatrix[(int)i/3][i%3]=-1;
				btnList.get(i).setEnabled(true);
				btnList.get(i).setIcon(new ImageIcon("E:\\Eclips\\tttb.png"));
			}
			count=0;
			//mainPanel.validate();
			//mainPanel.repaint();
		}
		
	}
	
	
	class crossListener implements ActionListener{

		private int btnNum;
		
		public crossListener(int bNum) {
			// TODO Auto-generated constructor stub
			btnNum=bNum;
		}
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			switch(btnNum)
			{
				case 0:
						check(0);
					break;
				
				case 1:
						check(1);
					break;
					
				case 2:
						check(2);
					break;
					
				case 3:
						check(3);
					break;
					
				case 4:
						check(4);
					break;
					
				case 5:
						check(5);
					break;
					
				case 6:
						check(6);
					break;
					
				case 7:
						check(7);
					break;
					
				case 8:
						check(8);
					break;
			}
			
		}
		private void check(int i) {
			// TODO Auto-generated method stub
			
			if(turn==false)
			{
				btnList.get(i).setIcon(new ImageIcon("E:\\Eclips\\tttc.png"));
				btnList.get(i).setEnabled(false);
				gamatrix[(int)i/3][i%3]=0;
			}
			else
			{
				btnList.get(i).setIcon(new ImageIcon("E:\\Eclips\\ttto.png"));
				btnList.get(i).setEnabled(false);
				gamatrix[(int)i/3][i%3]=1;
			}
			
			if(checkForRow(i/3,i%3)||checkForCol(i/3,i%3)||checkForDiag(i/3,i%3))
			{
				if(turn==false)
				{
					pl1++;
					JOptionPane.showMessageDialog(null, "PLAYER 1 WINS");
				}
				else
				{
					pl2++;
					JOptionPane.showMessageDialog(null, "PLAYER 2 WINS");
				}
				start();
				return;
			}
		
			count++;
			turn=(turn==false)?(true):(false);
			if(turn==false)
			{
				plyr.setText("PLAYER 1 TURN (X)");
			}
			else
			{
				plyr.setText("PLAYER 2 TURN (0)");
			}
			if(count==9)
			{
				JOptionPane.showMessageDialog(null, "MATCH IS A DRAW");
				start();
			}
		}
		
	}


	public boolean checkForDiag(int row,int col) {
		// TODO Auto-generated method stub
		if((row==0 && col==0)||(row==2 && col==2))
		{
			if((gamatrix[0][0]==gamatrix[1][1])&&(gamatrix[1][1]==gamatrix[2][2]) && gamatrix[0][0]!=-1)
			{
				return true;
			}
		}
		else if((row==0 && col==2)||(row==2 && col==0))
		{
			if((gamatrix[0][2]==gamatrix[1][1])&&(gamatrix[1][1]==gamatrix[2][0])&& gamatrix[0][2]!=-1)
			{
				return true;
			}
		}
		else if(row==1 && col==1)
		{
			if((gamatrix[0][0]==gamatrix[1][1])&&(gamatrix[1][1]==gamatrix[2][2])&& gamatrix[0][0]!=-1)
			{
				return true;
			}
			else if((gamatrix[0][2]==gamatrix[1][1])&&(gamatrix[1][1]==gamatrix[2][0])&& gamatrix[0][2]!=-1)
			{
				return true;
			}
		}
		return false;
	}
	public boolean checkForCol(int row,int col) {
		// TODO Auto-generated method stub
		if((gamatrix[0][col]==gamatrix[1][col]) &&(gamatrix[1][col]==gamatrix[2][col])&& gamatrix[0][col]!=-1)
		{
			return true;
		}
		return false;
	}
	public boolean checkForRow(int row,int col) {
		// TODO Auto-generated method stub
		
		if((gamatrix[row][0]==gamatrix[row][1])&&(gamatrix[row][1]==gamatrix[row][2])&& gamatrix[row][0]!=-1)
		{
			return true;
		}
		return false;
	}

}
