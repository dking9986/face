import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.opencv_imgcodecs;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;

import javax.swing.*;
import java.io.File;
import java.nio.IntBuffer;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_face.FaceRecognizer;
import static org.bytedeco.javacpp.opencv_face.LBPHFaceRecognizer;
import static org.bytedeco.javacpp.opencv_imgproc.*;
import static org.bytedeco.javacpp.opencv_objdetect.CascadeClassifier;

public class FaceRecog  {//用作所有人脸识别功能类


    public void getFace(String account,int usernum,String username,int facescount) throws Exception, Exception, InterruptedException {   //人脸获取并保存在文件夹中 每个人保存五张图
        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
        grabber.setImageWidth(640);
        grabber.setImageHeight(480);
        grabber.start();

        CanvasFrame canvas = new CanvasFrame("人脸录入");//新建一个窗口
        canvas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        int k = 1;//计数器
        while (true) {
            if(k==21+facescount){
                canvas.dispose();
            }
            if (!canvas.isShowing()) {//窗口是否关闭
                grabber.stop();//停止抓取
                return;

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
            CascadeClassifier cascade = new CascadeClassifier("D:\\bisheruanjian\\opencv4\\opencv\\sources\\data\\haarcascades\\haarcascade_frontalface_alt.xml");
            //检测人脸
            RectVector faces = new RectVector(); //人脸对应序列
            cascade.detectMultiScale(grayscr, faces);//用检测器检测人脸
            for (int i = 0; i < faces.size(); i++) {
                Rect face_i = faces.get(i);
                rectangle(scr, face_i, new Scalar(0, 255, 0, 1));
                roi = new Mat(grayscr, face_i);
                resize(roi, face, new Size(350, 350));//我的训练样本是350*350，要对应的进行修改

                if (k>20&&k<facescount+21 ) {//400ms之后再开始录入 给一点时间准备 facescount是要保存的图片数量
                    int t=k-20;
                    opencv_imgcodecs.imwrite("D:\\bisheruanjian\\testpic\\train\\"+usernum+"_" + username + "_" + t + ".jpg", face);//保存对应图片 共五张图

                }
                k++;
                //判断并显示

                    String box_text = "people:"+username;
                    int pos_x = Math.max(face_i.tl().x() - 10, 0);
                    int pos_y = Math.max(face_i.tl().y() - 10, 0);
                    putText(scr, box_text, new Point(pos_x, pos_y), FONT_HERSHEY_PLAIN, 1.0, new Scalar(0, 255, 0, 2.0));

            }
            //显示
            frame = convertor.convert(scr);//将检测结果重新的mat重新转化为frame
            canvas.showImage(frame);//获取摄像头图像并放到窗口上显示，frame是一帧视频图像
            Thread.sleep(20);//20毫秒刷新一次图像
        }

    }

    public void faceTrain(){//得到训练集
        //以下读取一个文件夹的所有图片
        File file=new File("D:\\bisheruanjian\\testpic\\train");
        String[] imgname=file.list();
        MatVector images = new MatVector(imgname.length);//

        Mat labels = new Mat(imgname.length, 1, CV_32SC1);
        //写入标签值
        IntBuffer lablesBuf = labels.createBuffer();
        for (int i=0;i<imgname.length;i++){
            //读取图片
            Mat mat = opencv_imgcodecs.imread("D:\\bisheruanjian\\testpic\\train\\"+imgname[i]+"", 0);
            images.put(i, mat);

            //写入标签
            String[] s=imgname[i].split("_");
            int q=Integer.parseInt(s[0]);
            lablesBuf.put(i, q);
        }

        for (int i=0;i<imgname.length;i++){

        }


        //创建人脸分类器，有Fisher、Eigen、LBPH，选哪种自己决定，这里使用FisherFaceRecognizer
        FaceRecognizer fr = LBPHFaceRecognizer.create();
        //训练
        fr.train(images, labels);//第一个参数是一个mat集合 第二个参数是一个mat图存对应的编号

        //保存训练结果
        fr.save("D:\\bisheruanjian\\testpic\\LBPHFaceRecognize.xml");
    }



    public void faceRec(long intervaltime) throws java.lang.Exception {//人脸识别运行部分


        FaceRecognizer fr = LBPHFaceRecognizer.create();
        fr.read("D:\\bisheruanjian\\testpic\\LBPHFaceRecognize.xml");
        //设置阈值
        fr.setThreshold(65.0);

        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
        grabber.setImageWidth(640);
        grabber.setImageHeight(480);
        grabber.start();

        CanvasFrame canvas = new CanvasFrame("人脸检测");//新建一个窗口
        canvas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        String[] namebefore=new String[5];
        int k=0;//计数器 计数连续人脸个数
        while (true) {

            if (!canvas.isShowing()) {//窗口是否关闭
                grabber.stop();//停止抓取
                return;
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
            CascadeClassifier cascade = new CascadeClassifier("D:\\bisheruanjian\\opencv4\\opencv\\sources\\data\\haarcascades\\haarcascade_frontalface_alt.xml");
            //检测人脸
            RectVector faces = new RectVector(); //人脸对应矩形序列
            cascade.detectMultiScale(grayscr, faces);//用检测器检测人脸 对应到矩形序列上

            IntPointer label = new IntPointer(1);
            DoublePointer confidence = new DoublePointer(1);


            //识别人脸
            for (int i = 0; i < faces.size(); i++) {
                Rect face_i = faces.get(i);
                rectangle(scr, face_i, new Scalar(0, 255, 0, 1));//画框
                roi = new Mat(grayscr, face_i);//将灰度图和矩形相对应
                resize(roi, face, new Size(350, 350));//   输入：改之前的图像  输出：改完的图像 样本大小
                fr.predict(face, label, confidence);//预测face 图像是什么 保存至label中
                int predictresult = label.get(0);//得到识别结果

                String name=new FaceRecord().getName(predictresult);//通过识别结果找人名

                if (name.equals("UnknownPeople")) {//如果没有此人脸信息
                    int pos_x = Math.max(face_i.tl().x() - 10, 0);
                    int pos_y = Math.max(face_i.tl().y() - 10, 0);
                    putText(scr, name, new Point(pos_x, pos_y), FONT_HERSHEY_PLAIN, 1.0, new Scalar(0, 0, 255, 2.0));

                } else {
                    //有该人脸信息
                    namebefore[k]=name;
                    k++;
                    int pos_x = Math.max(face_i.tl().x() - 10, 0);
                    int pos_y = Math.max(face_i.tl().y() - 10, 0);
                    putText(scr, name, new Point(pos_x, pos_y), FONT_HERSHEY_PLAIN, 1.0, new Scalar(0, 255, 0, 2.0));
                    //连续5次判断都是这个人的时候记录 否则重新判断
                    if (k==5&&namebefore[0].equals(namebefore[1])&&namebefore[1].equals(namebefore[2])&&namebefore[2].equals(namebefore[3])&&namebefore[3].equals(namebefore[4])){
                        new FaceRecord().recTrip(predictresult,intervaltime);//记录
                        k=0;
                    }else if (k==5){
                        k=0;
                    }

                }

            }

            //显示
            frame = convertor.convert(scr);//将检测结果重新的mat重新转化为frame
            canvas.showImage(frame);//获取摄像头图像并放到窗口上显示，frame是一帧视频图像
            Thread.sleep(20);//20毫秒刷新一次图像
        }
    }
}
