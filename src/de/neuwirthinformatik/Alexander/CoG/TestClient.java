package de.neuwirthinformatik.Alexander.CoG;
import GLOOP.*;
import de.neuwirthinformatik.Alexander.CoG.IO.UDPClient;
import de.neuwirthinformatik.Alexander.CoG.IO.UDPServer;
import de.neuwirthinformatik.Alexander.CoG.Person.GLKameraC;
import de.neuwirthinformatik.Alexander.CoG.Person.GLPersonC;
import de.neuwirthinformatik.Alexander.CoG.Person.GLPersonNC;

public class TestClient
{
    public static void main(String[] args)
    {
    	new Client("192.168.0.103").start();
    }
}
