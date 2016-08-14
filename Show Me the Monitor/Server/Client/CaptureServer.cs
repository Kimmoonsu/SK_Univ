using System;
using System.IO;
using System.Net;
using System.Data;
using System.Text;
using System.Drawing;
using System.Net.Sockets;
using System.Threading;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel;
using System.Windows.Forms;
namespace Client
{
    public class Model
    {
        private Bitmap[] bitmap = new Bitmap[5];

        public void setBitmap(Bitmap bitmap, int index)
        {
            this.bitmap[index] = bitmap;
        }
        public Bitmap getBitmap(int index)
        {
            return bitmap[index];
        }
        public void resetBitmap()
        {
            bitmap = new Bitmap[5];
        }
    }
    public class CaptureServer : Form
    {
        
        Model model;
        public static ArrayList socketList = new ArrayList(); //서버에 연결된 소켓들의 Array 입니다.
        private static CaptureServer broadcastServer;
        private Thread threadWaitingSocket;
        private Socket listener;
        private int port;
        private PictureBox[] screenBox = new PictureBox[6];
        private int client_count = 0;
        public TextBox textView = null;
        private Button stop_btn = null;
        public static void start(int _port, TextBox textview, Button stop_btn, PictureBox[] screenBox, Model model) //   서버 시작
        {
            if (broadcastServer == null)
            {
                broadcastServer = new CaptureServer(_port, textview, stop_btn, screenBox, model);
            }
        }
        public static void stop()       // 서버 종료
        {

            // Console.WriteLine("서버를 종료합니다.");

            foreach (SocketHandler socketHandler in socketList)
            {
                socketHandler.end();
            }
            if (broadcastServer != null)
            {
                broadcastServer.threadStop();
            }
        }
        private void threadStop()
        {
            threadWaitingSocket.Abort();
            listener.Close();
        }

        private CaptureServer(int _port, TextBox textView, Button stop_btn, PictureBox[] screenBox, Model model)
        {
            port = _port;
            this.model = model;
            this.textView = textView;
            this.stop_btn = stop_btn;
            this.screenBox = screenBox;
            threadWaitingSocket = new Thread(new ThreadStart(WaitingSocket));
            threadWaitingSocket.Start();

        }

        private void WaitingSocket()    //  클라이언트의 접속을 체크하는 스레드.
        {
            //IPAddress ipAddress = IPAddress.Any;
            IPEndPoint point = new IPEndPoint(IPAddress.Any, 9000);
            listener = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
            listener.Bind(point);
            listener.Listen(1000);      //  동시에 접속할 접속자수를 설정
            textView.AppendText("연결을 기다립니다.\n");
            //Console.WriteLine("연결을 기다립니다.");
            while (true)
            {
                Socket acceptedSocket = listener.Accept();      //  접속한 클라이언트 소켓
                string acceptedIP = ((IPEndPoint)acceptedSocket.RemoteEndPoint).Address.ToString(); //  접속한 클라이언트 IP
                client_count++;
                socketList.Add(new SocketHandler(acceptedSocket, textView, screenBox, client_count, model));                                  //  접속리스트에 추가
                textView.AppendText(socketList.Count + "번째 컴퓨터 - " + acceptedIP + "에서 접속하였습니다.");
            }
        }

        internal static void start(int p, TextBox textView, Button button2, PictureBox[] screenBox, Form1.Model model)
        {
            throw new NotImplementedException();
        }
    }
    public class SocketHandler
    {
        public Socket socket;
        public Model model;
        public Thread threadHandler;
        public TextBox textView;
        private PictureBox[] screenBox = new PictureBox[6];
        private Boolean checkScreen = false;
        private int client_number; // client번호 부여
        enum DataPacketType { TEXT = 1, IMAGE };
        private int index;
        private Socket acceptedSocket;
        private TextBox textView1;
        private int client_count;
        
        public SocketHandler(Socket socket, TextBox textView, PictureBox[] screenBox, int client_number, Model model)
        {
            this.socket = socket;
            this.textView = textView;
            this.screenBox = screenBox;
            this.screenBox[5].Visible = false;
            this.client_number = client_number;
            this.model = model;
            screenBox[1].Click += picture1Click;
            screenBox[2].Click += picture2Click;
            screenBox[3].Click += picture3Click;
            screenBox[4].Click += picture4Click;
            screenBox[5].Click += fullScreenClick;
            threadHandler = new Thread(new ThreadStart(Handler));
            threadHandler.Start();
        }

        private void picture1Click(object sender, EventArgs e)
        {
            index = 1;
            checkScreen = true;
            screenBox[5].Visible = true;
            screenBox[1].Visible = false;
            screenBox[2].Visible = false;
            screenBox[3].Visible = false;
            screenBox[4].Visible = false;
        }
        private void picture2Click(object sender, EventArgs e)
        {
            index = 2;
            checkScreen = true;
            screenBox[5].Visible = true;
            screenBox[1].Visible = false;
            screenBox[2].Visible = false;
            screenBox[3].Visible = false;
            screenBox[4].Visible = false;
        }
        private void picture3Click(object sender, EventArgs e)
        {
            index = 3;
            checkScreen = true;
            screenBox[5].Visible = true;
            screenBox[1].Visible = false;
            screenBox[2].Visible = false;
            screenBox[3].Visible = false;
            screenBox[4].Visible = false;
        }
        private void picture4Click(object sender, EventArgs e)
        {
            index = 4;
            checkScreen = true;
            screenBox[5].Visible = true;
            screenBox[1].Visible = false;
            screenBox[2].Visible = false;
            screenBox[3].Visible = false;
            screenBox[4].Visible = false;
        }
        private void fullScreenClick(object sender, EventArgs e)
        {
            checkScreen = false;
            screenBox[5].Visible = false;
            screenBox[1].Visible = true;
            screenBox[2].Visible = true;
            screenBox[3].Visible = true;
            screenBox[4].Visible = true;
        }
        private void repaint()
        {

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
                    byte[] buffer1 = new byte[4];
                    bufferCount = socket.Receive(buffer1);
                    if (bufferCount == 0) break;
                    int state = BitConverter.ToInt32(buffer1, 0);

                    if (state == (int)DataPacketType.IMAGE)
                    {
                        textView.AppendText("사진 전송 시작!\n");
                        int totalLength = 0;
                        socket.Receive(buffer1);
                        int fileLength = BitConverter.ToInt32(buffer1, 0);
                        buffer1 = new byte[fileLength];
                        socket.Receive(buffer1);

                        textView.AppendText("전송끝\n");
                        Bitmap image = ByteToImage(buffer1);
                        model.setBitmap(image, client_number);
                        screenBox[client_number].Image = image;
                        screenBox[client_number].SizeMode = PictureBoxSizeMode.StretchImage;
                        if (checkScreen)
                        {
                            screenBox[5].Image = model.getBitmap(index);
                            screenBox[5].SizeMode = PictureBoxSizeMode.StretchImage;
                            //model.resetBitmap();
                        }
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
                        //textView.AppendText("클라이언트에서 받은 메세지" + Msgs);
                        //SendMsg(Msgs);
                    }

                }
            }
            catch (Exception e)
            {
                textView.AppendText(e.ToString());
            }
            finally
            {
                textView.AppendText("클라이언트가 종료하였습니다.");
                CaptureServer.socketList.Remove(this);
                socket.Close();
                socket = null;
            }
        }
        public static Bitmap ByteToImage(byte[] blob)
        {
            MemoryStream mStream = new MemoryStream();
            byte[] pData = blob;
            mStream.Write(pData, 0, Convert.ToInt32(pData.Length));
            Bitmap bm = new Bitmap(mStream, false);
            mStream.Dispose();
            return bm;

        }
        private void SendData()
        {
            // Socket mySocket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
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
            foreach (SocketHandler socketHandler in CaptureServer.socketList)
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
