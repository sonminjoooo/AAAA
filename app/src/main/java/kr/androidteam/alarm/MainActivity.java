package kr.androidteam.alarm;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements OnClickListener,
		OnItemClickListener, OnItemLongClickListener, OnItemSelectedListener {



	public static Activity IntroAct;
	
	ImageView bottom;
	RelativeLayout background;
	ImageButton btn1; //추가버튼
	int CREATE_REQUEST = 1; // 리스트에 추가할때 requestcode와 맞는지
	int DELETEorCHANGE_REQUEST = 2; // 리스트에 수정할때 requestcode와 맞는지
	ListView list;
	int count = 0; // 리스트 뷰에 추가할때 어레이리스트의 갯수를 세주는 변수
	int number; // 리스트 뷰를 수정,삭제할때 어레이리스트가 몇번째인지 세주는 변수
	AlertDialog.Builder builder;  //dialog창을 위한 변수

	public static final String MYPREFS="AlarmLOG"; //알람 기록을 남김
	final int mode=Activity.MODE_PRIVATE;
	StringBuffer savedAlarmInfo=new StringBuffer(); //알람의 정보를 저장했다가 다시 불러오는 역할
	
	int request; // 알람을 등록하기 위한 요청 코드 - 코드당 1개의 알람만 등록이 됨
	int[] time = new int[2]; // 어레이 0번째엔 시, 1번째엔 분이 들어감
	int[] days;  //체크된 요일을 숫자로 체크한다.
	
	boolean toastFlag=false;
	boolean onBackground=false;
	
	int icon=R.drawable.time;
	String tickerText="알람등록";
	long when=System.currentTimeMillis();
	Notification notification=new Notification(icon,tickerText,when);
	String serName=Context.NOTIFICATION_SERVICE;
	NotificationManager NM;
		@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		IntroAct=this;
		bottom=(ImageView)findViewById(R.id.bottom);
		background=(RelativeLayout)findViewById(R.id.background);
		btn1 = (ImageButton) findViewById(R.id.button1);
		btn1.setOnClickListener(this);
		list = (ListView) findViewById(R.id.list);
		//customview를 위한 클래스를 지정해줌.
		SharedPreferences sh_Pref = getSharedPreferences(MYPREFS,mode);

		// 이 조건이 맞으면 알람의정보가 1개라도 들어있다는 뜻
		if(sh_Pref!=null && sh_Pref.contains("saveInfo") && sh_Pref.contains("savedInfoSize"))

		/*
		 * 알람이 울린다음 알람을 끌 때 이것이 단일알람인지 아닌지 값을 보내준다. 울린 알람의 포지션번호를 받아 단일알람이면
		 * 비활성화 시키고 단일알람이 아닐 시 그대로 냅둠
		 */
		





		list.setOnItemClickListener(this);
		list.setOnItemLongClickListener(this);
		list.setOnItemSelectedListener(this);
		
		NM=(NotificationManager)getSystemService(serName);
		Intent notifiIntent=new Intent(MainActivity.this,MainActivity.class);
		
		PendingIntent pi=PendingIntent.getActivity(MainActivity.this,0,
				notifiIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		
		NM=(NotificationManager)getSystemService(serName);
		notification.flags=Notification.FLAG_NO_CLEAR;
		notification.setLatestEventInfo(this, "알라미", "알람이 등록되었습니다.", pi);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void registerNotification(){
		
			NM.notify(1, notification);
			
		}
		
		public void notificationCancel(){
			NM.cancel(1);
		}

	public void alarmOn(){
		Resources res=getResources();
		
		TransitionDrawable drawable=(TransitionDrawable) res.getDrawable(R.drawable.wakebackground);
		background.setBackground(drawable);
		drawable.startTransition(1000);

		TransitionDrawable drawable2=(TransitionDrawable) res.getDrawable(R.drawable.wakebottom);
		bottom.setImageDrawable(drawable2);
		drawable2.startTransition(1000);
		
		btn1.setImageResource(R.drawable.wakebutton);
		TransitionDrawable drawableBtn=(TransitionDrawable) btn1.getDrawable();
		drawableBtn.startTransition(1000);
	}
	
	public void alarmOff(){
		Resources res=getResources();
		
		TransitionDrawable drawable=(TransitionDrawable) res.getDrawable(R.drawable.convertbackground);
		background.setBackground(drawable);
		drawable.startTransition(1000);

		TransitionDrawable drawable2=(TransitionDrawable) res.getDrawable(R.drawable.convertbottom);
		bottom.setImageDrawable(drawable2);
		drawable2.startTransition(1000);
		

		btn1.setImageResource(R.drawable.convertbutton);
		TransitionDrawable drawableBtn=(TransitionDrawable) btn1.getDrawable();
		drawableBtn.startTransition(1000);
	}
	









	


		


	


	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		
		
	}


	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onClick(View view) {

	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

	}

	@Override
	public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
		return false;
	}
}