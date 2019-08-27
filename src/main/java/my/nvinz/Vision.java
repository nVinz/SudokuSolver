package my.nvinz;

import org.bytedeco.javacv.*;
import org.bytedeco.opencv.opencv_core.CvScalar;
import org.bytedeco.opencv.opencv_core.CvSeq;
import org.bytedeco.opencv.opencv_core.IplImage;

import static org.bytedeco.opencv.global.opencv_core.cvScalar;
import static org.bytedeco.opencv.global.opencv_imgproc.cvCvtColor;

public class Vision {

    OpenCVFrameGrabber grabber;
    CvScalar minc = cvScalar(0,0,0,0), maxc = cvScalar(0,0,0,0);
    CvSeq contour1 = new CvSeq(), contour2;

    public Vision() {
        grabber = new OpenCVFrameGrabber(0);
    }

    public void start(){
        try {
            grabber.start();
            Frame frame = grabber.grab();

            AndroidFrameConverter converter = new AndroidFrameConverter();
            converter.convert(frame);

            CanvasFrame cframe = new CanvasFrame("Title");
            cframe.setCanvasSize(frame.imageWidth, frame.imageHeight);

            while (cframe.isVisible() && (frame = grabber.grab()) != null){
                cframe.showImage(frame);
            }
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        }
    }
}
