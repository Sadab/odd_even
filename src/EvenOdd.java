import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JOptionPane;
import java.util.Timer;
import java.util.TimerTask;

public class EvenOdd extends JFrame{
	JLabel randNumLabel = new JLabel();
	JLabel scoreLabel = new JLabel("0");
	JLabel highScrLabel = new JLabel("0");
	JLabel timerLabel = new JLabel();
	JLabel currentScoreLabel = new JLabel("Score");
	JLabel highScoreLabel = new JLabel("High Score");
	JLabel timerTxtLabel = new JLabel("Time Remaining:");
	Random generator = new Random();
	Font fnt = new Font("Arial",Font.PLAIN,30);
	Color clr = new Color(252, 247, 247);
	Border bdr = new LineBorder(new Color(72,170,219),4);
	JButton btnOdd = new JButton("ODD");
	JButton btnEven = new JButton("EVEN");
	Timer timer = new Timer();
	Timer timer2 = new Timer();
	int randomNumber;
	final int RANDOM_LOWER_BOUND = 1;
	final int RANDOM_UPPER_BOUND = 98;
	int finalScore = 0;
	int highScore = 0;
	int seconds = 30;
	int checkSec = 5;
	int bonusTime = 0;
	
	EvenOdd(){
		this.setTitle("Even Odd Game");
		this.setSize(300,330);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addItemToFrame();
		displayNewNumber();
		Listeners();
		gameTimer();
	}
	
	public void addItemToFrame(){
		//Labels
		timerTxtLabel.setBounds(10,10,170,50);
		timerTxtLabel.setFont(new Font("Arial",Font.BOLD,20));
		timerTxtLabel.setForeground(clr);
		timerLabel.setBounds(170, 10, 50, 50);
		timerLabel.setFont(new Font("Arial",Font.ITALIC,20));
		timerLabel.setForeground(clr);
		randNumLabel.setBounds(120,60,80,50);
		randNumLabel.setFont(fnt);
		randNumLabel.setForeground(clr);
		randNumLabel.setBorder(bdr);
		currentScoreLabel.setBounds(10, 180, 70, 50);
		currentScoreLabel.setFont(new Font("Arial",Font.BOLD,20));
		currentScoreLabel.setForeground(clr);
		scoreLabel.setBounds(100, 180, 50, 50);
		scoreLabel.setFont(new Font("Arial",Font.PLAIN,20));
		scoreLabel.setForeground(clr);
		highScoreLabel.setBounds(10,230,120,50);
		highScoreLabel.setFont(new Font("Arial",Font.BOLD,20));
		highScoreLabel.setForeground(clr);
		highScrLabel.setBounds(150, 230, 50, 50);
		highScrLabel.setFont(new Font("Arial",Font.PLAIN,20));
		highScrLabel.setForeground(clr);

		//Buttons
		btnOdd.setBounds(30, 130, 100, 40);
		btnEven.setBounds(160, 130, 100, 40);		
		btnOdd.setBackground(new Color(178, 17, 17));
		btnEven.setBackground(new Color(178, 17, 17));
		btnOdd.setForeground(clr);
		btnEven.setForeground(clr);
		btnOdd.setFont(new Font("Arial",Font.BOLD,20));
		btnEven.setFont(new Font("Arial",Font.BOLD,20));
		
		//Frames
		this.add(timerLabel);
		this.add(timerTxtLabel);
		this.add(randNumLabel);
		this.add(scoreLabel);
		this.add(currentScoreLabel);
		this.add(highScoreLabel);
		this.add(highScrLabel);
		this.add(timerTxtLabel);
		this.add(btnOdd);
		this.add(btnEven);
		this.getContentPane().setBackground(new Color(99, 92, 92));
		
	}
	
	public void Listeners(){
		btnOdd.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(randomNumber%2 != 0){
					updateScore();
					displayNewNumber();
					bonusTime++;
				}
				else{
					gameOver();
				}
			}
		});
		
		btnEven.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(randomNumber%2 == 0){
					updateScore();
					displayNewNumber();
					bonusTime++;
				}
				else{
					gameOver();
					
				}
			}
		});
	}
	
	public void displayNewNumber(){
		int tempOldRand = randomNumber;
		randomNumber = generator.nextInt(RANDOM_UPPER_BOUND) + RANDOM_LOWER_BOUND;
		while(randomNumber == tempOldRand){
			randomNumber = generator.nextInt(RANDOM_UPPER_BOUND);
		}
		randNumLabel.setText(Integer.toString(randomNumber));
	}
	
	public void updateScore(){
		finalScore++;
		scoreLabel.setText(Integer.toString(finalScore));
		if(finalScore > highScore){
			highScore++;
			highScrLabel.setText(Integer.toString(highScore));
		}
	}
	
	public void gameOver(){
		int result = JOptionPane.showConfirmDialog(this,"Wrong Answer. Play again?","Game Over", JOptionPane.YES_NO_OPTION);
		if(result==JOptionPane.NO_OPTION){
			System.exit(0);
		}
		else if(result == JOptionPane.YES_OPTION){
			seconds = 30;
			scoreLabel.setText("");
			finalScore=0;
			bonusTime = 0;
			displayNewNumber();
		}
	}
	
	public void gameTimer(){
		TimerTask task = new TimerTask(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(bonusTime == 5){
					seconds+=3;
					bonusTime = 0;
				}
				seconds--;
				timerLabel.setText(Integer.toString(seconds));
				if (seconds < 1) {
					int result = JOptionPane.showConfirmDialog(EvenOdd.this,"Time's up. Play again?","Time's up", JOptionPane.YES_NO_OPTION);
					if(result==JOptionPane.NO_OPTION){
						System.exit(0);
					}
					else if(result == JOptionPane.YES_OPTION){
						seconds = 30;
						scoreLabel.setText("");
						finalScore=0;
						bonusTime = 0;
						displayNewNumber();	
					}
                }	
			}
		};
		timer.scheduleAtFixedRate(task, 0, 1000);
	}
	
}	
