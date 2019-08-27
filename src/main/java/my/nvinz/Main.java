package my.nvinz;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {

        /*Solver solver = new Solver();

        try {
            if (solver.solve()) {
                System.out.println("> Solved");
                solver.print();
            } else {
                System.out.println("> Unsolvable");
            }
        }
        catch (NullPointerException e){
            System.out.println("Null pointer (Main.java): " + e);
        }*/

        Vision vision = new Vision();
        vision.start();
    }
}
