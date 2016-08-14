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
using System.Windows.Input;
using System.Windows.Forms;
using System.Drawing.Imaging;
using System.Runtime.InteropServices;
using System.Speech.Synthesis;
using System.Speech.Recognition;
using System.Speech.AudioFormat;
using DirectShowLib;
using Microsoft.DirectX;
using Microsoft.DirectX.DirectInput;
using Microsoft.DirectX.AudioVideoPlayback;
using Microsoft.DirectX.Diagnostics;
using Microsoft.DirectX.Direct3D;
using Microsoft.DirectX.DirectDraw;
using Microsoft.DirectX.PrivateImplementationDetails;
using Microsoft.DirectX.Security;
using Microsoft.Xna.Framework.Audio;
using Microsoft.DirectX.DirectPlay;
using Microsoft.DirectX.DirectSound;
using Microsoft.VisualBasic;
using System.Media;

using System.Security;

namespace Client
{

    public partial class Form1 : Form
    {
        private SoundPlayer Player = new SoundPlayer();
        private TcpClient mClient;
        private Thread mThreadReceive;
        private Thread mThreadSend;
        byte[] m_clientData = null;
        Socket mySocket = null;
        Socket textSocket = null;
        public Thread threadHandler;
        StreamReader mReader;
        StreamWriter mWriter;
        private Boolean leader_state = true;
        enum DataPacketType { TEXT = 1, IMAGE };
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
        
        }

        void Connect(String ip, int port)
        {
            IPEndPoint ipep = new IPEndPoint(IPAddress.Parse(ip), port);
            mySocket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
            mySocket.Connect(ipep);
            //this.mClient = new TcpClient();
            ////Server 연결
            //this.mClient.Connect(ip, port);
            textView.AppendText("서버와 연결\n");
            if (port == 9000)
            {
                Thread testThread = new Thread(new ThreadStart(monitorThread));
                testThread.Start();
                Thread captureThread = new Thread(new ThreadStart(captureHandler));
                captureThread.Start();
            }
        
        }                                                                                                                                        
        void textConnect(String ip, int port)
        {
            IPEndPoint ipep = new IPEndPoint(IPAddress.Parse(ip), port);
            textSocket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
            textSocket.Connect(ipep);
            Thread testThread = new Thread(new ThreadStart(Handler));
            testThread.Start();
        }

        public void Handler()                   //  실질적인 서버작업
        {
            int bufferCount;
            //SendMsg((BroadcastServer.socketList.Count) + "명이 접속해 있습니다.");
            try
            {
                while (true)
                {
                    byte[] buffer = new byte[4096];
                    //mySocket.Receive(buffer);
                    bufferCount = textSocket.Receive(buffer);
                    if (bufferCount == 0) break;
                    //int state = BitConverter.ToInt32(buffer, 0);
                    
                    string Msgs = ASCIIEncoding.UTF8.GetString(buffer);
                    byte[] byteimsi = new byte[bufferCount];    // 남은버퍼 없애기용 임시byte
                    for (int i = 0; i < bufferCount; i++)
                    {
                        byteimsi[i] = buffer[i];
                    }
                    Msgs = ASCIIEncoding.UTF8.GetString(byteimsi);
                    textView.AppendText(Msgs+"\n");
                    
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e.ToString());
            }
            finally
            {
                textSocket.Close();
                textSocket = null;
            }
        }


        public void SendMsg(string Msg)     //  메세지 보내기.
        {
            int bufferCount = 0;
            byte[] buffer = new byte[4096];
            buffer = ASCIIEncoding.UTF8.GetBytes(Msg);
            bufferCount = ASCIIEncoding.UTF8.GetByteCount(Msg);
            textSocket.Send(buffer, 0, bufferCount, SocketFlags.None);
        }
        
        private void SendData2(Socket mySocket)
        {
            Bitmap capture = captureScreen();
            MemoryStream imageStream = new MemoryStream();
            capture.Save(imageStream, ImageFormat.Jpeg);
            imageStream.Position = 0;

            // Do something with the memory stream. For example:
            byte[] imageBytes = imageStream.ToArray();
            // Save bytes to the database.

            //FileStream fileStr = new FileStream("test.png", FileMode.Open, FileAccess.Read);
            //int fileLength = (int)fileStr.Length;
           
            //pictureBox1.Image = image;
            byte[] buffer = BitConverter.GetBytes((int)DataPacketType.IMAGE);

            //mWriter.Write(buffer);
            mySocket.Send(buffer);
            buffer.Initialize();
            buffer = BitConverter.GetBytes(imageBytes.Length);
            mySocket.Send(buffer);
            int count = imageBytes.Length / 4096 + 1;
            //BinaryReader reader = new BinaryReader(fileStr);
            mySocket.Send(imageBytes);
          
        }

        /****************캡쳐화면 handler*********************/
        public void captureHandler()                   //  실질적인 서버작업
        {
            byte[] buffer = new byte[4096];
            int bufferCount;
            try
            {
                while (true)
                {
                    byte[] buffer1 = new byte[4];
                    bufferCount = mySocket.Receive(buffer1);
                    //textView.AppendText("" + bufferCount+"\n");
                    if (bufferCount == 0) break;
                    int state = BitConverter.ToInt32(buffer1, 0);

                    if (state == (int)DataPacketType.IMAGE)
                    {
                        pictureBox1.Visible = true;
                        hidden_view.AppendText("사진 전송 시작!\n");
                        int totalLength = 0;
                        mySocket.Receive(buffer1);
                        int fileLength = BitConverter.ToInt32(buffer1, 0);
                        buffer1 = new byte[fileLength];
                        mySocket.Receive(buffer1);
                        Bitmap image = ByteToImage(buffer1);
                        pictureBox1.Image = image;
                        pictureBox1.SizeMode = PictureBoxSizeMode.StretchImage;
                    }
                    else
                    {
                        pictureBox1.Visible = false;
                    }
                }
            }
            catch (Exception e)
            {
                textView.AppendText(e.ToString());
            }
            finally
            {
                mySocket.Close();
                mySocket = null;
            }
        }
        /*****************************************************/

        public static Bitmap ByteToImage(byte[] blob)
        {
            MemoryStream mStream = new MemoryStream();
            byte[] pData = blob;
            mStream.Write(pData, 0, Convert.ToInt32(pData.Length));
            Bitmap bm = new Bitmap(mStream, false);
            mStream.Dispose();
            return bm;

        }
        private void sendImage_Click(object sender, EventArgs e)
        {
            //textConnect("117.17.142.136", 9001);
            Connect("192.168.200.175", 9000);
        }
        private Bitmap captureScreen()
        {
            //화면 해상도를 구함 
            //int w = Screen.PrimaryScreen.Bounds.Width; 
            //int h = Screen.PrimaryScreen.Bounds.Height;

            //작업 표시줄을 제외한 영역 크기 
            int w = Screen.PrimaryScreen.WorkingArea.Width;
            int h = Screen.PrimaryScreen.WorkingArea.Height;

            //size 객체 생성
            Size s = new Size(w, h);

            //Bitmap 객체 생성 
            Bitmap b = new Bitmap(w, h);
            
            //Graphics 객체 생성 
            Graphics g = Graphics.FromImage(b);
            //Graphics 객체의 CopyFromScreen()메서드로 bitmap 객체에 Screen을 캡처하여 저장 
            g.CopyFromScreen(0, 0, 0, 0, s);
            int width = b.Width / 3;
            int height = b.Height / 3;
            Size resize = new Size(width, height);
            Bitmap resizeImage = new Bitmap(b, resize);
            //resizeImage.Save(Application.StartupPath + "\\" + "test.png", System.Drawing.Imaging.ImageFormat.Jpeg);
            
            return resizeImage;
            
        }
        void monitorThread()
        {
            while (true)
            {
                SendData2(mySocket);
               
                Thread.Sleep(100);
            }
        }
        void textThread()
        {
            //IPEndPoint ipep = new IPEndPoint(IPAddress.Parse("117.17.142.136"), 9000);
            //Socket mySocket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
            //mySocket.Connect(ipep);
            int count = 1;
            while (count == 1)
            {
                Thread.Sleep(5000);
            }
        }

        // 대화 시작 버튼
        private void button1_Click_1(object sender, EventArgs e)
        {
            String _ip;
            int _port;
            // Console.Write("server ip (127.0.0.1):");
            //_ip = Convert.ToString(Console.ReadLine());
            _ip = "117.17.142.139";
            //Console.Write("server port (8080):");
            //_port = Convert.ToInt32(Console.ReadLine());
            _port = 9000;
            Connect(_ip, _port);
        }
        
        // 메세지를 보내는 Method
        private void send_btn_Click(object sender, EventArgs e)
        {
            //try
            //{
            //    String _str = String.Format(textBox1.Text);
            //    mWriter.WriteLine(_str);
            //    mWriter.Flush();
            //}
            //catch (NullReferenceException)
            //{
            //}
            //catch (IOException)
            //{
            //    this.mClient.Close();
            //    this.mThreadSend.Abort();
            //    // this.mThreadReceive.Abort();
            //}
            String _str = String.Format(textBox1.Text);
            SendMsg(_str);
            textBox1.Text = null;
        }

        // 방 개설 버튼
        private void createBtn_Click(object sender, EventArgs e)
        {
            //if (leader_state)
            //    createSocket();
            //else
            //joinSocket();

        }

        
        private void button2_Click(object sender, EventArgs e)
        {
        }
    
        
        private void button3_Click(object sender, EventArgs e)
        {
           

        }

        public void LoadAsyncSound(String path)
        {
            try
            {
                // Replace this file name with a valid file name.
                this.Player.SoundLocation = path;
                this.Player.LoadAsync();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Error loading sound");
            }
        }
        void Player_LoadCompleted(object sender, AsyncCompletedEventArgs e)
        {
            if (Player.IsLoadCompleted)
            {
                try
                {
                    this.Player.Play();
                }
                catch (Exception ex)
                {
                    MessageBox.Show(ex.Message, "Error playing sound");
                }
            }
        }

    }

}