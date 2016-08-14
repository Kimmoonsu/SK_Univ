namespace Client
{
    partial class Form1
    {
        /// <summary>
        /// 필수 디자이너 변수입니다.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// 사용 중인 모든 리소스를 정리합니다.
        /// </summary>
        /// <param name="disposing">관리되는 리소스를 삭제해야 하면 true이고, 그렇지 않으면 false입니다.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form 디자이너에서 생성한 코드

        /// <summary>
        /// 디자이너 지원에 필요한 메서드입니다.
        /// 이 메서드의 내용을 코드 편집기로 수정하지 마십시오.
        /// </summary>
        private void InitializeComponent()
        {
            this.button1 = new System.Windows.Forms.Button();
            this.textView = new System.Windows.Forms.TextBox();
            this.button2 = new System.Windows.Forms.Button();
            this.chatGroup = new System.Windows.Forms.GroupBox();
            this.textBox1 = new System.Windows.Forms.TextBox();
            this.send_btn = new System.Windows.Forms.Button();
            this.screenBox1 = new System.Windows.Forms.PictureBox();
            this.screenBox2 = new System.Windows.Forms.PictureBox();
            this.screenBox3 = new System.Windows.Forms.PictureBox();
            this.screenBox4 = new System.Windows.Forms.PictureBox();
            this.screenBox5 = new System.Windows.Forms.PictureBox();
            this.hidden_view = new System.Windows.Forms.TextBox();
            this.chatGroup.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.screenBox1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.screenBox2)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.screenBox3)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.screenBox4)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.screenBox5)).BeginInit();
            this.SuspendLayout();
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(910, 583);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(143, 70);
            this.button1.TabIndex = 0;
            this.button1.Text = "방 만들기";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // textView
            // 
            this.textView.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(255)))), ((int)(((byte)(192)))));
            this.textView.Font = new System.Drawing.Font("휴먼편지체", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.textView.Location = new System.Drawing.Point(22, 20);
            this.textView.Multiline = true;
            this.textView.Name = "textView";
            this.textView.Size = new System.Drawing.Size(239, 419);
            this.textView.TabIndex = 1;
            // 
            // button2
            // 
            this.button2.Location = new System.Drawing.Point(1087, 630);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(75, 23);
            this.button2.TabIndex = 0;
            this.button2.Text = "stop";
            this.button2.Click += new System.EventHandler(this.button2_Click);
            // 
            // chatGroup
            // 
            this.chatGroup.BackColor = System.Drawing.SystemColors.ActiveCaption;
            this.chatGroup.Controls.Add(this.textBox1);
            this.chatGroup.Controls.Add(this.send_btn);
            this.chatGroup.Controls.Add(this.textView);
            this.chatGroup.Location = new System.Drawing.Point(888, 12);
            this.chatGroup.Name = "chatGroup";
            this.chatGroup.Size = new System.Drawing.Size(280, 520);
            this.chatGroup.TabIndex = 4;
            this.chatGroup.TabStop = false;
            this.chatGroup.Text = "Chatting";
            // 
            // textBox1
            // 
            this.textBox1.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(255)))), ((int)(((byte)(192)))));
            this.textBox1.Font = new System.Drawing.Font("휴먼편지체", 11.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.textBox1.Location = new System.Drawing.Point(22, 465);
            this.textBox1.Multiline = true;
            this.textBox1.Name = "textBox1";
            this.textBox1.Size = new System.Drawing.Size(187, 37);
            this.textBox1.TabIndex = 3;
            // 
            // send_btn
            // 
            this.send_btn.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(255)))), ((int)(((byte)(192)))));
            this.send_btn.Location = new System.Drawing.Point(222, 462);
            this.send_btn.Name = "send_btn";
            this.send_btn.Size = new System.Drawing.Size(52, 41);
            this.send_btn.TabIndex = 1;
            this.send_btn.Text = "전송";
            this.send_btn.UseVisualStyleBackColor = false;
            // 
            // screenBox1
            // 
            this.screenBox1.Location = new System.Drawing.Point(12, 12);
            this.screenBox1.Name = "screenBox1";
            this.screenBox1.Size = new System.Drawing.Size(410, 314);
            this.screenBox1.TabIndex = 2;
            this.screenBox1.TabStop = false;
            // 
            // screenBox2
            // 
            this.screenBox2.Location = new System.Drawing.Point(439, 12);
            this.screenBox2.Name = "screenBox2";
            this.screenBox2.Size = new System.Drawing.Size(410, 314);
            this.screenBox2.TabIndex = 5;
            this.screenBox2.TabStop = false;
            // 
            // screenBox3
            // 
            this.screenBox3.Location = new System.Drawing.Point(12, 351);
            this.screenBox3.Name = "screenBox3";
            this.screenBox3.Size = new System.Drawing.Size(410, 314);
            this.screenBox3.TabIndex = 6;
            this.screenBox3.TabStop = false;
            // 
            // screenBox4
            // 
            this.screenBox4.Location = new System.Drawing.Point(439, 351);
            this.screenBox4.Name = "screenBox4";
            this.screenBox4.Size = new System.Drawing.Size(410, 314);
            this.screenBox4.TabIndex = 7;
            this.screenBox4.TabStop = false;
            // 
            // screenBox5
            // 
            this.screenBox5.Location = new System.Drawing.Point(4, 3);
            this.screenBox5.Name = "screenBox5";
            this.screenBox5.Size = new System.Drawing.Size(873, 686);
            this.screenBox5.TabIndex = 8;
            this.screenBox5.TabStop = false;
            // 
            // hidden_view
            // 
            this.hidden_view.ForeColor = System.Drawing.SystemColors.Window;
            this.hidden_view.Location = new System.Drawing.Point(1083, 583);
            this.hidden_view.Name = "hidden_view";
            this.hidden_view.Size = new System.Drawing.Size(100, 21);
            this.hidden_view.TabIndex = 9;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1195, 709);
            this.Controls.Add(this.hidden_view);
            this.Controls.Add(this.screenBox5);
            this.Controls.Add(this.screenBox4);
            this.Controls.Add(this.screenBox3);
            this.Controls.Add(this.screenBox2);
            this.Controls.Add(this.chatGroup);
            this.Controls.Add(this.screenBox1);
            this.Controls.Add(this.button2);
            this.Controls.Add(this.button1);
            this.Name = "Form1";
            this.Text = "Form1";
            this.chatGroup.ResumeLayout(false);
            this.chatGroup.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.screenBox1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.screenBox2)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.screenBox3)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.screenBox4)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.screenBox5)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.TextBox textView;
        private System.Windows.Forms.Button button2;
        private System.Windows.Forms.GroupBox chatGroup;
        private System.Windows.Forms.TextBox textBox1;
        private System.Windows.Forms.Button send_btn;
        private System.Windows.Forms.PictureBox screenBox1;
        private System.Windows.Forms.PictureBox screenBox2;
        private System.Windows.Forms.PictureBox screenBox3;
        private System.Windows.Forms.PictureBox screenBox4;
        private System.Windows.Forms.PictureBox screenBox5;
        private System.Windows.Forms.TextBox hidden_view;
    }
}

