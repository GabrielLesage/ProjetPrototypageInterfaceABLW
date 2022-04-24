package ablw.projetprototypageinterfaceablw;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.*;

public class RechercheVoyage extends Service<ArrayList<Sejour>> {

    ArrayList<Sejour> listeSejour;
    String recherche;

    public RechercheVoyage(ArrayList<Sejour> listeSejour, String recherche) {
        this.listeSejour = listeSejour;
        this.recherche = recherche;
    }

    public void setListeSejour(ArrayList<Sejour> listeSejour) {
        this.listeSejour = listeSejour;
    }

    public ArrayList<Sejour> getListeSejour() {
        return listeSejour;
    }

    public String getRecherche() {
        return recherche;
    }

    protected Task<ArrayList<Sejour>> createTask(){
        return new Task<ArrayList<Sejour>>() {
            @Override
            protected ArrayList<Sejour> call() throws Exception {
                ArrayList<Sejour> res = rechercheSejour(getListeSejour(), getRecherche());
                updateValue(res);
                return res;
            }
        };
    }

    private ArrayList<Sejour> rechercheSejour(ArrayList<Sejour> listeSejour, String recherche){
        if(recherche.length() < 2) {
            return null;
        }
        ArrayList<Sejour> resultat = new ArrayList<Sejour>();
        for(Sejour sej:listeSejour) {
            if (sej.titre.contains(recherche)) resultat.add(sej);
            else if (sej.hote.contains(recherche)) resultat.add(sej);
            else if (sej.description.contains(recherche)) resultat.add(sej);
            else if (sej.tache.contains(recherche)) resultat.add(sej);
        }
        return resultat;
    }

}
