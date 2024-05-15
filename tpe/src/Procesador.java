import java.time.LocalDate;

public class Procesador {
    private int codigo;
    private String id;
    private boolean esta_refrigerado;
    private LocalDate anio_funcionamiento;

    public Procesador(int codigo, String id, boolean esta_refrigerado, LocalDate anio_funcionamiento) {
        this.codigo = codigo;
        this.id = id;
        this.esta_refrigerado = esta_refrigerado;
        this.anio_funcionamiento = anio_funcionamiento;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isEsta_refrigerado() {
        return esta_refrigerado;
    }

    public void setEsta_refrigerado(boolean esta_refrigerado) {
        this.esta_refrigerado = esta_refrigerado;
    }

    public LocalDate getAnio_funcionamiento() {
        return anio_funcionamiento;
    }

    public void setAnio_funcionamiento(LocalDate anio_funcionamiento) {
        this.anio_funcionamiento = anio_funcionamiento;
    }
}
