package ablw.projetprototypageinterfaceablw;

import java.util.List;

public class RechercheVoyage {
    public Sejour[] rechercheSejour(Sejour[] listeSejour, String recherche){
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
