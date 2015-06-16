package trabalho;

public class Vertice implements Comparable<Vertice>/* Para uso no Hashtable */ {
    //Valor da coordenada X
    private int x;
    //Valor da coodenada Y
    private int y;
    //Valor do custo até o pai do Vertice
    private double custo;
    //Custo + Heuristica
    private double custo_h;
    //Vertice do pai
    Vertice pai;

    public Vertice(int x, int y, double custo_h, double custo, Vertice pai) {
        this.x = x;
        this.y = y;
        this.custo = custo;
        this.custo_h = custo_h;
        this.pai = pai;
    }

    public Vertice(int x, int y, double custo) {
        this.pai = null;
        this.x = x;
        this.y = y;
        this.custo = custo;
        this.custo_h = 0;
    }
    
    public Vertice() {
        this.pai = null;
        this.x = 0;
        this.y = 0;
        this.custo = 0;
        this.custo_h = 0;
    }
    //Sobrescrita do metódo compareTo para uso em PriorityQueue 
    @Override
    public int compareTo(Vertice y) {
        return Double.compare(this.getCusto_h(), y.getCusto_h());
    }
    //Sobrescrita do metódo equals para uso em Hashtable e PriorityQueue 
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Vertice v = (Vertice) obj;
        return (this.y == v.getY() && this.x == v.getX());
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setCusto(float custo) {
        this.custo = custo;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getCusto() {
        return custo;
    }

    public Vertice getPai() {
        return pai;
    }
    
    public double getCusto_h() {
        return custo_h;
    }   
}
