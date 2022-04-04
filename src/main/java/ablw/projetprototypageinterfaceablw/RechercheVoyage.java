package ablw.projetprototypageinterfaceablw;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.List;

public class RechercheVoyage extends Service<Sejour[]> {

    Sejour[] listeSejour;
    String recherche;

    public RechercheVoyage(Sejour[] listeSejour, String recherche) {
        this.listeSejour = listeSejour;
        this.recherche = recherche;
    }

    public void setListeSejour(Sejour[] listeSejour) {
        this.listeSejour = listeSejour;
    }

    public Sejour[] getListeSejour() {
        return listeSejour;
    }

    public String getRecherche() {
        return recherche;
    }

    protected Task<Sejour[]> createTask(){
        return new Task<Sejour[]>() {
            @Override
            protected Sejour[] call() throws Exception {
                return rechercheSejour(getListeSejour(), getRecherche());
            }
        };
    }

    public  Sejour[]rechercher(Sejour[] listeSejour, String recherche){

    }

    private Sejour[] rechercheSejour(Sejour[] listeSejour, String recherche){
        if(recherche.length() < 2) {
            return null;
        }
        List<Sejour> resultat;
        for(Sejour sej:listeSejour) {
            if (sej.titre.contains(recherche)) resultat.add(sej);
            else if (sej.hote.contains(recherche)) resultat.add(sej);
            else if (sej.description.contains(recherche)) resultat.add(sej);
            else if (sej.tache.contains(recherche)) resultat.add(sej);
        }
        return (Sejour[]) resultat.toArray();
    }
}
