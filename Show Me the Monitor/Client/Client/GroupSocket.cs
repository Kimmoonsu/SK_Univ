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
    class GroupSocket
    {
        public static ArrayList socketList = new ArrayList(); //서버에 연결된 소켓들의 Array 입니다.
        private static GroupSocket groupSocket;
        private Thread threadWaitingSocket;
        private Socket listener;
        private int port;
        private Boolean leader_state = true;
        public static void start(int _port) //   서버 시작
        {
            if (groupSocket == null)
            {
                groupSocket = new GroupSocket(_port);
            }
        }
        public static void stop()       // 서버 종료
        {
            Console.WriteLine("서버를 종료합니다.");
            foreach (GroupSocketHandler socketHandler in socketList)
            {
                socketHandler.end();
            }
            if (groupSocket != null)
            {
                groupSocket.threadStop();
            }
        }
        private void threadStop()
        {
            threadWaitingSocket.Abort();
            listener.Close();
        }
        private GroupSocket(int _port)
        {
            port = _port;
            threadWaitingSocket = new Thread(new ThreadStart(WaitingSocket));
            threadWaitingSocket.Start();
        }
        private void WaitingSocket()    //  클라이언트의 접속을 체크하는 스레드.
        {
            //IPAddress ipAddress = IPAddress.Any;
            IPEndPoint point = new IPEndPoint(IPAddress.Loopback, 9000);
            listener = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Udp);
            listener.Bind(point);
            listener.Listen(1000);      //  동시에 접속할 접속자수를 설정
            Console.WriteLine("연결을 기다립니다.");
            while (true)
            {
                Socket acceptedSocket = listener.Accept();      //  접속한 클라이언트 소켓
                string acceptedIP = ((IPEndPoint)acceptedSocket.RemoteEndPoint).Address.ToString(); //  접속한 클라이언트 IP
                socketList.Add(new GroupSocketHandler(acceptedSocket));                                  //  접속리스트에 추가
                Console.WriteLine(socketList.Count + "번째 컴퓨터 - " + acceptedIP + "에서 접속하였습니다.");
            }
        }
    }
    public class GroupSocketHandler
    {
        public Socket socket;
        public Thread threadHandler;
        enum DataPacketType { TEXT = 1, IMAGE };
        public GroupSocketHandler(Socket socket)
        {
            this.socket = socket;
            threadHandler = new Thread(new ThreadStart(Handler));
            threadHandler.Start();
        }
        public void Handler()                   //  실질적인 서버작업
        {
            byte[] buffer = new byte[4096];
            int bufferCount;
            //SendMsg((BroadcastServer.socketList.Count) + "명이 접속해 있습니다.");
            try
            {
                while (true)
                {

                    // buffer.Initialize();
                    byte[] buffer1 = new byte[4096];
                    bufferCount = socket.Receive(buffer1);
                    if (bufferCount == 0) break;
                    int state = BitConverter.ToInt32(buffer1, 0);

                    if (state == (int)DataPacketType.IMAGE)
                    {

                        int totalLength = 0;
                        socket.Receive(buffer1);
                        int fileLength = BitConverter.ToInt32(buffer1, 0);
                        buffer1 = new byte[4096];
                        FileStream fileStr = new FileStream("test.png", FileMode.Create, FileAccess.Write);
                        BinaryWriter writer = new BinaryWriter(fileStr);
                        int i = 0;
                        while (totalLength < fileLength)
                        {
                            int receiveLength = socket.Receive(buffer1);
                            writer.Write(buffer1, 0, receiveLength);
                            totalLength += receiveLength;
                            Console.WriteLine("total : " + totalLength + " file : " + fileLength + "receiveLength : " + receiveLength + "i : " + (i++) + "\n");
                        }
                        writer.Close();
                        fileStr.Close();
                        SendData();
                        Console.WriteLine("전송끝\n");
                    }
                    else
                    {
                        string Msgs = ASCIIEncoding.UTF8.GetString(buffer1);
                        byte[] byteimsi = new byte[bufferCount];    // 남은버퍼 없애기용 임시byte
                        for (int i = 0; i < bufferCount; i++)
                        {
                            byteimsi[i] = buffer1[i];
                        }
                        Msgs = ASCIIEncoding.UTF8.GetString(byteimsi);
                        Console.WriteLine("클라이언트에서 받은 메세지" + Msgs);
                        SendMsg(Msgs);
                    }
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e.ToString());
            }
            finally
            {
                Console.WriteLine("클라이언트가 종료하였습니다.");
                GroupSocket.socketList.Remove(this);
                socket.Close();
                socket = null;
            }
        }
        private void SendData()
        {
            //Socket mySocket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
            //mySocket.Connect(IPAddress.Loopback, 9000);
            FileStream fileStr = new FileStream("test.png", FileMode.Open, FileAccess.Read);
            int fileLength = (int)fileStr.Length;
            byte[] buffer = new byte[4096];
            buffer = BitConverter.GetBytes((int)DataPacketType.IMAGE);

            ////mWriter.Write(buffer);
            //socket.Send(buffer, 0, buffer.Length, SocketFlags.None);

            buffer.Initialize();
            //socket.Send(buffer, 0, buffer.Length, SocketFlags.None);
            buffer = BitConverter.GetBytes(fileLength);
            socket.Send(buffer, 0, buffer.Length, SocketFlags.None);
            int count = fileLength / 4096 + 1;
            Console.WriteLine("file : " + fileLength);
            BinaryReader reader = new BinaryReader(fileStr);
            for (int i = 0; i < count; i++)
            {
                buffer = reader.ReadBytes(4096);
                socket.Send(buffer, 0, buffer.Length, SocketFlags.None);
            }
            buffer.Initialize();

            reader.Close();
            //mySocket.Close();
        }
        public void SendMsg(string Msg)     //  메세지 보내기.
        {
            int bufferCount = 0;
            byte[] buffer = new byte[4096];
            buffer = BitConverter.GetBytes((int)DataPacketType.TEXT);
            //foreach (SocketHandler socketHandler in BroadcastServer.socketList)
            //{
            //    socketHandler.socket.Send(buffer, 0, bufferCount, SocketFlags.None);
            //}
            //buffer.Initialize();
            buffer = ASCIIEncoding.UTF8.GetBytes(Msg);
            bufferCount = ASCIIEncoding.UTF8.GetByteCount(Msg);
            foreach (GroupSocketHandler socketHandler in GroupSocket.socketList)
            {
                Console.WriteLine("msg : " + Msg);
                socketHandler.socket.Send(buffer, 0, bufferCount, SocketFlags.None);
            }
        }
        public void end()
        {
            threadHandler.Abort();
        }
    }
}


