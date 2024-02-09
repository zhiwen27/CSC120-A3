import java.util.Scanner;
import java.util.Random;

public class Conversation{
  public static void main(String[] arguments){

    // You will start the conversation here.
    
    // Define string array Canned Response, which records randomly generated answers, and calculate its length
    String[] CannedResp = {"Hmmm...", "I see.", "Alright.", "Okay:)", "Great!"};
    int CannedRespLen = CannedResp.length;

    // Input the rounds of conversation
    System.out.println("Please indicate the rounds of conversation you would prefer:");
    Scanner sc = new Scanner(System.in);
    int ConvNum = sc.nextInt();

    // Define string array TranscriptIn and TranscriptOut to store user's input and bot's output
    // Define string array Transcript to store the whole transcript, and calculate its length
    int TransLen = 2*ConvNum + 2;
    String[] TranscriptIn = new String[ConvNum];
    String[] TranscriptOut = new String[ConvNum];
    String[] Transcript = new String[TransLen];

    // Start conversation!
    System.out.println("Hi there!  What's on your mind?");

    // Save the greeting into the transcript
    Transcript[0] = "Hi there!  What's on your mind?";
    sc.nextLine();  // clear up the scanner as it is not user's input

    for (int i = 0; i < ConvNum; i++){    // Start the rounds of conversation

      // User input
      String UserInput = sc.nextLine();
      // Define Chatbot output
      String BotOutput = "";
      // Define the boolean indicating whether the input needs to be mirrored
      Boolean NeedMirror = false;
      // Save the user input into the input transcript
      TranscriptIn[i] = UserInput;

      // Define a string array to store separate elements of the input string, and calculate its length
      String[] InputStorage = UserInput.split(" ");
      int InputStorLen = InputStorage.length;

      for (int j = 0; j < InputStorLen; j++){    // walk through every element of the string to check mirror conditions

        // Examine mirror conditions, store the changes into the string array and stage whether a mirroring is made

        // Mirroring pronouns
        // "I" -> "You"/"you": if the pronoun exists at the beginning of a sentence, assume it should be capitalized
        if (InputStorage[j].equals("I")){
          if (j == 0){    // Considering capitalization
            InputStorage[j] = InputStorage[j].replace("I", "You");
            NeedMirror = true;
          }
          else{
            InputStorage[j] = InputStorage[j].replace("I", "you");
            NeedMirror = true;
          }
        }
        // "You" -> "I": if the pronoun is capitalized, assume it's the subject of the sentence
        // "you" -> "I"/"me": if the pronoun is not capitalized, assume it's the subject of the sentence if it's at the beginning of
        //                    a sentence, otherwise, assume it's the object of the sentence
        else if (InputStorage[j].equals("You")){
          InputStorage[j] = InputStorage[j].replace("You", "I");
          NeedMirror = true;
        }
        else if (InputStorage[j].equals("you")){
          if (j == 1){
            InputStorage[j] = InputStorage[j].replace("you", "I");
            NeedMirror = true;
          }
          else{
            InputStorage[j] = InputStorage[j].replace("you", "me");
            NeedMirror = true;
          }
        }
        // "me" -> "you"
        else if (InputStorage[j].equals("me")){
          InputStorage[j] = InputStorage[j].replace("me", "you");
          NeedMirror = true;
        }
        // "My" <-> "Your" && "my" <-> "your" 
        else if (InputStorage[j].equals("My")){    // Considering capitalization
          InputStorage[j] = InputStorage[j].replace("My", "Your");
          NeedMirror = true;
        }
        else if (InputStorage[j].equals("Your")){    // Considering capitalization
          InputStorage[j] = InputStorage[j].replace("Your", "My");
          NeedMirror = true;
        }
        else if (InputStorage[j].equals("my")){
          InputStorage[j] = InputStorage[j].replace("my", "your");
          NeedMirror = true;
        }
        else if (InputStorage[j].equals("your")){
          InputStorage[j] = InputStorage[j].replace("your", "my");
          NeedMirror = true;
        }
        // "Mine" <-> "Yours" && "mine" <-> "yours"
        else if (InputStorage[j].equals("Mine")){    // Considering capitalization
          InputStorage[j] = InputStorage[j].replace("Mine", "Yours");
          NeedMirror = true;
        }
        else if (InputStorage[j].equals("Yours")){    // Considering capitalization
          InputStorage[j] = InputStorage[j].replace("Yours", "Mine");
          NeedMirror = true;
        }
        else if (InputStorage[j].equals("mine")){
          InputStorage[j] = InputStorage[j].replace("mine", "yours");
          NeedMirror = true;
        }
        else if (InputStorage[j].equals("yours")){
          InputStorage[j] = InputStorage[j].replace("yours", "mine");
          NeedMirror = true;
        }

        // Mirroring linking verbs
        // "Am" <-> "Are" && "am" <-> "are"
        else if (InputStorage[j].equals("Am")){    // Considering capitalization
          InputStorage[j] = InputStorage[j].replace("Am", "Are");
          NeedMirror = true;
        }
        else if (InputStorage[j].equals("Are")){    // Considering capitalization
          InputStorage[j] = InputStorage[j].replace("Are", "Am");
          NeedMirror = true;
        }
        else if (InputStorage[j].equals("am")){
          InputStorage[j] = InputStorage[j].replace("am", "are");
          NeedMirror = true;
        }
        else if (InputStorage[j].equals("are")){
          InputStorage[j] = InputStorage[j].replace("are", "am");
          NeedMirror = true;
        }

        // Mirroring conditions with abbreviations in punctuations
        // "You're"/"you're" <-> "I'm"
        else if (InputStorage[j].equals("You're")){    // Considering capitalization
          InputStorage[j] = InputStorage[j].replace("You're", "I'm");
          NeedMirror = true;
        }
        else if (InputStorage[j].equals("you're")){
          InputStorage[j] = InputStorage[j].replace("you're", "I'm");
          NeedMirror = true;
        }
        else if (InputStorage[j].equals("I'm")){
          if (j == 0){    // Considering capitalization
            InputStorage[j] = InputStorage[j].replace("I'm", "You're");
            NeedMirror = true;
          }
          else{
            InputStorage[j] = InputStorage[j].replace("I'm", "you're");
            NeedMirror = true;
          }
        }

        // Mirroring punctuations
        else if (InputStorage[j].contains(".")){
          if (InputStorage[j].equals("you.")){    // check if mirror conditions occur before punctuations
            InputStorage[j] = InputStorage[j].replace("you.", "me?");
            NeedMirror = true;
          }
          else if (InputStorage[j].equals("me.")){
            InputStorage[j] = InputStorage[j].replace("me.", "you?");
            NeedMirror = true;
          }
          else if (InputStorage[j].equals("mine.")){
            InputStorage[j] = InputStorage[j].replace("mine.", "yours?");
            NeedMirror = true;
          }
          else if (InputStorage[j].equals("yours.")){
            InputStorage[j] = InputStorage[j].replace("yours.", "mine?");
            NeedMirror = true;
          }
          else if (InputStorage[j].equals("am.")){
            InputStorage[j] = InputStorage[j].replace("am.", "are?");
            NeedMirror = true;
          }
          else if (InputStorage[j].equals("are.")){
            InputStorage[j] = InputStorage[j].replace("are.", "am?");
            NeedMirror = true;
          }
          else{
            InputStorage[j] = InputStorage[j].replace(".", "?");
          }
        }
        else if (InputStorage[j].contains("!")){
          if (InputStorage[j].equals("you!")){    // check if mirror conditions occur before punctuations
            InputStorage[j] = InputStorage[j].replace("you!", "me?");
            NeedMirror = true;
          }
          else if (InputStorage[j].equals("me!")){
            InputStorage[j] = InputStorage[j].replace("me!", "you?");
            NeedMirror = true;
          }
          else if (InputStorage[j].equals("mine!")){
            InputStorage[j] = InputStorage[j].replace("mine!", "yours?");
            NeedMirror = true;
          }
          else if (InputStorage[j].equals("yours!")){
            InputStorage[j] = InputStorage[j].replace("yours!", "mine?");
            NeedMirror = true;
          }
          else{
            InputStorage[j] = InputStorage[j].replace("!", "?");
          }
        }
        else if (InputStorage[j].contains("?")){
          if (InputStorage[j].equals("you?")){    // check if mirror conditions occur before punctuations
            if ((InputStorage[0].equals("Am") || InputStorage[0].equals("Are"))){    // check if linking verbs are at the front
              InputStorage[j] = InputStorage[j].replace("you?", "me?");
              NeedMirror = true;
            }
            else{
              InputStorage[j] = InputStorage[j].replace("you?", "me.");
              NeedMirror = true;
            }
          }
          else if (InputStorage[j].equals("me?")){
            if((InputStorage[0].equals("Am") || InputStorage[0].equals("Are"))){
              InputStorage[j] = InputStorage[j].replace("me?", "you?");
              NeedMirror = true;
            }
            else{
              InputStorage[j] = InputStorage[j].replace("me?", "you.");
              NeedMirror = true;
            }
          }
          else if (InputStorage[j].equals("mine?")){
            if((InputStorage[0].equals("Am") || InputStorage[0].equals("Are"))){
              InputStorage[j] = InputStorage[j].replace("mine?", "yours?");
              NeedMirror = true;
            }
            else{
              InputStorage[j] = InputStorage[j].replace("mine?", "yours.");
              NeedMirror = true;
            }
          }
          else if (InputStorage[j].equals("yours?")){
            if((InputStorage[0].equals("Am") || InputStorage[0].equals("Are"))){
              InputStorage[j] = InputStorage[j].replace("yours?", "mine?");
              NeedMirror = true;
            }
            else{
              InputStorage[j] = InputStorage[j].replace("yours?", "mine.");
              NeedMirror = true;
            }
          }
          if ((InputStorage[0].equals("Am") || InputStorage[0].equals("Are"))){    // do not mirror it if the linking verbs are at the front
            continue;
          }
          else{
            InputStorage[j] = InputStorage[j].replace("?", ".");
          }
        }
      }

      // If nothing needs to be mirrored, generate random answer from Canned Response!
      if (NeedMirror == false){
          Random random = new Random();
          int RandomCan = random.nextInt(CannedRespLen);
          BotOutput = CannedResp[RandomCan];
      }
      // Else, output the mirrored input message!
      else{
        for (int j = 0; j < InputStorLen; j++){
          BotOutput += InputStorage[j] + " ";
        }
      }
      // Save the chatbot output into the output transcript
      TranscriptOut[i] = BotOutput;
      // Chatbot output
      System.out.println(BotOutput);
    }

    // Say goodbye!
    System.out.println("You're running out of conversations...See you next time!");
    // Save the farewell into the transcript
    Transcript[TransLen - 1] = "You're running out of conversations...See you next time!";

    // Combine user's input transcript and chatbot's output transcript into the whole transcript
    int LoopCntIn = 0;
    for (int i = 1; i < TransLen-1; i+=2){
      int IndexIn = LoopCntIn;
      Transcript[i] = TranscriptIn[IndexIn];
      LoopCntIn++;
    }
    int LoopCntOut = 0;
    for (int i = 2; i <= TransLen-1; i+=2){
      int IndexOut = LoopCntOut;
      Transcript[i] = TranscriptOut[IndexOut];
      LoopCntOut++;
    }

    // Print out the transcript!
    System.out.println("Transcript:");
    for (int i = 0; i < TransLen; i++){
      System.out.println(Transcript[i]);
    }
    sc.close();
  }
}