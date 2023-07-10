import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Host implements Runnable {
    public Host(int addr, ArrayList<Frame> subNet) {
        this.addr = addr;
        this.subNet = subNet;
    }
    @Override
    public void run() {
        while(true){
            try {
                synchronized (subNet){
                    if(subNet.size() != 0) {
                        for (Frame each : subNet) {
                            if (each.getDes() == this.addr) {
                                System.out.println("主机" + addr + "收到：" + each.getData());
                                if(each.getData().equals("SEEK")){
                                    subNet.add(new Frame(addr,0,"REPLY"));
                                    System.out.println("网桥广播查找，以向网桥汇报");
                                }
                                subNet.remove(each);
                            }
                        }
                    }
                }
                sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public int getAddr() {
        return addr;
    }

    public void setAddr(int addr) {
        this.addr = addr;
    }

    public ArrayList<Frame> getSubNet() {
        return subNet;
    }

    public void setSubNet(ArrayList<Frame> subNet) {
        this.subNet = subNet;
    }

    private int addr;
    private ArrayList<Frame> subNet;

}
