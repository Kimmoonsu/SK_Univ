using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using System.Net;
using System.Net.Sockets;
using System.Threading;
using System.Collections;

namespace Client
{
    class UdpServer
    {
        int recv = 0;
        byte[] data = new byte[1024];
        IPEndPoint ep = null;
        Socket server = null;
        public UdpServer()
        {
        
            ep = new IPEndPoint(IPAddress.Any, 9050);
            server = new Socket(AddressFamily.InterNetwork, SocketType.Dgram, ProtocolType.Udp);
        }
        
        void test()
        {
            server.Bind(ep);
 
            Console.WriteLine("Waiting for a client...");
 
            IPEndPoint sender = new IPEndPoint(IPAddress.Any, 0);
            EndPoint remoteEP = (EndPoint)sender;
 
            recv = server.ReceiveFrom(data, ref remoteEP);
     
            Console.WriteLine("[first] Message received from {0}", remoteEP.ToString());
            Console.WriteLine("[first] received data : {0}", Encoding.UTF8.GetString(data, 0, recv));
 
            string welcome = "Welcome to udp server";
            data = Encoding.UTF8.GetBytes(welcome);
            server.SendTo(data, remoteEP);
 
            while(true)
            {
                data = new byte[1024];
                recv = server.ReceiveFrom(data, ref remoteEP);
                string recvData = Encoding.UTF8.GetString(data, 0, recv);
                Console.WriteLine("received data : {0}", recvData);
 
                server.SendTo(Encoding.UTF8.GetBytes(recvData), remoteEP);
                Console.WriteLine("send data : {0}", Encoding.UTF8.GetString(data, 0, recv));
                Console.WriteLine("");
            }
            server.Close();
        }
    }
}
