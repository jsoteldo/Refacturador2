package com.gs.mifarma.componentes;

public class OptionComboBox<T> implements Comparable<T> {
    private String label;
    private T codigo;
    private String agregaOpcionOtro;
    private boolean opcionOtro = false;
    private boolean seleccionado = false;

    public OptionComboBox() {
    }
    
    public OptionComboBox(String label, T codigo) {
        this.label = label;
        this.codigo = codigo;
    }
    
    public OptionComboBox(String label, T codigo, boolean seleccionado) {
        this.label = label;
        this.codigo = codigo;
        this.seleccionado = seleccionado;
    }
    
    public OptionComboBox(String label, T codigo, boolean seleccionado, boolean opcionOtro) {
        this.label = label;
        this.codigo = codigo;
        this.opcionOtro = opcionOtro;
        this.seleccionado = seleccionado;
    }

    @Override
    public String toString() {
        return this.label;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public T getCodigo() {
        return codigo;
    }

    public void setCodigo(T codigo) {
        this.codigo = codigo;
    }

    public boolean isOpcionOtro() {
        return opcionOtro;
    }

    public void setOpcionOtro(boolean opcionOtro) {
        this.opcionOtro = opcionOtro;
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }
    
    public void setAgregaOpcionOtro(String agregaOpcionOtro){
        this.agregaOpcionOtro = agregaOpcionOtro;
        setOpcionOtro("S".equalsIgnoreCase(agregaOpcionOtro));
    }
}
