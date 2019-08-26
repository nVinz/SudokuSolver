package my.nvinz;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Solver solver = new Solver();

        if (solver.solve()) {
            System.out.println("> Solved");
            solver.print();
        } else {
            System.out.println("> Unsolvable");
        }
    }
}
