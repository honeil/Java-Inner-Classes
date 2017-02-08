package oneil.hilary.zipcode;

import java.util.ArrayList;

/**
 * Created by hilaryoneil on 2/3/17.
 */
class ConnectionManager {

    ArrayList<Connection> connectionsList;
    private int numOfOpenConnections =0;
    static final int MAX_OPEN_CONNECTIONS =5 ;

    ConnectionManager(){
        connectionsList= new ArrayList<Connection>();
        numOfOpenConnections=0;
    }

    //This class is supposed to be private but I can't figure out testing...
     class ManagedConnection implements Connection{

        private String ip;
        private Protocol protocol;
        private int port;
        boolean isActive = true;

      private ManagedConnection(String ip, int port, Protocol protocol){
            this.ip = ip;
            this.port = port;
            this.protocol = protocol;
        }

      private ManagedConnection(String ip, Protocol protocol){
            this.ip = ip;
            this.port = 8000;
            this.protocol = protocol;
        }

      private ManagedConnection(String ip, int port){
            this.ip = ip;
            this.port = port;
            this.protocol = Protocol.HTTP;
        }

        public String getIP(){
            if(isActive) {
                return this.ip;
            }
            return "ERROR: closed connection.";
        }

        public int getPort(){
            if(isActive) {
                return this.port;
            }
            return -1;
        }

        public Protocol getProtocol() {
            if(isActive) {
                return this.protocol;
            }
            return null;
        }

        public String connect(){
            String message;
            if(isActive){
                message = "Connected to " + this.getIP() + ":" + this.getPort() + " via " + this.getProtocol();
            }
            else
                message = "ERROR: closed connection.";
            return message;
         }

        public void close(){
            isActive = false;
            if(numOfOpenConnections >0)
                numOfOpenConnections--;
        }

    }

    //Why does IntelliJ consider this a duplicate? My parameters are different.
    ManagedConnection buildConnection(String ip, Protocol protocol){
        if(numOfOpenConnections < MAX_OPEN_CONNECTIONS) {
            ManagedConnection managedConnection = new ManagedConnection(ip, protocol);
            connectionsList.add(managedConnection);
            numOfOpenConnections++;
            return managedConnection;
        }
        return null;
    }

    ManagedConnection buildConnection(String ip, int port, Protocol protocol){
        if(numOfOpenConnections < MAX_OPEN_CONNECTIONS) {
            ManagedConnection managedConnection = new ManagedConnection(ip, port, protocol);
            connectionsList.add(managedConnection);
            numOfOpenConnections++;
            return managedConnection;
        }
        return null;
    }

    ManagedConnection buildConnection(String ip, int port){
        if(numOfOpenConnections < MAX_OPEN_CONNECTIONS) {
            ManagedConnection managedConnection = new ManagedConnection(ip, port);
            connectionsList.add(managedConnection);
            numOfOpenConnections++;
            return managedConnection;
        }
        return null;
    }


}







