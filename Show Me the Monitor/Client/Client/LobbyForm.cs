using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Client
{
    public partial class LobbyForm : Form
    {
        public LobbyForm()
        {
            InitializeComponent();
        }
        private int button_X = 30;
        private int button_Y = 30;
        private void make_btn_Click(object sender, EventArgs e)
        {
            //PictureBox[] screenbox = new PictureBox[5];
            //screenbox[1] = new PictureBox();
            //screenbox[1].Load("logo1.png");
            //screenbox[1].Name = string.Format("test");
            //screenbox[1].Location = new Point(30, 30);

            //screenbox[1].Size = new Size(361, 267);
            //screenbox[1].SizeMode = PictureBoxSizeMode.StretchImage;
            //this.Controls.Add(screenbox[1]);
            Button[] BT = new Button[8];

            for (int i = 0; i < 8; i++)
            {
                BT[i] = new Button();
                BT[i].Name = "bt" + i.ToString();
                BT[i].Location = new Point(button_X, button_Y);
                BT[i].Text = "방 : " + (i + 1);
                BT[i].Width = 90;
                BT[i].Height = 30;
                BT[i].FlatStyle = System.Windows.Forms.FlatStyle.Flat;
                BT[i].BackColor = Color.LightYellow;
                BT[i].TabIndex = 9;
                if (button_X > 600)
                {
                    button_X = 30;
                    button_Y += 60;
                }
                else
                    button_X += 120;

                this.Controls.Add(BT[i]);
            }
            Form makeform = new MakeForm(this);
            makeform.Show();
        }
    }
}
