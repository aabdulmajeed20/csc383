/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//import sun.misc.Queue;

public class NFAToDFA {

    public static void main(String[] args) {

        String startingState = "1"; // Manually set the starting state. May or may not change in the future :3
        String currentState;
        String lineNFA;
        String[] splitLineNFA;
        HashMap <String, String> originalNFA = new HashMap <>();
        LinkedList<String> DFAstates = new LinkedList<String>();
        List <String> newDFAstates = new ArrayList <>();

        try {
            /*Empties the output file*/
            PrintWriter writer = new PrintWriter("DFA_output.txt");
            writer.print("");
            writer.close();

            /*Opens text file to read from it*/
            BufferedReader inputNFA = new BufferedReader(new FileReader("NFA_input.txt"));
            /*Reads the first line*/
            lineNFA = inputNFA.readLine();

            /*Splits the  line from input file according to the character
             * Uses the first part as "State" and second part as "Input"
             * State is "key" and Input is "value" in the HashMap
             * Continues reading line until ends of file reached*/
            while (lineNFA != null) {
                splitLineNFA = lineNFA.split("->");
                originalNFA.put(splitLineNFA[0], splitLineNFA[1]);
                lineNFA = inputNFA.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*Enqueues the starting state
        * This queue stores all the possible states*/
        DFAstates.insert(startingState);
        while (!DFAstates.empty()) {
            try {
                /*Dequeues the elements one by one to start the Conversion process
                * The queue elements are the possible states
                * Multiple states are separated by comma "," so we split the states using comma as parameter*/
                currentState = DFAstates.retrieve();
                String value_0 = "";
                String value_1 = "";
                String individualStates[] = currentState.split(",");

                 /*Each State is used as the key to determine the destination states
                 * The "Input" of the DFA or the "value" of hashmap retrieved
                 * Considered only two possible inputs
                 * Destination state of the inputs are separated by "|"*/

                for (int i = 0; i < individualStates.length; i++) {
                    String values = originalNFA.get(individualStates[i]);
                    String[] value = values.split("\\|");
                    value_0 = value_0 + "," + value[0];
                    value_1 = value_1 + "," + value[1];
                }

                /*Extra comma is removed from the strings*/
                value_0 = value_0.substring(1);
                value_1 = value_1.substring(1);

                /*Function call to return unique destination state set*/
                value_0 = uniqueState(value_0);
                value_1 = uniqueState(value_1);

                /**Checks if value_0 and value_1 are new states
                 * If yes then enqueue into DFAstates
                 * Else does nothing*/
                if (!newDFAstates.contains(value_0)) {
                    newDFAstates.add(value_0);
                    DFAstates.insert(value_0);
                }

                if (!newDFAstates.contains(value_1)) {
                    newDFAstates.add(value_1);
                    DFAstates.insert(value_1);
                }
                /*Constructs a new DFA State->Input set*/
                String newDfaLineConstruct = currentState + "->" + value_0 + "|" + value_1;

                /*Writes to file*/
                BufferedWriter outputDFA = new BufferedWriter(new FileWriter("DFA_output.txt",true));
                outputDFA.write(newDfaLineConstruct+"\n");
                outputDFA.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*Function to return a set of unique states
    * It also sorts the set */
    public static String uniqueState(String input){
        String output="";
        /*Splits the input string into individual states*/
        String[] splitInput = input.split(",");
        List <String> uniqueInput = new ArrayList <>();

        /*Adds states to the list if the state in unique in this list*/
        for (int i = 0; i < splitInput.length; i++){
            if (!uniqueInput.contains(splitInput[i])){
                uniqueInput.add(splitInput[i]);
            }
        }
        /*Sorting*/
        java.util.Collections.sort(uniqueInput);
        /*Prepares the return string*/
        for (int i = 0; i < uniqueInput.size(); i++){
            output = output + "," + uniqueInput.get(i);
        }
        /*Removes the extra comma */
        output = output.substring(1);
        return output;
    }
}
