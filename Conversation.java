import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Conversation{
  
  public static void main(String[] arguments){

    // You will start the conversation here.
    
    /*
     * Define cannedResponse, which is the arraylist to store randomly generated answers; start with only 5 random answers
     *        cannedRespLen: the size of the arraylist cannedResponse
     */
    ArrayList<String> cannedResp = new ArrayList<>();
    cannedResp.add("Hmmm...");
    cannedResp.add("I see.");
    cannedResp.add("Alright.");
    cannedResp.add("Okay:)");
    cannedResp.add("Great!");
    int cannedRespLen = cannedResp.size();

    /* Input the rounds of conversation */
    System.out.println("Please indicate the rounds of conversation you would prefer:");
    Scanner sc = new Scanner(System.in);
    int convNum = sc.nextInt();

    /*
     * Define transcriptIn: the transcipt from user's input
     *        transcriptOut: the transcript from Chatbot's output
     *        transcript: the whole transcript combining user's input and chatbot's output
     */
    int transLen = 2 * convNum + 2;
    String[] transcriptIn = new String[convNum];
    String[] transcriptOut = new String[convNum];
    String[] transcript = new String[transLen];

    /* Start conversation! */
    System.out.println("Hi there!  What's on your mind?");

    /* Save the greeting into the transcript */
    transcript[0] = "Hi there!  What's on your mind?";
    sc.nextLine();  // clear up the scanner as it is not user's input

    for (int i = 0; i < convNum; i++){    // Start the rounds of conversation

      /* User input */
      String userInput = sc.nextLine();
      /* Set botOutput to null string as no answers are generated from the beginning */
      String botOutput = "";
      /* Define needMirror, which indicates whether the input needs to be mirrored */
      Boolean needMirror = false;
      /* Save the user input into the input transcript */
      transcriptIn[i] = userInput;

      /*
       *  Define inputStorage: the string array to store separate elements of the input string 
       *         inputStorageOri: aother string array to store unchanged input
       */
      String[] inputStorage = userInput.split(" ");
      String[] inputStorageOri = userInput.split(" ");
      int inputStorLen = inputStorage.length;

      for (int j = 0; j < inputStorLen; j++){    // walk through every element of the string to check mirror conditions

        /* Examine mirror conditions, store the changes into the string array and stage whether a mirroring is made */

        /*
         * Mirroring pronouns
         * "I" -> "You"/"you": if the pronoun exists at the beginning of a sentence, assume it should be capitalized 
         */
        if (inputStorageOri[j].equals("I")){
          if (j == 0){    // Considering capitalization
            inputStorage[j] = inputStorage[j].replace("I", "You");
            needMirror = true;
          }
          else{
            inputStorage[j] = inputStorage[j].replace("I", "you");
            needMirror = true;
          }
        }
        /*
         * "You" -> "I": if the pronoun is capitalized, assume it's the subject of the sentence
         * "you" -> "I"/"me": if the pronoun is not capitalized or if there're linking verbs before the pronoun, assume it's the subject of the sentence
         *                    if it's at the beginning of a sentence, otherwise, assume it's the object of the sentence
         */
        else if (inputStorageOri[j].equals("You")){
          inputStorage[j] = inputStorage[j].replace("You", "I");
          needMirror = true;
        }
        else if (inputStorageOri[j].equals("you")){
          if ((inputStorageOri[j - 1].equals("Are")) || (inputStorageOri[j - 1].equals("are")) || (j == 1)){
            inputStorage[j] = inputStorage[j].replace("you", "I");
            needMirror = true;
          }
          else{
            inputStorage[j] = inputStorage[j].replace("you", "me");
            needMirror = true;
          }
        }
        /* "me" -> "you" */
        else if (inputStorageOri[j].equals("me")){
          inputStorage[j] = inputStorage[j].replace("me", "you");
          needMirror = true;
        }
        /* "My" <-> "Your" && "my" <-> "your"  */
        else if (inputStorageOri[j].equals("My")){    // Considering capitalization
          inputStorage[j] = inputStorage[j].replace("My", "Your");
          needMirror = true;
        }
        else if (inputStorageOri[j].equals("Your")){    // Considering capitalization
          inputStorage[j] = inputStorage[j].replace("Your", "My");
          needMirror = true;
        }
        else if (inputStorageOri[j].equals("my")){
          inputStorage[j] = inputStorage[j].replace("my", "your");
          needMirror = true;
        }
        else if (inputStorageOri[j].equals("your")){
          inputStorage[j] = inputStorage[j].replace("your", "my");
          needMirror = true;
        }
        /* "Mine" <-> "Yours" && "mine" <-> "yours" */
        else if (inputStorageOri[j].equals("Mine")){    // Considering capitalization
          inputStorage[j] = inputStorage[j].replace("Mine", "Yours");
          needMirror = true;
        }
        else if (inputStorageOri[j].equals("Yours")){    // Considering capitalization
          inputStorage[j] = inputStorage[j].replace("Yours", "Mine");
          needMirror = true;
        }
        else if (inputStorageOri[j].equals("mine")){
          inputStorage[j] = inputStorage[j].replace("mine", "yours");
          needMirror = true;
        }
        else if (inputStorageOri[j].equals("yours")){
          inputStorage[j] = inputStorage[j].replace("yours", "mine");
          needMirror = true;
        }

        /*
         * Mirroring linking verbs
         * "Am" <-> "Are" && "am" <-> "are"
         */
        else if (inputStorageOri[j].equals("Am")){    // Considering capitalization
          inputStorage[j] = inputStorage[j].replace("Am", "Are");
          needMirror = true;
        }
        else if (inputStorageOri[j].equals("Are")){    // Considering capitalization
          inputStorage[j] = inputStorage[j].replace("Are", "Am");
          needMirror = true;
        }
        else if (inputStorageOri[j].equals("am")){
          inputStorage[j] = inputStorage[j].replace("am", "are");
          needMirror = true;
        }
        else if (inputStorageOri[j].equals("are")){
          inputStorage[j] = inputStorage[j].replace("are", "am");
          needMirror = true;
        }

        /*
         * Mirroring conditions with abbreviations in punctuations
         * "You're"/"you're" <-> "I'm"
        */
        else if (inputStorageOri[j].equals("You're")){    // Considering capitalization
          inputStorage[j] = inputStorage[j].replace("You're", "I'm");
          needMirror = true;
        }
        else if (inputStorageOri[j].equals("you're")){
          inputStorage[j] = inputStorage[j].replace("you're", "I'm");
          needMirror = true;
        }
        else if (inputStorageOri[j].equals("I'm")){
          if (j == 0){    // Considering capitalization
            inputStorage[j] = inputStorage[j].replace("I'm", "You're");
            needMirror = true;
          }
          else{
            inputStorage[j] = inputStorage[j].replace("I'm", "you're");
            needMirror = true;
          }
        }

        // Mirroring punctuations
        /*
         * "." -> "?"
         * If mirror conditions occur before punctuations: "you." -> "me?" && "me." -> "you?"
         *                                                 "mine." -> "yours?" && "yours." -> "mine?"
         *                                                 "am." -> "are?" && "are." -> "am?"
         */
        else if (inputStorageOri[j].contains(".")){
          if (inputStorage[j].equals("you.")){    // check if mirror conditions occur before punctuations
            inputStorage[j] = inputStorage[j].replace("you.", "me?");
            needMirror = true;
          }
          else if (inputStorageOri[j].equals("me.")){
            inputStorage[j] = inputStorage[j].replace("me.", "you?");
            needMirror = true;
          }
          else if (inputStorageOri[j].equals("mine.")){
            inputStorage[j] = inputStorage[j].replace("mine.", "yours?");
            needMirror = true;
          }
          else if (inputStorageOri[j].equals("yours.")){
            inputStorage[j] = inputStorage[j].replace("yours.", "mine?");
            needMirror = true;
          }
          else if (inputStorageOri[j].equals("am.")){
            inputStorage[j] = inputStorage[j].replace("am.", "are?");
            needMirror = true;
          }
          else if (inputStorageOri[j].equals("are.")){
            inputStorage[j] = inputStorage[j].replace("are.", "am?");
            needMirror = true;
          }
          else{
            inputStorage[j] = inputStorage[j].replace(".", "?");
          }
        }
        /*
         * "!" -> "?"
         * If mirror conditions occur before punctuations: "you!" -> "me?" && "me!" -> "you?"
         *                                                 "mine!" -> "yours?" && "yours!" -> "mine?"
         */
        else if (inputStorageOri[j].contains("!")){
          if (inputStorage[j].equals("you!")){    // check if mirror conditions occur before punctuations
            inputStorage[j] = inputStorage[j].replace("you!", "me?");
            needMirror = true;
          }
          else if (inputStorageOri[j].equals("me!")){
            inputStorage[j] = inputStorage[j].replace("me!", "you?");
            needMirror = true;
          }
          else if (inputStorageOri[j].equals("mine!")){
            inputStorage[j] = inputStorage[j].replace("mine!", "yours?");
            needMirror = true;
          }
          else if (inputStorageOri[j].equals("yours!")){
            inputStorage[j] = inputStorage[j].replace("yours!", "mine?");
            needMirror = true;
          }
          else{
            inputStorage[j] = inputStorage[j].replace("!", "?");
          }
        }
        /*
         * "?" -> "."
         * If mirror conditions occur before punctuations: "you?" -> "me." && "me?" -> "you."
         *                                                 "mine?" -> "yours." && "yours?" -> "mine."
         */
        else if (inputStorageOri[j].contains("?")){
          if (inputStorageOri[j].equals("you?")){    // check if mirror conditions occur before punctuations
            inputStorage[j] = inputStorage[j].replace("you?", "me.");
            needMirror = true;
          }
          else if (inputStorageOri[j].equals("me?")){
            inputStorage[j] = inputStorage[j].replace("me?", "you.");
            needMirror = true;
          }
          else if (inputStorageOri[j].equals("mine?")){
            inputStorage[j] = inputStorage[j].replace("mine?", "yours.");
            needMirror = true;
          }
          else if (inputStorageOri[j].equals("yours?")){
            inputStorage[j] = inputStorage[j].replace("yours?", "mine.");
            needMirror = true;
          }
          if ((inputStorageOri[0].equals("Am") || inputStorageOri[0].equals("Are"))){    // do not mirror if the linking verbs are at the front
            continue;
          }
          else{
            inputStorage[j] = inputStorage[j].replace("?", ".");
          }
        }
      }

      /* If nothing needs to be mirrored, generate random answer from Canned Response! */
      if (needMirror == false){
          Random random = new Random();
          Integer randomCan = random.nextInt(cannedRespLen);
          botOutput = cannedResp.get(randomCan);
      }
      /* Else, output the mirrored input message! */
      else{
        for (int j = 0; j < inputStorLen; j++){
          botOutput += inputStorage[j] + " ";
        }
      }
      /* Save the chatbot output into the output transcript */
      transcriptOut[i] = botOutput;
      /* Chatbot output */
      System.out.println(botOutput);
    }

    /* Say goodbye! */
    System.out.println("You're running out of conversations...See you next time!");
    // Save the farewell into the transcript
    transcript[transLen - 1] = "You're running out of conversations...See you next time!";

    /* Combine user's input transcript and chatbot's output transcript into the whole transcript */
    int loopCntIn = 0;
    for (int i = 1; i < transLen-1; i+=2){
      int indexIn = loopCntIn;
      transcript[i] = transcriptIn[indexIn];
      loopCntIn++;
    }
    int loopCntOut = 0;
    for (int i = 2; i <= transLen-1; i+=2){
      int indexOut = loopCntOut;
      transcript[i] = transcriptOut[indexOut];
      loopCntOut++;
    }

    /* Print out the transcript! */
    System.out.println("transcript:");
    for (int i = 0; i < transLen; i++){
      System.out.println(transcript[i]);
    }
    sc.close();
  }
}