import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static ArrayList<Frame> readFrame(String fileName1,String fileName2){
        ArrayList<Frame> frameList = new ArrayList<>();
        ArrayList<Frame> frameList1 = new ArrayList<>();
        ArrayList<Frame> frameList2 = new ArrayList<>();

        // 读取文件中的数据
        try (BufferedReader br = new BufferedReader(new FileReader(fileName1))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(" ");
                int src = Integer.parseInt(tokens[0]);
                int des = Integer.parseInt(tokens[1]);
                String data = tokens[2];
                frameList1.add(new Frame(src, des, data)); // 将读取的数据存入列表中
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 读取文件中的数据
        try (BufferedReader br = new BufferedReader(new FileReader(fileName2))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(" ");
                int src = Integer.parseInt(tokens[0]);
                int des = Integer.parseInt(tokens[1]);
                String data = tokens[2];
                frameList2.add(new Frame(src, des, data)); // 将读取的数据存入列表中
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int i = 0;
        int j = 0;
        while (i < frameList1.size() && j < frameList2.size()) {
            frameList.add(frameList1.get(i));
            frameList.add(frameList2.get(j));
            i++;
            j++;
        }

        // 将list1或list2中可能剩余的元素加入到interleavedList中
        while (i < frameList1.size()) {
            frameList.add(frameList1.get(i));
            i++;
        }
        while (j < frameList2.size()) {
            frameList.add(frameList2.get(j));
            j++;
        }

        return frameList;
    }
    public static void main(String[] args){
        ArrayList<Frame> frameList = readFrame("frame1.txt","frame2.txt");

        ArrayList<Frame> subNet1 = new ArrayList<>();
        ArrayList<Frame> subNet2 = new ArrayList<>();

        ArrayList<ArrayList<Frame>> subNetList = new ArrayList<>(Arrays.asList(subNet1,subNet2));

        Thread host = null;
        ArrayList<Thread> hostList = new ArrayList<>();

        for(int i = 1;i <= 3; i++){
            host = new Thread(new Host(i,subNet1));
            host.setDaemon(true);
            hostList.add(host);
        }
        for(int i = 4;i <=6; i++){
            host = new Thread(new Host(i,subNet2));
            host.setDaemon(true);
            hostList.add(host);
        }

        for(Thread each : hostList){
            each.start();
        }

        Thread bridge = new Thread(new Bridge(subNetList,frameList));
        bridge.start();

    }
}