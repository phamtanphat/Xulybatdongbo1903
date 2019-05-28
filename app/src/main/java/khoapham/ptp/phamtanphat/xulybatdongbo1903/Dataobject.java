package khoapham.ptp.phamtanphat.xulybatdongbo1903;

public class Dataobject {
    private int A;
    private int B;

    public Dataobject(int a, int b) {
        A = a;
        B = b;
    }
    public int tinhTong(){
        return this.A + this.B;
    }

    public int getA() {
        return A;
    }

    public void setA(int a) {
        A = a;
    }

    public int getB() {
        return B;
    }

    public void setB(int b) {
        B = b;
    }
}
