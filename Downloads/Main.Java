import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	int N = 0;                              //Number of test cases
    int temp = 0;                           //Index in ArrayList
    int j = 0, k = 1;                       //Looping variables
    ArrayList<String> testCases = new ArrayList<>(); // To store input strings

    try(BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
        String Input;
        int count = 0;
        while((Input = in.readLine()) != null&&!Input.isEmpty()) {
            if(count == 0) {
                N = Integer.parseInt(Input);  // Storing number of String pairs
                count = 1;
            }
            else {
                testCases.add(temp++, Input);  //Storing strings in Arraylist
            }
        }

    }
    catch (Exception e) {
        System.out.println("Exception Occured "+e.getMessage());
    }

    for(int i = 0; i < N;i++) {
        stringChecker(testCases.get(j), testCases.get(k));  // For every two input strings calling our string checker method
        j = j+2;
        k = k+2;
    }

    }

    private static void stringChecker(String s, String s1) {
    String s2, s3;
    ArrayList<Character> representation = new ArrayList<>();
    int sLength = s.length();
    s2 = s.intern();        //Obtaining string Canonical form
    s3 = s1.intern();       //Obtaining string Canonical form
    if(s2 == s3) {
        for(int i = 0;  i< sLength;i++)
        {
            representation.add('.');
        }
        showOutput(s, s1, representation);
    }
    else {
        for(int i = 0; i< sLength; i++)
        {
            if(s.charAt(i) == s1.charAt(i))
                representation.add('.');
            else
                representation.add('*');
        }
        showOutput(s, s1, representation);

    }

    }

    private static void showOutput(String s, String s1, ArrayList<Character> representation) {   //Method to display the final output
    System.out.println(s);
    System.out.println(s1);
    for(char c: representation)
    {
        System.out.print(c);
    }
    System.out.println();
    representation.clear();
    }
}
