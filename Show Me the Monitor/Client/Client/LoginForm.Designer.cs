namespace Client
{
    partial class LoginForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(LoginForm));
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.edit_passwd = new System.Windows.Forms.TextBox();
            this.edit_id = new System.Windows.Forms.TextBox();
            this.login_btn = new System.Windows.Forms.Button();
            this.signup_btn = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            this.SuspendLayout();
            // 
            // pictureBox1
            // 
            this.pictureBox1.Image = ((System.Drawing.Image)(resources.GetObject("pictureBox1.Image")));
            this.pictureBox1.Location = new System.Drawing.Point(-2, -2);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(1070, 807);
            this.pictureBox1.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox1.TabIndex = 4;
            this.pictureBox1.TabStop = false;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.BackColor = System.Drawing.SystemColors.Desktop;
            this.label2.Font = new System.Drawing.Font("굴림", 24F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.label2.ForeColor = System.Drawing.SystemColors.ControlLightLight;
            this.label2.Location = new System.Drawing.Point(203, 712);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(143, 32);
            this.label2.TabIndex = 8;
            this.label2.Text = "비밀번호";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.BackColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.label1.Font = new System.Drawing.Font("굴림", 24F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.label1.ForeColor = System.Drawing.SystemColors.ControlLightLight;
            this.label1.Location = new System.Drawing.Point(235, 628);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(111, 32);
            this.label1.TabIndex = 7;
            this.label1.Text = "아이디";
            // 
            // edit_passwd
            // 
            this.edit_passwd.BackColor = System.Drawing.SystemColors.Info;
            this.edit_passwd.Font = new System.Drawing.Font("휴먼편지체", 20.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.edit_passwd.Location = new System.Drawing.Point(352, 706);
            this.edit_passwd.Multiline = true;
            this.edit_passwd.Name = "edit_passwd";
            this.edit_passwd.Size = new System.Drawing.Size(334, 38);
            this.edit_passwd.TabIndex = 6;
            // 
            // edit_id
            // 
            this.edit_id.BackColor = System.Drawing.SystemColors.Info;
            this.edit_id.Font = new System.Drawing.Font("휴먼편지체", 20.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.edit_id.Location = new System.Drawing.Point(352, 628);
            this.edit_id.Multiline = true;
            this.edit_id.Name = "edit_id";
            this.edit_id.Size = new System.Drawing.Size(334, 40);
            this.edit_id.TabIndex = 5;
            // 
            // login_btn
            // 
            this.login_btn.BackColor = System.Drawing.SystemColors.Info;
            this.login_btn.Location = new System.Drawing.Point(765, 628);
            this.login_btn.Name = "login_btn";
            this.login_btn.Size = new System.Drawing.Size(109, 45);
            this.login_btn.TabIndex = 9;
            this.login_btn.Text = "로그인";
            this.login_btn.UseVisualStyleBackColor = false;
            this.login_btn.Click += new System.EventHandler(this.login_btn_Click);
            // 
            // signup_btn
            // 
            this.signup_btn.BackColor = System.Drawing.SystemColors.Info;
            this.signup_btn.Location = new System.Drawing.Point(765, 699);
            this.signup_btn.Name = "signup_btn";
            this.signup_btn.Size = new System.Drawing.Size(109, 45);
            this.signup_btn.TabIndex = 10;
            this.signup_btn.Text = "회원가입";
            this.signup_btn.UseVisualStyleBackColor = false;
            // 
            // LoginForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.SystemColors.ControlDarkDark;
            this.ClientSize = new System.Drawing.Size(1066, 803);
            this.Controls.Add(this.signup_btn);
            this.Controls.Add(this.login_btn);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.edit_passwd);
            this.Controls.Add(this.edit_id);
            this.Controls.Add(this.pictureBox1);
            this.Name = "LoginForm";
            this.Text = "LoginForm";
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.PictureBox pictureBox1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox edit_passwd;
        private System.Windows.Forms.TextBox edit_id;
        private System.Windows.Forms.Button login_btn;
        private System.Windows.Forms.Button signup_btn;

    }
}