public class DiffieeHellman {
    public static void main(String args[]){
        int p=23;
        int g=5;
        int x=4;
        int y=3;
        double aliceSends=(Math.pow(g, x))%p;
        double bobComputes = (Math.pow(aliceSends, y))%p;
        double bobSends = (Math.pow(g,y))%p;
        double aliceComputes=(Math.pow(bobSends, x))%p;
        double sharedSecret = (Math.pow(g, (x*y)))%p;

        System.out.println("Simulation of Diffie- Hellmankey exchange algorithm\n");
        System.out.println("Alice Sends: "+aliceSends);
        System.out.println("Bob Computes: "+bobComputes);
        System.out.println("Bob Sends: "+bobSends);
        System.out.println("Alice Computes: "+aliceComputes);
        System.out.println("Shared Secret: "+sharedSecret);

        if((aliceComputes == sharedSecret) && (aliceComputes==bobComputes)){
            System.out.println("Success : shared secret Matches! "+sharedSecret);
        }else{
            System.out.println("Error! shared secret does not match ");
        }
    }
}
