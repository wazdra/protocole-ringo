import java.net.*;

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

    public int getPortTCP(){
        return this.portTCP;
    }

    public void connect(){
        try{
            System.out.println("Connecting to server...\n");
            ServerSocket server = new ServerSocket(portTCP);
            System.out.println("Connected with " + this.identifiant + "! You can now send messages.");
            while(true){
                Socket socket = server.accept();
                ServiceTCP tcp = new ServiceTCP(socket);
                Thread t = new Thread(tcp);
                t.start();
            }   
        } catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }

    //--------------------------------
    //--------------------------------
    //--------------------------------
    // MAIN
    //--------------------------------
    //--------------------------------
    //--------------------------------

    public static void main(String[] args){
        if (args.length == 3){
            Entity entity;
            try{
                String identifiant = args[0];
                if (identifiant.length() > 8){
                    System.out.println("Error while creating : the identifiant is not between 1 and 8 length");
                    return;
                }
                String ip = args[1];
                if (!Ring.checkIPv4(ip)){
                    System.out.println("Error while creating : the ip is not correct");
                    return;
                }
                int port = Integer.parseInt(args[2]);

                try{
                    Socket socket = new Socket(ip, port);
                    socket.close();
                } catch(Exception e){
                    System.out.println("Error : Fail to connect : " + e);
                }

            } catch(NumberFormatException e){
                System.out.println("Error while creating : the port is not a int");
            } catch(Exception e){
            }
        }
        else{
            System.out.println("Error : Wrong number of arguments.\nHere is the syntax : java Entity [identifiant] [IP] [portTCP]");
        }
    }
}