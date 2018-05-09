import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * This class generates the game layout
 * 
 * There is one frame for learning the words,
 * and a second frame for quizzing the user.
 *
 * @author Lauren Kapraun
 *
 */
public class LearnGermanGame extends JFrame implements ActionListener {

	private ArrayList<Words> words = Words.wordLists();

	//Font Styles for the terms and the heading for language
	Font term = new Font("Sans-Serif", Font.ITALIC, 20);
	Font termHeading = new Font("Sans-Serif", Font.BOLD, 20);

	//Frames
	JFrame frame1 = new JFrame();
	JFrame frame2 = new JFrame();
	private int frame1ShowCount = 1;
	private int frame2ShowCount = 0;
	private int currentShowIndex = 0;
	private int currentQuestionIndex = 0;

	//Content
	private JLabel englishTitle, englishLabel, germanTitle, germanLabel, questionLabel, answerCheckLabel;
	private JButton f1Next, f2Next, back, answer1, answer2, answer3, answer4;
	private Panel northPanel, eastPanel, westPanel, southPanel;

	//Score Keeper
	private JLabel scoreLabel = new JLabel("Score: 0");
	private int score = 0;
	private int totalQuestions = 0;


	/**
	 * Creates the frame to display English and German Terms
	 * 
	 * Displays a Next and Back Button
	 */
	private void setupFrameOne() {
		frame1.setLayout(new BorderLayout());

		//Panels
		eastPanel = new Panel();
		westPanel = new Panel();
		southPanel = new Panel();

		//West Panel
		englishTitle = new JLabel("English");
		englishTitle.setFont(termHeading);
		englishTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		englishLabel = new JLabel();
		englishLabel.setFont(term);
		englishLabel.setHorizontalAlignment(SwingConstants.CENTER);

		westPanel.setPreferredSize(new Dimension(400, 400));
		westPanel.add(englishTitle).setPreferredSize(new Dimension(400, 200));
		westPanel.add(englishLabel).setPreferredSize(new Dimension(400, 200));
		frame1.add(westPanel, BorderLayout.WEST);

		//East Panel
		germanTitle = new JLabel("German");
		germanTitle.setFont(termHeading);
		germanTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		germanLabel = new JLabel();
		germanLabel.setFont(term);
		germanLabel.setHorizontalAlignment(SwingConstants.CENTER);

		eastPanel.setPreferredSize(new Dimension(400, 400));
		eastPanel.add(germanTitle).setPreferredSize(new Dimension(400, 200));
		eastPanel.add(germanLabel).setPreferredSize(new Dimension(400, 200));
		frame1.add(eastPanel, BorderLayout.EAST);

		//South Panel
		f1Next = new JButton("Next");
		f1Next.addActionListener(this);
		back = new JButton("Previous");
		back.addActionListener(this);

		southPanel.setPreferredSize(new Dimension(800, 200));
		southPanel.add(back).setPreferredSize(new Dimension(200, 100));
		southPanel.add(f1Next).setPreferredSize(new Dimension(200, 100));
		frame1.add(southPanel, BorderLayout.SOUTH);

		//Create Frame 1
		frame1.pack();
		setSize(800, 800);
		frame1.setVisible(true);
		setTitle("Learn German!");
	}

	
	/**
	 * Creates the frame to display the quiz questions and score
	 */
	private void setupFrameTwo() {
		frame2.setLayout(new BorderLayout());

		//Panels
		northPanel = new Panel();
		eastPanel = new Panel();
		westPanel = new Panel();
		southPanel = new Panel();

		//North Panel
		questionLabel = new JLabel();
		questionLabel.setFont(termHeading);
		questionLabel.setHorizontalAlignment(SwingConstants.CENTER);

		scoreLabel.setFont(termHeading);
		scoreLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		northPanel.setPreferredSize(new Dimension(800, 200));
		northPanel.add(questionLabel).setPreferredSize(new Dimension(700, 100));
		northPanel.add(scoreLabel).setPreferredSize(new Dimension(700, 100));
		frame2.add(northPanel, BorderLayout.NORTH);

		//West Panel
		answer1 = new JButton();
		answer3 = new JButton();

		answer1.addActionListener(this);
		answer3.addActionListener(this);

		westPanel.setPreferredSize(new Dimension(400, 400));
		westPanel.add(answer1).setPreferredSize(new Dimension(400, 200));
		westPanel.add(answer3).setPreferredSize(new Dimension(400, 200));
		frame2.add(westPanel, BorderLayout.WEST);

		//East Panel
		answer2 = new JButton();
		answer4 = new JButton();
		
		answer2.addActionListener(this);
		answer4.addActionListener(this);
		
		eastPanel.setPreferredSize(new Dimension(400, 400));
		eastPanel.add(answer2).setPreferredSize(new Dimension(400, 200));
		eastPanel.add(answer4).setPreferredSize(new Dimension(400, 200));
		frame2.add(eastPanel, BorderLayout.EAST);

		//South Panel
		f2Next = new JButton("Next");
		f2Next.addActionListener(this);

		answerCheckLabel = new JLabel();
		answerCheckLabel.setFont(termHeading);
		answerCheckLabel.setHorizontalAlignment(SwingConstants.CENTER);

		southPanel.setPreferredSize(new Dimension(800, 200));
		southPanel.add(f2Next).setPreferredSize(new Dimension(200, 100));
		southPanel.add(answerCheckLabel).setPreferredSize(new Dimension(200, 100));

		frame2.add(southPanel, BorderLayout.SOUTH);

		//Create Frame 2
		frame2.pack();
		setSize(800, 800);
		frame2.setVisible(false);
		setTitle("Learn German!");
	}
	
	
	/**
	 * Shows the next word after clicking the next button
	 * Show frame1 (Terms & Translations)
	 * Hide frame2 (Quiz Pages)
	 * 
	 * @param index	int
	 */
	private void showNextWord(int index) {
		Words word = words.get(index);
		englishLabel.setText(word.getEnglish());
		germanLabel.setText(word.getGerman());

		frame1.setVisible(true);
		frame2.setVisible(false);
	}

	
	/**
	 * Shows the next question after clicking the next button
	 * Hide frame1 (Terms & Translations)
	 * Show frame2 (Quiz Pages)
	 * 
	 * @param index	int
	 */
	private void showNextQuestion(int index) {
		Words word = words.get(index);

		questionLabel.setText(word.getEnglish());
		answer1.setText(word.getAnswerOptions()[0]);
		answer2.setText(word.getAnswerOptions()[1]);
		answer3.setText(word.getAnswerOptions()[2]);
		answer4.setText(word.getAnswerOptions()[3]);

		answer1.setEnabled(true);
		answer2.setEnabled(true);
		answer3.setEnabled(true);
		answer4.setEnabled(true);

		answerCheckLabel.setText("");

		frame2.setVisible(true);
		frame1.setVisible(false);
	}

	
	/** 
	 * 
	 * If the user clicks on the frame1 next button, it shows the next term or goes on to the quiz
	 * If the user clicks on the frame2 next button, it shows the next quiz question or goes on to the next 2 terms
	 * If the user clicks on the back button, it takes the user back one page
	 * 
	 * If the user selects the correct answer, add 1 to score and total questions asked
	 * If the user selects the wrong answer, only add one to total questions asked
	 * 
	 * @param e ActionEvent
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == f1Next) {
			currentShowIndex += 1;
			if (currentShowIndex >= words.size()) {
				currentShowIndex = 0;
			}
			if (frame1ShowCount < 2) {
				frame1ShowCount += 1;
				showNextWord(currentShowIndex);
			} 
			else {
				frame1ShowCount = 0;
				frame2ShowCount = 1;
				showNextQuestion(currentQuestionIndex);
			}

		}
		else if (e.getSource() == f2Next) {
			currentQuestionIndex += 1;
			if (currentQuestionIndex >= words.size()) {
				currentQuestionIndex = 0;
			}
			if (frame2ShowCount < 2) {
				frame2ShowCount += 1;
				showNextQuestion(currentQuestionIndex);
			} else {
				frame2ShowCount = 0;
				frame1ShowCount = 1;
				showNextWord(currentShowIndex);
			}
		}

		else if (e.getSource() == back) {
			currentShowIndex -= 1;
			if (currentShowIndex < 0) {
				currentShowIndex = words.size() - 1;
			}
			showNextWord(currentShowIndex);
		}

		//Identify if answer is correct or not
		if (e.getSource() == answer1 || e.getSource() == answer2 || e.getSource() == answer3 || e.getSource() == answer4) {
			JButton button = (JButton) e.getSource();

			//Check correct answer, if correct increase score
			if (button.getText().equals(words.get(currentQuestionIndex).getGerman())) {
				score++;
				totalQuestions++;
				answerCheckLabel.setForeground(java.awt.Color.green);
				answerCheckLabel.setText("Correct");
			} else {
				totalQuestions++;
				answerCheckLabel.setForeground(java.awt.Color.red);
				answerCheckLabel.setText("Incorrect");
			}

			// Disable all buttons so that user do not tap again.
			answer1.setEnabled(false);
			answer2.setEnabled(false);
			answer3.setEnabled(false);
			answer4.setEnabled(false);

			scoreLabel.setText("Score: " + score + "/" + totalQuestions);
		}
	}
	
	
	/**
	 * Runs the application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		LearnGermanGame app = new LearnGermanGame();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	/**
	 * Default Constructor to call frames
	 * 
	 */
	public LearnGermanGame() {
		this.setupFrameOne();
		this.setupFrameTwo();
		this.showNextWord(0);
	}
}
