package test2;

import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
public class CardManagement extends JFrame{
  JLabel nameLabel,sexLabel,titleLabel,unitLabel;
  JLabel addressLabel,telLabel1,telLabel2,mobileLabel,faxLabel,emailLabel;
  JTextField nameTxt,unitTxt,addressTxt,telTxt1,telTxt2,mobileTxt,faxTxt,emailTxt;
  JRadioButton sexBtn1,sexBtn2;
  JComboBox titleBx;
  String title[]={"��","�ܲ�","�ܾ���","����","����","����","Ժ��","У��","�Ƴ�","����","��ʦ"};
  JButton addBtn,firstBtn,nextBtn;
  JPanel centerPanel,sPanel;
  GridBagLayout layout;
  GridBagConstraints constraints;
  
  
  RandomAccessFile file;
  public CardManagement() {
    super("��Ƭ����");
    try{
      file=new RandomAccessFile("card.dat","rw");
      //����RandomAccessFile���Զ�д��ʽ���ļ�card.dat
    }catch(IOException ex){
      System.err.println(ex.getMessage());
      System.exit(0);
    }
    
    Container c=getContentPane();
    c.setLayout(new BorderLayout());
    layout=new GridBagLayout();
    centerPanel=new JPanel(layout);
    sPanel=new JPanel();
    setComponent();//������centerPanel��sPanel�����; 
    c.add(centerPanel,BorderLayout.CENTER);
    c.add(sPanel,BorderLayout.SOUTH);

    pack();
    setVisible(true);
 }//���췽��
  
  public void setComponent(){//��ƺ�����GUI���������
  constraints=new GridBagConstraints();//������������ƶ���
  nameLabel=new JLabel("����");
  nameTxt=new JTextField(10);
  addComponent(nameLabel,0,0,1,1);
  addComponent(nameTxt,0,1,1,2);

  sexLabel=new JLabel("�Ա�");
  sexBtn1=new JRadioButton("��",false);
  sexBtn2=new JRadioButton("Ů",true);
  ButtonGroup group=new ButtonGroup();
  group.add(sexBtn1);
  group.add(sexBtn2);
  addComponent(sexLabel,0,4,1,1);
  addComponent(sexBtn1,0,5,1,1);
  addComponent(sexBtn2,0,6,1,1);
  
  titleLabel=new JLabel("��ν");
 
  titleBx=new JComboBox(title);
  titleBx.setMaximumRowCount(5);
  addComponent(titleLabel,0,7,1,1);
  addComponent(titleBx,0,8,1,1);
  
  unitLabel=new JLabel("������λ");
  unitTxt=new JTextField(30);
  addComponent(unitLabel,1,0,1,1);
  addComponent(unitTxt,1,2,1,8);

  addressLabel=new JLabel("������ַ");
  addressTxt=new JTextField(30);
  addComponent(addressLabel,2,0,1,1);
  addComponent(addressTxt,2,2,1,8);

  telLabel1=new JLabel("�绰");
  telTxt1=new JTextField(15);
  addComponent(telLabel1,3,0,1,1);
  addComponent(telTxt1,3,1,1,4);

  telLabel2=new JLabel("�绰");
  telTxt2=new JTextField(15);
  addComponent(telLabel2,3,5,1,1);
  addComponent(telTxt2,3,6,1,4);

  mobileLabel=new JLabel("�ֻ�");
  mobileTxt=new JTextField(15);
  addComponent(mobileLabel,4,0,1,1);
  addComponent(mobileTxt,4,1,1,4);

  faxLabel=new JLabel("����");
  faxTxt=new JTextField(15);
  addComponent(faxLabel,4,5,1,1);
  addComponent(faxTxt,4,6,1,4);

  emailLabel=new JLabel("E-mail");
  emailTxt=new JTextField(32);
  addComponent(emailLabel,5,0,1,1);
  addComponent(emailTxt,5,1,1,8);

  addBtn=new JButton("���Ӽ�¼");
  firstBtn=new JButton("��һ��");
  nextBtn=new JButton("��һ��");
   addBtn.addActionListener(new addBtnListener());
   firstBtn.addActionListener(new firstBtnBtnListener());
   nextBtn.addActionListener(new nextBtnListener());
   sPanel.add(firstBtn);
   sPanel.add(nextBtn);
  sPanel.add(addBtn);
    }//end of �������
  
void addComponent(Component component, int col, int row,int height,int width){
//�����component���õ���row��col��ռ�ݿ�width��height��λ�ã�
   constraints.gridx=row;
   constraints.gridy=col;
  
   constraints.gridwidth=width;
   constraints.gridheight=height;
  
   constraints.fill=GridBagConstraints.BOTH;
  
   constraints.weightx=100;
   constraints.weighty=200;
  
   layout.setConstraints(component,constraints);
   centerPanel.add(component);
 }

  public void writeCard() throws IOException{//����¼д���ļ���
   file.seek(file.length());//ָ���ļ���β��
   file.writeChars(nameTxt.getText()+"\n");
   if(sexBtn1.isSelected())
    file.writeInt(1);
   else
   file.writeInt(2);
   file.writeInt(titleBx.getSelectedIndex());
   file.writeChars(unitTxt.getText()+"\n");
   file.writeChars(addressTxt.getText()+"\n");
   file.writeChars(telTxt1.getText()+"\n");
   file.writeChars(telTxt2.getText()+"\n");
   file.writeChars(mobileTxt.getText()+"\n");
   file.writeChars(faxTxt.getText()+"\n");
   file.writeChars(emailTxt.getText()+"\n");
   }//end of writeCard;
  public void readCard(long position)throws IOException{
	//����¼position��ָ�ļ�¼��
	file.seek(position);//ȷ���ļ�ָ���λ��
	file.readUTF();
	file.readInt();
	file.readInt();
	file.readUTF();
	file.readUTF();
	file.readUTF();
	file.readUTF();
	file.readUTF();
	file.readUTF();
	file.readUTF();
	}
  
  class addBtnListener implements ActionListener{
  public void actionPerformed(ActionEvent e){
  
    try{
      if(e.getSource()==addBtn){
      writeCard();
       }
    }catch(IOException ex){
      System.err.println("д��/��ȡ����ʧ��");
  }
  }
  }
    class nextBtnListener implements ActionListener{
    	 public void actionPerformed(ActionEvent e)
    
    {
    	try {
        long currentPosition=file.getFilePointer();
    	if(currentPosition<file.length())
		
				readCard(currentPosition);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	}
 
  }//end of actioniPerformed

  class firstBtnBtnListener implements ActionListener{
	  public void actionPerformed(ActionEvent e){

  try {
	
	  readCard(0);
} catch (IOException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
  }
	  }
  public static void main(String[] args) {
    CardManagement card = new CardManagement();
    card.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }//end of main
}