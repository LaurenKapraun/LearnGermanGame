import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/** 
 * This class creates the lists of German and English Words.
 * 
 * It then generates an ArrayList of random word options for the quiz sections,
 * and sets the matching answer.
 * 
 * @author Lauren Kapraun
 *
 */
public class Words {

	private String english;
	private String german;
	private String[] answerOptions;

	/**
	 * Constructor that accepts values for the English & German words, 
	 * along with the answerOptions
	 * 
	 * @param english 		String
	 * @param german			String
	 * @param answerOptions	String[]
	 */
	public Words(String english, String german, String[] answerOptions) {
		this.english = english;
		this.german = german;
		this.answerOptions = answerOptions;
	}

	/**
	 * Returns the English word
	 * 
	 * @return english
	 */
	public String getEnglish() {
		return english;
	}

	/**
	 * Sets the English word
	 * 
	 * @param english	String
	 */
	public void setEnglish(String english) {
		this.english = english;
	}

	/**
	 * Returns the German word
	 * 
	 * @return german
	 */
	public String getGerman() {
		return german;
	}

	/**
	 * Sets the German word
	 * 
	 * @param german		String
	 */
	public void setGerman(String german) {
		this.german = german;
	}
	
	/**
	 * Returns the answerOptions for the quiz sections
	 * 
	 * @return answerOptions
	 */
	public String[] getAnswerOptions() {
		return answerOptions;
	}

	/** 
	 * Sets the answerOptions for the quiz sections
	 * 
	 * @param answerOptions	String[]
	 */
	public void setAnswerOptions(String[] answerOptions) {
		this.answerOptions = answerOptions;
	}
	
	
	/** 
	 * Method that Creates the Array List of English and German words
	 * and creates an ArrayList for random options for the quiz sections
	 * 
	 * @return words ArrayList that contains the EnglishWords, GermanWords, and Options
	 */
	public static ArrayList<Words> wordLists() {
		//Create List for English Words
		String[] englishWords = {"Yes", "No", "Please", "Thank You", "Hello", "Goodbye", "You", "I", "We", "Me",
				"What", "Here", "While", "Then", "Always", "Again", "Never", "Much", "Time", "Today"};
		//Create List for German Words
		String[] germanWords = {"Ja", "Nein", "Bitte", "Danke", "Hallo", "Auf Wiedersehen", "Du", "Ich", "Wir", "Mich",
				"Was", "Hier", "Als", "Dann", "Immer", "Wieder", "Nie", "Viel", "Zeit", "Heute"};

		ArrayList<Words> words = new ArrayList<>();
		Random rand = new Random();

		for(int i = 0; i < englishWords.length; i++) {
			//Create random option array
			String[] options = new String[4];
			ArrayList<String> availableOptions = new ArrayList<String>(Arrays.asList(germanWords));
			
			//Remove answer from available options
			availableOptions.remove(i);


			for (int j = 0; j < 4; j++) {
				//Create random options
				int randomWords = rand.nextInt(availableOptions.size());
				options[j] = availableOptions.get(randomWords);

				//Remove selected options from available options
				availableOptions.remove(randomWords);
			}

			//Set the correct answer
			int answer = rand.nextInt(4);
			options[answer] = germanWords[i];

			Words w = new Words(englishWords[i], germanWords[i], options);

			words.add(w);
		}

		return words;
	}

}
