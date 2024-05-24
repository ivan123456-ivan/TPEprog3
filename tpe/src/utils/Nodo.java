package utils;

public class Nodo<T> {
    private T value;
    private int key;
    private Nodo<T> mayor;
    private Nodo<T> menor;
    
        public Nodo (T value, int key){
            this.value = value;
            this.key =key;
            this.mayor = null;
            this.menor = null;
        }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public Nodo<T> getMayor() {
        return mayor;
    }

    public void setMayor(Nodo<T> mayor) {
        this.mayor = mayor;
    }

    public Nodo<T> getMenor() {
        return menor;
    }

    public void setMenor(Nodo<T> menor) {
        this.menor = menor;
    }

}
