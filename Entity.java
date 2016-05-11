public class Entity {
    protected String identifiant; // Au plus 8 caractères
    protected int portUDP; // < 9999
    protected int portTCP; 
    protected String nextIP; // L'adresse IP de la machine suivante dans l'anneau
    protected int nextUDP; // Le port UDP suivant dans l'anneau

    // Multi difusion : toutes les entités d'un anneau possèdent la même addresse + port
    protected String multiDifIPV4; // addresse IPv4 pour la multi difusion en cas de panne
    protected int multiDifPort; // Le port UDP de multi dif < 9999


    public Entity(String identifiant, int portUDP, int portTCP, String ip, String multiDifIPV4, int multiDifPort){
        this.identifiant = identifiant;
        this.portUDP = portUDP;
        this.portTCP = portTCP;
        this.nextIP = ip;
        this.nextUDP = portUDP;
        this.multiDifIPV4 = multiDifIPV4;
        this.multiDifPort = multiDifPort;
    }
}