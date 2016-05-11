import java.net.*;

public class Ring {
    private Entity doubleEntity;

    //--------------------------------
    // Constructeur

    public Ring(String identifiant, int portUDP, int portTCP, String ip, String multiDifIPV4, int multiDifPort){
        this.doubleEntity = new Entity(identifiant, portUDP, portTCP, ip, multiDifIPV4, multiDifPort);
    }


    //--------------------------------
    // insertEntity : insertion d'une entitée dans l'anneau

    public int insertEntity(Entity entity){
        return 0;
    }

    //--------------------------------
    // checkIPv4 : vérifie si un string est bien une addresse IPv4

    public static final boolean checkIPv4(String ip) {
        boolean isIPv4;
        try {
            final InetAddress inet = InetAddress.getByName(ip);
            isIPv4 = inet.getHostAddress().equals(ip) && inet instanceof Inet4Address;
        } catch (final UnknownHostException e) {
            isIPv4 = false;
        }

        return isIPv4;
    }

    //--------------------------------
    //--------------------------------
    //--------------------------------
    // MAIN
    //--------------------------------
    //--------------------------------
    //--------------------------------

    public static void main(String[] args){
        if (args.length == 6){

            // Creation de l'anneau

            System.out.println("Creating a ring...");
            Ring mainRing;
            try{
                String identifiant = args[0];
                if (identifiant.length() > 8){
                    System.out.println("Error while creating : the identifiant is not between 1 and 8 length");
                    return;
                }
                int portUDP = Integer.parseInt(args[1]);
                if (portUDP >= 9999) throw new NumberFormatException("portUDP"); 
                int portTCP = Integer.parseInt(args[2]);
                String ip = args[3];
                if (!checkIPv4(ip)){
                    System.out.println("Error while creating : the ip is not correct");
                    return;
                }
                String multiDifIPV4 = args[4];
                if (!checkIPv4(multiDifIPV4)){
                    System.out.println("Error while creating : the multi-difusion ip is not correct");
                    return;
                }
                int multiDifPort = Integer.parseInt(args[5]);
                if (multiDifPort >= 9999) throw new NumberFormatException("multiDifPort"); 
                mainRing = new Ring(identifiant, portUDP, portTCP, ip, multiDifIPV4, multiDifPort);
            } catch(NumberFormatException e){
                System.out.println("Error while creating : the " + e.getMessage() + " is not a int < 9999");
                return;
            }

            // Démarage du serveur
        }
        else{
            System.out.println("Error : Wrong number of arguments.\nHere is the syntax : java ring [identifiant] [portUPD] [portTCP] [IP] [UDP] [multiDifIPV4] [multiDifPort]");
        }
    }
}