import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_face.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;
import static org.bytedeco.javacpp.opencv_objdetect.*;

import java.io.File;
import java.nio.IntBuffer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;
import javax.swing.*;

import org.bytedeco.javacpp.opencv_imgcodecs;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;



public class Example {

    public static void main(String[] args) throws Exception, InterruptedException {
        //准备两个人的训练图片，每个人脸十张
        //需要注意，训练的图片必须是相同大小的灰度图
       /* String userName = "dk";

        //读取图片保存到mat
        Mat s1 = opencv_imgcodecs.imread("D:\\bisheruanjian\\testpic\\train\\1_dk_1.jpg", 0);


        Mat s2 = opencv_imgcodecs.imread("D:\\bisheruanjian\\testpic\\train\\1_dk_2.jpg", 0);


        Mat s3 = opencv_imgcodecs.imread("D:\\bisheruanjian\\testpic\\train\\1_dk_3.jpg", 0);

        Mat s4 = opencv_imgcodecs.imread("D:\\bisheruanjian\\testpic\\train\\1_dk_4.jpg", 0);

        Mat s5 = opencv_imgcodecs.imread("D:\\bisheruanjian\\testpic\\train\\1_dk_5.jpg", 0);

        //前面的参数是原图 ，后面的参数是大小改变过的图
        MatVector images = new MatVector(5);




        //MatVector images = new MatVector(6);//一共20个训练样本
        Mat lables = new Mat(5, 1, CV_32SC1);//对应20个标签值
        //写入标签值，前十个为1，后十个为2
        IntBuffer lablesBuf = lables.createBuffer();
        lablesBuf.put(0, 1);
        lablesBuf.put(1, 1);
        lablesBuf.put(2, 1);
        lablesBuf.put(3, 1);
        lablesBuf.put(4, 1);



        //写入图片
        images.put(0, s1);
        images.put(1, s2);
        images.put(2, s3);
        images.put(3, s4);
        images.put(4, s5);*/


        //以下读取一个文件夹的所有图片

        //读取结束



        File file=new File("D:\\bisheruanjian\\testpic\\train");
        String[] imgname=file.list();
        MatVector images = new MatVector(imgname.length);//
        for (int i=0;i<imgname.length;i++){
            Mat s1 = opencv_imgcodecs.imread("D:\\bisheruanjian\\testpic\\train\\"+imgname[i]+"", 0);
            images.put(i, s1);
        }
        //读取结束


        //给一个lables类型的图片
        Mat lables = new Mat(imgname.length, 1, CV_32SC1);//对应20个标签值

        //写入标签值
        IntBuffer lablesBuf = lables.createBuffer();

        for (int i=0;i<imgname.length;i++){
            String[] s=imgname[i].split("_");
            int q=Integer.parseInt(s[0]);
            lablesBuf.put(i, q);
        }


        //创建人脸分类器，有Fisher、Eigen、LBPH，选哪种自己决定，这里使用FisherFaceRecognizer
       //FaceRecognizer fr = FisherFaceRecognizer.create();
        FaceRecognizer fr = LBPHFaceRecognizer.create();
        //训练
        fr.train(images, lables);

        //保存训练结果
        fr.save("D:\\bisheruanjian\\LBPHFaceRecognize.xml");

        //读取训练出的xml文件
        fr.read("D:\\bisheruanjian\\LBPHFaceRecognize.xml");
        //设置阈值，阈值为0则任何人都不认识，阈值特别大的时候任何人都认识（返回和样本最相似的结果，永远不会返回-1）
        //前面忘记说了，检测返回-1代表不能和训练结果匹配
        fr.setThreshold(80.0);



        //*********************测试部分************************


                //开启摄像头，获取图像（得到的图像为frame类型，需要转换为mat类型进行检测和识别）
                OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
                grabber.setImageWidth(640);
                grabber.setImageHeight(480);
                grabber.start();

                CanvasFrame canvas = new CanvasFrame("人脸检测");//新建一个窗口
                canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                int k = 1;
                while (true) {
                    if (!canvas.isEnabled()) {//窗口是否关闭
                        grabber.stop();//停止抓取
                        System.exit(0);//退出
                    }
                    Frame frame = grabber.grab();

                    OpenCVFrameConverter.ToMat convertor = new OpenCVFrameConverter.ToMat();//用于类型转换
                    Mat scr = convertor.convertToMat(frame);//将获取的frame转化成mat数据类型         将获取到的图片保存为scr
                    Mat grayscr = new Mat();//灰度图
                    Mat face = new Mat();//人脸
                    Mat roi = new Mat();//
                    cvtColor(scr, grayscr, COLOR_BGRA2GRAY);//摄像头是彩色图像，所以先灰度化下   将图片存为灰度图保存为grayscr
                    equalizeHist(grayscr, grayscr);//均衡化直方图

                    //读取opencv人脸检测器，参考我的路径改为自己的路径
                    CascadeClassifier cascade = new CascadeClassifier(
                            "D:\\bisheruanjian\\opencv4\\opencv\\sources\\data\\haarcascades\\haarcascade_frontalface_alt.xml");
                    //检测人脸
                    RectVector faces = new RectVector(); //人脸对应序列
                    cascade.detectMultiScale(grayscr, faces);//用检测器检测人脸

                    IntPointer label = new IntPointer(1);
                    DoublePointer confidence = new DoublePointer(1);

                    //识别人脸
                    for (int i = 0; i < faces.size(); i++) {
                        Rect face_i = faces.get(i);
                        rectangle(scr, face_i, new Scalar(0, 255, 0, 1));
                        roi = new Mat(grayscr, face_i);
                        resize(roi, face, new Size(350, 350));//我的训练样本是350*350，要对应的进行修改
                        fr.predict(face, label, confidence);//预测face 图像是什么
                        int predictedLabel = label.get(0);//得到识别的标签值
                        System.out.println(faces.size());

                        /*if (k < 6) {
                            opencv_imgcodecs.imwrite("D:\\bisheruanjian\\testpic\\" + userName + "_" + k + ".jpg", face);
                            k++;
                        }*/

                        //判断并显示
                        if (predictedLabel == 1) {
                            String box_text = "people:DK1";
                            int pos_x = Math.max(face_i.tl().x() - 10, 0);
                            int pos_y = Math.max(face_i.tl().y() - 10, 0);
                            putText(scr, box_text, new Point(pos_x, pos_y),
                                    FONT_HERSHEY_PLAIN, 1.0, new Scalar(0, 255, 0, 2.0));
                        } else if (predictedLabel == 2) {
                            int pos_x = Math.max(face_i.tl().x() - 10, 0);
                            int pos_y = Math.max(face_i.tl().y() - 10, 0);
                            // And now put it into the image:
                            putText(scr, "people:DAH", new Point(pos_x, pos_y),
                                    FONT_HERSHEY_PLAIN, 1.0, new Scalar(0, 255, 0, 2.0));
                        } else {
                            int pos_x = Math.max(face_i.tl().x() - 10, 0);
                            int pos_y = Math.max(face_i.tl().y() - 10, 0);
                            // And now put it into the image:
                            putText(scr, "UnknownPeople!", new Point(pos_x, pos_y),
                                    FONT_HERSHEY_PLAIN, 1.0, new Scalar(0, 0, 255, 2.0));
                        }
                    }
                    //显示
                    frame = convertor.convert(scr);//将检测结果重新的mat重新转化为frame
                    canvas.showImage(frame);//获取摄像头图像并放到窗口上显示，frame是一帧视频图像
                    Thread.sleep(20);//20毫秒刷新一次图像
                }
    }
}