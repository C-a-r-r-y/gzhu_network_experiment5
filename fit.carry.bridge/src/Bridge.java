import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Thread.sleep;

public class Bridge implements Runnable{

    private HashMap<Integer,ArrayList<Frame>> forwardTable;
    private ArrayList<ArrayList<Frame>> subNetList;

    public Bridge(ArrayList<ArrayList<Frame>> subNetList, ArrayList<Frame> postList) {
        this.subNetList = subNetList;
        this.postList = postList;
        forwardTable = new HashMap<>();
    }

    private ArrayList<Frame> postList;


    @Override
    public void run() {
        try {
            ArrayList<Frame> target = null;
            Frame seeker = null;
            for (Frame toPost : postList) {
                target = forwardTable.get(toPost.getDes());
                if (null != target) {
                    System.out.println("找到"+toPost.getDes()+"的网段,已发送");
                    target.add(toPost);
                } else {
                    seeker = new Frame(0, toPost.getDes(), "SEEK");
                    for (ArrayList<Frame> subNet : subNetList) {
                        synchronized (subNet) {
                            subNet.add(seeker);
                        }
                    }
                    sleep(5);
                    out:
                    for (ArrayList<Frame> subNet : subNetList) {
                        synchronized (subNet) {
                            for (Frame frame : subNet) {
                                if (frame.getDes() == 0) {
                                    forwardTable.put(toPost.getDes(), subNet);
                                    System.out.println("已找到" + toPost.getDes() + "的网段，已记入转发表");
                                    subNet.add(toPost);
                                    subNet.remove(frame);
                                    break out;
                                }
                            }
                        }
                    }
                }

            }
        }
        catch (Exception e){
        }
    }

}
