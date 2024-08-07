import java.awt.Color;
import java.io.File;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class BlackJackGUI implements ActionListener{
	private BlackJack blackJack = new BlackJack();
	private JPanel bankPanel = new JPanel();
	private JPanel playerPanel = new JPanel();


	public BlackJackGUI() {
		JFrame frame = new JFrame("BlackJack GUI");
		JButton anotherCardButton = new JButton("Another card");
		JButton noMoreCardButton = new JButton("No more card");
		JButton resetButton = new JButton("Reset");
		JPanel topPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(centerPanel, BoxLayout.Y_AXIS);


		frame.setMinimumSize(new Dimension(640, 480));
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(centerPanel, BorderLayout.SOUTH);

		anotherCardButton.setActionCommand("Another card");
		anotherCardButton.addActionListener(this);

		noMoreCardButton.setActionCommand("No more card");
		noMoreCardButton.addActionListener(this);

		resetButton.setActionCommand("Reset");
		resetButton.addActionListener(this);

		topPanel.add(anotherCardButton);
		topPanel.add(noMoreCardButton);
		topPanel.add(resetButton);

		centerPanel.setLayout(boxlayout);
		centerPanel.add(bankPanel);
		centerPanel.add(playerPanel);

		bankPanel.setBorder(BorderFactory.createTitledBorder("Bank"));
		playerPanel.setBorder(BorderFactory.createTitledBorder("Player"));

		try{
			resetButton.setEnabled(true);
			updatePlayerPanel();
			updateBankPanel();

			if(blackJack.isGameFinished()){
				anotherCardButton.setEnabled(false);
				noMoreCardButton.setEnabled(false);
			}else{
				anotherCardButton.setEnabled(true);
				noMoreCardButton.setEnabled(true);
			}
		}catch (FileNotFoundException e) {
			System.out.println( e.getMessage() );
		}

		frame.pack();
		frame.setVisible(true);
	}

	private void addToPanel(JPanel p, String token) throws FileNotFoundException{
		File file = new File("./img/card_"+token+".png");
		if (!file.exists()) { throw new FileNotFoundException("Can't find "+file.getPath()); }

		ImageIcon icon = new ImageIcon(file.getPath());
		JLabel label = new JLabel(icon);

		p.add(label);
	}

	private void updatePlayerPanel() throws FileNotFoundException{
		JLabel playerBestLabel = new JLabel("Player best : " + blackJack.getPlayerBest());
		playerPanel.removeAll();

		try {
			for ( Card c: blackJack.getPlayerCardList() ) {
				addToPanel( playerPanel, c.toString() );
				playerPanel.add(playerBestLabel);
			}

			if(blackJack.isPlayerWinner() && !(blackJack.isBankWinner()) && blackJack.isGameFinished() ){
				if(blackJack.getPlayerBest() == 21){
					addToPanel( playerPanel, "blackjack" );
				}
				addToPanel( playerPanel, "winner" );
				addToPanel( bankPanel, "looser" );
			}

			playerPanel.updateUI();
		} catch (FileNotFoundException ex) {
			System.out.println( ex.getMessage() );
		}

	}

	private void updateBankPanel() throws FileNotFoundException{
		JLabel bankBestLabel = new JLabel("Bank best : " + blackJack.getBankBest());
		bankPanel.removeAll();

		try {
			for ( Card c: blackJack.getBankCardList() ) {
				addToPanel( bankPanel, c.toString() );
				bankPanel.add(bankBestLabel);
			}
			if(blackJack.isBankWinner() && !(blackJack.isPlayerWinner()) && blackJack.isGameFinished() ){
				//blackjack
				if(blackJack.getBankBest() == 21){
					addToPanel(bankPanel, "blackjack");
				}
				addToPanel(bankPanel, "winner");
				addToPanel(playerPanel, "looser");
			}

			bankPanel.updateUI();
		} catch (FileNotFoundException ex) {
			System.out.println( ex.getMessage() );
		}

	}

	public void actionPerformed(ActionEvent e){
		switch (e.getActionCommand()) {
			case "Another card":
				try {
					blackJack.playerDrawAnotherCard();
					updatePlayerPanel();
					updateBankPanel();
				} catch(Exception ex) {
					System.out.println( ex.getMessage() );
				}
				break;
			case "No more card":
				try {
					blackJack.bankLastTurn();
					updatePlayerPanel();
					updateBankPanel();
				} catch(Exception ex) {
					System.out.println( ex.getMessage() );
				}
				break;
			case "Reset":
				try {
					blackJack.reset();
					updatePlayerPanel();
					updateBankPanel();
				} catch(Exception ex) {
					System.out.println(ex.getMessage());
				}
				break;
		}

		// try{
		// 	updatePlayerPanel();
		// 	updateBankPanel();
		// 	if (blackJack.isPlayerWinner() && blackJack.isBankWinner() && blackJack.isGameFinished() ){
		// 		addToPanel(playerPanel, "draw");
		// 		addToPanel(playerPanel, "winner");
		// 		addToPanel(bankPanel, "draw");
		// 		addToPanel(bankPanel, "looser");
		// 	}
		// }catch (FileNotFoundException ex) {
		// 	JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		// 	System.exit(-1);
		// }
	}

	public static void main(String[] args) {
		new BlackJackGUI();
	}
}
