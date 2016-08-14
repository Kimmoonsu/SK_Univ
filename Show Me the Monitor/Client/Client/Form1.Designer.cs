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
            this.components = new System.ComponentModel.Container();
            this.chatGroup = new System.Windows.Forms.GroupBox();
            this.textBox1 = new System.Windows.Forms.TextBox();
            this.textView = new System.Windows.Forms.TextBox();
            this.send_btn = new System.Windows.Forms.Button();
            this.sendImage = new System.Windows.Forms.Button();
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            this.imageList1 = new System.Windows.Forms.ImageList(this.components);
            this.hidden_view = new System.Windows.Forms.TextBox();
            this.chatGroup.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            this.SuspendLayout();
            // 
            // chatGroup
            // 
            this.chatGroup.BackColor = System.Drawing.SystemColors.ActiveCaption;
            this.chatGroup.Controls.Add(this.textBox1);
            this.chatGroup.Controls.Add(this.textView);
            this.chatGroup.Controls.Add(this.send_btn);
            this.chatGroup.Location = new System.Drawing.Point(719, 102);
            this.chatGroup.Name = "chatGroup";
            this.chatGroup.Size = new System.Drawing.Size(280, 520);
            this.chatGroup.TabIndex = 3;
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
            // textView
            // 
            this.textView.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(255)))), ((int)(((byte)(192)))));
            this.textView.Font = new System.Drawing.Font("휴먼편지체", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.textView.Location = new System.Drawing.Point(19, 20);
            this.textView.Multiline = true;
            this.textView.Name = "textView";
            this.textView.Size = new System.Drawing.Size(243, 426);
            this.textView.TabIndex = 2;
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
            this.send_btn.Click += new System.EventHandler(this.send_btn_Click);
            // 
            // sendImage
            // 
            this.sendImage.BackColor = System.Drawing.SystemColors.ActiveCaption;
            this.sendImage.Location = new System.Drawing.Point(719, 43);
            this.sendImage.Name = "sendImage";
            this.sendImage.Size = new System.Drawing.Size(274, 41);
            this.sendImage.TabIndex = 5;
            this.sendImage.Text = "방 참가";
            this.sendImage.UseVisualStyleBackColor = false;
            this.sendImage.Click += new System.EventHandler(this.sendImage_Click);
            // 
            // pictureBox1
            // 
            this.pictureBox1.Location = new System.Drawing.Point(12, 12);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(683, 633);
            this.pictureBox1.TabIndex = 8;
            this.pictureBox1.TabStop = false;
            // 
            // imageList1
            // 
            this.imageList1.ColorDepth = System.Windows.Forms.ColorDepth.Depth8Bit;
            this.imageList1.ImageSize = new System.Drawing.Size(16, 16);
            this.imageList1.TransparentColor = System.Drawing.Color.Transparent;
            // 
            // hidden_view
            // 
            this.hidden_view.BackColor = System.Drawing.SystemColors.MenuBar;
            this.hidden_view.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.hidden_view.ForeColor = System.Drawing.SystemColors.MenuBar;
            this.hidden_view.Location = new System.Drawing.Point(828, 628);
            this.hidden_view.Name = "hidden_view";
            this.hidden_view.Size = new System.Drawing.Size(100, 14);
            this.hidden_view.TabIndex = 9;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1038, 657);
            this.Controls.Add(this.hidden_view);
            this.Controls.Add(this.pictureBox1);
            this.Controls.Add(this.sendImage);
            this.Controls.Add(this.chatGroup);
            this.Name = "Form1";
            this.Text = "Form1";
            this.chatGroup.ResumeLayout(false);
            this.chatGroup.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private System.Windows.Forms.GroupBox chatGroup;
        private System.Windows.Forms.TextBox textBox1;
        private System.Windows.Forms.TextBox textView;
        private System.Windows.Forms.Button send_btn;
        private System.Windows.Forms.Button sendImage;
        private System.Windows.Forms.PictureBox pictureBox1;
        private System.Windows.Forms.ImageList imageList1;
        private System.Windows.Forms.TextBox hidden_view;
    }
}

