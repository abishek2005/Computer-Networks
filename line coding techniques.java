import java.util.Scanner;
public class Linecoding {   

///UNIPOLAR///

static void unipolarNRZ(int[] data) {
    System.out.println("Unipolar NRZ Encoding:");
    for (int bit : data) {
        if (bit == 1) System.out.print("|-| ");
        else System.out.print("|__| ");
    }
    System.out.println();
}

///POLAR///

static void polarNRZ(int[] data) {
    System.out.println("Polar NRZ Encoding:");
    for (int bit : data) {
        if (bit == 1) System.out.print("|-| ");
        else System.out.print("|__| ");
    }
    System.out.println();
}

///MANCHESTER///

static void manchester(int[] data) {
    System.out.println("Manchester Encoding:");
    for (int bit : data) {
        if (bit == 1) System.out.print("|__-| ");
        else System.out.print("|-__| ");
    }
    System.out.println();
}

//DIFFERENTIAL MANCHESTER///

static void differentialManchester(int[] data) {
    System.out.println("Differential Manchester Encoding:");
    int lastTransition = 1; // Assume initial transition is high
    for (int bit : data) {
        if (bit == 0) {
            if (lastTransition == 1) System.out.print("|__-| ");
            else System.out.print("|-__| ");
            lastTransition = -lastTransition; // Toggle transition
        } else {
            if (lastTransition == 1) System.out.print("|-__| ");
            else System.out.print("|__-| ");
            lastTransition = -lastTransition; // Toggle transition
        }
    }
    System.out.println();
}
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    // Input: Digital Data
    System.out.println("Enter the number of bits in the data:");
    int n = sc.nextInt();
    int[] data = new int[n];
    System.out.println("Enter the binary data bits (0s and 1s):");
    for (int i = 0; i < n; i++) {
        data[i] = sc.nextInt();
    }
    unipolarNRZ(data);    
    polarNRZ(data);
    manchester(data);
    differentialManchester(data);  
    sc.close();
}
}
