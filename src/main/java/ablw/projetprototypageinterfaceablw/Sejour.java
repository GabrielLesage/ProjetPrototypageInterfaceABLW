package ablw.projetprototypageinterfaceablw;

public class Sejour {
    public String titre;
    public String hote;
    public String description;
    public String tache;

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getHote() {
        return hote;
    }

    public void setHote(String hote) {
        this.hote = hote;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTache() {
        return tache;
    }

    public void setTache(String tache) {
        this.tache = tache;
    }

    public Sejour(String titre, String hote, String description, String tache) {
        this.titre = titre;
        this.hote = hote;
        this.description = description;
        this.tache = tache;
    }
}
