package alexs7;

/**
 * Created by alex on 26/09/15.
 */
public class Template {

    private int rows;
    private int columns;
    private double[][] threeBythree;

    public Template(double i, double i1, double i2, double i3, double i4, double i5, double i6, double i7, double i8) {
        this.threeBythree = new double[][]{
                {i,i1,i2},
                {i3,i4,i5},
                {i6,i7,i8}
        };
        this.rows = 3;
        this.columns = 3;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public double valueAt(int x,int y){
        return threeBythree[y][x]; //reversed due to how java 2d array work
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(threeBythree[0][0] + " " + threeBythree[0][1] + " " + threeBythree[0][2] + "\n");
        stringBuilder.append(threeBythree[1][0] + " " + threeBythree[1][1] + " " + threeBythree[1][2] + "\n");
        stringBuilder.append(threeBythree[2][0] + " " + threeBythree[2][1] + " " + threeBythree[2][2] + "\n");
        return stringBuilder.toString();
    }
}
