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
using System.Drawing.Imaging;

namespace Client
{
    
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();

        }

        private void button1_Click(object sender, EventArgs e)
        {
            Model model = new Model();
            PictureBox[] screenBox = { null, screenBox1, screenBox2, screenBox3, screenBox4, screenBox5 };
            CaptureServer.start(9000, textView, button2, screenBox, model, hidden_view);
           // new BroadcastServer(9001, textView, button2, screenBox, model, "text", send_btn, textBox1, hidden_view);
            //new BroadcastServer(9000, textView, button2, screenBox, model, "image", send_btn, textBox1, hidden_view);
           // VoiceChat v = new VoiceChat();
        }
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
            private static CaptureServer captureServer;
            private Thread threadWaitingSocket;
            private Socket listener;
            private int port;
            private PictureBox[] screenBox = new PictureBox[6];
            private int client_count = 0;
            public TextBox textView = null;
            private TextBox hidden_view = null;
            private Button stop_btn = null;
            public static void start(int _port, TextBox textview, Button stop_btn, PictureBox[] screenBox, Model model, TextBox hidden_view) //   서버 시작
            {
                if (captureServer == null)
                {
                    captureServer = new CaptureServer(_port, textview, stop_btn, screenBox, model, hidden_view);
                }
            }
            public static void stop()       // 서버 종료
            {

                // Console.WriteLine("서버를 종료합니다.");

                foreach (SocketHandler1 socketHandler in socketList)
                {
                    socketHandler.end();
                }
                if (captureServer != null)
                {
                    captureServer.threadStop();
                }
            }
            private void threadStop()
            {
                threadWaitingSocket.Abort();
                listener.Close();
            }

            private CaptureServer(int _port, TextBox textView, Button stop_btn, PictureBox[] screenBox, Model model, TextBox hidden_view)
            {
                port = _port;
                this.model = model;
                this.textView = textView;
                this.stop_btn = stop_btn;
                this.screenBox = screenBox;
                this.hidden_view = hidden_view;
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
                    socketList.Add(new SocketHandler1(acceptedSocket, textView, screenBox, client_count, model, hidden_view));                                  //  접속리스트에 추가
                    textView.AppendText(socketList.Count + "번째 컴퓨터 - " + acceptedIP + "에서 접속하였습니다.");
                }
            }
        }
        public class SocketHandler1
        {
            public Socket socket;
            public Model model;
            public Thread threadHandler;
            public TextBox textView;
            private PictureBox[] screenBox = new PictureBox[6];
            private Boolean checkScreen = false;
            private TextBox hidden_view;
            private int client_number; // client번호 부여
            enum DataPacketType { TEXT = 1, IMAGE };
            private int index;
            public SocketHandler1(Socket socket, TextBox textView, PictureBox[] screenBox, int client_number, Model model, TextBox hidden_view)
            {
                this.socket = socket;
                this.textView = textView;
                this.screenBox = screenBox;
                this.screenBox[5].Visible = false;
                this.client_number = client_number;
                this.model = model;
                this.hidden_view = hidden_view;
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
                            hidden_view.AppendText("사진 전송 시작!\n");
                            int totalLength = 0;
                            socket.Receive(buffer1);
                            int fileLength = BitConverter.ToInt32(buffer1, 0);
                            buffer1 = new byte[fileLength];
                            socket.Receive(buffer1);
                            
                            Bitmap image = ByteToImage(buffer1);
                            model.setBitmap(image, client_number);
                            screenBox[client_number].Image = image;
                            screenBox[client_number].SizeMode = PictureBoxSizeMode.StretchImage;
                            if (checkScreen)
                            {
                                screenBox[5].Image = model.getBitmap(index);
                                screenBox[5].SizeMode = PictureBoxSizeMode.StretchImage;
                                if (index == client_number)
                                    SendData2(buffer1);
                                //SendMsg("hello");
                                //model.resetBitmap();
                            }
                            else
                            {
                                SendMsg("false");
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
          
            public void SendMsg(string Msg)     //  메세지 보내기.
            {
                byte[] buffer = BitConverter.GetBytes((int)DataPacketType.TEXT);

                //mWriter.Write(buffer);
                foreach (SocketHandler1 socketHandler in CaptureServer.socketList)
                {
                    socketHandler.socket.Send(buffer);
                }
            }
            private void SendData2(byte[] imageBytes)
            {
                //Bitmap capture = capture_bitmap;
                
                //MemoryStream imageStream = new MemoryStream();
                //capture.Save(imageStream, ImageFormat.Jpeg);
                //imageStream.Position = 0;

                //// Do something with the memory stream. For example:
                //byte[] imageBytes = imageStream.ToArray();
                // Save bytes to the database.

                //FileStream fileStr = new FileStream("test.png", FileMode.Open, FileAccess.Read);
                //int fileLength = (int)fileStr.Length;

                //pictureBox1.Image = image;
                byte[] buffer = BitConverter.GetBytes((int)DataPacketType.IMAGE);

                //mWriter.Write(buffer);
                foreach (SocketHandler1 socketHandler in CaptureServer.socketList)
                {
                    socketHandler.socket.Send(buffer);
                }
                buffer.Initialize();
                buffer = BitConverter.GetBytes(imageBytes.Length);
                foreach (SocketHandler1 socketHandler in CaptureServer.socketList)
                {
                    socketHandler.socket.Send(buffer);
                }
                int count = imageBytes.Length / 4096 + 1;
                //BinaryReader reader = new BinaryReader(fileStr);
                foreach (SocketHandler1 socketHandler in CaptureServer.socketList)
                {
                    socketHandler.socket.Send(imageBytes);
                }

            }
            public void end()
            {
                threadHandler.Abort();
            }
        }


















        /******************************************************************/
        public class BroadcastServer : Form
        {
            Model model;
            public static ArrayList socketList = new ArrayList(); //서버에 연결된 소켓들의 Array 입니다.
            private static BroadcastServer broadcastServer;
            private Thread threadWaitingSocket;
            private Socket listener;
            private int port;
            private PictureBox[] screenBox = new PictureBox[6];
            private int client_count = 0;
            public TextBox textbox = null;
            public TextBox textView = null;
            public TextBox hidden_view= null;
            private Button stop_btn = null;
            private Button send_btn = null;
            private String state = null;
            public static void start(int _port, TextBox textview, Button stop_btn, PictureBox[] screenBox, Model model, String state, Button send_btn, TextBox textbox, TextBox hidden_view) //   서버 시작
            {
                if (broadcastServer == null)
                {
                    broadcastServer = new BroadcastServer(_port, textview, stop_btn, screenBox, model, state, send_btn, textbox, hidden_view);
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

            public BroadcastServer(int _port, TextBox textView, Button stop_btn, PictureBox[] screenBox, Model model, String state, Button send_btn, TextBox textbox, TextBox hidden_view)
            {
                port = _port;
                this.model = model;
                this.textView = textView;
                this.stop_btn = stop_btn;
                this.screenBox = screenBox;
                this.state = state;
                this.send_btn = send_btn;
                this.textbox = textbox;
                this.hidden_view = hidden_view;
                threadWaitingSocket = new Thread(new ThreadStart(WaitingSocket));
                threadWaitingSocket.Start();

            }

            private void WaitingSocket()    //  클라이언트의 접속을 체크하는 스레드.
            {
                //IPAddress ipAddress = IPAddress.Any;
                IPEndPoint point = new IPEndPoint(IPAddress.Any, port);
                listener = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
                listener.Bind(point);
                listener.Listen(1000);      //  동시에 접속할 접속자수를 설정
                
                //Console.WriteLine("연결을 기다립니다.");
                while (true)
                {
                    Socket acceptedSocket = listener.Accept();      //  접속한 클라이언트 소켓
                    string acceptedIP = ((IPEndPoint)acceptedSocket.RemoteEndPoint).Address.ToString(); //  접속한 클라이언트 IP
                    client_count++;
                    socketList.Add(new SocketHandler(acceptedSocket, textView, screenBox, client_count, model, state, send_btn, textbox, hidden_view));                                  //  접속리스트에 추가
                //    textView.AppendText(socketList.Count + "번째 컴퓨터 - " + acceptedIP + "에서 접속하였습니다.");
                }
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
            private Button send_btn;
            private TextBox textbox;
            public TextBox hidden_view = null;
            enum DataPacketType { TEXT = 1, IMAGE };
            private int index;
            public String socket_state = "";
            int test = 0;
            public SocketHandler(Socket socket, TextBox textView, PictureBox[] screenBox, int client_number, Model model, String state, Button send_btn, TextBox textbox, TextBox hidden_view)
            {
                this.socket = socket;
                this.textView = textView;
                this.screenBox = screenBox;
                this.screenBox[5].Visible = false;
                this.client_number = client_number;
                this.model = model;
                this.socket_state = state;
                this.send_btn = send_btn;
                this.textbox = textbox;
                this.hidden_view = hidden_view;
                send_btn.Click += send_btn_Click;
                threadHandler = new Thread(new ThreadStart(Handler));
                threadHandler.Start();
            }
            
            private void send_btn_Click(object sender, EventArgs e)
            {
                String _str = String.Format(textbox.Text);
                SendMsg(_str);
                textbox.Text = null;
            }
            private void repaint()
            {

            }
            public void Handler()                   //  실질적인 서버작업
            {
                byte[] buffer = new byte[4096];
                int bufferCount;
             
                try
                {
                    while (true)
                    {
                        byte[] buffer1 = new byte[4096];
                        bufferCount = socket.Receive(buffer1);
                        if (bufferCount == 0) break;
                        int state = BitConverter.ToInt32(buffer1, 0);
                        string Msgs = ASCIIEncoding.UTF8.GetString(buffer1);
                        byte[] byteimsi = new byte[bufferCount];    // 남은버퍼 없애기용 임시byte
                        for (int i = 0; i < bufferCount; i++)
                        {
                            byteimsi[i] = buffer1[i];
                        }
                        Msgs = ASCIIEncoding.UTF8.GetString(byteimsi);
                        SendMsg(Msgs);
                    }
                }
                catch (Exception e)
                {
                    textView.AppendText(e.ToString());
                }
                finally
                {
                    textView.AppendText("클라이언트가 종료하였습니다.");
                    BroadcastServer.socketList.Remove(this);
                    socket.Close();
                    socket = null;
                }
            }
            public void SendMsg(string Msg)     //  메세지 보내기.
            {
                int bufferCount = 0;
                byte[] buffer = new byte[4096];
                buffer = BitConverter.GetBytes((int)DataPacketType.TEXT);
                buffer = ASCIIEncoding.UTF8.GetBytes(Msg);
                bufferCount = ASCIIEncoding.UTF8.GetByteCount(Msg);
                textView.AppendText(Msg + "\n");
                foreach (SocketHandler socketHandler in BroadcastServer.socketList)
                {
                    socketHandler.socket.Send(buffer, 0, bufferCount, SocketFlags.None);
                }
            }
            public void end()
            {
                threadHandler.Abort();
            }
         
        }

        private void button2_Click(object sender, EventArgs e)
        {
            
        }
        // 메세지를 보내는 Method
        
    }
}
