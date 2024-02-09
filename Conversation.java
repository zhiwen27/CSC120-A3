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

    for (int i = 0; i < ConvNum; i++){

      // User input
      String UserInput = sc.nextLine();
      // Define Chatbot output
      String BotOutput = "";
      // Define the boolean to decide whether the input needs to be mirrored
      Boolean NeedMirror = false;
      // Save the user input into the input transcript
      TranscriptIn[i] = UserInput;

      // Define a string array to store separate elements of the input string, and calculate its length
      String[] InputStorage = UserInput.split(" ");
      int InputStorLen = InputStorage.length;

      for (int j = 0; j < InputStorLen; j++){

        // Examine mirror conditions, store changes into the string array and indicate whether a mirroring is made

        // Mirroring pronouns
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
        else if (InputStorage[j].equals("you")){
          InputStorage[j] = InputStorage[j].replace("you", "me");
          NeedMirror = true;
        }
        else if (InputStorage[j].equals("me")){
          InputStorage[j] = InputStorage[j].replace("me", "you");
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
        // Dealing with capitalization
        else if (InputStorage[j].equals("You")){
          InputStorage[j] = InputStorage[j].replace("You", "I");
          NeedMirror = true;
        }
        else if (InputStorage[j].equals("Me")){
          InputStorage[j] = InputStorage[j].replace("Me", "You");
          NeedMirror = true;
        }
        else if (InputStorage[j].equals("My")){
          InputStorage[j] = InputStorage[j].replace("My", "Your");
          NeedMirror = true;
        }
        else if (InputStorage[j].equals("Your")){
          InputStorage[j] = InputStorage[j].replace("Your", "My");
          NeedMirror = true;
        }

        // Mirroring linking verbs
        else if (InputStorage[j].equals("am")){
          InputStorage[j] = InputStorage[j].replace("am", "are");
          NeedMirror = true;
        }
        else if (InputStorage[j].equals("are")){
          InputStorage[j] = InputStorage[j].replace("are", "am");
          NeedMirror = true;
        }
        // Dealing with capitalization
        else if (InputStorage[j].equals("Am")){
          InputStorage[j] = InputStorage[j].replace("Am", "Are");
          NeedMirror = true;
        }
        else if (InputStorage[j].equals("Are")){
          InputStorage[j] = InputStorage[j].replace("Are", "Am");
          NeedMirror = true;
        }

        // Mirroring punctuations
        else if (InputStorage[j].contains(".")){
          InputStorage[j] = InputStorage[j].replace(".", "?");
        }
        else if (InputStorage[j].contains("!")){
          InputStorage[j] = InputStorage[j].replace("!", "?");
        }
        else if (InputStorage[j].contains("?")){
          if (InputStorage[0].equals("Am") || InputStorage[0].equals("Are")){
            continue;
          }
          else{
            InputStorage[j] = InputStorage[j].replace("?", ".");
          }
        }

        // Mirroring conditions with abbreviations in punctuations
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
        // Dealing with capitalization
        else if (InputStorage[j].equals("You're")){
          InputStorage[j] = InputStorage[j].replace("You're", "I'm");
          NeedMirror = true;
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

      // Chatbot output
      System.out.println(BotOutput);
      // Save the chatbot output into the output transcript
      TranscriptOut[i] = BotOutput;
    }

    // Say goodbye!
    System.out.println("You're running out of conversations...See you next time!");
    // Save the farewell into the transcript
    Transcript[TransLen - 1] = "You're running out of conversations...See you next time!";

    // Combine user's input transcript and chatbot's output transcript into the whole transcript
    for (int i = 1; i < TransLen-1; i+=2){
      int IndexIn = 0;
      while(IndexIn < TranscriptIn.length){
        Transcript[i] = TranscriptIn[IndexIn];
        IndexIn++;
      }
    }
    for (int i = 2; i <= TransLen-1; i+=2){
      int IndexOut = 0;
      while(IndexOut < TranscriptIn.length){
        Transcript[i] = TranscriptOut[IndexOut];
        IndexOut++;
      }
    }

    // Print out the transcript!
    System.out.println("Transcript:");
    for (int i = 0; i < TransLen; i++){
      System.out.println(Transcript[i]);
    }
    sc.close();
  }
}