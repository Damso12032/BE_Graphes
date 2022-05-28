package org.insa.graphs.model;

public class Label implements Comparable<Label>{
	protected Node sommet_courant;
	protected boolean marque=false;
	protected double cout;
	protected Node pere=null;
	public Label(Node sommet_courant, double cout) {
		super();
		this.sommet_courant = sommet_courant;
		this.cout = cout;
	}
	
	public Node getSommet_courant() {
		return sommet_courant;
	}
	
	public void setSommet_courant(Node sommet_courant) {
		this.sommet_courant = sommet_courant;
	}
	
	public boolean isMarque() {
		return marque;
	}
	
	public void setMarque(boolean marque) {
		this.marque = marque;
	}
	
	public double getCost() {
		return cout;
	}
	
	public void setCout(double cout) {
		this.cout = cout;
	}
	
	public Node getPere() {
		return pere;
	}
	
	public void setPere(Node pere) {
		this.pere = pere;
	}
	
	public double getTotalCost() {
		return getCost();
	}
	
	
	public int compareTo(Label other) {
        return Double.compare(getTotalCost(), other.getTotalCost());
    }
}
