
import java.io.*;
import java.lang.*;
import java.util.*;

class Calculation {
    String key = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    int[][] km = new int[][] { { 1, 2, 1 }, { 2, 3, 2 }, { 2, 2, 1 } };
    int[][] ikm = new int[][] { { -1, 0, 1 }, { 2, -1, 0 }, { -2, 2, -1 } };

    public String encoding(char a, char b, char c) {
        String res;
        int x, y, z, posa, posb, posc;
        posa = (int) a - 65;
        posb = (int) b - 65;
        posc = (int) c - 65;
        x = posa * km[0][0] + posb * km[1][0] + posc * km[2][0];
        y = posa * km[0][1] + posb * km[1][1] + posc * km[2][1];
        z = posa * km[0][2] + posb * km[1][2] + posc * km[2][2];
        a = key.charAt(x % 26);
        b = key.charAt(y % 26);
        c = key.charAt(z % 26);
        res = "" + a + b + c;
        return res;
    }

    public String decoding(char a, char b, char c) {
        String res;
        int x, y, z, posa, posb, posc;
        posa = (int) a - 65;
        posb = (int) b - 65;
        posc = (int) c - 65;
        x = posa * ikm[0][0] + posb * ikm[1][0] + posc * ikm[2][0];
        y = posa * ikm[0][1] + posb * ikm[1][1] + posc * ikm[2][1];
        z = posa * ikm[0][2] + posb * ikm[1][2] + posc * ikm[2][2];
        a = key.charAt(x % 26 < 0 ? 26 + (x % 26) : x % 26);
        b = key.charAt(y % 26 < 0 ? 26 + (y % 26) : y % 26);
        c = key.charAt(z % 26 < 0 ? 26 + (z % 26) : z % 26);
        res = "" + a + b + c;
        return res;
    }
}

class HillCipher {
    public static void main(String args[]) throws Exception {
        String msg, enc = "", dec = "";
        StringBuffer pt;
        int n;
        Calculation cc = new Calculation();
        DataInputStream din = new DataInputStream(System.in);
        System.out.print("Enter the message :\n");
        pt = new StringBuffer(din.readLine());
        msg = pt.toString();
        msg = msg.toUpperCase();
        msg = msg.replaceAll("//s", "");
        n = msg.length() % 3;
        if (n != 0) {
            for (int i = 1; i <= (3 - n); i++) {
                msg += "x";
            }
        }
        char[] tempPT = msg.toCharArray();
        for (int i = 0; i < msg.length(); i += 3) {
            enc = enc + cc.encoding(tempPT[i], tempPT[i + 1], tempPT[i + 2]);
        }

        char[] tempET = enc.toCharArray();
        for (int i = 0; i < enc.length(); i += 3) {
            dec = dec + cc.decoding(tempET[i], tempET[i + 1], tempET[i + 2]);
        }
        System.out.print("--------------HILL CIPHER-----------\n");
        System.out.print("Plain Text : " + msg + "\n");
        System.out.print("--------------Encryption-------------\n");
        System.out.print("Cipher Text : " + enc + "\n");
        System.out.print("-------------Decryption--------------\n");
        System.out.print("Decrypt Text : " + dec + "\n");
    }
}