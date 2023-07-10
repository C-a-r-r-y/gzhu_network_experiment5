public class Frame {
    private int src;
    private int des;

    public int getRtt() {
        return rtt;
    }

    private int rtt;
    private String data;
    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public int getDes() {
        return des;
    }

    public void setDes(int des) {
        this.des = des;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    public Frame(){

    }
    public Frame(int src, int des, String data) {
        this.src = src;
        this.des = des;
        this.data = data;
        this.rtt = 0;
    }
}
