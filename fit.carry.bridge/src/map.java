import java.util.ArrayList;

public class map{
    private int addr;
    private ArrayList<Frame> net;
    public map(int addr, ArrayList<Frame> net) {
        this.addr = addr;
        this.net = net;
    }
    public int getAddr() {
        return addr;
    }

    public void setAddr(int addr) {
        this.addr = addr;
    }

    public ArrayList<Frame> getNet() {
        return net;
    }

    public void setNet(ArrayList<Frame> net) {
        this.net = net;
    }
}