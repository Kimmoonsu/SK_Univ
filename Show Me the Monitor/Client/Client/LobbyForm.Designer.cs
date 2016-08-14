namespace Client
{
    partial class LobbyForm
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
            this.make_btn = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // make_btn
            // 
            this.make_btn.BackColor = System.Drawing.SystemColors.Info;
            this.make_btn.Font = new System.Drawing.Font("휴먼편지체", 27.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.make_btn.Location = new System.Drawing.Point(610, 506);
            this.make_btn.Name = "make_btn";
            this.make_btn.Size = new System.Drawing.Size(235, 80);
            this.make_btn.TabIndex = 0;
            this.make_btn.Text = "방만들기";
            this.make_btn.UseVisualStyleBackColor = false;
            this.make_btn.Click += new System.EventHandler(this.make_btn_Click);
            // 
            // LobbyForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(884, 668);
            this.Controls.Add(this.make_btn);
            this.Name = "LobbyForm";
            this.Text = "LobbyForm";
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button make_btn;
    }
}