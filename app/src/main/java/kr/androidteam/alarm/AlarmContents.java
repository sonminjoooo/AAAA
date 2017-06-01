package kr.androidteam.alarm;

import java.util.ArrayList;

import kr.androidteam.alarm.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AlarmContents extends Activity implements OnClickListener, OnCheckedChangeListener, android.widget.RadioGroup.OnCheckedChangeListener{


	ArrayList<String> timeTest = new ArrayList<String>();
	String string,time;
	Button btn2,btn3;
	CheckBox mon,tue,wed,thu,fri,sat,sun;
	String [] days=new String[7]; // 월~금까지 체크가 된것을 나타냄
	//시간은 나중에 드래그로 표현할거니까 이해안해도됨
	RadioGroup rg;
	RadioButton decibel, word;
	ImageButton sms,Memo;
	String number;
	String memo;
	boolean[] check=new boolean[2]; 
	 final static int DIALOG_1 =0;
	 final static int DIALOG_2 = 1;

	LayoutInflater inflater;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alarm_contents);
		check[0]=check[1]=false;
		
		for(int i=0;i<7;i++)  //초기에 스트링어레이를 0으로 초기화
			days[i]="0";
		
		btn2=(Button)findViewById(R.id.insert);
		btn3=(Button)findViewById(R.id.cancel);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		
		mon=(CheckBox)findViewById(R.id.mon);
		thu=(CheckBox)findViewById(R.id.thu);
		wed=(CheckBox)findViewById(R.id.wed);
		tue=(CheckBox)findViewById(R.id.tue);
		fri=(CheckBox)findViewById(R.id.fri);
		sat=(CheckBox)findViewById(R.id.sat);
		sun=(CheckBox)findViewById(R.id.sun);
		
		rg=(RadioGroup)findViewById(R.id.radioGroup1);

		word=(RadioButton)findViewById(R.id.word);


		inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);


		
		rg.setOnCheckedChangeListener(this);
		
		mon.setOnCheckedChangeListener(this);
		thu.setOnCheckedChangeListener(this);
		wed.setOnCheckedChangeListener(this);
		tue.setOnCheckedChangeListener(this);
		fri.setOnCheckedChangeListener(this);
		sat.setOnCheckedChangeListener(this);
		sun.setOnCheckedChangeListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		
		//추가버튼을 눌렀을 때 시간을 지정안했으면 지정하게 한 후 정했으면 바로 보냄
		if(v==btn2){
		
			kr.androidteam.alarm.dragAndDrap drap=(kr.androidteam.alarm.dragAndDrap)findViewById(R.id.drag);
			if(drap.getTime()!=""){
				string=drap.getTime();
				time=drap.getTime();
			}
			
			String option="";
			String sendCheck="";
			if(string == null)
				Toast.makeText(this, "Please set a Time",Toast.LENGTH_SHORT).show();

			else if(!word.isChecked() && !decibel.isChecked() )
				Toast.makeText(this, "Please check a option",Toast.LENGTH_SHORT).show();
			
			else{
				
				StringBuffer sendCheckedDays=new StringBuffer(""); 
				//스트링버퍼로 체크된 요일을 붙여나감
			
				for(int i=0;i<7;i++)
					if(!days[i].equals("0")) //만약 체크가 된 것이면 스트링에 덧붙임
						sendCheckedDays.append(days[i]);
			
				if(word.isChecked())
					option="word";

				else if(decibel.isChecked())
					option="decibel";

				
				if(check[0])
					sendCheck+='1';
				if(check[1])
					sendCheck+='2';
				
				
				Intent it=new Intent(this,MainActivity.class);
				

			setResult(RESULT_OK,it);
			finish();
			}
			
		}
		
		//취소눌렀으면 그냥 뒤로 돌아감
		else if(v==btn3){
			Intent it=new Intent(this,MainActivity.class);
			setResult(RESULT_CANCELED,it);
			finish(); //해당 엑티비티종료
			
		}
	}
	/*
	 * 이 액티비티에서 뒤로가기를 누르면 바로 꺼짐 -> 뒤로가기버튼동작을 제어해야 함
	 * 이기능을 추가할까말까
	 */

	//요일이 눌렸을때 눌린요일에 대한 숫자를 저장, 삭제함
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		if(sun.isChecked())
			days[0]="1";
		
		else
			days[0]="0";
		
		
		if(mon.isChecked())
			days[1]="2";
		
		else
			days[2]="0";
		
		if(tue.isChecked())
			days[2]="3";
		
		else
			days[2]="0";
		
		if(wed.isChecked())
			days[3]="4";
		
		else
			days[3]="0";
		
		if(thu.isChecked())
			days[4]="5";
		
		else
			days[4]="0";
		
		if(fri.isChecked())
			days[5]="6";
		
		else
			days[5]="0";
		
		if(sat.isChecked())
			days[6]="7";
		
		else
			days[6]="0";
		
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		
		switch (checkedId) { //각 버튼 눌렀을 때 계산을 함



		case R.id.word:

			decibel.setChecked(false);
			word.setChecked(true);

			
			break;



		

		}

	}

}
