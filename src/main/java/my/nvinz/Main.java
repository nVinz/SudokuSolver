package my.nvinz;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Solver solver = new Solver();

        /*if (solver.solve()) {
            System.out.println("> Solved");
            solver.print();
        } else {
            System.out.println("> Unsolvable");
        }*/


        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        VideoCapture capture = new VideoCapture(0);
        Mat matrix = new Mat();
        capture.read(matrix);

    }
}
