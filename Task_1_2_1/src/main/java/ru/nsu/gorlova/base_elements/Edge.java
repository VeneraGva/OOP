package ru.nsu.gorlova.base_elements;

/**
 * Ориентированное ребро графа.
 */
public class Edge {

    /**
     * Поле выходящей вершины ребра.
     */
    private final Vertex outVertex;

    /**
     * Поле входящей вершины ребра.
     */
    private final Vertex inVertex;

    /**
     * Поле кратности ребра
     */
    private Integer multiplicity;

    /**
     * Создание ребра.
     *
     * @param outVertex выходящая вершина ребра.
     * @param inVertex входящая вершина ребра.
     * @param multiplicity кратность ребра.
     */
    public Edge(Vertex outVertex, Vertex inVertex, Integer multiplicity) {
        this.outVertex = outVertex;
        this.inVertex = inVertex;
        this.multiplicity = multiplicity;
    }

    /**
     * Функция, возвращающая строковую запись ребра.
     *
     * @return строковая запись ребра.
     */
    @Override
    public String toString() {
        return "[ ( " + outVertex.toString() + "; " + inVertex.toString() + "), " + Integer.toString(multiplicity) + " ]";
    }

    /**
     * Функция получения выходящей вершины ребра.
     *
     * @return выходящая вершина ребра.
     */
    public Vertex getOutVertex() {
        return outVertex;
    }

    /**
     * Функция получения входящей вершины ребра.
     *
     * @return входящая вершина ребра.
     */
    public Vertex getInVertex() {
        return inVertex;
    }

    /**
     * Функция получения кратности ребра.
     *
     * @return кратность ребра.
     */
    public Integer getMultiplicity() {
        return multiplicity;
    }

    /**
     * Функция для изменения кратности ребра.
     *
     * @param newMultiplicity новая кратность ребра
     */
    public void setMultiplicity(Integer newMultiplicity) {
        if (newMultiplicity < 1) {
            throw new IllegalArgumentException("The multiplicity must be >= 1.");
        }
        this.multiplicity = newMultiplicity;
    }

    /**
     * Функция сравнения ребра. Кратность не сравнивается.
     * @param edge ребро, с которым сравнивается.
     * @return правда, если ребра равны.
     */
    @Override
    public boolean equals(Object edge) {
        if (this == edge) {
            return true;
        }
        if (edge == null || getClass() != edge.getClass()) {
            return false;
        }
        return outVertex.equals(((Edge) edge).outVertex) && inVertex.equals(((Edge) edge).inVertex);
    }

    /**
     * Функция, которая возвращает хеш код ребра. Кратность не входит в хеш код.
     *
     * @return хеш код.
     */
    @Override
    public int hashCode() {
        int result = outVertex.hashCode();
        result = 31 * result + inVertex.hashCode();
        return result;
    }
}