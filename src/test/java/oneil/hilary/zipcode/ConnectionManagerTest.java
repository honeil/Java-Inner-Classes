package oneil.hilary.zipcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static oneil.hilary.zipcode.ConnectionManager.MAX_OPEN_CONNECTIONS;

/**
 * Created by hilaryoneil on 2/3/17.
 */
public class ConnectionManagerTest {

    @Test
    public void buildConnectionTest_InputProtocolAndIPToCreateNewConnectionManager_ShouldReturnProtocol(){
        ConnectionManager test = new ConnectionManager();
        Protocol protocol = Protocol.FTP;
        String IP = "127.0.0.1";
        String expectedValue = "FTP";
        String actualValue = test.buildConnection(IP, protocol).getProtocol().toString();
        Assert.assertEquals(expectedValue,actualValue);
    }

    @Test
    public void buildConnectionTest_InputProtocolAndIPToCreateNewConnectionManager_ShouldReturnIP(){
        ConnectionManager test = new ConnectionManager();
        Protocol protocol = Protocol.FTP;
        String IP = "127.0.0.1";
        String expectedValue = "127.0.0.1";
        String actualValue = test.buildConnection(IP, protocol).getIP();
        Assert.assertEquals(expectedValue,actualValue);
    }

    @Test
    public void buildConnectionTest_InputIPAndPortToCreateNewConnectionManager_ShouldReturnDefaultPort(){
        ConnectionManager test = new ConnectionManager();
        Protocol protocol = Protocol.FTP;
        String IP = "127.0.0.1";
        int expectedValue = 8000;
        int actualValue = test.buildConnection(IP, protocol).getPort();
        Assert.assertEquals(expectedValue,actualValue);
    }

    @Test
    public void buildConnectionTest_InputIPPortAndProtocolToCreateNewConnectionManager_ShouldReturnIP(){
        ConnectionManager test = new ConnectionManager();
        Protocol protocol = Protocol.HTTP;
        String IP = "127.0.0.1";
        int port = 8000;
        String expectedValue= "127.0.0.1";
        String actualValue = test.buildConnection(IP, port,protocol).getIP();
        Assert.assertEquals(expectedValue,actualValue);
    }

    @Test
    public void buildConnectionTest_InputIPPortAndProtocolToCreateNewConnectionManager_ShouldReturnPort(){
        ConnectionManager test = new ConnectionManager();
        Protocol protocol = Protocol.HTTP;
        String IP = "127.0.0.1";
        int port = 8000;
        int expectedValue= 8000;
        int actualValue = test.buildConnection(IP, port,protocol).getPort();
        Assert.assertEquals(expectedValue,actualValue);
    }

    @Test
    public void buildConnectionTest_InputIPPortAndProtocolToCreateNewConnectionManager_ShouldReturnProtocol(){
        ConnectionManager test = new ConnectionManager();
        Protocol protocol = Protocol.HTTP;
        String IP = "127.0.0.1";
        int port = 8000;
        Protocol expectedValue= Protocol.HTTP;
        Protocol actualValue = test.buildConnection(IP, port,protocol).getProtocol();
        Assert.assertEquals(expectedValue,actualValue);
    }

    @Test
    public void buildConnectionTest_InputIPAndPortToCreateNewConnectionManager_ShouldReturnIP(){
        ConnectionManager test = new ConnectionManager();
        String IP = "127.0.0.1";
        int port = 8000;
        String expectedValue= "127.0.0.1";
        String actualValue = test.buildConnection(IP, port).getIP();
        Assert.assertEquals(expectedValue,actualValue);
    }

    @Test
    public void buildConnectionTest_InputIPAndPortToCreateNewConnectionManager_ShouldReturnPort(){
        ConnectionManager test = new ConnectionManager();
        String IP = "127.0.0.1";
        int port = 8000;
        int expectedValue= 8000;
        int actualValue = test.buildConnection(IP, port).getPort();
        Assert.assertEquals(expectedValue,actualValue);
    }

    @Test
    public void buildConnectionTest_InputIPAndPortToCreateNewConnectionManager_ShouldReturnDefaultProtocol(){
        ConnectionManager test = new ConnectionManager();
        String IP = "127.0.0.1";
        int port = 8000;
        Protocol expectedValue= Protocol.HTTP;
        Protocol actualValue = test.buildConnection(IP, port).getProtocol();
        Assert.assertEquals(expectedValue,actualValue);
    }

    @Test
    public void testMaxNumOfConnections_InputTenManagedConnections_ShouldStopAtFiveConnections(){
        ConnectionManager test = new ConnectionManager();
        ArrayList<Connection> connectionsList = new ArrayList<Connection>();
        int numOfOpenConnections = 0;
        for(int i = 0; i < 10; i++){
            if(numOfOpenConnections < MAX_OPEN_CONNECTIONS) {
                    ConnectionManager.ManagedConnection managedConnection = test.new ManagedConnection("127.0.0.1",8000, Protocol.HTTP);
                    connectionsList.add(managedConnection);
                    numOfOpenConnections++;
            }
        }
        int expectedValue = 5;
        int actualValue = connectionsList.size();
        Assert.assertEquals(expectedValue, actualValue);
    }


}
